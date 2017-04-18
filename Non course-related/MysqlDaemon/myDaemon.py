#!/usr/bin/env python
import ConfigParser
import os
import time
import sys, time, datetime
import smtplib
import base64
from daemon import Daemon
from subprocess import call

class MySqlDaemon(Daemon):
	def run(self):
		##runs until... nothing. xD
		print "Running daemonia!"
		while True:
			##Sleeptime! It's supposed to wait until certain date/time of the day
			print "sleeping..."
			time.sleep(30)
			##here we can get some configuration file or the like.
			target_h = 18
			target_m = 30
			
			now = datetime.datetime.now()
			
			if now.minute == target_m and now.hour == target_h:		
				#print "Running!"
				##Here we can also try to get theese from a .conf, but it's not needed yet
				files_to_send = []
				username = 'root'
				password = 'reverse'
				hostname = '192.168.1.110'
				
				filestamp = time.strftime('%Y-%m-%d')

				# Get a list of databases with :
				database_list_command="mysql -u%s -p%s -h %s --silent -N -e 'show databases'" % (username, password, hostname)
				for database in os.popen(database_list_command).readlines():
					database = database.strip()
				
					if database == 'information_schema':
						continue
					filename = "%s-%s.sql" % (database, filestamp)
					os.popen("mysqldump -u%s -p%s -h %s -e --opt -c %s | gzip -c > %s.gz" % (username, password, hostname, database, filename))								
					files_to_send.append(filename)
					
				##now send each file!
				for file in files_to_send:
					sendfile(file)
					##then delete.
					
				##call(["wall", "Running!!!!"]);
				

if __name__ == "__main__":
	daemon = MySqlDaemon('/tmp/mysql-daemon.pid')
	if len(sys.argv) == 2:
		if 'start' == sys.argv[1]:
			daemon.start()
		elif 'stop' == sys.argv[1]:
			daemon.stop()
		elif 'restart' == sys.argv[1]:
			daemon.restart()
		else:
			print "Unknown command"
			sys.exit(2)
		sys.exit(0)
	else:
		print "usage: %s start|stop|restart" % sys.argv[0]
		sys.exit(2)

def sendfile(filename, sender, reciever):
	# Read a file and encode it into base64 format
	fo = open(filename, "rb")
	filecontent = fo.read()
	encodedcontent = base64.b64encode(filecontent)  # base64
	
	marker = "anUniqueMarker"
	
	body ="""Database backup. %s
	"""
	body = body % (filename)
	# Define the main headers.
	part1 = """From: <%s>
	To: <%s>
	Subject: DatabaseBackup %s
	MIME-Version: 1.0
	Content-Type: multipart/mixed; boundary=%s
	--%s
	"""
	part1 = part1 % (filename, sender, reciever, marker, marker)
		
	# Define the message action
	part2 = """Content-Type: text/plain
	Content-Transfer-Encoding:8bit
	
	%s
	--%s
	"""
	part2 = part2 % (body,marker)
	
	# Define the attachment section
	part3 = """Content-Type: multipart/mixed; name=\"%s\"
	Content-Transfer-Encoding:base64
	Content-Disposition: attachment; filename=%s
	
	%s
	--%s--
	""" %(filename, filename, encodedcontent, marker)
	message = part1 + part2 + part3
	
	try:
	   ##now send an email with the data backup.
	   smtpObj = connectStmpServer("stmp.google.com","yourEmailAddress@yourDomain.com", "yourPassword")
	   smtpObj.sendmail(sender, reciever, message)
	   print "Successfully sent email"
	except Exception:
	   print "Error: unable to send email"
	
	
def connectSmtpServer(sender_imapServerAddress, senderAddress, sender_password):
	try:
		server = smtplib.SMTP(sender_imapServerAddress, 587)
		server.set_debuglevel(0)
		server.ehlo()
		server.starttls()
		server.login(sender_address, sender_password)
		return server
        except Exception as e:
		print e.message
		raise NameError('No se puede conectar a origen de datos SMTP')

