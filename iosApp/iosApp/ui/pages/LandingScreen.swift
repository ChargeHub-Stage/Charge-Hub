import SwiftUI
import Shared
import KMMViewModelSwiftUI

struct LandingScreenView: View {
    var body: some View {
        NavigationStack() {
            ZStack {
                Image("landingbackground")
                    .resizable()
                    .scaledToFill()
                    .edgesIgnoringSafeArea(.all)
                
                VStack(alignment: .leading) {
                    HeaderComponent()
                    Spacer()
                    VStack {
                        Button(action: {}, label: {
                            NavigationLink(destination: LoginScreenView()) {
                                Text("Login")
                            }
                        })
                        .frame(maxWidth: .infinity, maxHeight: 48)
                        .padding(.horizontal, 18)
                        .foregroundStyle(Color.blackPearl)
                        .background(Color.acid)
                        .cornerRadius(10)
                        Button(action: {}, label: {
                            NavigationLink(destination: RegistrationScreenView()) {
                                Text("Registreren")
                            }
                        })
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
        .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, maxHeight: .infinity)
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

//Button("Aanmelden") {
//    path.append("Login")
//}
//.frame(maxWidth: .infinity, maxHeight: 48)
//.padding(.horizontal, 18)
//.foregroundStyle(Color.blackPearl)
//.background(Color.acid)
//.cornerRadius(10)
//Button("Registreren") {
//    path.append("Register")
//}
//.frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, maxHeight: 48)
//.padding(.horizontal, 18)
//.foregroundStyle(.white)
//.background(Color.smoke)
//.cornerRadius(10)

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

//    .navigationDestination(for: String.self) { value in
//        switch value {
//        case "Login":
//            LoginScreenView()
//        case "Register":
//            RegistrationScreenView()
//        default:
//            LandingScreenView()
//        }
//    }
