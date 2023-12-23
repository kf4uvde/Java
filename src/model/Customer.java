package model;
import checker.Checker;

public final class Customer {
    private final String firstName;
    private final String lastName;
    private final String emil;

    public Customer(String email,String firstName,String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;

        if(Checker.checkEmail(email))
        {
            this.emil = email;
        }
        else
        {
            throw new IllegalArgumentException("Email Format is wrong");
        }
    }



    @Override
    public String toString() {
        return "Name : " + firstName + " " + lastName + " ,Emil is " + emil;
    }
}
