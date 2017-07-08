--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.2
-- Dumped by pg_dump version 9.3.2
-- Started on 2013-12-13 19:45:41

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

DROP DATABASE tarea;
--
-- TOC entry 2052 (class 1262 OID 16394)
-- Name: tarea; Type: DATABASE; Schema: -; Owner: postgres
--

CREATE DATABASE tarea WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'Spanish_Spain.1252' LC_CTYPE = 'Spanish_Spain.1252';


ALTER DATABASE tarea OWNER TO postgres;

\connect tarea

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 5 (class 2615 OID 2200)
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- TOC entry 2053 (class 0 OID 0)
-- Dependencies: 5
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


--
-- TOC entry 188 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2055 (class 0 OID 0)
-- Dependencies: 188
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 173 (class 1259 OID 16441)
-- Name: alumnos; Type: TABLE; Schema: public; Owner: usuario; Tablespace: 
--

CREATE TABLE alumnos (
    matricula integer NOT NULL,
    nombre text NOT NULL,
    rut text NOT NULL,
    "año_ingreso" integer NOT NULL,
    id_carrera integer NOT NULL
);


ALTER TABLE public.alumnos OWNER TO usuario;

--
-- TOC entry 172 (class 1259 OID 16439)
-- Name: alumnos_matricula_seq; Type: SEQUENCE; Schema: public; Owner: usuario
--

CREATE SEQUENCE alumnos_matricula_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.alumnos_matricula_seq OWNER TO usuario;

--
-- TOC entry 2056 (class 0 OID 0)
-- Dependencies: 172
-- Name: alumnos_matricula_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: usuario
--

ALTER SEQUENCE alumnos_matricula_seq OWNED BY alumnos.matricula;


--
-- TOC entry 175 (class 1259 OID 16457)
-- Name: beneficios; Type: TABLE; Schema: public; Owner: usuario; Tablespace: 
--

CREATE TABLE beneficios (
    codigo integer NOT NULL,
    nombre text NOT NULL
);


ALTER TABLE public.beneficios OWNER TO usuario;

--
-- TOC entry 184 (class 1259 OID 24650)
-- Name: becas; Type: TABLE; Schema: public; Owner: usuario; Tablespace: 
--

CREATE TABLE becas (
    duracion_maxima integer NOT NULL,
    tipo integer NOT NULL
)
INHERITS (beneficios);


ALTER TABLE public.becas OWNER TO usuario;

--
-- TOC entry 174 (class 1259 OID 16455)
-- Name: beneficios_id_seq; Type: SEQUENCE; Schema: public; Owner: usuario
--

CREATE SEQUENCE beneficios_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.beneficios_id_seq OWNER TO usuario;

--
-- TOC entry 2057 (class 0 OID 0)
-- Dependencies: 174
-- Name: beneficios_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: usuario
--

ALTER SEQUENCE beneficios_id_seq OWNED BY beneficios.codigo;


--
-- TOC entry 171 (class 1259 OID 16408)
-- Name: carreras; Type: TABLE; Schema: public; Owner: usuario; Tablespace: 
--

CREATE TABLE carreras (
    id integer NOT NULL,
    nombre text NOT NULL
);


ALTER TABLE public.carreras OWNER TO usuario;

--
-- TOC entry 170 (class 1259 OID 16406)
-- Name: carreras_id_seq; Type: SEQUENCE; Schema: public; Owner: usuario
--

CREATE SEQUENCE carreras_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.carreras_id_seq OWNER TO usuario;

--
-- TOC entry 2058 (class 0 OID 0)
-- Dependencies: 170
-- Name: carreras_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: usuario
--

ALTER SEQUENCE carreras_id_seq OWNED BY carreras.id;


--
-- TOC entry 185 (class 1259 OID 24664)
-- Name: creditos; Type: TABLE; Schema: public; Owner: usuario; Tablespace: 
--

CREATE TABLE creditos (
    institucion integer NOT NULL
)
INHERITS (beneficios);


ALTER TABLE public.creditos OWNER TO usuario;

--
-- TOC entry 179 (class 1259 OID 16523)
-- Name: documentos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE documentos (
    id integer NOT NULL,
    tipo integer NOT NULL,
    nombre text NOT NULL
);


ALTER TABLE public.documentos OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 24583)
-- Name: documentos_beneficios; Type: TABLE; Schema: public; Owner: usuario; Tablespace: 
--

CREATE TABLE documentos_beneficios (
    id_documento integer NOT NULL,
    id_beneficio integer NOT NULL,
    id integer NOT NULL
);


ALTER TABLE public.documentos_beneficios OWNER TO usuario;

--
-- TOC entry 182 (class 1259 OID 24581)
-- Name: documentos_beneficios_id_seq; Type: SEQUENCE; Schema: public; Owner: usuario
--

