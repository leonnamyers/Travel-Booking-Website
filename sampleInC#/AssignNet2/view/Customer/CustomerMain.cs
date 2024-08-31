using AssignNet2;
using Booking.com.view.Customer;
using System;
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
    public partial class CustomerMain : Form
    {
        Customer customer;
        public CustomerMain(Customer customer)
        {
            InitializeComponent();
            this.customer = customer;
            label_welcome.Text = $"Welcome to your customer booking portal, {customer.FirstName}!";
            label_loggedinuser.Text = $"You are logged in as '{customer.FirstName} {customer.LastName}' ({customer.Email})";
            this.Show();
        }

        private void logout_click(object sender, EventArgs e)
        {
            Start start = new Start();
            start.Show();
            this.Close();
        }

        private void changedetails_Click(object sender, EventArgs e)
        {
            ChangeAccountDetails customerChangeDetailsView = new ChangeAccountDetails(customer);
            customerChangeDetailsView.ShowDialog();
        }

        private void button_bookHotel_Click(object sender, EventArgs e)
        {
            SelectHotelToBook customerViewHotels = new SelectHotelToBook(customer);
            customerViewHotels.Show();
        }

        private void button_viewhotelbookings_Click(object sender, EventArgs e)
        {
            ViewHotelBookings test = new ViewHotelBookings(customer, this);
            test.Show();
        }

        private void button_bookAirfare_Click(object sender, EventArgs e)
        {
            SelectAirfareToBook selectAirfareToBook = new SelectAirfareToBook(customer);
            selectAirfareToBook.ShowDialog();
        }

        private void button_viewAirfare_Click(object sender, EventArgs e)
        {
            ViewAirfareBookings viewAirfareBookings = new();
            viewAirfareBookings.ShowDialog();
        }
    }
}
