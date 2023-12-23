package model;
import checker.Checker;

public class Room implements IRoom{
    protected final String roomNumber;
    protected final Double roomPrice;
    protected final RoomType roomType;

    public Room(String roomNumber,Double roomPrice,RoomType roomType)
    {
        this.roomPrice = roomPrice;
        this.roomType = roomType;
        if(Checker.checkNumbers(roomNumber))
        {
            this.roomNumber = roomNumber;
        }
        else
        {
            throw new IllegalArgumentException("Room Number is not Numbers");
        }
    }
    @Override
    public String toString() {
        return "Room number : " + roomNumber + "\nRoom type : " + roomType.toString() + "\nRoom price : " + roomPrice+"\n";
    }

    @Override
    public Double getRoomPrice() {
        return roomPrice;
    }

    @Override
    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public boolean isFree() {
            return false;
    }
}
