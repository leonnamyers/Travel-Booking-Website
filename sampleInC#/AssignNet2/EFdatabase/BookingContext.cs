using Microsoft.EntityFrameworkCore;
using System;
using System.IO;

namespace Booking.com
{
    // Using Entity Framework Code First approach to generate the DB tables and store the bookings that were made by customers.
    public class BookingContext : DbContext
    {
        public static readonly string DbFilePath = Path.Combine(Directory.GetParent(Environment.CurrentDirectory).Parent.Parent.FullName, "booking_details.db");

        public DbSet<Booking> Bookings { get; set; }

        public BookingContext() : base(GetOptions())
        {

        }

        private static DbContextOptions<BookingContext> GetOptions()
        {

            return SqliteDbContextOptionsBuilderExtensions.UseSqlite(new DbContextOptionsBuilder<BookingContext>(), $"Data Source={DbFilePath}").Options;
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            modelBuilder.Entity<Booking>(entity =>
            {
                entity.ToTable("Bookings");
                entity.HasKey(b => b.BookingId);
            });

            
            /*modelBuilder.Entity<Booking>().HasData(
                new Booking
                {
                    BookingId = 1,
                    Email = "test@sample.com",
                    CheckIn = new DateTime(2023, 10, 1, 14, 30, 0),
                    CheckOut = new DateTime(2023, 10, 5, 10, 00, 0),
                    BookingType = BookingType.Hotel,
                    CompanyType = 1,
                    EntityId = 1,
                }
            );
            */
            
        }

    }
}
