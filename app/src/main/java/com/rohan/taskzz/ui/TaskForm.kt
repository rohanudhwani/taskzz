package com.rohan.taskz.ui

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.rohan.taskzz.common.Priority
import com.rohan.taskzz.model.Task
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import androidx.compose.material3.*


@Composable
fun TaskForm(
    modifier: Modifier = Modifier,
    taskId: Int,
    initialTitle: String = "",
    initialDescription: String = "",
    initialDueDate: Date = Date(),
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

    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    // Date formatter
    val dateFormatter = remember {
        SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    }

    calendar.time = dueDate

    // Due Date Picker Dialog
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            // Update the calendar with the selected date
            calendar.set(year, month, dayOfMonth)
            dueDate = calendar.time
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Text(text = "ID: $taskId", color = MaterialTheme.colorScheme.onBackground)

        // Title TextField
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
        )

        // Description TextField
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
        )

        // Due Date Button
        Button(
            onClick = { datePickerDialog.show() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(text = "Due Date: ${dateFormatter.format(dueDate)}")
        }

        // Priority DropdownMenu
        Button(
            onClick = { isDropdownExpanded = true },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            // Display the current priority name
            val currentPriority = Priority.entries.find { it.level == priority }?.name ?: "Unknown"
            Text("Priority: $currentPriority")
        }

        DropdownMenu(
            expanded = isDropdownExpanded,
            onDismissRequest = { isDropdownExpanded = false }
        ) {
            // Iterate over the Priority enum values instead of indices
            Priority.entries.forEach { priorityEnum ->
                DropdownMenuItem(
                    onClick = {
                        priority = priorityEnum.level // Set the priority level
                        isDropdownExpanded = false
                    },
                    text = { Text(text = priorityEnum.name) }
                )
            }
        }

        Text(
            text = "Completion Status: ${completionStatus}%",
            color = MaterialTheme.colorScheme.onBackground
        )

        // Completion Status Slider
        Slider(
            value = completionStatus.toFloat(),
            onValueChange = { completionStatus = it.toInt() },
            valueRange = 0f..100f,
            modifier = Modifier.fillMaxWidth(),
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.primary,
                activeTrackColor = MaterialTheme.colorScheme.primary,
                inactiveTrackColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
            )
        )

        // Save Task Button
        Button(
            onClick = {
                val task = Task(
                    id = taskId,
                    title = title,
                    description = description,
                    dueDate = dueDate,
                    priority = priority,
                    completionStatus = completionStatus
                )
                onSaveTask(task)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(if (taskId != -1) "Update Task" else "Save Task")
        }
    }
}

