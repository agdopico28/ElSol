package com.example.elsol

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.example.elsol.ui.theme.ElSolTheme
import com.example.elsol.ui.theme.Pink40
import com.example.elsol.ui.theme.Purple40
import com.example.elsol.ui.theme.PurpleGrey80
import kotlinx.coroutines.launch
import java.security.Principal

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Principal(navController: NavHostController) {
    var badgeCount by remember { mutableStateOf(0) }
    var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    Scaffold(snackbarHost = {
        SnackbarHost(hostState = snackbarHostState)
    },
        bottomBar = { //barra de abajo
            BottomAppBar(
                containerColor = Color.Red,// color de fondo
                contentColor = Color.White, // color de las letras
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(1f)
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
                        IconButton( // boton para sacar el menu arrastrando
                            onClick = {
                                scope.launch { drawerState.open() }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                        BadgedBox(badge = {//corazon con el contador encima para que cada vez que puses aumente
                            Badge {
                                Text(text = badgeCount.toString())
                            }

                        }, modifier = Modifier
                            .padding(10.dp)
                            .clickable { badgeCount++ }) {//clicas aumenta el contador
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                    Row {//boton que no have nada
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
        val items = listOf(Icons.Default.Build, Icons.Default.Info, Icons.Default.Email)
        val selectedItem = remember {
            mutableStateOf(items[0])
        }
        ModalNavigationDrawer(drawerState = drawerState, //
            drawerContent = {
                ModalDrawerSheet {
                    Image(
                        painter = painterResource(id = R.drawable.sol),
                        contentDescription = "Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    items.forEach { item ->
                        NavigationDrawerItem(
                            icon = { Icon(item, contentDescription = null) },
                            label = { Text(text = item.name.substringAfter(".")) },
                            selected = item == selectedItem.value,
                            onClick = {
                                scope.launch { drawerState.close() }
                                selectedItem.value = item
                                navController.navigate(item.name)
                            })
                    }
                }
            }, content = {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = it.calculateBottomPadding())
                ) {
                    val cardDataList = getCardData()
                    //recorre la el getCardData() y los distribuye en 2 columnas
                    LazyVerticalGrid(columns = GridCells.Fixed(2),
                        content = {
                            items(cardDataList) { cardData ->
                                ItemCard(cardData, snackbarHostState) //snackbarHostState -> para que al pulsar la carta aparezca el texto abajo de la pantalla
                            }
                        }
                    )
                }
            })
    }
}


data class CardData( //la clase principal
    var name: String,
    @DrawableRes var photo: Int
)

fun getCardData(): List<CardData> {//rellenamos los valores que despues vamos a utilizar
    return listOf(
        CardData(
            "Corona Solar",
            R.drawable.corona_solar,
        ),
        CardData(
            "Erupción solar",
            R.drawable.erupcionsolar,
        ),
        CardData(
            "Espículas",
            R.drawable.espiculas,
        ),
        CardData(
            "Filamentos",

            R.drawable.filamentos,
        ),
        CardData(
            "Magnetosfera",
            R.drawable.magnetosfera,
        ),
        CardData(
            "Mancha solar",
            R.drawable.manchasolar,
        ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemCard(cardData: CardData, snackbarHostState: SnackbarHostState) {
    var isImageMenuVisible by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope() //importante! para la Snackbar
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        onClick = { scope.launch { snackbarHostState.showSnackbar(cardData.name) } } //hace que al clicar aparezca el texto abajo
    ) {
        Column(Modifier.fillMaxSize()) {
            Image(//la imagen que va a aparecer en la carta
                painter = painterResource(id = cardData.photo), //referencia a la clase data
                contentDescription = "Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp), //modificamos el tamaño
                contentScale = ContentScale.Crop
            )
            BottomAppBar(modifier = Modifier.height(55.dp)) {//barra de abajo de cada carta
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = cardData.name, modifier = Modifier.padding(start = 10.dp)) //incluye el nombre de data class en la barra
                    IconButton( //el icono del menu
                        onClick = {
                            isImageMenuVisible = true
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Abrir menú"
                        )
                    }
                }

                DropdownMenu( //la expansion de la bottomBar
                    expanded = isImageMenuVisible,
                    onDismissRequest = { isImageMenuVisible = false },
                    offset = DpOffset(0.dp, ((-40).dp))
                )
                {
                    DropdownMenuItem( //primer item que es el de copiar
                        text = {
                            Text(
                                text = "Copiar",
                                color = Color.Black,
                                fontSize = 16.sp
                            )
                        },
                        onClick = { isImageMenuVisible = false }, //hace que cuado cliques encima del texto, desaparezca el DropdownMenu
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
                        onClick = { isImageMenuVisible = false },
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


