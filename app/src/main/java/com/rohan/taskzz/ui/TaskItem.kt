package com.rohan.taskzz.ui

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rohan.taskzz.model.Task

@Composable
fun TaskItem(
    task: Task,
    onDeleteTask: (Task) -> Unit // Callback to handle the deletion
) {
    var showDeleteDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    // Handle long press to show delete dialog
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        showDeleteDialog = true
                    },
                    onTap = {
                        // Navigate to AddEditTaskActivity with pre-filled task data on single tap
                        val intent = Intent(context, AddEditTaskActivity::class.java).apply {
                            putExtra("taskId", task.id)
                            putExtra("taskTitle", task.title)
                            putExtra("taskDescription", task.description)
                            putExtra("taskDueDate", task.dueDate)
                            putExtra("taskPriority", task.priority)
                            putExtra("taskCompletionStatus", task.completionStatus)
                        }
                        context.startActivity(intent)
                    }
                )
            }
            .padding(16.dp)
    ) {
        Column {
            Text(text = "ID: ${task.id}")
            Text(text = "Title: ${task.title}")
            Text(text = "Description: ${task.description}")
            Text(text = "Due Date: ${task.dueDate}")
            Text(text = "Priority: ${task.priority}")
            Text(text = "Completion: ${task.completionStatus}%")
        }
    }

    // Delete confirmation dialog
    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text(text = "Delete Task") },
            text = { Text("Are you sure you want to delete this task?") },
            confirmButton = {
                Button(
                    onClick = {
                        onDeleteTask(task)
                        showDeleteDialog = false
                    }
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                Button(onClick = { showDeleteDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}