
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
  USING hash
  (matricula);
  
CREATE INDEX beneficiosidx
  ON beneficios
  USING hash
  (codigo);
  
CREATE INDEX carrerasidx
  ON carreras
  USING hash
  (id);
  
CREATE INDEX documentos_idx
  ON documentos
  USING hash
  (tipo);
  
CREATE INDEX documentos__idx
  ON documentos
  USING hash
  (id);
  
CREATE INDEX doc_beneidx
  ON documentos_beneficios
  USING hash
  (id);
CREATE INDEX doc_bene_idx
  ON documentos_beneficios
  USING hash
  (id_documento);
CREATE INDEX doc_bene__idx
  ON documentos_beneficios
  USING hash
  (id_beneficio);
  
CREATE INDEX institucionesidx
  ON instituciones
  USING hash
  (id);
  
CREATE INDEX postulacionesidx
  ON postulaciones
  USING hash
  (id_beneficio);
CREATE INDEX postulaciones_idx
  ON postulaciones
  USING hash
  (id_alumno);
CREATE INDEX postulaciones__idx
  ON postulaciones
  USING hash
  (id);
  
CREATE INDEX postulacionesdocumentosidx
  ON postulaciones_documentos
  USING hash
  (id_documento);
  CREATE INDEX postulaciones_documentosidx
  ON postulaciones_documentos
  USING hash
  (id);
  CREATE INDEX postulaciones__documentosidx
  ON postulaciones_documentos
  USING hash
  (id_postulacion);
  
CREATE INDEX tiposidx
  ON tipos
  USING hash
  (id);
  