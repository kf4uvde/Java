package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {

    private static ReservationService singletonReservationService = new ReservationService();
    private Map<String, IRoom> mapOfRoom = new HashMap<String, IRoom>();
    private Map<String,List<Reservation>> mapOfReservation = new HashMap<String,List<Reservation>>();//String for Room ID
    private ReservationService(){};
    public static ReservationService getInstance()
    {
        return singletonReservationService;
    }

    public void addRoom(IRoom room)
    {
        if(room != null)
        {
            if(!mapOfRoom.containsKey(room.getRoomNumber()))
            {
                mapOfRoom.put(room.getRoomNumber(),room);
            }
            else
            {
                throw new IllegalArgumentException("Room ID is exit");
            }
        }
        else
        {
            throw new IllegalArgumentException("Room is null");
        }
    }
    public IRoom getARoom(String roomID)
    {
        return mapOfRoom.get(roomID);
    }
    public List<IRoom> getAllRooms()
    {
        List<IRoom> rooms = new LinkedList<IRoom>();
        for(String roomID : mapOfRoom.keySet())
        {
            rooms.add(mapOfRoom.get(roomID));
        }
        return rooms;
    }

    public Reservation reservationARoom(Customer customer,IRoom room,Date checkInDate,Date ckeckOutDate)
    {
        if(customer != null && room != null)
        {
            Reservation newReservation = new Reservation(customer,room,checkInDate,ckeckOutDate);
            if(!mapOfReservation.containsKey(room.getRoomNumber()))
            {
                List<Reservation> listOfARoomReservation = new LinkedList<Reservation>();
                listOfARoomReservation.add(newReservation);
                mapOfReservation.put(room.getRoomNumber(),listOfARoomReservation);
            }
            else
            {
                mapOfReservation.get(room.getRoomNumber()).add(newReservation);
            }
            return newReservation;
        }
        else
        {
            throw new IllegalArgumentException("customer or room is null");
        }
    }

    public List<IRoom> findRooms(Date checkInDate,Date ckeckOutDate)
    {
        if(checkInDate==null && ckeckOutDate==null) {
            throw new IllegalArgumentException("Check IN or Check Out Date is Null");
        }
        if(!ckeckOutDate.after(checkInDate)) {
            throw new IllegalArgumentException("Check IN Date is later than Check Out Date");
        }

        List<IRoom> foundRooms = new LinkedList<IRoom>();
        for(String roomID : mapOfRoom.keySet())
        {
            if(mapOfReservation.containsKey(roomID))
            {
                for(Reservation reservation : mapOfReservation.get(roomID))
                {
                    if(ckeckOutDate.before(reservation.getCheckINDate()) || checkInDate.after(reservation.getCkeckOutDate()))
                    {
                        foundRooms.add(mapOfRoom.get(roomID));
                    }
                }
            }
            else
            {
                foundRooms.add(mapOfRoom.get(roomID));
            }
        }
        return foundRooms;
    }

    public List<Reservation> getCustomerReservation(Customer customer)
    {
        if(customer != null)
        {
            List<Reservation> foundReservations = new LinkedList<Reservation>();
            for(String roomID : mapOfReservation.keySet())
            {
                for(Reservation reservation : mapOfReservation.get(roomID))
                {
                    if(reservation.getCustomer().equals(customer))
                    {
                        foundReservations.add(reservation);
                    }
                }
            }
            return foundReservations;
        }
        else
        {
            throw new IllegalArgumentException("customer is Null");
        }
    }

    public void printAllReservation()
    {
        for(String roomID : mapOfReservation.keySet())
        {
            for(Reservation reservation : mapOfReservation.get(roomID))
            {
                System.out.println(reservation);
            }
        }
    }
}
