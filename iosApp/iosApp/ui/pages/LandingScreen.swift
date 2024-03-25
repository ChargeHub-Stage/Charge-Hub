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
    var body: some View {
        VStack(alignment: .leading, spacing: 0) {
            Text("Stroomlijn je dag met")
                .font(.system(size: 64))
                .fontWeight(.bold)
                .foregroundColor(.white)
            Text("Charge\nHub.")
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
    @StateViewModel var landingViewModel = LandingScreenViewModel()
    
    var body: some View {
        VStack() {
            PrimaryButton(
                text: "Aanmelden",
                textColor: .blackPearl,
                backgroundColor: .acid,
                action: {
                    landingViewModel.onAction(action: LandingScreenUiAction.ClickedLoginButtonAction())
                }
            )
            .padding(.bottom, 16)
            .frame(maxWidth: .infinity) .cornerRadius(8)
            
            PrimaryButton(
                text: "Registreren",
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


