package com.uzias.runrunpocket.tasks.domain.model

import com.uzias.runrunpocket.core.util.InvalidData

data class Task(val title: String = InvalidData.UNINITIALIZED.getString(),
                val id: Long = InvalidData.UNINITIALIZED.getLong(),
                var working: Boolean = InvalidData.UNINITIALIZED.getBoolean(),
                val order: Int = InvalidData.UNINITIALIZED.getInt())