using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Threading;
using System.Windows.Forms;

namespace WindowsFormsApplication1
{
    public partial class Form1 : Form
    {
        Word[] program;
        Word[] ram;
        SortedSet<int> usedRam;
        int pc;
        Word A;
        Word D;

        public Form1()
        {
            A = new Word(0);
            D = new Word(0);
            usedRam = new SortedSet<int>();
            ram = new Word[16000];
            for (int i = 0; i < ram.Length; i++)
            {
                ram[i] = new Word(0);
            }
            pc = 0;
            InitializeComponent();
            //MessageBox.Show(Convert.ToString((12), 2).PadLeft(16, '0') + Environment.NewLine +
            //                Convert.ToString((121), 2).PadLeft(16, '0') + Environment.NewLine +
            //                Convert.ToString((12 | 121), 2).PadLeft(16, '0'));
            //Word a = new Word(12);
            //Word b = new Word(121);
            //MessageBox.Show(a.GetBinary() + Environment.NewLine +
            //                b.GetBinary() + Environment.NewLine +
            //                (a|b).GetBinary());
        }

        private void cargarToolStripMenuItem_Click(object sender, EventArgs e)
        {
            openFileDialog1.ShowDialog();
        }

        private void openFileDialog1_FileOk(object sender, CancelEventArgs e)
        {
            string[] lines = System.IO.File.ReadAllLines(openFileDialog1.FileName);

            program = new Word[lines.Length];

            for (int i = 0; i < lines.Length; i++)
            {
                program[i] = new Word(lines[i]);
            }

            PopulateProgramDialog();

            this.programList.Items[0].Selected = true;
            this.pc = 0;
        }

        private void PopulateProgramDialog()
        {
            this.programList.Items.Clear();
            this.ram_list.Items.Clear();
            this.reg_list.Items.Clear();

            for (int i = 0; i < program.Length; i++)
            {
                ListViewItem item = new ListViewItem(new String[] { i.ToString(), program[i].GetAsmCode(), program[i].GetBinary() });
                this.programList.Items.Add(item);
            }

            foreach (int i in usedRam){
                if (i < 0) continue;
                ListViewItem item = new ListViewItem(new String[] { i.ToString(), ram[i].GetDecimal().ToString(), ram[i].GetBinary() });
                this.ram_list.Items.Add(item);
            }
            this.programList.Items[pc].Selected = true;

            ListViewItem a_item = new ListViewItem(new String[] { "A", A.GetDecimal().ToString(), A.GetBinary() });
            ListViewItem d_item = new ListViewItem(new String[] { "D", D.GetDecimal().ToString(), D.GetBinary() });
            ListViewItem m_item = new ListViewItem(new String[] { "M[A]", ram[A.GetDecimal()].GetDecimal().ToString(), ram[A.GetDecimal()].GetBinary() });
            this.reg_list.Items.Add(a_item);
            this.reg_list.Items.Add(d_item);
            this.reg_list.Items.Add(m_item);
        }

        private void executeSelectedLine()
        {
            if (this.pc >= program.Length) return;
            int line = program[this.pc].ExecuteAsmCode(ref this.ram, ref this.A, ref this.D, ref this.pc);
            this.programList.Items[this.pc].Selected = true;
            this.usedRam.Add(line);
            PopulateProgramDialog();
        }

        private void executeButton_Click(object sender, EventArgs e)
        {
            this.executeSelectedLine();
        }

        private void reiniciarProgramaToolStripMenuItem_Click(object sender, EventArgs e)
        {

            A = new Word(0);
            D = new Word(0);
            usedRam = new SortedSet<int>();
            ram = new Word[16000];
            for (int i = 0; i < ram.Length; i++)
            {
                ram[i] = new Word(0);
            }
            pc = 0;

            PopulateProgramDialog();
        }

        private void borrarMemoriaToolStripMenuItem_Click(object sender, EventArgs e)
        {
            A = new Word(0);
            D = new Word(0);
            usedRam = new SortedSet<int>();
            ram = new Word[16000];
            for (int i = 0; i < ram.Length; i++)
            {
                ram[i] = new Word(0);
            }

            PopulateProgramDialog();
        }

        private void reproducirProgramaToolStripMenuItem_Click(object sender, EventArgs e)
        {
            MessageBox.Show("No implementado en esta versión.");
            //while (this.pc < program.Length)
            //{
            //    this.executeSelectedLine();
            //}
        }


    }
}
