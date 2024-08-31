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
    public partial class SelectHotelToBook : Form
    {
        Customer customer;
        public SelectHotelToBook(Customer customer)
        {
            InitializeComponent();
            this.customer = customer;
            listHotels();
        }

        private void ReturnToPreviousForm()
        {
            this.Close();
        }

        private void listHotels()
        {
            List<Hotel> hotels = Hotel.FileManager.DeserializeEntitiesFromFile();

            foreach (Hotel hotel in hotels)
            {
                lb_hotellist.Items.Add(hotel);
            }
        }

        private void button_book_Click(object sender, EventArgs e)
        {
            if (lb_hotellist.SelectedIndex != -1 && lb_hotellist.SelectedItems[0] != null)
            {
                BookHotel bookHotel = new BookHotel(customer, (Hotel)lb_hotellist.SelectedItems[0]);
                bookHotel.Show();
                this.Close();
            }
            else
            {
                MessageBox.Show("Please select a hotel from the list");
            }
        }

        private void button_back_Click(object sender, EventArgs e)
        {
            ReturnToPreviousForm();
        }

        private void button_view_Click(object sender, EventArgs e)
        {
            if (lb_hotellist.SelectedIndex != -1 && lb_hotellist.SelectedItems[0] != null)
            {
                ViewHotel viewHotelDetails = new ViewHotel((Hotel)lb_hotellist.SelectedItems[0]);
                viewHotelDetails.ShowDialog();
            }
            else
            {
                MessageBox.Show("Please select a hotel from the list");
            }
        }

        private void lb_hotellist_SelectedIndexChanged(object sender, EventArgs e)
        {
            button_view.Enabled = true;
            button_book.Enabled = true;
        }
    }
}
