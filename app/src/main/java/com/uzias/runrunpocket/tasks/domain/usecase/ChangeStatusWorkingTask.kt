package com.uzias.runrunpocket.tasks.domain.usecase

import com.uzias.runrunpocket.core.domain.UseCase
import com.uzias.runrunpocket.tasks.domain.model.Task
import io.reactivex.Completable

interface ChangeStatusWorkingTask: UseCase<Completable> {
    fun set(task: Task) : ChangeStatusWorkingTask
}