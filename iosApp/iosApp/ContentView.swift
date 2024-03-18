import SwiftUI
import Shared

struct ContentView: View {
    
    var body: some View {
        VStack {
            Text("Hello, World!")
                .foregroundColor(.acid)
            
            TextOnlyButton(
                text: "Text Only"){
                    print("Text Only Button tapped")
                }
            
            PrimaryButton(
                text: "App Button",
                enabled: true,
                textColor: .blackPearl,
                trailingIcon: Image(systemName: "star.fill"),
                leadingIcon: Image(systemName: "star.fill")) {
                    print("App Button tapped")
                }
            
            IconButton(icon: Image(systemName: "star.fill")) {
                print("Icon Button tapped")
            }
        }
        .background(Image("images/landingbackground").resizable().aspectRatio(contentMode: .fill))

    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
