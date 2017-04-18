__author__ = 'jvarred'
from TwitterAPI import TwitterAPI
import sqlite3
import urllib
import os
import logging
import time
from datetime import datetime

def init_db():
    conn = sqlite3.connect('kuky.db')
    c = conn.cursor()

    c.execute("SELECT * FROM sqlite_master WHERE type='table';")
    if(len(c.fetchall()) < 1):
        c.execute('''CREATE TABLE tweets
             (id text)''')
        conn.commit()
        c.execute("INSERT INTO tweets VALUES ('0')")
        conn.commit()

logging.captureWarnings(True)


CONSUMER_KEY = 'YOUR_CONSUMER_KEY'
CONSUMER_SECRET = 'YOUR_CONSUMER_SECRET'
ACCESS_TOKEN_KEY = 'YOUR_ACCESS_TOKEN_KEY'
ACCESS_TOKEN_SECRET = 'YOUR_ACCESS_TOKEN_SECRET'

proxy_url = None  # Example: 'https://USERNAME:PASSWORD@PROXYSERVER:PORT'

api = TwitterAPI(CONSUMER_KEY,
                 CONSUMER_SECRET,
                 ACCESS_TOKEN_KEY,
                 ACCESS_TOKEN_SECRET,
                 auth_type='oAuth1',
                 proxy_url=proxy_url)

conn = sqlite3.connect('kuky.db')
init_db()


def exists_id(id):
    c = conn.cursor()
    c.execute("SELECT id FROM tweets WHERE id='"+id+"'")
    if len(c.fetchall()) > 0:
        return True
    return False

def add_id(id):
    if not exists_id(id):
        c = conn.cursor()
        c.execute("INSERT INTO tweets VALUES ('"+id+"')")
        conn.commit()
#
# while True:
#     try:
#         r = api.request('statuses/filter', {'track':'yuudachi OR kancolle OR kantai'})
#         for item in r.get_iterator():
#            if 'text' in item:
#             print item['text']
#     except:
#         print("An error has ocurred. Trying again after two minutes.")
#         time.sleep(120)


while True:
    try:
         time_1 = datetime.now()
         tweets = api.request('search/tweets', {'q':'(yuudachi OR kancolle OR kantai OR japan OR battleship OR admiral) poi', "count": "15", "result_type " : "recent"})
         for item in tweets:
             id = str(item["id"])
             if not exists_id(id):
             #else:
                 api.request("statuses/retweet/:%d"%int(id))
                 add_id(id)
                 print("Retweeting ["+id+"] = " + item["text"])
         time_2 = datetime.now()
         time.sleep(max(60 - int((time_2-time_1).seconds),0));
    except:
    	print "An error has ocurred, retrying in two minutes."
    	time.sleep(120)
