using Booking.com;
using Booking.com.controller;
using Booking.com.model;
using System.Collections.Generic;

public class Hotel : BookingEntity
{
    public double PricePerNight { get; set; }
    public int NumberOfFloors { get; set; }
    public List<HotelAmenity> AvailableAmenities { get; set; }

    public static readonly HotelFileManager FileManager = new HotelFileManager();

    public Hotel(string name, string location, int id, double pricePerNight, int numberOfFloors, List<HotelAmenity> availableAmenities) : base(name, location, id)
    {
        this.NumberOfFloors = numberOfFloors;
        this.PricePerNight = pricePerNight;
        this.AvailableAmenities = availableAmenities;
    }

    public static List<Hotel> GetAllHotels()
    {
        return FileManager.DeserializeEntitiesFromFile();
    }

    public static string getNameById(int id)
    {
        return FileManager.getNameById(id);
    }

    public override string ToString()
    {
        return Name + ", " + Location;
    }
}

