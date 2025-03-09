package com.biprangshu.littlelemonapp.homescreen

import android.content.Context
import android.content.SharedPreferences
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.biprangshu.littlelemonapp.R
import com.biprangshu.littlelemonapp.data.remote.Menu
import com.biprangshu.littlelemonapp.ui.theme.Karla
import com.biprangshu.littlelemonapp.ui.theme.LittleLemonGreen
import com.biprangshu.littlelemonapp.ui.theme.LittleLemonYellow
import com.biprangshu.littlelemonapp.ui.theme.MarkaziText
import com.biprangshu.littlelemonapp.util.menuCategory
import com.biprangshu.littlelemonapp.viewmodel.MainViewModel

@Composable
fun homeScreen(modifier: Modifier = Modifier, viewModel: MainViewModel = hiltViewModel(), navController: NavController, sharedPreferences: SharedPreferences) {


    val isLoading by viewModel.isLoading.collectAsState() // Collect isLoading StateFlow
    val errorMessage by viewModel.errorMessage.collectAsState() // Collect errorMessage StateFlow
    val menuItems by viewModel.menuItems.collectAsState()   // Collect menuItems StateFlow

    val listState= rememberLazyListState()
    val scrolledUp by remember {
        derivedStateOf { listState.firstVisibleItemIndex<=0 }
    }

    var selectedCategory by remember {
        mutableStateOf("All")
    }

    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            dynamicHeader(navController = navController, scrolledUp = scrolledUp, sharedPreferences = sharedPreferences, onSearch = {})

            if(isLoading){
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }

            if (!errorMessage.isNullOrBlank()) {
                Text(
                    text = errorMessage?: "Unknown Error",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(16.dp)
                )
            }

            Row(
                modifier= Modifier.fillMaxWidth().padding(bottom = 10.dp)
            ) {
                sortButtons(categories = menuCategory, selectedCategory = selectedCategory) {
                        category->
                    selectedCategory=category
                }
            }

            LazyColumn(
                modifier= Modifier.fillMaxSize(),
                state = listState
            ) {
                items(menuItems){
                    menuItem->
                    ItemCard(menu = menuItem)
                }
            }
        }
    }
}


@Composable
fun dynamicHeader(navController: NavController, scrolledUp: Boolean, onSearch: (String)-> Unit, sharedPreferences: SharedPreferences) {
    val headerHeightExpanded=180.dp
    val headerHeightCollapsed=56.dp

    val headerHeight by animateDpAsState(
        targetValue = if(scrolledUp) headerHeightExpanded else headerHeightCollapsed,
        animationSpec = tween(500)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(headerHeight)
            .background(MaterialTheme.colorScheme.background) // Ensure header background is consistent
    ) {
        AnimatedVisibility(
            visible = scrolledUp,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            ExpandedHeaderContent(navController = navController, onSearch = onSearch, sharedPreferences = sharedPreferences)
        }
        AnimatedVisibility(
            visible = !scrolledUp,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            CollapsedHeaderContent()
        }
    }
}

@Composable
fun CollapsedHeaderContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(painter = painterResource(R.drawable.little_lemon_logo,), contentDescription = "Little Lemon Logo", contentScale = ContentScale.Crop)
    }
}

@Composable
fun ExpandedHeaderContent(navController: NavController, onSearch: (String) -> Unit, sharedPreferences: SharedPreferences) {

    val iconTint= if(isSystemInDarkTheme()){
        ColorFilter.tint(Color.White)
    } else {
        ColorFilter.tint(Color.Black)
    }

    val userName=sharedPreferences.getString("first_name", "user")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                Text("Hello, $userName", fontFamily = MarkaziText, fontSize = 42.sp, fontWeight = FontWeight.SemiBold)
                Text("Welcome to Little Lemon!", fontFamily = Karla, fontWeight = FontWeight.Normal, fontSize = 20.sp, color = LittleLemonYellow)
            }
            IconButton(onClick = {
                navController.navigate("reservetable"){
                    launchSingleTop=true
                }
            }) {
                Image(painter = painterResource(R.drawable.reserve_table), contentDescription = "Reserve table", modifier = Modifier
                    .height(100.dp)
                    .width(100.dp), colorFilter = iconTint )
            }
        }
        SearchBox {  }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBox(modifier: Modifier = Modifier, onSearch: (String) -> Unit) {
    var searchQuery by remember{
        mutableStateOf("")
    }


    TextField(
        value = searchQuery,
        onValueChange = {searchQuery=it},
        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Search Icon",
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        placeholder = {
            Text("Search For Food", color = MaterialTheme.colorScheme.onSurfaceVariant)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        colors= TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            containerColor = MaterialTheme.colorScheme.onSurface
        ),
        singleLine = true
    )
}


@Composable
fun ItemCard(modifier: Modifier=Modifier, menu: Menu) {
    var isLoading = remember { false }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(8.dp))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(menu.image)
                    .crossfade(true)
                    .build(),
                contentDescription = menu.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                onLoading = { isLoading = true },
                onSuccess = { isLoading = false }
            )
//            if (isLoading) {
//                CircularProgressIndicator(
//                    color = MaterialTheme.colorScheme.primary,
//                    strokeWidth = 2.dp
//                )
//            }
            Spacer(Modifier.height(8.dp))
            Text(text = menu.title, style = MaterialTheme.typography.titleMedium)
            Text(text = menu.description, style = MaterialTheme.typography.bodyMedium)
            Text(text = "Price: ${menu.price}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun sortButtons(modifier: Modifier = Modifier, categories: List<String>, selectedCategory: String, onCategorySelected: (String)->Unit) {
    LazyRow {
        items(categories){
            category->
            val isSelected = category == selectedCategory
            val backgroundColor = if(isSelected) LittleLemonGreen else Color.LightGray
            val textColor = if(isSelected) Color.White else Color.Black

            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .clip(RoundedCornerShape(50))
                    .background(backgroundColor)
                    .clickable { onCategorySelected(category) }
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                contentAlignment = Alignment.Center
            ){
                Text(text = category, color = textColor, fontFamily = Karla, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//private fun homeScreenPrev() {
//    CollapsedHeaderContent()
//}
