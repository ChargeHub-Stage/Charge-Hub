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
                        .stroke(textColor, lineWidth: 0)
                )
        }
        .disabled(!enabled)
    }
}

struct PrimaryButton: View {
    var text: String
    var enabled: Bool
    var textColor: Color
    var backgroundColor: Color
    var trailingIcon: Image? = nil
    var leadingIcon: Image? = nil
    var action: () -> Void

    init(
        text: String,
        enabled: Bool = true,
        textColor: Color = .black,
        backgroundColor: Color = .acid,
        trailingIcon: Image? = nil,
        leadingIcon: Image? = nil,
        action: @escaping () -> Void
    )
    {
        self.text = text
        self.enabled = enabled
        self.textColor = textColor
        self.backgroundColor = backgroundColor
        self.action = action
        self.trailingIcon = trailingIcon
        self.leadingIcon = leadingIcon
    }
    
    var body: some View {
        Button(action: action) {
            ZStack {
                if let leadingIcon = leadingIcon {
                    leadingIcon
                        .resizable()
                        .foregroundColor(textColor)
                        .frame(width: 20, height: 20)
                        .padding(.leading)
                }
                Text(text)
                    .foregroundColor(textColor)
                    .frame(maxWidth: .infinity, alignment: .center)
                if let trailingIcon = trailingIcon {
                    trailingIcon
                        .resizable()
                        .foregroundColor(textColor)
                        .frame(width: 20, height: 20)
                        .padding(.trailing)
                }
            }
            .padding()
            .background(enabled ? backgroundColor : Color.gray)
            .cornerRadius(5)
        }
        .disabled(!enabled)
        .frame(maxWidth: .infinity)
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
