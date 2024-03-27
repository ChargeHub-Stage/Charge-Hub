import SwiftUI

extension Color {
    static let blackPearl = Color(UIColor(hex: "000000"))
    static let sanJuan = Color(UIColor(hex: "475161"))
    static let shadowBlue = Color(UIColor(hex: "7887A0"))
    static let periWinkel = Color(UIColor(hex: "CDD4E3"))
    static let solitude = Color(UIColor(hex: "E9EDF6"))
    static let catSkillWhite = Color(UIColor(hex: "F0F4FA"))
    static let acid = Color(UIColor(hex: "CCFF00"))
    static let secondary = Color(UIColor(hex: "235757"))
    static let tertiary = Color(UIColor(hex: "F0FFB6"))
    static let rebel = Color(UIColor(hex: "EB333D"))
    static let lightGrey = Color(UIColor(hex: "E3E3E3"))
    static let warning = Color(UIColor(hex: "475161"))
    static let black = Color(UIColor(hex: "000000"))
    static let mist = Color(UIColor(hex: "999999"))
    static let mediumGray = Color(UIColor(hex: "888888"))
    static let smoke = Color(UIColor(hex: "333333"))
    static let white = Color(UIColor(hex: "FFFFFF"))
    static let veryLightGray = Color(UIColor(hex: "F4F4F4"))
}

extension UIColor {
    convenience init(hex: String, alpha: CGFloat = 1.0) {
        var hexValue = hex.trimmingCharacters(in: CharacterSet.whitespacesAndNewlines).uppercased()

        if hexValue.hasPrefix("#") {
            hexValue.remove(at: hexValue.startIndex)
        }

        var rgbValue: UInt64 = 0
        Scanner(string: hexValue).scanHexInt64(&rgbValue)

        let red = CGFloat((rgbValue & 0xFF0000) >> 16) / 255.0
        let green = CGFloat((rgbValue & 0x00FF00) >> 8) / 255.0
        let blue = CGFloat(rgbValue & 0x0000FF) / 255.0

        self.init(red: red, green: green, blue: blue, alpha: alpha)
    }
}

