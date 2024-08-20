package com.iotbay.Controller;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

/*
 * Validation for login, update and registration pages
 * At least one form of validation has been applied to each field that can be entered.
 */

public class UserValidation implements Serializable {

    private final static String alphanumericPattern = "^[a-zA-Z0-9-\\s]+$";
    private final static String postcodePattern = "\\d{4,8}";
    private final static String phonePattern = "^[0-9]{8,15}$";
    private final static String staffIdPattern = "^[0-9]{8}$";
    private final static String emailPattern = "([a-zA-Z0-9]+)(([._-])([a-zA-Z0-9]+))*(@)([a-z]+)(.)([a-z]{3})((([.])[a-z]{0,2})*)";      
    //private final static String namePattern = "([A-Z][a-z]+[\\s])+[A-Z][a-z]*";       
    
    public static boolean validate(String pattern, String input){       
        Pattern regEx = Pattern.compile(pattern);       
        Matcher match = regEx.matcher(input);       
        return match.matches(); 
     } 

    public static boolean isEmailValid(String email) {
        if (email.length() < 60) {
            return validate(emailPattern, email);
        }
        return false;
    }

    // check if oldEmail == newEmail
    // check if newEmail is valid
    public static boolean isEmailValid(String oldEmail, String newEmail) {
        if (!oldEmail.equals(newEmail)) {
            return isEmailValid(newEmail);
        }
        return true;
    }

    //to do
    public static boolean isEmailUnique(String email) {
        return false;
    }

    // Password must be at least 8 characters
    public static boolean isPasswordValid(String password) {
        if (password.length() < 8) {
            return false;
        }
        return true;
    }

    // checking based on the alphanumeric pattern
    public static boolean isFieldAlphaNum(String field) {
        if (!field.isEmpty()) {
            return validate(alphanumericPattern, field);
        }
        return false;
    }

    // checks if postcode is between 4-8 length, and contains only digits
    public static boolean isPostcodeValid(int postcode) {
        String postcodeString = String.valueOf(postcode);
        return validate(postcodePattern, postcodeString);
    }

    // checks if phone numbers are between 8-15 length and contains only digits
    public static boolean isPhoneNumberValid(String phoneNumber) {
        return validate(phonePattern, phoneNumber);
    }

    // checks if staff id contains only digits and is of 8 length
    public static boolean isStaffIdInvalid(String staffId) {
        return validate(staffIdPattern, staffId);
    }

    // checks if input date time format is correct (for searching user access logs)
    public static boolean isDateValid(String inputDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        
        try {
            sdf.parse(inputDate); // Parsing without casting
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static void clear(HttpSession session) {
        session.setAttribute("duplicateEmail", "");
        session.setAttribute("emailError", "");
        session.setAttribute("passwordError", "");
        session.setAttribute("loginError", "");
        session.setAttribute("firstNameError", "");
        session.setAttribute("lastNameError", "");
        session.setAttribute("homePhoneError", "");
        session.setAttribute("mobilePhoneError", "");
        session.setAttribute("streetAddressError", "");
        session.setAttribute("postcodeError", "");
        session.setAttribute("cityError", "");
        session.setAttribute("staffIDError", "");
        session.setAttribute("updateSuccess", "");
        session.setAttribute("duplicateStaffID", "");
        session.setAttribute("dateFormatError", "");
    }
}
