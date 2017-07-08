##deprecated due to inability of DB to handle 4 DB concurrently
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

##database connection settings
postgres = {
    'database': 'postgres',
    'user': 'postgres',
    'password': 'thepassword',
    'host': '10.211.55.9',
    'port': 5432
}
non_indexed_db = {
    'database': 'icpcdb_non_indexed',
    'user': 'jvarred',
    'password': 'thepassword',
    'host': '10.211.55.9',
    'port': 5432
}
btree_indexed_db = {
    'database': 'icpcdb_btree_indexed',
    'user': 'jvarred',
    'password': 'thepassword',
    'host': '10.211.55.9',
    'port': 5432
}
hash_indexed_db = {
    'database': 'icpcdb_hash_indexed',
    'user': 'jvarred',
    'password': 'thepassword',
    'host': '10.211.55.9',
    'port': 5432
}
fully_indexed_db = {
    'database': 'icpcdb_fully_indexed',
    'user': 'jvarred',
    'password': 'thepassword',
    'host': '10.211.55.9',
    'port': 5432
}

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


db_init()

n_conn = psycopg2.connect(**non_indexed_db)
b_conn = psycopg2.connect(**btree_indexed_db)
h_conn = psycopg2.connect(**hash_indexed_db)
f_conn = psycopg2.connect(**fully_indexed_db)

databases = [['icpcdb_w_o_index', non_indexed_db, n_conn, n_conn.cursor()],
             ['icpcdb_btree_index', btree_indexed_db, b_conn, b_conn.cursor()],
             ['icpcdb_hash_index', hash_indexed_db, h_conn, h_conn.cursor()],
             ['icpcdb_full_index', fully_indexed_db, f_conn, f_conn.cursor()]]

d_dummy_btree = [['icpcdb_btree_index', btree_indexed_db, b_conn, b_conn.cursor()],
                 ['icpcdb_full_index', fully_indexed_db, f_conn, f_conn.cursor()]]

d_dummy_hash = [['icpcdb_hash_index', hash_indexed_db, h_conn, h_conn.cursor()],
                 ['icpcdb_full_index', fully_indexed_db, f_conn, f_conn.cursor()]]


##wea
def execute_file(params, o_input_file):
    input_file = base_route + o_input_file
    print("\nExecuting file %s:" % (o_input_file))
    for [name, conf, conn, cur] in params:
        elapsed_time = 0
        with open(o_input_file, "r") as f:
            buffer = ""
            for line in f:
                if not f:
                    continue
                elif line.strip().endswith(");"):
                    start = int(round(time.time() * 1000))
                    sql = str(buffer + line)
                    buffer = """"""
                    cur.execute(sql)
                    conn.commit()
                    end = int(round(time.time() * 1000))
                    elapsed_time = (end-start)
                else:
                    buffer = buffer + line
        print("\t%s: %s msecs" % (o_input_file, elapsed_time))

def log_execute_whole_file(params, o_input_file, o_output_file):
    input_file = base_route + o_input_file
    output_file = base_route + o_output_file
    print("\nExecuting file %s:" % (o_input_file))
    elapsed_time = 0
    with open(output_file, "a") as o_file:
        o_file.write(o_input_file + ":all_data")
        for [name, conf, conn, cur] in params:
            start = int(round(time.time() * 1000))
            cur.execute(open(input_file, "r").read())
            conn.commit()
            end = int(round(time.time() * 1000))
            elapsed_time = end - start
            o_file.write("\t" + str((end - start)))
        o_file.write("\n")
    print("\t%s: %s msecs" % (o_input_file, elapsed_time))

def log_execute_per_line_file(params, o_input_file, o_output_file):
    input_file = base_route + o_input_file
    output_file = base_route + o_output_file
    print("\nExecuting file %s:" % (o_input_file))
    with open(output_file, "a") as o_file:
        with open(input_file, "r") as f:
            line_number = 0
            buffer = ""
            for line in f:
                if not f:
                    continue
                elif line.strip().endswith(");"):
                    timed_records = list()
                    for [name, conf, conn, cur] in params:
                        start = int(round(time.time() * 1000))
                        sql = str(buffer + line)
                        buffer = ""
                        cur.execute(sql)
                        conn.commit()
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

def db_assignment():
    print "Starting process"
    log_execute_per_line_file(databases, "commands/01_table_generation.sql","experiments.log")
    log_execute_whole_file(databases, "commands/02_roles.sql", "experiments.log")
    log_execute_whole_file(databases, "commands/03_problems.sql", "experiments.log")
    log_execute_whole_file(databases, "commands/04_countries.sql", "experiments.log")
    log_execute_whole_file(databases, "commands/05_sites.sql", "experiments.log")
    log_execute_whole_file(databases, "commands/06_contests.sql", "experiments.log")
    log_execute_whole_file(databases, "commands/07_contestants.sql", "experiments.log")
    log_execute_whole_file(databases, "commands/08_problem_sets.sql", "experiments.log")
    log_execute_whole_file(databases, "commands/09_contest_sites.sql", "experiments.log")
    log_execute_whole_file(databases, "commands/10_teams.sql", "experiments.log")
    log_execute_whole_file(databases, "commands/11_scoreboards.sql", "experiments.log")
    log_execute_whole_file(databases, "commands/12_team_members.sql", "experiments.log")
    execute_file(d_dummy_btree, "commands/01_table_generation_btree.sql")
    execute_file(d_dummy_hash, "commands/01_table_generation_hash.sql")

    #log_execute_per_line_file(databases, "13_queries.sql", "experiments.log")

db_assignment()

for [name, params, conn, cur] in databases:
    conn.close()
    cur.close()