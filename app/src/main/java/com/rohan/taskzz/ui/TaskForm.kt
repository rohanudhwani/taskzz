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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.rohan.taskzz.model.Task

@Composable
fun TaskForm(
    modifier: Modifier = Modifier,
    onSaveTask: (Task) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var dueDate by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf(0) }
    var completionStatus by remember { mutableStateOf(0f) }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = dueDate,
            onValueChange = { dueDate = it },
            label = { Text("Due Date") },
            modifier = Modifier.fillMaxWidth()
        )

        // Priority Dropdown
        DropdownMenu(
            expanded = true, // Simplified logic for example
            onDismissRequest = { /* Dismiss logic here */ }
        ) {
            (0..3).forEach { index ->
                DropdownMenuItem(
                    onClick = { priority = index },
                    text = { Text(text = "Priority: $index") }
                )
            }
        }

        // Completion Status Slider
        Slider(
            value = completionStatus,
            onValueChange = { completionStatus = it },
            valueRange = 0f..100f,
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                // Create a new task and pass it to onSaveTask callback
                val task = Task(
                    title = title,
                    description = description,
                    dueDate = dueDate,
                    priority = priority,
                    completionStatus = completionStatus
                )
                onSaveTask(task)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Task")
        }
    }
}
