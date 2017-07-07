########################### RESUMEN RENDIMIENTO ###########################
set encoding utf8
set terminal postscript color eps "Helvetica" 16
set output "randomsummary1.eps"
set xlabel "dimensión"
set ylabel "tiempo [sec]"
set xtics 100000,3000000,9000000
set key below
set title "Resumen rendimiento - RANDOM - QuickSelect/BFPRT/iBFPRT/IntroQuickMedian"

plot [100000:9000000] [] \
      'random.log' using 1:2 title "QuickSelect" with linespoints lt rgb "violet", \
      'random.log' using 1:3 title "BFPRT" with linespoints lt rgb "brown", \
      'random.log' using 1:4 title "iBFPRT" with linespoints lt rgb "blue", \
      'random.log' using 1:5 title "IntroQuickMedian" with linespoints lt rgb "red"

set output "randomsummary2.eps"
set xlabel "dimensión"
set ylabel "tiempo [sec]"
set xtics 100,30000,100000
set title "Resumen rendimiento - RANDOM - QuickSelect/BFPRT/iBFPRT/IntroQuickMedian"

plot [100:100000] [] \
      'random.log' using 1:2 title "QuickSelect" with linespoints lt rgb "violet", \
      'random.log' using 1:3 title "BFPRT" with linespoints lt rgb "brown", \
      'random.log' using 1:4 title "iBFPRT" with linespoints lt rgb "blue", \
      'random.log' using 1:5 title "IntroQuickMedian" with linespoints lt rgb "red"

########################### RENDIMIENTO QuickSelect ###########################
set output "randomqs1.eps"
set xlabel "dimensión"
set ylabel "tiempo [sec]"
set xtics 100000,3000000,9000000
set title "Rendimiento - RANDOM - QuickSelect"

plot [100000:9000000] [] \
      'random.log' using 1:2 title "QuickSelect" with linespoints lt rgb "violet"

set output "randomqs2.eps"
set xlabel "dimensión"
set ylabel "tiempo [sec]"
set xtics 100,30000,100000
set title "Rendimiento - RANDOM - QuickSelect"

plot [100:100000] [] \
      'random.log' using 1:2 title "QuickSelect" with linespoints lt rgb "violet"

########################### RENDIMIENTO BFPRT ###########################
set output "randombfprt1.eps"
set xlabel "dimensión"
set ylabel "tiempo [sec]"
set xtics 100000,3000000,9000000
set title "Rendimiento - RANDOM - BFPRT"

plot [100000:9000000] [] \
     'random.log' using 1:3 title "BFPRT" with linespoints lt rgb "brown"

set output "randombfprt2.eps"
set xlabel "dimensión"
set ylabel "tiempo [sec]"
set xtics 100,30000,100000
set title "Rendimiento - RANDOM - BFPRT"

plot [100:100000] [] \
     'random.log' using 1:3 title "BFPRT" with linespoints lt rgb "brown"

########################### RENDIMIENTO iBFPRT ###########################
set output "randomibfprt1.eps"
set xlabel "dimensión"
set ylabel "tiempo [sec]"
set xtics 100000,3000000,9000000
set title "Rendimiento - RANDOM - iBFPRT"

plot [100000:9000000] [] \
     'random.log' using 1:3 title "BFPRT" with linespoints lt rgb "brown", \
     'random.log' using 1:4 title "iBFPRT" with linespoints lt rgb "blue"

set output "randomibfprt2.eps"
set xlabel "dimensión"
set ylabel "tiempo [sec]"
set xtics 100,30000,100000
set title "Rendimiento - RANDOM - iBFPRT"

plot [100:100000] [] \
     'random.log' using 1:3 title "BFPRT" with linespoints lt rgb "brown", \
     'random.log' using 1:4 title "iBFPRT" with linespoints lt rgb "blue"


########################### RENDIMIENTO IntroQuickMedian ###########################
set output "randomiqm1.eps"
set xlabel "dimensión"
set ylabel "tiempo [sec]"
set xtics 100000,3000000,9000000
set title "Rendimiento - RANDOM - IntroQuickMedian"

plot [100000:9000000] [] \
     'random.log' using 1:5 title "IntroQuickMedian" with linespoints lt rgb "red"

set output "randomiqm2.eps"
set xlabel "dimensión"
set ylabel "tiempo [sec]"
set xtics 100,30000,100000
set title "Rendimiento - RANDOM - IntroQuickMedian"

plot [100:100000] [] \
     'random.log' using 1:5 title "IntroQuickMedian" with linespoints lt rgb "red"

