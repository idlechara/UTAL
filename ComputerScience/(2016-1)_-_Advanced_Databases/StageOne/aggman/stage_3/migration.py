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

base_route = "/Users/jvarred/Documents/Sources/2016/DatabasesManagement/aggman/stage_3/"
logging_route = "/Users/jvarred/Documents/Sources/2016/DatabasesManagement/aggman/stage_3/exec.log"

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

def log_execute_per_line_file(params, o_input_file, o_output_file):
    input_file = base_route + o_input_file
    output_file = base_route + o_output_file
    print("\nExecuting file %s:" % (o_input_file))
    elapsed_time = 0
    with open(output_file, "a") as o_file:
        with open(input_file, "r") as f:
            line_number = 0
            buffer = ""
            for line in f:
                if not line:
                    continue
                elif line.strip().endswith(";"):
                    timed_records = list()
                    for [name, conf, conn, cur] in params:
                        sql = str(buffer + line)
                        buffer = ""
                        start = int(round(time.time() * 1000))
                        cur.execute(sql)
                        end = int(round(time.time() * 1000))
                        elapsed_time = (end - start)
                        timed_records.append(elapsed_time)
                    o_file.write(o_input_file+":"+str(line_number))
                    line_number = line_number +1
                    for data in timed_records:
                        o_file.write("\t" + str(data))
                    o_file.write("\n")
                else:
                    buffer = buffer + line
    print("\t%s: %s msecs" % (o_input_file, elapsed_time))




konnect()
# log_execute_per_line_file(databases, "scripts/13_queries.sql", "13_queries.log")
log_execute_per_line_file(databases, "scripts/15_final.sql", "15_final.log")

for [name, params, conn, cur] in databases:
    conn.close()
    cur.close()