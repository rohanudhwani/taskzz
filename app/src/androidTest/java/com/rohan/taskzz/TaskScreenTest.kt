package com.rohan.taskzz

// Imports needed for Compose testing
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.assertIsDisplayed
import com.rohan.taskz.ui.TaskForm
import org.junit.Rule
import org.junit.Test
import java.util.Date

class TaskScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun taskScreenDisplaysTaskTitle() {
        composeTestRule.setContent {
            TaskForm(
//                modifier = TODO(),
                taskId = 3,
                initialTitle = "Rohan's Task",
                initialDescription = "Des",
                initialDueDate = Date(10 - 20 - 1928),
                initialPriority = 4,
                initialCompletionStatus = 45
            ) { }
        }

        composeTestRule
            .onNodeWithText("Rohan's Task")
            .assertIsDisplayed()
    }
}
