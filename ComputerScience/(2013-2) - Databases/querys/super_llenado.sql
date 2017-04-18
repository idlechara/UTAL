CREATE OR REPLACE FUNCTION llenar() RETURNS integer AS $$
DECLARE
    mviews RECORD;
BEGIN

	FOR j IN 1..2 LOOP

		FOR i IN 1..150 LOOP
			INSERT INTO tipos (nombre, descripcion) VALUES ( md5(random()::text), md5(random()::text));
		END LOOP;

		FOR i IN 1..50 LOOP
			INSERT INTO documentos (tipo, nombre) VALUES (
				(SELECT id FROM tipos ORDER BY RANDOM() LIMIT 1) , 'documento '||i );
		END LOOP;

		FOR i IN 1..30 LOOP
			INSERT INTO carreras (nombre) VALUES ( md5(random()::text));
		END LOOP;

		FOR i IN 1..3000 LOOP
			INSERT INTO alumnos(nombre, rut, año_ingreso, id_carrera) VALUES ( 'alumno '||i, random()*2000000 +1, random()*2014+1,
					(SELECT id FROM carreras ORDER BY RANDOM() LIMIT 1)
					);
		END LOOP;

		FOR i IN 1..50 LOOP
			INSERT INTO instituciones(nombre) VALUES ('banco ' ||i);
		END LOOP;

		FOR i IN 1..40 LOOP
			INSERT INTO becas (nombre, duracion_maxima, tipo) VALUES ( 'beca '||i, random()*10+1,
					(SELECT id FROM tipos ORDER BY RANDOM() LIMIT 1));
		END LOOP;

		FOR i IN 1..20LOOP
			INSERT INTO creditos (nombre, institucion) VALUES ( 'credito '||i,
					(SELECT id FROM instituciones ORDER BY RANDOM() LIMIT 1)
					);
		END LOOP;

		FOR i IN 1..500 LOOP
			INSERT INTO documentos_beneficios (id_documento, id_beneficio) VALUES ( (SELECT id FROM documentos ORDER BY RANDOM() LIMIT 1),
					(SELECT codigo FROM beneficios ORDER BY RANDOM() LIMIT 1)
					);
		END LOOP;
		
		FOR i IN 1..6000 LOOP
			INSERT INTO postulaciones (id_beneficio, fecha,id_alumno, renovante) VALUES (
					(SELECT codigo FROM beneficios ORDER BY RANDOM() LIMIT 1), '2013-11-18 17:39:00',
					(SELECT matricula FROM alumnos ORDER BY RANDOM() LIMIT 1), true
					);
		END LOOP;
		
		FOR i IN 1..7000 LOOP
			INSERT INTO postulaciones_documentos (id_documento, id_postulacion, contenido) VALUES (
					(SELECT id FROM documentos ORDER BY RANDOM() LIMIT 1), 
					(SELECT id FROM postulaciones ORDER BY RANDOM() LIMIT 1), md5(random()::text)
					);
		END LOOP;
	END LOOP;
    RETURN 1;
END;
$$ LANGUAGE plpgsql;

SELECT llenar();