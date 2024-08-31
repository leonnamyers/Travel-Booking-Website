using System;
using System.Collections;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Booking.com
{
    public partial class EditHotel : Form
    {
        Hotel hotel;
        Admin admin;
        public EditHotel(Hotel hotel, Admin admin)
        {
            InitializeComponent();
            this.hotel = hotel;
            this.admin = admin;
            DisplayHotelDetails();

        }

        private void DisplayHotelDetails()
        {
            tb_name.Text = hotel.Name;
            tb_location.Text = hotel.Location;
            string price = hotel.PricePerNight.ToString();
            tb_price.Text = price;
            string floors = hotel.NumberOfFloors.ToString();
            tb_floors.Text = floors;
            List<int> amenityEnums = hotel.AvailableAmenities.ConvertAll(i => (int)i);
            foreach (int amenityEnum in amenityEnums)
            {
                clb_amenities.SetItemChecked(amenityEnum, true);
            }
        }

        private void ReturnToPreviousForm()
        {
            ViewAllHotels viewHotelsView = new ViewAllHotels(admin);
            viewHotelsView.Show();
            this.Close();
        }

        private void button_save_Click(object sender, EventArgs e)
        {
            Dictionary<string, string> properties = new Dictionary<string, string>
            {
                { "Name", tb_name.Text },
                { "Location", tb_location.Text },
                { "Price", tb_price.Text },
                { "NumberOfFloors", tb_floors.Text }
            };


            // construct a comma separated string with the enum values of the HotelAmenity list that this hotel provides
            // e.g. "0,2" might indicate that the hotel has both a Pool and a Bar
            string amenities = "";
            for (int i = 0; i < clb_amenities.Items.Count; i++)
            {
                if (!clb_amenities.CheckedItems.Contains(clb_amenities.Items[i]))
                {
                    continue;
                }

                if (amenities != "")
                {
                    amenities += ",";
                }
                amenities += i.ToString();
            }
            properties.Add("Amenities", amenities);

            try
            {
                Hotel.FileManager.UpdateDetails(hotel, properties);
                ReturnToPreviousForm();
            }
            catch (Exception ex)
            {
                Debug.WriteLine(ex.ToString());
                // do nothing and let the user try again. detailed error message boxes are already displayed in a deeper level exception.
            }
        }

        private void button_back_Click(object sender, EventArgs e)
        {
            ReturnToPreviousForm();
        }

        private void button_delete_Click(object sender, EventArgs e)
        {
            try
            {
                Hotel.FileManager.DeleteEntity(hotel);
                ReturnToPreviousForm();
            }
            catch
            {
                // no need to do anything here. any MessageBox errors to do with deleting are handled in a deeper level exception. allow the user to try again.
            }

        }
    }
}
