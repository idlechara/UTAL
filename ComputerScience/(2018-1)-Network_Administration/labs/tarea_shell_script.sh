#!/bin/bash
echo "Laboratorio 6"
set -x #echo on


echo "Listar el contenido del directorio /etc:"
echo ">   Ordenar el listado por fecha de modificación (mostrar primero los archivos más recientes)"
cd /etc/
ls -tl

echo ">   Ordenar el listado por fecha de modificación (mostrar primero los archivos más antiguos)"
ls -tlr

echo ">   Mostrar los tamaños de archivo en unidades amigables (KB, MB, GB)"
ls -hl

echo ">   Listar primero los directorios y luego los archivos"
ls -hl --group-directories-first

echo ">   Ordenar por tamaño de archivo (de mayor a menor)"
ls -S -l

echo ">   Ordenar por tamaño de archivo (de menor a mayor)"
ls -S -r

echo ">   Crear el alias 'l' que muestre una línea por cada ítem junto con los permisos, usuario, grupo, tamaño (en unidades amigables), y que además muestre primero los directorios y luego los archivos"
alias l='ls -lh --group-directories-first'

echo "Determinar en una única línea de comandos cuántos elementos posee un directorio (sin contar los elementos '.' y '..')."
ls -l | grep -v ^l | wc -l

echo ">   Determinar en una única línea de comandos cuántas palabras (separadas por espacio) contiene un archivo de texto."
cat fstab| wc -w
# In this example, we use ubuntu's examples.desktop

echo ">   Volcar por línea de comandos el contenido del archivo /etc/fstab exceptuando las líneas que contengan el caracter '#'."
cat fstab | grep -v "#"

echo ">   Volcar por línea de comandos el contenido del archivo /etc/fstab exceptuando las 3 primeras líneas."
tail -n +3 fstab

echo ">   Buscar recursivamente dentro del directorio /etc todos los archivos cuyo nombre comienza con 'net' (case insensitive)."
find . -name 'net*'

echo ">   Determinar cuánto espacio en disco ocupa el directorio /home."
du -hcs /home

echo ">   Determinar el PID (Process ID) del proceso init."
ps ax | grep init

echo ">   Crear un script bash pepe.sh que se ejecute automáticamente cada vez que se inicia el sistema. En cada ejecución debe crear (y sobreescribir) el archivo /var/log/pepelastrun.txt, el cual debe contener la fecha y hora de la última ejecución del script."

printf "#\041/bin/bash \n date > /var/log/pepelastrun.txt" > pepe.sh
chmod a+x pepe.sh
chmod 777 pepe.sh
cp pepe.sh /etc/init.d/
update-rc.d pepe.sh defaults

echo ">   Crear el archivo pepe1995.txt dentro del directorio /tmp cuya fecha de modificación sea el día 31 de diciembre de 1995 a la hora 23:59:59."
touch /tmp/pepe1995.txt
touch -mt 199512312359.59 /tmp/pepe1995.txt

echo ">   Buscar dentro del directorio \$HOME los archivos que hayan sido modificados el día de ayer."
find ./ -mtime -1

echo ">   Buscar dentro del directorio /var/log los archivos que hayan sido modificados entre ayer y anteayer."
find /var/tmp -mtime +1 -a -mtime -3 -ls

echo "Determinar en una línea de comandos los tipos de filesystems montados utilizando una única columna, por ejemplo debe retornar:"
cat /proc/filesystems | awk '{ print $2 }' | awk 'NF'

echo "Mantenimiento de usuarios:"
echo '>   Crear el usuario "pepe" cuyo directorio $HOME sea /home/pepe'
useradd -m -d /home/pepe pepe

echo '>   Asignar un nuevo password al usuario "pepe"'
echo pepe:password | /usr/sbin/chpasswd

echo '>   Agregar al usuario "pepe" al grupo "wheel"'
#puede ser necesario groupadd wheel
usermod -a -G wheel pepe

echo '>   Cambiar el nombre del usuario "pepe" a "pedro"'
usermod -l pedro pepe

echo '>   Determinar a qué grupos pertenece el usuario "pedro"'
groups pedro

echo '>   Permitir el uso del comando "sudo" al usuario "pedro"'
adduser pedro sudo

