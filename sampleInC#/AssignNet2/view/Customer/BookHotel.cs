using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Booking.com
{
    public partial class BookHotel : Form
    {
        Customer customer;
        Hotel hotel;

        public BookHotel(Customer customer, Hotel hotel)
        {
            InitializeComponent();
            this.customer = customer;
            this.hotel = hotel;
            DisplayHotelName();
            DisplayBookingDatesInvalid();
        }

        private void DisplayBookingDatesInvalid()
        {
            text_nights.Text = "";
            text_price.Text = "Check-out date must be after check-in date.";
        }

        private void ReturnToPreviousForm()
        {
            SelectHotelToBook customerViewHotels = new SelectHotelToBook(customer);
            customerViewHotels.Show();
            this.Close();
        }

        private void DisplayHotelName()
        {
            text_bookhotel.Text = $"Book Hotel: {hotel} for {hotel.PricePerNight.ToString("C2")} per night.";
        }

        private void button_confirm_Click(object sender, EventArgs e)
        {
            int numberOfNights = BookingManager.CalculateNumberOfNights(dtp_checkin.Value, dtp_checkout.Value);
            Hashtable properties = new Hashtable()
            {
                {"Email", customer.Email },
                {"CheckIn", dtp_checkin.Value },
                {"CheckOut", dtp_checkout.Value },
                {"BookingType", BookingType.Hotel },
                {"EntityId", hotel.Id },
                {"NumberOfNights", numberOfNights },
                {"TotalPrice", numberOfNights * hotel.PricePerNight }
            };
            bool bookingSuccess = BookingManager.CreateHotelBooking(properties);
            if (bookingSuccess)
            {
                MessageBox.Show("Your hotel booking was successful!", "Booking Confirmed", MessageBoxButtons.OK, MessageBoxIcon.Information);
                ReturnToPreviousForm();
            }
        }

        private void DisplayTotalPriceAndNights()
        {
            int numberOfNights = BookingManager.CalculateNumberOfNights(dtp_checkin.Value, dtp_checkout.Value);
            if (numberOfNights >= 1)
            {
                text_nights.Text = numberOfNights.ToString();
                text_price.Text = $"{(numberOfNights * hotel.PricePerNight).ToString("C2")}";
            }
            else
            {
                DisplayBookingDatesInvalid();
            }
        }

        private void button_cancel_Click(object sender, EventArgs e)
        {
            ReturnToPreviousForm();
        }

        private void dtp_checkin_value_changed(object sender, EventArgs e)
        {
            DisplayTotalPriceAndNights();
        }

        private void dtp_checkout_value_changed(object sender, EventArgs e)
        {
            DisplayTotalPriceAndNights();
        }
    }
}
