import SwiftUI
import Shared

struct ContentView: View {
    @StateObject var navController = NavigationController()
    
    var body: some View {
        switch navController.navigationStack.last {
        case .landing:
            LandingScreenView(navigation: navController)
        case .login:
            LoginScreenView(navigation: navController)
        case .register:
            RegistrationScreenView(navigation: navController)
        case .home:
            HomeScreen(navigation: navController)
        case .none:
            EmptyView()
        }
    }
}
