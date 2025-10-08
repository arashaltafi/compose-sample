package ir.arash.altafi.sample.ui.component

import android.app.Activity
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import ir.arash.altafi.sample.navigation.Route
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BackPressHandler(
    navController: NavController,
    onNavigationItemSelected: (Int) -> Unit
) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route

    val context = LocalContext.current
    val activity = (context as? Activity)
    val packageName = context.packageName

    val isHome = currentDestination == packageName + Route.Home.route

    val allowBottomBar = arrayOf(
        packageName + Route.Home.route,
        packageName + Route.Test.route,
        packageName + Route.Profile.route,
        packageName + Route.Setting.route
    )

    var doubleBackToExitPressedOnce by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    // Use BackHandler to intercept the system back press
    BackHandler {
        if (currentDestination !in allowBottomBar) {
            navController.popBackStack()
        } else if (navController.previousBackStackEntry != null) {
            // Pop the backstack if there is a previous route
            onNavigationItemSelected(0)
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
    }
}
