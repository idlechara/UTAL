#!/bin/bash
echo "Laboratorio 9"
set -x #echo on

echo "1. Ver la vesión de iptables."
iptables --version
iptables -S

echo "2. Borrado de todas las reglas."
iptables -F
iptables -S

echo "3. Añadir una regla a la cadena INPUT para aceptar todos los paquetes que se originan desde la dirección 192.168.0.155."
iptables -A INPUT -p tcp -s 192.168.0.155
iptables -S

echo "4. Descartar todos los paquetes que entren."
iptables -P INPUT DROP
iptables -P FORWARD DROP
# iptables -P OUTPUT ACCEPT
iptables -S

echo "5. Permitir la salida de paquetes."
iptables -P OUTPUT ACCEPT
iptables -S

echo "6. Añadir una regla a la cadena INPUT para rechazar todos los paquetes que se originan desde la dirección 192.168.0.155."
iptables -A INPUT -j REJECT -p tcp -s 192.168.0.155
iptables -S

echo "7. Añadir una regla a la cadena INPUT para rechazar todos los paquetes que se originan desde la dirección de red 192.168.0.0."
iptables -A INPUT -j REJECT -p tcp -s 192.168.0.0/24
# iptables -I INPUT -m iprange --src-range 192.168.0.0-192.168.0.255 -j DROP
iptables -S

echo "8. Añadir una regla a la cadena INPUT para rechazar todos los paquetes que se originan desde la dirección 192.168.0.155 y enviar un mensaje de error icmp."
iptables -A INPUT -j REJECT -p tcp -s 192.168.0.155 --reject-with icmp-host-prohibited
iptables -S

echo "9. Permitir conexiones locales (al localhost), por ejemplo, a mysql."
iptables -A INPUT -i lo -j ACCEPT -p tcp --dport 3306
iptables -A OUTPUT -o lo -j ACCEPT --sport 3306 -j ACCEPT
iptables -S

echo "10. Permitir el acceso a nuestro servidor web (puerto TCP 80)."
# /bin/echo "1" > /proc/sys/net/netfilter/nf_conntrack_acct # enable accounting for conntrack
iptables -A INPUT -j ACCEPT -p tcp --dport 80
iptables -A OUTPUT -p tcp --sport 80 -j ACCEPT
iptables -S

echo "11. Permitir el acceso a nuestro servidor ftp (puerto TCP 20 y 21)."
iptables -A INPUT -j ACCEPT -p tcp --dport 20
iptables -A OUTPUT -p tcp --sport 20 -j ACCEPT
iptables -A INPUT -j ACCEPT -p tcp --dport 21
iptables -A OUTPUT -p tcp --sport 21 -j ACCEPT
iptables -S

echo "12. Permitir a la máquina con IP 192.168.0.155 conectarse a nuestro equipo a través de SSH."
iptables -A INPUT -j ACCEPT -p tcp --dport 22 -s 192.168.0.155
iptables -A OUTPUT -p tcp --sport 22 -j ACCEPT
iptables -S

echo "13. Rechazar a la máquina con IP 192.168.0.155 conectarse a nuestro equipo a través de Telnet."
iptables -A INPUT -j REJECT -p tcp --dport 20 -s 192.168.0.155
iptables -S

echo "14. Rechazar las conexiones que se originen de la máquina con la dirección física 00:db:f0:34:ab:78."
iptables -A INPUT -m mac --mac-source 00:db:f0:34:ab:78 -j DROP
iptables -S

echo "15. Rechazar todo el tráfico que ingrese a nuestra red LAN 192.168.0.0 /24 desde una red remota, como Internet, a través de la interfaz eth0."
# eth0 is no longer used because systemd!
# this requieres a flush, maybe
iptables -A INPUT -p tcp --dport 22 -s 192.168.0.0/24 -j ACCEPT -i eth0 -d 192.168.0.0/24
iptables -A INPUT -p tcp --dport 22 -s 127.0.0.0/8 -j ACCEPT -i eth0 -d 192.168.0.0/24
iptables -A INPUT -p tcp --dport 22 -j DROP -i eth0 -d 192.168.0.0/24
iptables -S
