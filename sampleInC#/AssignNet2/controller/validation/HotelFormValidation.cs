using System;
using System.Collections;
using System.Collections.Generic;
using System.Windows.Forms;

namespace Booking.com.controller.validation
{
    public class HotelFormValidation
    {
        public static bool AreHotelInputsValid(Dictionary<string, string> properties)
        {
            if (string.IsNullOrEmpty(properties["Name"]))
            {
                MessageBox.Show("Please enter a valid hotel name");
                return false;
            }
            else if (string.IsNullOrEmpty(properties["Location"]))
            {
                MessageBox.Show("Please enter a valid hotel location");
                return false;
            }
            else if (string.IsNullOrEmpty(properties["Price"]))
            {
                MessageBox.Show("Please enter a valid price per night");
                return false;
            }
            return true;
        }

        public static bool IsHotelBookingValid(Hashtable properties)
        {
            if (DateTime.Compare((DateTime)properties["CheckOut"], (DateTime)properties["CheckIn"]) < 0)
            {
                MessageBox.Show("Check out date cannot be before check in date.", "Error - Dates Not Allowed", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return false;
            }
            return true;
        }
    }
}
