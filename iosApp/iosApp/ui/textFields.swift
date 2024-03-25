import SwiftUI

struct EditText: View {
    @Binding var input: String
    var topLabel: String?
    var errorMessage: String?
    var shadowRadius: CGFloat = 10
    var isSecure: Bool = false
    
    var body: some View {
        VStack(alignment: .leading) {
            if let topLabel = topLabel {
                Text(topLabel)
            }
            if isSecure {
                SecureField("Type here", text: $input)
                    .modifier(InputFieldModifier(isSecure: isSecure))
            } else {
                TextField("Type here", text: $input)
                    .modifier(InputFieldModifier(isSecure: isSecure))
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
    @Binding var isPasswordVisible: Bool
    
    var body: some View {
        EditText(input: $password, topLabel: "Password", isSecure: !isPasswordVisible)
            .overlay(
                Button(action: {
                    isPasswordVisible.toggle()
                }) {
                    Image(systemName: isPasswordVisible ? "eye.slash.fill" : "eye.fill")
                        .foregroundColor(.blackPearl)
                        .padding(EdgeInsets(top: 30, leading : 0, bottom: 0, trailing: 8))
                }
                    .frame(maxHeight: .infinity, alignment: .center),
                alignment: .trailing
            )
        if !isValid {
            HStack {
                Image(systemName: "exclamationmark.circle.fill")
                    .foregroundColor(.red)
                Text("Password is required")
                    .foregroundColor(.red)
            }
        }
    }
}


struct EmailTextField: View {
    @Binding var email: String
    var isValid: Bool
    
    var body: some View {
        EditText(input: $email, topLabel: "Email", errorMessage: isValid ? nil : "Email is required")
    }
}
