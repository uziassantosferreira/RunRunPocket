package com.uzias.runrunpocket.tasks.domain.usecase

import com.uzias.runrunpocket.tasks.domain.model.Task
import com.uzias.runrunpocket.tasks.domain.repository.TasksRepository
import io.reactivex.Observable

class GetTasksImpl(val repository: TasksRepository): GetTasks {

    override fun run(): Observable<List<Task>>  = repository.getTasks()

}