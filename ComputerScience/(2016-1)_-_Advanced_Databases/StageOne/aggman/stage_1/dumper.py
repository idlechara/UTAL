import psycopg2
import random
import string
from random import randrange
from datetime import timedelta
import datetime
from sys import stdout
from time import sleep

######### PARAMETERS ###########

role_table_size = 4000 + 2000
problems_table_size = 80000 + 40000
countries_table_size = 4000 + 2000
sites_table_size = 16000 + 8000
contests_table_size = 40000 + 20000
contestants_table_size = 80000 + 40000
problem_sets_table_size = 16000 + 8000
contest_sites_table_size = 12000 + 6000
teams_table_size = 80000 + 40000
scoreboards_table_size = 24000 + 12000
team_members_table_size = 80000 + 40000

birth_start = datetime.datetime.strptime('1/1/1980 1:30 PM', '%m/%d/%Y %I:%M %p')
birth_end = datetime.datetime.strptime('1/1/2016 4:50 AM', '%m/%d/%Y %I:%M %p')
contest_start = datetime.datetime.strptime('1/1/1990 1:30 PM', '%m/%d/%Y %I:%M %p')
contest_end = datetime.datetime.strptime('1/1/2024 4:50 AM', '%m/%d/%Y %I:%M %p')

params = {
    'database': 'icpcdb',
    'user': 'jvarred',
    'password': 'thepassword',
    'host': '10.211.55.9',
    'port': 5432
}
#################################


conn = psycopg2.connect(**params)
cur = conn.cursor()


def get_id_list(table):
    w = list()
    print cur.mogrify("SELECT id FROM " + table + ";")
    for record in cur:
        w.append(record[0])
    return w


def random_date(start, end):
    """
    This function will return a random datetime between two datetime
    objects.
    """
    delta = end - start
    int_delta = (delta.days * 24 * 60 * 60) + delta.seconds
    random_second = randrange(int_delta)
    return start + timedelta(seconds=random_second)


def random_string(min_length, max_length):
    return ''.join(random.choice(string.lowercase) for i in range(random.randint(min_length, max_length)))


def random_text(min_length, max_length):
    return ''.join(
        random.choice(string.lowercase + string.uppercase + string.digits + ".,;:-_" + string.whitespace) for i in
        range(random.randint(min_length, max_length)))


def nullable(factor, choice):
    if random.randint(0, 100) < factor:
        return None
    else:
        return choice


###### INITIALIZING TABLES! #########
print "Initializing tables on database"
sql = "CREATE TABLE IF NOT EXISTS roles (  id serial NOT NULL PRIMARY KEY,  name text NOT NULL);CREATE TABLE IF NOT EXISTS countries (  id serial NOT NULL PRIMARY KEY,  name text NOT NULL);CREATE TABLE IF NOT EXISTS contestants (  id SERIAL NOT NULL PRIMARY KEY,  first_name TEXT NOT NULL,  last_name TEXT NOT NULL,  birthdate DATE NOT NULL,  affiliation TEXT NOT NULL,  title TEXT NULL,  sex INTEGER NULL,  shirt_size TEXT NULL,  badge_name TEXT NULL,  home_country INTEGER NOT NULL REFERENCES countries(id) ON DELETE RESTRICT,  home_city TEXT NOT NULL,  home_state TEXT NULL,  ocuppation TEXT NULL,  special_needs TEXT NULL,  acm_id INT NULL,  certificate_name TEXT NULL,  study_area TEXT NULL,  degree_pursued TEXT NULL,  bachelor_start_date DATE NULL,  bachelor_end_date DATE NULL);CREATE TABLE IF NOT EXISTS problems (  id SERIAL NOT NULL PRIMARY KEY,  letter TEXT NOT NULL,  pdf_file TEXT NOT NULL,  ballon_colour TEXT NOT NULL,  plain_text_content TEXT NOT NULL,  description TEXT NOT NULL,  codename TEXT NOT NULL);CREATE TABLE IF NOT EXISTS contests (  id SERIAL NOT NULL PRIMARY KEY,  name TEXT NOT NULL,  start_datetime TIMESTAMP NULL,  end_datetime TIMESTAMP NULL);CREATE TABLE IF NOT EXISTS problem_set (  id SERIAL NOT NULL PRIMARY KEY,  contest_id INT NOT NULL REFERENCES contests(id) ON DELETE RESTRICT,  problem_id INT NOT NULL REFERENCES problems(id) ON DELETE RESTRICT);CREATE TABLE IF NOT EXISTS sites (  id SERIAL NOT NULL PRIMARY KEY,  name TEXT NOT NULL,  country_id INT NOT NULL REFERENCES countries(id) ON DELETE RESTRICT, institution TEXT NOT NULL);CREATE TABLE IF NOT EXISTS contest_sites (  id SERIAL NOT NULL PRIMARY KEY,  id_site INT NOT NULL REFERENCES sites(id)  ON DELETE RESTRICT,  id_contest INT NOT NULL REFERENCES contests(id)  ON DELETE RESTRICT);CREATE TABLE IF NOT EXISTS teams (  id SERIAL NOT NULL PRIMARY KEY,  institution TEXT NOT NULL,  coach_id INT NOT NULL REFERENCES contestants(id) ON DELETE RESTRICT,  name TEXT NOT NULL,  site_id INT NOT NULL REFERENCES sites(id),  approved BOOLEAN NULL,  include_coach_cert BOOLEAN NULL,  make_coach_individual_cert BOOLEAN NULL);CREATE TABLE IF NOT EXISTS team_members (  id SERIAL NOT NULL PRIMARY KEY,  contestant_id INT NOT NULL REFERENCES contestants(id) ON DELETE RESTRICT,  team_id INT NOT NULL REFERENCES teams(id) ON DELETE CASCADE,  role_id INT NOT NULL REFERENCES roles(id) ON DELETE RESTRICT,  registration_complete BOOLEAN NULL,  on_team_certificate BOOLEAN NULL,  on_individual_certificate BOOLEAN NULL); CREATE TABLE IF NOT EXISTS scoreboards (  id SERIAL NOT NULL PRIMARY KEY,  score INT NOT NULL,  problem_id INT NOT NULL REFERENCES problems(id) ON DELETE RESTRICT,  contest_id INT NOT NULL REFERENCES contests(id) ON DELETE CASCADE,  team_id INT NOT NULL REFERENCES teams(id) ON DELETE RESTRICT);";
print cur.mogrify(sql)
conn.commit()

