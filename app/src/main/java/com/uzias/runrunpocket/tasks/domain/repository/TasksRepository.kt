package com.uzias.runrunpocket.tasks.domain.repository

import com.uzias.runrunpocket.tasks.domain.model.Task
import io.reactivex.Completable
import io.reactivex.Observable

interface TasksRepository {

    fun getTasks() : Observable<List<Task>>

    fun changeStatusWorking(task: Task) : Completable

    fun done(task: Task) : Completable

    fun refreshTasks(): Observable<List<Task>>

}