package manu;

import api.AdminResource;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static checker.Checker.checkDate;
import static checker.Checker.checkEmail;

public class MainMenu {

    private Scanner scan = new Scanner(System.in);
    private HotelResource singletonHotelResource = HotelResource.getInstance();
    private AdminResource singletonAdminResource = AdminResource.getInstance();
    public void SelectMenuItems()
    {
        while(true)
        {
            String userInput = "";
            System.out.println("1. Find and reserve a room");
            System.out.println("2. See my reservations");
            System.out.println("3. Create a account");
            System.out.println("4. Admin");
            System.out.println("5. Exit");
            System.out.println("------------------------------------------");
            System.out.println("Please Select a number for the menu option");


            if (scan.hasNextLine()) {
                userInput = scan.nextLine();

                switch (userInput)
                {
                    case "1":
                        findAndReserveARoom();
                        break;
                    case "2":
                        seeMyReservations();
                        break;
                    case "3":
                        CreateAAccount();
                        break;
                    case "4":
                        AdminMenu adminMenu = new AdminMenu();
                        adminMenu.SelectMenuItems();
                        break;
                    case "5":
                        return;
                    default:
                        System.out.println("Error Input");
                }
            }
        }
    }

    private void seeMyReservations()
    {
        String email = "";
        List<Reservation> reservations = null;

        do
        {
            System.out.println("Enter Email format: name@domain.com");
            if (scan.hasNextLine()) {
                email = scan.nextLine();
            }
        }while (!checkEmail(email));

        try
        {
            reservations = singletonHotelResource.getCustomerReservation(email);
            if(reservations != null)
            {
                for(Reservation reservation : reservations)
                {
                    System.out.println(reservation.toString());
                }
            }
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("There are no Reservation record by email:" + email);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }

    }

    private void findAndReserveARoom()
    {
        String sCheckInDate = "";
        String sCheckOutDate = "";
        Date checkInDate = null;
        Date checkOutDate = null;

        //User enter their check in date
        do
        {
            System.out.println("Enter CheckIn Date mm/dd/yyyy example 01/01/2023");
            if (scan.hasNextLine()) {
                sCheckInDate = scan.nextLine();
                if(checkDate(sCheckInDate)) {
                    checkInDate = convertToDate(sCheckInDate);
                }
            }
        }while (checkInDate == null);


        //User enter their check out date
        do
        {
            System.out.println("Enter checkOut Date mm/dd/yyyy example 01/01/2024");
            if (scan.hasNextLine()) {
                sCheckOutDate = scan.nextLine();
                if(checkDate(sCheckOutDate)) {
                    checkOutDate = convertToDate(sCheckOutDate);
                }
            }
        }while (checkOutDate == null);

        List<IRoom> availableRooms = singletonHotelResource.findARoom(checkInDate,checkOutDate);

        if(availableRooms == null || availableRooms.size() < 1)
        {
            checkInDate = new Date(checkInDate.getTime() + (1000 * 60 * 60 * 24*7)); //add 7days
            checkOutDate  = new Date(checkOutDate.getTime() + (1000 * 60 * 60 * 24*7)); //add 7days
            availableRooms = singletonHotelResource.findARoom(checkInDate,checkOutDate);

            //add 7days and check again
            if(availableRooms == null || availableRooms.size() < 1)
            {
                System.out.println("There are no match days.");
                return;
            }
            System.out.println("There are no match days, we search 7 days after for available rooms you.");
        }

        //print out available Rooms
        System.out.println("available Rooms");
        for(IRoom room : availableRooms)
        {
            System.out.println(room);
        }

        //------------------------------------------------Reserve
        String continuetobook = "";
        do
        {
            System.out.println("Would you like to Reservation a room? (y/n)");
            if (scan.hasNextLine()) {
                continuetobook = scan.nextLine();
            }
            if(continuetobook.equalsIgnoreCase("n") || continuetobook.equalsIgnoreCase("no"))
            {
                return;
            }
        }while (!continuetobook.equalsIgnoreCase("y") && !continuetobook.equalsIgnoreCase("yes"));

        String email = "";
        do
        {
            System.out.println("Enter Email format: name@domain.com");
            if (scan.hasNextLine()) {
                email = scan.nextLine();
            }
        }while (!checkEmail(email));

        if(singletonHotelResource.getCustomer(email) == null)
        {
            System.out.println("This Email is not our member,please create a account");
            return;
        }

        String roomID = "";
        do
        {
            System.out.println("What room number would you like to book");
            if (scan.hasNextLine()) {
                roomID  = scan.nextLine();
            }
        }while (singletonHotelResource.getRoom(roomID) == null || !availableRooms.contains(singletonHotelResource.getRoom(roomID)));

        singletonHotelResource.bookARoom(email,singletonHotelResource.getRoom(roomID),checkInDate,checkOutDate);
    }

    private Date convertToDate(String Str)
    {
        try {
            String date[] = Str.split("/");
            DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
            return  dateFormat1.parse(date[2] + "-" + date[0] + "-" + date[1]);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        return null;
    }

    private void CreateAAccount()
    {
        String email = "";
        String firstName = "";
        String lastName = "";

        do
        {
            System.out.println("Enter Email format: name@domain.com");
            if (scan.hasNextLine()) {
                email = scan.nextLine();
            }
        }while (!checkEmail(email));

        do
        {
            System.out.println("Enter first name");
            if (scan.hasNextLine()) {
                firstName = scan.nextLine();
            }
        }while (firstName.equals(""));

        do
        {
            System.out.println("Enter last name");
            if (scan.hasNextLine()) {
                lastName = scan.nextLine();
            }
        }while (lastName.equals(""));

        try
        {
            singletonHotelResource.createACustomer(email,firstName,lastName);
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }



    private void createAAccount()
    {

    }
}
