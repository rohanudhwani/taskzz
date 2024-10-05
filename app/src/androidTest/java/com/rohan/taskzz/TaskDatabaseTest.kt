package com.rohan.taskzz

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.rohan.taskzz.dao.TaskDao
import com.rohan.taskzz.database.TaskDatabase
import com.rohan.taskzz.model.Task
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Date


@RunWith(AndroidJUnit4::class)
@SmallTest
class TaskDatabaseTest {

    private lateinit var database: TaskDatabase
    private lateinit var taskDao: TaskDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TaskDatabase::class.java
        ).allowMainThreadQueries().build()
        taskDao = database.taskDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertTaskAndRetrieve() = runBlocking {
        val task = Task(
            1, "Test Task", "Description", Date(10 - 20 - 1945),
            priority = 2,
            completionStatus = 49
        )
        taskDao.insert(task)

        val tasks = taskDao.getAllTasks().value
        if (tasks != null) {
            assert(tasks.contains(task))
        }
    }

    @Test
    fun updateTaskAndCheck() = runBlocking {
        val task = Task(
            1, "Test Task", "Description", Date(10 - 20 - 1945),
            priority = 2,
            completionStatus = 49
        )
        taskDao.insert(task)

        val updatedTask = task.copy(title = "Updated Task", completionStatus = 100)
        taskDao.update(updatedTask)

        val tasks = taskDao.getAllTasks().value

        if (tasks != null) {
            val taskFromDb = tasks.find { it.id == task.id }
            assert(taskFromDb?.title == "Updated Task")
            assert(taskFromDb?.completionStatus == 100)
        }
    }

    @Test
    fun deleteTaskAndCheck() = runBlocking {
        // Insert a task
        val task = Task(
            2, "Delete Task", "Task to be deleted", Date(11 - 12 - 1955),
            priority = 3,
            completionStatus = 75
        )
        taskDao.insert(task)

        taskDao.delete(task)

        // Retrieve the tasks
        val tasks = taskDao.getAllTasks().value

        // Verify the deletion
        if (tasks != null) {
            assert(!tasks.contains(task))
        }
    }


}
