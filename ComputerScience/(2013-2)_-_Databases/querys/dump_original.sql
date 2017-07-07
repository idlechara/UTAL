--
-- PostgreSQL database dump
--

-- Dumped from database version 9.1.11
-- Dumped by pg_dump version 9.1.11
-- Started on 2013-12-17 00:44:11 CLST

SET statement_timeout = 0;
SET client_encoding = 'SQL_ASCII';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 2018 (class 1262 OID 16384)
-- Name: tarea; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE tarea WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


ALTER DATABASE tarea OWNER TO postgres;

\connect tarea

SET statement_timeout = 0;
SET client_encoding = 'SQL_ASCII';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 181 (class 3079 OID 11679)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2021 (class 0 OID 0)
-- Dependencies: 181
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 161 (class 1259 OID 16539)
-- Dependencies: 6
-- Name: alumnos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE alumnos (
    matricula integer NOT NULL,
    nombre text NOT NULL,
    rut text NOT NULL,
    "año_ingreso" integer NOT NULL,
    id_carrera integer NOT NULL
);


ALTER TABLE public.alumnos OWNER TO postgres;

--
-- TOC entry 162 (class 1259 OID 16545)
-- Dependencies: 161 6
-- Name: alumnos_matricula_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE alumnos_matricula_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.alumnos_matricula_seq OWNER TO postgres;

--
-- TOC entry 2022 (class 0 OID 0)
-- Dependencies: 162
-- Name: alumnos_matricula_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE alumnos_matricula_seq OWNED BY alumnos.matricula;


--
-- TOC entry 163 (class 1259 OID 16547)
-- Dependencies: 6
-- Name: beneficios; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE beneficios (
    codigo integer NOT NULL,
    nombre text NOT NULL
);


ALTER TABLE public.beneficios OWNER TO postgres;

--
-- TOC entry 164 (class 1259 OID 16553)
-- Dependencies: 163 6
-- Name: becas; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE becas (
    duracion_maxima integer NOT NULL,
    tipo integer NOT NULL
)
INHERITS (beneficios);


ALTER TABLE public.becas OWNER TO postgres;

--
-- TOC entry 165 (class 1259 OID 16559)
-- Dependencies: 6 163
-- Name: beneficios_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE beneficios_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.beneficios_id_seq OWNER TO postgres;

--
-- TOC entry 2023 (class 0 OID 0)
-- Dependencies: 165
-- Name: beneficios_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE beneficios_id_seq OWNED BY beneficios.codigo;


--
-- TOC entry 166 (class 1259 OID 16561)
-- Dependencies: 6
-- Name: carreras; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE carreras (
    id integer NOT NULL,
    nombre text NOT NULL
);


ALTER TABLE public.carreras OWNER TO postgres;

--
-- TOC entry 167 (class 1259 OID 16567)
-- Dependencies: 6 166
-- Name: carreras_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE carreras_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.carreras_id_seq OWNER TO postgres;

--
-- TOC entry 2024 (class 0 OID 0)
-- Dependencies: 167
-- Name: carreras_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE carreras_id_seq OWNED BY carreras.id;


--
-- TOC entry 168 (class 1259 OID 16569)
-- Dependencies: 163 6
-- Name: creditos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE creditos (
    institucion integer NOT NULL
)
INHERITS (beneficios);


ALTER TABLE public.creditos OWNER TO postgres;

--
-- TOC entry 169 (class 1259 OID 16575)
-- Dependencies: 6
-- Name: documentos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE documentos (
    id integer NOT NULL,
    tipo integer NOT NULL,
    nombre text NOT NULL
);


ALTER TABLE public.documentos OWNER TO postgres;

--
-- TOC entry 170 (class 1259 OID 16581)
-- Dependencies: 6
-- Name: documentos_beneficios; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE documentos_beneficios (
    id_documento integer NOT NULL,
    id_beneficio integer NOT NULL,
    id integer NOT NULL
);


ALTER TABLE public.documentos_beneficios OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 16584)
-- Dependencies: 6 170
-- Name: documentos_beneficios_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE documentos_beneficios_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.documentos_beneficios_id_seq OWNER TO postgres;

--
-- TOC entry 2025 (class 0 OID 0)
-- Dependencies: 171
-- Name: documentos_beneficios_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE documentos_beneficios_id_seq OWNED BY documentos_beneficios.id;


--
-- TOC entry 172 (class 1259 OID 16586)
-- Dependencies: 6 169
-- Name: documentos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE documentos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.documentos_id_seq OWNER TO postgres;

