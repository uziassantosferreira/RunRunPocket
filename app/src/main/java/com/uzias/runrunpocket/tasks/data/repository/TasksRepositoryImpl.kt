package com.uzias.runrunpocket.tasks.data.repository

import com.uzias.runrunpocket.tasks.data.repository.datasource.NetworkingDatasource
import com.uzias.runrunpocket.tasks.data.repository.datasource.RealmDatasource
import com.uzias.runrunpocket.tasks.domain.model.Task
import com.uzias.runrunpocket.tasks.domain.repository.TasksRepository
import io.reactivex.Completable
import io.reactivex.Observable

class TasksRepositoryImpl(val networkingDatasource: NetworkingDatasource,
                          val realmDatasource: RealmDatasource) : TasksRepository {

    override fun refreshTasks(): Observable<List<Task>>
            = realmDatasource.deleteAllTasks().andThen(getTasks())


    override fun getTasks(): Observable<List<Task>> =  realmDatasource.getTasks()
            .switchIfEmpty(networkingDatasource.getTasks().flatMap{
                realmDatasource.saveTasks(it)
            })

    override fun changeStatusWorking(task: Task): Completable {
        val completable = if (task.working) networkingDatasource.playTask(task.id)
        else networkingDatasource.pauseTask(task.id)
        return completable.andThen(realmDatasource.updateTask(task))
    }

    override fun done(task: Task): Completable = networkingDatasource.closeTask(task.id)
            .andThen(realmDatasource.deleteTask(task.id))

}
