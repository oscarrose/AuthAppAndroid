package com.example.appfirebaseauth.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.appfirebaseauth.model.Task
import com.example.appfirebaseauth.model.TaskWithUser
import com.example.appfirebaseauth.model.User

@Database(entities = [User::class, Task::class], version = 1,exportSchema = false)
abstract class AppDatabase :RoomDatabase() {
    //abstract fun userDao():UserDao
    abstract fun taskDao():TaskDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "database_App"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}


