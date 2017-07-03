package com.uzias.runrunpocket.tasks.presentation.view

import com.uzias.runrunpocket.core.presentation.BaseView
import com.uzias.runrunpocket.tasks.presentation.model.PresentationTask

interface TasksView : BaseView {

    fun showList(it: MutableList<PresentationTask>)

    fun notifyTasksChanged()

    fun showToastError()

    fun removeTask(presentationTask: PresentationTask)

    fun cleanList()

    fun dismissRefresh()

}