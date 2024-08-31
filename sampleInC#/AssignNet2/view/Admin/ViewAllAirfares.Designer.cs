namespace Booking.com
{
    partial class ViewAllAirfares
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
            lb_airfarelist = new System.Windows.Forms.ListBox();
            button_view = new System.Windows.Forms.Button();
            button_editdetails = new System.Windows.Forms.Button();
            button_cancel = new System.Windows.Forms.Button();
            label_title = new System.Windows.Forms.Label();
            SuspendLayout();
            // 
            // lb_airfarelist
            // 
            lb_airfarelist.ColumnWidth = 2;
            lb_airfarelist.FormattingEnabled = true;
            lb_airfarelist.ItemHeight = 25;
            lb_airfarelist.Location = new System.Drawing.Point(54, 142);
            lb_airfarelist.Margin = new System.Windows.Forms.Padding(6, 5, 6, 5);
            lb_airfarelist.Name = "lb_airfarelist";
            lb_airfarelist.Size = new System.Drawing.Size(331, 379);
            lb_airfarelist.TabIndex = 0;
            lb_airfarelist.SelectedValueChanged += lb_airfarelist_SelectedValueChanged;
            // 
            // button_view
            // 
            button_view.Enabled = false;
            button_view.Location = new System.Drawing.Point(451, 142);
            button_view.Margin = new System.Windows.Forms.Padding(6, 5, 6, 5);
            button_view.Name = "button_view";
            button_view.Size = new System.Drawing.Size(126, 45);
            button_view.TabIndex = 1;
            button_view.Text = "View Details";
            button_view.UseVisualStyleBackColor = true;
            button_view.Click += button_view_Click;
            // 
            // button_editdetails
            // 
            button_editdetails.Enabled = false;
            button_editdetails.Location = new System.Drawing.Point(451, 198);
            button_editdetails.Margin = new System.Windows.Forms.Padding(6, 5, 6, 5);
            button_editdetails.Name = "button_editdetails";
            button_editdetails.Size = new System.Drawing.Size(126, 45);
            button_editdetails.TabIndex = 2;
            button_editdetails.Text = "Edit Airfare";
            button_editdetails.UseVisualStyleBackColor = true;
            button_editdetails.Click += button_editdetails_Click;
            // 
            // button_cancel
            // 
            button_cancel.Location = new System.Drawing.Point(451, 500);
            button_cancel.Margin = new System.Windows.Forms.Padding(6, 5, 6, 5);
            button_cancel.Name = "button_cancel";
            button_cancel.Size = new System.Drawing.Size(126, 45);
            button_cancel.TabIndex = 3;
            button_cancel.Text = "Cancel";
            button_cancel.UseVisualStyleBackColor = true;
            button_cancel.Click += button_cancel_Click;
            // 
            // label_title
            // 
            label_title.AutoSize = true;
            label_title.Location = new System.Drawing.Point(54, 73);
            label_title.Margin = new System.Windows.Forms.Padding(6, 0, 6, 0);
            label_title.Name = "label_title";
            label_title.Size = new System.Drawing.Size(139, 25);
            label_title.TabIndex = 4;
            label_title.Text = "View All Airfares";
            // 
            // ViewAllAirfares
            // 
            AutoScaleDimensions = new System.Drawing.SizeF(10F, 25F);
            AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            ClientSize = new System.Drawing.Size(673, 1080);
            Controls.Add(label_title);
            Controls.Add(button_cancel);
            Controls.Add(button_editdetails);
            Controls.Add(button_view);
            Controls.Add(lb_airfarelist);
            Margin = new System.Windows.Forms.Padding(6, 5, 6, 5);
            Name = "ViewAllAirfares";
            Text = "View All Airfares";
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private System.Windows.Forms.ListBox lb_airfarelist;
        private System.Windows.Forms.Button button_view;
        private System.Windows.Forms.Button button_editdetails;
        private System.Windows.Forms.Button button_cancel;
        private System.Windows.Forms.Label label_title;
    }
}