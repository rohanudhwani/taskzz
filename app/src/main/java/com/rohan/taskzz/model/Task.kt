package com.rohan.taskzz.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val title: String,
    val description: String,
    val dueDate: String,
    val priority: Int,
    val completionStatus: Int
)
