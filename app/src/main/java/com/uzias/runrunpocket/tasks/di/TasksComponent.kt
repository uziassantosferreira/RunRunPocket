package com.uzias.runrunpocket.tasks.di

import com.uzias.runrunpocket.core.di.AppComponent
import com.uzias.runrunpocket.tasks.di.scope.TasksScope
import com.uzias.runrunpocket.tasks.presentation.view.TasksViewImpl
import dagger.Component

@TasksScope
@Component(modules = arrayOf(TasksModule::class), dependencies = arrayOf(AppComponent::class))
interface TasksComponent {

    fun inject(taskView: TasksViewImpl)

}
