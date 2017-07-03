package com.uzias.runrunpocket.tasks.di

import com.uzias.runrunpocket.tasks.data.repository.TasksRepositoryImpl
import com.uzias.runrunpocket.tasks.data.repository.datasource.NetworkingDatasource
import com.uzias.runrunpocket.tasks.data.repository.datasource.RealmDatasource
import com.uzias.runrunpocket.tasks.data.repository.datasource.networking.NetworkingDatasourceImpl
import com.uzias.runrunpocket.tasks.data.repository.datasource.networking.service.TasksService
import com.uzias.runrunpocket.tasks.data.repository.datasource.realm.RealmDatasourceImpl
import com.uzias.runrunpocket.tasks.domain.repository.TasksRepository
import com.uzias.runrunpocket.tasks.domain.usecase.*
import com.uzias.runrunpocket.tasks.presentation.presenter.TasksPresenter
import com.uzias.runrunpocket.tasks.presentation.presenter.TasksPresenterImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module class TasksModule {

    @Provides fun providesTasksPresenter(getTasks: GetTasks,
                                         changeStatusWorkingTask: ChangeStatusWorkingTask,
                                         doneTask: DoneTask,
                                         refreshTasks: RefreshTasks)
            : TasksPresenter = TasksPresenterImpl(getTasks = getTasks,
            changeStatusWorkingTask = changeStatusWorkingTask, doneTask = doneTask,
            refreshTasks = refreshTasks)

    @Provides fun providesGetTasks(tasksRepository: TasksRepository)
            : GetTasks = GetTasksImpl(repository = tasksRepository)

    @Provides fun providesChangeStatusWorkingTask(tasksRepository: TasksRepository)
            : ChangeStatusWorkingTask = ChangeStatusWorkingTaskImpl(repository = tasksRepository)

    @Provides fun providesDoneTask(tasksRepository: TasksRepository)
            : DoneTask = DoneTaskImpl(repository = tasksRepository)

    @Provides fun providesRefreshTasks(tasksRepository: TasksRepository)
            : RefreshTasks = RefreshTasksImpl(repository = tasksRepository)

    @Provides fun providesTasksRepository(networkingDatasource: NetworkingDatasource,
                                          realmDatasource: RealmDatasource)
            : TasksRepository = TasksRepositoryImpl(networkingDatasource = networkingDatasource,
            realmDatasource = realmDatasource)

    @Provides fun providesNetworkingDatasource(tasksService: TasksService)
            : NetworkingDatasource = NetworkingDatasourceImpl(tasksService)

    @Provides fun providesRealmDatasource(): RealmDatasource = RealmDatasourceImpl()

    @Provides fun providesTasksService(retrofit: Retrofit)
            : TasksService = retrofit.create(TasksService::class.java)

}

