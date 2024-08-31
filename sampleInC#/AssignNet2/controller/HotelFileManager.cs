using System;
using System.Collections.Generic;
using System.IO;
using System.Text.Json;
using System.Text.Json.Serialization;
using System.Windows.Forms;
using Booking.com.model;
using System.Linq;
using System.Diagnostics;

namespace Booking.com.controller
{
    using exceptions;
    using validation;

    public class HotelFileManager : IFileManager<Hotel>
    {
        public static readonly string FilePath = Path.Combine(Directory.GetParent(Environment.CurrentDirectory).Parent.Parent.FullName, "hotel_details.txt");
        private List<Hotel> hotelList = new List<Hotel>();

        public HotelFileManager()
        {
            DeserializeEntitiesFromFile();
        }

        public List<Hotel> DeserializeEntitiesFromFile()
        {
            string hotelData = File.ReadAllText(FilePath);

            JsonSerializerOptions options = new JsonSerializerOptions
            {
                WriteIndented = true,
                Converters = { new HotelConverter() },
            };
            try
            {
                hotelList = JsonSerializer.Deserialize<List<Hotel>>(hotelData, options);
                return hotelList;
            }
            catch (JsonException ex)
            {
                Debug.WriteLine("Json Deserialization Error:", ex);
            }
            return null;
        }

        public Hotel SearchBy(string nameLocation)
        {
            hotelList = DeserializeEntitiesFromFile();
            string[] details = nameLocation.Split(new string[] { ", " }, StringSplitOptions.None);

            foreach (Hotel hotel in hotelList)
            {
                if (hotel.Name.ToLower() == details[0].ToLower())
                {
                    if (hotel.Location.ToLower() == details[1].ToLower())
                    {
                        return hotel;
                    }
                }
            }
            return null;
        }

        public Hotel SearchBy(string name, string location)
        {
            hotelList = DeserializeEntitiesFromFile();
            foreach (Hotel hotel in hotelList)
            {
                if (hotel.Name.ToLower() == name.ToLower())
                {
                    if (location.ToLower() == location.ToLower())
                    {
                        return hotel;
                    }
                }
            }
            return null;
        }

        public string getNameById(int id)
        {
            Hotel matchingHotel = hotelList.Find(hotel => hotel.Id == id);
            return matchingHotel.Name;
        }

        public void AddNewEntity(Hotel hotel)
        {
            hotelList = DeserializeEntitiesFromFile();
            hotelList.Add(hotel);
            SerializeEntitiesToFile();
        }

        public void AddNewEntity(Dictionary<string, string> hotelProperties)
        {
            bool areHotelInputsValid = HotelFormValidation.AreHotelInputsValid(hotelProperties);
            if (!areHotelInputsValid)
            {
                throw new InvalidInputException();
            }

            double price;
            try
            {
                price = Convert.ToDouble(hotelProperties["Price"]);
            }
            catch (FormatException)
            {
                MessageBox.Show("Error: Invalid Price Format");
                return;
            }
            catch (OverflowException)
            {
                MessageBox.Show("Error: Price too large");
                return;
            }
            int numberOfFloors;
            try
            {
                numberOfFloors = Convert.ToInt32(hotelProperties["NumberOfFloors"]);
            }
            catch (FormatException)
            {
                MessageBox.Show("Error: Invalid Number Of Floors Format");
                return;
            }
            catch (OverflowException)
            {
                MessageBox.Show("Error: Number Of Floors Too Large");
                return;
            }

            List<string> amenityEnums = hotelProperties["Amenities"].Split(',').ToList();
            List<HotelAmenity> availableAmenities = new List<HotelAmenity>();
            foreach (string amenityEnum in amenityEnums)
            {
                availableAmenities.Add(Enum.Parse<HotelAmenity>(amenityEnum));
            }
            Hotel hotel = new Hotel(hotelProperties["Name"], hotelProperties["Location"], GenerateNewId(), price, numberOfFloors, availableAmenities);
            AddNewEntity(hotel);
        }

        public void UpdateDetails(Hotel hotel, Dictionary<string, string> hotelProperties)
        {
            bool areHotelInputsValid = HotelFormValidation.AreHotelInputsValid(hotelProperties);
            if (!areHotelInputsValid)
            {
                throw new InvalidUpdateException();
            }

            hotelList = DeserializeEntitiesFromFile();
            int index = hotelList.FindIndex(res => res.Id == hotel.Id);
            if (index == -1)
            {
                string errorMessage = "Error: Invalid PricePerNight Format";
                MessageBox.Show(errorMessage);
                throw new InvalidUpdateException(errorMessage);
            }
            else
            {
                try
                {
                    hotelList[index].PricePerNight = double.Parse(hotelProperties["Price"]);
                }
                catch (FormatException)
                {
                    string errorMessage = "Erorr: Invalid Price Per Night Format";
                    MessageBox.Show(errorMessage);
                    throw new InvalidUpdateException(errorMessage);
                }
                catch (OverflowException)
                {
                    string errorMessage = "Error: Price Per Night too large";
                    MessageBox.Show(errorMessage);
                    throw new InvalidUpdateException(errorMessage);
                }
                try
                {
                    hotelList[index].NumberOfFloors = int.Parse(hotelProperties["NumberOfFloors"]);
                }
                catch (FormatException)
                {
                    string errorMessage = "Erorr: Invalid Number Of Floors Format";
                    MessageBox.Show(errorMessage);
                    throw new InvalidUpdateException(errorMessage);
                }
                catch (OverflowException)
                {
                    string errorMessage = "Error: Number Of Floors too large";
                    MessageBox.Show(errorMessage);
                    throw new InvalidUpdateException(errorMessage);
                }
                hotelList[index].Name = hotelProperties["Name"];
                hotelList[index].Location = hotelProperties["Location"];
                List<string> amenityEnums = hotelProperties["Amenities"].Split(',').ToList();
                List<HotelAmenity> availableAmenities = new List<HotelAmenity>();
                foreach (string amenityEnum in amenityEnums)
                {
                    availableAmenities.Add(Enum.Parse<HotelAmenity>(amenityEnum));
                }
                hotelList[index].AvailableAmenities = availableAmenities;
                SerializeEntitiesToFile();
                MessageBox.Show("Success! The hotel details were successfully changed");
            }
        }

