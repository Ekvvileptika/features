package az.ekvileptika.utils

import kotlinx.coroutines.*

inline fun CoroutineScope.launchIO(
    crossinline safeAction: suspend () -> Unit,
    crossinline onError: (Throwable) -> Unit,
    errorDispatcher: CoroutineDispatcher = Dispatchers.Main
): Job{
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        launch(errorDispatcher) {
            onError.invoke(throwable)
        }
    }

    return this.launch(exceptionHandler + Dispatchers.IO) {
        safeAction.invoke()
    }
}

inline fun CoroutineScope.launchMain(
    crossinline safeAction: suspend () -> Unit,
    crossinline onError: (Throwable) -> Unit
): Job{
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        launch(Dispatchers.Main) {
            onError.invoke(throwable)
        }
    }

    return this.launch(exceptionHandler + Dispatchers.Main) {
        safeAction.invoke()
    }
}