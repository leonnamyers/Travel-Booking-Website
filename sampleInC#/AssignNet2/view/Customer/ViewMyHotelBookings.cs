
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Booking.com
{
    public partial class ViewMyHotelBookings : Form
    {
        BookingContext context;
        public ViewMyHotelBookings()
        {
            InitializeComponent();
        }

        protected override void OnLoad(EventArgs e)
        {
            base.OnLoad(e);
            this.context = new BookingContext();
            this.context.Database.EnsureCreated();
            this.context.Bookings.Load();
            this.bookingBindingSource.DataSource = context.Bookings.Local.ToBindingList();
        }

        protected override void OnClosing(CancelEventArgs e)
        {
            base.OnClosing(e);
            this.context?.Dispose();
            this.context = null;
        }

        // testing db
        public void test()
        {
            var email = context.Bookings
                .Where(b => b.BookingId == 1)
                .Select(b => b.Email)
                .FirstOrDefault();

            if (email != null)
            {
                MessageBox.Show(email);
            }
        }

        private void Test_Load(object sender, EventArgs e)
        {

        }
    }
}
