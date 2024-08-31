using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Windows.Forms;

namespace Booking.com.controller.validation
{
    // This class contains the logic for ensuring valid inputs into fields across the application for Users including customer login and registration.
    // It only deals with sane input validation, but does not perform logic such as checking whether a user exists
    // The error MessageBoxes are shown directly from this class to reduce complexity, as opposed to all other classes having to parse, handle and display their own error messages.
    public class UserFormValidation
    {
        public static bool AreModifiedCustomerInputsValid(Dictionary<string, string> properties)
        {
            if (string.IsNullOrEmpty(properties["Email"]) || !properties["Email"].Contains('@'))
            {
                MessageBox.Show("Please enter a valid email address");
                return false;
            }
            else if (string.IsNullOrEmpty(properties["Password"]) || properties["Password"].Count() < 8)
            {
                MessageBox.Show("Please enter a valid password (at least 8 characters)");
                return false;
            }
            else if (string.IsNullOrEmpty(properties["FirstName"]))
            {
                MessageBox.Show("Please enter a First Name");
                return false;
            }
            else if (string.IsNullOrEmpty(properties["LastName"]))
            {
                MessageBox.Show("Please enter a Last Name");
                return false;
            }
            else if (string.IsNullOrEmpty(properties["Phone"]) || properties["Phone"].Count() < 8)
            {
                MessageBox.Show("Please enter a valid phone number (must be at least 8 digits)");
                return false;
            }
            else if (string.IsNullOrEmpty(properties["Address"]))
            {
                MessageBox.Show("Please enter a valid Street Address. Address can not be empty");
                return false;
            }
            return true;
        }

        public static bool AreLoginInputsValid(string email, string password)
        {
            if (string.IsNullOrEmpty(email))
            {
                MessageBox.Show("Please enter a valid email address");
                return false;
            }
            else if (string.IsNullOrEmpty(password) || password.Length < 8)
            {
                MessageBox.Show("Please enter a valid password");
                return false;
            }
            return true;
        }

        public static bool AreCustomerRegistrationInputsValid(Dictionary<string, string> properties)
        {
            if (string.IsNullOrEmpty(properties["Email"]) || !properties["Email"].Contains('@'))
            {
                MessageBox.Show("Please enter a valid email address");
                return false;
            }
            else if (string.IsNullOrEmpty(properties["Password"]) || properties["Password"].Length < 8)
            {
                MessageBox.Show("Please enter a valid password (at least 8 characters)");
                return false;
            }
            else if (string.IsNullOrEmpty(properties["FirstName"]))
            {
                MessageBox.Show("Please enter a First Name");
                return false;
            }
            else if (string.IsNullOrEmpty(properties["LastName"]))
            {
                MessageBox.Show("Please enter a Last Name");
                return false;
            }
            else if (string.IsNullOrEmpty(properties["Phone"]) || properties["Phone"].Length < 8)
            {
                MessageBox.Show("Please enter a valid phone number (at least 8 digits)");
                return false;
            }
            else if (string.IsNullOrEmpty(properties["StreetAddress"]))
            {
                MessageBox.Show("Please enter a valid Street Address");
                return false;
            }
            else if (string.IsNullOrEmpty(properties["Suburb"]))
            {
                MessageBox.Show("Please enter a valid suburb");
                return false;
            }
            else if (string.IsNullOrEmpty(properties["Postcode"]) || properties["Postcode"].Length < 4)
            {
                MessageBox.Show("Please enter a valid Postcode");
                return false;
            }
            else if (string.IsNullOrEmpty(properties["State"]))
            {
                MessageBox.Show("Please select a State or Territory from the dropdown list");
                return false;
            }
            return true;
        }
    }
}
