import SwiftUI
import Shared
struct EditText: View {
    @Binding var input: String
    var topLabel: String?
    var errorMessage: String?
    var shadowRadius: CGFloat = 10
    var isSecure: Bool = false
    var clearInputIcon: Bool = true
    var clearInputAction: () -> Void = {}

    private var clearButton: some View {
           Button(action: clearInputAction) {
               Image(systemName: "xmark")
                   .foregroundColor(.black)
                   .frame(width: 24, height: 24)
           }
           .padding(.trailing, 8)
       }
    
    var body: some View {
        VStack(alignment: .leading) {
            if let topLabel = topLabel {
                Text(topLabel).padding(.bottom, 4)
            }
            if isSecure {
                SecureField("Type here", text: $input)
                    .modifier(InputFieldModifier(isSecure: isSecure))
                    .autocapitalization(/*@START_MENU_TOKEN@*/.none/*@END_MENU_TOKEN@*/)
                    .overlay(clearInputIcon ? clearButton : nil, alignment: .trailing)
            } else {
                TextField("Type here", text: $input)
                    .modifier(InputFieldModifier(isSecure: isSecure))
                    .autocapitalization(/*@START_MENU_TOKEN@*/.none/*@END_MENU_TOKEN@*/)
                    .overlay(clearInputIcon ? clearButton : nil, alignment: .trailing)

            }
            if let errorMessage = errorMessage {
                HStack {
                    Image(systemName: "exclamationmark.circle.fill")
                        .foregroundColor(.red)
                    Text(errorMessage)
                        .foregroundColor(.red)
                }
            }
        }
    }
}

struct InputFieldModifier: ViewModifier {
    var isSecure: Bool
    
    func body(content: Content) -> some View {
        content
            .padding()
            .overlay(
                RoundedRectangle(cornerRadius: 10)
                    .stroke(isSecure ? Color.black : Color.blackPearl, lineWidth: 1)
            )
    }
    
}



struct PasswordTextField: View {
    @Binding var password: String
    var isValid: Bool
    var isPasswordVisible: Bool
    var clearInputAction: () -> Void
    var body: some View {
        EditText(input: $password, topLabel: "Wachtwoord", errorMessage: isValid ? nil : "Password is required" ,isSecure: !isPasswordVisible, clearInputIcon: true ,clearInputAction: clearInputAction)
    }
}


struct EmailTextField: View {
    @Binding var email: String
    var isValid: Bool
    var clearInputAction: () -> Void
    
    var body: some View {
        EditText(input: $email, topLabel: "E-mail", errorMessage: isValid ? nil : "Email is required", clearInputIcon: true ,clearInputAction: clearInputAction)
        
    }
}
