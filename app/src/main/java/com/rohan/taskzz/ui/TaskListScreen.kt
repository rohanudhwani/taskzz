package com.rohan.taskzz.ui

import android.app.Application
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rohan.taskzz.model.TaskViewModel

@Composable
fun TaskListScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val taskViewModel: TaskViewModel =
        viewModel(factory = ViewModelProvider.AndroidViewModelFactory(context.applicationContext as Application)) // Get the ViewModel
    val taskList by taskViewModel.allTasks.observeAsState(listOf()) // Observe the task list

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(taskList, key = { task -> task.id }) { task ->
                TaskItem(
                    task = task,
                    onDeleteTask = { taskToDelete ->
                        taskViewModel.delete(taskToDelete) // Use ViewModel to delete the task
                    }
                )
            }
        }

        // Floating Action Button for adding tasks
        FloatingActionButton(
            onClick = {
                val intent = Intent(context, AddEditTaskActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Task")
        }
    }
}
