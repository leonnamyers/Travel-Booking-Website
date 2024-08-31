namespace Booking.com
{
    partial class ViewCustomer
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
            label_name = new System.Windows.Forms.Label();
            label_email = new System.Windows.Forms.Label();
            label_phone = new System.Windows.Forms.Label();
            label_address = new System.Windows.Forms.Label();
            listBox1 = new System.Windows.Forms.ListBox();
            text_name = new System.Windows.Forms.Label();
            text_email = new System.Windows.Forms.Label();
            text_phone = new System.Windows.Forms.Label();
            text_address = new System.Windows.Forms.Label();
            label_title = new System.Windows.Forms.Label();
            label_bookings = new System.Windows.Forms.Label();
            button_close = new System.Windows.Forms.Button();
            SuspendLayout();
            // 
            // label_name
            // 
            label_name.AutoSize = true;
            label_name.Location = new System.Drawing.Point(88, 104);
            label_name.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_name.Name = "label_name";
            label_name.Size = new System.Drawing.Size(42, 15);
            label_name.TabIndex = 0;
            label_name.Text = "Name:";
            // 
            // label_email
            // 
            label_email.AutoSize = true;
            label_email.Location = new System.Drawing.Point(91, 142);
            label_email.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_email.Name = "label_email";
            label_email.Size = new System.Drawing.Size(39, 15);
            label_email.TabIndex = 1;
            label_email.Text = "Email:";
            // 
            // label_phone
            // 
            label_phone.AutoSize = true;
            label_phone.Location = new System.Drawing.Point(84, 178);
            label_phone.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_phone.Name = "label_phone";
            label_phone.Size = new System.Drawing.Size(44, 15);
            label_phone.TabIndex = 2;
            label_phone.Text = "Phone:";
            // 
            // label_address
            // 
            label_address.AutoSize = true;
            label_address.Location = new System.Drawing.Point(76, 215);
            label_address.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_address.Name = "label_address";
            label_address.Size = new System.Drawing.Size(52, 15);
            label_address.TabIndex = 3;
            label_address.Text = "Address:";
            // 
            // listBox1
            // 
            listBox1.FormattingEnabled = true;
            listBox1.ItemHeight = 15;
            listBox1.Location = new System.Drawing.Point(49, 308);
            listBox1.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            listBox1.Name = "listBox1";
            listBox1.Size = new System.Drawing.Size(270, 214);
            listBox1.TabIndex = 1;
            // 
            // text_name
            // 
            text_name.AutoSize = true;
            text_name.Location = new System.Drawing.Point(210, 104);
            text_name.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            text_name.Name = "text_name";
            text_name.Size = new System.Drawing.Size(0, 15);
            text_name.TabIndex = 5;
            // 
            // text_email
            // 
            text_email.AutoSize = true;
            text_email.Location = new System.Drawing.Point(210, 142);
            text_email.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            text_email.Name = "text_email";
            text_email.Size = new System.Drawing.Size(0, 15);
            text_email.TabIndex = 6;
            // 
            // text_phone
            // 
            text_phone.AutoSize = true;
            text_phone.Location = new System.Drawing.Point(210, 178);
            text_phone.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            text_phone.Name = "text_phone";
            text_phone.Size = new System.Drawing.Size(0, 15);
            text_phone.TabIndex = 7;
            // 
            // text_address
            // 
            text_address.AutoSize = true;
            text_address.Location = new System.Drawing.Point(210, 215);
            text_address.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            text_address.Name = "text_address";
            text_address.Size = new System.Drawing.Size(0, 15);
            text_address.TabIndex = 8;
            // 
            // label_title
            // 
            label_title.AutoSize = true;
            label_title.Location = new System.Drawing.Point(127, 53);
            label_title.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_title.Name = "label_title";
            label_title.Size = new System.Drawing.Size(97, 15);
            label_title.TabIndex = 9;
            label_title.Text = "Customer Details";
            // 
            // label_bookings
            // 
            label_bookings.AutoSize = true;
            label_bookings.Location = new System.Drawing.Point(49, 286);
            label_bookings.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_bookings.Name = "label_bookings";
            label_bookings.Size = new System.Drawing.Size(59, 15);
            label_bookings.TabIndex = 10;
            label_bookings.Text = "Bookings:";
            // 
            // button_close
            // 
            button_close.Location = new System.Drawing.Point(133, 549);
            button_close.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            button_close.Name = "button_close";
            button_close.Size = new System.Drawing.Size(88, 27);
            button_close.TabIndex = 2;
            button_close.Text = "Close";
            button_close.UseVisualStyleBackColor = true;
            button_close.Click += button_close_Click;
            // 
            // ViewCustomer
            // 
            AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            ClientSize = new System.Drawing.Size(476, 620);
            Controls.Add(button_close);
            Controls.Add(label_bookings);
            Controls.Add(label_title);
            Controls.Add(text_address);
            Controls.Add(text_phone);
            Controls.Add(text_email);
            Controls.Add(text_name);
            Controls.Add(listBox1);
            Controls.Add(label_address);
            Controls.Add(label_phone);
            Controls.Add(label_email);
            Controls.Add(label_name);
            Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            Name = "ViewCustomer";
            Text = "View Customer Details";
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private System.Windows.Forms.Label label_name;
        private System.Windows.Forms.Label label_email;
        private System.Windows.Forms.Label label_phone;
        private System.Windows.Forms.Label label_address;
        private System.Windows.Forms.ListBox listBox1;
        private System.Windows.Forms.Label text_name;
        private System.Windows.Forms.Label text_email;
        private System.Windows.Forms.Label text_phone;
        private System.Windows.Forms.Label text_address;
        private System.Windows.Forms.Label label_title;
        private System.Windows.Forms.Label label_bookings;
        private System.Windows.Forms.Button button_close;
    }
}