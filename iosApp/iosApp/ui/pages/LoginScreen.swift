import SwiftUI
import Shared
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync

struct LoginScreenView: View {
    @Environment(\.presentationMode) var presentationMode
    @StateViewModel var viewModel = LoginScreenViewModel(firebaseRepo: FirebaseRepository())
    @State var user = FirebaseRepository().getCurrentUserUid()
    @State var loggedIn = false

    var body: some View {
        VStack(spacing: 0) {
            Text("Welkom Terug!")
                .font(.system(size: 28))
                .fontWeight(.bold)
                .padding(.bottom, 5)
            Text("Fijn om je terug te zien")
            Spacer().frame(height: 60)
            VStack(spacing: 0) {
//                EmailTextField(email: viewModel, isValid:
                TextField("Email hier", text: Binding(
                    get: { viewModel.getEmail() },
                    set: { value in viewModel.setEmail(email: value) }
                )).autocapitalization(/*@START_MENU_TOKEN@*/.none/*@END_MENU_TOKEN@*/)
                Spacer().frame(height: 14)
                VStack(spacing: 0) {
//                    PasswordTextField(
//                        password: viewModel.state.password,
//                        isValid: true,
//                        isPasswordVisible: viewModel.state.passwordVisibility
//                    ).padding(.bottom, 4)
                    SecureField("Wachtwoord hier", text: Binding(
                        get: { viewModel.getPassword() },
                        set: { value in viewModel.setPassword(password: value) }
                    )).autocapitalization(/*@START_MENU_TOKEN@*/.none/*@END_MENU_TOKEN@*/)
                    HStack {
                        Text("Wachtwoord vergeten?").font(.system(size: 12)).fontWeight(.regular)
                        Spacer()
                    }
                }
                Spacer().frame(height: 30)
                Button(action: {
                    viewModel.onAction(action: LoginScreenUiAction.OnClickedLoginButtonAction())
                    loggedIn = !loggedIn
                }) {
                    Text("Aanmelden")
                        .font(.system(size: 16))
                        .fontWeight(.bold)
                        .foregroundStyle(.black)
                }
                .frame(maxWidth: .infinity, maxHeight: 48)
                .background(Color.acid)
                .cornerRadius(10)
                
                if(loggedIn) {
                    Text(user)
                }
            }
            .frame(maxWidth: .infinity)
            .padding(.horizontal, 18)
            Spacer()
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .padding(.top, 70)
    }
}
