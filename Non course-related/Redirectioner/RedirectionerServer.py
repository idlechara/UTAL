# -*- coding: utf-8 -*-
"""
Created on Fri Jun 28 12:03:54 2013

@author: kynku
"""
import re
import smtplib
import imaplib
import threading
import time

class MailRedirection:
    def __init__(self):
        self.__origin_avaliable = False
        self.__destination_avaliable = False
        self.__sender_avaliable = False
        self.__processThread = threading.Thread(target=self.mainLoop, args=())
        self.__signalFlag = False
        self.__destination_avaliable = False
        self.__sender_avaliable = False
        self.__origin_avaliable = False
        self.__imap_connected = False
        self.__smtp_connected = False

    def __sendKillSignal(self):
        self.__signalFlag = True
        
    def setOrigin(self,address, password, imapServerAddress):
        self.__origin_address = address
        self.__origin_password = password
        self.__origin_imapServerAddress = imapServerAddress
        if (address is None and  password is None and imapServerAddress is None):
            self.__origin_avaliable = True
        
    def setSender(self,address, password, imapServerAddress):
        self.__sender_address = address
        self.__sender_password = password
        self.__sender_imapServerAddress = imapServerAddress
        if (address is None and  password is None and imapServerAddress is None):
            self.__sender_avaliable = True
        
    def setDestination(self,address):
        self.__destination_address = address
        if (address is None):
            self.__destination_avaliable = True
        
    def setCopySettingsOriginToSender(self):
        self.__sender_address = self.__origin_address
        self.__sender_imapServerAddress= self.__origin_imapServerAddress
        self.__sender_password= self.__origin_password
        self.__sender_avaliable = self.__sender_avaliable

    def connectImapServer(self):
        try:
            if (self.__origin_avaliable == False):
                raise NameError('Origen de datos IMAP no establecido')
            self.__M = imaplib.IMAP4_SSL(self.__origin_imapServerAddress)
            self.__M.login(self.__origin_address, self.__origin_password)
            self.__imap_connected = True
        except Exception as e:
            print e.message
            raise NameError('No se puede conectar a origen de datos IMAP')
                
        
    def disconnectImapServer(self):
        try:
            if (self.__imap_connected == False):
                raise NameError('No existe conexión a origen de datos IMAP activa.')
            self.__M.close()
            self.__M.logout()            
            self.__imap_connected = False
        except Exception as e:
            print e.message
            raise NameError('Error durante el cierre de conexion IMAP')
        
    def connectSmtpServer(self):
        try:
            if (self.__sender_avaliable == False):
                raise NameError('Origen de datos SMTP no establecido')
            self.__server = smtplib.SMTP(self.__sender_imapServerAddress, 587)
            #self.__server.set_debuglevel(1)
            self.__server.set_debuglevel(0)
            self.__server.ehlo()
            self.__server.starttls()
            self.__server.login(self.__sender_address, self.__sender_password)
            self.__smtp_connected = True
        except Exception as e:
            print e.message 
            raise NameError('No se puede conectar a origen de datos SMTP')


    def disconnectSmtpServer(self):
        try:
            if (self.__imap_connected == False):
                raise NameError('No existe conexión a origen de datos SMTP activa.')
            self.__server.quit()        
            self.__smtp_connected = False
        except Exception as e:
            print e.message
            raise NameError('Error durante el cierre de conexion SMTP')

    def __sendMailViaSmtp(self, raw_message):        
        try:
            self.__server.sendmail(self.__sender_address, self.__destination_address, raw_message)
            #self.__server.sendmail(self.__sender_address, self.__getFromField(raw_message), raw_message)
        except Exception as e:
            print e.message
            raise NameError('Error en reenvio de datos')
        

    def __checkInboxUnreadMails(self):
        try:
            self.__M.select("INBOX")
            typ, data = self.__M.search(None, 'Unseen')
            #typ, data = self.__M.search(None, '(FROM "kynku.nekoi@gmail.com")')
            messageCount = len(data[0].split())    
            if messageCount > 0:
                return data[0].split()
        except Exception as e:
            print e.message
            raise NameError('Error durante la recuperacion de correos no vistos')
            
            
    def __filterViaSubjectField(self,data, subjectCriteria):
        try:
            mails = []
            for mailIdx in data:
                typ, data = self.__M.fetch(mailIdx, '(BODY[])')
                if (self.__getSubjectField(data[0][1]) == subjectCriteria):
                    mails.append(data[0][1])
            return mails
        except Exception as e:
            print e.message
            raise NameError('Error en filtrado via asunto')

        
    def __filterViaDomainField(self,data, domainCriteria):
        try:
            mails = []
            for mailIdx in data:
                typ, data = self.__M.fetch(mailIdx, '(BODY[])')
                if (self.__getFromDomainField(data[0][1]) == domainCriteria):
                    mails.append(data[0][1])
            return mails   
        except Exception as e:
            print e.message
            raise NameError('Error en filtrado via dominio')
        
    def __filterViaFromField(self,data, fromCriteria):
        try:        
            mails = []
            for mailIdx in data:
                typ, data = self.__M.fetch(mailIdx, '(BODY[])')
                if (self.__getFromField(data[0][1]) == fromCriteria):
                    mails.append(data[0][1])
            return mails      
        except Exception as e:
            print e.message
            raise NameError('Error en filtrado via direccion entrante')
        
    def __filterWithNone(self, data):
        try:
            mails = []
            for mailIdx in data:
                typ, data = self.__M.fetch(mailIdx, '(BODY[])')
                mails.append(data[0][1])
            return mails
        except Exception as e:
            print e.message
            raise NameError('Error en filtrado via direccion entrante')
        
    def __getFromField(self, data):
        try:            
            print data
            dat = re.findall("From: .*$",data,re.MULTILINE)[0].split("<")
            return dat[len(dat)-1].replace(">", "", 1).strip()
        except Exception as e:
            print e.message
            raise NameError('No se puede recuperar direccion entrante')
        
        
    def __getFromDomainField(self, data):
        try:
            return str(self.__getFromField(data)).split("@")[1]
        except Exception as e:
            print e.message
            raise NameError('No se puede recuperar dominio')
            
        
    def __getSubjectField(self, data):
        try:
            return re.findall("Subject: .*$",data,re.MULTILINE)[0].replace("Subject: ", "", 1).strip()
        except Exception as e:
            print e.message
            raise NameError('No se puede recuperar asunto')
        
        
    def mainLoop(self):
        try:
            while (self.__signalFlag == False):
                unread = None
                unread = self.__checkInboxUnreadMails()
                if unread is not None:
                    if len(unread) > 0:
                        ##Change filter here!
                        mail = self.__filterWithNone(unread)
                        for message in mail:                        
                            self.__sendMailViaSmtp(message)
                            print "Reenviando """ + str(self.__getSubjectField(message)) + """ de """ + str(self.__getFromField(message)) + """ a """ + str(self.__destination_address)
                time.sleep(1)
        except Exception as e:
            print e.message
            raise NameError('Error durante la ejecución del hilo principal')
        
        
    def startThread(self):
        try:
            print "Conectando a fuente de datos IMAP... (""" + str(self.__origin_address) + """@""" + str(self.__origin_imapServerAddress) + """)"""
            self.connectImapServer()
            print "Conectando a fuente de datos SMTP...(""" + str(self.__sender_address) + """@""" + str(self.__sender_imapServerAddress) + """)"""
            self.connectSmtpServer()
            print "Arrancando Hilo..."
            self.__processThread.start()
        except Exception as e:
            print e.message
            raise NameError('Error en inicio de hilo principal')
        
        
    def stopThread(self):
        try:
            print "Enviando señal de termino..."
            self.__sendKillSignal()
            print "Uniendo threads..."
            self.__processThread.join
            print "Desconectando IMAP..."
            self.connectImapServer()
            print "Desconectando SMTP..."
            self.connectSmtpServer()
            return
        except Exception as e:
            print e.message
            raise NameError('Error en detencion de hilo principal')            
        
    def getStats(self):
        if self.__signalFlag == False:
            print "El hilo esta corriendo."
        if self.__signalFlag == True:
            print "El hilo no esta corriendo."
        return
    
    
print "Inicializando"
i = MailRedirection()
i.setOrigin("example@example.com","password", "imap.example.com")
i.setDestination("example@example.com")
i.setCopySettingsOriginToSender()
i.startThread()
#i.connectImapServer()
#i.connectSmtpServer()
#i.mainLoop()