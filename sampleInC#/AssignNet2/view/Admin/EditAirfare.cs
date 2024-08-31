using System;
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
    public partial class EditAirfare : Form
    {

        private Airfare Airfare;
        public EditAirfare(Airfare airfare)
        {
            InitializeComponent();
            Airfare = airfare;
            DisplayAirfareDetails();
        }

        private void DisplayAirfareDetails()
        {
            tb_airlinename.Text = Airfare.Name;
            tb_location.Text = Airfare.Location;
            tb_destination.Text = Airfare.Destination;
            tb_price.Text = Airfare.Price.ToString();
        }

        private void button_addairfare_Click(object sender, EventArgs e)
        {
            Dictionary<string, string> properties = new()
            {
                {"Name", tb_airlinename.Text},
                {"Location", tb_location.Text},
                {"Destination", tb_destination.Text},
                {"Price", tb_price.Text}
            };

            try
            {
                Airfare.FileManager.UpdateDetails(Airfare, properties);
                Close();
            }
            catch (Exception ex)
            {
                Debug.WriteLine(ex);
            }
        }

        private void button_cancel_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void tb_price_KeyPress(object sender, KeyPressEventArgs e)
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
    }
}
