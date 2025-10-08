package ir.arash.altafi.sample.navigation

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.*
import androidx.navigation.toRoute
import ir.arash.altafi.sample.ui.component.ImageScreen
import ir.arash.altafi.sample.ui.presentation.main.MainScreen
import ir.arash.altafi.sample.R
import ir.arash.altafi.sample.ui.component.BackPressHandler
import ir.arash.altafi.sample.ui.component.ImageUrl
import ir.arash.altafi.sample.ui.presentation.home.HomeScreen
import ir.arash.altafi.sample.ui.presentation.main.MainScreen2
import ir.arash.altafi.sample.ui.presentation.paging.PagingScreen
import ir.arash.altafi.sample.ui.presentation.profile.ProfileScreen
import ir.arash.altafi.sample.ui.presentation.setting.SettingScreen
import ir.arash.altafi.sample.ui.presentation.test.TestScreen
import ir.arash.altafi.sample.ui.presentation.testDetail.TestDetail
import ir.arash.altafi.sample.ui.presentation.testPagingList.TestPagingList
import ir.arash.altafi.sample.ui.theme.CustomFont
import ir.arash.altafi.sample.ui.theme.SampleTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route

    val context = LocalContext.current
    val activity = (context as? Activity)
    val packageName = context.packageName

    val isHome = currentDestination == packageName + Route.Home.route

    var isScrolled by remember { mutableStateOf(false) }

    var doubleBackToExitPressedOnce by remember { mutableStateOf(false) }
    var navigationSelectedItem by remember { mutableIntStateOf(0) }
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }

    val coroutineScope = rememberCoroutineScope()

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val darkTheme: Boolean = isSystemInDarkTheme()
    var theme by remember { mutableStateOf("dark") }

    var isConnected by remember { mutableStateOf(false) }

    val allowBottomBar = arrayOf(
        packageName + Route.Home.route,
        packageName + Route.Test.route,
        packageName + Route.Profile.route,
        packageName + Route.Setting.route
    )
    val allowTopBar = arrayOf(
        packageName + Route.Home.route,
        packageName + Route.Test.route,
        packageName + Route.Profile.route,
        packageName + Route.Setting.route
    )
    val allowNavigationBar = arrayOf(
        packageName + Route.Home.route,
        packageName + Route.Test.route,
        packageName + Route.Profile.route,
        packageName + Route.Setting.route
    )

    SampleTheme(
        darkTheme = darkTheme
    ) {
        ModalNavigationDrawer(
            gesturesEnabled = currentDestination in allowNavigationBar,
            drawerContent = {
                ModalDrawerSheet(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(LocalConfiguration.current.screenWidthDp.dp * 0.7f),
                    drawerContainerColor = colorResource(R.color.blue_200),
                    drawerContentColor = colorResource(R.color.blue_200),
                    drawerShape = RoundedCornerShape(topEnd = 20.dp, bottomEnd = 20.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 32.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp, 0.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            ImageUrl(
                                url = "https://arashaltafi.ir/arash.jpg",
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(CircleShape)
                                    .shadow(8.dp)
                                    .border(
                                        1.dp,
                                        Color.Green,
                                        CircleShape
                                    )
                            )
                            Spacer(Modifier.width(22.dp))
                            Text(
                                text = "arash altafi",
                                fontSize = 16.sp,
                                fontStyle = FontStyle.Normal,
                                fontFamily = CustomFont,
                                color = Color.White
                            )
                        }

                        Spacer(Modifier.height(20.dp))

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            navigationDrawerItems().forEachIndexed { index, item ->
                                val isSelected = index == selectedItemIndex
                                val contentColor = if (isSelected) Color.Magenta else Color.White

                                NavigationDrawerItem(
                                    modifier = Modifier
                                        .padding(NavigationDrawerItemDefaults.ItemPadding),
                                    label = {
                                        Text(
                                            text = context.getString(item.label),
                                            color = contentColor,
                                            fontFamily = CustomFont
                                        )
                                    },
                                    selected = isSelected,
                                    icon = {
                                        Icon(
                                            painter = painterResource(item.icon),
                                            contentDescription = context.getString(item.label),
                                            tint = contentColor
                                        )
                                    },
                                    colors = NavigationDrawerItemDefaults.colors(
                                        selectedContainerColor = Color.Transparent,
                                        unselectedContainerColor = Color.Transparent
                                    ),
                                    onClick = {
                                        navController.navigate(item.route)
                                        selectedItemIndex = index
                                        navigationSelectedItem = index
                                        coroutineScope.launch {
                                            drawerState.close()
                                        }
                                    }
                                )
                            }
                        }

                        Spacer(Modifier.height(20.dp))

                        Row(
                            modifier = Modifier
                                .padding(16.dp, 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            NavigationDrawerItem(
                                modifier = Modifier
                                    .background(
                                        color = colorResource(R.color.red_300),
                                        shape = RoundedCornerShape(
                                            16.dp
                                        )
                                    )
                                    .padding(NavigationDrawerItemDefaults.ItemPadding),
                                label = {
                                    Text(
                                        text = context.getString(R.string.logout),
                                        color = Color.White,
                                        fontFamily = CustomFont
                                    )
                                },
                                selected = false,
                                icon = {
                                    Icon(
                                        painter = painterResource(R.drawable.ic_logout),
                                        contentDescription = context.getString(R.string.logout),
                                        tint = Color.White
                                    )
                                },
                                colors = NavigationDrawerItemDefaults.colors(
                                    selectedContainerColor = Color.Transparent,
                                    unselectedContainerColor = Color.Transparent
                                ),
                                onClick = {
                                    coroutineScope.launch {
                                        drawerState.close()
                                    }
                                }
                            )
                        }
                    }
                }
            },
            drawerState = drawerState,
        ) {
            Scaffold(
                modifier = Modifier
                    .fillMaxSize(),
                topBar = {
                    if (currentDestination in allowTopBar) {
                        @OptIn(ExperimentalMaterial3Api::class)
                        TopAppBar(
                            modifier = Modifier
                                .clip(RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp)),
                            title = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    if (!isConnected) {
                                        Icon(
                                            imageVector = Icons.Filled.Error,
                                            contentDescription = "No internet connection",
                                            tint = Color.Red
                                        )
                                    }
                                    val title = when (currentDestination) {
                                        packageName + Route.Home.route -> context.getString(R.string.home)
                                        packageName + Route.Setting.route -> context.getString(R.string.setting)
                                        packageName + Route.Profile.route -> context.getString(R.string.profile)
                                        packageName + Route.Test.route -> context.getString(R.string.test)
                                        else -> context.getString(R.string.app_name)
                                    }
                                    Text(
                                        text = title,
                                        color = Color.White,
                                        fontFamily = CustomFont
                                    )
                                }
                            },
                            navigationIcon = {
                                Row {
                                    IconButton(
                                        onClick = {
                                            coroutineScope.launch {
                                                drawerState.open()
                                            }
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Menu,
                                            contentDescription = "Menu",
                                            tint = Color.White
                                        )
                                    }
                                }
                            },
                            actions = {
                                Row {
                                    IconButton(
                                        onClick = {
//                                            dataStoreViewModel.changeTheme()
                                        }
                                    ) {
                                        Icon(
                                            painter = painterResource(id = if (theme == "dark") R.drawable.round_light_mode_24 else R.drawable.round_dark_mode_24),
                                            contentDescription = if (theme == "dark") "Switch to Light Theme" else "Switch to Dark Theme",
                                            tint = Color.White
                                        )
                                    }
                                    AnimatedVisibility(visible = !isHome) {
                                        IconButton(
                                            onClick = {
                                                if (currentDestination !in allowBottomBar) {
                                                    navController.popBackStack()
                                                } else if (navController.previousBackStackEntry != null && navigationSelectedItem != 0) {
                                                    // Pop the backstack if there is a previous route
                                                    navigationSelectedItem = 0
                                                    navController.navigate(Route.Home) {
                                                        popUpTo(0)
                                                        launchSingleTop = true
                                                    }
                                                } else if (isHome) {
                                                    // Handle double back press to exit the app
                                                    if (doubleBackToExitPressedOnce) {
                                                        // Exit the app if back is pressed twice within 5 seconds
                                                        activity?.finish()
                                                    } else {
                                                        // Show the toast message and start a 5-second timer
                                                        doubleBackToExitPressedOnce = true
                                                        Toast.makeText(
                                                            context,
                                                            "برای خروج یک بار دیگر دکمه برگشت را بزنید",
                                                            Toast.LENGTH_SHORT
                                                        )
                                                            .show()

                                                        // Reset the flag after 5 seconds using coroutine
                                                        coroutineScope.launch {
                                                            delay(5000)  // 5-second delay
                                                            doubleBackToExitPressedOnce = false
                                                        }
                                                    }
                                                } else {
                                                    navController.popBackStack()
                                                }
                                            },
                                        ) {
                                            Icon(
                                                modifier = Modifier.rotate(180f),
                                                painter = painterResource(id = R.drawable.round_arrow_back_24),
                                                contentDescription = "Back",
                                                tint = Color.White
                                            )
                                        }
                                    }
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = colorResource(R.color.blue_300),
                                titleContentColor = Color.White,
                            )
                        )
                    }
                },
                bottomBar = {
                    if (currentDestination in allowBottomBar) {
                        AnimatedVisibility(
                            visible = !isScrolled,
                            enter = fadeIn() + expandHorizontally() + slideInHorizontally(),
                            exit = fadeOut() + shrinkHorizontally() + slideOutHorizontally()
                        ) {
                            NavigationBar(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(topEnd = 10.dp, topStart = 10.dp)),
                                containerColor = colorResource(R.color.blue_300),
                            ) {
                                //getting the list of bottom navigation items for our data class
                                bottomNavigationItems().forEachIndexed { index, navigationItem ->
                                    NavigationBarItem(
                                        selected = index == navigationSelectedItem,
                                        label = {
                                            Text(
                                                text = context.getString(navigationItem.label),
                                                fontFamily = CustomFont
                                            )
                                        },
                                        icon = {
                                            BadgedBox(
                                                badge = {
                                                    if (navigationItem.badgeCount != 0) {
                                                        Badge(
                                                            containerColor = Color.White,
                                                            modifier = Modifier.border(
                                                                1.dp,
                                                                Color.Magenta,
                                                                CircleShape
                                                            )
                                                        ) {
                                                            Text(
                                                                text = navigationItem.badgeCount.toString(),
                                                                fontFamily = CustomFont,
                                                                color = Color.Black
                                                            )
                                                        }
                                                    }
                                                },
                                            ) {
                                                Icon(
                                                    painter = painterResource(id = navigationItem.icon),
                                                    contentDescription = context.getString(
                                                        navigationItem.label
                                                    ),
                                                )
                                            }
                                        },
                                        onClick = {
                                            if (index != 0) navigationSelectedItem = index
                                            navController.navigate(navigationItem.route)
                                        },
                                        colors = NavigationBarItemDefaults.colors(
                                            selectedIconColor = Color.Magenta,
                                            selectedTextColor = Color.Magenta,
                                            unselectedIconColor = Color.White,
                                            unselectedTextColor = Color.White,
                                            indicatorColor = Color.Transparent
                                        )
                                    )
                                }
                            }
                        }
                    }
                },
                floatingActionButton = {
                    AnimatedVisibility(visible = isHome) {
                        FloatingActionButton(
                            onClick = {
                                navController.navigate(Route.Main)
                            },
                            containerColor = MaterialTheme.colorScheme.secondary,
                            shape = RoundedCornerShape(
                                topStart = 20.dp,
                                topEnd = 20.dp,
                                bottomStart = 20.dp,
                                bottomEnd = 0.dp
                            )
                        ) {
                            Icon(Icons.Filled.Add, contentDescription = "Add")
                        }
                    }
                },
                floatingActionButtonPosition = FabPosition.End,
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = Route.Home,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable<Route.Home> {
                        HomeScreen(navController)
                    }
                    composable<Route.Profile> {
                        ProfileScreen(navController)
                    }
                    composable<Route.Setting> {
                        SettingScreen(navController)
                    }
                    composable<Route.Test> {
                        TestScreen(navController)
                    }
                    composable<Route.Main> {
                        MainScreen(navController)
                    }
                    composable<Route.Main2> {
                        MainScreen2(navController)
                    }
                    composable<Route.Paging> {
                        PagingScreen(navController)
                    }
                    composable<Route.ImageScreen> { backStackEntry: NavBackStackEntry ->
                        val args = backStackEntry.toRoute<Route.ImageScreen>()
                        ImageScreen(navController, args.title, args.imageUrl)
                    }
                    composable<Route.TestPagingList> {
                        TestPagingList(navController)
                    }
                    composable<Route.TestDetail> { backStackEntry: NavBackStackEntry ->
                        val args = backStackEntry.toRoute<Route.TestDetail>()
                        TestDetail(args.userId, navController)
                    }
                }

                BackPressHandler(navController) { newItem ->
                    if (currentDestination in allowBottomBar)
                        navigationSelectedItem = newItem
                }
            }
        }
    }
}