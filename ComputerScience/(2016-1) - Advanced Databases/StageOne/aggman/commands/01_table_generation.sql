CREATE TABLE IF NOT EXISTS roles (  id serial NOT NULL ,  name text NOT NULL);
CREATE TABLE IF NOT EXISTS countries (  id serial NOT NULL ,  name text NOT NULL);
CREATE TABLE IF NOT EXISTS contestants (  id SERIAL NOT NULL ,  first_name TEXT NOT NULL,  last_name TEXT NOT NULL,  birthdate DATE NOT NULL,  affiliation TEXT NOT NULL,  title TEXT NULL,  sex INTEGER NULL,  shirt_size TEXT NULL,  badge_name TEXT NULL,  home_country INTEGER NOT NULL ,  home_city TEXT NOT NULL,  home_state TEXT NULL,  ocuppation TEXT NULL,  special_needs TEXT NULL,  acm_id INT NULL,  certificate_name TEXT NULL,  study_area TEXT NULL,  degree_pursued TEXT NULL,  bachelor_start_date DATE NULL,  bachelor_end_date DATE NULL);
CREATE TABLE IF NOT EXISTS problems (  id SERIAL NOT NULL ,  letter TEXT NOT NULL,  pdf_file TEXT NOT NULL,  ballon_colour TEXT NOT NULL,  plain_text_content TEXT NOT NULL,  description TEXT NOT NULL,  codename TEXT NOT NULL);
CREATE TABLE IF NOT EXISTS contests (  id SERIAL NOT NULL ,  name TEXT NOT NULL,  start_datetime TIMESTAMP NULL,  end_datetime TIMESTAMP NULL);
CREATE TABLE IF NOT EXISTS problem_set (  id SERIAL NOT NULL ,  contest_id INT NOT NULL ,  problem_id INT NOT NULL );
CREATE TABLE IF NOT EXISTS sites (  id SERIAL NOT NULL ,  name TEXT NOT NULL,  country_id INT NOT NULL , institution TEXT NOT NULL);
CREATE TABLE IF NOT EXISTS contest_sites (  id SERIAL NOT NULL ,  id_site INT NOT NULL  ,  id_contest INT NOT NULL  );
CREATE TABLE IF NOT EXISTS teams (  id SERIAL NOT NULL ,  institution TEXT NOT NULL,  coach_id INT NOT NULL ,  name TEXT NOT NULL,  site_id INT NOT NULL ,  approved BOOLEAN NULL,  include_coach_cert BOOLEAN NULL,  make_coach_individual_cert BOOLEAN NULL);
CREATE TABLE IF NOT EXISTS team_members (  id SERIAL NOT NULL ,  contestant_id INT NOT NULL ,  team_id INT NOT NULL ,  role_id INT NOT NULL ,  registration_complete BOOLEAN NULL,  on_team_certificate BOOLEAN NULL,  on_individual_certificate BOOLEAN NULL);
CREATE TABLE IF NOT EXISTS scoreboards (  id SERIAL NOT NULL ,  score INT NOT NULL,  problem_id INT NOT NULL ,  contest_id INT NOT NULL ,  team_id INT NOT NULL );