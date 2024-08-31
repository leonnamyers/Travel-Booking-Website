using Booking.com.controller;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Booking.com
{
    public class Airfare : BookingEntity
    {
        public string Destination {  get; set; }
        public double Price { get; set; }

        public static readonly AirfareFileManager FileManager = new AirfareFileManager();

        public Airfare(string name, string location, int id, string destination, double price) : base(name, location, id) 
        {
            this.Destination = destination;
            this.Price = price;
        }

        public static List<Airfare> GetAllEntities()
        {
            return FileManager.DeserializeEntitiesFromFile();
        }

        public override string ToString()
        {
            return $"{Name}, {Location} to {Destination}";
        }
    }
}
