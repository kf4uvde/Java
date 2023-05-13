package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Date;
import java.util.List;

public class HotelResource {

    private static HotelResource singletonHotelResource = new HotelResource();
    private CustomerService customerService = CustomerService.getInstance();
    private ReservationService reservationService = ReservationService.getInstance();

    private HotelResource() {}
    public static HotelResource getInstance() {return singletonHotelResource;}

    public Customer getCustomer(String emil)
    {
        return customerService.getCustomer(emil);
    }

    public void createACustomer(String emil,String firstName,String lastName)
    {
        customerService.addCustomer(emil,firstName,lastName);
    }

    public IRoom getRoom(String roomNumbers)
    {
        return reservationService.getARoom(roomNumbers);
    }

    public void bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate)
    {
        reservationService.reservationARoom(getCustomer(customerEmail),room,checkInDate,checkOutDate);
    }

    public List<Reservation> getCustomerReservation(String Email)
    {
        return reservationService.getCustomerReservation(getCustomer(Email));
    }

    public List<IRoom> findARoom(Date checkInDate,Date ckeckOutDate)
    {
        return reservationService.findRooms(checkInDate,ckeckOutDate);
    }
}
