package com.example.appfirebaseauth.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.appfirebaseauth.model.Task
import com.example.appfirebaseauth.model.TaskWithUser
import com.example.appfirebaseauth.model.User

@Dao
interface UserDao {

    //for extract the data o
    @Query("SELECT * FROM users")
     fun getUser():LiveData<List<User>>


    //for extract the data of user sign in
    @Query("SELECT * FROM Users where idUser=:id")
    fun getUserById(id: Int): User

    //for insert un new user
    @Insert
    suspend fun insertUser(user: User)

    //for update un user
    @Update
    fun updateUser(user: User)

}