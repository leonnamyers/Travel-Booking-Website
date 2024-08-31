using System;

namespace Booking.com {
public class WeatherData
{
    public DateTime Date { get; set; }
    public double Temperature { get; set; }

    public WeatherData(DateTime date, double temperature)
    {
        Date = date;
        Temperature = temperature;
    }

    public override string ToString()
    {
        return $"DateTime: {Date}, Temperature: {Temperature} degrees Celsius";
    }
}

}