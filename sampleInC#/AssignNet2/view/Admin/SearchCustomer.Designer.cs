namespace Booking.com
{
    partial class SearchCustomer
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
            textBox1 = new System.Windows.Forms.TextBox();
            label_email = new System.Windows.Forms.Label();
            button_search = new System.Windows.Forms.Button();
            button_cancel = new System.Windows.Forms.Button();
            SuspendLayout();
            // 
            // textBox1
            // 
            textBox1.Location = new System.Drawing.Point(152, 73);
            textBox1.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            textBox1.Name = "textBox1";
            textBox1.Size = new System.Drawing.Size(240, 23);
            textBox1.TabIndex = 1;
            // 
            // label_email
            // 
            label_email.AutoSize = true;
            label_email.Location = new System.Drawing.Point(36, 76);
            label_email.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_email.Name = "label_email";
            label_email.Size = new System.Drawing.Size(106, 15);
            label_email.TabIndex = 2;
            label_email.Text = "Customer Email/Id";
            // 
            // button_search
            // 
            button_search.Location = new System.Drawing.Point(261, 134);
            button_search.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            button_search.Name = "button_search";
            button_search.Size = new System.Drawing.Size(88, 27);
            button_search.TabIndex = 3;
            button_search.Text = "Search";
            button_search.UseVisualStyleBackColor = true;
            button_search.Click += button_search_Click;
            // 
            // button_cancel
            // 
            button_cancel.Location = new System.Drawing.Point(152, 134);
            button_cancel.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            button_cancel.Name = "button_cancel";
            button_cancel.Size = new System.Drawing.Size(88, 27);
            button_cancel.TabIndex = 2;
            button_cancel.Text = "Cancel";
            button_cancel.UseVisualStyleBackColor = true;
            // 
            // SearchCustomer
            // 
            AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            ClientSize = new System.Drawing.Size(492, 225);
            Controls.Add(button_cancel);
            Controls.Add(button_search);
            Controls.Add(label_email);
            Controls.Add(textBox1);
            Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            Name = "SearchCustomer";
            Text = "Customer Search";
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private System.Windows.Forms.TextBox textBox1;
        private System.Windows.Forms.Label label_email;
        private System.Windows.Forms.Button button_search;
        private System.Windows.Forms.Button button_cancel;
    }
}