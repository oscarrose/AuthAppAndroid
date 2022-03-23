package com.example.appfirebaseauth.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.appfirebaseauth.model.Task
import com.example.appfirebaseauth.model.TaskWithUser
@Dao
interface TaskDao {
    //for insert un new task
    @Insert
    fun insertTask(task: Task):Long

    //for extract the data o
    @Query("SELECT * FROM Task")
    fun getTask(): LiveData<List<Task>>

    //for extract the task with relation user
    @Transaction
    @Query("SELECT * FROM task where UserCreatorId=:UserCreatorId")
    fun getTaskWithIUser(UserCreatorId:Long): List<TaskWithUser>
}