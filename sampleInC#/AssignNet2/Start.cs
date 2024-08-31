
using Booking.com.model;
using System;
using System.Linq;
using System.Windows.Forms;

namespace Booking.com
{
    public partial class Start : Form
    {
        public Start()
        {
            InitializeComponent();
            BookingManager.CreateBookingsDbContext();
            this.AcceptButton = btn_login;
        }

        private void loginBtn_Click(object sender, EventArgs e)
        {
            string email = tb_email.Text;
            string password = tb_password.Text;

            User user = User.FileManager.GetUserWithLoginDetails(email, password);
            if (user == null)
            {
                tb_password.Text = string.Empty;
                return;
            }
            if (user.Type == UserType.Admin)
            {
                AdminMain adminView = new AdminMain((Admin)user);
                adminView.Show();
                this.Hide();
            }
            else
            {
                CustomerMain customerView = new CustomerMain((Customer)user);
                customerView.Show();
                this.Hide();
            }
        }

        private void registerBtn_click(object sender, EventArgs e)
        {
            Register register = new Register();
            register.ShowDialog();
        }
    }
}
