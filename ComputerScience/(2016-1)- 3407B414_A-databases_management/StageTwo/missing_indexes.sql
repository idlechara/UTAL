-- PROBLEM INDICES 
CREATE INDEX problem_id_idx ON problems USING hash (id);
CREATE INDEX problem_letter_idx ON problems USING hash (letter);
CREATE INDEX problem_pdf_file_idx ON problems USING hash (pdf_file);
CREATE INDEX problem_colour_id_idx ON problems USING hash (colour_id);
CREATE INDEX problem_description_id_idx ON problems USING hash (description);
CREATE INDEX problem_codename_idx ON problems USING hash (codename);

-- Problem content :p
CREATE INDEX problems_content_id_idx ON problems_content USING hash (id);
--CREATE INDEX problems_content_plain_text_content_idx ON problems_content USING gist (plain_text_content);

-- Sites
CREATE INDEX sites_institution_idx ON sites USING hash (institution);

-- Team members
CREATE INDEX team_members_id_idx ON team_members USING hash (id);
CREATE INDEX team_members_contestant_id_idx ON team_members USING hash (contestant_id);
CREATE INDEX team_members_team_id_idx ON team_members USING hash (team_id);
CREATE INDEX team_members_role_id_idx ON team_members USING hash (role_id);
CREATE INDEX team_members_registration_complete_idx ON team_members USING hash (registration_complete);
CREATE INDEX team_members_on_team_certificate_idx ON team_members USING hash (on_team_certificate);
CREATE INDEX team_members_on_individual_certificate_idx ON team_members USING hash (on_individual_certificate);
CREATE INDEX team_members_team_name_idx ON team_members USING hash (team_name);

-- TEAMS!
CREATE INDEX team_id_idx ON teams USING hash (id);
CREATE INDEX team_institution_idx ON teams USING hash (institution);
CREATE INDEX team_coach_id_idx ON teams USING hash (coach_id);
CREATE INDEX team_name_idx ON teams USING hash (name);
CREATE INDEX team_site_id_idx ON teams USING hash (site_id);
CREATE INDEX team_approved_idx ON teams USING hash (approved);
CREATE INDEX team_include_coach_cert_idx ON teams USING hash (include_coach_cert);
CREATE INDEX team_make_coach_individual_cert_idx ON teams USING hash (make_coach_individual_cert);
CREATE INDEX team_country_id_idx ON teams USING hash (country_id);