import SwiftUI
import Shared

struct NextButton: View {
    var state: RegisterScreenUiState
    let onAction: (RegisterScreenUiAction) -> Void
    let strings = StringsHelper().getResourceStrings()

    
    var body: some View {
        
        PrimaryButton(text: strings.get(id: SharedRes.strings().next, args: []),
                      enabled: Binding(get: { /* calculateButtonEnableState(for: state) */ true },
                                       set: { _ in }),
                      action: {
            onAction(.OnNextClickedAction())
        })
    }
}
private func calculateButtonEnableState(for state: RegisterScreenUiState) -> Bool {
    guard let currentRegisterState = state.currentRegisterState else {
        return false
    }
    
    switch currentRegisterState {
    case .email:
        return state.isEmailStepValid()
    case .profile:
        return state.isProfileStepValid()
    case .carConnect:
        return state.isVinStepValid()
    case .info:
        return true
    default:
        return false
    }
}


struct SkipTextButton: View {
    let onAction: () -> Void
    let strings = StringsHelper().getResourceStrings()

    var body: some View {
        Button(action: onAction) {
            Text(strings.get(id: SharedRes.strings().skip, args: []))
                .underline()
                .foregroundColor(.black)
        }
    }
}


struct CircularProfilePicture: View {
    let image: Image = Image(systemName: "person.circle.fill")
    let borderColor: Color
    let borderWidth: CGFloat
    
    var body: some View {
        image
            .resizable()
            .aspectRatio(contentMode: .fill)
            .frame(width: 100, height: 110)
            .clipShape(Circle())
            .overlay(
                Circle().stroke(borderColor, lineWidth: borderWidth)
            )
    }
}

struct PrivacySwitch: View {
    @Binding var isChecked: Bool
    let strings = StringsHelper().getResourceStrings()

    var body: some View {
        HStack() {
            Toggle("", isOn: $isChecked)
                .toggleStyle(SwitchToggleStyle(tint: isChecked ? Color.acid : Color.lightGrey))
                .labelsHidden()
            Button(action: {
                
            }) {
                Link(strings.get(id: SharedRes.strings().privacy, args: []), destination: URL(string: "https://policies.google.com/privacy")!)
                    .font(.body)
                    .foregroundColor(.blue)
            }
            .buttonStyle(PlainButtonStyle())
            Spacer()
        }
    }
}

