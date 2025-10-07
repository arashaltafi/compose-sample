package ir.arash.altafi.sample.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface Route {
    val route: String

    @Serializable
    data object Splash : Route {
        override val route: String = ".ui.navigation.Route.Splash"
    }

    @Serializable
    data object Main : Route {
        override val route: String = ".ui.navigation.Route.Main"
    }

    @Serializable
    data object FloatingBottomNavBar : Route {
        override val route: String = ".ui.navigation.Route.FloatingBottomNavBar"
    }

    @Serializable
    data object Main2 : Route {
        override val route: String = ".ui.navigation.Route.Main2"
    }

    @Serializable
    data object User : Route {
        override val route: String = ".ui.navigation.Route.User"
    }

    @Serializable
    data object Celebrity : Route {
        override val route: String = ".ui.navigation.Route.Celebrity"
    }

    @Serializable
    data object Paging : Route {
        override val route: String = ".ui.navigation.Route.Paging"
    }

    @Serializable
    data class ImageScreen(var title: String, val imageUrl: String) : Route {
        override val route: String = ".ui.navigation.Route.ImageScreen"
    }

    @Serializable
    data object TestList : Route {
        override val route: String = ".ui.navigation.Route.TestList"
    }

    @Serializable
    data object TestPagingList : Route {
        override val route: String = ".ui.navigation.Route.TestPagingList"
    }

    @Serializable
    data class TestDetail(var userId: String) : Route {
        override val route: String = ".ui.navigation.Route.TestDetail"
    }

    // bottom navigation
    @Serializable
    data object Home : Route {
        override val route: String = ".ui.navigation.Route.Home"
    }

    @Serializable
    data object Profile : Route {
        override val route: String = ".ui.navigation.Route.Profile"
    }

    @Serializable
    data object Setting : Route {
        override val route: String = ".ui.navigation.Route.Setting"
    }

    @Serializable
    data object Test : Route {
        override val route: String = ".ui.navigation.Route.Test"
    }
}