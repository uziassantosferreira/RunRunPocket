package com.uzias.runrunpocket.tasks.presentation.presenter

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.uzias.runrunpocket.tasks.util.Rx2TestSchedulerRule
import com.uzias.runrunpocket.tasks.domain.model.Task
import com.uzias.runrunpocket.tasks.domain.usecase.ChangeStatusWorkingTask
import com.uzias.runrunpocket.tasks.domain.usecase.DoneTask
import com.uzias.runrunpocket.tasks.domain.usecase.GetTasks
import com.uzias.runrunpocket.tasks.domain.usecase.RefreshTasks
import com.uzias.runrunpocket.tasks.presentation.mapper.PresentationTaskMapper
import com.uzias.runrunpocket.tasks.presentation.view.TasksView
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TasksPresenterImplTest {

    @get:Rule
    var rxRule = Rx2TestSchedulerRule()

    private val getTasks: GetTasks = mock()
    private val changeStatusWorkingTask: ChangeStatusWorkingTask = mock()
    private val doneTask: DoneTask = mock()
    private val refreshTasks: RefreshTasks = mock()
    private val view: TasksView = mock()
    private val tasks = mutableListOf(Task())
    private val presentationTask = PresentationTaskMapper.transformFrom(Task())

    @InjectMocks
    private lateinit var presenter: TasksPresenterImpl

    @Before
    fun setUp(){
        given(getTasks.run()).willReturn(Observable.just(tasks))
        given(changeStatusWorkingTask.set(tasks.first())).willReturn(changeStatusWorkingTask)
        given(changeStatusWorkingTask.run()).willReturn(Completable.complete())
        given(doneTask.set(tasks.first())).willReturn(doneTask)
        given(doneTask.run()).willReturn(Completable.complete())
        given(refreshTasks.run()).willReturn(Observable.just(tasks))
        presenter.attachTo(view)
    }

    @Test
    fun get_tasks() {
        verify(view).showList(PresentationTaskMapper.transformFromMutableList(tasks, true))
    }

    @Test
    fun clicked_button_play_or_pause_successful() {
        presenter.clickedButtonPlayOrPause(presentationTask)
        verify(view).showLoading()
        verify(view).dismissLoading()
        verify(view).notifyTasksChanged()
    }

    @Test
    fun clicked_button_play_or_pause_failure() {
        given(changeStatusWorkingTask.run()).willReturn(Completable.error(Throwable()))
        presenter.clickedButtonPlayOrPause(presentationTask)
        verify(view).showLoading()
        verify(view).dismissLoading()
        verify(view).showToastError()
    }

    @Test
    fun clicked_button_done_successful() {
        presenter.clickedButtonDone(presentationTask)
        verify(view).showLoading()
        verify(view).dismissLoading()
        verify(view).removeTask(presentationTask)
    }

    @Test
    fun clicked_button_done_failure() {
        given(doneTask.run()).willReturn(Completable.error(Throwable()))
        presenter.clickedButtonDone(presentationTask)
        verify(view).showLoading()
        verify(view).dismissLoading()
        verify(view).showToastError()
    }

    @Test
    fun on_refresh_list() {
        presenter.onRefresh()
        verify(view).cleanList()
        verify(view).dismissRefresh()
        verify(view, times(2)).showList(PresentationTaskMapper.transformFromMutableList(tasks, true))
    }

}