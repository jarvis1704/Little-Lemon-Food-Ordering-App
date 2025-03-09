package com.biprangshu.littlelemonapp

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.biprangshu.littlelemonapp.onboarding.onBoardingScreen
import com.biprangshu.littlelemonapp.ui.theme.LittleLemonAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val sharedPref=getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        val isFirstTime=sharedPref.getBoolean("isFirstTime", true)
        val startDestination=if(isFirstTime) "onboardingscreen" else "homescreen"
        setContent {
            LittleLemonAppTheme {
                val navController= rememberNavController()
                AppNav(navController = navController, startDestination = startDestination, sharedPref = sharedPref)
            }
        }
    }
}
