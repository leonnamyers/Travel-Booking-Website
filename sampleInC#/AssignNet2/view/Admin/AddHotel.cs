using System;
using System.Collections.Generic;
using System.Windows.Forms;

namespace Booking.com
{
    public partial class AddHotel : Form
    {
        Admin admin;
        public AddHotel(Admin admin)
        {
            InitializeComponent();
            this.admin = admin;
        }
        private void ReturnToPreviousForm()
        {
            this.Close();
        }

        private void buttonadd_Click(object sender, EventArgs e)
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
                Hotel.FileManager.AddNewEntity(properties);
                MessageBox.Show("Success! The hotel has been added to the system.");
                ReturnToPreviousForm();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.ToString());
                // no need to do anything here, as a detailed MessageBox error is already shown at a deeper exception level. let the user continue to try add again.
            }
        }

        private void tb_price_keypress(object sender, KeyPressEventArgs e)
        {
            if (!char.IsControl(e.KeyChar) && !char.IsDigit(e.KeyChar) && e.KeyChar != '.')
            {
                e.Handled = true;
            }

            if (e.KeyChar == '.' && (sender as TextBox).Text.Contains('.'))
            {
                e.Handled = true;
            }
        }

        private void tb_floors_KeyPress(object sender, KeyPressEventArgs e)
        {
            if (!char.IsControl(e.KeyChar) && !char.IsDigit(e.KeyChar))
            {
                e.Handled = true;
            }
        }

        private void button_cancel_Click(object sender, EventArgs e)
        {
            ReturnToPreviousForm();
        }
    }
}
