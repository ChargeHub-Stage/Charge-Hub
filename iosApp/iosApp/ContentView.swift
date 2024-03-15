import SwiftUI
import Shared

struct ContentView: View {
    
    @State private var firstName: String = "Test"
    @State private var email: String = "test@test.com"
    @State private var password: String = "test@test.com"
    @State private var isPasswordVisible: Bool = false
    
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
            
            
            EditText(input: $firstName, topLabel: "First Name", errorMessage: "First name is required")
            EmailTextField(email: $email, isValid: true)
            PasswordTextField(password: $password, isValid: true, isPasswordVisible: $isPasswordVisible)
            
            
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

