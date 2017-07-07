-- DELETE SCOREBOARDS ROWS ASOCIATED TO PROBLEM

CREATE OR REPLACE FUNCTION delete_problem_scoreboard() RETURNS TRIGGER AS $$
    BEGIN
        DELETE FROM scoreboards WHERE scoreboards.problem_id = OLD.id;
        RETURN OLD;
    END;
$$ LANGUAGE plpgsql;

DROP TRIGGER t_delete_problem_scoreboard ON problems;
CREATE TRIGGER t_delete_problem_scoreboard
    BEFORE DELETE ON problems
    FOR EACH ROW EXECUTE PROCEDURE delete_problem_scoreboard();

DELETE FROM problems WHERE id = 658996;
SELECT FROM scoreboards WHERE problem_id = 658996;




--CHECK CONSISTENCY OF PROBLEMS ON PROBLEM SETS
CREATE OR REPLACE FUNCTION baloon_and_letter() RETURNS trigger AS $baloon_and_letter$
DECLARE
    repeated_letters int;
    the_letter text;
    
    repeated_problems int;

    the_balloon int;
    repeated_balloons int;
    BEGIN
    SELECT letter into the_letter FROM problems WHERE NEW.problem_id = problems.id;
    SELECT colour_id into the_balloon FROM problems WHERE NEW.problem_id = problems.id;
    
    SELECT COUNT (*) into repeated_letters FROM problems, problem_set WHERE the_letter = problems.letter AND problems.id = problem_set.problem_id AND problem_set.contest_id = NEW.contest_id;
    
    SELECT COUNT (*) into repeated_balloons FROM problems, problem_set WHERE the_balloon = problems.colour_id AND problems.id = problem_set.problem_id AND problem_set.contest_id = NEW.contest_id;
    
    SELECT COUNT (*) into repeated_problems FROM problem_set WHERE contest_id = NEW.contest_id;

    
        IF repeated_letters != 0 THEN
            RAISE EXCEPTION 'Letter % is already present on the problem set for the contest %', the_letter, NEW.contest_id;
        END IF;


        IF repeated_balloons != 0 THEN
            RAISE EXCEPTION 'Balloon % is already present on the problem set for the contest %', (SELECT colours.name FROM problems, colours WHERE colours.id = problems.id AND NEW.problem_id = problems.id) , NEW.contest_id;
        END IF;


        IF repeated_problems != 0 THEN
            RAISE EXCEPTION 'Problem % is already present on the problem set for the contest %', NEW.problem_id, NEW.contest_id;
        END IF;
        
        RETURN NEW;
    END;
$baloon_and_letter$ LANGUAGE plpgsql;
DROP TRIGGER baloon_and_letter ON problem_set;
CREATE TRIGGER baloon_and_letter BEFORE INSERT OR UPDATE ON problem_set
    FOR EACH ROW EXECUTE PROCEDURE baloon_and_letter();

INSERT INTO problem_set(problem_id, contest_id) VALUES(100902, 485868);



-- TEAM MEMBER RESTRICTION TO #MEMBERS<= 3

CREATE OR REPLACE FUNCTION check_members_from_team() RETURNS TRIGGER AS $$
DECLARE
    the_institution text;
    the_affiliation text;
    BEGIN
        SELECT institution into the_affiliation FROM teams WHERE NEW.team_id = teams.id;
        SELECT affiliation into the_institution FROM contestants WHERE NEW.contestant_id = contestants.id;
       
        IF (the_institution != the_affiliation) THEN
            RAISE EXCEPTION 'The contestant does not share the same institution as the team';
        END IF;
 
        IF((SELECT COUNT(*) FROM team_members WHERE team_id = NEW.team_id) < 3.0 )
        THEN
            RETURN NEW;
        END IF;
        RAISE EXCEPTION 'A team cannot have more than 3 members';
    END;
$$ LANGUAGE plpgsql;

DROP TRIGGER t_max_members_per_team ON team_members;
CREATE TRIGGER t_max_members_per_team
    BEFORE INSERT ON team_members
    FOR EACH ROW EXECUTE PROCEDURE check_members_from_team();

INSERT INTO team_members(contestant_id,team_id,role_id,registration_complete,on_team_certificate,on_individual_certificate,team_name) VALUES(1,1,1,false,false,false,'teamname');
INSERT INTO team_members(contestant_id,team_id,role_id,registration_complete,on_team_certificate,on_individual_certificate,team_name) VALUES(2,1,1,false,false,false,'teamname');
INSERT INTO team_members(contestant_id,team_id,role_id,registration_complete,on_team_certificate,on_individual_certificate,team_name) VALUES(2,1,1,false,false,false,'teamname');



