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
btree_indexed_db = {
    'database': 'icpcdb_btree_indexed',
    'user': 'jvarred',
    'password': 'thepassword',
    'host': hostname,
    'port': 5432
}
hash_indexed_db = {
    'database': 'icpcdb_hash_indexed',
    'user': 'jvarred',
    'password': 'thepassword',
    'host': hostname,
    'port': 5432
}
fully_indexed_db = {
    'database': 'icpcdb_fully_indexed',
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
    b_conn = psycopg2.connect(**btree_indexed_db)
    h_conn = psycopg2.connect(**hash_indexed_db)
    f_conn = psycopg2.connect(**fully_indexed_db)
    n_conn.autocommit = True
    b_conn.autocommit = True
    h_conn.autocommit = True
    f_conn.autocommit = True
    databases = [['icpcdb_w_o_index', non_indexed_db, n_conn, n_conn.cursor()],
                 ['icpcdb_btree_index', btree_indexed_db, b_conn, b_conn.cursor()],
                 ['icpcdb_hash_index', hash_indexed_db, h_conn, h_conn.cursor()],
                 ['icpcdb_full_index', fully_indexed_db, f_conn, f_conn.cursor()]]

##wea
def execute_file(params, o_input_file):
    input_file = base_route + o_input_file
    log("\nExecuting file %s:" % (o_input_file), params[0][0])
    for [name, conf, conn, cur] in params:
        elapsed_time = 0
        with open(o_input_file, "r") as f:
            buffer = ""
            for line in f:
                if not line:
                    continue
                elif line.strip().endswith(");"):
                    sql = str(buffer + line)
                    buffer = """"""
                    start = int(round(time.time() * 1000))
                    cur.execute(sql)
                    end = int(round(time.time() * 1000))
                    elapsed_time = (end-start)
                else:
                    buffer = buffer + line
        log("\t%s: %s msecs" % (o_input_file, elapsed_time))

def log_execute_whole_file(params, o_input_file, o_output_file, bulk=False):
    input_file = base_route + o_input_file
    output_file = base_route + o_output_file
    log("\nExecuting file %s:" % (o_input_file), params[0][0])
    elapsed_time = 0
    with open(output_file, "a") as o_file:
        o_file.write(o_input_file + ":all_data")
        for [name, conf, conn, cur] in params:
            start = int(round(time.time() * 1000))

            elapsed_time = 0

            if not bulk :
                with open(o_input_file, "r") as f:
                    buffer = ""
                    for line in f:
                        if not line:
                            continue
                        elif line.strip().endswith(");"):
                            sql = str(buffer + line)
                            buffer = """"""
                            start = int(round(time.time() * 1000))
                            cur.execute(sql)
                            end = int(round(time.time() * 1000))
                            elapsed_time = elapsed_time + (end-start)
                        else:
                            buffer = buffer + line
            else:
                start = int(round(time.time() * 1000))
                cur.execute(open(input_file, "r").read())
                end = int(round(time.time() * 1000))
                elapsed_time += end - start
            o_file.write("\t" + str(elapsed_time))
        o_file.write("\n")
    log("\t%s: %s msecs" % (o_input_file, elapsed_time), params[0][0])

def log_execute_per_line_file(params, o_input_file, o_output_file):
    input_file = base_route + o_input_file
    output_file = base_route + o_output_file
    log("\nExecuting file %s:" % (o_input_file), params[0][0])
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
    log("\t%s: %s msecs" % (o_input_file, elapsed_time), params[0][0])

def followed_execution(args):
    params = args[0]
    btree = args[1]
    hash = args[2]
    test = args[3]
    # log("Starting process for " + str(params[0]) +": ")
    databases_args = list()
    databases_args.append(params)
    log_execute_per_line_file(databases_args, "commands/01_table_generation.sql", str(params[0])+".log")
    if btree:
        execute_file(databases_args, "commands/01_table_generation_btree.sql")
    if hash:
        execute_file(databases_args, "commands/01_table_generation_hash.sql")
    log_execute_whole_file(databases_args, "commands/02_roles.sql", str(params[0])+".log")
    log_execute_whole_file(databases_args, "commands/03_problems.sql", str(params[0])+".log")
    log_execute_whole_file(databases_args, "commands/04_countries.sql", str(params[0])+".log")
    log_execute_whole_file(databases_args, "commands/05_sites.sql", str(params[0])+".log")
    log_execute_whole_file(databases_args, "commands/06_contests.sql", str(params[0])+".log")
    log_execute_whole_file(databases_args, "commands/07_contestants.sql", str(params[0])+".log")
    log_execute_whole_file(databases_args, "commands/08_problem_sets.sql", str(params[0])+".log")
    log_execute_whole_file(databases_args, "commands/09_contest_sites.sql", str(params[0])+".log")
    log_execute_whole_file(databases_args, "commands/10_teams.sql", str(params[0])+".log")
    log_execute_whole_file(databases_args, "commands/11_scoreboards.sql", str(params[0])+".log")
    log_execute_whole_file(databases_args, "commands/12_team_members.sql", str(params[0])+".log")
    if test:
        log_execute_per_line_file(databases_args, "commands/13_queries.sql", str(params[0])+".log")

def drop(btree=False, hash=False, full=False, non_indexed=False):
    with psycopg2.connect(**postgres) as conn:
        with conn.cursor() as cur:
            conn.autocommit = True  # Explains why we do this - we cannot drop or create from within a DB transaction. http://initd.org/psycopg/docs/connection.html#connection.autocommit
            if non_indexed:
                databases[0][2].close()
                databases[0][3].close()
                cur.execute("DROP DATABASE IF EXISTS icpcdb_non_indexed")
            if btree:
                databases[1][2].close()
                databases[1][3].close()
                cur.execute("DROP DATABASE IF EXISTS icpcdb_btree_indexed")
            if hash:
                databases[2][2].close()
                databases[2][3].close()
                cur.execute("DROP DATABASE IF EXISTS icpcdb_hash_indexed;")
            if full:
                databases[3][2].close()
                databases[4][3].close()
                cur.execute("DROP DATABASE IF EXISTS icpcdb_fully_indexed;")

def db_assignment(test):
    db_init() #you can comment this if the db is already created-desu
    konnect()
    pool_arguments = [
        [databases[0], False, False, test],
        [databases[1], True, False, test],
        [databases[2], False, True, test],
        [databases[3], True, True, test]
    ]

    pool = ThreadPool(4)
    print "Launching threads."
    pool.map(followed_execution, pool_arguments)
    pool.close()
    print "Waiting for threads to join."
    pool.join()
    print "Threads joined."

db_assignment(True)

for [name, params, conn, cur] in databases:
    conn.close()
    cur.close()