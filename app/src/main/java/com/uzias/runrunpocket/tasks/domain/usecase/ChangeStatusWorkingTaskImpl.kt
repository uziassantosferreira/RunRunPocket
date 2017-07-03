package com.uzias.runrunpocket.tasks.domain.usecase

import com.uzias.runrunpocket.tasks.domain.model.Task
import com.uzias.runrunpocket.tasks.domain.repository.TasksRepository
import io.reactivex.Completable

class ChangeStatusWorkingTaskImpl(val repository: TasksRepository): ChangeStatusWorkingTask {

    private lateinit var task: Task

    override fun set(task: Task): ChangeStatusWorkingTask {
        this.task = task
        return this
    }

    override fun run(): Completable {
        task.working = task.working.not()
        return repository.changeStatusWorking(task)
    }

}