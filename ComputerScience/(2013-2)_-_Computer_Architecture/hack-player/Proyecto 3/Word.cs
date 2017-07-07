using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsApplication1
{
    class Word
    {
        private short value;
        private String binary;
        private int p;

        public Word()
        {
            this.value = 0;
            updateBinary();
        }

        public Word(short value)
        {
            this.value = value;
            updateBinary();
        }

        public Word(String value)
        {
            this.value = Convert.ToInt16(value, 2);
            this.binary = value;
        }

        public int GetDecimal()
        {
            return this.value;
        }

        public String GetBinary()
        {
            return this.binary;
        }

        private void updateBinary()
        {
            this.binary = Convert.ToString(value, 2).PadLeft(16, '0');
        }

        public String GetAsmCode()
        {
            if (this.binary.ToCharArray()[0] == '0')
            {
                //A-instruction
                int val = Convert.ToInt16(this.binary.Substring(1, 15), 2);
                return '@' + val.ToString();
            }
            else
            {
                //C-instruction
                bool a = this.binary.ToCharArray()[3] == '0';
                String comp = this.binary.Substring(4, 6);
                String dest = this.binary.Substring(10, 3);
                String jump = this.binary.Substring(13, 3);

                #region Computation Switch
                switch (comp)
                {
                    case "101010":
                        //0
                        comp = "0";
                        break;

                    case "111111":
                        //1
                        comp = "1";
                        break;

                    case "111010":
                        //-1
                        comp = "-1";
                        break;


                    case "001100":
                        //D
                        comp = "D";
                        break;

                    case "110000":
                        //A or M
                        comp = a ? "A" : "M";
                        break;

                    case "001101":
                        //!D
                        comp = "!D";
                        break;

                    case "110001":
                        //!A or !M
                        comp = "!" + (a ? "A" : "M");
                        break;

                    case "001111":
                        //-D
                        comp = "-D";
                        break;

                    case "110011":
                        //-A or -M
                        comp = "-" + (a ? "A" : "M");
                        break;

                    case "011111":
                        //D+1
                        comp = "D+1";
                        break;

                    case "110111":
                        //A+1 or M+1
                        comp = (a ? "A" : "M") + "+1";
                        break;

                    case "001110":
                        //D-1
                        comp = "D-1";
                        break;

                    case "110010":
                        //A-1 or M-1
                        comp = (a ? "A" : "M") + "-1";
                        break;

                    case "000010":
                        //D+A or M+A
                        comp = (a ? "D+A" : "D+M");
                        break;

                    case "010011":
                        //D-A or M-A
                        comp = (a ? "D-A" : "D-M");
                        break;

                    case "000111":
                        //A-D or A-M
                        comp = (a ? "A-D" : "M-D");
                        break;

                    case "000000":
                        //D&A
                        comp = (a ? "D&A" : "D&M");
                        break;

                    case "010101":
                        //D|A
                        comp = (a ? "D|A" : "D|M");
                        break;
                }
                #endregion

                #region Jump Switch
                switch (jump)
                {
                    case "000":
                        //null
                        jump = "";
                        break;
                    case "001":
                        //JGT
                        jump = ";JGT";
                        break;
                    case "010":
                        //JEQ
                        jump = ";JEQ";
                        break;
                    case "011":
                        //JGE
                        jump = ";JGE";
                        break;
                    case "100":
                        //JLT
                        jump = ";JLT";
                        break;
                    case "101":
                        //JNE
                        jump = ";JNE";
                        break;
                    case "110":
                        //JLE
                        jump = ";JLE";
                        break;
                    case "111":
                        //JMP
                        jump = ";JMP";
                        break;
                }
                #endregion

                #region Dest Switch
                switch (dest)
                {
                    case "000":
                        //null
                        dest = "";
                        break;
                    case "001":
                        //M
                        dest = "M=";
                        break;
                    case "010":
                        //D
                        dest = "D=";
                        break;
                    case "011":
                        //MD
                        dest = "MD=";
                        break;
                    case "100":
                        //A
                        dest = "A=";
                        break;
                    case "101":
                        //AM
                        dest = "AM=";
                        break;
                    case "110":
                        //AD
                        dest = "AD=";
                        break;
                    case "111":
                        //AMD
                        dest = "AMD=";
                        break;
                }
                #endregion

                return dest + comp + jump;
            }

        }

        public int ExecuteAsmCode(ref Word[] M, ref Word A, ref Word D, ref int PC)
        {
            if (this.binary.ToCharArray()[0] == '0')
            {
                //A-instruction
                short val = Convert.ToInt16(this.binary.Substring(1, 15), 2);
                A = new Word((short)val);
                PC++;
                return -1;
            }
            else
            {
                int line = -1;
                //C-instruction
                bool a = this.binary.ToCharArray()[3] == '0';
                String comp = this.binary.Substring(4, 6);
                String dest = this.binary.Substring(10, 3);
                String jump = this.binary.Substring(13, 3);

                Word comp_word = new Word(0);

                #region Computation Switch
                switch (comp)
                {
                    case "101010":
                        //0
                        comp_word = new Word(0);
                        break;

                    case "111111":
                        //1
                        comp_word = new Word(1);
                        break;

                    case "111010":
                        //-1
                        comp_word = new Word(-1);
                        break;


                    case "001100":
                        //D
                        comp_word = D;
                        break;

                    case "110000":
                        //A or M
                        comp_word = a ? A : M[A.value];
                        break;

                    case "001101":
                        //!D
                        comp_word = !D;
                        break;

                    case "110001":
                        //!A or !M
                        comp_word = a ? !A : !M[A.value];
                        break;

                    case "001111":
                        //-D
                        comp_word = -D;
                        break;

                    case "110011":
                        //-A or -M
                        comp_word = (a ? -A : -M[A.value]);
                        break;

                    case "011111":
                        //D+1
                        comp_word = D + 1;
                        break;

                    case "110111":
                        //A+1 or M+1
                        comp_word = (a ? A + 1 : M[A.value] + 1);
                        break;

                    case "001110":
                        //D-1
                        comp_word = D - 1;
                        break;

                    case "110010":
                        //A-1 or M-1
                        comp_word = (a ? A - 1 : M[A.value] - 1);
                        break;

                    case "000010":
                        //D+A or M+A
                        comp_word = (a ? D + A : D + M[A.value]);
                        break;

                    case "010011":
                        //D-A or M-A
                        comp_word = (a ? D - A : D - M[A.value]);
                        break;

                    case "000111":
                        //A-D or A-M
                        comp_word = (a ? A - D : M[A.value] - D);
                        break;

                    case "000000":
                        //D&A
                        comp_word = D & A;
                        break;

                    case "010101":
                        //D|A
                        comp_word = D | A;
                        break;
                }
                #endregion

                #region Dest Switch
                switch (dest)
                {
                    case "000":
                        //null
                        break;
                    case "001":
                        //M
                        M[A.value] = comp_word;
                        line = A.value;
                        break;
                    case "010":
                        //D
                        D = comp_word;
                        break;
                    case "011":
                        //MD
                        M[A.value] = comp_word;
                        line = A.value;
                        D = comp_word;
                        break;
                    case "100":
                        //A
                        A = comp_word;
                        break;
                    case "101":
                        //AM
                        M[A.value] = comp_word;
                        line = A.value;
                        A = comp_word;
                        break;
                    case "110":
                        //AD
                        A = comp_word;
                        D = comp_word;
                        break;
                    case "111":
                        //AMD
                        M[A.value] = comp_word;
                        line = A.value;
                        A = comp_word;
                        D = comp_word;
                        break;
                }
                #endregion

                #region Jump Switch
                switch (jump)
                {
                    case "000":
                        //null
                        PC++;
                        break;
                    case "001":
                        //JGT
                        if (comp_word.value > 0) PC = A.value;
                        else PC++;
                        break;
                    case "010":
                        //JEQ
                        if (comp_word.value == 0) PC = A.value;
                        else PC++;
                        break;
                    case "011":
                        //JGE
                        if (comp_word.value >= 0) PC = A.value;
                        else PC++;
                        break;
                    case "100":
                        //JLT
                        if (comp_word.value < 0) PC = A.value;
                        else PC++;
                        break;
                    case "101":
                        //JNE
                        if (comp_word.value != 0) PC = A.value;
                        else PC++;
                        break;
                    case "110":
                        //JLE
                        if (comp_word.value <= 0) PC = A.value;
                        else PC++;
                        break;
                    case "111":
                        //JMP
                        PC = A.value;
                        break;
                }
                #endregion

                return line;
            }

        }


        /*
         *Operator overload 
         */
        #region operatorOverload

        public static Word operator +(Word word1, Word word2)
        {
            return new Word((short)(word1.value + word2.value));
        }

        public static Word operator +(Word word1, int val)
        {
            return new Word((short)(word1.value + val));
        }

        public static Word operator -(Word word1, Word word2)
        {
            return new Word((short)(word1.value - word2.value));
        }

        public static Word operator -(Word word1, int val)
        {
            return new Word((short)(word1.value - val));
        }

        public static Word operator -(Word word1)
        {
            return new Word((short)(-word1.value));
        }

        public static Word operator !(Word word1)
        {
            String val = word1.binary;
            val = val.Replace('0', 'a');
            val = val.Replace('1', 'b');
            val = val.Replace('a', '1');
            val = val.Replace('b', '0');
            return new Word(val);
        }

        public static Word operator &(Word word1, Word word2)
        {
            return new Word((short)(word1.value & word2.value));
        }

        public static Word operator |(Word word1, Word word2)
        {
            return new Word((short)(word1.value | word2.value));
        }
        #endregion


    }
}
