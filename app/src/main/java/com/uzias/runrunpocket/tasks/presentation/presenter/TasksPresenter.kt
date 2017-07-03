package com.uzias.runrunpocket.tasks.presentation.presenter

import com.uzias.runrunpocket.core.presentation.BasePresenter
import com.uzias.runrunpocket.tasks.presentation.model.PresentationTask

interface TasksPresenter: BasePresenter {

    fun clickedButtonPlayOrPause(presentationTask: PresentationTask)

    fun clickedButtonDone(presentationTask: PresentationTask)

    fun onRefresh()
}
