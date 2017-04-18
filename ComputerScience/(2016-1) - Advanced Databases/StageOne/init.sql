CREATE TABLE IF NOT EXISTS roles (
  id serial NOT NULL PRIMARY KEY,
  name text NOT NULL
);

CREATE TABLE IF NOT EXISTS countries (
  id serial NOT NULL PRIMARY KEY,
  name text NOT NULL
);

CREATE TABLE IF NOT EXISTS contestants (
  id SERIAL NOT NULL PRIMARY KEY,
  first_name TEXT NOT NULL,
  last_name TEXT NOT NULL,
  birthdate DATE NOT NULL,
  affiliation TEXT NOT NULL,
  title TEXT NULL,
  sex INTEGER NULL,
  shirt_size TEXT NULL,
  badge_name TEXT NULL,
  home_country INTEGER NOT NULL REFERENCES countries(id) ON DELETE RESTRICT,
  home_city TEXT NOT NULL,
  home_state TEXT NULL,
  ocuppation TEXT NULL,
  special_needs TEXT NULL,
  acm_id INT NULL,
  certificate_name TEXT NULL,
  study_area TEXT NULL,
  degree_pursued TEXT NULL,
  bachelor_start_date DATE NULL,
  bachelor_end_date DATE NULL
);

CREATE TABLE IF NOT EXISTS problems (
  id SERIAL NOT NULL PRIMARY KEY,
  letter TEXT NOT NULL,
  pdf_file TEXT NOT NULL,
  ballon_colour TEXT NOT NULL,
  plain_text_content TEXT NOT NULL,
  description TEXT NOT NULL,
  codename TEXT NOT NULL
);


CREATE TABLE IF NOT EXISTS contests (
  id SERIAL NOT NULL PRIMARY KEY,
  name TEXT NOT NULL,
  start_datetime TIMESTAMP NULL,
  end_datetime TIMESTAMP NULL
);

CREATE TABLE IF NOT EXISTS problem_set (
  id SERIAL NOT NULL PRIMARY KEY,
  contest_id INT NOT NULL REFERENCES contests(id) ON DELETE RESTRICT,
  problem_id INT NOT NULL REFERENCES problems(id) ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS sites (
  id SERIAL NOT NULL PRIMARY KEY,
  name TEXT NOT NULL,
  country_id INT NOT NULL REFERENCES countries(id) ON DELETE RESTRICT, --'country_id = location'
  institution TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS contest_sites (
  id SERIAL NOT NULL PRIMARY KEY,
  id_site INT NOT NULL REFERENCES sites(id)  ON DELETE RESTRICT,
  id_contest INT NOT NULL REFERENCES contests(id)  ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS teams (
  id SERIAL NOT NULL PRIMARY KEY,
  institution TEXT NOT NULL,
  coach_id INT NOT NULL REFERENCES contestants(id) ON DELETE RESTRICT,
  name TEXT NOT NULL,
  site_id INT NOT NULL REFERENCES sites(id),
  approved BOOLEAN NULL,
  include_coach_cert BOOLEAN NULL,
  make_coach_individual_cert BOOLEAN NULL
);

CREATE TABLE IF NOT EXISTS team_members (
  id SERIAL NOT NULL PRIMARY KEY,
  contestant_id INT NOT NULL REFERENCES contestants(id) ON DELETE RESTRICT,
  team_id INT NOT NULL REFERENCES teams(id) ON DELETE CASCADE,
  role_id INT NOT NULL REFERENCES roles(id) ON DELETE RESTRICT,
  registration_complete BOOLEAN NULL,
  on_team_certificate BOOLEAN NULL,
  on_individual_certificate BOOLEAN NULL
);

-- CREATE TABLE IF NOT EXISTS contest_teams (
--   id SERIAL NOT NULL PRIMARY KEY,
--   team_id INT NOT NULL REFERENCES teams(id) ON DELETE RESTRICT,
--   contest_id INT NOT NULL REFERENCES contests(id) ON DELETE CASCADE
-- );

CREATE TABLE IF NOT EXISTS scoreboards (
  id SERIAL NOT NULL PRIMARY KEY,
  score INT NOT NULL,
  problem_id INT NOT NULL REFERENCES problems(id) ON DELETE RESTRICT,
  contest_id INT NOT NULL REFERENCES contests(id) ON DELETE CASCADE,
  team_id INT NOT NULL REFERENCES teams(id) ON DELETE RESTRICT
);