CREATE SEQUENCE documentos_beneficios_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.documentos_beneficios_id_seq OWNER TO usuario;

--
-- TOC entry 2059 (class 0 OID 0)
-- Dependencies: 182
-- Name: documentos_beneficios_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: usuario
--

ALTER SEQUENCE documentos_beneficios_id_seq OWNED BY documentos_beneficios.id;


--
-- TOC entry 178 (class 1259 OID 16521)
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
-- TOC entry 2060 (class 0 OID 0)
-- Dependencies: 178
-- Name: documentos_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE documentos_id_seq OWNED BY documentos.id;


--
-- TOC entry 187 (class 1259 OID 24676)
-- Name: instituciones; Type: TABLE; Schema: public; Owner: usuario; Tablespace: 
--

CREATE TABLE instituciones (
    id integer NOT NULL,
    nombre text NOT NULL
);


ALTER TABLE public.instituciones OWNER TO usuario;

--
-- TOC entry 186 (class 1259 OID 24674)
-- Name: instituciones_id_seq; Type: SEQUENCE; Schema: public; Owner: usuario
--

CREATE SEQUENCE instituciones_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.instituciones_id_seq OWNER TO usuario;

--
-- TOC entry 2061 (class 0 OID 0)
-- Dependencies: 186
-- Name: instituciones_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: usuario
--

ALTER SEQUENCE instituciones_id_seq OWNED BY instituciones.id;


--
-- TOC entry 177 (class 1259 OID 16468)
-- Name: postulaciones; Type: TABLE; Schema: public; Owner: usuario; Tablespace: 
--

CREATE TABLE postulaciones (
    id_beneficio integer NOT NULL,
    fecha timestamp without time zone,
    id_alumno integer NOT NULL,
    id integer NOT NULL,
    renovante boolean DEFAULT false NOT NULL
);


ALTER TABLE public.postulaciones OWNER TO usuario;

--
-- TOC entry 176 (class 1259 OID 16466)
-- Name: postulaciones_id_seq; Type: SEQUENCE; Schema: public; Owner: usuario
--

CREATE SEQUENCE postulaciones_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.postulaciones_id_seq OWNER TO usuario;

--
-- TOC entry 2062 (class 0 OID 0)
-- Dependencies: 176
-- Name: postulaciones_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: usuario
--

ALTER SEQUENCE postulaciones_id_seq OWNED BY postulaciones.id;


--
-- TOC entry 181 (class 1259 OID 16534)
-- Name: tipos; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE tipos (
    id integer NOT NULL,
    nombre text NOT NULL,
    descripcion text
);


ALTER TABLE public.tipos OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 16532)
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
-- TOC entry 2063 (class 0 OID 0)
-- Dependencies: 180
-- Name: tipos_documento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE tipos_documento_id_seq OWNED BY tipos.id;


--
-- TOC entry 1882 (class 2604 OID 16444)
-- Name: matricula; Type: DEFAULT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY alumnos ALTER COLUMN matricula SET DEFAULT nextval('alumnos_matricula_seq'::regclass);


--
-- TOC entry 1889 (class 2604 OID 24653)
-- Name: codigo; Type: DEFAULT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY becas ALTER COLUMN codigo SET DEFAULT nextval('beneficios_id_seq'::regclass);


--
-- TOC entry 1883 (class 2604 OID 16460)
-- Name: codigo; Type: DEFAULT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY beneficios ALTER COLUMN codigo SET DEFAULT nextval('beneficios_id_seq'::regclass);


--
-- TOC entry 1881 (class 2604 OID 16411)
-- Name: id; Type: DEFAULT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY carreras ALTER COLUMN id SET DEFAULT nextval('carreras_id_seq'::regclass);


--
-- TOC entry 1890 (class 2604 OID 24667)
-- Name: codigo; Type: DEFAULT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY creditos ALTER COLUMN codigo SET DEFAULT nextval('beneficios_id_seq'::regclass);


--
-- TOC entry 1886 (class 2604 OID 16526)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY documentos ALTER COLUMN id SET DEFAULT nextval('documentos_id_seq'::regclass);


--
-- TOC entry 1888 (class 2604 OID 24586)
-- Name: id; Type: DEFAULT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY documentos_beneficios ALTER COLUMN id SET DEFAULT nextval('documentos_beneficios_id_seq'::regclass);


--
-- TOC entry 1891 (class 2604 OID 24679)
-- Name: id; Type: DEFAULT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY instituciones ALTER COLUMN id SET DEFAULT nextval('instituciones_id_seq'::regclass);


--
-- TOC entry 1884 (class 2604 OID 16471)
-- Name: id; Type: DEFAULT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY postulaciones ALTER COLUMN id SET DEFAULT nextval('postulaciones_id_seq'::regclass);


