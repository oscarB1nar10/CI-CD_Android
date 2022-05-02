package com.example.ci_cd

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.ci_cd.ui.theme.CI_CDTheme
import com.microsoft.appcenter.crashes.Crashes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Crashes.hasCrashedInLastSession()
            .thenAccept { hasCrashed ->
                if (hasCrashed) {
                    Toast.makeText(
                        this,
                        "Oops, sorry for that",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        setContent {
            CI_CDTheme {
                RetirementCompose()
            }
        }
    }

}