using System;
using System.Collections;
using System.Collections.Generic;
using System.Windows.Forms;

namespace Booking.com
{
    public partial class EditCustomer : Form
    {
        Admin admin;
        Customer customer;
        public EditCustomer(Customer customer, Admin admin)
        {
            InitializeComponent();
            this.admin = admin;
            this.customer = customer;
            DisplayCustomerDetails();
        }

        private void ReturnToPreviousForm()
        {
            ViewAllCustomers viewAllCustomersView = new ViewAllCustomers(admin);
            viewAllCustomersView.Show();
            this.Close();
        }

        private void DisplayCustomerDetails()
        {
            tb_firstname.Text = customer.FirstName;
            tb_lastname.Text = customer.LastName;
            tb_email.Text = customer.Email;
            tb_phone.Text = customer.Phone.ToString();
            tb_address.Text = customer.Address;
        }

        private void tb_phone_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (!char.IsControl(e.KeyChar) && !char.IsDigit(e.KeyChar))
            {
                e.Handled = true;
            }
        }

        private void button_save_Click(object sender, EventArgs e)
        {
            Dictionary<string, string> properties = new Dictionary<string, string>
            {
                { "Email", tb_email.Text },
                { "Password", tb_password.Text },
                { "FirstName", tb_firstname.Text },
                { "LastName", tb_lastname.Text },
                { "Phone", tb_phone.Text },
                { "Address", tb_address.Text }
            };

            bool detailsChanged = customer.ChangeAccountDetails(properties);
            if (detailsChanged)
            {
                MessageBox.Show("Success! The details of the customer were changed in the system");
                ReturnToPreviousForm();
            }
        }

        private void button_cancel_Click(object sender, EventArgs e)
        {
            ReturnToPreviousForm();
        }

        private void button_delete_Click(object sender, EventArgs e)
        {
            User.FileManager.DeleteEntity(customer);
            ReturnToPreviousForm();
        }
    }
}
