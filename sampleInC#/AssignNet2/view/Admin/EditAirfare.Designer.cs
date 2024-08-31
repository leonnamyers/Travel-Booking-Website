namespace Booking.com
{
    partial class EditAirfare
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
            label_airlinename = new System.Windows.Forms.Label();
            label_location = new System.Windows.Forms.Label();
            label_destination = new System.Windows.Forms.Label();
            label_price = new System.Windows.Forms.Label();
            label_title = new System.Windows.Forms.Label();
            tb_airlinename = new System.Windows.Forms.TextBox();
            tb_location = new System.Windows.Forms.TextBox();
            tb_destination = new System.Windows.Forms.TextBox();
            tb_price = new System.Windows.Forms.TextBox();
            button_addairfare = new System.Windows.Forms.Button();
            button_cancel = new System.Windows.Forms.Button();
            SuspendLayout();
            // 
            // label_airlinename
            // 
            label_airlinename.AutoSize = true;
            label_airlinename.Location = new System.Drawing.Point(164, 212);
            label_airlinename.Margin = new System.Windows.Forms.Padding(6, 0, 6, 0);
            label_airlinename.Name = "label_airlinename";
            label_airlinename.Size = new System.Drawing.Size(61, 25);
            label_airlinename.TabIndex = 0;
            label_airlinename.Text = "Airline";
            // 
            // label_location
            // 
            label_location.AutoSize = true;
            label_location.Location = new System.Drawing.Point(143, 298);
            label_location.Margin = new System.Windows.Forms.Padding(6, 0, 6, 0);
            label_location.Name = "label_location";
            label_location.Size = new System.Drawing.Size(79, 25);
            label_location.TabIndex = 1;
            label_location.Text = "Location";
            // 
            // label_destination
            // 
            label_destination.AutoSize = true;
            label_destination.Location = new System.Drawing.Point(123, 398);
            label_destination.Margin = new System.Windows.Forms.Padding(6, 0, 6, 0);
            label_destination.Name = "label_destination";
            label_destination.Size = new System.Drawing.Size(102, 25);
            label_destination.TabIndex = 2;
            label_destination.Text = "Destination";
            // 
            // label_price
            // 
            label_price.AutoSize = true;
            label_price.Location = new System.Drawing.Point(171, 490);
            label_price.Margin = new System.Windows.Forms.Padding(6, 0, 6, 0);
            label_price.Name = "label_price";
            label_price.Size = new System.Drawing.Size(49, 25);
            label_price.TabIndex = 3;
            label_price.Text = "Price";
            // 
            // label_title
            // 
            label_title.AutoSize = true;
            label_title.Location = new System.Drawing.Point(254, 140);
            label_title.Margin = new System.Windows.Forms.Padding(6, 0, 6, 0);
            label_title.Name = "label_title";
            label_title.Size = new System.Drawing.Size(99, 25);
            label_title.TabIndex = 4;
            label_title.Text = "Edit Airfare";
            // 
            // tb_airlinename
            // 
            tb_airlinename.Location = new System.Drawing.Point(301, 212);
            tb_airlinename.Margin = new System.Windows.Forms.Padding(6, 5, 6, 5);
            tb_airlinename.Name = "tb_airlinename";
            tb_airlinename.Size = new System.Drawing.Size(245, 31);
            tb_airlinename.TabIndex = 1;
            // 
            // tb_location
            // 
            tb_location.Location = new System.Drawing.Point(301, 298);
            tb_location.Margin = new System.Windows.Forms.Padding(6, 5, 6, 5);
            tb_location.Name = "tb_location";
            tb_location.Size = new System.Drawing.Size(245, 31);
            tb_location.TabIndex = 2;
            // 
            // tb_destination
            // 
            tb_destination.Location = new System.Drawing.Point(301, 392);
            tb_destination.Margin = new System.Windows.Forms.Padding(6, 5, 6, 5);
            tb_destination.Name = "tb_destination";
            tb_destination.Size = new System.Drawing.Size(245, 31);
            tb_destination.TabIndex = 3;
            // 
            // tb_price
            // 
            tb_price.Location = new System.Drawing.Point(301, 485);
            tb_price.Margin = new System.Windows.Forms.Padding(6, 5, 6, 5);
            tb_price.Name = "tb_price";
            tb_price.Size = new System.Drawing.Size(245, 31);
            tb_price.TabIndex = 4;
            tb_price.KeyPress += tb_price_KeyPress;
            // 
            // button_addairfare
            // 
            button_addairfare.Location = new System.Drawing.Point(331, 605);
            button_addairfare.Margin = new System.Windows.Forms.Padding(6, 5, 6, 5);
            button_addairfare.Name = "button_addairfare";
            button_addairfare.Size = new System.Drawing.Size(126, 45);
            button_addairfare.TabIndex = 6;
            button_addairfare.Text = "Edit Airfare";
            button_addairfare.UseVisualStyleBackColor = true;
            button_addairfare.Click += button_addairfare_Click;
            // 
            // button_cancel
            // 
            button_cancel.Location = new System.Drawing.Point(177, 605);
            button_cancel.Margin = new System.Windows.Forms.Padding(6, 5, 6, 5);
            button_cancel.Name = "button_cancel";
            button_cancel.Size = new System.Drawing.Size(126, 45);
            button_cancel.TabIndex = 5;
            button_cancel.Text = "Cancel";
            button_cancel.UseVisualStyleBackColor = true;
            button_cancel.Click += button_cancel_Click;
            // 
            // EditAirfare
            // 
            AutoScaleDimensions = new System.Drawing.SizeF(10F, 25F);
            AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            ClientSize = new System.Drawing.Size(679, 802);
            Controls.Add(button_cancel);
            Controls.Add(button_addairfare);
            Controls.Add(tb_price);
            Controls.Add(tb_destination);
            Controls.Add(tb_location);
            Controls.Add(tb_airlinename);
            Controls.Add(label_title);
            Controls.Add(label_price);
            Controls.Add(label_destination);
            Controls.Add(label_location);
            Controls.Add(label_airlinename);
            Margin = new System.Windows.Forms.Padding(6, 5, 6, 5);
            Name = "EditAirfare";
            Text = "Edit Airfare";
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private System.Windows.Forms.Label label_airlinename;
        private System.Windows.Forms.Label label_location;
        private System.Windows.Forms.Label label_destination;
        private System.Windows.Forms.Label label_price;
        private System.Windows.Forms.Label label_title;
        private System.Windows.Forms.TextBox tb_airlinename;
        private System.Windows.Forms.TextBox tb_location;
        private System.Windows.Forms.TextBox tb_destination;
        private System.Windows.Forms.TextBox tb_price;
        private System.Windows.Forms.Button button_addairfare;
        private System.Windows.Forms.Button button_cancel;
    }
}