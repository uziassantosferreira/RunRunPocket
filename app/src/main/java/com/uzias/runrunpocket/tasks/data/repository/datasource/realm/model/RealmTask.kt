package com.uzias.runrunpocket.tasks.data.repository.datasource.realm.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class RealmTask(

        @PrimaryKey var id: Long = 0,

        var title: String = "",

        var working: Boolean = false,

        var order: Int = 0

) : RealmObject(){}