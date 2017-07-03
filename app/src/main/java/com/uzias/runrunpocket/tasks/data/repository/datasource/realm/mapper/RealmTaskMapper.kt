package com.uzias.runrunpocket.tasks.data.repository.datasource.realm.mapper

import com.uzias.runrunpocket.core.mapper.BaseMapper
import com.uzias.runrunpocket.tasks.data.repository.datasource.realm.model.RealmTask
import com.uzias.runrunpocket.tasks.domain.model.Task

object RealmTaskMapper : BaseMapper<Task, RealmTask>() {

    override fun transformFrom(source: Task): RealmTask
            = RealmTask(id = source.id, title = source.title, working = source.working,
            order = source.order)

    override fun transformFrom(source: RealmTask, disambiguate: Boolean): Task =
            Task(id = source.id, title = source.title, working = source.working, order = source.order)

}