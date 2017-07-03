package com.uzias.runrunpocket.tasks.presentation.mapper

import com.uzias.runrunpocket.core.mapper.BaseMapper
import com.uzias.runrunpocket.tasks.domain.model.Task
import com.uzias.runrunpocket.tasks.presentation.model.PresentationTask

object PresentationTaskMapper : BaseMapper<Task, PresentationTask>() {

    override fun transformFrom(source: PresentationTask, disambiguate: Boolean): Task =
            Task(title = source.title, id = source.id, working = source.working,
                    order = source.order)

    override fun transformFrom(source: Task): PresentationTask =
            PresentationTask(title = source.title, id = source.id, working = source.working,
                    order = source.order)

}