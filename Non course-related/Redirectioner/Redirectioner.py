# -*- coding: utf-8 -*-
"""
Created on Fri Jun 28 12:03:54 2013

@author: kynku
"""
import re
import sys
import uuid
import re
import smtplib
import email
import imaplib

class MailRedirection:
    def __init__(self):
        self._origin_avaliable = False
        self._destination_avaliable = False
        self._sender_avaliable = False

        
    def setOrigin(self,address, password, imapServerAddress):
        self._origin_address = address
        self._origin_password = password
        self._origin_imapServerAddress = imapServerAddress
        if (address is None and  password is None and imapServerAddress is None):
            self._origin_avaliable = True
        
    def setSender(self,address, password, imapServerAddress):
        self._sender_address = address
        self._sender_password = password
        self._sender_imapServerAddress = imapServerAddress
        if (address is None and  password is None and imapServerAddress is None):
            self._sender_avaliable = True
        
    def setDestination(self,address):
        self._destination_address = address
        if (address is None):
            self._destination_avaliable = True
        
    def setCopySettingsOriginToSender(self):
        self._sender_address = self._origin_address
        self._sender_imapServerAddress= self._origin_imapServerAddress
        self._sender_password= self._origin_password
        self._sender_avaliable = self._sender_avaliable

    def checkInboxUnreadMails(self):
        self._M.select("INBOX")
        #typ, data = self._M.search(None, 'Unseen')
        typ, data = self._M.search(None, '(FROM "kynku.nekoi@gmail.com")')
    
        messageCount = len(data[0].split())    
        if messageCount > 0:
            return data[0].split()

    def connectImapServer(self):
        self._M = imaplib.IMAP4_SSL(self._origin_imapServerAddress)
        self._M.login(self._origin_address, self._origin_password)        
    
    def disconnectImapServer(self):
        self._M.close()
        self._M.logout()            
    
    def filterViaSubjectField(self,data, subjectCriteria):
        mails = []
        for mailIdx in data:
            typ, data = self._M.fetch(mailIdx, '(BODY[])')
            if (self.getSubjectField(data) == subjectCriteria):
                mails.push(data[1])
        return mails
        
    def filterViaDomainField(self,data, domainCriteria):
        mails = []
        for mailIdx in data:
            typ, data = self._M.fetch(mailIdx, '(BODY[])')
            if (self.getFromDomainField(data) == domainCriteria):
               mails.push(data[1])
        return mails   
        
    def filterViaFromField(self,data, fromCriteria):
        mails = []
        for mailIdx in data:
            typ, data = self._M.fetch(mailIdx, '(BODY[])')
            if (self.getFromField(data) == fromCriteria):
                mails.push(data[1])
        return mails      
        
    def getFromField(self, data):
        return re.findall("From: .*$",data[0][1],re.MULTILINE)[0].replace("From: ", "", 1).strip()
    def getFromDomainField(self, data):
        return re.findall("From: .*$",data[0][1],re.MULTILINE)[0].replace("From: ", "", 1).split("@")[1].strip().replace(">", "", 1)
    def getSubjectField(self, data):
        return re.findall("Subject: .*$",data[0][1],re.MULTILINE)[0].replace("Subject: ", "", 1).strip()
        

    def connectSmtpServer(self):
        self.__server = smtplib.SMTP(self._sender_imapServerAddress, 587)
        self.__server.set_debuglevel(1)
        self.__server.ehlo()
        self.__server.starttls()
        self.__server.login(self._sender_address, self._sender_password)

    def disconnectSmtpServer(self):
        self.__server.quit()        

    def sendMail(self, raw_message):
        self.__server.sendmail(self._sender_address, self._destination_address, raw_message)
        
    def startThread(self):
        return
    def stopThread(self):
        return
    def getStats(self):
        return
    
print "Inicializando"
i = MailRedirection()

print "seteando origen"
i.setOrigin("example@example.com","password", "imap.example.com")

print "conectando a server..."
i.connectImapServer()

print "leyendo..."
unread = i.checkInboxUnreadMails()
print i.filterViaFromField(unread, "example2@gmail.com")