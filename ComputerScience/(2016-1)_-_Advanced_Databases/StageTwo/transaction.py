import psycopg2
import random
import string
from sys import stdout
from time import sleep

params = {
    'database': 'icpcdb',
    'user': 'postgres',
    'password': '1234',
    'host': '10.211.55.4',
    'port': 5432
}

conn = psycopg2.connect(**params)
cur = conn.cursor()
teams_id = -1;
con1_id = -1;
con2_id = -1;
con3_id = -1;
# create team
cur.execute("INSERT INTO teams VALUES (DEFAULT,'University of Westeros',1,'Not Candy',1,'t','t','t',1);")
cur.execute("SELECT currval('teams_id_seq');");
if(cur.rowcount > 0):
    teams_id = cur.fetchone()[0] ##store the assigned id

# insert first contestant
cur.execute("INSERT INTO contestants VALUES (DEFAULT,'Juan','Nieves','1990-05-12','University of Westeros',NULL,1,'M',NULL,1,'Winterfell','WESTEROS',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);")
cur.execute("SELECT currval('contestants_id_seq');")
if(cur.rowcount > 0):
    con1_id = cur.fetchone()[0]

# insert second contestant
cur.execute("INSERT INTO contestants VALUES (DEFAULT,'Tyrion','Lannister','1985-07-09','University of Westeros',NULL,1,'M',NULL,1,'Casterly Rock','WESTEROS',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);")
cur.execute("SELECT currval('contestants_id_seq');")
if(cur.rowcount > 0):
    con2_id = cur.fetchone()[0]

# insert third contestant
cur.execute("INSERT INTO contestants VALUES (DEFAULT,'Daenerys Queen of Meereen, Queen of the Andals, the Rhoynar and the First Men, Lady Regnant of the Seven Kingdoms, Khaleesi of the Great Grass Sea, Mhysa, Breaker of Chains, the Unburnt, Mother of Dragons','Targaryen','1991-12-18','University of Westeros',NULL,2,'M',NULL,1,'Meereen','Essos',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);")
cur.execute("SELECT currval('contestants_id_seq');")
if(cur.rowcount > 0):
    con3_id = cur.fetchone()[0]

try:
    #assign team members 
    cur.execute("INSERT INTO team_members VALUES(DEFAULT, %s, %s, 1, 't', 't', 't','University of Westeros');", (con1_id,teams_id))
    cur.execute("INSERT INTO team_members VALUES(DEFAULT, %s, %s, 1, 't', 't', 't','University of Westeros');", (con2_id,teams_id))
    cur.execute("INSERT INTO team_members VALUES(DEFAULT, %s, %s, 1, 't', 't', 't','University of Westeros');", (con3_id,teams_id))

except Exception, e:
    cur.rollback()
    print "An error has ocurred, rollin'back!"
    print e
conn.commit()
cur.close()
conn.close()
print "Insertion of test data finished."