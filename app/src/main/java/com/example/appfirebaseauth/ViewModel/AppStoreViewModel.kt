package com.example.appfirebaseauth.ViewModel

import com.example.appfirebaseauth.repository.AppRepository
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import com.example.appfirebaseauth.data.AppDatabase
import com.example.appfirebaseauth.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppStoreViewModel(application: Application): AndroidViewModel(application)   {

    val readAllData: LiveData<List<Task>>
    private val repository: AppRepository

    init {
        val taskDao = AppDatabase.getDatabase(application).taskDao()
        repository = AppRepository(taskDao)
        readAllData = repository.readAllDataTask
    }

   /* fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }*/

    fun addUTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTask(task)
        }
    }


}