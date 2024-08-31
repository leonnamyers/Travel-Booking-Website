namespace Booking.com
{
    partial class BookHotel
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
            button_cancel = new System.Windows.Forms.Button();
            button_confirm = new System.Windows.Forms.Button();
            dtp_checkin = new System.Windows.Forms.DateTimePicker();
            dtp_checkout = new System.Windows.Forms.DateTimePicker();
            label_checkin = new System.Windows.Forms.Label();
            label_checkout = new System.Windows.Forms.Label();
            text_bookhotel = new System.Windows.Forms.Label();
            label_price = new System.Windows.Forms.Label();
            text_price = new System.Windows.Forms.Label();
            label_nights = new System.Windows.Forms.Label();
            text_nights = new System.Windows.Forms.Label();
            SuspendLayout();
            // 
            // button_cancel
            // 
            button_cancel.Location = new System.Drawing.Point(409, 494);
            button_cancel.Name = "button_cancel";
            button_cancel.Size = new System.Drawing.Size(75, 23);
            button_cancel.TabIndex = 3;
            button_cancel.Text = "Cancel";
            button_cancel.UseVisualStyleBackColor = true;
            button_cancel.Click += button_cancel_Click;
            // 
            // button_confirm
            // 
            button_confirm.Location = new System.Drawing.Point(534, 494);
            button_confirm.Name = "button_confirm";
            button_confirm.Size = new System.Drawing.Size(75, 23);
            button_confirm.TabIndex = 4;
            button_confirm.Text = "Confirm";
            button_confirm.UseVisualStyleBackColor = true;
            button_confirm.Click += button_confirm_Click;
            // 
            // dtp_checkin
            // 
            dtp_checkin.Location = new System.Drawing.Point(409, 290);
            dtp_checkin.Name = "dtp_checkin";
            dtp_checkin.Size = new System.Drawing.Size(200, 23);
            dtp_checkin.TabIndex = 1;
            dtp_checkin.ValueChanged += dtp_checkin_value_changed;
            // 
            // dtp_checkout
            // 
            dtp_checkout.Location = new System.Drawing.Point(409, 344);
            dtp_checkout.Name = "dtp_checkout";
            dtp_checkout.Size = new System.Drawing.Size(200, 23);
            dtp_checkout.TabIndex = 2;
            dtp_checkout.ValueChanged += dtp_checkout_value_changed;
            // 
            // label_checkin
            // 
            label_checkin.AutoSize = true;
            label_checkin.Location = new System.Drawing.Point(330, 296);
            label_checkin.Name = "label_checkin";
            label_checkin.Size = new System.Drawing.Size(55, 15);
            label_checkin.TabIndex = 4;
            label_checkin.Text = "Check-In";
            // 
            // label_checkout
            // 
            label_checkout.AutoSize = true;
            label_checkout.Location = new System.Drawing.Point(323, 340);
            label_checkout.Name = "label_checkout";
            label_checkout.Size = new System.Drawing.Size(65, 15);
            label_checkout.TabIndex = 5;
            label_checkout.Text = "Check-Out";
            // 
            // text_bookhotel
            // 
            text_bookhotel.AutoSize = true;
            text_bookhotel.Location = new System.Drawing.Point(361, 243);
            text_bookhotel.Name = "text_bookhotel";
            text_bookhotel.Size = new System.Drawing.Size(72, 15);
            text_bookhotel.TabIndex = 6;
            text_bookhotel.Text = "Book Hotel :";
            // 
            // label_price
            // 
            label_price.AutoSize = true;
            label_price.Location = new System.Drawing.Point(448, 422);
            label_price.Name = "label_price";
            label_price.Size = new System.Drawing.Size(64, 15);
            label_price.TabIndex = 8;
            label_price.Text = "Total Price:";
            // 
            // text_price
            // 
            text_price.AutoSize = true;
            text_price.Location = new System.Drawing.Point(534, 422);
            text_price.Name = "text_price";
            text_price.Size = new System.Drawing.Size(0, 15);
            text_price.TabIndex = 9;
            // 
            // label_nights
            // 
            label_nights.AutoSize = true;
            label_nights.Location = new System.Drawing.Point(464, 447);
            label_nights.Name = "label_nights";
            label_nights.Size = new System.Drawing.Size(48, 15);
            label_nights.TabIndex = 10;
            label_nights.Text = "Nights :";
            // 
            // text_nights
            // 
            text_nights.AutoSize = true;
            text_nights.Location = new System.Drawing.Point(534, 447);
            text_nights.Name = "text_nights";
            text_nights.Size = new System.Drawing.Size(0, 15);
            text_nights.TabIndex = 11;
            // 
            // BookHotel
            // 
            AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            ClientSize = new System.Drawing.Size(1023, 752);
            Controls.Add(text_nights);
            Controls.Add(label_nights);
            Controls.Add(text_price);
            Controls.Add(label_price);
            Controls.Add(text_bookhotel);
            Controls.Add(label_checkout);
            Controls.Add(label_checkin);
            Controls.Add(dtp_checkout);
            Controls.Add(dtp_checkin);
            Controls.Add(button_confirm);
            Controls.Add(button_cancel);
            Name = "BookHotel";
            Text = "Book A Hotel";
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private System.Windows.Forms.Button button_cancel;
        private System.Windows.Forms.Button button_confirm;
        private System.Windows.Forms.DateTimePicker dtp_checkin;
        private System.Windows.Forms.DateTimePicker dtp_checkout;
        private System.Windows.Forms.Label label_checkin;
        private System.Windows.Forms.Label label_checkout;
        private System.Windows.Forms.Label text_bookhotel;
        private System.Windows.Forms.Label label_price;
        private System.Windows.Forms.Label text_price;
        private System.Windows.Forms.Label label_nights;
        private System.Windows.Forms.Label text_nights;
    }
}