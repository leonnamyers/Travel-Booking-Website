namespace Booking.com.view
{
    partial class ViewAirfare
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
            name = new System.Windows.Forms.Label();
            location = new System.Windows.Forms.Label();
            Destination = new System.Windows.Forms.Label();
            price = new System.Windows.Forms.Label();
            id = new System.Windows.Forms.Label();
            tb_name = new System.Windows.Forms.TextBox();
            tb_location = new System.Windows.Forms.TextBox();
            tb_destination = new System.Windows.Forms.TextBox();
            tb_price = new System.Windows.Forms.TextBox();
            tb_id = new System.Windows.Forms.TextBox();
            close = new System.Windows.Forms.Button();
            lb_weather = new System.Windows.Forms.ListBox();
            weatherlabel = new System.Windows.Forms.Label();
            SuspendLayout();
            // 
            // name
            // 
            name.AutoSize = true;
            name.Location = new System.Drawing.Point(74, 81);
            name.Name = "name";
            name.Size = new System.Drawing.Size(130, 25);
            name.TabIndex = 0;
            name.Text = "Airlines Name: ";
            // 
            // location
            // 
            location.AutoSize = true;
            location.Location = new System.Drawing.Point(74, 143);
            location.Name = "location";
            location.Size = new System.Drawing.Size(100, 25);
            location.TabIndex = 1;
            location.Text = "Departure: ";
            // 
            // Destination
            // 
            Destination.AutoSize = true;
            Destination.Location = new System.Drawing.Point(74, 213);
            Destination.Name = "Destination";
            Destination.Size = new System.Drawing.Size(111, 25);
            Destination.TabIndex = 2;
            Destination.Text = "Destination: ";
            // 
            // price
            // 
            price.AutoSize = true;
            price.Location = new System.Drawing.Point(74, 283);
            price.Name = "price";
            price.Size = new System.Drawing.Size(58, 25);
            price.TabIndex = 3;
            price.Text = "Price: ";
            // 
            // id
            // 
            id.AutoSize = true;
            id.Location = new System.Drawing.Point(74, 348);
            id.Name = "id";
            id.Size = new System.Drawing.Size(39, 25);
            id.TabIndex = 4;
            id.Text = "ID: ";
            // 
            // tb_name
            // 
            tb_name.Location = new System.Drawing.Point(281, 81);
            tb_name.Name = "tb_name";
            tb_name.Size = new System.Drawing.Size(150, 31);
            tb_name.TabIndex = 5;
            // 
            // tb_location
            // 
            tb_location.Location = new System.Drawing.Point(281, 143);
            tb_location.Name = "tb_location";
            tb_location.Size = new System.Drawing.Size(150, 31);
            tb_location.TabIndex = 6;
            // 
            // tb_destination
            // 
            tb_destination.Location = new System.Drawing.Point(281, 213);
            tb_destination.Name = "tb_destination";
            tb_destination.Size = new System.Drawing.Size(150, 31);
            tb_destination.TabIndex = 7;
            // 
            // tb_price
            // 
            tb_price.Location = new System.Drawing.Point(281, 283);
            tb_price.Name = "tb_price";
            tb_price.Size = new System.Drawing.Size(150, 31);
            tb_price.TabIndex = 8;
            // 
            // tb_id
            // 
            tb_id.Location = new System.Drawing.Point(281, 348);
            tb_id.Name = "tb_id";
            tb_id.Size = new System.Drawing.Size(150, 31);
            tb_id.TabIndex = 9;
            // 
            // close
            // 
            close.Location = new System.Drawing.Point(1171, 484);
            close.Name = "close";
            close.Size = new System.Drawing.Size(150, 45);
            close.TabIndex = 10;
            close.Text = "Close";
            close.UseVisualStyleBackColor = true;
            close.Click += close_Click;
            // 
            // lb_weather
            // 
            lb_weather.FormattingEnabled = true;
            lb_weather.ItemHeight = 25;
            lb_weather.Location = new System.Drawing.Point(74, 484);
            lb_weather.Name = "lb_weather";
            lb_weather.Size = new System.Drawing.Size(1028, 854);
            lb_weather.TabIndex = 11;
            // 
            // weatherlabel
            // 
            weatherlabel.AutoSize = true;
            weatherlabel.Location = new System.Drawing.Point(74, 430);
            weatherlabel.Name = "weatherlabel";
            weatherlabel.Size = new System.Drawing.Size(181, 25);
            weatherlabel.TabIndex = 12;
            weatherlabel.Text = "Weather Forecast for ";
            // 
            // ViewAirfare
            // 
            AutoScaleDimensions = new System.Drawing.SizeF(10F, 25F);
            AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            ClientSize = new System.Drawing.Size(1448, 1050);
            Controls.Add(weatherlabel);
            Controls.Add(lb_weather);
            Controls.Add(close);
            Controls.Add(tb_id);
            Controls.Add(tb_price);
            Controls.Add(tb_destination);
            Controls.Add(tb_location);
            Controls.Add(tb_name);
            Controls.Add(id);
            Controls.Add(price);
            Controls.Add(Destination);
            Controls.Add(location);
            Controls.Add(name);
            Name = "ViewAirfare";
            Text = "View Airfare";
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private System.Windows.Forms.Label name;
        private System.Windows.Forms.Label location;
        private System.Windows.Forms.Label Destination;
        private System.Windows.Forms.Label price;
        private System.Windows.Forms.Label id;
        private System.Windows.Forms.TextBox tb_name;
        private System.Windows.Forms.TextBox tb_location;
        private System.Windows.Forms.TextBox tb_destination;
        private System.Windows.Forms.TextBox tb_price;
        private System.Windows.Forms.TextBox tb_id;
        private System.Windows.Forms.Button close;
        private System.Windows.Forms.ListBox lb_weather;
        private System.Windows.Forms.Label weatherlabel;
    }
}