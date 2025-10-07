package ir.arash.altafi.sample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius
import com.exyte.animatednavbar.items.dropletbutton.DropletButton
import dagger.hilt.android.AndroidEntryPoint
import ir.arash.altafi.sample.navigation.AppNavigation
import ir.arash.altafi.sample.ui.theme.Green500
import ir.arash.altafi.sample.ui.theme.Red500
import ir.arash.altafi.sample.ui.theme.White
import ir.arash.altafi.sample.ui.theme.Yellow500
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation()
//            SampleTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->
////                    MyApp()
//                    FloatingBottomNavBar()
//                }
//            }
        }
    }
}

@Composable
fun MyApp() {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val context = LocalContext.current

    // For double-back to exit
    var backPressTimestamp by remember { mutableLongStateOf(0L) }
    val exitThreshold = 5_000L
    val coroutineScope = rememberCoroutineScope()

    BackHandler {
        if (selectedIndex != 0) {
            selectedIndex = 0
        } else {
            val now = System.currentTimeMillis()
            if (now - backPressTimestamp < exitThreshold) {
                (context as? ComponentActivity)?.finish()
            } else {
                backPressTimestamp = now
                Toast.makeText(context, "Press back again to exit", Toast.LENGTH_SHORT).show()

                coroutineScope.launch {
                    delay(exitThreshold)
                    if (System.currentTimeMillis() - backPressTimestamp >= exitThreshold) {
                        backPressTimestamp = 0L
                    }
                }
            }
        }
    }

    Surface(color = MaterialTheme.colorScheme.background) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Content
            when (selectedIndex) {
                0 -> PageScreen("Home Page")
                1 -> PageScreen("Search Page")
                2 -> PageScreen("Notification Page")
                3 -> PageScreen("Setting Page")
                4 -> PageScreen("Profile Page")
            }

            // Bottom bar
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
            ) {
                AnimatedNavigationBar(
                    selectedIndex = selectedIndex,
                    barColor = Yellow500,
                    ballColor = Green500,
                    cornerRadius = shapeCornerRadius(16.dp),
                    ballAnimation = Parabolic(tween(300)),
                    indentAnimation = Height(animationSpec = tween(500)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(78.dp)
                ) {
                    DropletButton(
                        icon = R.drawable.ic_home,
                        isSelected = selectedIndex == 0,
                        onClick = { selectedIndex = 0 },
                        size = 32.dp,
                        modifier = Modifier.padding(vertical = 16.dp),
                        dropletColor = Red500,
                        iconColor = White,
                        contentDescription = "Home"
                    )
                    DropletButton(
                        icon = R.drawable.ic_search,
                        isSelected = selectedIndex == 1,
                        onClick = { selectedIndex = 1 },
                        size = 32.dp,
                        modifier = Modifier.padding(vertical = 16.dp),
                        dropletColor = Red500,
                        iconColor = White,
                        contentDescription = "Search"
                    )
                    DropletButton(
                        icon = R.drawable.ic_notification,
                        isSelected = selectedIndex == 2,
                        onClick = { selectedIndex = 2 },
                        size = 32.dp,
                        modifier = Modifier.padding(vertical = 16.dp),
                        dropletColor = Red500,
                        iconColor = White,
                        contentDescription = "Notification"
                    )
                    DropletButton(
                        icon = R.drawable.ic_setting,
                        isSelected = selectedIndex == 3,
                        onClick = { selectedIndex = 3 },
                        size = 32.dp,
                        modifier = Modifier.padding(vertical = 16.dp),
                        dropletColor = Red500,
                        iconColor = White,
                        contentDescription = "Setting"
                    )
                    DropletButton(
                        icon = R.drawable.ic_profile,
                        isSelected = selectedIndex == 4,
                        onClick = { selectedIndex = 4 },
                        size = 32.dp,
                        modifier = Modifier.padding(vertical = 16.dp),
                        dropletColor = Red500,
                        iconColor = White,
                        contentDescription = "Profile"
                    )
                }
            }
        }
    }
}

@Composable
fun PageScreen(title: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = title, style = MaterialTheme.typography.headlineMedium)
    }
}
