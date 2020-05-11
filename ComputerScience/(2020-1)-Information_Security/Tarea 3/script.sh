#!/bin/bash
## instalar herramientas
sudo apt-get install steghide

## obtener imagen portadora
wget -O carrier.jpg https://avatars2.githubusercontent.com/u/3944601\?s\=400\&u\=15c877e746043bd5136706f0f51ce6bc0d69353c\&v\=4

## guardar texto
echo "haha. Esto podria ser un ejecutable." > payload.txt

## embed contenido en archivo
steghide embed -p le_passwd  -cf carrier.jpg -ef payload.txt -sf new_carrier.jpg

## compare
cmp carrier.jpg new_carrier.jpg
xxd carrier.jpg > carrier.hex
xxd new_carrier.jpg > new_carrier.hex
diff carrier.hex new_carrier.hex #check that it's important to preserve the header

## delete originals
rm carrier.jpg payload.txt

## retrieve file
steghide extract -p le_passwd  -sf new_carrier.jpg -xf payload_retrieved.txt

## haha
cat payload_retrieved.txt
