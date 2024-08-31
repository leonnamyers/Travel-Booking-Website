namespace Booking.com
{
    partial class SelectHotelToBook
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
            label_select = new System.Windows.Forms.Label();
            lb_hotellist = new System.Windows.Forms.ListBox();
            button_book = new System.Windows.Forms.Button();
            button_view = new System.Windows.Forms.Button();
            button_logout = new System.Windows.Forms.Button();
            button_back = new System.Windows.Forms.Button();
            label_title = new System.Windows.Forms.Label();
            SuspendLayout();
            // 
            // label_select
            // 
            label_select.AutoSize = true;
            label_select.Location = new System.Drawing.Point(57, 112);
            label_select.Name = "label_select";
            label_select.Size = new System.Drawing.Size(83, 15);
            label_select.TabIndex = 0;
            label_select.Text = "Select a hotel :";
            // 
            // lb_hotellist
            // 
            lb_hotellist.FormattingEnabled = true;
            lb_hotellist.ItemHeight = 15;
            lb_hotellist.Location = new System.Drawing.Point(57, 141);
            lb_hotellist.Name = "lb_hotellist";
            lb_hotellist.Size = new System.Drawing.Size(292, 319);
            lb_hotellist.TabIndex = 1;
            lb_hotellist.SelectedIndexChanged += lb_hotellist_SelectedIndexChanged;
            // 
            // button_book
            // 
            button_book.Enabled = false;
            button_book.Location = new System.Drawing.Point(391, 141);
            button_book.Name = "button_book";
            button_book.Size = new System.Drawing.Size(118, 23);
            button_book.TabIndex = 2;
            button_book.Text = "Book Hotel";
            button_book.UseVisualStyleBackColor = true;
            button_book.Click += button_book_Click;
            // 
            // button_view
            // 
            button_view.Enabled = false;
            button_view.Location = new System.Drawing.Point(391, 179);
            button_view.Name = "button_view";
            button_view.Size = new System.Drawing.Size(118, 23);
            button_view.TabIndex = 3;
            button_view.Text = "View Details";
            button_view.UseVisualStyleBackColor = true;
            button_view.Click += button_view_Click;
            // 
            // button_logout
            // 
            button_logout.Location = new System.Drawing.Point(483, 12);
            button_logout.Name = "button_logout";
            button_logout.Size = new System.Drawing.Size(75, 23);
            button_logout.TabIndex = 5;
            button_logout.Text = "Logout";
            button_logout.UseVisualStyleBackColor = true;
            // 
            // button_back
            // 
            button_back.Location = new System.Drawing.Point(434, 437);
            button_back.Name = "button_back";
            button_back.Size = new System.Drawing.Size(75, 23);
            button_back.TabIndex = 4;
            button_back.Text = "Back";
            button_back.UseVisualStyleBackColor = true;
            button_back.Click += button_back_Click;
            // 
            // label_title
            // 
            label_title.AutoSize = true;
            label_title.Location = new System.Drawing.Point(219, 29);
            label_title.Name = "label_title";
            label_title.Size = new System.Drawing.Size(120, 15);
            label_title.TabIndex = 6;
            label_title.Text = "View Available Hotels";
            // 
            // SelectHotelToBook
            // 
            AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            ClientSize = new System.Drawing.Size(573, 521);
            Controls.Add(label_title);
            Controls.Add(button_back);
            Controls.Add(button_logout);
            Controls.Add(button_view);
            Controls.Add(button_book);
            Controls.Add(lb_hotellist);
            Controls.Add(label_select);
            Name = "SelectHotelToBook";
            Text = "View Available Hotels";
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private System.Windows.Forms.Label label_select;
        private System.Windows.Forms.ListBox lb_hotellist;
        private System.Windows.Forms.Button button_book;
        private System.Windows.Forms.Button button_view;
        private System.Windows.Forms.Button button_logout;
        private System.Windows.Forms.Button button_back;
        private System.Windows.Forms.Label label_title;
    }
}