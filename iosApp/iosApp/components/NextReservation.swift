import SwiftUI

struct NextReservation: View {
    var place: String
    var date: String
    var startDate: String
    var start: String
    var end: String
    
    var body: some View {
        ZStack {
            Image("rings")
                .resizable()
                .frame(width: 300, height: 400)
                .offset(x: 50, y: 90)
                .rotationEffect(.degrees(0))
            VStack {
                HStack {
                    Image("Laadpaal_icon").foregroundStyle(.white)
                    VStack(alignment: .leading) {
                        Text(place)
                            .foregroundStyle(Color.acid)
                            .fontWeight(.bold)
                            .font(.system(size: 17))
                        Text(date)
                            .foregroundStyle(.white)
                            .fontWeight(.medium)
                            .font(.system(size: 13))
                    }
                    Spacer()
                    Button(action: {}) {
                        Image(systemName: "chevron.right")
                            .foregroundStyle(.white)
                            .fontWeight(.semibold)
                    }
                }
                .padding(.horizontal, 12)
                .padding(.bottom, 8)
                HStack {
                    VStack(alignment: .leading, spacing: 0) {
                        Text("JOUW TIJDSLOT")
                            .foregroundStyle(Color.mist)
                            .font(.system(size: 10))
                            .fontWeight(.medium)
                        Text("\(start) - \(end)").foregroundStyle(.white)
                    }
                    Spacer()
                    VStack(alignment: .leading) {
                        Text("START")
                            .foregroundStyle(Color.mist)
                            .font(.system(size: 10))
                            .fontWeight(.medium)
                        Text(startDate).foregroundStyle(.white)
                    }
                }
                .padding(.horizontal, 12)
            }
            .frame(maxWidth: 362, maxHeight: 108)
        }
        .frame(maxWidth: 362, maxHeight: 108)
        .background(.black)
        .clipShape(RoundedRectangle(cornerRadius: 4))
    }
}

#Preview {
    NextReservation(
        place: "Laadpaal Carport",
        date: "29 september",
        startDate: "Over 1 dag",
        start: "11:00",
        end: "14:00"
    )
}
