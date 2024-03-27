import SwiftUI
import Shared
import KMMViewModelSwiftUI

struct LandingScreenView: View {
    @ObservedObject var navigation: NavigationController
    
    var body: some View {
        ZStack {
            Image("landingbackground")
                .resizable()
                .scaledToFill()
                .edgesIgnoringSafeArea(.all)
            
            VStack(alignment: .leading) {
                HeaderComponent()
                Spacer()
                VStack {
                    Button("Login") {
                        navigation.navigateTo(.login)
                    }
                    .frame(maxWidth: .infinity, maxHeight: 48)
                    .padding(.horizontal, 18)
                    .foregroundStyle(Color.blackPearl)
                    .background(Color.acid)
                    .cornerRadius(10)
                    Button("Registreren") {
                        navigation.navigateTo(.register)
                    }
                    .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, maxHeight: 48)
                    .padding(.horizontal, 18)
                    .foregroundStyle(.white)
                    .background(Color.smoke)
                    .cornerRadius(10)
                }
                .padding(.bottom, 50)
            }
            .padding(.horizontal, 16)
            .padding(.top, 46)
        }
        .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, maxHeight: .infinity)
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
