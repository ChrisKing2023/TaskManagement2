package com.example.taskmanagement2.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskmanagement2.model.Task
import com.example.taskmanagement2.repository.taskRepository
import kotlinx.coroutines.launch

class TaskViewModel(app: Application, private val taskRepository: taskRepository): AndroidViewModel(app) {

    fun addTask(task: Task) =
        viewModelScope.launch {
            taskRepository.insertTask(task)
        }

    fun deleteTask(task: Task) =
        viewModelScope.launch {
            taskRepository.deleteTask(task)
        }

    fun updateTask(task: Task) =
        viewModelScope.launch {
            taskRepository.updateTask(task)
        }

    fun getAllTasks() = taskRepository.getAllTasks()

    fun searchNote(query: String?) = taskRepository.searchTask(query)

}