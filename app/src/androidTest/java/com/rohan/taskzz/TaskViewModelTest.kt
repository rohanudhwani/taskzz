//package com.rohan.taskzz
//
//import android.app.Application
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.Observer
//import com.rohan.taskzz.model.Task
//import com.rohan.taskzz.model.TaskViewModel
//import com.rohan.taskzz.repository.TaskRepository
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.test.runBlockingTest
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.mockito.Mockito.mock
//import org.mockito.Mockito.verify
//import org.mockito.Mockito.`when`
//import java.util.Date
//
//
//@OptIn(ExperimentalCoroutinesApi::class)
//class TaskViewModelTest {
//
//    @get:Rule
//    val instantExecutorRule = InstantTaskExecutorRule()
//
//    private lateinit var taskViewModel: TaskViewModel
//    private lateinit var repository: TaskRepository
//    private lateinit var application: Application
//    private val observer: Observer<List<Task>> = mock(Observer::class.java) as Observer<List<Task>>
//
//    @Before
//    fun setup() {
//        repository = mock(TaskRepository::class.java) // Automatically infers the type
//        application = mock(Application::class.java)  // Mock Application
//        taskViewModel = TaskViewModel(application, repository)  // Use secondary constructor
//    }
//
//    @Test
//    fun insertTask_callsRepositoryInsert() = runBlockingTest {
//        val task = Task(1, "New Task", "Description", Date(), 1, 0)
//
//        // Call insert
//        taskViewModel.insert(task)
//
//        // Verify if repository.insert was called with the correct task
//        verify(repository).insert(task)
//    }
//
//    @Test
//    fun updateTask_callsRepositoryUpdate() = runBlockingTest {
//        val task = Task(1, "Updated Task", "Description", Date(), 1, 0)
//
//        // Call update
//        taskViewModel.update(task)
//
//        // Verify if repository.update was called with the correct task
//        verify(repository).update(task)
//    }
//
//    @Test
//    fun deleteTask_callsRepositoryDelete() = runBlockingTest {
//        val task = Task(1, "Task to Delete", "Description", Date(), 1, 0)
//
//        // Call delete
//        taskViewModel.delete(task)
//
//        // Verify if repository.delete was called with the correct task
//        verify(repository).delete(task)
//    }
//
//    @Test
//    fun getAllTasks_observesLiveData() {
//        val mockTasks = listOf(
//            Task(1, "Task 1", "Description 1", Date(), 1, 0),
//            Task(2, "Task 2", "Description 2", Date(), 2, 1)
//        )
//
//        val liveData = mockTasks.toMutableLiveData() // Using the extension function
//        `when`(repository.allTasks).thenReturn(liveData) // Use Mockito's `when`
//
//        // Observe the LiveData from ViewModel
//        taskViewModel.allTasks.observeForever(observer)
//
//        // Verify that observer receives the correct data
//        verify(observer).onChanged(mockTasks)
//    }
//
//    private fun <T> List<T>.toMutableLiveData(): MutableLiveData<List<T>> {
//        return MutableLiveData(this)
//    }
//}
