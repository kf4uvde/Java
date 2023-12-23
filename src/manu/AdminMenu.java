package manu;

import api.AdminResource;
import api.HotelResource;
import model.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class AdminMenu {
    private Scanner scan = new Scanner(System.in);
    private HotelResource singletonHotelResource = HotelResource.getInstance();
    private AdminResource singletonAdminResource = AdminResource.getInstance();
    public void SelectMenuItems()
    {
        while(true)
        {
            String userInput = "";
            System.out.println("1. See all Customers");
            System.out.println("2. See all Rooms");
            System.out.println("3. See all Reservations");
            System.out.println("4. Add a Room");
            System.out.println("5. Back to Main Manu");
            System.out.println("------------------------------------------");
            System.out.println("Please Select a number for the menu option");

            if (scan.hasNextLine()) {
                userInput = scan.nextLine();

                switch (userInput)
                {
                    case "1":
                        seeAllCustomers();
                        break;
                    case "2":
                        seeAllRooms();
                        break;
                    case "3":
                        seeAllReservations();
                        break;
                    case "4":
                        addARoom();
                        break;
                    case "5":
                        System.out.println("Exit admin mode");
                        return;
                    default:
                        System.out.println("Error Input");
                }
            }
        }
    }

    private void seeAllRooms()
    {
        System.out.println("All Rooms");
        List<IRoom> rooms = singletonAdminResource.getAllRooms();
        for(IRoom room : rooms )
        {
            System.out.println(room + "\n");
        }
    }

    private void seeAllCustomers()
    {
        System.out.println("All Customers");
        List<Customer> customers = singletonAdminResource.getAllCustomers();
        for(Customer customer : customers )
        {
            System.out.println(customer + "\n");
        }
    }

    private void seeAllReservations()
    {
        System.out.println("All Reservations");
        singletonAdminResource.displayAllReservations();
    }

    private void addARoom()
    {
        String roomNumber = "";
        double Price = 0;
        int iRoomType = 0;
        RoomType ERoomType = RoomType.SINGLE;
        List<IRoom> rooms = new LinkedList<IRoom>();
        String addAnotherRoom = "";

        do {
            do
            {
                System.out.println("Enter room number(numbers only)");
                if (scan.hasNextLine()) {
                    roomNumber = scan.nextLine();
                }
            }while(!checkNumbers(roomNumber));

            do
            {
                System.out.println("Enter price per night");
                Price = scanDouble();
            }while (Price < 0);

            do
            {
                System.out.println("Enter room type: 1 for single bed, 2 for double bed");
                iRoomType = scanInteger();
                System.out.println(iRoomType);
            }while ((iRoomType != 1) && (iRoomType != 2));

            if(iRoomType == 2)
            {
                ERoomType = RoomType.DOUBLE;
            }

            if(Price == 0)
            {
                rooms.add(new FreeRoom(roomNumber,ERoomType));
            }
            else
            {
                rooms.add(new Room(roomNumber,Price,ERoomType));
            }

            do
            {
                System.out.println("Would you like to add another room? (y/n)");
                if (scan.hasNextLine()) {
                    addAnotherRoom = scan.nextLine();
                }
            }while (!addAnotherRoom.equalsIgnoreCase("y") && !addAnotherRoom.equalsIgnoreCase("yes") && !addAnotherRoom.equalsIgnoreCase("n") && !addAnotherRoom.equalsIgnoreCase("no"));

        }while (addAnotherRoom.equalsIgnoreCase("y") || addAnotherRoom.equalsIgnoreCase("yes"));

        for(IRoom room : rooms )
        {
            System.out.println(room);
        }
        singletonAdminResource.addRoom(rooms);
    }

    private int scanInteger()
    {
        try{
            if (scan.hasNextLine()) {
                return  Integer.parseInt(scan.nextLine());
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return -1;
    }

    private double scanDouble()
    {
        try{
            if (scan.hasNextLine()) {
                return  Double.parseDouble(scan.nextLine());
            }
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        return -1;
    }

    private boolean checkNumbers(String Numbers)
    {
        String numbersPattern = "^[0-9]*$";
        Pattern pattern = Pattern.compile(numbersPattern);

        return pattern.matcher(Numbers).matches();
    }
}
