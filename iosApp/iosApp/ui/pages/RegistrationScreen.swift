import SwiftUI
import Shared
import KMMViewModelSwiftUI
import KMPNativeCoroutinesAsync

struct RegistrationScreen: View {
    @ObservedObject var navigation: NavigationController
    @StateViewModel var viewModel = RegisterScreenViewModel()
    
    var body: some View {
        let state = viewModel.getState()
        let currentRegisterState = state.currentRegisterState
        
        VStack() {
            if (currentRegisterState == CurrentRegisterState.email) {
                EmailRegisterStep() { action in
                    viewModel.onAction(action: action)
                }.padding(.horizontal, 16)
            } else if (currentRegisterState == CurrentRegisterState.profile) {
                ProfileCompletionStep(state: state) { action in
                    viewModel.onAction(action: action)
                }.padding(.horizontal, 16)
            } else {
                Text("")
            }
        }
    }
}

struct EmailRegisterStep: View {
    @State private var email: String = ""
    @State private var isEmailValid: Bool = false
    @StateViewModel var viewModel = RegisterScreenViewModel()
    
    var onAction: (RegisterScreenUiAction) -> Void
    
    let strings = StringsHelper().getResourceStrings()

    
    var body: some View {
        VStack() {
            EmailTextField(email:
                            Binding(
                                get: { viewModel.getState().email },
                                set: { value in onAction(RegisterScreenUiAction.OnEmailChangedAction(email: value)) }
                            ),
                           isValid: viewModel.getState().isEmailValid,
                           clearInputAction: { onAction(RegisterScreenUiAction.OnEmailChangedAction(email: "")) }
            )
            Text(strings.get(id: SharedRes.strings().privacy, args: []))
                .alignmentGuide(.leading) { _ in 0 }
                .frame(maxWidth: .infinity, alignment: .leading)
                .padding(.leading, 16)
            NextButton(state: viewModel.getState(), onAction: onAction)
        }
    }
}

struct ProfileCompletionStep: View {
    var state: RegisterScreenUiState
    let onAction: (RegisterScreenUiAction) -> Void
    let strings = StringsHelper().getResourceStrings()

    var body: some View {
        VStack {
            CircularProfilePicture(borderColor: .white, borderWidth: 4)
                .padding(.bottom, 14)
            
            EditText(
                input: Binding(
                    get: { state.firstName },
                    set: { value in onAction(RegisterScreenUiAction.OnFirstNameChangedAction(firstName: value)) }
                ),
                topLabel: strings.get(id: SharedRes.strings().firstname, args: []),
                errorMessage: state.isFirstNameValid ? nil : strings.get(id: SharedRes.strings().first_name_validation_error, args: []),
                clearInputAction: { onAction(RegisterScreenUiAction.OnFirstNameChangedAction(firstName: "")) }
            )
            .padding(.bottom, 12)

            EditText(
                input: Binding(
                    get: { state.lastName },
                    set: { value in onAction(RegisterScreenUiAction.OnLastNameChangedAction(lastName: value)) }
                ),
                topLabel: strings.get(id: SharedRes.strings().lastname, args: []),
                errorMessage: state.isLastNameValid ? nil : strings.get(id: SharedRes.strings().last_name_validation_error, args: []),
                clearInputAction: { onAction(RegisterScreenUiAction.OnLastNameChangedAction(lastName: "")) }
            )
            .padding(.bottom, 12)

            PasswordTextField(
                password: Binding(
                    get: { state.password },
                    set: { value in onAction(RegisterScreenUiAction.OnPasswordChangedAction(password: value)) }
                ),
                isValid: state.isPasswordValid,
                isPasswordVisible: false,
                clearInputAction: { onAction(RegisterScreenUiAction.OnPasswordChangedAction(password: "")) }
            )
            .padding(.bottom, 17)

            PrivacySwitch(
                isChecked: Binding(
                    get: { state.isPrivacyChecked },
                    set: { value in onAction(RegisterScreenUiAction.OnPrivacyCheckedChangedAction()) }
                )
            )
            .padding(.bottom, 20)
            
            NextButton(state: state, onAction: onAction)
        }
        .padding()
        .background(Color(UIColor.systemBackground))
    }
}
