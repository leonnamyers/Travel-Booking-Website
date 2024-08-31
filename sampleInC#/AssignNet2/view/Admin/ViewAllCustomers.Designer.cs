namespace Booking.com
{
    partial class ViewAllCustomers
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
            label_title = new System.Windows.Forms.Label();
            lb_customers = new System.Windows.Forms.ListBox();
            button_viewdetails = new System.Windows.Forms.Button();
            button_editdeletecustomer = new System.Windows.Forms.Button();
            button_close = new System.Windows.Forms.Button();
            SuspendLayout();
            // 
            // label_title
            // 
            label_title.AutoSize = true;
            label_title.Location = new System.Drawing.Point(146, 25);
            label_title.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_title.Name = "label_title";
            label_title.Size = new System.Drawing.Size(109, 15);
            label_title.TabIndex = 0;
            label_title.Text = "View All Customers";
            // 
            // lb_customers
            // 
            lb_customers.FormattingEnabled = true;
            lb_customers.ItemHeight = 15;
            lb_customers.Location = new System.Drawing.Point(64, 62);
            lb_customers.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            lb_customers.Name = "lb_customers";
            lb_customers.Size = new System.Drawing.Size(293, 364);
            lb_customers.TabIndex = 1;
            lb_customers.SelectedValueChanged += lb_customers_SelectedValueChanged;
            // 
            // button_viewdetails
            // 
            button_viewdetails.Enabled = false;
            button_viewdetails.Location = new System.Drawing.Point(413, 62);
            button_viewdetails.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            button_viewdetails.Name = "button_viewdetails";
            button_viewdetails.Size = new System.Drawing.Size(149, 27);
            button_viewdetails.TabIndex = 2;
            button_viewdetails.Text = "View Details";
            button_viewdetails.UseVisualStyleBackColor = true;
            button_viewdetails.Click += button_viewdetails_Click;
            // 
            // button_editdeletecustomer
            // 
            button_editdeletecustomer.Enabled = false;
            button_editdeletecustomer.Location = new System.Drawing.Point(413, 106);
            button_editdeletecustomer.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            button_editdeletecustomer.Name = "button_editdeletecustomer";
            button_editdeletecustomer.Size = new System.Drawing.Size(149, 27);
            button_editdeletecustomer.TabIndex = 3;
            button_editdeletecustomer.Text = "Edit Customer";
            button_editdeletecustomer.UseVisualStyleBackColor = true;
            button_editdeletecustomer.Click += button_editcustomer_Click;
            // 
            // button_close
            // 
            button_close.Location = new System.Drawing.Point(413, 400);
            button_close.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            button_close.Name = "button_close";
            button_close.Size = new System.Drawing.Size(149, 27);
            button_close.TabIndex = 4;
            button_close.Text = "Close";
            button_close.UseVisualStyleBackColor = true;
            button_close.Click += button_close_Click;
            // 
            // ViewAllCustomers
            // 
            AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            ClientSize = new System.Drawing.Size(612, 458);
            Controls.Add(button_close);
            Controls.Add(button_editdeletecustomer);
            Controls.Add(button_viewdetails);
            Controls.Add(lb_customers);
            Controls.Add(label_title);
            Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            Name = "ViewAllCustomers";
            Text = "View All Customers";
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private System.Windows.Forms.Label label_title;
        private System.Windows.Forms.ListBox lb_customers;
        private System.Windows.Forms.Button button_viewdetails;
        private System.Windows.Forms.Button button_editdeletecustomer;
        private System.Windows.Forms.Button button_close;
    }
}