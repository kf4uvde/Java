package model;

import java.util.regex.Pattern;

public final class Customer {
    String firstName;
    String lastName;
    String emil;

    public Customer(String email,String firstName,String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;

        if(checkEmail(email))
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

    private boolean checkEmail(String email)
    {
        String emailPattern = "^(.+)@(.+).(.+)$";
        Pattern pattern = Pattern.compile(emailPattern);

        return pattern.matcher(email).matches();
    }
}
