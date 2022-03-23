package com.example.appfirebaseauth.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.ParcelField
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Task")
data class Task(
    @PrimaryKey(autoGenerate=true)
    @ColumnInfo(name="idTask")
    var idTask:Long,

    @ColumnInfo(name = "UserCreatorId")
    @NonNull
    var UserCreatorId: Long,

    @ColumnInfo(name = "title")
    @NonNull
    var title: String,

    @ColumnInfo(name = "note")
    var note:String,

    @ColumnInfo(name = "dateTask")
    @NonNull
    var dateTask:String,

    @ColumnInfo(name = "completed")
    @NonNull
    var completed:Boolean

    ): Parcelable