import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull

fun <T> apiRequestFlow(call: suspend () -> Result<T>) = flow {
    //loading
    withTimeoutOrNull(20_000L) {
        try {
            val response = call()

            when {
                response.isFailure -> {
                    emit( Result.failure<Throwable>(Throwable(response.exceptionOrNull())) )
                }

                response.isSuccess -> {
                    emit( Result.success(response) )
                }

                else -> {
                    Result.failure<Throwable>(Throwable("Another error"))
                }
            }

        } catch (ex: Exception) {
            Result.failure<Throwable>(ex)
        }
    } ?: emit(Result.failure<Throwable>(Throwable("TimeOut")))

}.flowOn(Dispatchers.IO)


/// using
@HiltViewModel
class SomeViewModel @Inject constructor(
    private val retrofitApi: RetrofitAPI
): ViewModel() {
    var job: Job? = null

    fun makeRequest() {
        job = viewModelScope.launch {
            apiRequestFlow {
                retrofitApi.getSomeData()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()

        job?.let {
            if(it.isActive) it.cancel()
        }
    }
}