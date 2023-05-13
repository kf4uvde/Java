package checker;

import java.util.regex.Pattern;

//To check some input format is what i expect.
public class Checker {
    public static boolean checkDate(String date)
    {
        String RegExDate = "^(1[0-2]|0[1-9])/(3[01]|[12][0-9]|0[1-9])/[0-9]{4}$";
        //Creating a pattern object
        Pattern datePattern = Pattern.compile(RegExDate);
        //Matching the compiled pattern in the String
        return datePattern.matcher(date).matches();
    }

    public static boolean checkEmail(String email)
    {
        String emailPattern = "^(.+)@(.+).(.+)$";
        Pattern pattern = Pattern.compile(emailPattern);

        return pattern.matcher(email).matches();
    }
}
