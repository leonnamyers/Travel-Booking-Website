namespace Booking.com
{
    partial class SelectAirfareToBook
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
            lb_airfarelist = new System.Windows.Forms.ListBox();
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
            label_select.Location = new System.Drawing.Point(81, 187);
            label_select.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_select.Name = "label_select";
            label_select.Size = new System.Drawing.Size(143, 25);
            label_select.TabIndex = 0;
            label_select.Text = "Select an Airfare:";
            // 
            // lb_airfarelist
            // 
            lb_airfarelist.FormattingEnabled = true;
            lb_airfarelist.ItemHeight = 25;
            lb_airfarelist.Location = new System.Drawing.Point(81, 235);
            lb_airfarelist.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            lb_airfarelist.Name = "lb_airfarelist";
            lb_airfarelist.Size = new System.Drawing.Size(415, 529);
            lb_airfarelist.TabIndex = 1;
            lb_airfarelist.SelectedValueChanged += lb_airfarelist_SelectedValueChanged;
            // 
            // button_book
            // 
            button_book.Enabled = false;
            button_book.Location = new System.Drawing.Point(559, 235);
            button_book.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            button_book.Name = "button_book";
            button_book.Size = new System.Drawing.Size(169, 38);
            button_book.TabIndex = 2;
            button_book.Text = "Book Airfare";
            button_book.UseVisualStyleBackColor = true;
            button_book.Click += button_book_Click;
            // 
            // button_view
            // 
            button_view.Enabled = false;
            button_view.Location = new System.Drawing.Point(559, 298);
            button_view.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            button_view.Name = "button_view";
            button_view.Size = new System.Drawing.Size(169, 38);
            button_view.TabIndex = 3;
            button_view.Text = "View Details";
            button_view.UseVisualStyleBackColor = true;
            button_view.Click += button_view_Click;
            // 
            // button_logout
            // 
            button_logout.Location = new System.Drawing.Point(690, 20);
            button_logout.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            button_logout.Name = "button_logout";
            button_logout.Size = new System.Drawing.Size(107, 38);
            button_logout.TabIndex = 5;
            button_logout.Text = "Logout";
            button_logout.UseVisualStyleBackColor = true;
            button_logout.Click += button_logout_Click;
            // 
            // button_back
            // 
            button_back.Location = new System.Drawing.Point(620, 728);
            button_back.Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            button_back.Name = "button_back";
            button_back.Size = new System.Drawing.Size(107, 38);
            button_back.TabIndex = 4;
            button_back.Text = "Back";
            button_back.UseVisualStyleBackColor = true;
            button_back.Click += button_back_Click;
            // 
            // label_title
            // 
            label_title.AutoSize = true;
            label_title.Location = new System.Drawing.Point(313, 48);
            label_title.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_title.Name = "label_title";
            label_title.Size = new System.Drawing.Size(190, 25);
            label_title.TabIndex = 6;
            label_title.Text = "View Available Airfares";
            // 
            // SelectAirfareToBook
            // 
            AutoScaleDimensions = new System.Drawing.SizeF(10F, 25F);
            AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            ClientSize = new System.Drawing.Size(819, 868);
            Controls.Add(label_title);
            Controls.Add(button_back);
            Controls.Add(button_logout);
            Controls.Add(button_view);
            Controls.Add(button_book);
            Controls.Add(lb_airfarelist);
            Controls.Add(label_select);
            Margin = new System.Windows.Forms.Padding(4, 5, 4, 5);
            Name = "SelectAirfareToBook";
            Text = "View Available Airfares";
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private System.Windows.Forms.Label label_select;
        private System.Windows.Forms.ListBox lb_airfarelist;
        private System.Windows.Forms.Button button_book;
        private System.Windows.Forms.Button button_view;
        private System.Windows.Forms.Button button_logout;
        private System.Windows.Forms.Button button_back;
        private System.Windows.Forms.Label label_title;
    }
}