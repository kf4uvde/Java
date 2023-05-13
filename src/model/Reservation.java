package model;

import java.util.Date;

public class Reservation {
    Customer customer;
    IRoom room;
    Date checkINDate;
    Date ckeckOutDate;

    public Reservation(Customer customer,IRoom room,Date checkINDate,Date ckeckOutDate)
    {
        if(ckeckOutDate.after(checkINDate))
        {
            this.checkINDate = checkINDate;
            this.ckeckOutDate = ckeckOutDate;
            this.customer = customer;
            this.room = room;
        }
        else
        {
            throw new IllegalArgumentException("Check IN Date is later than Check Out Date");
        }
    }

    public Date getCheckINDate() {
        return checkINDate;
    }

    public Date getCkeckOutDate() {
        return ckeckOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return customer.toString() + "\nReservation Date:\nCheck In Date : " + checkINDate + "\nCheck Out Date : " + ckeckOutDate;
    }
}
