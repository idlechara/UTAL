########################### RESUMEN RENDIMIENTO ###########################
set terminal postscript color eps "Helvetica" 16
set output "desortedsummary2.eps"
set xlabel "dimension"
set ylabel "tiempo [sec]"
set xtics 100,7000,30000
set logscale y 10
set key below
set title "Resumen rendimiento - DESORTED - QuickSelect/BFPRT/IntroQuickMedian"

plot [100:30000] [] \
      'desorted.log' using 1:2 title "QuickSelect" with linespoints lt rgb "violet", \
      'desorted.log' using 1:3 title "BFPRT" with linespoints lt rgb "brown", \
      'desorted.log' using 1:4 title "iBFPRT" with linespoints lt rgb "blue", \
      'desorted.log' using 1:5 title "IntroQuickMedian" with linespoints lt rgb "red"
unset logscale y
########################### RENDIMIENTO QuickSelect ###########################
set output "desortedqs2.eps"
set xlabel "dimension"
set ylabel "tiempo [sec]"
set xtics 100,7000,30000
#set ytics 0,50,450
#set grid
set key below
set title "Rendimiento - DESORTED - QuickSelect"

plot [100:30000] [] \
      'desorted.log' using 1:2 title "QuickSelect" with linespoints lt rgb "violet"

########################### RENDIMIENTO BFPRT ###########################
set output "desortedbfprt1.eps"
set xlabel "dimension"
set ylabel "tiempo [sec]"
set xtics 100000,300000,900000
#set ytics 0,50,450
#set grid
set key below
set title "Rendimiento - DESORTED - BFPRT"

plot [100000:900000] [] \
     'desorted.log' using 1:3 title "BFPRT" with linespoints lt rgb "brown"

set output "desortedbfprt2.eps"
set xlabel "dimension"
set ylabel "tiempo [sec]"
set xtics 100,30000,100000
#set ytics 0,50,450
#set grid
set key below
set title "Rendimiento - DESORTED - BFPRT"

plot [100:100000] [] \
     'desorted.log' using 1:3 title "BFPRT" with linespoints lt rgb "brown"

########################### RENDIMIENTO iBFPRT ###########################
set output "desortedibfprt1.eps"
set xlabel "dimension"
set ylabel "tiempo [sec]"
set xtics 100000,300000,900000
#set ytics 0,50,450
#set grid
set key below
set title "Rendimiento - DESORTED - iBFPRT"

plot [100000:900000] [] \
     'desorted.log' using 1:3 title "BFPRT" with linespoints lt rgb "brown", \
     'desorted.log' using 1:4 title "iBFPRT" with linespoints lt rgb "blue"

set output "desortedibfprt2.eps"
set xlabel "dimension"
set ylabel "tiempo [sec]"
set xtics 100,30000,100000
#set ytics 0,50,450
#set grid
set key below
set title "Rendimiento - DESORTED - iBFPRT"

plot [100:100000] [] \
     'desorted.log' using 1:3 title "BFPRT" with linespoints lt rgb "brown", \
     'desorted.log' using 1:4 title "iBFPRT" with linespoints lt rgb "blue"


########################### RENDIMIENTO IntroQuickMedian ###########################
set output "desortediqm1.eps"
set xlabel "dimension"
set ylabel "tiempo [sec]"
set xtics 100000,300000,900000
#set ytics 0,50,450
#set grid
set key below
set title "Rendimiento - DESORTED - IntroQuickMedian"

plot [100000:900000] [] \
     'desorted.log' using 1:5 title "IntroQuickMedian" with linespoints lt rgb "red"

set output "desortediqm2.eps"
set xlabel "dimension"
set ylabel "tiempo [sec]"
set xtics 100,30000,100000
#set ytics 0,50,450
#set grid
set key below
set title "Rendimiento - DESORTED - IntroQuickMedian"

plot [100:100000] [] \
     'desorted.log' using 1:5 title "IntroQuickMedian" with linespoints lt rgb "red"

