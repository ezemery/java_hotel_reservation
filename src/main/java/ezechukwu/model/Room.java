package ezechukwu.model;

import java.util.Objects;

public class Room implements IRoom{
    protected String roomNumber;
    protected Double price;
    protected RoomType enumeration;

    public Room(String roomNumber, Double price, RoomType enumeration) {
        this.roomNumber = roomNumber;
        this.price = price;
        this.enumeration = enumeration;
    }

    @Override
    public String getRoomNumber() {
        return this.roomNumber;
    }

    @Override
    public String getRoomPrice() {
        return this.price.toString();
    }

    @Override
    public RoomType getRoomType() {
        return this.enumeration;
    }

    @Override
    public boolean isFree() {
        return this.price == 0.0;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + this.roomNumber + '\'' +
                ", price=" + this.price +
                ", enumeration=" + this.enumeration +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomNumber, room.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }
}
