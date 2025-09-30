package ir.arash.altafi.sample.navigation

import ir.arash.altafi.sample.R

data class NavigationDrawerItem(
    val label: Int,
    val icon: Int,
    val route: Route,
)

fun navigationDrawerItems(): List<NavigationDrawerItem> {
    return listOf(
        NavigationDrawerItem(
            label = R.string.dialog,
            icon = R.drawable.ic_home,
            route = Route.Main,
        ),
        NavigationDrawerItem(
            label = R.string.profile,
            icon = R.drawable.ic_profile,
            route = Route.User,
        ),
        NavigationDrawerItem(
            label = R.string.chat_room,
            icon = R.drawable.ic_notification,
            route = Route.Paging,
        ),
        NavigationDrawerItem(
            label = R.string.setting,
            icon = R.drawable.ic_setting,
            route = Route.Main2,
        ),
    )
}