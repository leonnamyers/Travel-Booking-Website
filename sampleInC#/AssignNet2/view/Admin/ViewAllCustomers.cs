using Booking.com.model;
using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace Booking.com
{
    public partial class ViewAllCustomers : Form
    {
        Admin admin;
        public ViewAllCustomers(Admin admin)
        {
            InitializeComponent();
            DisplayCustomers();
            this.admin = admin;
        }

        private void DisplayCustomers()
        {
            List<User> users = User.FileManager.DeserializeEntitiesFromFile();
            foreach (User user in users)
            {
                if (user.Type == UserType.Customer)
                {
                    lb_customers.Items.Add(user);
                }
            }
        }

        private void ReturnToPreviousForm()
        {
            this.Close();
        }

        private void button_viewdetails_Click(object sender, EventArgs e)
        {
            if (lb_customers.SelectedItem != null)
            {
                Customer customer = (Customer)lb_customers.SelectedItem;
                ViewCustomer viewCustomerWindow = new ViewCustomer(customer);
                viewCustomerWindow.ShowDialog();
            }
        }

        private void button_editcustomer_Click(object sender, EventArgs e)
        {
            if (lb_customers.SelectedItem != null)
            {
                Customer customer = (Customer)lb_customers.SelectedItem;
                EditCustomer editCustomerView = new EditCustomer(customer, admin);
                editCustomerView.Show();
                this.Close();
            }
        }

        private void button_close_Click(object sender, EventArgs e)
        {
            ReturnToPreviousForm();
        }

        private void lb_customers_SelectedValueChanged(object sender, EventArgs e)
        {
            button_viewdetails.Enabled = true;
            button_editdeletecustomer.Enabled = true;
        }
    }
}
