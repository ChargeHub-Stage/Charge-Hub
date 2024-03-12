import SwiftUI
import Shared

struct ContentViewNew: View {
        // Create helper instance
    let greet = GreetingHelper().greet()

    var body: some View {
        Text(greet)
    }
}


@main
struct iOSApp: App {

	init() {
	   HelperKt.doInitKoin()
	}

	var body: some Scene {
		WindowGroup {
			ContentViewNew()
		}
	}
}
