package com.uzias.runrunpocket.tasks.domain.usecase

import com.uzias.runrunpocket.tasks.domain.model.Task
import com.uzias.runrunpocket.tasks.domain.repository.TasksRepository
import io.reactivex.Observable

class RefreshTasksImpl(val repository: TasksRepository): RefreshTasks {

    override fun run(): Observable<List<Task>> = repository.refreshTasks()

}