--
-- TOC entry 1887 (class 2604 OID 16537)
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY tipos ALTER COLUMN id SET DEFAULT nextval('tipos_documento_id_seq'::regclass);


--
-- TOC entry 2033 (class 0 OID 16441)
-- Dependencies: 173
-- Data for Name: alumnos; Type: TABLE DATA; Schema: public; Owner: usuario
--



--
-- TOC entry 2064 (class 0 OID 0)
-- Dependencies: 172
-- Name: alumnos_matricula_seq; Type: SEQUENCE SET; Schema: public; Owner: usuario
--

SELECT pg_catalog.setval('alumnos_matricula_seq', 1, false);


--
-- TOC entry 2044 (class 0 OID 24650)
-- Dependencies: 184
-- Data for Name: becas; Type: TABLE DATA; Schema: public; Owner: usuario
--



--
-- TOC entry 2035 (class 0 OID 16457)
-- Dependencies: 175
-- Data for Name: beneficios; Type: TABLE DATA; Schema: public; Owner: usuario
--



--
-- TOC entry 2065 (class 0 OID 0)
-- Dependencies: 174
-- Name: beneficios_id_seq; Type: SEQUENCE SET; Schema: public; Owner: usuario
--

SELECT pg_catalog.setval('beneficios_id_seq', 1, false);


--
-- TOC entry 2031 (class 0 OID 16408)
-- Dependencies: 171
-- Data for Name: carreras; Type: TABLE DATA; Schema: public; Owner: usuario
--



--
-- TOC entry 2066 (class 0 OID 0)
-- Dependencies: 170
-- Name: carreras_id_seq; Type: SEQUENCE SET; Schema: public; Owner: usuario
--

SELECT pg_catalog.setval('carreras_id_seq', 1, false);


--
-- TOC entry 2045 (class 0 OID 24664)
-- Dependencies: 185
-- Data for Name: creditos; Type: TABLE DATA; Schema: public; Owner: usuario
--



--
-- TOC entry 2039 (class 0 OID 16523)
-- Dependencies: 179
-- Data for Name: documentos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2043 (class 0 OID 24583)
-- Dependencies: 183
-- Data for Name: documentos_beneficios; Type: TABLE DATA; Schema: public; Owner: usuario
--



--
-- TOC entry 2067 (class 0 OID 0)
-- Dependencies: 182
-- Name: documentos_beneficios_id_seq; Type: SEQUENCE SET; Schema: public; Owner: usuario
--

SELECT pg_catalog.setval('documentos_beneficios_id_seq', 1, false);


--
-- TOC entry 2068 (class 0 OID 0)
-- Dependencies: 178
-- Name: documentos_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('documentos_id_seq', 1, false);


--
-- TOC entry 2047 (class 0 OID 24676)
-- Dependencies: 187
-- Data for Name: instituciones; Type: TABLE DATA; Schema: public; Owner: usuario
--



--
-- TOC entry 2069 (class 0 OID 0)
-- Dependencies: 186
-- Name: instituciones_id_seq; Type: SEQUENCE SET; Schema: public; Owner: usuario
--

SELECT pg_catalog.setval('instituciones_id_seq', 1, false);


--
-- TOC entry 2037 (class 0 OID 16468)
-- Dependencies: 177
-- Data for Name: postulaciones; Type: TABLE DATA; Schema: public; Owner: usuario
--



--
-- TOC entry 2070 (class 0 OID 0)
-- Dependencies: 176
-- Name: postulaciones_id_seq; Type: SEQUENCE SET; Schema: public; Owner: usuario
--

SELECT pg_catalog.setval('postulaciones_id_seq', 1, false);


--
-- TOC entry 2041 (class 0 OID 16534)
-- Dependencies: 181
-- Data for Name: tipos; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- TOC entry 2071 (class 0 OID 0)
-- Dependencies: 180
-- Name: tipos_documento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('tipos_documento_id_seq', 1, false);


--
-- TOC entry 1895 (class 2606 OID 16449)
-- Name: alumnoPK; Type: CONSTRAINT; Schema: public; Owner: usuario; Tablespace: 
--

ALTER TABLE ONLY alumnos
    ADD CONSTRAINT "alumnoPK" PRIMARY KEY (matricula);


--
-- TOC entry 1908 (class 2606 OID 24658)
-- Name: becasPK; Type: CONSTRAINT; Schema: public; Owner: usuario; Tablespace: 
--

ALTER TABLE ONLY becas
    ADD CONSTRAINT "becasPK" PRIMARY KEY (codigo);


--
-- TOC entry 1906 (class 2606 OID 24588)
-- Name: beneficio_documentoPK; Type: CONSTRAINT; Schema: public; Owner: usuario; Tablespace: 
--

ALTER TABLE ONLY documentos_beneficios
    ADD CONSTRAINT "beneficio_documentoPK" PRIMARY KEY (id);


