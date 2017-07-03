package com.uzias.runrunpocket.tasks.domain.usecase

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.uzias.runrunpocket.tasks.domain.model.Task
import com.uzias.runrunpocket.tasks.domain.repository.TasksRepository
import io.reactivex.Completable
import junit.framework.Assert.assertEquals
import org.junit.Test

class ChangeStatusWorkingTaskImplTest {

    private lateinit var changeStatusWorkingTask: ChangeStatusWorkingTask
    private var repository: TasksRepository = mock()
    private var task: Task = Task()

    @Test
    fun successful_execute() {
        given(repository.changeStatusWorking(task))
                .willReturn(Completable.complete())
        changeStatusWorkingTask = ChangeStatusWorkingTaskImpl(repository)
        changeStatusWorkingTask.set(task)
                .run()
                .test()
                .assertComplete()
                .assertNoErrors()

        assertEquals(task.working, true)
    }

}