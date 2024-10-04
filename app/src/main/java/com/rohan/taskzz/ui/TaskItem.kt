package com.rohan.taskzz.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rohan.taskzz.model.Task

@Composable
fun TaskItem(task: Task) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Title
            Text(
                text = "Title: ${task.title}",
                fontSize = 20.sp
            )

            // Description
            Text(
                text = "Description: ${task.description}",
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 8.dp)
            )

            // Due Date
            Text(
                text = "Due Date: ${task.dueDate}",
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp)
            )

            // Priority
            Text(
                text = "Priority: ${task.priority}",
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp)
            )

            // Completion Status
            Text(
                text = "Completion Status: ${task.completionStatus.toInt()}%",
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
