package br.com.davidcastro.features.navigation

enum class Routes(val route: String, val routeWithArgs: String) {
    HomeScreen("home", ""),
    PhotoListScreen("photoList", "photoList/{data}"),
    PhotoDetailScreen("photoDetail", "photoDetail/{data}"),
}
