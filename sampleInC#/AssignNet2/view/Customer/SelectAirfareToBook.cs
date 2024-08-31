using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Booking.com.view;

namespace Booking.com
{
    public partial class SelectAirfareToBook : Form
    {
        Customer Customer;
        public SelectAirfareToBook(Customer customer)
        {
            InitializeComponent();
            Customer = customer;
            ListAirfares();
        }

        public void ListAirfares()
        {
            List<Airfare> airfares = Airfare.FileManager.DeserializeEntitiesFromFile();
            foreach (Airfare airfare in airfares)
            {
                lb_airfarelist.Items.Add(airfare);
            }
        }

        private void button_book_Click(object sender, EventArgs e)
        {
            Airfare airfare = lb_airfarelist.SelectedItem as Airfare;
            if (airfare != null)
            {
                MessageBox.Show("Now book the airfare for the customer!");
            }
        }

        private void button_view_Click(object sender, EventArgs e)
        {
            Airfare airfare = lb_airfarelist.SelectedItem as Airfare;
            if (airfare != null)
            {
                ViewAirfare viewAirfare = new(airfare);
                viewAirfare.ShowDialog();
            }
        }

        private void button_back_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void button_logout_Click(object sender, EventArgs e)
        {
        }

        private void lb_airfarelist_SelectedValueChanged(object sender, EventArgs e)
        {
            button_view.Enabled = true;
            button_book.Enabled = true;
        }
    }
}
