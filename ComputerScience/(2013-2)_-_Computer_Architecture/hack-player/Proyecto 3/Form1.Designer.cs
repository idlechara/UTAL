namespace WindowsFormsApplication1
{
    partial class Form1
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
            this.programList = new System.Windows.Forms.ListView();
            this.columnHeader1 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.columnHeader2 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.columnHeader3 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.archivoToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.cargarToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.programaToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.ejecutarLíneaActualToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.reproducirProgramaToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.reiniciarProgramaToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.memoriaToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.borrarMemoriaToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.openFileDialog1 = new System.Windows.Forms.OpenFileDialog();
            this.ram_list = new System.Windows.Forms.ListView();
            this.columnHeader4 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.columnHeader5 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.columnHeader6 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.executeButton = new System.Windows.Forms.Button();
            this.reg_list = new System.Windows.Forms.ListView();
            this.columnHeader7 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.columnHeader8 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.columnHeader9 = ((System.Windows.Forms.ColumnHeader)(new System.Windows.Forms.ColumnHeader()));
            this.play = new System.Windows.Forms.Button();
            this.numericUpDown1 = new System.Windows.Forms.NumericUpDown();
            this.label1 = new System.Windows.Forms.Label();
            this.menuStrip1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDown1)).BeginInit();
            this.SuspendLayout();
            // 
            // programList
            // 
            this.programList.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left)));
            this.programList.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.columnHeader1,
            this.columnHeader2,
            this.columnHeader3});
            this.programList.Enabled = false;
            this.programList.FullRowSelect = true;
            this.programList.HeaderStyle = System.Windows.Forms.ColumnHeaderStyle.Nonclickable;
            this.programList.HideSelection = false;
            this.programList.Location = new System.Drawing.Point(12, 27);
            this.programList.MultiSelect = false;
            this.programList.Name = "programList";
            this.programList.Size = new System.Drawing.Size(317, 499);
            this.programList.TabIndex = 0;
            this.programList.UseCompatibleStateImageBehavior = false;
            this.programList.View = System.Windows.Forms.View.Details;
            // 
            // columnHeader1
            // 
            this.columnHeader1.Text = "Línea";
            // 
            // columnHeader2
            // 
            this.columnHeader2.Text = "Hack";
            this.columnHeader2.Width = 49;
            // 
            // columnHeader3
            // 
            this.columnHeader3.Text = "Binario";
            this.columnHeader3.Width = 186;
            // 
            // menuStrip1
            // 
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.archivoToolStripMenuItem,
            this.programaToolStripMenuItem,
            this.memoriaToolStripMenuItem});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(810, 24);
            this.menuStrip1.TabIndex = 1;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // archivoToolStripMenuItem
            // 
            this.archivoToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.cargarToolStripMenuItem});
            this.archivoToolStripMenuItem.Name = "archivoToolStripMenuItem";
            this.archivoToolStripMenuItem.Size = new System.Drawing.Size(69, 20);
            this.archivoToolStripMenuItem.Text = "Archivo...";
            // 
            // cargarToolStripMenuItem
            // 
            this.cargarToolStripMenuItem.Name = "cargarToolStripMenuItem";
            this.cargarToolStripMenuItem.Size = new System.Drawing.Size(109, 22);
            this.cargarToolStripMenuItem.Text = "Cargar";
            this.cargarToolStripMenuItem.Click += new System.EventHandler(this.cargarToolStripMenuItem_Click);
            // 
            // programaToolStripMenuItem
            // 
            this.programaToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.ejecutarLíneaActualToolStripMenuItem,
            this.reproducirProgramaToolStripMenuItem,
            this.reiniciarProgramaToolStripMenuItem});
            this.programaToolStripMenuItem.Name = "programaToolStripMenuItem";
            this.programaToolStripMenuItem.Size = new System.Drawing.Size(80, 20);
            this.programaToolStripMenuItem.Text = "Programa...";
            // 
            // ejecutarLíneaActualToolStripMenuItem
            // 
            this.ejecutarLíneaActualToolStripMenuItem.Name = "ejecutarLíneaActualToolStripMenuItem";
            this.ejecutarLíneaActualToolStripMenuItem.Size = new System.Drawing.Size(187, 22);
            this.ejecutarLíneaActualToolStripMenuItem.Text = "Ejecutar línea actual";
            // 
            // reproducirProgramaToolStripMenuItem
            // 
            this.reproducirProgramaToolStripMenuItem.Name = "reproducirProgramaToolStripMenuItem";
            this.reproducirProgramaToolStripMenuItem.Size = new System.Drawing.Size(187, 22);
            this.reproducirProgramaToolStripMenuItem.Text = "Reproducir programa";
            this.reproducirProgramaToolStripMenuItem.Click += new System.EventHandler(this.reproducirProgramaToolStripMenuItem_Click);
            // 
            // reiniciarProgramaToolStripMenuItem
            // 
            this.reiniciarProgramaToolStripMenuItem.Name = "reiniciarProgramaToolStripMenuItem";
            this.reiniciarProgramaToolStripMenuItem.Size = new System.Drawing.Size(187, 22);
            this.reiniciarProgramaToolStripMenuItem.Text = "Reiniciar programa";
            this.reiniciarProgramaToolStripMenuItem.Click += new System.EventHandler(this.reiniciarProgramaToolStripMenuItem_Click);
            // 
            // memoriaToolStripMenuItem
            // 
            this.memoriaToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.borrarMemoriaToolStripMenuItem});
            this.memoriaToolStripMenuItem.Name = "memoriaToolStripMenuItem";
            this.memoriaToolStripMenuItem.Size = new System.Drawing.Size(76, 20);
            this.memoriaToolStripMenuItem.Text = "Memoria...";
            // 
            // borrarMemoriaToolStripMenuItem
            // 
            this.borrarMemoriaToolStripMenuItem.Name = "borrarMemoriaToolStripMenuItem";
            this.borrarMemoriaToolStripMenuItem.Size = new System.Drawing.Size(157, 22);
            this.borrarMemoriaToolStripMenuItem.Text = "Borrar memoria";
            this.borrarMemoriaToolStripMenuItem.Click += new System.EventHandler(this.borrarMemoriaToolStripMenuItem_Click);
            // 
            // openFileDialog1
            // 
            this.openFileDialog1.FileName = "openFileDialog1";
            this.openFileDialog1.Title = "Elija archivo";
            this.openFileDialog1.FileOk += new System.ComponentModel.CancelEventHandler(this.openFileDialog1_FileOk);
            // 
            // ram_list
            // 
            this.ram_list.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left)));
            this.ram_list.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.columnHeader4,
            this.columnHeader5,
            this.columnHeader6});
            this.ram_list.Location = new System.Drawing.Point(335, 27);
            this.ram_list.Name = "ram_list";
            this.ram_list.Size = new System.Drawing.Size(317, 372);
            this.ram_list.TabIndex = 2;
            this.ram_list.UseCompatibleStateImageBehavior = false;
            this.ram_list.View = System.Windows.Forms.View.Details;
            // 
            // columnHeader4
            // 
            this.columnHeader4.Text = "Índice";
            // 
            // columnHeader5
            // 
            this.columnHeader5.Text = "Decimal";
            this.columnHeader5.Width = 96;
            // 
            // columnHeader6
            // 
            this.columnHeader6.Text = "Binario";
            this.columnHeader6.Width = 155;
            // 
            // executeButton
            // 
            this.executeButton.Location = new System.Drawing.Point(658, 27);
            this.executeButton.Name = "executeButton";
            this.executeButton.Size = new System.Drawing.Size(140, 64);
            this.executeButton.TabIndex = 3;
            this.executeButton.Text = "Ejecutar línea actual";
            this.executeButton.UseVisualStyleBackColor = true;
            this.executeButton.Click += new System.EventHandler(this.executeButton_Click);
            // 
            // reg_list
            // 
            this.reg_list.Columns.AddRange(new System.Windows.Forms.ColumnHeader[] {
            this.columnHeader7,
            this.columnHeader8,
            this.columnHeader9});
            this.reg_list.Location = new System.Drawing.Point(335, 405);
            this.reg_list.Name = "reg_list";
            this.reg_list.Size = new System.Drawing.Size(317, 117);
            this.reg_list.TabIndex = 4;
            this.reg_list.UseCompatibleStateImageBehavior = false;
            this.reg_list.View = System.Windows.Forms.View.Details;
            // 
            // columnHeader7
            // 
            this.columnHeader7.Text = "Variable";
            // 
            // columnHeader8
            // 
            this.columnHeader8.Text = "Decimal";
            // 
            // columnHeader9
            // 
            this.columnHeader9.Text = "Binario";
            // 
            // play
            // 
            this.play.Location = new System.Drawing.Point(658, 432);
            this.play.Name = "play";
            this.play.Size = new System.Drawing.Size(140, 64);
            this.play.TabIndex = 5;
            this.play.Text = "Reproducir programa";
            this.play.UseVisualStyleBackColor = true;
            this.play.Click += new System.EventHandler(this.reproducirProgramaToolStripMenuItem_Click);
            // 
            // numericUpDown1
            // 
            this.numericUpDown1.Location = new System.Drawing.Point(720, 502);
            this.numericUpDown1.Maximum = new decimal(new int[] {
            1215752192,
            23,
            0,
            0});
            this.numericUpDown1.Name = "numericUpDown1";
            this.numericUpDown1.Size = new System.Drawing.Size(78, 20);
            this.numericUpDown1.TabIndex = 6;
            this.numericUpDown1.Value = new decimal(new int[] {
            1000,
            0,
            0,
            0});
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(658, 504);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(56, 13);
            this.label1.TabIndex = 7;
            this.label1.Text = "Delay (ms)";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(810, 538);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.numericUpDown1);
            this.Controls.Add(this.play);
            this.Controls.Add(this.reg_list);
            this.Controls.Add(this.executeButton);
            this.Controls.Add(this.ram_list);
            this.Controls.Add(this.programList);
            this.Controls.Add(this.menuStrip1);
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "Form1";
            this.Text = "Hack-Player";
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.numericUpDown1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListView programList;
        private System.Windows.Forms.ColumnHeader columnHeader1;
        private System.Windows.Forms.ColumnHeader columnHeader2;
        private System.Windows.Forms.ColumnHeader columnHeader3;
        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem archivoToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem cargarToolStripMenuItem;
        private System.Windows.Forms.OpenFileDialog openFileDialog1;
        private System.Windows.Forms.ListView ram_list;
        private System.Windows.Forms.ColumnHeader columnHeader4;
        private System.Windows.Forms.ColumnHeader columnHeader5;
        private System.Windows.Forms.ColumnHeader columnHeader6;
        private System.Windows.Forms.Button executeButton;
        private System.Windows.Forms.ListView reg_list;
        private System.Windows.Forms.ColumnHeader columnHeader7;
        private System.Windows.Forms.ColumnHeader columnHeader8;
        private System.Windows.Forms.ColumnHeader columnHeader9;
        private System.Windows.Forms.ToolStripMenuItem programaToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem ejecutarLíneaActualToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem reproducirProgramaToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem reiniciarProgramaToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem memoriaToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem borrarMemoriaToolStripMenuItem;
        private System.Windows.Forms.Button play;
        private System.Windows.Forms.NumericUpDown numericUpDown1;
        private System.Windows.Forms.Label label1;
    }
}

