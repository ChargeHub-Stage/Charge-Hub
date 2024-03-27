import SwiftUI
import Shared
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync

struct LoginScreenView: View {
    @ObservedObject var navigation: NavigationController
    @Environment(\.presentationMode) var presentationMode
    @StateViewModel var viewModel = LoginScreenViewModel(firebaseRepo: FirebaseRepository())

    var body: some View {
        HStack {
            Button(action: {
                navigation.navigateBack()
            }) {
                HStack(spacing: 0) {
                    Image(systemName: "chevron.left")
                        .foregroundStyle(.black)
                        .fontWeight(.bold)
                    Text("Terug")
                        .foregroundStyle(.black)
                }
            }
            .padding(.leading, 5)
            Spacer()
            Text("Aanmelden")
                .font(.system(size: 17))
                .fontWeight(.semibold)
            Spacer()
        }
        .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, alignment: .center)
        VStack(spacing: 0) {
            Text("Welkom Terug!")
                .font(.system(size: 28))
                .fontWeight(.bold)
                .padding(.bottom, 5)
            Text("Fijn om je terug te zien")
            Spacer().frame(height: 60)
            VStack(spacing: 0) {
                EmailTextField(
                    email: Binding(
                        get: { viewModel.getState().email },
                        set: { value in viewModel.setEmail(email: value) }
                    ),
                    isValid: true
                )
                Spacer().frame(height: 14)
                VStack(spacing: 0) {
                    PasswordTextField(
                        password: Binding(
                            get: { viewModel.getState().password },
                            set: { value in viewModel.setPassword(password: value)}
                        ),
                        isValid: true,
                        isPasswordVisible: Binding(
                            get: { viewModel.getState().passwordVisibility },
                            set: { value in viewModel.setPasswordVisibility(visibility: value) }
                        )
                    ).padding(.bottom, 4)
                    HStack {
                        Text("Wachtwoord vergeten?").font(.system(size: 12)).fontWeight(.regular)
                        Spacer()
                    }
                }
                Spacer().frame(height: 30)
                Button(action: {
                    viewModel.onAction(action: LoginScreenUiAction.OnClickedLoginButtonAction())
                    navigation.navigateTo(.home)
                }) {
                    Text("Aanmelden")
                        .font(.system(size: 16))
                        .fontWeight(.bold)
                        .foregroundStyle(.black)
                }
                .frame(maxWidth: .infinity, maxHeight: 48)
                .background(Color.acid)
                .cornerRadius(10)
            }
            .frame(maxWidth: .infinity)
            .padding(.horizontal, 18)
            Spacer()
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .padding(.top, 70)
    }
}
