package com.uzias.runrunpocket.tasks.domain.usecase

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.uzias.runrunpocket.tasks.domain.model.Task
import com.uzias.runrunpocket.tasks.domain.repository.TasksRepository
import io.reactivex.Observable
import org.junit.Test

class GetTasksImplTest {

    private lateinit var getTasks: GetTasks
    private var repository: TasksRepository = mock()
    private var tasks: List<Task> = mutableListOf(Task())

    @Test
    fun successful_execute() {
        given(repository.getTasks())
                .willReturn(Observable.just(tasks))
        getTasks = GetTasksImpl(repository)
        getTasks.run()
                .test()
                .assertValue(tasks)
                .assertComplete()
                .assertNoErrors()
    }

}