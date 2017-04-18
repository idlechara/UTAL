-- create new team
DO $$
DECLARE
	con1_id int;
	con2_id int;
	con3_id int;
	team_id int;
BEGIN;
	SET CONSTRAINTS ALL IMMEDIATE;
	INSERT INTO teams VALUES (DEFAULT,'University of Westeros',1,'Not Candy',1,t,t,t,1);

	SELECT currval('teams_id_seq') INTO team_id;

	INSERT INTO contestants VALUES (DEFAULT,'Juan','Nieves','1990-05-12','University of Westeros',NULL,1,'M',NULL,1,'Winterfell','WESTEROS',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

	SELECT currval('contestants_id_seq') INTO con1_id;

	INSERT INTO contestants VALUES (DEFAULT,'Tyrion','Lannister','1985-07-09','University of Westeros',NULL,1,'M',NULL,1,'Casterly Rock','WESTEROS',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

	SELECT currval('contestants_id_seq') INTO con2_id;

	INSERT INTO contestants VALUES (DEFAULT,'Daenerys Queen of Meereen, Queen of the Andals, the Rhoynar and the First Men, Lady Regnant of the Seven Kingdoms, Khaleesi of the Great Grass Sea, Mhysa, Breaker of Chains, the Unburnt, Mother of Dragons','Targaryen','1991-12-18','University of Westeros',NULL,2,'M',NULL,1,'Meereen','Essos',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);

	SELECT currval('contestants_id_seq') INTO con3_id;

	INSERT INTO team_members VALUES(DEFAULT, con1_id, team_id, 1, t, t, t,'University of Westeros');
	INSERT INTO team_members VALUES(DEFAULT, con2_id, team_id, 1, t, t, t,'University of Westeros');
	INSERT INTO team_members VALUES(DEFAULT, con3_id, team_id, 1, t, t, t,'University of Westeros');

EXCEPTION
	WHEN OTHERS THEN 
		ROLLBACK;
END;
COMMIT;
$$
LANGUAGE plpgsql;



-- Create new Problem Set
DO $$
DECLARE;
	pid int;
BEGIN;
	SET CONSTRAINTS ALL IMMEDIATE;

	INSERT INTO problems VALUES(DEFAULT, 'A', 'A.pdf', 1, 'Elmano parece candy pero no es candy asi que candy candy aprende algo candy','notCandy');

	SELECT currval('problems_id_seq') INTO pid;

	INSERT INTO problems_content VALUES(pid,'aennginif si  isngua ugn augouneaiurgn uonrg arug ar auiguoireaig ueagurarug eauib uah baurgb auireg be uibg bvananinaviunriu aiunr auiagui lljio');

	INSERT INTO problem_set VALUES(DEFAULT,1,pid);

	SAVEPOINT sp1;

	INSERT INTO problems VALUES(DEFAULT, 'B', 'B.pdf', 2, 'Aca habría una descripción... Si se me ocurriera una!','notDesc');

	SELECT currval('problems_id_seq') INTO pid;

	INSERT INTO problems_content VALUES(pid,'ef pjpg reijg pirgisgji sgi');

	INSERT INTO problem_set VALUES(DEFAULT,1,pid);

	EXCEPTION
		WHEN OTHER THEN
			ROLLBACK TO sp1;


    SAVEPOINT sp2;

	INSERT INTO problems VALUES(DEFAULT, 'C', 'C.pdf', 3, 'soa bashele, haga algo! T-T','soaBashele');

	SELECT currval('problems_id_seq') INTO pid;

	INSERT INTO problems_content VALUES(pid,'lala lalala lala lalala');

	INSERT INTO problem_set VALUES(DEFAULT,1,pid);

	EXCEPTION
		WHEN OTHER THEN
			ROLLBACK TO sp2;
END;
COMMIT;
$$
LANGUAGE plpgsql;

-- Problem update

CREATE OR REPLACE FUNCTION delete_problem_scoreboard() RETURNS TRIGGER AS $$
    BEGIN
    	SET CONSTRAINTS ALL IMMEDIATE;
        DELETE FROM scoreboards WHERE scoreboards.problem_id = OLD.id;
    EXCEPTION
		WHEN OTHERS THEN 
			ROLLBACK;
        RETURN OLD;
    END;
$$ LANGUAGE plpgsql;

DROP TRIGGER t_delete_problem_scoreboard ON problems;
CREATE TRIGGER t_delete_problem_scoreboard
    BEFORE UPDATE ON problems
    FOR EACH ROW EXECUTE PROCEDURE delete_problem_scoreboard();

DELETE FROM problems WHERE id = 658996;
SELECT FROM scoreboards WHERE problem_id = 658996;