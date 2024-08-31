using System;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using System.Collections.Generic;
using System.Net.Http;
using System.Threading.Tasks;
using Booking.com;

public static class WeatherDataAPIService
{
    private static readonly HttpClient client = new HttpClient();

    public static async Task<List<WeatherData>> GetWeatherData(string location)
    {
        string apiKey = "6676e8d993612289e87fd993b451c49c";
        string url = $"https://api.openweathermap.org/data/2.5/forecast?q={location}&units=metric&appid={apiKey}";

        // Send a request to the URL
        HttpResponseMessage response = await client.GetAsync(url);
        response.EnsureSuccessStatusCode();

        // Get the JSON response and parse it
        string responseBody = await response.Content.ReadAsStringAsync();
        JObject parsedJson = JObject.Parse(responseBody);
        JArray weatherArray = (JArray)parsedJson["list"];

        var weatherDataList = new List<WeatherData>();

        foreach (var item in weatherArray)
        {
            long timestamp = (long)item["dt"];
            DateTime date = DateTimeOffset.FromUnixTimeSeconds(timestamp).DateTime;
            double temperature = (double)item["main"]["temp"];

            var weatherData = new WeatherData(date, temperature);
            weatherDataList.Add(weatherData);
        }

        return weatherDataList;
    }
}
