import SwiftUI

struct Timeslot: View {
    var date: Int
    var month: String
    
    var body: some View {
        HStack(spacing: 0) {
            VStack(spacing: 0) {
                Text("\(date)")
                    .fontWeight(/*@START_MENU_TOKEN@*/.bold/*@END_MENU_TOKEN@*/)
                    .font(.system(size: 21))
                Text(month)
                    .font(.system(size: 14))
            }
            .frame(maxWidth: 56, maxHeight: .infinity)
            .background(Color.acid)
            VStack {
                HStack {
                    Text("Laadpaal Carport")
                        .fontWeight(.semibold)
                        .font(.system(size: 17))
                    Spacer()
                    Image(systemName: "chevron.right")
                        .fontWeight(.semibold)
                }
                HStack(spacing: 0) {
                    VStack(alignment: .leading) {
                        Text("JOUW TIJDSLOT")
                            .font(.system(size: 10))
                            .foregroundStyle(Color.mediumGray)
                        Text("11:00 - 14:00")
                    }
                    .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/)
                    VStack(alignment: .leading) {
                        Text("OPLAADTIJD")
                            .font(.system(size: 10))
                            .foregroundStyle(Color.mediumGray)
                        Text("3 uur")
                    }
                    .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/)
                }
                .frame(maxWidth: .infinity, maxHeight: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/)
            }
            .padding(.horizontal, 12)
            .padding(.vertical, 8)
        }
        .frame(maxWidth: 362, maxHeight: 82)
        .background(Color.veryLightGray)
        .clipShape(RoundedRectangle(cornerRadius: 4))
    }
}

#Preview {
    Timeslot(date: 29, month: "Sep")
}
