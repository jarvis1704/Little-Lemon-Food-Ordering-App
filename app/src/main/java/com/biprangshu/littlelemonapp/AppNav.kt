package com.biprangshu.littlelemonapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.biprangshu.littlelemonapp.homescreen.homeScreen
import com.biprangshu.littlelemonapp.onboarding.onBoardingForm
import com.biprangshu.littlelemonapp.onboarding.onBoardingScreen
import com.biprangshu.littlelemonapp.viewmodel.MainViewModel

@Composable
fun AppNav(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        navController = navController, startDestination = "onboardingscreen"
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
            homeScreen(navController = navController)
        }
    }
}