package com.uzias.runrunpocket.tasks.presentation.listener

import com.uzias.runrunpocket.tasks.presentation.model.PresentationTask

interface TaskListener {

    fun clickedButtonPlayOrPause(presentationTask: PresentationTask)

    fun clickedButtonDone(presentationTask: PresentationTask)

}