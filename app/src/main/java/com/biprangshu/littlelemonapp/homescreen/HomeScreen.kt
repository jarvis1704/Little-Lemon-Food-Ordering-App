package com.biprangshu.littlelemonapp.homescreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.biprangshu.littlelemonapp.R
import com.biprangshu.littlelemonapp.data.remote.Menu
import com.biprangshu.littlelemonapp.viewmodel.MainViewModel

@Composable
fun homeScreen(modifier: Modifier = Modifier, viewModel: MainViewModel = hiltViewModel(), navController: NavController) {


    val isLoading by viewModel.isLoading.collectAsState() // Collect isLoading StateFlow
    val errorMessage by viewModel.errorMessage.collectAsState() // Collect errorMessage StateFlow
    val menuItems by viewModel.menuItems.collectAsState()   // Collect menuItems StateFlow

    val listState= rememberLazyListState()
    val scrolledUp by remember {
        derivedStateOf { listState.firstVisibleItemIndex<=0 }
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

            dynamicHeader(navController = navController, scrolledUp = scrolledUp) { }

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
fun dynamicHeader(navController: NavController, scrolledUp: Boolean, onSearch: (String)-> Unit) {
    val headerHeightExpanded=150.dp
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
            ExpandedHeaderContent(navController = navController, onSearch = onSearch)
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
fun ExpandedHeaderContent(navController: NavController, onSearch: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row {
            Column {
                Text("Hello User!")
                Text("Welcome to Little Lemon!")
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
fun ItemCard(menu: Menu) {
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
            if (isLoading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = 2.dp
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(text = menu.title, style = MaterialTheme.typography.titleMedium)
            Text(text = menu.description, style = MaterialTheme.typography.bodyMedium)
            Text(text = "Price: ${menu.price}", style = MaterialTheme.typography.bodySmall)
        }
    }
}
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//private fun homeScreenPrev() {
//    CollapsedHeaderContent()
//}
