package com.biprangshu.littlelemonapp.reservetable

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.biprangshu.littlelemonapp.R
import com.biprangshu.littlelemonapp.ui.theme.Karla
import com.biprangshu.littlelemonapp.ui.theme.LittleLemonYellow
import com.biprangshu.littlelemonapp.ui.theme.MarkaziText

@Composable
fun confirmScreen(modifier: Modifier = Modifier, navController: NavController, sharedPreferences: SharedPreferences) {
    val email=sharedPreferences.getString("email", "something")
    Surface(
        modifier= Modifier.fillMaxSize()
    ) {
        Column(
            modifier= Modifier.fillMaxSize().statusBarsPadding().navigationBarsPadding().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(R.drawable.confirm_image), contentDescription = "Confirmed")
            Spacer(Modifier.height(16.dp))
            Text("Your Table is Booked", fontFamily = MarkaziText, fontWeight = FontWeight.Medium, fontSize = 44.sp)
            Spacer(Modifier.height(8.dp))
            Text("Thank you for choosing Little Lemon. A confirmation mail has been sent to $email", fontFamily = Karla, fontWeight = FontWeight.Medium, fontSize = 18.sp, textAlign = TextAlign.Center)
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = {
                    navController.popBackStack()
                    navController.navigate("homescreen")
                },
                modifier = Modifier.padding(top = 100.dp).width(250.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 16.dp, pressedElevation = 0.dp),
                colors = ButtonDefaults.buttonColors(contentColor = Color.Black, containerColor = LittleLemonYellow)
            ) {
                Text("Done", fontFamily = MarkaziText, fontSize = 28.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}