package com.rohan.taskzz.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.lifecycle.ViewModelProvider
import com.rohan.taskzz.model.TaskViewModel
import com.rohan.taskzz.ui.theme.TaskzTheme
import androidx.compose.ui.Modifier
import com.rohan.taskz.ui.TaskForm
import com.rohan.taskzz.model.Task

class AddEditTaskActivity : ComponentActivity() {

    private lateinit var taskViewModel: TaskViewModel

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        setContent {
            TaskzTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Add/Edit Task") },
                            navigationIcon = {
                                IconButton(onClick = { finish() }) {
                                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                                }
                            }
                        )
                    },
                    content = { innerPadding ->
                        TaskForm(
                            modifier = Modifier.padding(innerPadding),
                            onSaveTask = { task: Task ->
                                // Save the task to the ViewModel and database
                                taskViewModel.insert(task)
                                finish()  // Return to the previous screen
                            }
                        )
                    }
                )
            }
        }
    }
}
