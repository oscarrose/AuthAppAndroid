package com.example.appfirebaseauth.repository
import androidx.lifecycle.LiveData
import com.example.appfirebaseauth.data.TaskDao
import com.example.appfirebaseauth.data.UserDao
import com.example.appfirebaseauth.model.Task
import com.example.appfirebaseauth.model.User


class AppRepository( private val taskDao: TaskDao) {
   // val readAllData: LiveData<List<User>> = userDao.getUser()
    val readAllDataTask: LiveData<List<Task>> = taskDao.getTask()

   /* suspend fun addUser(user: User){
        userDao.insertUser(user)
    }*/

    suspend fun addTask(task: Task){
        taskDao.insertTask(task)
    }
}