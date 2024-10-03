package com.rohan.taskzz.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.rohan.taskzz.model.Task

@Composable
fun TaskItem(task: Task) {
    Text(text = task.title) // Adjust according to your Task model
}
