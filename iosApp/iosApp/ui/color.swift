import SwiftUI

extension Color {
    static let blackPearl = UIColor(hex: "000000")
    static let sanJuan = UIColor(hex: "475161")
    static let shadowBlue = UIColor(hex: "7887A0")
    static let periWinkel = UIColor(hex: "CDD4E3")
    static let solitude = UIColor(hex: "E9EDF6")
    static let catSkillWhite = UIColor(hex: "F0F4FA")
    static let acid = UIColor(hex: "D7FC51")
    static let secondary = UIColor(hex: "235757")
    static let tertiary = UIColor(hex: "F0FFB6")
    static let rebel = UIColor(hex: "EB333D")
    static let lightGrey = UIColor(hex: "E3E3E3")
    static let warning = UIColor(hex: "475161")
    static let black = UIColor(hex: "000000")
    static let mist = UIColor(hex: "999999")
    static let mediumGray = UIColor(hex: "888888")
    static let smoke = UIColor(hex: "333333")
    static let white = UIColor(hex: "FFFFFF")
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

