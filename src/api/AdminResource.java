package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.List;

public class AdminResource {
    private static AdminResource singletonAdminResource = new AdminResource();
    private CustomerService customerService = CustomerService.getInstance();
    private ReservationService reservationService = ReservationService.getInstance();
    private AdminResource() {}

    public static AdminResource getInstance() { return singletonAdminResource; }

    public Customer getCustomer(String emil)
    {
        return customerService.getCustomer(emil);
    }

    public void addRoom(List<IRoom> rooms)
    {
        if(rooms != null)
        {
            for(IRoom room : rooms)
            {
                reservationService.addRoom(room);
            }
        }
    }

    public List<IRoom> getAllRooms()
    {
        return reservationService.getAllRooms();
    }

    public List<Customer> getAllCustomers()
    {
        return customerService.getAllCustomers();
    }

    public void displayAllReservations()
    {
        reservationService.printAllReservation();
    }
}
