namespace Booking.com
{
    partial class Start
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
            btn_login = new System.Windows.Forms.Button();
            btn_register = new System.Windows.Forms.Button();
            tb_password = new System.Windows.Forms.TextBox();
            tb_email = new System.Windows.Forms.TextBox();
            label_email = new System.Windows.Forms.Label();
            label_password = new System.Windows.Forms.Label();
            label_welcome = new System.Windows.Forms.Label();
            SuspendLayout();
            // 
            // btn_login
            // 
            btn_login.Anchor = System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left | System.Windows.Forms.AnchorStyles.Right;
            btn_login.Location = new System.Drawing.Point(544, 406);
            btn_login.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            btn_login.Name = "btn_login";
            btn_login.Size = new System.Drawing.Size(88, 27);
            btn_login.TabIndex = 3;
            btn_login.Text = "Login";
            btn_login.UseVisualStyleBackColor = true;
            btn_login.Click += loginBtn_Click;
            // 
            // btn_register
            // 
            btn_register.Anchor = System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left | System.Windows.Forms.AnchorStyles.Right;
            btn_register.Location = new System.Drawing.Point(650, 406);
            btn_register.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            btn_register.Name = "btn_register";
            btn_register.Size = new System.Drawing.Size(88, 27);
            btn_register.TabIndex = 4;
            btn_register.Text = "Register";
            btn_register.UseVisualStyleBackColor = true;
            btn_register.Click += registerBtn_click;
            // 
            // tb_password
            // 
            tb_password.Location = new System.Drawing.Point(544, 343);
            tb_password.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            tb_password.Name = "tb_password";
            tb_password.PasswordChar = '*';
            tb_password.Size = new System.Drawing.Size(194, 23);
            tb_password.TabIndex = 2;
            tb_password.UseSystemPasswordChar = true;
            // 
            // tb_email
            // 
            tb_email.Location = new System.Drawing.Point(544, 293);
            tb_email.Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            tb_email.Name = "tb_email";
            tb_email.Size = new System.Drawing.Size(194, 23);
            tb_email.TabIndex = 1;
            // 
            // label_email
            // 
            label_email.AutoSize = true;
            label_email.Location = new System.Drawing.Point(438, 296);
            label_email.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_email.Name = "label_email";
            label_email.Size = new System.Drawing.Size(94, 15);
            label_email.TabIndex = 4;
            label_email.Text = "Username/Email";
            // 
            // label_password
            // 
            label_password.AutoSize = true;
            label_password.Location = new System.Drawing.Point(475, 346);
            label_password.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            label_password.Name = "label_password";
            label_password.Size = new System.Drawing.Size(57, 15);
            label_password.TabIndex = 5;
            label_password.Text = "Password";
            // 
            // label_welcome
            // 
            label_welcome.AutoSize = true;
            label_welcome.Location = new System.Drawing.Point(421, 255);
            label_welcome.Name = "label_welcome";
            label_welcome.Size = new System.Drawing.Size(381, 15);
            label_welcome.TabIndex = 6;
            label_welcome.Text = "Welcome to the Booking.com App. Please Login or Register to contine.";
            // 
            // Start
            // 
            AutoScaleDimensions = new System.Drawing.SizeF(7F, 15F);
            AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            ClientSize = new System.Drawing.Size(1181, 703);
            Controls.Add(label_welcome);
            Controls.Add(label_password);
            Controls.Add(label_email);
            Controls.Add(tb_email);
            Controls.Add(tb_password);
            Controls.Add(btn_register);
            Controls.Add(btn_login);
            Margin = new System.Windows.Forms.Padding(4, 3, 4, 3);
            Name = "Start";
            Text = "Start";
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private System.Windows.Forms.Button btn_login;
        private System.Windows.Forms.Button btn_register;
        private System.Windows.Forms.TextBox tb_password;
        private System.Windows.Forms.TextBox tb_email;
        private System.Windows.Forms.Label label_email;
        private System.Windows.Forms.Label label_password;
        private System.Windows.Forms.Label label_welcome;
    }
}

