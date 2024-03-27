import SwiftUI
import Shared

struct EditText: View {
    let strings = StringsHelper().getResourceStrings()
    @Binding var input: String
    var topLabel: String?
    var errorMessage: String?
    var shadowRadius: CGFloat = 10
    var isSecure: Bool = false
    
    var body: some View {
        let typeHere = strings.get(id: SharedRes.strings().type_here, args: [])
        VStack(alignment: .leading) {
            if let topLabel = topLabel {
                Text(topLabel).padding(.bottom, 4)
            }
            if isSecure {
                SecureField(typeHere, text: $input)
                    .modifier(InputFieldModifier(isSecure: isSecure))
                    .autocapitalization(/*@START_MENU_TOKEN@*/.none/*@END_MENU_TOKEN@*/)
            } else {
                TextField(typeHere, text: $input)
                    .modifier(InputFieldModifier(isSecure: isSecure))
                    .autocapitalization(/*@START_MENU_TOKEN@*/.none/*@END_MENU_TOKEN@*/)
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
    let strings = StringsHelper().getResourceStrings()
    
    var body: some View {
        EditText(input: $password, topLabel:
                    strings.get(id: SharedRes.strings().password, args: []), isSecure: !isPasswordVisible)
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
                Text(strings.get(id: SharedRes.strings().password_is_required, args: []))
                    .foregroundColor(.red)
            }
        }
    }
}


struct EmailTextField: View {
    let strings = StringsHelper().getResourceStrings()
    @Binding var email: String
    var isValid: Bool
    
    var body: some View {
        EditText(input: $email, topLabel: strings.get(id: SharedRes.strings().email, args: []) , errorMessage: isValid ? nil : strings.get(id: SharedRes.strings().email_is_required, args: []))
    }
}
