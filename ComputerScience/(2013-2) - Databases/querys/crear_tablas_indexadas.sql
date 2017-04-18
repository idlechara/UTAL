CREATE TABLE alumnos (
    matricula serial NOT NULL,
    nombre text NOT NULL,
    rut text NOT NULL,
    "año_ingreso" integer NOT NULL,
    id_carrera integer NOT NULL
);

CREATE TABLE beneficios (
    codigo serial NOT NULL,
    nombre text NOT NULL
);

CREATE TABLE becas (
    duracion_maxima integer NOT NULL,
    tipo integer NOT NULL
)
INHERITS (beneficios);

CREATE TABLE carreras (
    id serial NOT NULL,
    nombre text NOT NULL
);

CREATE TABLE creditos (
    institucion integer NOT NULL
)
INHERITS (beneficios);
CREATE TABLE documentos (
    id serial NOT NULL,
    tipo integer NOT NULL,
    nombre text NOT NULL
);

CREATE TABLE documentos_beneficios (
    id_documento integer NOT NULL,
    id_beneficio integer NOT NULL,
    id serial NOT NULL
);

CREATE TABLE instituciones (
    id serial NOT NULL,
    nombre text NOT NULL
);

CREATE TABLE postulaciones (
    id_beneficio integer NOT NULL,
    fecha timestamp without time zone,
    id_alumno integer NOT NULL,
    id serial NOT NULL,
    renovante boolean DEFAULT false NOT NULL
);

CREATE TABLE postulaciones_documentos (
    id serial NOT NULL,
    id_documento integer NOT NULL,
    id_postulacion integer NOT NULL,
    contenido text
);

CREATE TABLE tipos (
    id serial NOT NULL,
    nombre text NOT NULL,
    descripcion text
);


ALTER TABLE ONLY alumnos
    ADD CONSTRAINT "alumnoPK" PRIMARY KEY (matricula);



ALTER TABLE ONLY becas
    ADD CONSTRAINT "becasPK" PRIMARY KEY (codigo);


ALTER TABLE ONLY documentos_beneficios
    ADD CONSTRAINT "beneficio_documentoPK" PRIMARY KEY (id);

ALTER TABLE ONLY beneficios
    ADD CONSTRAINT "beneficiosPK" PRIMARY KEY (codigo);


ALTER TABLE ONLY carreras
    ADD CONSTRAINT "carrerasPK" PRIMARY KEY (id);



ALTER TABLE ONLY creditos
    ADD CONSTRAINT "creditosPK" PRIMARY KEY (codigo);


ALTER TABLE ONLY documentos
    ADD CONSTRAINT "documentoPK" PRIMARY KEY (id);


ALTER TABLE ONLY postulaciones_documentos
    ADD CONSTRAINT "documento_postulacionPK" PRIMARY KEY (id);


ALTER TABLE ONLY instituciones
    ADD CONSTRAINT "institucionesPK" PRIMARY KEY (id);

ALTER TABLE ONLY postulaciones
    ADD CONSTRAINT "postulacionPK" PRIMARY KEY (id);


ALTER TABLE ONLY tipos
    ADD CONSTRAINT "tipo_documentoPK" PRIMARY KEY (id);



ALTER TABLE ONLY documentos_beneficios
    ADD CONSTRAINT "documentoFK" FOREIGN KEY (id_documento) REFERENCES documentos(id);


ALTER TABLE ONLY postulaciones_documentos
    ADD CONSTRAINT "documentoFK" FOREIGN KEY (id_documento) REFERENCES documentos(id);


ALTER TABLE ONLY postulaciones
    ADD CONSTRAINT id_alumno FOREIGN KEY (id_alumno) REFERENCES alumnos(matricula);



ALTER TABLE ONLY alumnos
    ADD CONSTRAINT "id_carreraFK" FOREIGN KEY (id_carrera) REFERENCES carreras(id);


ALTER TABLE ONLY documentos
    ADD CONSTRAINT id_tipo FOREIGN KEY (tipo) REFERENCES tipos(id);



ALTER TABLE ONLY creditos
    ADD CONSTRAINT "institucionesFK" FOREIGN KEY (institucion) REFERENCES instituciones(id);


ALTER TABLE ONLY postulaciones_documentos
    ADD CONSTRAINT "postulacionFK" FOREIGN KEY (id_postulacion) REFERENCES postulaciones(id);


ALTER TABLE ONLY becas
    ADD CONSTRAINT "tipoFK" FOREIGN KEY (tipo) REFERENCES tipos(id);


CREATE INDEX alumnosidx
  ON alumnos
  USING btree
  (matricula);
  
CREATE INDEX beneficiosidx
  ON beneficios
  USING btree
  (codigo);
  
CREATE INDEX carrerasidx
  ON carreras
  USING btree
  (id);
  
CREATE INDEX documentos_idx
  ON documentos
  USING btree
  (tipo);
  
CREATE INDEX documentos__idx
  ON documentos
  USING btree
  (id);
  
CREATE INDEX doc_beneidx
  ON documentos_beneficios
  USING btree
  (id);
CREATE INDEX doc_bene_idx
  ON documentos_beneficios
  USING btree
  (id_documento);
CREATE INDEX doc_bene__idx
  ON documentos_beneficios
  USING btree
  (id_beneficio);
  
CREATE INDEX institucionesidx
  ON instituciones
  USING btree
  (id);
  
CREATE INDEX postulacionesidx
  ON postulaciones
  USING btree
  (id_beneficio);
CREATE INDEX postulaciones_idx
  ON postulaciones
  USING btree
  (id_alumno);
CREATE INDEX postulaciones__idx
  ON postulaciones
  USING btree
  (id);
  
CREATE INDEX postulacionesdocumentosidx
  ON postulaciones_documentos
  USING btree
  (id_documento);
  CREATE INDEX postulaciones_documentosidx
  ON postulaciones_documentos
  USING btree
  (id);
  CREATE INDEX postulaciones__documentosidx
  ON postulaciones_documentos
  USING btree
  (id_postulacion);
  
CREATE INDEX tiposidx
  ON tipos
  USING btree
  (id);
  
