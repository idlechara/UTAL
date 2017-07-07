CREATE INDEX contestants_id_idx ON contestants USING hash (id);
CREATE INDEX contestants_fn_idx ON contestants USING hash (first_name);
CREATE INDEX contestants_ln_idx ON contestants USING hash (last_name);
CREATE INDEX problems_id_idx ON problems USING hash (id);
CREATE INDEX teams_id_idx ON teams USING hash (id);
CREATE INDEX teams_institution_idx ON teams USING hash (institution);
CREATE INDEX team_members_id_idx ON team_members USING hash (id);
CREATE INDEX team_members_contestant_id_idx ON team_members USING hash (contestant_id);