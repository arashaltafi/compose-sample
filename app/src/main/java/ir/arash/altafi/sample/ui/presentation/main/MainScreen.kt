package ir.arash.altafi.sample.ui.presentation.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import ir.arash.altafi.sample.ui.presentation.home.HomeScreen
import ir.arash.altafi.sample.ui.presentation.paging.PagingScreen
import ir.arash.altafi.sample.ui.presentation.testList.TestList
import kotlinx.coroutines.launch

@Composable
fun MainScreen(navController: NavHostController) {
    val tabs = listOf("Users", "Celebrities", "Paging")
    val pagerState = rememberPagerState(
        pageCount = { tabs.size },
        initialPage = 0
    )
    val coroutineScope = rememberCoroutineScope()

    Column {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = {
                        Text(
                            text = title,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontStyle = FontStyle.Normal,
                        )
                    },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch { pagerState.animateScrollToPage(index) }
                    }
                )
            }
        }
        HorizontalPager(
            state = pagerState
        ) { page ->
            when (page) {
                0 -> TestList(navController)
                1 -> HomeScreen(navController)
                2 -> PagingScreen(navController)
            }
        }
    }
}