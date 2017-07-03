package com.uzias.runrunpocket.tasks.data.repository.datasource.networking

import com.uzias.runrunpocket.tasks.data.repository.datasource.NetworkingDatasource
import com.uzias.runrunpocket.tasks.data.repository.datasource.networking.mapper.JsonTaskMapper
import com.uzias.runrunpocket.tasks.data.repository.datasource.networking.service.TasksService
import com.uzias.runrunpocket.tasks.domain.model.Task
import io.reactivex.Completable
import io.reactivex.Observable

class NetworkingDatasourceImpl(val tasksService: TasksService) : NetworkingDatasource {

    override fun playTask(id: Long): Completable = tasksService.playTask(id)

    override fun pauseTask(id: Long): Completable = tasksService.pauseTask(id)

    override fun getTasks(): Observable<List<Task>> =
            tasksService.getTasks()
                    .map {JsonTaskMapper.transformFromMutableList(it.toMutableList()) }

    override fun closeTask(id: Long): Completable = tasksService.closeTask(id)

}