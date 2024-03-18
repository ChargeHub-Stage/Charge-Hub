import SwiftUI
import Shared
import Firebase

@main
struct iOSApp: App {

	init() {
        FirebaseApp.configure()
        HelperKt.doInitKoinIos()
	}

	var body: some Scene {
		WindowGroup {
			LandingScreenView()
		}
	}
}
