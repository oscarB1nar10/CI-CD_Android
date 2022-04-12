package com.example.ci_cd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ci_cd.ui.models.Retirement
import com.example.ci_cd.ui.theme.CI_CDTheme
import com.microsoft.appcenter.analytics.Analytics
import java.lang.Exception

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CI_CDTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(horizontal = 16.dp)
                    ) {
                        var monthlySaving by remember { mutableStateOf("") }

                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            value = monthlySaving,
                            onValueChange = { monthlySaving = it },
                            label = { Text(text = "Monthly saving") }
                        )

                        var interestRate by remember { mutableStateOf("") }

                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            value = interestRate,
                            onValueChange = { interestRate = it },
                            label = { Text(text = "Interest rate") }
                        )

                        var currentAge by remember { mutableStateOf("") }

                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            value = currentAge,
                            onValueChange = { currentAge = it },
                            label = { Text(text = "currentAge") }
                        )

                        var plannedRetirementAge by remember { mutableStateOf("") }

                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            value = plannedRetirementAge,
                            onValueChange = { plannedRetirementAge = it },
                            label = { Text(text = "Planned retirement age") }
                        )

                        var currentSavings by remember { mutableStateOf("") }

                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight(),
                            value = currentSavings,
                            onValueChange = { currentSavings = it },
                            label = { Text(text = "Current savings") }
                        )

                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                        )

                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            onClick = {
                                val retirement = Retirement(
                                    interestRate = interestRate.toFloat(),
                                    currentAge = currentAge.toInt(),
                                    retirementAge = plannedRetirementAge.toInt()
                                )
                                onCalculate(retirement)
                            },
                        ) {
                            Text(
                                text = "Calculate"
                            )
                        }
                    }
                }
            }
        }
    }

    private fun onCalculate(retirement: Retirement) {
        try {
            val appCenterProperties = HashMap<String, String>().apply {
                put("interest_rate", retirement.interestRate.toString())
                put("retirement_age", retirement.retirementAge.toString())
            }
            if (retirement.interestRate <= 0) {
                Analytics.trackEvent("Wrong interest rate", appCenterProperties)
            }

            if (retirement.retirementAge <= retirement.currentAge) {
                Analytics.trackEvent("wrong age", appCenterProperties)
            }
        } catch (exception: Exception) {
            Analytics.trackEvent(exception.message.toString())
        }
    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CI_CDTheme {
        Greeting("Android")
    }
}