--
-- TOC entry 1897 (class 2606 OID 16465)
-- Name: beneficiosPK; Type: CONSTRAINT; Schema: public; Owner: usuario; Tablespace: 
--

ALTER TABLE ONLY beneficios
    ADD CONSTRAINT "beneficiosPK" PRIMARY KEY (codigo);


--
-- TOC entry 1893 (class 2606 OID 16416)
-- Name: carrerasPK; Type: CONSTRAINT; Schema: public; Owner: usuario; Tablespace: 
--

ALTER TABLE ONLY carreras
    ADD CONSTRAINT "carrerasPK" PRIMARY KEY (id);


--
-- TOC entry 1910 (class 2606 OID 24672)
-- Name: creditosPK; Type: CONSTRAINT; Schema: public; Owner: usuario; Tablespace: 
--

ALTER TABLE ONLY creditos
    ADD CONSTRAINT "creditosPK" PRIMARY KEY (codigo);


--
-- TOC entry 1902 (class 2606 OID 16531)
-- Name: documentoPK; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY documentos
    ADD CONSTRAINT "documentoPK" PRIMARY KEY (id);


--
-- TOC entry 1912 (class 2606 OID 24684)
-- Name: institucionesPK; Type: CONSTRAINT; Schema: public; Owner: usuario; Tablespace: 
--

ALTER TABLE ONLY instituciones
    ADD CONSTRAINT "institucionesPK" PRIMARY KEY (id);


--
-- TOC entry 1900 (class 2606 OID 16473)
-- Name: postulacionPK; Type: CONSTRAINT; Schema: public; Owner: usuario; Tablespace: 
--

ALTER TABLE ONLY postulaciones
    ADD CONSTRAINT "postulacionPK" PRIMARY KEY (id);


--
-- TOC entry 1904 (class 2606 OID 16542)
-- Name: tipo_documentoPK; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY tipos
    ADD CONSTRAINT "tipo_documentoPK" PRIMARY KEY (id);


--
-- TOC entry 1898 (class 1259 OID 24695)
-- Name: fki_beneficiosFK; Type: INDEX; Schema: public; Owner: usuario; Tablespace: 
--

CREATE INDEX "fki_beneficiosFK" ON postulaciones USING btree (id_beneficio);


--
-- TOC entry 1919 (class 2606 OID 24706)
-- Name: becaFK; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY documentos_beneficios
    ADD CONSTRAINT "becaFK" FOREIGN KEY (id_beneficio) REFERENCES becas(codigo);


--
-- TOC entry 1916 (class 2606 OID 24696)
-- Name: beneficios2FK; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY postulaciones
    ADD CONSTRAINT "beneficios2FK" FOREIGN KEY (id_beneficio) REFERENCES creditos(codigo);


--
-- TOC entry 1915 (class 2606 OID 24690)
-- Name: beneficiosFK; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY postulaciones
    ADD CONSTRAINT "beneficiosFK" FOREIGN KEY (id_beneficio) REFERENCES becas(codigo);


--
-- TOC entry 1918 (class 2606 OID 24701)
-- Name: creditoFK; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY documentos_beneficios
    ADD CONSTRAINT "creditoFK" FOREIGN KEY (id_beneficio) REFERENCES creditos(codigo);


--
-- TOC entry 1920 (class 2606 OID 24711)
-- Name: documentoFK; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY documentos_beneficios
    ADD CONSTRAINT "documentoFK" FOREIGN KEY (id_documento) REFERENCES documentos(id);


--
-- TOC entry 1914 (class 2606 OID 16492)
-- Name: id_alumno; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY postulaciones
    ADD CONSTRAINT id_alumno FOREIGN KEY (id_alumno) REFERENCES alumnos(matricula);


--
-- TOC entry 1913 (class 2606 OID 16450)
-- Name: id_carreraFK; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY alumnos
    ADD CONSTRAINT "id_carreraFK" FOREIGN KEY (id_carrera) REFERENCES carreras(id);


--
-- TOC entry 1917 (class 2606 OID 16543)
-- Name: id_tipo; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY documentos
    ADD CONSTRAINT id_tipo FOREIGN KEY (tipo) REFERENCES tipos(id);


--
-- TOC entry 1922 (class 2606 OID 24685)
-- Name: institucionesFK; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY creditos
    ADD CONSTRAINT "institucionesFK" FOREIGN KEY (institucion) REFERENCES instituciones(id);


--
-- TOC entry 1921 (class 2606 OID 24659)
-- Name: tipoFK; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY becas
    ADD CONSTRAINT "tipoFK" FOREIGN KEY (tipo) REFERENCES tipos(id);


--
-- TOC entry 2054 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2013-12-13 19:45:41

--
-- PostgreSQL database dump complete
--

