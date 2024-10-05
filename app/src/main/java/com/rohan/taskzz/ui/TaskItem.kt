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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rohan.taskzz.common.Priority
import com.rohan.taskzz.model.Task
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun TaskItem(
    task: Task,
    onDeleteTask: (Task) -> Unit // Callback to handle the deletion
) {
    var showDeleteDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    // Create variables for task properties
    val taskId by rememberUpdatedState(newValue = task.id)
    val taskTitle by rememberUpdatedState(newValue = task.title)
    val taskDescription by rememberUpdatedState(newValue = task.description)
    val taskDueDate by rememberUpdatedState(newValue = task.dueDate)
    val taskPriority by rememberUpdatedState(newValue = task.priority)
    val taskCompletionStatus by rememberUpdatedState(newValue = task.completionStatus)

    // Handle long press to show delete dialog
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        showDeleteDialog = true
                    },
                    onTap = {
                        val intent = Intent(context, AddEditTaskActivity::class.java).apply {
                            putExtra("taskId", taskId)
                            putExtra("taskTitle", taskTitle)
                            putExtra("taskDescription", taskDescription)
                            putExtra("taskDueDate", taskDueDate.time)
                            putExtra("taskPriority", taskPriority)
                            putExtra("taskCompletionStatus", taskCompletionStatus)
                        }
                        context.startActivity(intent)
                    }
                )
            }
            .padding(16.dp)
    ) {
        Column {
            val priorityText =
                Priority.entries.find { it.level == taskPriority }?.name ?: "Unknown"
            val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            Text(
                text = "ID: $taskId",
                color = MaterialTheme.colorScheme.onSurface
            ) // Use theme's onSurface color
            Text(text = "Title: $taskTitle", color = MaterialTheme.colorScheme.onSurface)
            Text(
                text = "Description: $taskDescription",
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Due Date: ${dateFormat.format(taskDueDate)}",
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(text = "Priority: $priorityText", color = MaterialTheme.colorScheme.onSurface)
            Text(
                text = "Completion: $taskCompletionStatus%",
                color = MaterialTheme.colorScheme.onSurface
            )
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