package com.uzias.runrunpocket.tasks.domain.usecase

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.uzias.runrunpocket.tasks.domain.model.Task
import com.uzias.runrunpocket.tasks.domain.repository.TasksRepository
import io.reactivex.Completable
import org.junit.Test

class DoneTaskImplTest {

    private lateinit var doneTask: DoneTask
    private var repository: TasksRepository = mock()
    private var task: Task = Task()

    @Test
    fun successful_execute() {
        given(repository.done(task))
                .willReturn(Completable.complete())
        doneTask = DoneTaskImpl(repository)
        doneTask.set(task)
                .run()
                .test()
                .assertComplete()
                .assertNoErrors()
    }

}