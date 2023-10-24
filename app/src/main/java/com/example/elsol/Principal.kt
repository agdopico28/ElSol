package com.example.elsol

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.elsol.ui.theme.ElSolTheme
import com.example.elsol.ui.theme.Pink40
import com.example.elsol.ui.theme.Purple40
import com.example.elsol.ui.theme.PurpleGrey80
import java.security.Principal

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Principal(navController: NavHostController) {
    var isMenuVisible by remember { mutableStateOf(false) }
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
        }

        else -> {
            Scaffold(
                //snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                bottomBar = {
                    BottomAppBar(
                        containerColor = Color.Red,
                        contentColor = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            //.zIndex(1f)
                    )
                    {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 5.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                IconButton(
                                    onClick = {
                                        //showDrawer = !showDrawer
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = null,
                                        tint = Color.White
                                    )
                                }
                                /*BadgedBox(badge = {
                                    Text(text = badgeCount.toString(), modifier = Modifier
                                        .background(Color.Blue, shape = CircleShape)) }) {

                                    Icon(

                                        imageVector = Icons.Default.Favorite,
                                        contentDescription = null,
                                        modifier = Modifier.clickable { badgeCount++ },
                                        tint = Color.White
                                    )
                                }*/
                            }
                            Row {
                                FloatingActionButton(onClick = { /*TODO*/ }, containerColor = Pink40) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = null,
                                        tint = Color.Black
                                    )
                                }
                            }
                        }
                    }
                }
            )
            {
                Column(modifier = Modifier.padding(bottom = 60.dp)) {
                    LazyVerticalGrid(columns = GridCells.Fixed(2),
                        content = {items(getCardCoffee()) { lazy ->
                        ItemsCoffe(cardCoffee = lazy, navController = navController)}
                    } )
                }
            }


        }
    }

}

data class CardCoffee(var name: String, @DrawableRes var image: Int)

fun getCardCoffee(): List<CardCoffee> {
    return listOf(
        CardCoffee(
            "Antico Caffè Greco",
            R.drawable.corona_solar
        ),
        CardCoffee(
            "Coffee Room",
            R.drawable.erupcionsolar
        ),
        CardCoffee(
            "Coffee Ibiza",
            R.drawable.espiculas
        ),
        CardCoffee(
            "Pudding Coffee Shop",
            R.drawable.filamentos
        ),
        CardCoffee(
            "L'Express",
            R.drawable.magnetosfera
        ),
        CardCoffee(
            "Coffee Ibiza",
            R.drawable.manchasolar
        ),

        )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemsCoffe(cardCoffee: CardCoffee, navController: NavHostController) {
    var isMenuVisible by remember { mutableStateOf(false) }
    Card(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { },
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = cardCoffee.image),
                contentDescription = "Coffee",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(175.dp),
                contentScale = ContentScale.Crop
            )
            BottomAppBar(modifier = Modifier.height(55.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = cardCoffee.name, modifier = Modifier.padding(start = 5.dp))
                    IconButton(
                        onClick = {
                            isMenuVisible = true
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Abrir menú"
                        )
                    }
                }

                DropdownMenu(
                    expanded = isMenuVisible,
                    onDismissRequest = { isMenuVisible = false },
                    offset = DpOffset(0.dp, ((-40).dp))
                )
                {
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "Copiar",
                                color = Color.Black,
                                fontSize = 16.sp
                            )
                        },
                        onClick = { isMenuVisible = false },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null,
                                tint = Color.Black
                            )
                        },
                    )
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = "Eliminar",
                                color = Color.Black,
                                fontSize = 16.sp
                            )
                        },
                        onClick = { isMenuVisible = false },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                                tint = Color.Black
                            )
                        },
                    )
                }
            }


        }
    }
}