###### CREATE ROLES #####
for i in range(1, role_table_size + 1):
    sql = "INSERT INTO roles (name) VALUES (%s)";
    data = (random_string(10, 90),)
    print cur.mogrify(sql, data)
    ##stdout.write("\rPopulating ROLES: [%i/%i] completed" % (i, role_table_size))
    ##stdout.flush()
conn.commit()

###### CREATE PROBLEMS #####
print " ... DONE!"
for i in range(1, problems_table_size + 1):
    sql = "INSERT INTO problems (letter, pdf_file, ballon_colour, plain_text_content, description, codename) VALUES (%s,%s,%s,%s,%s,%s)";
    data = (random_string(1, 1), random_string(5, 30) + ".pdf", random_text(4, 10),
            random_text(1000, 12000), random_text(100, 1000), random_string(5, 10),);
    ##stdout.write("\rPopulating PROBLEMS: [%i/%i] completed" % (i, problems_table_size))
    ##stdout.flush()
    print cur.mogrify(sql, data)
conn.commit()

###### CREATE COUNTRIES #####
print " ... DONE!"
for i in range(1, countries_table_size + 1):
    sql = "INSERT INTO countries (name) VALUES (%s)";
    data = (random_string(10, 90),)
    ##stdout.write("\rPopulating COUNTRIES: [%i/%i] completed" % (i, countries_table_size))
    ##stdout.flush()
    print cur.mogrify(sql, data)
conn.commit()

###### CREATE SITES #####
print " ... DONE!"
countries_list = get_id_list("countries")
for i in range(1, sites_table_size + 1):
    sql = "INSERT INTO sites (name, country_id, institution) VALUES (%s,%s,%s)";
    data = (random_string(10, 50), random.choice(countries_list), random_text(4, 10),);
    ##stdout.write("\rPopulating SITES: [%i/%i] completed" % (i, sites_table_size))
    ##stdout.flush()
    print cur.mogrify(sql, data)
conn.commit()

###### CREATE CONTESTS #####
print " ... DONE!"
for i in range(1, contests_table_size + 1):
    sql = "INSERT INTO contests (name, start_datetime, end_datetime) VALUES (%s,%s,%s)";
    start_dt = random_date(contest_start, contest_end)
    data = (random_string(10, 50), start_dt, start_dt + timedelta(hours=4),);
    ##stdout.write("\rPopulating CONTESTS: [%i/%i] completed" % (i, contests_table_size))
    ##stdout.flush()
    print cur.mogrify(sql, data)
conn.commit()

###### CREATE CONTESTANTS #####
print " ... DONE!"
countries_list = get_id_list("countries")
for i in range(1, contestants_table_size + 1):
    sql = "INSERT INTO contestants " \
          "(first_name, last_name, birthdate, affiliation, title," \
          " sex, shirt_size, badge_name, home_country, home_city," \
          " home_state, ocuppation, special_needs, acm_id, certificate_name," \
          " study_area, bachelor_start_date, bachelor_end_date) " \
          "VALUES (%s,%s,%s,%s,%s," \
          "%s,%s,%s,%s,%s," \
          "%s,%s,%s,%s,%s," \
          "%s,%s,%s)";
    birth_dt = random_date(birth_start, birth_end)
    bstart_dt = random_date(birth_start + timedelta(days=(17 * 365)), birth_start + timedelta(days=(30 * 365)))
    bend_dt = random_date(bstart_dt, bstart_dt + timedelta(days=(9 * 365)))
    data = (random_string(10, 50), random_string(10, 50), birth_dt, random_string(10, 50),
            nullable(70, random.choice(["Mr.", "Mrs.", "Dr.", "PhD."])),
            nullable(3, random.choice(["1", "2"])), nullable(1, random.choice(["S", "M", "L", "XL"])),
            random_string(10, 50), random.choice(countries_list), random_text(10, 50),
            nullable(90, random_text(10, 50)), nullable(70, random_text(10, 50)), nullable(95, random_text(10, 50)),
            nullable(90, random.randint(1, 1e9)), random_string(10, 30),
            nullable(90, random_text(10, 50)), nullable(90, bstart_dt), nullable(90, bend_dt),)
    ##stdout.write("\rPopulating CONTESTANTS: [%i/%i] completed" % (i, contestants_table_size))
    ##stdout.flush()
    print cur.mogrify(sql, data)