--
-- TOC entry 2026 (class 0 OID 0)
-- Dependencies: 172
-- Name: documentos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE documentos_id_seq OWNED BY documentos.id;


--
-- TOC entry 173 (class 1259 OID 16588)
-- Dependencies: 6
-- Name: instituciones; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE instituciones (
    id integer NOT NULL,
    nombre text NOT NULL
);


ALTER TABLE public.instituciones OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 16594)
-- Dependencies: 173 6
-- Name: instituciones_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE instituciones_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.instituciones_id_seq OWNER TO postgres;

--
-- TOC entry 2027 (class 0 OID 0)
-- Dependencies: 174
-- Name: instituciones_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE instituciones_id_seq OWNED BY instituciones.id;


--
-- TOC entry 175 (class 1259 OID 16596)
-- Dependencies: 1858 6
-- Name: postulaciones; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE postulaciones (
    id_beneficio integer NOT NULL,
    fecha timestamp without time zone,
    id_alumno integer NOT NULL,
    id integer NOT NULL,
    renovante boolean DEFAULT false NOT NULL
);


ALTER TABLE public.postulaciones OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 16849)
-- Dependencies: 6
-- Name: postulaciones_documentos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE postulaciones_documentos (
    id integer NOT NULL,
    id_documento integer NOT NULL,
    id_postulacion integer NOT NULL,
    contenido text
);


ALTER TABLE public.postulaciones_documentos OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 16847)
-- Dependencies: 180 6
-- Name: postulaciones_documentos_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE postulaciones_documentos_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.postulaciones_documentos_id_seq OWNER TO postgres;

--
-- TOC entry 2028 (class 0 OID 0)
-- Dependencies: 179
-- Name: postulaciones_documentos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE postulaciones_documentos_id_seq OWNED BY postulaciones_documentos.id;


--
-- TOC entry 176 (class 1259 OID 16600)
-- Dependencies: 175 6
-- Name: postulaciones_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE postulaciones_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.postulaciones_id_seq OWNER TO postgres;

--
-- TOC entry 2029 (class 0 OID 0)
-- Dependencies: 176
-- Name: postulaciones_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE postulaciones_id_seq OWNED BY postulaciones.id;


--
-- TOC entry 177 (class 1259 OID 16602)
-- Dependencies: 6
-- Name: tipos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tipos (
    id integer NOT NULL,
    nombre text NOT NULL,
    descripcion text
);


ALTER TABLE public.tipos OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 16608)
-- Dependencies: 177 6
-- Name: tipos_documento_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE tipos_documento_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tipos_documento_id_seq OWNER TO postgres;

--
-- TOC entry 2030 (class 0 OID 0)
-- Dependencies: 178
-- Name: tipos_documento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tipos_documento_id_seq OWNED BY tipos.id;


--
-- TOC entry 1850 (class 2604 OID 16610)
-- Dependencies: 162 161
-- Name: matricula; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY alumnos ALTER COLUMN matricula SET DEFAULT nextval('alumnos_matricula_seq'::regclass);


--
-- TOC entry 1852 (class 2604 OID 16611)
-- Dependencies: 164 165 164
-- Name: codigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY becas ALTER COLUMN codigo SET DEFAULT nextval('beneficios_id_seq'::regclass);


--
-- TOC entry 1851 (class 2604 OID 16612)
-- Dependencies: 165 163
-- Name: codigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY beneficios ALTER COLUMN codigo SET DEFAULT nextval('beneficios_id_seq'::regclass);


--
-- TOC entry 1853 (class 2604 OID 16613)
-- Dependencies: 167 166
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY carreras ALTER COLUMN id SET DEFAULT nextval('carreras_id_seq'::regclass);


--
-- TOC entry 1854 (class 2604 OID 16614)
-- Dependencies: 168 165 168
-- Name: codigo; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY creditos ALTER COLUMN codigo SET DEFAULT nextval('beneficios_id_seq'::regclass);


--
-- TOC entry 1855 (class 2604 OID 16615)
-- Dependencies: 172 169
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY documentos ALTER COLUMN id SET DEFAULT nextval('documentos_id_seq'::regclass);


--
-- TOC entry 1856 (class 2604 OID 16616)
-- Dependencies: 171 170
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY documentos_beneficios ALTER COLUMN id SET DEFAULT nextval('documentos_beneficios_id_seq'::regclass);


--
-- TOC entry 1857 (class 2604 OID 16617)
-- Dependencies: 174 173
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY instituciones ALTER COLUMN id SET DEFAULT nextval('instituciones_id_seq'::regclass);


