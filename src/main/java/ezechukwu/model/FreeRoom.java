package ezechukwu.model;

public final class FreeRoom extends Room{
    public FreeRoom(String roomNumber, RoomType roomType) {
        super(roomNumber,0.0, roomType);

    }

    @Override
    public String toString() {
        return "FreeRoom{" +
                "roomNumber='" + this.roomNumber + '\'' +
                ", price=" + this.price +
                ", enumeration=" + this.enumeration +
                '}';
    }
}
