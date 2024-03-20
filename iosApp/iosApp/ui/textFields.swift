import SwiftUI

struct EditText: View {
    @Binding var input: String
    var topLabel: String?
    var errorMessage: String?
    var shadowRadius: CGFloat = 10
    var isSecure: Bool = false
    var body: some View {
        
        VStack(alignment: .leading, spacing: 0) {
            if let topLabel = topLabel {
                Text(topLabel).padding(.bottom, 4)
            }
            if (!isSecure) {
                TextField("Type here", text: $input)
                    .padding()
                    .overlay(
                        RoundedRectangle(cornerRadius: 10)
                            .stroke(Color.blackPearl, lineWidth: 1)
                    )
                if let errorMessage = errorMessage {
                    HStack {
                        Image(systemName: "exclamationmark.circle.fill")
                            .foregroundColor(.red)
                        Text(errorMessage)
                            .foregroundColor(.red)
                    }
                }
            } else {
                SecureField("Type here", text: $input)
                    .padding()
                
                    .overlay(
                        RoundedRectangle(cornerRadius: 10)
                            .stroke(Color.black, lineWidth: 1)
                    )
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
}



struct PasswordTextField: View {
    @Binding var password: String
    var isValid: Bool
    @Binding var isPasswordVisible: Bool
    
    var body: some View {
        EditText(input: $password, topLabel: "Wachtwoord", isSecure: !isPasswordVisible)
            .overlay(
                Button(action: {
                    isPasswordVisible.toggle()
                }) {
                    Image(systemName: isPasswordVisible ? "eye.slash.fill" : "eye.fill")
                        .foregroundColor(.blackPearl)
                        .padding(.top, 30)
                    
                }
                    .padding(.trailing, 8)
                    .frame(maxHeight: .infinity, alignment: .center),
                alignment: .trailing
            )
            .padding(.trailing, 8)
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
        EditText(input: $email, topLabel: "E-mail", errorMessage: isValid ? nil : "Email is required")
    }
}
