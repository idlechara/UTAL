BEGIN TRANSACTION;
CREATE TABLE RECURSO (
    ID INT PRIMARY KEY NOT NULL,
    NOMBRE TEXT NOT NULL,
    DESCRIPCION TEXT,
    TIPO_RECURSO TEXT
);
CREATE TABLE PERSONA (
    ID INT PRIMARY KEY NOT NULL,
    NOMBRE TEXT NOT NULL,
    TELEFONO TEXT,
    CORREO TEXT
);
CREATE TABLE EVENTO (
    ID INT PRIMARY KEY NOT NULL,
    NOMBRE TEXT NOT NULL,
    RESPONSABLE INT,
    BLOQUES INT,
    DESCRIPCION TEXT,
    CORREO CHAR(50)
);
CREATE TABLE BLOQUE (
    ID INT PRIMARY KEY NOT NULL,
    TIMESTAMP_INICIO DATETIME NOT NULL,
    TIMESTAMP_TERMINO DATETIME NOT NULL
);
COMMIT;