package com.rohan.taskzz.ui

import android.app.Application
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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

import androidx.compose.material3.Switch

import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import com.rohan.taskzz.common.PreferencesManager

@Composable
fun TaskListScreen(modifier: Modifier = Modifier, onThemeToggle: (Boolean) -> Unit) {
    val context = LocalContext.current
    val taskViewModel: TaskViewModel =
        viewModel(factory = ViewModelProvider.AndroidViewModelFactory(context.applicationContext as Application))
    val taskList by taskViewModel.allTasks.observeAsState(listOf())

    var isDarkMode by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Column(modifier = Modifier.fillMaxSize()) {
            // Title Row with Switch
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Task List",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                // Theme Toggle Switch
                Switch(
                    checked = isDarkMode,
                    onCheckedChange = { isChecked ->
                        isDarkMode = isChecked
                        onThemeToggle(isChecked)
                    }
                )
            }

            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(taskList, key = { task -> task.id }) { task ->
                    TaskItem(
                        task = task,
                        onDeleteTask = { taskToDelete ->
                            taskViewModel.delete(taskToDelete)
                        }
                    )
                }
            }
        }

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
