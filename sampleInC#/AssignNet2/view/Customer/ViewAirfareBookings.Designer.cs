namespace Booking.com.view.Customer
{
    partial class ViewAirfareBookings
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
            airlinesname = new System.Windows.Forms.DataGridViewTextBoxColumn();
            location = new System.Windows.Forms.DataGridViewTextBoxColumn();
            destination = new System.Windows.Forms.DataGridViewTextBoxColumn();
            price = new System.Windows.Forms.DataGridViewTextBoxColumn();
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
            dtg_bookings.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[] { bookingid, airlinesname, location, destination, price });
            dtg_bookings.Location = new System.Drawing.Point(37, 137);
            dtg_bookings.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            dtg_bookings.Name = "dtg_bookings";
            dtg_bookings.RowHeadersWidth = 62;
            dtg_bookings.RowTemplate.Height = 25;
            dtg_bookings.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            dtg_bookings.Size = new System.Drawing.Size(1061, 772);
            dtg_bookings.TabIndex = 0;
            // 
            // bookingid
            // 
            bookingid.HeaderText = "Booking ID";
            bookingid.MinimumWidth = 8;
            bookingid.Name = "bookingid";
            bookingid.Width = 150;
            // 
            // airlinesname
            // 
            airlinesname.HeaderText = "Airlines Name";
            airlinesname.MinimumWidth = 8;
            airlinesname.Name = "airlinesname";
            airlinesname.Width = 200;
            // 
            // location
            // 
            location.HeaderText = "Departure";
            location.MinimumWidth = 8;
            location.Name = "location";
            location.Width = 150;
            // 
            // destination
            // 
            destination.HeaderText = "Destination";
            destination.MinimumWidth = 8;
            destination.Name = "destination";
            destination.Width = 150;
            // 
            // price
            // 
            price.HeaderText = "Price";
            price.MinimumWidth = 8;
            price.Name = "price";
            price.Width = 150;
            // 
            // label_title
            // 
            label_title.AutoSize = true;
            label_title.Location = new System.Drawing.Point(37, 90);
            label_title.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_title.Name = "label_title";
            label_title.Size = new System.Drawing.Size(130, 25);
            label_title.TabIndex = 1;
            label_title.Text = "Your Bookings:";
            // 
            // button_cancelbooking
            // 
            button_cancelbooking.Location = new System.Drawing.Point(1140, 137);
            button_cancelbooking.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            button_cancelbooking.Name = "button_cancelbooking";
            button_cancelbooking.Size = new System.Drawing.Size(160, 38);
            button_cancelbooking.TabIndex = 2;
            button_cancelbooking.Text = "Cancel Booking";
            button_cancelbooking.UseVisualStyleBackColor = true;
            button_cancelbooking.Click += button_cancelbooking_Click;
            // 
            // button_logout
            // 
            button_logout.Location = new System.Drawing.Point(950, 20);
            button_logout.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            button_logout.Name = "button_logout";
            button_logout.Size = new System.Drawing.Size(107, 38);
            button_logout.TabIndex = 4;
            button_logout.Text = "Logout";
            button_logout.UseVisualStyleBackColor = true;
            button_logout.Click += button_logout_Click;
            // 
            // button_back
            // 
            button_back.Location = new System.Drawing.Point(1140, 870);
            button_back.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            button_back.Name = "button_back";
            button_back.Size = new System.Drawing.Size(107, 38);
            button_back.TabIndex = 3;
            button_back.Text = "Back";
            button_back.UseVisualStyleBackColor = true;
            button_back.Click += button_back_Click;
            // 
            // label_loggedinuser
            // 
            label_loggedinuser.AutoSize = true;
            label_loggedinuser.Location = new System.Drawing.Point(37, 33);
            label_loggedinuser.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_loggedinuser.Name = "label_loggedinuser";
            label_loggedinuser.Size = new System.Drawing.Size(229, 25);
            label_loggedinuser.TabIndex = 5;
            label_loggedinuser.Text = "Logged in user details here.";
            // 
            // ViewAirfareBookings
            // 
            AutoScaleDimensions = new System.Drawing.SizeF(10F, 25F);
            AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            ClientSize = new System.Drawing.Size(1433, 980);
            Controls.Add(label_loggedinuser);
            Controls.Add(button_back);
            Controls.Add(button_logout);
            Controls.Add(button_cancelbooking);
            Controls.Add(label_title);
            Controls.Add(dtg_bookings);
            Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            Name = "ViewAirfareBookings";
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
        private System.Windows.Forms.DataGridViewTextBoxColumn airlinesname;
        private System.Windows.Forms.DataGridViewTextBoxColumn location;
        private System.Windows.Forms.DataGridViewTextBoxColumn destination;
        private System.Windows.Forms.DataGridViewTextBoxColumn price;
        private System.Windows.Forms.Label label_loggedinuser;
    }
}