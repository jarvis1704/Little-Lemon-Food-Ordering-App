package com.biprangshu.littlelemonapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.biprangshu.littlelemonapp.R



// Karla Font Family
val Karla = FontFamily(
    Font(R.font.karla_regular, FontWeight.Normal),
    Font(R.font.karla_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.karla_bold, FontWeight.Bold),
    Font(R.font.karla_bolditalic, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.karla_extrabold, FontWeight.ExtraBold),
    Font(R.font.karla_extrabolditalic, FontWeight.ExtraBold, FontStyle.Italic),
    Font(R.font.karla_extralight, FontWeight.ExtraLight),
    Font(R.font.karla_extralightitalic, FontWeight.ExtraLight, FontStyle.Italic),
    Font(R.font.karla_light, FontWeight.Light),
    Font(R.font.karla_lightitalic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.karla_medium, FontWeight.Medium),
    Font(R.font.karla_mediumitalic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.karla_semibold, FontWeight.SemiBold),
    Font(R.font.karla_semibolditalic, FontWeight.SemiBold, FontStyle.Italic),
)

// Markazi Text Font Family
val MarkaziText = FontFamily(
    Font(R.font.markazi_text_regular, FontWeight.Normal),
    Font(R.font.markazi_text_medium, FontWeight.Medium),
    Font(R.font.markazi_text_bold, FontWeight.Bold),
    Font(R.font.markazi_text_semibold, FontWeight.SemiBold),
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Karla,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = MarkaziText,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Karla,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = MarkaziText,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 28.sp,
    ),
    displayLarge = TextStyle(
        fontFamily = Karla,
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp,
    ),
    displayMedium = TextStyle(
        fontFamily = Karla,
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp,
        lineHeight = 52.sp,
    ),
    displaySmall = TextStyle(
        fontFamily = Karla,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 44.sp,
    )

)

