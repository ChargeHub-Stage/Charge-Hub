//
//  Navigation.swift
//  iosApp
//
//  Created by Sebastian Van Grieken on 26/03/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

enum AppView: Int {
    case landing
    case login
    case register
    case home
    case forgotPassword
}

class NavigationController: ObservableObject {
//    @Published var currentView: AppView = .landing
    @Published var navigationStack: [AppView] = [.landing]
    
    func navigateTo(_ view: AppView) {
        navigationStack.append(view)
    }
    
    func navigateBack() {
        if navigationStack.count > 1 {
            navigationStack.removeLast()
        }
    }
}
