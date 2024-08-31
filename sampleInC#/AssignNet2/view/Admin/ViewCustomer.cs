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
    public partial class ViewCustomer : Form
    {
        Customer customer;
        public ViewCustomer(Customer customer)
        {
            InitializeComponent();
            this.customer = customer;
            DisplayAllDetails();
            DisplayAllBookings();
        }

        private void DisplayAllDetails()
        {
            text_name.Text = customer.ToString();
            text_email.Text = customer.Email;
            text_phone.Text = customer.Phone;
            text_address.Text = customer.Address;
        }

        private void DisplayAllBookings()
        {
            //implement after LINQ
        }

        private void button_close_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
