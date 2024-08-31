namespace Booking.com
{
    partial class AddHotel
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
            components = new System.ComponentModel.Container();
            tb_name = new System.Windows.Forms.TextBox();
            tb_location = new System.Windows.Forms.TextBox();
            tb_price = new System.Windows.Forms.TextBox();
            label_name = new System.Windows.Forms.Label();
            label_location = new System.Windows.Forms.Label();
            label_pricepernight = new System.Windows.Forms.Label();
            label_title = new System.Windows.Forms.Label();
            button_cancel = new System.Windows.Forms.Button();
            button_add = new System.Windows.Forms.Button();
            tb_floors = new System.Windows.Forms.TextBox();
            label_floors = new System.Windows.Forms.Label();
            errorProvider1 = new System.Windows.Forms.ErrorProvider(components);
            clb_amenities = new System.Windows.Forms.CheckedListBox();
            label_amenities = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)errorProvider1).BeginInit();
            SuspendLayout();
            // 
            // tb_name
            // 
            tb_name.Location = new System.Drawing.Point(610, 195);
            tb_name.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            tb_name.Name = "tb_name";
            tb_name.Size = new System.Drawing.Size(262, 23);
            tb_name.TabIndex = 1;
            // 
            // tb_location
            // 
            tb_location.Location = new System.Drawing.Point(610, 238);
            tb_location.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            tb_location.Name = "tb_location";
            tb_location.Size = new System.Drawing.Size(262, 23);
            tb_location.TabIndex = 2;
            // 
            // tb_price
            // 
            tb_price.Location = new System.Drawing.Point(610, 286);
            tb_price.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            tb_price.Name = "tb_price";
            tb_price.Size = new System.Drawing.Size(262, 23);
            tb_price.TabIndex = 3;
            tb_price.KeyPress += tb_price_keypress;
            // 
            // label_name
            // 
            label_name.AutoSize = true;
            label_name.Location = new System.Drawing.Point(496, 195);
            label_name.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_name.Name = "label_name";
            label_name.Size = new System.Drawing.Size(88, 15);
            label_name.TabIndex = 3;
            label_name.Text = "Name of Hotel:";
            // 
            // label_location
            // 
            label_location.AutoSize = true;
            label_location.Location = new System.Drawing.Point(527, 238);
            label_location.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_location.Name = "label_location";
            label_location.Size = new System.Drawing.Size(56, 15);
            label_location.TabIndex = 4;
            label_location.Text = "Location:";
            // 
            // label_pricepernight
            // 
            label_pricepernight.AutoSize = true;
            label_pricepernight.Location = new System.Drawing.Point(477, 289);
            label_pricepernight.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_pricepernight.Name = "label_pricepernight";
            label_pricepernight.Size = new System.Drawing.Size(106, 15);
            label_pricepernight.TabIndex = 5;
            label_pricepernight.Text = "Price Per Night ($):";
            // 
            // label_title
            // 
            label_title.AutoSize = true;
            label_title.Font = new System.Drawing.Font("Segoe UI", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point);
            label_title.ForeColor = System.Drawing.SystemColors.Desktop;
            label_title.Location = new System.Drawing.Point(653, 154);
            label_title.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_title.Name = "label_title";
            label_title.Size = new System.Drawing.Size(88, 15);
            label_title.TabIndex = 6;
            label_title.Text = "Add New Hotel";
            // 
            // button_cancel
            // 
            button_cancel.Location = new System.Drawing.Point(605, 658);
            button_cancel.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            button_cancel.Name = "button_cancel";
            button_cancel.Size = new System.Drawing.Size(88, 27);
            button_cancel.TabIndex = 8;
            button_cancel.Text = "Cancel";
            button_cancel.UseVisualStyleBackColor = true;
            button_cancel.Click += button_cancel_Click;
            // 
            // button_add
            // 
            button_add.Location = new System.Drawing.Point(748, 658);
            button_add.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            button_add.Name = "button_add";
            button_add.Size = new System.Drawing.Size(88, 27);
            button_add.TabIndex = 9;
            button_add.Text = "Add";
            button_add.UseVisualStyleBackColor = true;
            button_add.Click += buttonadd_Click;
            // 
            // tb_floors
            // 
            tb_floors.Location = new System.Drawing.Point(610, 338);
            tb_floors.Name = "tb_floors";
            tb_floors.Size = new System.Drawing.Size(262, 23);
            tb_floors.TabIndex = 4;
            tb_floors.KeyPress += tb_floors_KeyPress;
            // 
            // label_floors
            // 
            label_floors.AutoSize = true;
            label_floors.Location = new System.Drawing.Point(481, 338);
            label_floors.Name = "label_floors";
            label_floors.Size = new System.Drawing.Size(103, 15);
            label_floors.TabIndex = 10;
            label_floors.Text = "Number of Floors:";
            // 
            // errorProvider1
            // 
            errorProvider1.ContainerControl = this;
            // 
            // clb_amenities
            // 
            clb_amenities.CheckOnClick = true;
            clb_amenities.FormattingEnabled = true;
            clb_amenities.Items.AddRange(new object[] { "Pool", "Restaurant", "Bar", "Executive Lounge", "Rooftop", "Continental Breakfast", "Breakfast Buffet", "Room Service", "Housekeeping", "Parking", "Spa", "Minibar" });
            clb_amenities.Location = new System.Drawing.Point(610, 382);
            clb_amenities.Name = "clb_amenities";
            clb_amenities.Size = new System.Drawing.Size(231, 238);
            clb_amenities.TabIndex = 7;
            // 
            // label_amenities
            // 
            label_amenities.AutoSize = true;
            label_amenities.Location = new System.Drawing.Point(488, 382);
            label_amenities.Name = "label_amenities";
            label_amenities.Size = new System.Drawing.Size(95, 15);
            label_amenities.TabIndex = 13;
            label_amenities.Text = "Hotel Amenities:";
            // 
            // AddHotel
            // 
            AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            ClientSize = new System.Drawing.Size(1265, 881);
            Controls.Add(label_amenities);
            Controls.Add(clb_amenities);
            Controls.Add(label_floors);
            Controls.Add(tb_floors);
            Controls.Add(button_add);
            Controls.Add(button_cancel);
            Controls.Add(label_title);
            Controls.Add(label_pricepernight);
            Controls.Add(label_location);
            Controls.Add(label_name);
            Controls.Add(tb_price);
            Controls.Add(tb_location);
            Controls.Add(tb_name);
            Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            Name = "AddHotel";
            Text = "Add A New Hotel";
            ((System.ComponentModel.ISupportInitialize)errorProvider1).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private System.Windows.Forms.TextBox tb_name;
        private System.Windows.Forms.TextBox tb_location;
        private System.Windows.Forms.TextBox tb_price;
        private System.Windows.Forms.Label label_name;
        private System.Windows.Forms.Label label_location;
        private System.Windows.Forms.Label label_pricepernight;
        private System.Windows.Forms.Label label_title;
        private System.Windows.Forms.Button button_cancel;
        private System.Windows.Forms.Button button_add;
        private System.Windows.Forms.TextBox tb_floors;
        private System.Windows.Forms.Label label_floors;
        private System.Windows.Forms.ErrorProvider errorProvider1;
        private System.Windows.Forms.Label label_amenities;
        private System.Windows.Forms.CheckedListBox clb_amenities;
    }
}