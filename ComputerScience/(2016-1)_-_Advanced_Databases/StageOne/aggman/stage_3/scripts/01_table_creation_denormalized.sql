CREATE TABLE d1_problems_content AS SELECT id,plain_text_content FROM problems;
CREATE TABLE d1_problems AS SELECT problems.id,problems.letter,problems.pdf_file,problems.ballon_colour,problems.description,problems.codename FROM problems,d1_problems_content WHERE problems.id = d1_problems_content.id;
CREATE TABLE d2_problems AS SELECT problems.id,problems.letter,problems.pdf_file,problems.ballon_colour,problems.plain_text_content,problems.description,problems.codename,contests.name AS contest_name FROM problems,contests,problem_set WHERE problem_set.problem_id = problems.id AND problem_set.contest_id = contests.id;
CREATE TABLE d3_teams AS SELECT teams.id,teams.institution,teams.coach_id,teams.name,teams.site_id,teams.approved,teams.include_coach_cert,teams.make_coach_individual_cert,sites.country_id AS country_id FROM teams,sites WHERE teams.site_id = sites.id;
CREATE TABLE d4_team_members AS SELECT team_members.id,team_members.contestant_id,team_members.team_id,team_members.role_id,team_members.registration_complete,team_members.on_team_certificate,team_members.on_individual_certificate,teams.name AS team_name FROM teams,team_members WHERE team_members.team_id = teams.id;
CREATE TABLE d5_colours AS SELECT row_number()over() AS id,problems.ballon_colour AS name FROM problems GROUP BY ballon_colour;
CREATE TABLE d5_problems AS SELECT problems.id,problems.letter,problems.pdf_file,d5_colours.id AS colour_id,problems.plain_text_content,problems.description,problems.codename FROM problems,d5_colours WHERE problems.ballon_colour = d5_colours.name;