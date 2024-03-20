import SwiftUI
import Shared
import Firebase

@available(iOS 16.0, *)
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
