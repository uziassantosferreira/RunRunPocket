package com.uzias.runrunpocket.tasks.data.repository.datasource.networking.model

import com.google.gson.annotations.SerializedName

data class JsonTask(var title: String,
                var id: Long,
                @SerializedName("is_working_on") var working: Boolean,
                var done: Boolean,
                @SerializedName("queue_position")var order: Int)