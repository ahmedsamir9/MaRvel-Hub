package com.example.marvelhub.application.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.*
import com.example.marvelhub.data.repository.LocalDataSource
import com.example.marvelhub.domain.useCases.ReleaseAllCharactersUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

@HiltWorker
public class DeleteWorker @AssistedInject constructor(@Assisted private val appContext: Context
                                                   , @Assisted private val workerParams: WorkerParameters, private val releaseAllCharactersUseCase: LocalDataSource ):
CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        return try {
            Timber.tag("SSSSSSSSSSSSSSiiiiiiiiiiiiiiiiiiiiiiiiiii").d("in work")
withContext(Dispatchers.IO){
    releaseAllCharactersUseCase.releaseAllCharacterData()
}

            ListenableWorker.Result.success()
        }catch (ex:Exception){
            ListenableWorker.Result.failure()
        }
    }

}