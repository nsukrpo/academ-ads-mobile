package nsu.krpo.academads.ui.auth

sealed class AuthScreenRoutes {
    class ToRegistration: AuthScreenRoutes()

    class ToMainScreen: AuthScreenRoutes()
}