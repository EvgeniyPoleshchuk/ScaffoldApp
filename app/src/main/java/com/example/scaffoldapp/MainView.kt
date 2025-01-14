package com.example.scaffoldapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.primarySurface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.IconButton


import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MainView() {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope: CoroutineScope = rememberCoroutineScope()
    val isSheetFullScreen by remember { mutableStateOf(false) }
    val modifier = if (isSheetFullScreen) Modifier.fillMaxSize() else Modifier.fillMaxWidth()
    val controller: NavController = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val viewModel: MainViewModel = viewModel()
    val currentRoute = navBackStackEntry?.destination?.route
    val currentScreen = remember { viewModel.currentScreen.value }
    val title = remember { mutableStateOf(currentScreen.title) }
    val dialogOpen = remember { mutableStateOf(false) }


    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    val roundedCornerRadius = if (isSheetFullScreen) 0.dp else 12.dp

    val bottomBar: @Composable () -> Unit = {
        if (currentScreen is Screen.DrawerScreen || currentScreen == Screen.BottomScreen.Home) {
            BottomNavigation(Modifier.wrapContentSize()) {
                screenInBottom.forEach { item ->
                    val isSelected = currentRoute == item.bRoute
                    val tint = if (isSelected) Color.White else Color.Black
                    BottomNavigationItem(currentRoute == item.bRoute,
                        onClick = {
                            controller.navigate(item.bRoute)
                            title.value = item.bTitle
                        }, icon = {
                            Icon(painterResource(item.icon), item.bTitle, tint = tint)
                        }, label = { Text(item.bTitle, color = tint) },
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.Black
                    )

                }
            }
        }
    }
    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(
            topStart = roundedCornerRadius,
            topEnd = roundedCornerRadius
        ),
        sheetContent = {
            MoreBottomSheet(modifier = modifier)
        }) {
        Scaffold(
            bottomBar = { bottomBar() },
            topBar = {
                TopAppBar(title = {
                    Text(title.value)
                }, actions = {
                    IconButton({
                        scope.launch {
                            if (modalSheetState.isVisible)
                                modalSheetState.hide()
                            else
                                modalSheetState.show()
                        }
                    }) {
                        Icon(Icons.Default.MoreVert, "")

                    }
                },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }) {
                            Icon(Icons.Default.AccountCircle, "")
                        }
                    })
            }, scaffoldState = scaffoldState,
            drawerContent = {
                LazyColumn(modifier = Modifier.padding(top = 20.dp)) {
                    items(screensInDrawer) { item ->
                        DrawerItem(currentRoute == item.route, item) {
                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                            if (item.dRoute == "add_account") {
                                dialogOpen.value = true
                            } else {
                                controller.navigate(item.dRoute)
                                title.value = item.dTitle
                            }

                        }
                    }
                }
            }


        ) {
            Navigation(controller, viewModel, it)
            Account(dialogOpen)
        }
    }
}

@Composable
fun MoreBottomSheet(modifier: Modifier) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(MaterialTheme.colors.primarySurface)
    ) {
        Column(modifier = modifier.padding(16.dp), verticalArrangement = Arrangement.SpaceBetween) {
            Row(modifier.padding(16.dp).fillMaxWidth()) {
                Icon(
                    painter = painterResource(R.drawable.baseline_settings_24), contentDescription = ""
                )
                Text("Settings", fontSize = 20.sp, color = Color.White,modifier = modifier.padding(start = 8.dp))
            }
            Row(modifier.padding(16.dp).fillMaxWidth()) {
                Icon(
                    painter = painterResource(R.drawable.ic_baseline_share_24), contentDescription = ""
                )
                Text("Share", fontSize = 20.sp, color = Color.White,modifier = modifier.padding(start = 8.dp))
            }
            Row(modifier.padding(16.dp).fillMaxWidth()) {
                Icon(
                    painter = painterResource(R.drawable.ic_help_green), contentDescription = ""
                )
                Text("Help", fontSize = 20.sp, color = Color.White,modifier = modifier.padding(start = 8.dp))
            }
        }
    }
}

@Composable
fun Navigation(navController: NavController, viewModel: MainViewModel, dp: PaddingValues) {
    NavHost(
        navController = navController as NavHostController,
        startDestination = Screen.DrawerScreen.Account.route, modifier = Modifier.padding(dp)
    ) {
        composable(Screen.BottomScreen.Home.bRoute) {
            HomeView()
        }
        composable(Screen.BottomScreen.Browse.bRoute) {
            Browse()
        }
        composable(Screen.BottomScreen.Library.bRoute) {
            Library()
        }
        composable(Screen.DrawerScreen.Account.route) {
            AccountView()
        }
        composable(Screen.DrawerScreen.Subscription.route) {
            Subscription()
        }
    }
}

@Composable
fun DrawerItem(
    selected: Boolean,
    item: Screen.DrawerScreen,
    onDrawerItemClicked: () -> Unit
) {
    val background = if (selected) Color.DarkGray else Color.White

    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp)
            .background(background)
            .clickable {
                onDrawerItemClicked()
            }, verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painterResource(item.icon), item.dTitle, Modifier.padding(end = 8.dp, top = 2.dp)
        )
        Text(text = item.dTitle, style = MaterialTheme.typography.h5)
    }

}