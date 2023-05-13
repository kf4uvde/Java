package model;

public class FreeRoom extends Room{
    public FreeRoom(String roomNumber,RoomType roomType)
    {
        super(roomNumber,(double) 0,roomType);
    }

    @Override
    public String toString() {
        return "Room number : " + roomNumber + "\nRoom type : " + roomType.toString() + "\nRoom is Free";
    }
}
