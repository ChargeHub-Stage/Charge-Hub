import SwiftUI

struct RegistrationScreenView: View {
    @ObservedObject var navigation: NavigationController
    
    var body: some View {
        Button("Back") { navigation.navigateBack() }
        Text(/*@START_MENU_TOKEN@*/"Hello, World!"/*@END_MENU_TOKEN@*/)
    }
}
