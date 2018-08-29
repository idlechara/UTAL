#!/bin/bash
echo "Laboratorio 8"
set -x #echo on


echo "Manejo Básico de Procesos"
echo "> 1. Use ps para buscar el proceso init por su nombre."
ps -ax | grep init

echo "> 2. ¿Cuál es el process id (pid) del proceso init?"
ps -ax | egrep "sbin\/init"  | awk '{ print $1 }' # asumiendo que es el primero (obviamente)

echo "> 3. Use el comando who am i para determinar el nombre de su terminal."
whoami

echo "> 4. Usando el nombre de su terminal obtenido anteriormente, use ps para encontrar todos los procesos asociados a tu terminal."
ps -u $(whoami)

echo "> 5. ¿Cuál es el process id de su shell?"
echo $$

echo "> 6. ¿Cuál es el process id del proceso al que está asociado su shell?"
( echo $$; echo $BASHPID )

echo "> 7. Inicie dos instancias de sleep 3342 tras bastidores (background)."
sleep 3342&
SLEEP_1=$!
sleep 3342&
SLEEP_2=$!

echo "> 8. Localice el process id de todos los comandos sleep."
pidof sleep

echo "> 9. Muestre solo aquellos dos procesos sleep en top. Luego termine top."
top -c -p $(pgrep -d',' -f string_to_match_in_cmd_line)

echo "> 10. Use un kill standard para terminar (matar) uno de los procesos sleep."
kill $SLEEP_1

echo "> 11. Use un solo comando kill para terminar con todos los procesos sleep."
killall sleep

echo "Procesos en background"
echo "> 1. Use el comando jobs para verificar si usted tiene algún proceso corriendo en background."
jobs

echo "> 2. Use vi para crear un pequeño archivo de texto. Deje suspendido vi en background."
touch new_text_file.txt
vi new_text_file.txt &

echo "> 3. Verifique, usando el comando jobs que vi está suspendido en background."
jobs

echo "> 4. Inicie find / > allfiles.txt 2>/dev/null en foreground y luego suspéndalo en background antes de que termine."
find / > allfiles.txt 2>/dev/null & #acá hay que usar atajos de teclado, de otra forma usar el comando a continuación
# find / > allfiles.txt 2>/dev/null 

echo "> 5. Inicie dos procesos sleep largos (long sleep) en background."
sleep 9999999
LONG_SLEEP_1=$!
sleep 9999999
LONG_SLEEP_2=$!

echo "> 6. Muestre todos los jobs en background."
jobs

echo "> 7. Use el comando kill para suspender el último proceso sleep."
kill $LONG_SLEEP_2

echo "> 8. Continúe el proceso find, que dejamos en background, y verifique que continuo su ejecución."
fg $(jobs | egrep 'vi' |  awk '{print $1}' | sed 's/[^0-9]*//g') # this assumes that there is only one instance running

echo "> 9. Traiga unos de los comandos sleep de vuelta al foreground."
fg $LONG_SLEEP_1