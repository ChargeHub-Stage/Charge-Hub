import SwiftUI
import Shared

@main
struct iOSApp: App {

	init() {
	   HelperKt.doInitKoinIos()
	}

	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
