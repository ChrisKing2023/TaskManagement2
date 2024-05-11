package com.example.taskmanagement2.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.taskmanagement2.model.Task

@Dao
interface TaskDao {

    //IF there is a Conflict of data, "REPLACE" will remove the old date with the new data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    //To read Tasks and search Task requires custom Queries

    @Query("SELECT * FROM TASKS ORDER BY id DESC")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM TASKS WHERE taskTitle LIKE :query OR taskDesc LIKE :query ")
    fun searchTask(query: String?): LiveData<List<Task>>

}