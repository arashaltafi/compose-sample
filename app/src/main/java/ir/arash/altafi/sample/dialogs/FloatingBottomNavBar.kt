package ir.arash.altafi.sample.dialogs

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material.icons.filled.LibraryBooks
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

enum class FloatingBottomNavItem(val title: String, val icon: Any) {
    Settings("Настройки", Icons.Default.Settings),
    Scanner("Сканер", Icons.Default.DocumentScanner),
    Library("Библиотека", Icons.Default.LibraryBooks)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FloatingBottomNavBar() {
    val pagerState = rememberPagerState(pageCount = { FloatingBottomNavItem.entries.size })
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier.fillMaxSize()) {
        // Pages (swipe left/right)
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        when (FloatingBottomNavItem.entries[page]) {
                            FloatingBottomNavItem.Settings -> Color(0xFFE3F2FD)
                            FloatingBottomNavItem.Scanner -> Color(0xFFE8F5E9)
                            FloatingBottomNavItem.Library -> Color(0xFFFFFDE7)
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Screen: ${FloatingBottomNavItem.entries[page].title}",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333)
                )
            }
        }

        // Floating bottom nav
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp)
                .shadow(12.dp, shape = RoundedCornerShape(24.dp))
                .background(Color(0xFFF8F6F0), shape = RoundedCornerShape(24.dp))
                .padding(horizontal = 24.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            FloatingBottomNavItem.entries.forEachIndexed { index, item ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .clickable {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                        .padding(horizontal = 12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                color = if (pagerState.currentPage == index) Color(0xFFFFE082) else Color.Transparent,
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (item.icon is androidx.compose.ui.graphics.vector.ImageVector) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title,
                                tint = Color(0xFF333333),
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = item.title,
                        fontSize = 12.sp,
                        fontWeight = if (pagerState.currentPage == index) FontWeight.Bold else FontWeight.Normal,
                        color = Color(0xFF333333)
                    )
                }
            }
        }
    }
}
