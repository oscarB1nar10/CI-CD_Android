package com.example.ci_cd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.ci_cd.ui.theme.CI_CDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CI_CDTheme {
                RetirementCompose()
            }
        }
    }

}