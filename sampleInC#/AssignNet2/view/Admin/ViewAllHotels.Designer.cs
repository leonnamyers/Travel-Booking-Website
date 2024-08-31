namespace Booking.com
{
    partial class ViewAllHotels
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
            lb_hotellist = new System.Windows.Forms.ListBox();
            button_view = new System.Windows.Forms.Button();
            button_editdetails = new System.Windows.Forms.Button();
            button_cancel = new System.Windows.Forms.Button();
            label_title = new System.Windows.Forms.Label();
            SuspendLayout();
            // 
            // lb_hotellist
            // 
            lb_hotellist.ColumnWidth = 2;
            lb_hotellist.FormattingEnabled = true;
            lb_hotellist.ItemHeight = 15;
            lb_hotellist.Location = new System.Drawing.Point(38, 85);
            lb_hotellist.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            lb_hotellist.Name = "lb_hotellist";
            lb_hotellist.Size = new System.Drawing.Size(233, 484);
            lb_hotellist.TabIndex = 0;
            lb_hotellist.SelectedValueChanged += lb_hotellist_SelectedValueChanged;
            // 
            // button_view
            // 
            button_view.Enabled = false;
            button_view.Location = new System.Drawing.Point(316, 85);
            button_view.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            button_view.Name = "button_view";
            button_view.Size = new System.Drawing.Size(88, 27);
            button_view.TabIndex = 1;
            button_view.Text = "View Details";
            button_view.UseVisualStyleBackColor = true;
            button_view.Click += button_view_Click;
            // 
            // button_editdetails
            // 
            button_editdetails.Enabled = false;
            button_editdetails.Location = new System.Drawing.Point(316, 119);
            button_editdetails.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            button_editdetails.Name = "button_editdetails";
            button_editdetails.Size = new System.Drawing.Size(88, 27);
            button_editdetails.TabIndex = 2;
            button_editdetails.Text = "Edit Hotel";
            button_editdetails.UseVisualStyleBackColor = true;
            button_editdetails.Click += button_editdetails_Click;
            // 
            // button_cancel
            // 
            button_cancel.Location = new System.Drawing.Point(316, 543);
            button_cancel.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            button_cancel.Name = "button_cancel";
            button_cancel.Size = new System.Drawing.Size(88, 27);
            button_cancel.TabIndex = 3;
            button_cancel.Text = "Cancel";
            button_cancel.UseVisualStyleBackColor = true;
            button_cancel.Click += button_cancel_Click;
            // 
            // label_title
            // 
            label_title.AutoSize = true;
            label_title.Location = new System.Drawing.Point(38, 44);
            label_title.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_title.Name = "label_title";
            label_title.Size = new System.Drawing.Size(86, 15);
            label_title.TabIndex = 4;
            label_title.Text = "View All Hotels";
            // 
            // ViewAllHotels
            // 
            AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            ClientSize = new System.Drawing.Size(471, 648);
            Controls.Add(label_title);
            Controls.Add(button_cancel);
            Controls.Add(button_editdetails);
            Controls.Add(button_view);
            Controls.Add(lb_hotellist);
            Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            Name = "ViewAllHotels";
            Text = "View All Hotels";
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private System.Windows.Forms.ListBox lb_hotellist;
        private System.Windows.Forms.Button button_view;
        private System.Windows.Forms.Button button_editdetails;
        private System.Windows.Forms.Button button_cancel;
        private System.Windows.Forms.Label label_title;
    }
}