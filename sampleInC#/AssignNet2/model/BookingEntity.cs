using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Booking.com
{
    public abstract class BookingEntity
    {
        public int Id { get; set; }
        public String Name { get; set; }
        public String Location { get; set; }

        public BookingEntity(string name, string location, int id)
        {
            this.Name = name;
            this.Location = location;
            this.Id = id;
        }
    }
}
