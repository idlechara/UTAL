

CREATE INDEX alumnosidx
  ON alumnos
  USING gist
  (matricula);
  
CREATE INDEX beneficiosidx
  ON beneficios
  USING gist
  (codigo);
  
CREATE INDEX carrerasidx
  ON carreras
  USING gist
  (id);
  
CREATE INDEX documentos_idx
  ON documentos
  USING gist
  (tipo);
  
CREATE INDEX documentos__idx
  ON documentos
  USING gist
  (id);
  
CREATE INDEX doc_beneidx
  ON documentos_beneficios
  USING gist
  (id);
CREATE INDEX doc_bene_idx
  ON documentos_beneficios
  USING gist
  (id_documento);
CREATE INDEX doc_bene__idx
  ON documentos_beneficios
  USING gist
  (id_beneficio);
  
CREATE INDEX institucionesidx
  ON instituciones
  USING gist
  (id);
  
CREATE INDEX postulacionesidx
  ON postulaciones
  USING gist
  (id_beneficio);
CREATE INDEX postulaciones_idx
  ON postulaciones
  USING gist
  (id_alumno);
CREATE INDEX postulaciones__idx
  ON postulaciones
  USING gist
  (id);
  
CREATE INDEX postulacionesdocumentosidx
  ON postulaciones_documentos
  USING gist
  (id_documento);
  CREATE INDEX postulaciones_documentosidx
  ON postulaciones_documentos
  USING gist
  (id);
  CREATE INDEX postulaciones__documentosidx
  ON postulaciones_documentos
  USING gist
  (id_postulacion);
  
CREATE INDEX tiposidx
  ON tipos
  USING gist
  (id);
  