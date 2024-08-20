package com.iotbay;

import javax.servlet.http.HttpSession;

/*
 * Validation for login, update and registration pages
 * At least one form of validation has been applied to each field that can be filled.
 */

public class UserValidation {

    private final static String alphanumericPattern = "^[a-zA-Z0-9-\\s]+$";
    private final static String postcodePattern = "\\d{4,8}";
    private final static String phonePattern = "^[0-9]{8,15}$";
    private final static String staffIdPattern = "^[0-9]{8}$";
    
    public static boolean isEmailValid(String email) {
        if (email.length() > 60) {
            return false;
        }
        return true;
    }

    public static boolean isEmailValid(String oldEmail, String newEmail) {
        if (newEmail.length() > 60) {
            return false;
        }
        return true;
    }

    public static boolean isPasswordValid(String password) {
        if (password.length() < 8) {
            return false;
        }
        return true;
    }

    // check field only contains alphanumeric characters. common method used to validate almost all of the fields
    public static boolean isFieldAlphaNum(String field) {
        if (field.isEmpty()) {
            return false;
        }
        return field.matches(alphanumericPattern);
    }

    // checks if postcode is between 4-8 length, and contains only digits
    public static boolean isPostcodeValid(int postcode) {
        String postcodeString = String.valueOf(postcode);
        return postcodeString.matches(postcodePattern);
    }

    // checks if phone numbers are between 8-15 length and contain only digits
    public static boolean isPhoneNumberValid(String phoneNumber) {
        return phoneNumber.matches(phonePattern);
    }

    // checks if staff id contains only digits and is of 8 length
    public static boolean isStaffIdInvalid(String staffId) {
        return !staffId.matches(staffIdPattern);
    }

    public static void clear(HttpSession session) {
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
    }
}
