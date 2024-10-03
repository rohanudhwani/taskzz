package com.rohan.taskzz.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val dueDate: String,          // Adjust the type as needed (e.g., LocalDate)
    val priority: Int,            // Assuming priority is an Int
    val completionStatus: Float  // Assuming completionStatus is a Boolean
)
