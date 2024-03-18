import SwiftUI

struct TextOnlyButton: View {
    var text: String
    var enabled: Bool
    var textColor: Color
    var action: () -> Void
    
    init(
        text: String,
        enabled: Bool = true,
        textColor: Color = .black,
        action: @escaping () -> Void)
    {
            
        self.text = text
        self.enabled = enabled
        self.textColor = textColor
        self.action = action
    }
    
    var body: some View {
        Button(action: action) {
            Text(text)
                .foregroundColor(textColor)
                .padding()
                .background(Color.clear)
                .cornerRadius(5)
                .overlay(
                    RoundedRectangle(cornerRadius: 5)
                        .stroke(textColor, lineWidth: 1)
                )
        }
        .disabled(!enabled)
    }
}

struct AppButton: View {
    var text: String
    var enabled: Bool
    var textColor: Color
    var action: () -> Void
    
    init(
        text: String,
        enabled: Bool = true,
        textColor: Color = .black,
        action: @escaping () -> Void) 
    {
        self.text = text
        self.enabled = enabled
        self.textColor = textColor
        self.action = action
    }
    
    var body: some View {
        Button(action: action) {
            Text(text)
                .foregroundColor(textColor)
                .padding()
                .background(enabled ? Color.blue : Color.gray)
                .cornerRadius(5)
        }
        .disabled(!enabled)
    }
}

struct IconButton: View {
    var icon: Image
    var enabled: Bool
    var action: () -> Void
    
    init(
        icon: Image,
        enabled: Bool = true,
        action: @escaping () -> Void)
    {
        self.icon = icon
        self.enabled = enabled
        self.action = action
    }
    
    var body: some View {
        Button(action: action) {
            icon
                .padding()
                .background(Color.clear)
                .cornerRadius(5)
                .overlay(
                    RoundedRectangle(cornerRadius: 5)
                        .stroke(Color.black, lineWidth: 1)
                )
        }
        .disabled(!enabled)
    }
}
