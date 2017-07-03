package com.uzias.runrunpocket.tasks.data.repository

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.uzias.runrunpocket.tasks.data.repository.datasource.NetworkingDatasource
import com.uzias.runrunpocket.tasks.data.repository.datasource.RealmDatasource
import com.uzias.runrunpocket.tasks.domain.model.Task
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TasksRepositoryImplTest {

    private val networkingDatasource: NetworkingDatasource = mock()
    private val realmDatasource: RealmDatasource = mock()

    private val tasks = mutableListOf(Task())

    @InjectMocks
    private lateinit var repository: TasksRepositoryImpl

    @Before
    fun setUp() {
        given(networkingDatasource.pauseTask(tasks.first().id)).willReturn(Completable.complete())
        given(networkingDatasource.closeTask(tasks.first().id)).willReturn(Completable.complete())
        given(networkingDatasource.getTasks()).willReturn(Observable.just(tasks))
        given(realmDatasource.deleteAllTasks()).willReturn(Completable.complete())
        given(realmDatasource.deleteTask(tasks.first().id)).willReturn(Completable.complete())
        given(realmDatasource.updateTask(tasks.first())).willReturn(Completable.complete())
        given(realmDatasource.getTasks()).willReturn(Observable.just(tasks))
    }

    @Test
    fun must_call_refresh_tasks() {
        repository.refreshTasks()
                .test()
                .assertNoErrors()
                .assertComplete()
                .assertValue(tasks)
        verify(realmDatasource).deleteAllTasks()
        verify(realmDatasource).getTasks()
    }

    @Test
    fun must_call_get_tasks() {
        repository.getTasks()
                .test()
                .assertNoErrors()
                .assertComplete()
                .assertValue(tasks)
        verify(realmDatasource).getTasks()
    }

    @Test
    fun must_call_change_status_tasks() {
        repository.changeStatusWorking(tasks.first())
                .test()
                .assertNoErrors()
                .assertComplete()

        verify(networkingDatasource).pauseTask(tasks.first().id)
        verify(realmDatasource).updateTask(tasks.first())
    }

    @Test
    fun must_call_done() {
        repository.done(tasks.first())
                .test()
                .assertNoErrors()
                .assertComplete()

        verify(networkingDatasource).closeTask(tasks.first().id)
        verify(realmDatasource).deleteTask(tasks.first().id)
    }


}