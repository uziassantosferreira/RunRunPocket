package com.uzias.runrunpocket.tasks.data.repository.datasource

import com.uzias.runrunpocket.tasks.domain.model.Task
import io.reactivex.Completable
import io.reactivex.Observable

interface RealmDatasource {

    fun getTasks() : Observable<List<Task>>

    fun saveTasks(tasks: List<Task>) : Observable<List<Task>>

    fun updateTask(task: Task): Completable

    fun deleteTask(id: Long) : Completable

    fun deleteAllTasks() : Completable

}