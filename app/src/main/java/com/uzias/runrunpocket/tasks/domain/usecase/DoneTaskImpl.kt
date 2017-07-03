package com.uzias.runrunpocket.tasks.domain.usecase

import com.uzias.runrunpocket.tasks.domain.model.Task
import com.uzias.runrunpocket.tasks.domain.repository.TasksRepository
import io.reactivex.Completable

class DoneTaskImpl(val repository: TasksRepository): DoneTask {

    private lateinit var task: Task

    override fun set(task: Task): DoneTask {
        this.task = task
        return this
    }

    override fun run(): Completable {
        return repository.done(task)
    }

}