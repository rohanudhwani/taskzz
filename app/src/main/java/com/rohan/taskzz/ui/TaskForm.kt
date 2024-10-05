package com.rohan.taskz.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rohan.taskzz.model.Task

@Composable
fun TaskForm(
    modifier: Modifier = Modifier,
    taskId: Int? = null,
    initialTitle: String = "",
    initialDescription: String = "",
    initialDueDate: String = "",
    initialPriority: Int = 0,
    initialCompletionStatus: Int = 0,
    onSaveTask: (Task) -> Unit
) {
    // Use initial values passed into the composable
    var title by remember { mutableStateOf(initialTitle) }
    var description by remember { mutableStateOf(initialDescription) }
    var dueDate by remember { mutableStateOf(initialDueDate) }
    var priority by remember { mutableStateOf(initialPriority) }
    var completionStatus by remember { mutableStateOf(initialCompletionStatus) }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(text = "ID: $taskId")

        // Title TextField
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        // Description TextField
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        // Due Date TextField
        TextField(
            value = dueDate,
            onValueChange = { dueDate = it },
            label = { Text("Due Date") },
            modifier = Modifier.fillMaxWidth()
        )

        // Priority DropdownMenu
        Button(
            onClick = { isDropdownExpanded = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Priority: $priority")
        }
        DropdownMenu(
            expanded = isDropdownExpanded,
            onDismissRequest = { isDropdownExpanded = false }
        ) {
            (0..3).forEach { index ->
                DropdownMenuItem(
                    onClick = {
                        priority = index
                        isDropdownExpanded = false
                    },
                    text = { Text(text = "Priority: $index") }
                )
            }
        }

        Text(text = "Completion Status: ${completionStatus}%")
        // Completion Status Slider
        Slider(
            value = completionStatus.toFloat(),
            onValueChange = { completionStatus = it.toInt() },
            valueRange = 0f..100f,
            modifier = Modifier.fillMaxWidth()
        )

        // Save Task Button
        Button(
            onClick = {
                val task = Task(
                    title = title,
                    description = description,
                    dueDate = dueDate,
                    priority = priority,
                    completionStatus = completionStatus
                )
                if (taskId != null && taskId != -1) task.id = taskId.toInt()
                onSaveTask(task)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (taskId != null && taskId != -1) "Update Task" else "Save Task")
        }
    }
}
