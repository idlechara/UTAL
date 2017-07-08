-- --Documentos Entregados

SELECT DISTINCT
	alumnos.matricula,
-- 	postulaciones.id AS id_postulacion,
	alumnos.nombre,
	postulaciones_documentos.id_documento AS id_documento_requerido,
	documentos.nombre AS nombre_documento
FROM
	alumnos
JOIN
	postulaciones ON (alumnos.matricula = postulaciones.id_alumno)
JOIN
	postulaciones_documentos ON (postulaciones_documentos.id_postulacion = postulaciones.id)
JOIN
	documentos ON (postulaciones_documentos.id_documento = documentos.id)

ORDER BY
	alumnos.nombre, nombre_documento, id_documento_requerido