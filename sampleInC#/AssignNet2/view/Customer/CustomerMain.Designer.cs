namespace Booking.com
{
    partial class CustomerMain
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            button_changecustomerdetails = new System.Windows.Forms.Button();
            button_logout = new System.Windows.Forms.Button();
            button_bookHotel = new System.Windows.Forms.Button();
            button_viewhotelbookings = new System.Windows.Forms.Button();
            label_welcome = new System.Windows.Forms.Label();
            label_loggedinuser = new System.Windows.Forms.Label();
            button_bookAirfare = new System.Windows.Forms.Button();
            button_viewAirfare = new System.Windows.Forms.Button();
            SuspendLayout();
            // 
            // button_changecustomerdetails
            // 
            button_changecustomerdetails.Location = new System.Drawing.Point(689, 20);
            button_changecustomerdetails.Margin = new System.Windows.Forms.Padding(6, 5, 6, 5);
            button_changecustomerdetails.Name = "button_changecustomerdetails";
            button_changecustomerdetails.Size = new System.Drawing.Size(214, 60);
            button_changecustomerdetails.TabIndex = 2;
            button_changecustomerdetails.Text = "Change My Details";
            button_changecustomerdetails.UseVisualStyleBackColor = true;
            button_changecustomerdetails.Click += changedetails_Click;
            // 
            // button_logout
            // 
            button_logout.Location = new System.Drawing.Point(927, 20);
            button_logout.Margin = new System.Windows.Forms.Padding(6, 5, 6, 5);
            button_logout.Name = "button_logout";
            button_logout.Size = new System.Drawing.Size(143, 60);
            button_logout.TabIndex = 3;
            button_logout.Text = "Logout";
            button_logout.UseVisualStyleBackColor = true;
            button_logout.Click += logout_click;
            // 
            // button_bookHotel
            // 
            button_bookHotel.Location = new System.Drawing.Point(114, 470);
            button_bookHotel.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            button_bookHotel.Name = "button_bookHotel";
            button_bookHotel.Size = new System.Drawing.Size(214, 58);
            button_bookHotel.TabIndex = 1;
            button_bookHotel.Text = "Book Hotel";
            button_bookHotel.UseVisualStyleBackColor = true;
            button_bookHotel.Click += button_bookHotel_Click;
            // 
            // button_viewhotelbookings
            // 
            button_viewhotelbookings.Location = new System.Drawing.Point(361, 470);
            button_viewhotelbookings.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            button_viewhotelbookings.Name = "button_viewhotelbookings";
            button_viewhotelbookings.Size = new System.Drawing.Size(214, 58);
            button_viewhotelbookings.TabIndex = 4;
            button_viewhotelbookings.Text = "View My Hotel Bookings";
            button_viewhotelbookings.UseVisualStyleBackColor = true;
            button_viewhotelbookings.Click += button_viewhotelbookings_Click;
            // 
            // label_welcome
            // 
            label_welcome.AutoSize = true;
            label_welcome.Location = new System.Drawing.Point(114, 400);
            label_welcome.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_welcome.Name = "label_welcome";
            label_welcome.Size = new System.Drawing.Size(206, 25);
            label_welcome.TabIndex = 5;
            label_welcome.Text = "Welcome text goes here.";
            // 
            // label_loggedinuser
            // 
            label_loggedinuser.AutoSize = true;
            label_loggedinuser.Location = new System.Drawing.Point(39, 38);
            label_loggedinuser.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_loggedinuser.Name = "label_loggedinuser";
            label_loggedinuser.Size = new System.Drawing.Size(209, 25);
            label_loggedinuser.TabIndex = 6;
            label_loggedinuser.Text = "Logged in user info here.";
            // 
            // button_bookAirfare
            // 
            button_bookAirfare.Location = new System.Drawing.Point(114, 536);
            button_bookAirfare.Name = "button_bookAirfare";
            button_bookAirfare.Size = new System.Drawing.Size(214, 58);
            button_bookAirfare.TabIndex = 7;
            button_bookAirfare.Text = "Book Airfare";
            button_bookAirfare.UseVisualStyleBackColor = true;
            button_bookAirfare.Click += button_bookAirfare_Click;
            // 
            // button_viewAirfare
            // 
            button_viewAirfare.Location = new System.Drawing.Point(361, 536);
            button_viewAirfare.Name = "button_viewAirfare";
            button_viewAirfare.Size = new System.Drawing.Size(214, 58);
            button_viewAirfare.TabIndex = 8;
            button_viewAirfare.Text = "View Airfare Bookings";
            button_viewAirfare.UseVisualStyleBackColor = true;
            button_viewAirfare.Click += button_viewAirfare_Click;
            // 
            // CustomerMain
            // 
            AutoScaleDimensions = new System.Drawing.SizeF(10F, 25F);
            AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            ClientSize = new System.Drawing.Size(1089, 810);
            Controls.Add(button_viewAirfare);
            Controls.Add(button_bookAirfare);
            Controls.Add(label_loggedinuser);
            Controls.Add(label_welcome);
            Controls.Add(button_viewhotelbookings);
            Controls.Add(button_bookHotel);
            Controls.Add(button_logout);
            Controls.Add(button_changecustomerdetails);
            Margin = new System.Windows.Forms.Padding(6, 5, 6, 5);
            Name = "CustomerMain";
            Text = "Customer";
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private System.Windows.Forms.Button button_changecustomerdetails;
        private System.Windows.Forms.Button button_logout;
        private System.Windows.Forms.Button button_bookHotel;
        private System.Windows.Forms.Button button_viewhotelbookings;
        private System.Windows.Forms.Label label_welcome;
        private System.Windows.Forms.Label label_loggedinuser;
        private System.Windows.Forms.Button button_bookAirfare;
        private System.Windows.Forms.Button button_viewAirfare;
    }
}