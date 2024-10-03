package com.rohan.taskzz.repository

import androidx.lifecycle.LiveData
import com.rohan.taskzz.dao.TaskDao
import com.rohan.taskzz.model.Task

class TaskRepository(private val taskDao: TaskDao) {
    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()

    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    suspend fun update(task: Task) {
        taskDao.update(task)
    }

    suspend fun delete(task: Task) {
        taskDao.delete(task)
    }
}


