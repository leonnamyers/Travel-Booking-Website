using System;

namespace Booking.com
{
    public class Booking
    {
        public int BookingId { get; set; }
        public string Email { get; set; }
        public DateTime CheckIn { get; set; }
        public DateTime CheckOut { get; set; }
        public BookingType BookingType { get; set; }
        public int NumberOfNights { get; set; }
        public double TotalPrice { get; set; }
        public int EntityId { get; set; }
    }
}
