package com.biprangshu.littlelemonapp.onboarding

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.biprangshu.littlelemonapp.R
import com.biprangshu.littlelemonapp.ui.theme.Karla
import com.biprangshu.littlelemonapp.ui.theme.LittleLemonGreen
import com.biprangshu.littlelemonapp.ui.theme.LittleLemonYellow
import com.biprangshu.littlelemonapp.ui.theme.MarkaziText

@Composable
fun onBoardingForm(modifier: Modifier = Modifier, navController: NavController) {

    var firsttext by remember {
        mutableStateOf("")
    }

    var lastText by remember {
        mutableStateOf("")
    }

    var emailText by remember {
        mutableStateOf("")
    }

    val context= LocalContext.current
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
        ) {
            Row(
                modifier= Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(painter = painterResource(R.drawable.little_lemon_logo), contentDescription = "Little Lemon Logo", contentScale = ContentScale.Crop)
            }
            Spacer(Modifier.height(8.dp))
            Row(
                modifier= Modifier.fillMaxWidth().background(LittleLemonGreen).height(100.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Let's get to know you", color = Color.White, fontSize = 32.sp, modifier = Modifier.padding(16.dp), fontFamily = MarkaziText)
            }
            Spacer(Modifier.height(8.dp))
            Column(
                modifier= Modifier.fillMaxWidth(),
            ) {
                Text("Personal Information", fontFamily = Karla, fontSize = 24.sp, fontWeight = FontWeight.SemiBold, modifier = Modifier.padding(horizontal = 16.dp, vertical = 40.dp))
                Spacer(Modifier.height(8.dp))
                Column(
                    modifier= Modifier.fillMaxWidth()
                ) {
                    Text("First Name", fontFamily = Karla, fontSize = 16.sp, fontWeight = FontWeight.Normal, modifier = Modifier.padding(horizontal = 16.dp))
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = firsttext,
                        onValueChange = {firsttext=it},
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        label = { Text("Enter your first name") },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = LittleLemonGreen,
                            unfocusedTextColor = LittleLemonGreen,
                            focusedIndicatorColor = LittleLemonGreen,
                            unfocusedContainerColor = Color.LightGray,
                            cursorColor = LittleLemonGreen,
                            focusedLabelColor = LittleLemonYellow,
                            focusedContainerColor = Color.White
                        ),
                        shape = OutlinedTextFieldDefaults.shape.let {
                            RoundedCornerShape(16.dp)
                        },
                    )
                }
                Spacer(Modifier.height(16.dp))
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Last Name", fontFamily = Karla, fontSize = 16.sp, fontWeight = FontWeight.Normal, modifier = Modifier.padding(horizontal = 16.dp))
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = lastText,
                        onValueChange = {lastText=it},
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        label = { Text("Enter your Last name") },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = LittleLemonGreen,
                            unfocusedTextColor = LittleLemonGreen,
                            focusedIndicatorColor = LittleLemonGreen,
                            unfocusedContainerColor = Color.LightGray,
                            cursorColor = LittleLemonGreen,
                            focusedLabelColor = LittleLemonYellow,
                            focusedContainerColor = Color.White
                        ),
                        shape = OutlinedTextFieldDefaults.shape.let {
                            RoundedCornerShape(16.dp)
                        }
                    )
                }
                Spacer(Modifier.height(16.dp))
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Email", fontFamily = Karla, fontSize = 16.sp, fontWeight = FontWeight.Normal, modifier = Modifier.padding(horizontal = 16.dp))
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = emailText,
                        onValueChange = {emailText=it},
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        label = { Text("Enter your Email") },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = LittleLemonGreen,
                            unfocusedTextColor = LittleLemonGreen,
                            focusedIndicatorColor = LittleLemonGreen,
                            unfocusedContainerColor = Color.LightGray,
                            cursorColor = LittleLemonGreen,
                            focusedLabelColor = LittleLemonYellow,
                            focusedContainerColor = Color.White
                        ),
                        shape = OutlinedTextFieldDefaults.shape.let {
                            RoundedCornerShape(16.dp)
                        }
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE).edit().apply{
                            putString("first_name", firsttext)
                            putString("last_name", lastText)
                            putString("email", emailText)
                            putBoolean("isFirstTime", false)
                            apply()
                        }
                        navController.navigate("homescreen"){
                            popUpTo("onboardingscreen"){inclusive=true}
                        }
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
}


