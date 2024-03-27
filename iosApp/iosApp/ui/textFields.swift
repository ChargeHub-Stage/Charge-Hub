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

    let strings = StringsHelper().getResourceStrings()

    
    private var clearButton: some View {
           Button(action: clearInputAction) {
               Image(systemName: "xmark")
                   .foregroundColor(.black)
                   .frame(width: 24, height: 24)
           }
           .padding(.trailing, 8)
       }
    
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
                    .overlay(clearInputIcon ? clearButton : nil, alignment: .trailing)
            } else {
                TextField(typeHere, text: $input)
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
    
    let strings = StringsHelper().getResourceStrings()

    var body: some View {
        EditText(
            input: $password,
            topLabel: strings.get(id: SharedRes.strings().password, args: <#T##[Any]#>),
            errorMessage: isValid ? nil : strings.get(id: SharedRes.strings().password_is_required, args: []),
            isSecure: !isPasswordVisible,
            clearInputIcon: true,
            clearInputAction: clearInputAction)
    }
}


struct EmailTextField: View {
    @Binding var email: String
    var isValid: Bool
    var clearInputAction: () -> Void
    let strings = StringsHelper().getResourceStrings()

    var body: some View {
        EditText(
            input: $email,
            topLabel: strings.get(id: SharedRes.strings().email, args: []),
            errorMessage: isValid ? nil : strings.get(id: SharedRes.strings().email_is_required, args: []),
            clearInputIcon: true,
            clearInputAction: clearInputAction)
        
    }
}
