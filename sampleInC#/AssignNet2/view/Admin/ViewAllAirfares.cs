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
    public partial class ViewAllAirfares : Form
    {
        public ViewAllAirfares()
        {
            InitializeComponent();
            AddAllAirfaresToListBox();
        }

        private void AddAllAirfaresToListBox()
        {
            List<Airfare> airfares = Airfare.GetAllEntities();
            foreach (Airfare airfare in airfares)
            {
                lb_airfarelist.Items.Add(airfare);
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

        private void button_editdetails_Click(object sender, EventArgs e)
        {
            Airfare airfare = lb_airfarelist.SelectedItem as Airfare;
            if (airfare != null)
            {
                EditAirfare editAirfareView = new EditAirfare(airfare);
                editAirfareView.ShowDialog();
            }
        }

        private void button_cancel_Click(object sender, EventArgs e)
        {
            Close();
        }

        private void lb_airfarelist_SelectedValueChanged(object sender, EventArgs e)
        {
            button_view.Enabled = true;
            button_editdetails.Enabled = true;
        }
    }
}
