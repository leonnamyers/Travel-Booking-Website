
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Windows.Forms;

namespace Booking.com
{
    public partial class ViewHotelBookings : Form
    {
        private BookingContext context;
        private Customer customer;
        private Form parentForm;

        public ViewHotelBookings(Customer customer, Form parentForm)
        {
            InitializeComponent();
            this.customer = customer;
            this.parentForm = parentForm;
            label_loggedinuser.Text = $"You are logged in as '{customer.FirstName} {customer.LastName}' ({customer.Email})";
        }

        protected override void OnLoad(EventArgs e)
        {
            base.OnLoad(e);
            this.context = new BookingContext();
            this.context.Database.EnsureCreated();
            this.context.Bookings.Load();
            PopulateDatagridWithLinq();
        }

        private void PopulateDatagridWithLinq()
        {
            List<Booking> customerBookings = context.Bookings
            .Where(b => b.Email == customer.Email)
            .ToList();

            foreach (Booking booking in customerBookings)
            {
                var rowId = dtg_bookings.Rows.Add();
                var row = dtg_bookings.Rows[rowId];
                row.Cells["bookingid"].Value = booking.BookingId;
                row.Cells["hotelname"].Value = Hotel.getNameById(booking.EntityId);
                row.Cells["checkin"].Value = booking.CheckIn.ToShortDateString();
                row.Cells["checkout"].Value = booking.CheckOut.ToShortDateString();
                row.Cells["nights"].Value = booking.NumberOfNights;
                row.Cells["totalprice"].Value = booking.TotalPrice.ToString("C2");
            }
        }

        public void ReturnToPreviousForm()
        {
            this.Close();
        }

        private void button_logout_Click(object sender, EventArgs e)
        {
            Start start = new Start();
            start.Show();
            this.Close();
            parentForm.Close();
        }

        private void button_back_Click(object sender, EventArgs e)
        {
            ReturnToPreviousForm();
        }

        private void button_cancelbooking_Click(object sender, EventArgs e)
        {
            int selectedRows = dtg_bookings.Rows.GetRowCount(DataGridViewElementStates.Selected);
            if (selectedRows > 0)
            {
                foreach (DataGridViewRow row in dtg_bookings.SelectedRows)
                {
                    int bookingIdToDelete = (int)row.Cells["bookingid"].Value;
                    BookingManager.DeleteBooking(bookingIdToDelete);
                    dtg_bookings.Rows.RemoveAt(row.Index);
                }
            }
        }
    }
}
