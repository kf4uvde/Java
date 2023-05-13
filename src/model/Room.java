package model;

import java.util.regex.Pattern;

public class Room implements IRoom{
    String roomNumber;
    Double roomPrice;
    RoomType roomType;

    public Room(String roomNumber,Double roomPrice,RoomType roomType)
    {
        this.roomPrice = roomPrice;
        this.roomType = roomType;
        if(checkNumbers(roomNumber))
        {
            this.roomNumber = roomNumber;
        }
        else
        {
            throw new IllegalArgumentException("Room Number is not Numbers");
        }
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
    public String toString() {
        return "Room number : " + roomNumber + "\nRoom type : " + roomType.toString() + "\nRoom price : " + roomPrice;
    }

    @Override
    public boolean isFree() {
        if(roomPrice == 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean checkNumbers(String Numbers)
    {
        String numbersPattern = "^[0-9]*$";
        Pattern pattern = Pattern.compile(numbersPattern);

        return pattern.matcher(Numbers).matches();
    }
}