--
-- TOC entry 1859 (class 2604 OID 16618)
-- Dependencies: 176 175
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY postulaciones ALTER COLUMN id SET DEFAULT nextval('postulaciones_id_seq'::regclass);


--
-- TOC entry 1861 (class 2604 OID 16852)
-- Dependencies: 180 179 180
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY postulaciones_documentos ALTER COLUMN id SET DEFAULT nextval('postulaciones_documentos_id_seq'::regclass);


--
-- TOC entry 1860 (class 2604 OID 16619)
-- Dependencies: 178 177
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipos ALTER COLUMN id SET DEFAULT nextval('tipos_documento_id_seq'::regclass);


--
-- TOC entry 1994 (class 0 OID 16539)
-- Dependencies: 161 2014
-- Data for Name: alumnos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO alumnos (matricula, nombre, rut, "año_ingreso", id_carrera) VALUES (1, 'alumno 1', '1231564', 1999, 1);
INSERT INTO alumnos (matricula, nombre, rut, "año_ingreso", id_carrera) VALUES (2, 'alumno 2', '123456', 2000, 1);
INSERT INTO alumnos (matricula, nombre, rut, "año_ingreso", id_carrera) VALUES (3, 'alumno 3', '1212', 1990, 2);


--
-- TOC entry 2031 (class 0 OID 0)
-- Dependencies: 162
-- Name: alumnos_matricula_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('alumnos_matricula_seq', 3, true);


--
-- TOC entry 1997 (class 0 OID 16553)
-- Dependencies: 164 2014
-- Data for Name: becas; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO becas (codigo, nombre, duracion_maxima, tipo) VALUES (5, 'beca 1', 1, 1);
INSERT INTO becas (codigo, nombre, duracion_maxima, tipo) VALUES (7, 'beca 2', 2, 1);
INSERT INTO becas (codigo, nombre, duracion_maxima, tipo) VALUES (8, 'beca 3', 23, 1);


--
-- TOC entry 1996 (class 0 OID 16547)
-- Dependencies: 163 2014
-- Data for Name: beneficios; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO beneficios (codigo, nombre) VALUES (99, 'concha, chupalo!');


--
-- TOC entry 2032 (class 0 OID 0)
-- Dependencies: 165
-- Name: beneficios_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('beneficios_id_seq', 12, true);


--
-- TOC entry 1999 (class 0 OID 16561)
-- Dependencies: 166 2014
-- Data for Name: carreras; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO carreras (id, nombre) VALUES (1, 'compu');
INSERT INTO carreras (id, nombre) VALUES (2, 'industrial');
INSERT INTO carreras (id, nombre) VALUES (3, 'minas');
INSERT INTO carreras (id, nombre) VALUES (4, 'mecaninca');


--
-- TOC entry 2033 (class 0 OID 0)
-- Dependencies: 167
-- Name: carreras_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('carreras_id_seq', 4, true);


--
-- TOC entry 2001 (class 0 OID 16569)
-- Dependencies: 168 2014
-- Data for Name: creditos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO creditos (codigo, nombre, institucion) VALUES (9, 'credito 1', 1);
INSERT INTO creditos (codigo, nombre, institucion) VALUES (10, 'credito 2', 2);
INSERT INTO creditos (codigo, nombre, institucion) VALUES (12, 'credito 4 ', 1);
INSERT INTO creditos (codigo, nombre, institucion) VALUES (11, 'credito 3', 3);
INSERT INTO creditos (codigo, nombre, institucion) VALUES (13, 'credito 5', 1);


--
-- TOC entry 2002 (class 0 OID 16575)
-- Dependencies: 169 2014
-- Data for Name: documentos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO documentos (id, tipo, nombre) VALUES (1, 1, 'documento 1 -1');
INSERT INTO documentos (id, tipo, nombre) VALUES (2, 1, 'documento 1-2');
INSERT INTO documentos (id, tipo, nombre) VALUES (4, 2, 'documento 2-1');
INSERT INTO documentos (id, tipo, nombre) VALUES (6, 2, 'documento 2-2');


--
-- TOC entry 2003 (class 0 OID 16581)
-- Dependencies: 170 2014
-- Data for Name: documentos_beneficios; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO documentos_beneficios (id_documento, id_beneficio, id) VALUES (1, 99, 36);
INSERT INTO documentos_beneficios (id_documento, id_beneficio, id) VALUES (1, 5, 41);
INSERT INTO documentos_beneficios (id_documento, id_beneficio, id) VALUES (1, 7, 43);
INSERT INTO documentos_beneficios (id_documento, id_beneficio, id) VALUES (2, 7, 44);
INSERT INTO documentos_beneficios (id_documento, id_beneficio, id) VALUES (6, 7, 48);
INSERT INTO documentos_beneficios (id_documento, id_beneficio, id) VALUES (1, 2, 49);
INSERT INTO documentos_beneficios (id_documento, id_beneficio, id) VALUES (2, 2, 50);
INSERT INTO documentos_beneficios (id_documento, id_beneficio, id) VALUES (4, 2, 51);


