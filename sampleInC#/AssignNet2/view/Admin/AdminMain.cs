
using System;
using System.Windows.Forms;

namespace Booking.com
{
    public partial class AdminMain : Form
    {
        Admin admin;
        public AdminMain(Admin admin)
        {
            InitializeComponent();
            this.admin = admin;
        }

        private void logout_click(object sender, EventArgs e)
        {
            Start start = new Start();
            start.Show();
            this.Close();
        }

        private void addhotel_click(object sender, EventArgs e)
        {
            AddHotel addHotelView = new AddHotel(admin);
            addHotelView.Show();
        }

        private void button_viewallhotels_Click(object sender, EventArgs e)
        {
            ViewAllHotels viewAllHotelsView = new ViewAllHotels(admin);
            viewAllHotelsView.Show();
        }

        private void button_addairline_Click(object sender, EventArgs e)
        {
            AddAirfare addAirfareView = new AddAirfare(admin);
            addAirfareView.Show();
        }

        private void button_registercustomer_Click(object sender, EventArgs e)
        {
            Register registerView = new Register();
            registerView.ShowDialog();
        }

        private void button_searchcustomerbookings_Click(object sender, EventArgs e)
        {
            SearchCustomer searchCustomerView = new SearchCustomer();
            searchCustomerView.ShowDialog();
        }

        private void button_viewallcustomers_Click(object sender, EventArgs e)
        {
            ViewAllCustomers viewAllCustomersView = new ViewAllCustomers(admin);
            viewAllCustomersView.Show();
        }

        private void button_searchbookings_Click(object sender, EventArgs e)
        {

        }

        private void button_vieweditairfare_Click(object sender, EventArgs e)
        {
            ViewAllAirfares viewAllAirfares = new();
            viewAllAirfares.ShowDialog();
        }
    }
}
