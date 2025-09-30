package ir.arash.altafi.sample.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.arash.altafi.mvisample.ui.component.ImageScreen
import ir.arash.altafi.sample.ui.presentation.celebrity.CelebrityScreen
import com.arash.altafi.mvisample.ui.presentation.main.MainScreen
import ir.arash.altafi.sample.ui.presentation.main.MainScreen2
import ir.arash.altafi.sample.ui.presentation.paging.PagingScreen
import ir.arash.altafi.sample.ui.presentation.testDetail.TestDetail
import ir.arash.altafi.sample.ui.presentation.testList.TestList
import ir.arash.altafi.sample.ui.presentation.testPagingList.TestPagingList
import ir.arash.altafi.sample.ui.presentation.user.UserScreen
import ir.arash.altafi.sample.ui.theme.SampleTheme

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    SampleTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Route.Main2,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable<Route.Main> {
                    MainScreen(navController)
                }
                composable<Route.Main2> {
                    MainScreen2(navController)
                }
                composable<Route.User> {
                    UserScreen(navController)
                }
                composable<Route.Celebrity> {
                    CelebrityScreen(navController)
                }
                composable<Route.Paging> {
                    PagingScreen(navController)
                }
                composable<Route.ImageScreen> { backStackEntry: NavBackStackEntry ->
                    val args = backStackEntry.toRoute<Route.ImageScreen>()
                    ImageScreen(navController, args.title, args.imageUrl)
                }
                composable<Route.TestList> {
                    TestList(navController)
                }
                composable<Route.TestPagingList> {
                    TestPagingList(navController)
                }
                composable<Route.TestDetail> { backStackEntry: NavBackStackEntry ->
                    val args = backStackEntry.toRoute<Route.TestDetail>()
                    TestDetail(args.userId, navController)
                }
            }
        }
    }
}