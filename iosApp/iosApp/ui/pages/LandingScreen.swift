import SwiftUI

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
            Image("logo_acid")
                .resizable()
                .frame(width: 74, height: 74)
            
        }
    }
}

struct ButtonsComponent: View {
    var body: some View {
        VStack() {
            PrimaryButton(
                text: "Aanmelden",
                textColor: .blackPearl,
                backgroundColor: .acid,
                action: {}
            )
            .padding(.bottom, 16)
            .frame(maxWidth: .infinity) .cornerRadius(8)
            
            PrimaryButton(
                text: "Registreren",
                textColor: .white,
                backgroundColor: .smoke,
                action: {}
            )
            .padding(.bottom, 35)
    
            .frame(maxWidth: .infinity) .cornerRadius(8)
        }
    }
}