        public void DeleteEntity(Hotel hotel)
        {
            hotelList = DeserializeEntitiesFromFile();
            int index = hotelList.FindIndex(x => x.Name == hotel.Name && x.Location == hotel.Location);
            if (index == -1)
            {
                string errorMessage = "Error: Hotel not found in file";
                MessageBox.Show(errorMessage);
                throw new InvalidDeletionException(errorMessage);
            }
            else
            {
                try
                {
                    hotelList.RemoveAt(index);
                    SerializeEntitiesToFile();
                    MessageBox.Show("Sucesss! The hotel has been deleted");
                }
                catch (Exception ex)
                {
                    MessageBox.Show($"An error occurred while trying to delete the hotel from the file: {ex.Message}");
                    throw new InvalidDeletionException(ex.Message, ex);
                }
            }
        }

        public void SerializeEntitiesToFile()
        {
            JsonSerializerOptions options = new JsonSerializerOptions
            {
                WriteIndented = true,
                Converters = { new HotelConverter() },
            };
            string writeHotels = JsonSerializer.Serialize(hotelList, options);
            File.WriteAllText(FilePath, writeHotels);
        }

        private int GenerateNewId()
        {
            while (true)
            {
                Random random = new Random();
                int minDigits = 2;
                int maxDigits = 8;
                int minValue = (int)Math.Pow(10, minDigits - 1);
                int maxValue = (int)Math.Pow(10, maxDigits) - 1;
                int id = random.Next(minValue, maxValue + 1);
                // id cannot Start with a 0
                while (id.ToString()[0] == '0')
                {
                    id = random.Next(minValue, maxValue + 1);
                }

                if (!IsDuplicateID(id))
                {
                    return id;
                }
            }
        }

        private bool IsDuplicateID(int id)
        {
            foreach (Hotel hotel in DeserializeEntitiesFromFile())
            {
                if (hotel.Id == id)
                {
                    return true;
                }
            }
            return false;
        }
    }


    public class HotelConverter : JsonConverter<Hotel>
    {
        public override Hotel Read(ref Utf8JsonReader reader, Type typeToConvert, JsonSerializerOptions options)
        {
            int id = 0;
            string name = null;
            string location = null;
            double pricePerNight = 0;
            int numberOfFloors = 0;
            List<HotelAmenity> amenities = new List<HotelAmenity>();

            while (reader.Read())
            {
                if (reader.TokenType == JsonTokenType.EndObject)
                {
                    return new Hotel(name, location, id, pricePerNight, numberOfFloors, amenities);
                }

                if (reader.TokenType != JsonTokenType.PropertyName)
                {
                    throw new JsonException($"Expected PropertyName but got {reader.TokenType}");
                }

                string propertyName = reader.GetString();
                reader.Read();

                switch (propertyName)
                {
                    case "Id":
                        id = reader.GetInt32();
                        break;
                    case "Name":
                        name = reader.GetString();
                        break;
                    case "Location":
                        location = reader.GetString();
                        break;
                    case "PricePerNight":
                        pricePerNight = reader.GetDouble();
                        break;
                    case "NumberOfFloors":
                        numberOfFloors = reader.GetInt32();
                        break;
                    case "Amenities":
                        foreach (string amenityEnum in reader.GetString().Split(',').ToList())
                        {
                            amenities.Add((HotelAmenity)int.Parse(amenityEnum));
                        }
                        break;
                    default:
                        throw new JsonException($"Unrecognized property: {propertyName}");
                }
            }

            throw new JsonException("Unexpected end of JSON data");
        }
        public override void Write(Utf8JsonWriter writer, Hotel value, JsonSerializerOptions options)
        {
            writer.WriteStartObject();
            writer.WriteNumber("Id", value.Id);
            writer.WriteString("Name", value.Name);
            writer.WriteString("Location", value.Location);
            writer.WriteNumber("PricePerNight", value.PricePerNight);
            writer.WriteNumber("NumberOfFloors", value.NumberOfFloors);
            writer.WriteString("Amenities", string.Join(',', value.AvailableAmenities.ConvertAll(i => (int)i)));
            writer.WriteEndObject();
        }
    }
}




