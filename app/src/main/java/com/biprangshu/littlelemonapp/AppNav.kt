package com.biprangshu.littlelemonapp

import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.biprangshu.littlelemonapp.homescreen.homeScreen
import com.biprangshu.littlelemonapp.onboarding.onBoardingForm
import com.biprangshu.littlelemonapp.onboarding.onBoardingScreen
import com.biprangshu.littlelemonapp.reservetable.confirmScreen
import com.biprangshu.littlelemonapp.reservetable.reserveTable

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNav(modifier: Modifier = Modifier, navController: NavHostController, startDestination: String, sharedPref: SharedPreferences) {
    NavHost(
        navController = navController, startDestination = startDestination
    ) {
        composable(
            route = "onboardingscreen"
        ){
            onBoardingScreen(navController = navController)
        }

        composable(
            route = "onboardingform"
        ){
            onBoardingForm(navController = navController)
        }

        composable(
            route = "homescreen"
        ){
            homeScreen(navController = navController, sharedPreferences = sharedPref)
        }
        composable(route = "reservetable"){
            reserveTable(navController= navController)
        }
        composable(route= "confirmscreen"){
            confirmScreen(navController = navController, sharedPreferences = sharedPref)
        }
    }
}