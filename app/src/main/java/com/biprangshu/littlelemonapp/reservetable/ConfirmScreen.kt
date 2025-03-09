package com.biprangshu.littlelemonapp.reservetable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.biprangshu.littlelemonapp.R
import com.biprangshu.littlelemonapp.ui.theme.LittleLemonYellow
import com.biprangshu.littlelemonapp.ui.theme.MarkaziText

@Composable
fun confirmScreen(modifier: Modifier = Modifier, navController: NavController) {
    Surface(
        modifier= Modifier.fillMaxSize()
    ) {
        Column(
            modifier= Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painter = painterResource(R.drawable.confirm_image), contentDescription = "Confirmed")
            Spacer(Modifier.height(8.dp))
            Text("Your Table is Booked")
            Spacer(Modifier.height(8.dp))
            Text("Thank you for choosing Little Lemon. A confirmation mail has been sent.Here are the details of your reservation.")
            Spacer(Modifier.height(8.dp))
            Button(
                onClick = {
                    navController.navigate("homescreen")
                },
                modifier = Modifier.padding(top = 100.dp).width(250.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 16.dp, pressedElevation = 0.dp),
                colors = ButtonDefaults.buttonColors(contentColor = Color.Black, containerColor = LittleLemonYellow)
            ) {
                Text("Get Started", fontFamily = MarkaziText, fontSize = 28.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}