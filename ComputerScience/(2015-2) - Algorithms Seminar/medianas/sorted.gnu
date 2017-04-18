########################### RESUMEN RENDIMIENTO ###########################
set terminal postscript color eps "Helvetica" 16
set output "sortedsummary2.eps"
set xlabel "dimension objetos"
set ylabel "tiempo [sec]"
set xtics 100,30000,100000
#set ytics 0,50,450
#set grid
set logscale y 10
set key below
set title "Resumen rendimiento - SORTED - QuickSelect/BFPRT/IntroQuickMedian"

plot [100:30000] [] \
      'sorted.log' using 1:2 title "QuickSelect" with linespoints lt rgb "violet", \
      'sorted.log' using 1:3 title "BFPRT" with linespoints lt rgb "brown", \
      'sorted.log' using 1:4 title "iBFPRT" with linespoints lt rgb "blue", \
      'sorted.log' using 1:5 title "IntroQuickMedian" with linespoints lt rgb "red"
unset logscale y
########################### RENDIMIENTO QuickSelect ###########################
set output "sortedqs2.eps"
set xlabel "dimension objetos"
set ylabel "tiempo [sec]"
set xtics 100,7000,30000
#set ytics 0,50,450
#set grid
set key below
set title "Rendimiento - SORTED - QuickSelect"

plot [100:30000] [] \
      'sorted.log' using 1:2 title "QuickSelect" with linespoints lt rgb "violet"

########################### RENDIMIENTO BFPRT ###########################
set output "sortedbfprt1.eps"
set xlabel "dimension"
set ylabel "tiempo [sec]"
set xtics 100000,300000,900000
#set ytics 0,50,450
#set grid
set key below
set title "Rendimiento - SORTED - BFPRT"

plot [100000:900000] [] \
     'sorted.log' using 1:3 title "BFPRT" with linespoints lt rgb "brown"

set output "sortedbfprt2.eps"
set xlabel "dimension"
set ylabel "tiempo [sec]"
set xtics 100,30000,100000
#set ytics 0,50,450
#set grid
set key below
set title "Rendimiento - SORTED - BFPRT"

plot [100:100000] [] \
     'sorted.log' using 1:3 title "BFPRT" with linespoints lt rgb "brown"

########################### RENDIMIENTO iBFPRT ###########################
set output "sortedibfprt1.eps"
set xlabel "dimension"
set ylabel "tiempo [sec]"
set xtics 100000,300000,900000
#set ytics 0,50,450
#set grid
set key below
set title "Rendimiento - SORTED - iBFPRT"

plot [100000:900000] [] \
     'sorted.log' using 1:3 title "BFPRT" with linespoints lt rgb "brown", \
     'sorted.log' using 1:4 title "iBFPRT" with linespoints lt rgb "blue"

set output "sortedibfprt2.eps"
set xlabel "dimension"
set ylabel "tiempo [sec]"
set xtics 100,30000,100000
#set ytics 0,50,450
#set grid
set key below
set title "Rendimiento - SORTED - iBFPRT"

plot [100:100000] [] \
     'sorted.log' using 1:3 title "BFPRT" with linespoints lt rgb "brown", \
     'sorted.log' using 1:4 title "iBFPRT" with linespoints lt rgb "blue"


########################### RENDIMIENTO IntroQuickMedian ###########################
set output "sortediqm1.eps"
set xlabel "dimension"
set ylabel "tiempo [sec]"
set xtics 100000,300000,900000
#set ytics 0,50,450
#set grid
set key below
set title "Rendimiento - SORTED - IntroQuickMedian"

plot [100000:900000] [] \
     'sorted.log' using 1:5 title "IntroQuickMedian" with linespoints lt rgb "red"

set output "sortediqm2.eps"
set xlabel "dimension"
set ylabel "tiempo [sec]"
set xtics 100,30000,100000
#set ytics 0,50,450
#set grid
set key below
set title "Rendimiento - SORTED - IntroQuickMedian"

plot [100:100000] [] \
     'sorted.log' using 1:5 title "IntroQuickMedian" with linespoints lt rgb "red"

