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
            label = R.string.home,
            icon = R.drawable.ic_home,
            route = Route.Home,
        ),
        NavigationDrawerItem(
            label = R.string.profile,
            icon = R.drawable.ic_profile,
            route = Route.Profile,
        ),
        NavigationDrawerItem(
            label = R.string.test,
            icon = R.drawable.ic_test,
            route = Route.Test,
        ),
        NavigationDrawerItem(
            label = R.string.setting,
            icon = R.drawable.ic_setting,
            route = Route.Setting,
        ),
    )
}