--
-- TOC entry 2034 (class 0 OID 0)
-- Dependencies: 171
-- Name: documentos_beneficios_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('documentos_beneficios_id_seq', 51, true);


--
-- TOC entry 2035 (class 0 OID 0)
-- Dependencies: 172
-- Name: documentos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('documentos_id_seq', 6, true);


--
-- TOC entry 2006 (class 0 OID 16588)
-- Dependencies: 173 2014
-- Data for Name: instituciones; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO instituciones (id, nombre) VALUES (1, 'banco 1');
INSERT INTO instituciones (id, nombre) VALUES (2, 'banco 2');
INSERT INTO instituciones (id, nombre) VALUES (3, 'banco 3');


--
-- TOC entry 2036 (class 0 OID 0)
-- Dependencies: 174
-- Name: instituciones_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('instituciones_id_seq', 3, true);


--
-- TOC entry 2008 (class 0 OID 16596)
-- Dependencies: 175 2014
-- Data for Name: postulaciones; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO postulaciones (id_beneficio, fecha, id_alumno, id, renovante) VALUES (5, '1990-12-12 17:50:15', 1, 2, true);
INSERT INTO postulaciones (id_beneficio, fecha, id_alumno, id, renovante) VALUES (6, '1990-12-12 17:50:15', 2, 3, true);
INSERT INTO postulaciones (id_beneficio, fecha, id_alumno, id, renovante) VALUES (5, '1990-12-12 17:50:15', 2, 4, false);
INSERT INTO postulaciones (id_beneficio, fecha, id_alumno, id, renovante) VALUES (7, '1990-12-12 17:50:15', 1, 5, true);
INSERT INTO postulaciones (id_beneficio, fecha, id_alumno, id, renovante) VALUES (7, '1990-12-12 17:50:15', 1, 6, true);


--
-- TOC entry 2013 (class 0 OID 16849)
-- Dependencies: 180 2014
-- Data for Name: postulaciones_documentos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO postulaciones_documentos (id, id_documento, id_postulacion, contenido) VALUES (1, 1, 5, 'asdfasdfasfasdf');
INSERT INTO postulaciones_documentos (id, id_documento, id_postulacion, contenido) VALUES (2, 2, 5, 'asdfasfdasdf');
INSERT INTO postulaciones_documentos (id, id_documento, id_postulacion, contenido) VALUES (14, 6, 4, NULL);


--
-- TOC entry 2037 (class 0 OID 0)
-- Dependencies: 179
-- Name: postulaciones_documentos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('postulaciones_documentos_id_seq', 14, true);


--
-- TOC entry 2038 (class 0 OID 0)
-- Dependencies: 176
-- Name: postulaciones_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('postulaciones_id_seq', 6, true);


--
-- TOC entry 2010 (class 0 OID 16602)
-- Dependencies: 177 2014
-- Data for Name: tipos; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO tipos (id, nombre, descripcion) VALUES (1, 'beca', 'asda');
INSERT INTO tipos (id, nombre, descripcion) VALUES (2, 'credito', 'asdasdasda');


--
-- TOC entry 2039 (class 0 OID 0)
-- Dependencies: 178
-- Name: tipos_documento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipos_documento_id_seq', 2, true);


--
-- TOC entry 1863 (class 2606 OID 16621)
-- Dependencies: 161 161 2015
-- Name: alumnoPK; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY alumnos
    ADD CONSTRAINT "alumnoPK" PRIMARY KEY (matricula);


--
-- TOC entry 1867 (class 2606 OID 16623)
-- Dependencies: 164 164 2015
-- Name: becasPK; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY becas
    ADD CONSTRAINT "becasPK" PRIMARY KEY (codigo);


--
-- TOC entry 1875 (class 2606 OID 16625)
-- Dependencies: 170 170 2015
-- Name: beneficio_documentoPK; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY documentos_beneficios
    ADD CONSTRAINT "beneficio_documentoPK" PRIMARY KEY (id);


