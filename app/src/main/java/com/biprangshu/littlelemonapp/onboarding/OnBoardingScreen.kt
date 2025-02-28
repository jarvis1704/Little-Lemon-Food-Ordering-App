package com.biprangshu.littlelemonapp.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.biprangshu.littlelemonapp.R
import com.biprangshu.littlelemonapp.ui.theme.Karla
import com.biprangshu.littlelemonapp.ui.theme.LittleLemonGreen
import com.biprangshu.littlelemonapp.ui.theme.MarkaziText

@Composable
fun onBoardingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(painter = painterResource(R.drawable.little_lemon_onboarding), contentDescription = "OnBoarding", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.FillBounds)

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(R.drawable.little_lemon_logo), contentDescription = "Little Lemon Logo", contentScale = ContentScale.Fit)
            Spacer(Modifier.height(16.dp))
            Text("Welcome", fontFamily = MarkaziText, fontSize = 68.sp, fontWeight = FontWeight.Medium, color = LittleLemonGreen)
            Button(
                onClick = {},
                modifier = Modifier.padding(top = 100.dp).width(250.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 16.dp, pressedElevation = 0.dp),
                colors = ButtonDefaults.buttonColors(contentColor = Color.White, containerColor = LittleLemonGreen)
            ) {
                Text("Get Started", fontFamily = MarkaziText, fontSize = 28.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Preview
@Composable
private fun onBoardingPreview() {
    onBoardingScreen()
}