conn.commit()

###### CREATE PROBLEM SETS #####
print " ... DONE!"
contest_list = get_id_list("contests")
problem_list = get_id_list("problems")
random.shuffle(problem_list)
for i in range(1, problem_sets_table_size + 1):
    sql = "INSERT INTO problem_set (contest_id, problem_id) VALUES (%s,%s)";
    data = (random.choice(contest_list), problem_list.pop(),)
    ##stdout.write("\rPopulating PROBLEM SETS: [%i/%i] completed" % (i, problem_sets_table_size))
    ##stdout.flush()
    print cur.mogrify(sql, data)
conn.commit()

###### CREATE CONTEST SITES #####
print " ... DONE!"
sites_list = get_id_list("sites")
contests_list = get_id_list("contests")
random.shuffle(contests_list)
for i in range(1, contest_sites_table_size + 1):
    sql = "INSERT INTO contest_sites (id_contest, id_site) VALUES (%s,%s)";
    data = (contest_list.pop(), random.choice(sites_list),)
    ##stdout.write("\rPopulating SITES: [%i/%i] completed" % (i, contest_sites_table_size))
    ##stdout.flush()
    print cur.mogrify(sql, data)
conn.commit()

###### CREATE TEAMS #####
print " ... DONE!"
contestants_list = get_id_list("contestants")
sites_list = get_id_list("sites")
for i in range(1, teams_table_size + 1):
    sql = "INSERT INTO teams (institution, coach_id, name, site_id, approved, include_coach_cert, make_coach_individual_cert) VALUES (%s,%s,%s,%s,%s,%s,%s)";
    data = (random_text(10, 30), random.choice(contestants_list), random_text(4, 45),
            random.choice(sites_list), nullable(15, random.choice([True, False])),
            nullable(15, random.choice([True, False])), nullable(15, random.choice([True, False])),)
    ##stdout.write("\rPopulating TEAMS: [%i/%i] completed" % (i, teams_table_size))
    ##stdout.flush()
    print cur.mogrify(sql, data)
conn.commit()

###### CREATE CONTEST-TEAM-SCOREBOARD ASSOCIATION #####
print " ... DONE!"
teams_list = get_id_list("teams")
contests_list = get_id_list("contests")
contestants_list = get_id_list("contestants")
role_list = get_id_list("roles")
for i in range(1, scoreboards_table_size + 1):
    team_id = random.choice(teams_list);
    sql = "INSERT INTO scoreboards (score, problem_id, team_id, contest_id) VALUES (%s,%s,%s,%s)";
    data = (random.randint(0, 9999), random.choice(problem_list), team_id, random.choice(contests_list))
    print cur.mogrify(sql, data)

    sql = "INSERT INTO team_members (contestant_id, team_id, role_id, registration_complete, on_team_certificate, on_individual_certificate) VALUES (%s,%s,%s,%s,%s,%s)";
    data = (
    random.choice(contestants_list), team_id, random.choice(role_list), nullable(15, random.choice([True, False])),
    nullable(15, random.choice([True, False])), nullable(15, random.choice([True, False])),)

    ##stdout.write("\rPopulating CONTEST-TEAM-SCOREBOARD ASSOCIATION: [%i/%i] completed" % (i, scoreboards_table_size))
    ##stdout.flush()
    print cur.mogrify(sql, data)
conn.commit()

###### CREATE CONTEST-TEAM ASSOCIATION #####
print " ... DONE!"
teams_list = get_id_list("teams")
contestants_list = get_id_list("contestants")
role_list = get_id_list("roles")
for i in range(1, team_members_table_size + 1):
    sql = "INSERT INTO team_members (contestant_id, team_id, role_id, registration_complete, on_team_certificate, on_individual_certificate) VALUES (%s,%s,%s,%s,%s,%s)";
    data = (
    random.choice(contestants_list), team_id, random.choice(role_list), nullable(15, random.choice([True, False])),
    nullable(15, random.choice([True, False])), nullable(15, random.choice([True, False])),)

    ##stdout.write("\rPopulating TEAM MEMBERS: [%i/%i] completed" % (i, team_members_table_size))
    ##stdout.flush()
    print cur.mogrify(sql, data)
conn.commit()
print " ... DONE!"

# print cur.mogrify("SELECT * FROM roles;")
# print(cur.fetchone());
# (1, 100, "abc'def")
conn.commit()
cur.close()
conn.close()
print "Process finished. If you want more data, then run this program again-desu!"
