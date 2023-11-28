package nsu.krpo.academads.ui.splash_screen

sealed interface SplashRoutes {

    object ToLogin : SplashRoutes

    object ToMainScreen: SplashRoutes
}