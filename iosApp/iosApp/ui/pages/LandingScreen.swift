import SwiftUI
import Shared
import KMMViewModelSwiftUI

@available(iOS 16.0, *)
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
            Image("logo_acid")
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
