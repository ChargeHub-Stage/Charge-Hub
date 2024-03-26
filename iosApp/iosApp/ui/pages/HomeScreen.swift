import SwiftUI
import Shared

struct HomeScreen: View {
    @ObservedObject var navigation: NavigationController
    var user = FirebaseRepository().getCurrentUserUid()
    
    var body: some View {
        Text("Logged in User uid: \(user)")
    }
}
