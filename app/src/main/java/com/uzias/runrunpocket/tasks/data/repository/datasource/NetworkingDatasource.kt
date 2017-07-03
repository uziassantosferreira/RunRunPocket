package com.uzias.runrunpocket.tasks.data.repository.datasource

import com.uzias.runrunpocket.tasks.domain.model.Task
import io.reactivex.Completable
import io.reactivex.Observable

interface NetworkingDatasource {

    fun getTasks() : Observable<List<Task>>

    fun playTask(id: Long) : Completable

    fun pauseTask(id: Long) : Completable

    fun closeTask(id: Long) : Completable

}