package com.example.myapplication.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Configuration
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.myapplication.notifications.Notifications
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MyWorkManager(
    private val context: Context,
    private val params: WorkerParameters
): CoroutineWorker(context, params) {
    private val log = "#WorkManager"

    override suspend fun doWork(): Result {
        working()

        return Result.success()
    }

    private suspend fun working() = withContext(Dispatchers.IO){
        Log.e(log, ": working some functions")
        val note = Notifications(context)
        note.makeNotification("Background", "Description")
    }
}