-- Muestra los documentos faltantes por entregar

SELECT DISTINCT 
	requeridos.matricula,
	requeridos.nombre,
	requeridos.id_documento_requerido AS id_documento_faltante,
	requeridos.nombre_documento AS nombre_documento_faltante
	
FROM	-- Documentos requeridos
	(SELECT
		alumnos.matricula AS matricula, 
		alumnos.nombre AS nombre,
		documentos_beneficios.id_documento AS id_documento_requerido,
		documentos.nombre AS nombre_documento
		
	FROM	alumnos
	JOIN	postulaciones ON (alumnos.matricula = postulaciones.id_alumno)
	JOIN	beneficios ON (beneficios.codigo = postulaciones.id_beneficio)
	JOIN	documentos_beneficios ON (documentos_beneficios.id_beneficio = beneficios.codigo)
	JOIN	documentos ON (documentos_beneficios.id_documento = documentos.id)
	) AS requeridos

	LEFT JOIN
		-- Documentos entregados
		(SELECT
			alumnos.matricula AS matricula, 
			alumnos.nombre AS nombre,
			postulaciones_documentos.id_documento AS id_documento_requerido,
			documentos.nombre AS nombre_documento
			
		FROM	alumnos
		JOIN	postulaciones ON (alumnos.matricula = postulaciones.id_alumno)
		JOIN	postulaciones_documentos ON (postulaciones_documentos.id_postulacion = postulaciones.id)
		JOIN	documentos ON (postulaciones_documentos.id_documento = documentos.id)
		) AS entregados
		
	ON (requeridos.matricula = entregados.matricula) 
	AND (requeridos.id_documento_requerido = entregados.id_documento_requerido)

WHERE entregados.id_documento_requerido IS NULL

ORDER BY matricula, nombre, id_documento_faltante, nombre_documento_faltante;