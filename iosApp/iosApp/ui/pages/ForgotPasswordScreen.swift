//
//  ForgotPasswordScreen.swift
//  iosApp
//
//  Created by Sebastian Van Grieken on 26/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared
import KMMViewModelSwiftUI

struct ForgotPasswordScreen: View {
    @ObservedObject var navigation: NavigationController
    @StateViewModel var viewModel = ForgotPasswordViewModel(firebaseRepo: FirebaseRepository())
    
    var body: some View {
        HStack {
            Button(action: {
                navigation.navigateBack()
            }) {
                HStack(spacing: 0) {
                    Image(systemName: "chevron.left")
                        .foregroundStyle(.black)
                        .fontWeight(.bold)
                    Text("Terug")
                        .foregroundStyle(.black)
                }
            }
            .padding(.leading, 5)
            Spacer()
            Text("Wachtwoord Vergeten")
                .font(.system(size: 17))
                .fontWeight(.semibold)
            Spacer()
        }
        .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, alignment: .center)
        VStack {
            Text("Geef je e-mail adres in en volg de instructies om je wachtwoord te resetten").frame(alignment: .center).padding(.bottom, 30)
            EmailTextField(
                email: Binding(
                    get: { viewModel.getState().email },
                    set: { value in viewModel.setEmail(email: value) }
                ),
                isValid: true
            )
            Button(action: {
                viewModel.onAction(action: ForgotPasswordUiAction.OnClickedSendEmailAction())
                navigation.navigateTo(.login)
            }) {
                Text("Stuur mail")
                    .font(.system(size: 16))
                    .fontWeight(.bold)
                    .foregroundStyle(.black)
            }
            .frame(maxWidth: .infinity, maxHeight: 48)
            .background(Color.acid)
            .cornerRadius(10)
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .padding(.top, 70)
        .padding(.horizontal, 16)
    }
}
