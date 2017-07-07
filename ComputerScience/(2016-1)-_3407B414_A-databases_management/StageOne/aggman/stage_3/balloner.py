##deprecated due to inability of DB to handle 4 DB concurrently
from multiprocessing.dummy import Pool as ThreadPool
import psycopg2
import random
from psycopg2.extensions import adapt
import string
from random import randrange
from datetime import timedelta
import datetime
from sys import stdout
import time

base_route = "/Users/jvarred/Documents/Sources/2016/DatabasesManagement/aggman/"
logging_route = "/Users/jvarred/Documents/Sources/2016/DatabasesManagement/aggman/exec.log"

hostname = '10.211.55.11'

##database connection settings
postgres = {
    'database': 'postgres',
    'user': 'postgres',
    'password': 'thepassword',
    'host': hostname,
    'port': 5432
}
non_indexed_db = {
    'database': 'icpcdb_non_indexed',
    'user': 'jvarred',
    'password': 'thepassword',
    'host': hostname,
    'port': 5432
}


def log(string, extension=""):
    print string
    with open(logging_route+"_"+extension, "a") as f:
        f.write(string)
        f.write("\n")

def db_init():
    with psycopg2.connect(**postgres) as conn:
        with conn.cursor() as cur:
            conn.autocommit = True  # Explains why we do this - we cannot drop or create from within a DB transaction. http://initd.org/psycopg/docs/connection.html#connection.autocommit
            cur.execute("DROP DATABASE IF EXISTS icpcdb_non_indexed")
            cur.execute("DROP DATABASE IF EXISTS icpcdb_btree_indexed")
            cur.execute("DROP DATABASE IF EXISTS icpcdb_hash_indexed;")
            cur.execute("DROP DATABASE IF EXISTS icpcdb_fully_indexed;")
            cur.execute("CREATE DATABASE icpcdb_non_indexed;")
            cur.execute("CREATE DATABASE icpcdb_btree_indexed;")
            cur.execute("CREATE DATABASE icpcdb_hash_indexed;")
            cur.execute("CREATE DATABASE icpcdb_fully_indexed;")

# n_conn = None
# b_conn = None
# h_conn = None
# f_conn = None
# databases = None

def konnect():
    global n_conn, b_conn, h_conn, f_conn, databases
    n_conn = psycopg2.connect(**non_indexed_db)
    n_conn.autocommit = True
    cursor = n_conn.cursor()
    databases = [['icpcdb_w_o_index', non_indexed_db, n_conn, cursor]]
    colours = ["yellow","red","blue","green","gray","cyan","magenta","white","black","silver","gold","violet","teal","purple","orange","lime","indigo","pink","crimson","chocolate"]
    for i in range(0, len(colours)-1):
        print("UPDATE problems SET ballon_colour='"+colours[i]+"' WHERE id%"+str(len(colours))+"="+str(i)+";")
        cursor.execute("UPDATE problems SET ballon_colour='"+colours[i]+"' WHERE id%"+str(len(colours))+"="+str(i)+";")

konnect()

for [name, params, conn, cur] in databases:
    conn.close()
    cur.close()