import SwiftUI
import Shared
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync

struct LoginScreenView: View {
    @ObservedObject var navigation: NavigationController
    @Environment(\.presentationMode) var presentationMode
    @StateViewModel var viewModel = LoginScreenViewModel(firebaseRepo: FirebaseRepository())
    let strings = StringsHelper().getResourceStrings()

    var body: some View {
        HStack {
            Button(action: {
                navigation.navigateBack()
            }) {
                HStack(spacing: 0) {
                    Image(systemName: "chevron.left")
                        .foregroundStyle(.black)
                        .fontWeight(.bold)
                    Text(strings.get(id: SharedRes.strings().back_button, args: []))
                        .foregroundStyle(.black)
                }
            }
            .padding(.leading, 5)
            Spacer()
            Text(strings.get(id: SharedRes.strings().login, args: []))
                .font(.system(size: 17))
                .fontWeight(.semibold)
            Spacer()
        }
        .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, alignment: .center)
        VStack(spacing: 0) {
            Text(strings.get(id: SharedRes.strings().welcome_back, args: []))
                .font(.system(size: 28))
                .fontWeight(.bold)
                .padding(.bottom, 5)
            Text(strings.get(id: SharedRes.strings().login_welcome_text, args: []))
            Spacer().frame(height: 60)
            VStack(spacing: 0) {
                EmailTextField(
                    email: Binding(
                        get: { viewModel.getState().email },
                        set: { value in viewModel.setEmail(email: value) }
                    ),
                    isValid: true,
                    clearInputAction: { viewModel.onAction(action: LoginScreenUiAction.OnEmailChangedAction(email: "")) }
                )
                Spacer().frame(height: 14)
                VStack(spacing: 0) {
                    PasswordTextField(
                        password: Binding(
                            get: { viewModel.getState().password },
                            set: { value in viewModel.setPassword(password: value)}
                        ),
                        isValid: true,
                        isPasswordVisible: true,
                        clearInputAction: { viewModel.onAction(action: LoginScreenUiAction.OnPasswordChangedAction(password: "")) }
                    ).padding(.bottom, 4)
                    HStack {
                        Text(strings.get(id: SharedRes.strings().forgot_password, args: [])).font(.system(size: 12)).fontWeight(.regular)
                        Spacer()
                    }
                }
                Spacer().frame(height: 30)
                Button(action: {
                    viewModel.onAction(action: LoginScreenUiAction.OnClickedLoginButtonAction())
                    navigation.navigateTo(.home)
                }) {
                    Text(strings.get(id: SharedRes.strings().login, args: []))
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
