package com.uzias.runrunpocket.tasks.data.repository.datasource.networking.mapper

import com.uzias.runrunpocket.core.mapper.BaseMapper
import com.uzias.runrunpocket.tasks.data.repository.datasource.networking.model.JsonTask
import com.uzias.runrunpocket.tasks.domain.model.Task

object JsonTaskMapper : BaseMapper<Task, JsonTask>() {

    override fun transformFrom(source: JsonTask, disambiguate: Boolean): Task =
            Task(title = source.title, id = source.id, working = source.working,
                    order = source.order)

    override fun transformFrom(source: Task): JsonTask {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}