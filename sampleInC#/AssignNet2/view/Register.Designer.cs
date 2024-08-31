namespace Booking.com
{
    partial class Register
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
            tb_email = new System.Windows.Forms.TextBox();
            tb_password = new System.Windows.Forms.TextBox();
            tb_firstname = new System.Windows.Forms.TextBox();
            tb_streetaddress = new System.Windows.Forms.TextBox();
            tb_phonenumber = new System.Windows.Forms.TextBox();
            tb_lastname = new System.Windows.Forms.TextBox();
            label_email = new System.Windows.Forms.Label();
            label_password = new System.Windows.Forms.Label();
            label_firstname = new System.Windows.Forms.Label();
            label_lastname = new System.Windows.Forms.Label();
            label_phonenumber = new System.Windows.Forms.Label();
            label_streetaddress = new System.Windows.Forms.Label();
            tb_suburb = new System.Windows.Forms.TextBox();
            label_suburb = new System.Windows.Forms.Label();
            tb_postcode = new System.Windows.Forms.TextBox();
            label_postcode = new System.Windows.Forms.Label();
            cb_state = new System.Windows.Forms.ComboBox();
            label_state = new System.Windows.Forms.Label();
            button_register = new System.Windows.Forms.Button();
            button_cancel = new System.Windows.Forms.Button();
            label_errormessage = new System.Windows.Forms.Label();
            label_title = new System.Windows.Forms.Label();
            SuspendLayout();
            // 
            // tb_email
            // 
            tb_email.Location = new System.Drawing.Point(426, 202);
            tb_email.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            tb_email.Name = "tb_email";
            tb_email.Size = new System.Drawing.Size(269, 23);
            tb_email.TabIndex = 0;
            // 
            // tb_password
            // 
            tb_password.Location = new System.Drawing.Point(426, 257);
            tb_password.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            tb_password.Name = "tb_password";
            tb_password.Size = new System.Drawing.Size(269, 23);
            tb_password.TabIndex = 1;
            tb_password.UseSystemPasswordChar = true;
            // 
            // tb_firstname
            // 
            tb_firstname.Location = new System.Drawing.Point(426, 321);
            tb_firstname.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            tb_firstname.Name = "tb_firstname";
            tb_firstname.Size = new System.Drawing.Size(269, 23);
            tb_firstname.TabIndex = 2;
            // 
            // tb_streetaddress
            // 
            tb_streetaddress.Location = new System.Drawing.Point(426, 501);
            tb_streetaddress.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            tb_streetaddress.Name = "tb_streetaddress";
            tb_streetaddress.Size = new System.Drawing.Size(269, 23);
            tb_streetaddress.TabIndex = 5;
            // 
            // tb_phonenumber
            // 
            tb_phonenumber.Location = new System.Drawing.Point(426, 441);
            tb_phonenumber.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            tb_phonenumber.Name = "tb_phonenumber";
            tb_phonenumber.Size = new System.Drawing.Size(269, 23);
            tb_phonenumber.TabIndex = 4;
            tb_phonenumber.KeyPress += phone_Keypress;
            // 
            // tb_lastname
            // 
            tb_lastname.Location = new System.Drawing.Point(426, 378);
            tb_lastname.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            tb_lastname.Name = "tb_lastname";
            tb_lastname.Size = new System.Drawing.Size(269, 23);
            tb_lastname.TabIndex = 3;
            // 
            // label_email
            // 
            label_email.AutoSize = true;
            label_email.Location = new System.Drawing.Point(347, 205);
            label_email.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_email.Name = "label_email";
            label_email.Size = new System.Drawing.Size(36, 15);
            label_email.TabIndex = 6;
            label_email.Text = "Email";
            // 
            // label_password
            // 
            label_password.AutoSize = true;
            label_password.Location = new System.Drawing.Point(322, 265);
            label_password.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_password.Name = "label_password";
            label_password.Size = new System.Drawing.Size(57, 15);
            label_password.TabIndex = 7;
            label_password.Text = "Password";
            // 
            // label_firstname
            // 
            label_firstname.AutoSize = true;
            label_firstname.Location = new System.Drawing.Point(318, 321);
            label_firstname.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_firstname.Name = "label_firstname";
            label_firstname.Size = new System.Drawing.Size(64, 15);
            label_firstname.TabIndex = 8;
            label_firstname.Text = "First Name";
            // 
            // label_lastname
            // 
            label_lastname.AutoSize = true;
            label_lastname.Location = new System.Drawing.Point(316, 378);
            label_lastname.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_lastname.Name = "label_lastname";
            label_lastname.Size = new System.Drawing.Size(63, 15);
            label_lastname.TabIndex = 9;
            label_lastname.Text = "Last Name";
            // 
            // label_phonenumber
            // 
            label_phonenumber.AutoSize = true;
            label_phonenumber.Location = new System.Drawing.Point(293, 441);
            label_phonenumber.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_phonenumber.Name = "label_phonenumber";
            label_phonenumber.Size = new System.Drawing.Size(88, 15);
            label_phonenumber.TabIndex = 10;
            label_phonenumber.Text = "Phone Number";
            // 
            // label_streetaddress
            // 
            label_streetaddress.AutoSize = true;
            label_streetaddress.Location = new System.Drawing.Point(295, 504);
            label_streetaddress.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_streetaddress.Name = "label_streetaddress";
            label_streetaddress.Size = new System.Drawing.Size(82, 15);
            label_streetaddress.TabIndex = 11;
            label_streetaddress.Text = "Street Address";
            // 
            // tb_suburb
            // 
            tb_suburb.Location = new System.Drawing.Point(426, 565);
            tb_suburb.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            tb_suburb.Name = "tb_suburb";
            tb_suburb.Size = new System.Drawing.Size(269, 23);
            tb_suburb.TabIndex = 12;
            // 
            // label_suburb
            // 
            label_suburb.AutoSize = true;
            label_suburb.Location = new System.Drawing.Point(336, 565);
            label_suburb.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_suburb.Name = "label_suburb";
            label_suburb.Size = new System.Drawing.Size(45, 15);
            label_suburb.TabIndex = 13;
            label_suburb.Text = "Suburb";
            // 
            // tb_postcode
            // 
            tb_postcode.Location = new System.Drawing.Point(426, 619);
            tb_postcode.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            tb_postcode.Name = "tb_postcode";
            tb_postcode.Size = new System.Drawing.Size(140, 23);
            tb_postcode.TabIndex = 14;
            tb_postcode.KeyPress += postcode_Keypress;
            // 
            // label_postcode
            // 
            label_postcode.AutoSize = true;
            label_postcode.Location = new System.Drawing.Point(323, 619);
            label_postcode.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_postcode.Name = "label_postcode";
            label_postcode.Size = new System.Drawing.Size(56, 15);
            label_postcode.TabIndex = 15;
            label_postcode.Text = "Postcode";
            // 
            // cb_state
            // 
            cb_state.BackColor = System.Drawing.SystemColors.Window;
            cb_state.ForeColor = System.Drawing.SystemColors.WindowText;
            cb_state.FormattingEnabled = true;
            cb_state.Location = new System.Drawing.Point(426, 678);
            cb_state.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            cb_state.Name = "cb_state";
            cb_state.Size = new System.Drawing.Size(140, 23);
            cb_state.TabIndex = 16;
            cb_state.KeyPress += cb_state_KeyPress;
            // 
            // label_state
            // 
            label_state.AutoSize = true;
            label_state.Location = new System.Drawing.Point(347, 678);
            label_state.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_state.Name = "label_state";
            label_state.Size = new System.Drawing.Size(33, 15);
            label_state.TabIndex = 17;
            label_state.Text = "State";
            // 
            // button_register
            // 
            button_register.Location = new System.Drawing.Point(533, 787);
            button_register.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            button_register.Name = "button_register";
            button_register.Size = new System.Drawing.Size(88, 27);
            button_register.TabIndex = 18;
            button_register.Text = "Register";
            button_register.UseVisualStyleBackColor = true;
            button_register.Click += register_Click;
            // 
            // button_cancel
            // 
            button_cancel.Location = new System.Drawing.Point(404, 787);
            button_cancel.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            button_cancel.Name = "button_cancel";
            button_cancel.Size = new System.Drawing.Size(88, 27);
            button_cancel.TabIndex = 19;
            button_cancel.Text = "Cancel";
            button_cancel.UseVisualStyleBackColor = true;
            button_cancel.Click += cancel_Click;
            // 
            // label_errormessage
            // 
            label_errormessage.AutoSize = true;
            label_errormessage.Location = new System.Drawing.Point(384, 153);
            label_errormessage.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_errormessage.Name = "label_errormessage";
            label_errormessage.Size = new System.Drawing.Size(0, 15);
            label_errormessage.TabIndex = 20;
            // 
            // label_title
            // 
            label_title.AutoSize = true;
            label_title.Location = new System.Drawing.Point(447, 153);
            label_title.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_title.Name = "label_title";
            label_title.Size = new System.Drawing.Size(101, 15);
            label_title.TabIndex = 21;
            label_title.Text = "Registration Form";
            // 
            // Register
            // 
            AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            ClientSize = new System.Drawing.Size(1038, 895);
            Controls.Add(label_title);
            Controls.Add(label_errormessage);
            Controls.Add(button_cancel);
            Controls.Add(button_register);
            Controls.Add(label_state);
            Controls.Add(cb_state);
            Controls.Add(label_postcode);
            Controls.Add(tb_postcode);
            Controls.Add(label_suburb);
            Controls.Add(tb_suburb);
            Controls.Add(label_streetaddress);
            Controls.Add(label_phonenumber);
            Controls.Add(label_lastname);
            Controls.Add(label_firstname);
            Controls.Add(label_password);
            Controls.Add(label_email);
            Controls.Add(tb_streetaddress);
            Controls.Add(tb_phonenumber);
            Controls.Add(tb_lastname);
            Controls.Add(tb_firstname);
            Controls.Add(tb_password);
            Controls.Add(tb_email);
            Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            Name = "Register";
            Text = "Register";
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private System.Windows.Forms.TextBox tb_email;
        private System.Windows.Forms.TextBox tb_password;
        private System.Windows.Forms.TextBox tb_firstname;
        private System.Windows.Forms.TextBox tb_streetaddress;
        private System.Windows.Forms.TextBox tb_phonenumber;
        private System.Windows.Forms.TextBox tb_lastname;
        private System.Windows.Forms.Label label_email;
        private System.Windows.Forms.Label label_password;
        private System.Windows.Forms.Label label_firstname;
        private System.Windows.Forms.Label label_lastname;
        private System.Windows.Forms.Label label_phonenumber;
        private System.Windows.Forms.Label label_streetaddress;
        private System.Windows.Forms.TextBox tb_suburb;
        private System.Windows.Forms.Label label_suburb;
        private System.Windows.Forms.TextBox tb_postcode;
        private System.Windows.Forms.Label label_postcode;
        private System.Windows.Forms.ComboBox cb_state;
        private System.Windows.Forms.Label label_state;
        private System.Windows.Forms.Button button_register;
        private System.Windows.Forms.Button button_cancel;
        private System.Windows.Forms.Label label_errormessage;
        private System.Windows.Forms.Label label_title;
    }
}