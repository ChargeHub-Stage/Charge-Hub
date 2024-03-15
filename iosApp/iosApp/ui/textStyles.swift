import UIKit
import SwiftUI
import Shared

struct TextStyle {
    let fontSize: CGFloat
    let fontWeight: UIFont.Weight
    let lineHeight: CGFloat
    let color: Color?

    init(fontSize: CGFloat, fontWeight: UIFont.Weight, lineHeight: CGFloat, color: Color? = nil) {
        self.fontSize = fontSize
        self.fontWeight = fontWeight
        self.lineHeight = lineHeight
        self.color = color
    }
}


struct TextStyles {
    static let topLabelText = TextStyle(
        fontSize: 14,
        fontWeight: .semibold,
        lineHeight: 16.71
    )

    static let hint = TextStyle(
        fontSize: 16,
        fontWeight: .regular,
        lineHeight: 19.09
    )

    static let error = TextStyle(
        fontSize: 12,
        fontWeight: .regular,
        lineHeight: 14.31,
        color: .rebel
    )
}

