-- --Documentos requeridos


SELECT DISTINCT
	alumnos.matricula,
-- 	postulaciones.id AS id_postulacion,
	alumnos.nombre,
	documentos_beneficios.id_documento AS id_documento_requerido,
	documentos.nombre AS nombre_documento
FROM
	alumnos
JOIN
	postulaciones ON (alumnos.matricula = postulaciones.id_alumno)
JOIN
	beneficios ON (beneficios.codigo = postulaciones.id_beneficio)
JOIN
	documentos_beneficios ON (documentos_beneficios.id_beneficio = beneficios.codigo)
JOIN
	documentos ON (documentos_beneficios.id_documento = documentos.id)

ORDER BY
	alumnos.nombre, nombre_documento, id_documento_requerido