package com.biprangshu.littlelemonapp.reservetable

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.biprangshu.littlelemonapp.R
import com.biprangshu.littlelemonapp.ui.theme.Karla
import com.biprangshu.littlelemonapp.ui.theme.LittleLemonGreen
import com.biprangshu.littlelemonapp.ui.theme.LittleLemonYellow
import com.biprangshu.littlelemonapp.ui.theme.MarkaziText
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun reserveTable(modifier: Modifier = Modifier, navController: NavController) {

    var date by remember { mutableStateOf(LocalDate.now()) }


    Surface(
        modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())
    ) {
        Column(

        ) {
            Box(
                modifier = Modifier.fillMaxWidth()
            ){
                Image(painter = painterResource(R.drawable.reserve_table_image), contentDescription = "Reserve table image", modifier = Modifier.fillMaxWidth().aspectRatio(16f / 9f), contentScale = ContentScale.Crop)
            }
            Column(
                modifier = Modifier.fillMaxSize().navigationBarsPadding().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Text("Reserve a Table", fontFamily = MarkaziText, fontSize = 44.sp, color = LittleLemonYellow, fontWeight = FontWeight.Bold)
                Text("Please Enter details for reserving your table at little Lemon", textAlign = TextAlign.Center, fontFamily = Karla, fontWeight = FontWeight.Medium, fontSize = 18.sp)
                Spacer(Modifier.height(8.dp))

                Column (
                    modifier= Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    Text("Date of your Reservation", fontFamily = MarkaziText, fontWeight = FontWeight.Medium, fontSize = 24.sp)
                    dateYearSelector(
                        dateToBeUpdated = date,
                        onDateChanged = {newDate-> date=newDate},
                        selectedDate = date
                    )
                    Spacer(Modifier.height(8.dp))
                    Text("Number of persons", fontFamily = MarkaziText, fontWeight = FontWeight.Medium, fontSize = 24.sp)
                    Spacer(Modifier.height(8.dp))
                    var numberOfPersons by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = numberOfPersons,
                        onValueChange = {numberOfPersons=it},
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
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
                    Button(
                        onClick = {
                            navController.popBackStack()
                            navController.navigate("confirmscreen")
                        },
                        modifier = Modifier.padding(top = 100.dp).width(250.dp),
                        shape = RoundedCornerShape(16.dp),
                        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 16.dp, pressedElevation = 0.dp),
                        colors = ButtonDefaults.buttonColors(contentColor = Color.Black, containerColor = LittleLemonYellow)
                    ) {
                        Text("Reserve your Table", fontFamily = MarkaziText, fontSize = 28.sp, fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }
    }
}