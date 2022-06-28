package UtilClass;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class CalculatingAge {
    String CYAN	= "\u001B[36m";
    boolean success;
    public boolean ageCalculation(String dob) throws ParseException {

        String ANSI_RESET = "\u001B[0m";
        //Converting String to Date
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = formatter.parse(dob);
        //Converting obtained Date object to LocalDate object
        Instant instant = date.toInstant();
        ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
        LocalDate givenDate = zone.toLocalDate();
        //Calculating the difference between given date to current date.
        Period period = Period.between(givenDate, LocalDate.now());
        if(period.getYears() > 26)
        {
            System.out.println(CYAN+"Sorry your age is "+period.getYears()+" greater than 26..in our college we have a age limit"+ANSI_RESET);
            success = false;
        }
        else
        {
            success = true;
        }
        return success;
    }
}
