import SwiftUI
import Shared
import KMMViewModelSwiftUI



struct LandingScreenView: View {
    var body: some View {
        ZStack {
            Image("landingbackground")
                .resizable()
                .scaledToFill()
                .edgesIgnoringSafeArea(.all)
            
            VStack(alignment: .leading) {
                HeaderComponent()
                Spacer()
                ButtonsComponent()
            }.padding(.horizontal, 16)
                .padding(.top, 46)
        }
    }
}

struct HeaderComponent: View {
    let strings = StringsHelper().getResourceStrings()
    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            Text(strings.get(id: SharedRes.strings().streamline_your_day_with, args: []))
                .font(.system(size: 64))
                .fontWeight(.bold)
                .foregroundColor(.white)
            Text(strings.get(id: SharedRes.strings().charge, args:[]))
                .font(.system(size: 64))
                .fontWeight(.bold)
                .foregroundColor(.acid)
            Image("logo")
                .resizable()
                .frame(width: 74, height: 74)
        }
    }
}

struct ButtonsComponent: View {
    let strings = StringsHelper().getResourceStrings()

    @StateViewModel var landingViewModel = LandingScreenViewModel()
    
    var body: some View {
        VStack() {
            PrimaryButton(
                text: strings.get(id: SharedRes.strings().login, args: []),
                textColor: .blackPearl,
                backgroundColor: .acid,
                action: {
                    landingViewModel.onAction(action: LandingScreenUiAction.ClickedLoginButtonAction())
                }
            )
            .padding(.bottom, 16)
            .frame(maxWidth: .infinity) .cornerRadius(8)
            
            PrimaryButton(
                text: strings.get(id: SharedRes.strings().register_, args: []),
                textColor: .white,
                backgroundColor: .smoke,
                action: {
                    landingViewModel.onAction(action: LandingScreenUiAction.ClickedRegisterButtonAction())
                }
            )
            .padding(.bottom, 35)
            .frame(maxWidth: .infinity) .cornerRadius(8)
        }
    }
}


