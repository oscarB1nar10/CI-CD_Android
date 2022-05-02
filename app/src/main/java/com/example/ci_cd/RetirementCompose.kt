package com.example.ci_cd

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.ci_cd.ui.models.Retirement
import com.microsoft.appcenter.analytics.Analytics

@Composable
internal fun RetirementCompose() {
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
            val context = LocalContext.current
            var retireAmount by remember { mutableStateOf(0.0f) }
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

                    retiringMessage(onGetRetiringMessage(retirement), context = context)

                    val remainingYears =
                        plannedRetirementAge.toInt() - currentAge.toInt()
                    retireAmount =
                        getRetirementAmount(monthlySaving.toFloat(), remainingYears)

                },
            ) {
                Text(
                    text = "Calculate"
                )
            }

            if (retireAmount > 0.0) {
                Text(text = "Retirement amount: $retireAmount")
            }
        }
    }
}

private fun onGetRetiringMessage(retirement: Retirement): String {
    try {
        val appCenterProperties = HashMap<String, String>().apply {
            put("interest_rate", retirement.interestRate.toString())
            put("retirement_age", retirement.retirementAge.toString())
        }
        if (retirement.interestRate <= 0) {
            Analytics.trackEvent("Wrong interest rate", appCenterProperties)
            return "Wrong interest rate"
        }

        if (retirement.retirementAge <= retirement.currentAge) {
            Analytics.trackEvent("wrong age", appCenterProperties)
            return "wrong age"
        }

        return "You can request your retire"
    } catch (exception: Exception) {
        Analytics.trackEvent(exception.message.toString())
    }

    return "You cant retire right now"
}

private fun getRetirementAmount(monthlySaving: Float, remainingYears: Int): Float {
    return (monthlySaving * 12) * remainingYears
}

private fun retiringMessage(message: String, context: Context) {
    Toast.makeText(
        context,
        message,
        Toast.LENGTH_LONG
    ).show()
}








