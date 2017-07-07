########################### RESUMEN RENDIMIENTO ###########################
set encoding utf8

set terminal postscript color eps "Helvetica" 16
set output "randomprecision2.eps"
set xlabel "dimensión"
set ylabel "precisión [%]"
set xtics 90000,2000000,9000000
set key below
set title "Resumen precisión - RANDOM - QuickSelect/BFPRT/iBFPRT/IntroQuickMedian"
plot [90000:9000000] [] \
      'random_precision.log' using 1:2 title "QuickSelect" with linespoints lt rgb "violet", \
      'random_precision.log' using 1:3 title "BFPRT" with linespoints lt rgb "brown", \
      'random_precision.log' using 1:4 title "iBFPRT" with linespoints lt rgb "blue", \
      'random_precision.log' using 1:5 title "IntroQuickMedian" with linespoints lt rgb "red"

########################### RESUMEN RENDIMIENTO ###########################
set output "randomprecision1.eps"
set xlabel "dimensión"
set ylabel "precisión [%]"
set xtics 100,30000,90000
set title "Resumen precisión - RANDOM - QuickSelect/BFPRT/iBFPRT/IntroQuickMedian"
plot [100:90000] [] \
      'random_precision.log' using 1:2 title "QuickSelect" with linespoints lt rgb "violet", \
      'random_precision.log' using 1:3 title "BFPRT" with linespoints lt rgb "brown", \
      'random_precision.log' using 1:4 title "iBFPRT" with linespoints lt rgb "blue", \
      'random_precision.log' using 1:5 title "IntroQuickMedian" with linespoints lt rgb "red"


########################### RESUMEN RENDIMIENTO ###########################
set output "desortedprecision2.eps"
set xlabel "dimensión"
set ylabel "precisión [%]"
set xtics 90000,2000000,9000000
set title "Resumen precisión - WORST - QuickSelect/BFPRT/iBFPRT/IntroQuickMedian"
plot [90000:9000000] [] \
      'desorted_precision.log' using 1:2 title "QuickSelect" with linespoints lt rgb "violet", \
      'desorted_precision.log' using 1:3 title "BFPRT" with linespoints lt rgb "brown", \
      'desorted_precision.log' using 1:4 title "iBFPRT" with linespoints lt rgb "blue", \
      'desorted_precision.log' using 1:5 title "IntroQuickMedian" with linespoints lt rgb "red"

########################### RESUMEN RENDIMIENTO ###########################
set output "desortedprecision1.eps"
set xlabel "dimensión"
set ylabel "precisión [%]"
set xtics 100,30000,90000
set title "Resumen precisión - WORST - QuickSelect/BFPRT/iBFPRT/IntroQuickMedian"
plot [100:90000] [] \
      'desorted_precision.log' using 1:2 title "QuickSelect" with linespoints lt rgb "violet", \
      'desorted_precision.log' using 1:3 title "BFPRT" with linespoints lt rgb "brown", \
      'desorted_precision.log' using 1:4 title "iBFPRT" with linespoints lt rgb "blue", \
      'desorted_precision.log' using 1:5 title "IntroQuickMedian" with linespoints lt rgb "red"

