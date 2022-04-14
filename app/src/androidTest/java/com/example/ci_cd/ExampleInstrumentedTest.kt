package com.example.ci_cd

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.ci_cd.ui.theme.CI_CDTheme
import com.microsoft.appcenter.espresso.Factory
import com.microsoft.appcenter.espresso.ReportHelper
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val reportHelper: ReportHelper = Factory.getReportHelper()

    @Test
    fun testRetirementAmount() {
        composeTestRule.setContent {
            CI_CDTheme() {
                RetirementCompose()
            }
        }

        composeTestRule.onNodeWithText("Calculate").assertIsDisplayed()
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.ci_cd", appContext.packageName)
    }

    @After
    fun tearDown() {
        reportHelper.label("Stopping App")
    }
}