--
-- TOC entry 1865 (class 2606 OID 16627)
-- Dependencies: 163 163 2015
-- Name: beneficiosPK; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY beneficios
    ADD CONSTRAINT "beneficiosPK" PRIMARY KEY (codigo);


--
-- TOC entry 1869 (class 2606 OID 16629)
-- Dependencies: 166 166 2015
-- Name: carrerasPK; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY carreras
    ADD CONSTRAINT "carrerasPK" PRIMARY KEY (id);


--
-- TOC entry 1871 (class 2606 OID 16631)
-- Dependencies: 168 168 2015
-- Name: creditosPK; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY creditos
    ADD CONSTRAINT "creditosPK" PRIMARY KEY (codigo);


--
-- TOC entry 1873 (class 2606 OID 16633)
-- Dependencies: 169 169 2015
-- Name: documentoPK; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY documentos
    ADD CONSTRAINT "documentoPK" PRIMARY KEY (id);


--
-- TOC entry 1884 (class 2606 OID 16857)
-- Dependencies: 180 180 2015
-- Name: documento_postulacionPK; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY postulaciones_documentos
    ADD CONSTRAINT "documento_postulacionPK" PRIMARY KEY (id);


--
-- TOC entry 1877 (class 2606 OID 16635)
-- Dependencies: 173 173 2015
-- Name: institucionesPK; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY instituciones
    ADD CONSTRAINT "institucionesPK" PRIMARY KEY (id);


--
-- TOC entry 1880 (class 2606 OID 16637)
-- Dependencies: 175 175 2015
-- Name: postulacionPK; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY postulaciones
    ADD CONSTRAINT "postulacionPK" PRIMARY KEY (id);


--
-- TOC entry 1882 (class 2606 OID 16639)
-- Dependencies: 177 177 2015
-- Name: tipo_documentoPK; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tipos
    ADD CONSTRAINT "tipo_documentoPK" PRIMARY KEY (id);


--
-- TOC entry 1878 (class 1259 OID 16640)
-- Dependencies: 175 2015
-- Name: fki_beneficiosFK; Type: INDEX; Schema: public; Owner: postgres; Tablespace: 
--

CREATE INDEX "fki_beneficiosFK" ON postulaciones USING btree (id_beneficio);


--
-- TOC entry 1889 (class 2606 OID 16837)
-- Dependencies: 169 170 1872 2015
-- Name: documentoFK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY documentos_beneficios
    ADD CONSTRAINT "documentoFK" FOREIGN KEY (id_documento) REFERENCES documentos(id);


--
-- TOC entry 1891 (class 2606 OID 16858)
-- Dependencies: 1872 169 180 2015
-- Name: documentoFK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY postulaciones_documentos
    ADD CONSTRAINT "documentoFK" FOREIGN KEY (id_documento) REFERENCES documentos(id);


--
-- TOC entry 1890 (class 2606 OID 16842)
-- Dependencies: 175 1862 161 2015
-- Name: id_alumno; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY postulaciones
    ADD CONSTRAINT id_alumno FOREIGN KEY (id_alumno) REFERENCES alumnos(matricula);


--
-- TOC entry 1885 (class 2606 OID 16671)
-- Dependencies: 1868 161 166 2015
-- Name: id_carreraFK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY alumnos
    ADD CONSTRAINT "id_carreraFK" FOREIGN KEY (id_carrera) REFERENCES carreras(id);


--
-- TOC entry 1888 (class 2606 OID 16676)
-- Dependencies: 169 177 1881 2015
-- Name: id_tipo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY documentos
    ADD CONSTRAINT id_tipo FOREIGN KEY (tipo) REFERENCES tipos(id);


--
-- TOC entry 1887 (class 2606 OID 16681)
-- Dependencies: 168 1876 173 2015
-- Name: institucionesFK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY creditos
    ADD CONSTRAINT "institucionesFK" FOREIGN KEY (institucion) REFERENCES instituciones(id);


--
-- TOC entry 1892 (class 2606 OID 16863)
-- Dependencies: 1879 175 180 2015
-- Name: postulacionFK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY postulaciones_documentos
    ADD CONSTRAINT "postulacionFK" FOREIGN KEY (id_postulacion) REFERENCES postulaciones(id);


--
-- TOC entry 1886 (class 2606 OID 16686)
-- Dependencies: 177 164 1881 2015
-- Name: tipoFK; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY becas
    ADD CONSTRAINT "tipoFK" FOREIGN KEY (tipo) REFERENCES tipos(id);


--
-- TOC entry 2020 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2013-12-17 00:44:11 CLST

--
-- PostgreSQL database dump complete
--

