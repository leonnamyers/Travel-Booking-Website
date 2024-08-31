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
    public partial class ViewAllHotels : Form
    {
        Admin admin;
        public ViewAllHotels(Admin admin)
        {
            InitializeComponent();
            this.admin = admin;
            AddAllHotelsToListbox();
        }

        private void ReturnToPreviousForm()
        {
            this.Close();
        }

        private void AddAllHotelsToListbox()
        {
            List<Hotel> hotels = Hotel.GetAllHotels();

            foreach (Hotel hotel in hotels)
            {
                lb_hotellist.Items.Add(hotel);
            }
        }

        private void button_cancel_Click(object sender, EventArgs e)
        {
            ReturnToPreviousForm();
        }

        private void button_view_Click(object sender, EventArgs e)
        {
            Hotel hotel = (Hotel)lb_hotellist.SelectedItem;
            if (hotel != null)
            {
                ViewHotel viewHotelDetailsView = new ViewHotel(hotel);
                viewHotelDetailsView.ShowDialog();
            }
        }

        private void button_editdetails_Click(object sender, EventArgs e)
        {
            Hotel hotel = (Hotel)lb_hotellist.SelectedItem;
            if (hotel != null)
            {
                EditHotel editHotelView = new EditHotel(hotel, admin);
                editHotelView.Show();
                this.Close();
            }
        }

        private void lb_hotellist_SelectedValueChanged(object sender, EventArgs e)
        {
            button_view.Enabled = true;
            button_editdetails.Enabled = true;
        }
    }
}
