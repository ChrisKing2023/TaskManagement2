package com.example.taskmanagement2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taskmanagement2.model.Task

@Database(entities = [Task::class], version = 1)

abstract class TaskDatabase: RoomDatabase() {

    abstract fun getTaskDao(): TaskDao

    companion object{
        @Volatile //ensures that changes made to 1 thread are immediately visible to other threads
        private var instance: TaskDatabase? = null
        //LOCK is used to synchronize the database process
        //Ensures that only 1 thread can execute the code at a time
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?:
        synchronized(LOCK){
            instance ?:
            createDatabase(context).also{
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                TaskDatabase::class.java,
                "task_db"
            ).build()
    }
}