using Booking.com.controller.validation;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections;
using System.IO;
using System.Linq;
using System.Windows.Forms;

namespace Booking.com
{
    public class BookingManager
    {
        public static BookingContext context;

        public static void CreateBookingsDbContext()
        {
            if (context == null)
            {
                context = new BookingContext();
                context.Database.EnsureCreated();
                context.Bookings.Load();
            }
        }

        public static bool CreateHotelBooking(Hashtable properties)
        {
            bool validInputs = HotelFormValidation.IsHotelBookingValid(properties);
            if (validInputs)
            {
                Booking booking = new Booking
                {
                    Email = (string)properties["Email"],
                    CheckIn = (DateTime)properties["CheckIn"],
                    CheckOut = (DateTime)properties["CheckOut"],
                    BookingType = (BookingType)properties["BookingType"],
                    EntityId = (int)properties["EntityId"],
                    NumberOfNights = (int)properties["NumberOfNights"],
                    TotalPrice = (double)properties["TotalPrice"]
                };
                context.Bookings.Add(booking);
                context.SaveChanges();
                return true;
            }
            return false;
        }

        public static int CalculateNumberOfNights(DateTime checkIn, DateTime checkOut)
        {
            int numberOfNights = (int)Math.Ceiling((checkOut - checkIn).TotalDays);
            return numberOfNights;
        }

        public static void DeleteBooking(int bookingId)
        {
            var bookingToRemove = context.Bookings.Where(b => b.BookingId == bookingId).FirstOrDefault();
            if (bookingToRemove != null)
            {
                context.Bookings.Remove(bookingToRemove);
                context.SaveChanges();
                MessageBox.Show("Your booking was successfully cancelled.", "Cancellation Confirmation", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
        }

        public static void DeleteAllBookings()
        {
            context.Bookings.RemoveRange(context.Bookings);
            context.SaveChanges();
        }
    }
}
