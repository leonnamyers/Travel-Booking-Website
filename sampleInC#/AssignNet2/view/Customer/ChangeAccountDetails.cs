using Booking.com.controller.exceptions;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Drawing;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Booking.com
{
    public partial class ChangeAccountDetails : Form
    {
        Customer customer;
        public ChangeAccountDetails(Customer customer)
        {
            InitializeComponent();
            this.customer = customer;

            tb_email.Text = customer.Email;
            tb_password.Text = customer.Password;
            tb_firstname.Text = customer.FirstName;
            tb_lastname.Text = customer.LastName;
            tb_phonenumber.Text = customer.Phone;
            tb_address.Text = customer.Address;
        }

        private void changedetails_Click(object sender, EventArgs e)
        {
            Dictionary<string, string> properties = new Dictionary<string, string>
            {
                { "Email", tb_email.Text },
                { "Password", tb_password.Text },
                { "FirstName", tb_firstname.Text },
                { "LastName", tb_lastname.Text },
                { "Phone", tb_phonenumber.Text },
                { "Address", tb_address.Text }
            };
            try
            {
                bool detailsChanged = customer.ChangeAccountDetails(properties);
                if (detailsChanged)
                {
                    MessageBox.Show("Your customer details were successfully updated.", "Account Update Successful");
                    this.Close();
                }
            }
            catch (Exception ex)
            {
                Debug.WriteLine(ex.ToString());
                // do nothing and let the user try again. detailed error message boxes are already displayed in a deeper level exception.
            }
        }

        private void phone_Keypress(object sender, KeyPressEventArgs e)
        {
            if (!char.IsControl(e.KeyChar) && !char.IsDigit(e.KeyChar))
            {
                e.Handled = true;
            }
        }

        private void postcode_Keypress(object sender, KeyPressEventArgs e)
        {
            if (!char.IsControl(e.KeyChar) && !char.IsDigit(e.KeyChar))
            {
                e.Handled = true;
            }
        }

        private void cancel_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
