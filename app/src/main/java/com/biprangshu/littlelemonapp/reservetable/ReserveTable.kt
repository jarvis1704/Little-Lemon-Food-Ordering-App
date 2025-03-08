package com.biprangshu.littlelemonapp.reservetable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.biprangshu.littlelemonapp.R

@Composable
fun reserveTable(modifier: Modifier = Modifier) {
    Surface {
        Column {
            Box(
                modifier = Modifier.fillMaxWidth()
            ){
                Image(painter = painterResource(R.drawable.reserve_table_image), contentDescription = "Reserve table image", contentScale = ContentScale.Fit)
            }
        }
    }
}