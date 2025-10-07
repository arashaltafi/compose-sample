package ir.arash.altafi.sample.navigation

import ir.arash.altafi.sample.R

data class BottomNavigationItem(
    val label: Int,
    val icon: Int,
    val route: Route,
    val badgeCount: Int,
)

fun bottomNavigationItems(): List<BottomNavigationItem> {
    return listOf(
        BottomNavigationItem(
            label = R.string.home,
            icon = R.drawable.ic_home,
            route = Route.Home,
            badgeCount = 0
        ),
        BottomNavigationItem(
            label = R.string.test,
            icon = R.drawable.ic_test,
            route = Route.Test,
            badgeCount = 0
        ),
        BottomNavigationItem(
            label = R.string.profile,
            icon = R.drawable.ic_profile,
            route = Route.Profile,
            badgeCount = 0
        ),
        BottomNavigationItem(
            label = R.string.setting,
            icon = R.drawable.ic_setting,
            route = Route.Setting,
            badgeCount = 0
        ),
    )
}