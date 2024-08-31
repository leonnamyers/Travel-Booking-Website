using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;
using System.Collections;
using Booking.com.controller.validation;
using Booking.com.model;

public class Customer : User
{
    public Customer(string email, string password, string firstName, string lastName, string phone, string address) : base(email, password, firstName, lastName, phone, address, UserType.Customer)
    {
    }

    public override string ToString()
    {
        return FirstName + " " + LastName;
    }


    public bool ChangeAccountDetails(Dictionary<string, string> customerDetails)
    {
        if (UserFormValidation.AreModifiedCustomerInputsValid(customerDetails))
        {
            FileManager.UpdateDetails(this, customerDetails);
            this.Email = customerDetails["Email"];
            this.Password = customerDetails["Password"];
            this.FirstName = customerDetails["FirstName"];
            this.LastName = customerDetails["LastName"];
            this.Phone = customerDetails["Phone"];
            this.Address = customerDetails["Address"];
            return true;
        }
        return false;
    }
}



