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
            label = R.string.dialog,
            icon = R.drawable.ic_home,
            route = Route.Main,
            badgeCount = 0
        ),
        BottomNavigationItem(
            label = R.string.profile,
            icon = R.drawable.ic_profile,
            route = Route.User,
            badgeCount = 0
        ),
        BottomNavigationItem(
            label = R.string.chat_room,
            icon = R.drawable.ic_notification,
            route = Route.Paging,
            badgeCount = 0
        ),
        BottomNavigationItem(
            label = R.string.setting,
            icon = R.drawable.ic_setting,
            route = Route.Main2,
            badgeCount = 0
        ),
    )
}