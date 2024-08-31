using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Booking.com.view
{
    public partial class ViewAirfare : Form
    {
        Airfare Airfare;
        public ViewAirfare(Airfare airfare)
        {
            InitializeComponent();
            Airfare = airfare;
            DisplayAirfareProperties();
            FetchWeatherData();
        }

        private void DisplayAirfareProperties()
        {
            tb_name.Text = Airfare.Name;
            tb_location.Text = Airfare.Location;
            tb_destination.Text = Airfare.Destination;
            tb_price.Text = Airfare.Price.ToString();
            tb_id.Text = Airfare.Id.ToString();
            weatherlabel.Text += $" {Airfare.Location}";
        }

        // Get the 3-hour 5-day weather forecast for the destination
        private async void FetchWeatherData()
        {
            string location = Airfare.Destination;
            try
            {
                List<WeatherData> weatherDataList = await WeatherDataAPIService.GetWeatherData(location);
                foreach (WeatherData weatherData in weatherDataList)
                {
                    lb_weather.Items.Add(weatherData);
                }
            }
            catch (Exception)
            {
                MessageBox.Show($"Failed to fetch weather data for the location {location} - City does not exist.");
            }
        }

        private void close_Click(object sender, EventArgs e)
        {
            Close();
        }
    }
}
