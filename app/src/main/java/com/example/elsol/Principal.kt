package com.example.elsol

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.elsol.ui.theme.ElSolTheme
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
            Scaffold(bottomBar = {
                TopAppBar(
                    title = {
                        Text(text = "CoffeeShops", color = Color.White, fontSize = 20.sp)
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    },
                    actions = {
                        Row() {
                            IconButton(
                                onClick = {
                                    isMenuVisible = true
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.MoreVert,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        }
                        Row() {
                            DropdownMenu(
                                expanded = isMenuVisible,
                                onDismissRequest = {
                                    isMenuVisible = false
                                },
                                modifier = Modifier.background(PurpleGrey80)
                            ) {
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = "Compartir",
                                            color = Color.Black,
                                            fontSize = 16.sp
                                        )
                                    },
                                    onClick = { isMenuVisible = false },
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Default.Share,
                                            contentDescription = null,
                                            tint = Color.Black
                                        )
                                    },
                                )
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = "Album",
                                            color = Color.Black,
                                            fontSize = 16.sp
                                        )
                                    },
                                    onClick = { isMenuVisible = false },
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Default.Lock,
                                            contentDescription = null,
                                            tint = Color.Black
                                        )
                                    },
                                )
                            }
                        }
                    },
                    colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Purple40)
                )
            }
            ) {
                Column(modifier = Modifier.padding(top = 60.dp)) {
                    LazyColumn {
                        items(getCardCoffee()) { lazy ->
                            ItemsCoffe(cardCoffee = lazy, navController = navController)
                        }
                    }
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
            .clickable { navController.navigate("Comentarios/${cardCoffee.name}") },
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
                    .size(200.dp),
                contentScale = ContentScale.Crop
            )
        }
        Scaffold(bottomBar = {
            TopAppBar(
                title = {
                    Text(text =cardCoffee.name, color = Color.White, fontSize = 20.sp)
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    Row() {
                        IconButton(
                            onClick = {
                                isMenuVisible = true
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                    Row() {
                        DropdownMenu(
                            expanded = isMenuVisible,
                            onDismissRequest = {
                                isMenuVisible = false
                            },
                            modifier = Modifier.background(PurpleGrey80)
                        ) {
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "Compartir",
                                        color = Color.Black,
                                        fontSize = 16.sp
                                    )
                                },
                                onClick = { isMenuVisible = false },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Share,
                                        contentDescription = null,
                                        tint = Color.Black
                                    )
                                },
                            )
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        text = "Album",
                                        color = Color.Black,
                                        fontSize = 16.sp
                                    )
                                },
                                onClick = { isMenuVisible = false },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Default.Lock,
                                        contentDescription = null,
                                        tint = Color.Black
                                    )
                                },
                            )
                        }
                    }
                },
            )

        }){ Text(text = "hola")}
    }
}


