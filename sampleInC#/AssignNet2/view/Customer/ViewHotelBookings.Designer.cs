namespace Booking.com
{
    partial class ViewHotelBookings
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
            dtg_bookings = new System.Windows.Forms.DataGridView();
            bookingid = new System.Windows.Forms.DataGridViewTextBoxColumn();
            hotelname = new System.Windows.Forms.DataGridViewTextBoxColumn();
            checkin = new System.Windows.Forms.DataGridViewTextBoxColumn();
            checkout = new System.Windows.Forms.DataGridViewTextBoxColumn();
            nights = new System.Windows.Forms.DataGridViewTextBoxColumn();
            totalprice = new System.Windows.Forms.DataGridViewTextBoxColumn();
            label_title = new System.Windows.Forms.Label();
            button_cancelbooking = new System.Windows.Forms.Button();
            button_logout = new System.Windows.Forms.Button();
            button_back = new System.Windows.Forms.Button();
            label_loggedinuser = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)dtg_bookings).BeginInit();
            SuspendLayout();
            // 
            // dtg_bookings
            // 
            dtg_bookings.AllowUserToAddRows = false;
            dtg_bookings.AllowUserToDeleteRows = false;
            dtg_bookings.AllowUserToResizeColumns = false;
            dtg_bookings.AllowUserToResizeRows = false;
            dtg_bookings.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dtg_bookings.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] { bookingid, hotelname, checkin, checkout, nights, totalprice });
            dtg_bookings.Location = new System.Drawing.Point(26, 82);
            dtg_bookings.Name = "dtg_bookings";
            dtg_bookings.RowTemplate.Height = 25;
            dtg_bookings.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            dtg_bookings.Size = new System.Drawing.Size(743, 463);
            dtg_bookings.TabIndex = 0;
            // 
            // bookingid
            // 
            bookingid.HeaderText = "Booking ID";
            bookingid.Name = "bookingid";
            // 
            // hotelname
            // 
            hotelname.HeaderText = "Hotel Name";
            hotelname.Name = "hotelname";
            hotelname.Width = 200;
            // 
            // checkin
            // 
            checkin.HeaderText = "Check In Date";
            checkin.Name = "checkin";
            // 
            // checkout
            // 
            checkout.HeaderText = "Check Out Date";
            checkout.Name = "checkout";
            // 
            // nights
            // 
            nights.HeaderText = "Number Of Nights";
            nights.Name = "nights";
            // 
            // totalprice
            // 
            totalprice.HeaderText = "Total Price";
            totalprice.Name = "totalprice";
            // 
            // label_title
            // 
            label_title.AutoSize = true;
            label_title.Location = new System.Drawing.Point(26, 54);
            label_title.Name = "label_title";
            label_title.Size = new System.Drawing.Size(86, 15);
            label_title.TabIndex = 1;
            label_title.Text = "Your Bookings:";
            // 
            // button_cancelbooking
            // 
            button_cancelbooking.Location = new System.Drawing.Point(798, 82);
            button_cancelbooking.Name = "button_cancelbooking";
            button_cancelbooking.Size = new System.Drawing.Size(112, 23);
            button_cancelbooking.TabIndex = 2;
            button_cancelbooking.Text = "Cancel Booking";
            button_cancelbooking.UseVisualStyleBackColor = true;
            button_cancelbooking.Click += button_cancelbooking_Click;
            // 
            // button_logout
            // 
            button_logout.Location = new System.Drawing.Point(665, 12);
            button_logout.Name = "button_logout";
            button_logout.Size = new System.Drawing.Size(75, 23);
            button_logout.TabIndex = 4;
            button_logout.Text = "Logout";
            button_logout.UseVisualStyleBackColor = true;
            button_logout.Click += button_logout_Click;
            // 
            // button_back
            // 
            button_back.Location = new System.Drawing.Point(798, 522);
            button_back.Name = "button_back";
            button_back.Size = new System.Drawing.Size(75, 23);
            button_back.TabIndex = 3;
            button_back.Text = "Back";
            button_back.UseVisualStyleBackColor = true;
            button_back.Click += button_back_Click;
            // 
            // label_loggedinuser
            // 
            label_loggedinuser.AutoSize = true;
            label_loggedinuser.Location = new System.Drawing.Point(26, 20);
            label_loggedinuser.Name = "label_loggedinuser";
            label_loggedinuser.Size = new System.Drawing.Size(151, 15);
            label_loggedinuser.TabIndex = 5;
            label_loggedinuser.Text = "Logged in user details here.";
            // 
            // ViewHotelBookings
            // 
            AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            ClientSize = new System.Drawing.Size(1003, 588);
            Controls.Add(label_loggedinuser);
            Controls.Add(button_back);
            Controls.Add(button_logout);
            Controls.Add(button_cancelbooking);
            Controls.Add(label_title);
            Controls.Add(dtg_bookings);
            Name = "ViewHotelBookings";
            Text = "View All Bookings";
            ((System.ComponentModel.ISupportInitialize)dtg_bookings).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private System.Windows.Forms.DataGridView dtg_bookings;
        private System.Windows.Forms.Label label_title;
        private System.Windows.Forms.Button button_view;
        private System.Windows.Forms.Button button_cancelbooking;
        private System.Windows.Forms.Button button_logout;
        private System.Windows.Forms.Button button_back;
        private System.Windows.Forms.DataGridViewTextBoxColumn bookingid;
        private System.Windows.Forms.DataGridViewTextBoxColumn hotelname;
        private System.Windows.Forms.DataGridViewTextBoxColumn checkin;
        private System.Windows.Forms.DataGridViewTextBoxColumn checkout;
        private System.Windows.Forms.DataGridViewTextBoxColumn nights;
        private System.Windows.Forms.DataGridViewTextBoxColumn totalprice;
        private System.Windows.Forms.Label label_loggedinuser;
    }
}