echo '>   Denegar el uso del comando "sudo" al usuario "pedro"'
deluser pedro sudo

echo '>   Eliminar al usuario "pedro" junto con su directorio $HOME'
deluser --remove-home pedro

echo 'Listar los últimos comandos ejecutados en la sesión actual.'
history

echo 'Determinar en una línea de comandos cuánto espacio libre queda en las diferentes particiones del sistema.'
df -h

echo 'Manejo de archivos:'
echo '>   Crear el directorio /tmp/pepe2/'
mkdir /tmp/pepe2/
echo '>   Copiar todos los archivos de nuestro directorio $HOME al directorio /tmp/pepe2/ preservando todas las propiedades de los mismos (owners, permisos, fechas, etc.)'
cp -rfvp $HOME/* /tmp/pepe2/

echo '>   Mover el directorio /tmp/pepe2/ a /tmp/pepe3/'
mv /tmp/pepe2/ /tmp/pepe3/

echo '>   Dar permiso de ejecución a todos los archivos dentro del directorio /tmp/pepe3/'
chmod +x --recursive /tmp/pepe3/

echo '>   Quitar permiso de escritura a todos los archivos dentro del directorio /tmp/pepe3/'
chmod +w --recursive /tmp/pepe3/

echo '>   Eliminar el directorio /tmp/pepe3/ junto con todo su contenido'
rm -rfv /tmp/pepe3

echo 'Manejo de procesos:'
echo '>   Determinar el userid efectivo de la sesión actual'
id -u $USER

echo '>   Ejecutar en background (segundo plano) el comando ping 8.8.8.8 > /dev/null'
ping 8.8.8.8 > /dev/null &

echo '>   Ejecutar el comando top, una vez iniciado enviarlo a segundo plano.'
#control+z, then execute bg

echo '>   Mostrar todos los procesos que se están ejecutando con el mismo userid efectivo que la sesión actual'
ps -u $USER

echo '>   Mostrar un árbol sólo de los procesos que se están ejecutando con el mismo userid efectivo que la sesión actual'
ps f -u $USER

echo '>   Mostrar todos los procesos ejecutando como usuario "root" (ID efectivo y real) en formato de usuario'
ps -U root -u root u

echo '>   Determinar el PID (Process ID) del proceso ejecutando el comando ping 8.8.8.8 > /dev/null'
ps ax | grep ping

echo '>   Bajar al mínimo la prioridad de ejecución del proceso ejecutando el comando ping 8.8.8.8 > /dev/null'
renice 19 -p $(ps -u $USER|grep ping|awk '{ print $1 }')

echo '>   Matar el proceso ejecutando el comando ping 8.8.8.8 > /dev/null'
kill -6 $(ps -u $USER|grep ping|awk '{ print $1 }')

echo '>   Determinar qué proceso está consumiendo más CPU'
ps --sort=-pcpu | head -n 1

echo '>   Determinar qué proceso está consumiendo más memoria RAM'
ps --sort=-pmem | head -n 1

echo '>   Determinar cuanta memoria RAM disponible (libre) posee el sistema'
free -h

echo '>   Traer a primer plano el proceso ejecutando el comando top'
# fg <id proceso>, requires to run top and detach it first

echo 'Crear un directorio llamado "A" que contenga los archivos "doc1.txt", "doc3.txt" y "doc77.txt". Crear un directorio llamado "B" que contenga los archivos "doc1.txt", "doc4.txt" y "doc89.txt".'
mkdir A
touch A/doc1.txt
touch A/doc3.txt
touch A/doc77.txt
mkdir B
touch B/doc1.txt
touch B/doc4.txt
touch B/doc89.txt

echo '>   Listar, utilizando una única línea de comandos, los nombres de archivo que se encuentran en el directorio "A" pero no en el directorio "B"'
ls A
echo '>   Listar, utilizando una única línea de comandos, los nombres de archivo que se encuentran tanto en el directorio "A" como en el directorio "B"'
ls B

echo 'Comprimir los directorios "A" y "B" utilizando el algoritmo "gzip" en un único archivo llamado "ab.tar.gz".'
tar -czf ab.tar.gz A B


