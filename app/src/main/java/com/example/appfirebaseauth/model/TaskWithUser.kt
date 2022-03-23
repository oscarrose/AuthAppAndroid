package com.example.appfirebaseauth.model

import androidx.room.Embedded
import androidx.room.Relation

data class TaskWithUser(
    @Embedded val task: Task,
    @Relation(
        parentColumn = "UserCreatorId",
        entityColumn = "idUser"
    )
    val user :List<User>
)
