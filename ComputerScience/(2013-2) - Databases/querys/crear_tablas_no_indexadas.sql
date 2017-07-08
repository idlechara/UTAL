
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