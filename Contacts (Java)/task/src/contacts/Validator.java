package contacts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Validator {
    /**
     * Check if number is incorrect https://en.wikipedia.org/wiki/National_conventions_for_writing_telephone_numbers
     * @param phoneNumber Number to check
     * @param message Message about adding contact
     * @return return true if phone number is invalid otherwise false
     */
    public static boolean isInvalidPhoneNumber(String phoneNumber, String message) {
        String regex = "^\\+?(\\(\\w+\\)|\\w+[ -]\\(\\w{2,}\\)|\\w+)([ -]\\w{2,})*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (!matcher.matches()) {
            System.out.println("Wrong number format!");
            System.out.println(message);
        } else {
            System.out.println(message);
        }
        return !matcher.matches();
    }

    /**
     * Check if birthdate is incorrect
     * @param birthDateString Date to check
     * @return Return true if date is invalid otherwise false
     */
    public static boolean isInvalidBirthDate(String birthDateString) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            LocalDate birthDate = LocalDate.parse(birthDateString, dateFormatter);
            return false;
        } catch (DateTimeParseException e) {
            System.out.println("Bad birth date!");
            return true;
        }
    }

    /**
     * Check if Gender is incorrect
     * @param gender Gender to check
     * @return true if gender is incorrect otherwise false
     */
    public static boolean isInvalidGender(String gender) {
        if (gender.equals("M") || gender.equals("F")) {
            return false;
        }
        System.out.println("Bad gender!");
        return true;
    }
}
