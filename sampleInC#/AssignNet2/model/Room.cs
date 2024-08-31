using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Booking.com.model
{
    internal class Room
    {
        public string Name { get; set; }
        public string Description { get; set; }
        public int Capacity { get; set; }

        public Room(string name, string description, int capacity)
        {
            this.Name = name;
            this.Description = description;
            this.Capacity = capacity;
        }
    }
}
