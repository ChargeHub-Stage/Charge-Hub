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
                Buttons(navigation: navigation)
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
            Text(strings.get(id: SharedRes.strings().charge, args: []))
                .font(.system(size: 64))
                .fontWeight(.bold)
                .foregroundColor(.acid)
            Image("logo")
                .resizable()
                .frame(width: 74, height: 74)
        }
    }
}

struct Buttons: View {
    var navigation: NavigationController
    let strings = StringsHelper().getResourceStrings()
    var body: some View {
        VStack {
            Button(strings.get(id: SharedRes.strings().login, args: [])) {
                navigation.navigateTo(.login)
            }
            .frame(maxWidth: .infinity, maxHeight: 48)
            .padding(.horizontal, 18)
            .foregroundStyle(Color.blackPearl)
            .background(Color.acid)
            .cornerRadius(10)
            Spacer().frame(height: 16)
            Button(strings.get(id: SharedRes.strings().register_, args: [])) {
                navigation.navigateTo(.register)
            }
            .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, maxHeight: 48)
            .padding(.horizontal, 18)
            .foregroundStyle(.white)
            .background(Color.smoke)
            .cornerRadius(10)
            .padding(.bottom, 16)
        }
        .padding(.bottom, 50)
    }
}
