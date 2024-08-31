namespace Booking.com
{
    partial class EditCustomer
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
            tb_firstname = new System.Windows.Forms.TextBox();
            tb_lastname = new System.Windows.Forms.TextBox();
            tb_email = new System.Windows.Forms.TextBox();
            tb_phone = new System.Windows.Forms.TextBox();
            tb_address = new System.Windows.Forms.TextBox();
            label_firstname = new System.Windows.Forms.Label();
            label_lastname = new System.Windows.Forms.Label();
            label_email = new System.Windows.Forms.Label();
            label_phone = new System.Windows.Forms.Label();
            label_address = new System.Windows.Forms.Label();
            button_save = new System.Windows.Forms.Button();
            button_cancel = new System.Windows.Forms.Button();
            button_delete = new System.Windows.Forms.Button();
            label_title = new System.Windows.Forms.Label();
            label_password = new System.Windows.Forms.Label();
            tb_password = new System.Windows.Forms.TextBox();
            label_error = new System.Windows.Forms.Label();
            SuspendLayout();
            // 
            // tb_firstname
            // 
            tb_firstname.Location = new System.Drawing.Point(186, 100);
            tb_firstname.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            tb_firstname.Name = "tb_firstname";
            tb_firstname.Size = new System.Drawing.Size(174, 23);
            tb_firstname.TabIndex = 1;
            // 
            // tb_lastname
            // 
            tb_lastname.Location = new System.Drawing.Point(186, 142);
            tb_lastname.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            tb_lastname.Name = "tb_lastname";
            tb_lastname.Size = new System.Drawing.Size(174, 23);
            tb_lastname.TabIndex = 2;
            // 
            // tb_email
            // 
            tb_email.Location = new System.Drawing.Point(186, 187);
            tb_email.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            tb_email.Name = "tb_email";
            tb_email.Size = new System.Drawing.Size(174, 23);
            tb_email.TabIndex = 3;
            // 
            // tb_phone
            // 
            tb_phone.Location = new System.Drawing.Point(186, 228);
            tb_phone.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            tb_phone.Name = "tb_phone";
            tb_phone.Size = new System.Drawing.Size(174, 23);
            tb_phone.TabIndex = 4;
            tb_phone.KeyPress += tb_phone_KeyPress;
            // 
            // tb_address
            // 
            tb_address.Location = new System.Drawing.Point(186, 268);
            tb_address.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            tb_address.Name = "tb_address";
            tb_address.Size = new System.Drawing.Size(174, 23);
            tb_address.TabIndex = 5;
            // 
            // label_firstname
            // 
            label_firstname.AutoSize = true;
            label_firstname.Location = new System.Drawing.Point(80, 104);
            label_firstname.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_firstname.Name = "label_firstname";
            label_firstname.Size = new System.Drawing.Size(67, 15);
            label_firstname.TabIndex = 5;
            label_firstname.Text = "First Name:";
            // 
            // label_lastname
            // 
            label_lastname.AutoSize = true;
            label_lastname.Location = new System.Drawing.Point(81, 145);
            label_lastname.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_lastname.Name = "label_lastname";
            label_lastname.Size = new System.Drawing.Size(66, 15);
            label_lastname.TabIndex = 6;
            label_lastname.Text = "Last Name:";
            // 
            // label_email
            // 
            label_email.AutoSize = true;
            label_email.Location = new System.Drawing.Point(103, 187);
            label_email.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_email.Name = "label_email";
            label_email.Size = new System.Drawing.Size(39, 15);
            label_email.TabIndex = 7;
            label_email.Text = "Email:";
            // 
            // label_phone
            // 
            label_phone.AutoSize = true;
            label_phone.Location = new System.Drawing.Point(103, 231);
            label_phone.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_phone.Name = "label_phone";
            label_phone.Size = new System.Drawing.Size(44, 15);
            label_phone.TabIndex = 8;
            label_phone.Text = "Phone:";
            // 
            // label_address
            // 
            label_address.AutoSize = true;
            label_address.Location = new System.Drawing.Point(94, 272);
            label_address.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_address.Name = "label_address";
            label_address.Size = new System.Drawing.Size(52, 15);
            label_address.TabIndex = 9;
            label_address.Text = "Address:";
            // 
            // button_save
            // 
            button_save.Location = new System.Drawing.Point(218, 385);
            button_save.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            button_save.Name = "button_save";
            button_save.Size = new System.Drawing.Size(88, 27);
            button_save.TabIndex = 8;
            button_save.Text = "Save Details";
            button_save.UseVisualStyleBackColor = true;
            button_save.Click += button_save_Click;
            // 
            // button_cancel
            // 
            button_cancel.Location = new System.Drawing.Point(113, 385);
            button_cancel.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            button_cancel.Name = "button_cancel";
            button_cancel.Size = new System.Drawing.Size(88, 27);
            button_cancel.TabIndex = 7;
            button_cancel.Text = "Cancel";
            button_cancel.UseVisualStyleBackColor = true;
            button_cancel.Click += button_cancel_Click;
            // 
            // button_delete
            // 
            button_delete.Location = new System.Drawing.Point(14, 509);
            button_delete.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            button_delete.Name = "button_delete";
            button_delete.Size = new System.Drawing.Size(118, 27);
            button_delete.TabIndex = 9;
            button_delete.Text = "Delete Customer";
            button_delete.UseVisualStyleBackColor = true;
            button_delete.Click += button_delete_Click;
            // 
            // label_title
            // 
            label_title.AutoSize = true;
            label_title.Location = new System.Drawing.Point(155, 35);
            label_title.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_title.Name = "label_title";
            label_title.Size = new System.Drawing.Size(120, 15);
            label_title.TabIndex = 13;
            label_title.Text = "Edit Customer Details";
            // 
            // label_password
            // 
            label_password.AutoSize = true;
            label_password.Location = new System.Drawing.Point(85, 311);
            label_password.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_password.Name = "label_password";
            label_password.Size = new System.Drawing.Size(60, 15);
            label_password.TabIndex = 14;
            label_password.Text = "Password:";
            // 
            // tb_password
            // 
            tb_password.Location = new System.Drawing.Point(186, 311);
            tb_password.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            tb_password.Name = "tb_password";
            tb_password.Size = new System.Drawing.Size(174, 23);
            tb_password.TabIndex = 6;
            tb_password.UseSystemPasswordChar = true;
            // 
            // label_error
            // 
            label_error.AutoSize = true;
            label_error.Location = new System.Drawing.Point(132, 68);
            label_error.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_error.Name = "label_error";
            label_error.Size = new System.Drawing.Size(0, 15);
            label_error.TabIndex = 16;
            // 
            // EditCustomer
            // 
            AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            ClientSize = new System.Drawing.Size(439, 549);
            Controls.Add(label_error);
            Controls.Add(tb_password);
            Controls.Add(label_password);
            Controls.Add(label_title);
            Controls.Add(button_delete);
            Controls.Add(button_cancel);
            Controls.Add(button_save);
            Controls.Add(label_address);
            Controls.Add(label_phone);
            Controls.Add(label_email);
            Controls.Add(label_lastname);
            Controls.Add(label_firstname);
            Controls.Add(tb_address);
            Controls.Add(tb_phone);
            Controls.Add(tb_email);
            Controls.Add(tb_lastname);
            Controls.Add(tb_firstname);
            Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            Name = "EditCustomer";
            Text = "Edit Customer Details";
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private System.Windows.Forms.TextBox tb_firstname;
        private System.Windows.Forms.TextBox tb_lastname;
        private System.Windows.Forms.TextBox tb_email;
        private System.Windows.Forms.TextBox tb_phone;
        private System.Windows.Forms.TextBox tb_address;
        private System.Windows.Forms.Label label_firstname;
        private System.Windows.Forms.Label label_lastname;
        private System.Windows.Forms.Label label_email;
        private System.Windows.Forms.Label label_phone;
        private System.Windows.Forms.Label label_address;
        private System.Windows.Forms.Button button_save;
        private System.Windows.Forms.Button button_cancel;
        private System.Windows.Forms.Button button_delete;
        private System.Windows.Forms.Label label_title;
        private System.Windows.Forms.Label label_password;
        private System.Windows.Forms.TextBox tb_password;
        private System.Windows.Forms.Label label_error;
    }
}