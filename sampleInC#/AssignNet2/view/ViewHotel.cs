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
    public partial class ViewHotel : Form
    {
        Hotel hotel;
        public ViewHotel(Hotel hotel)
        {
            InitializeComponent();
            this.hotel = hotel;
            DisplayHotelDetails();
            FetchWeatherData();
        }

        private void DisplayHotelDetails()
        {
            text_name.Text = hotel.Name;
            text_location.Text = hotel.Location;
            text_pricepernight.Text = hotel.PricePerNight.ToString("C2");
            text_id.Text = hotel.Id.ToString();
            text_floors.Text = hotel.NumberOfFloors.ToString();
            text_amenities.Text = string.Join(", ", hotel.AvailableAmenities);
            weatherLabel.Text += $" {hotel.Location}";
        }

        // Get the 3-hour 5-day weather forecast for the destination
        private async void FetchWeatherData()
        {
            string location = hotel.Location;
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

        private void button_close_Click(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
