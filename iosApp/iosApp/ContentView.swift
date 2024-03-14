import SwiftUI
import Shared

struct ContentView: View {
    var body: some View {
        VStack {
            Text("Hello, World!")
                .foregroundColor(.acid)

            TextOnlyButton(text: "Text Only Button") {
                print("Text Only Button tapped")
            }

            AppButton(text: "App Button") {
                print("App Button tapped")
            }

            IconButton(icon: Image(systemName: "star.fill")) {
                print("Icon Button tapped")
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
