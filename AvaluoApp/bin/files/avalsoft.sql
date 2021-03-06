-- -----------------------------------------------------
-- Schema Avalsoft
-- -----------------------------------------------------
CREATE DATABASE avalsoft
    WITH 
    OWNER = postgres
    ENCODING = UTF8
    LC_COLLATE = Spanish_Colombia.1252
    LC_CTYPE = Spanish_Colombia.1252
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- SCHEMA: avalsoft
-- DROP SCHEMA avalsoft ;
CREATE SCHEMA avalsoft
    AUTHORIZATION postgres;


-- -----------------------------------------------------
-- Table avalsoft.rol
-- -----------------------------------------------------
DROP TABLE avalsoft.rol ;

DROP SEQUENCE avalsoft.rol_seq;
CREATE SEQUENCE avalsoft.rol_seq;
ALTER SEQUENCE avalsoft.rol_seq
    OWNER TO postgres; 

CREATE TABLE avalsoft.rol (
  id integer NOT NULL DEFAULT nextval('avalsoft.rol_seq'::regclass),
  nombre VARCHAR(50) NULL,
  descripcion VARCHAR(100) NULL,
  estado boolean not NULL,
  CONSTRAINT pk_rol PRIMARY KEY (id));

ALTER TABLE avalsoft.rol
    OWNER to postgres;

-- -----------------------------------------------------
-- Table avalsoft.empresa
-- -----------------------------------------------------
DROP TABLE avalsoft.empresa ;

DROP SEQUENCE avalsoft.empresa_seq;
CREATE SEQUENCE avalsoft.empresa_seq;
ALTER SEQUENCE avalsoft.empresa_seq
    OWNER TO postgres; 

CREATE TABLE avalsoft.empresa (
  id integer NOT NULL DEFAULT nextval('avalsoft.empresa_seq'::regclass),
  tipo_identificacion VARCHAR(10) NULL,
  identificacion VARCHAR(20) NULL,
  nombre VARCHAR(50) NULL,
  description VARCHAR(100) NULL,
  estado boolean not NULL,
  ciudad_id integer NOT NULL,
  CONSTRAINT pk_empresa PRIMARY KEY (id),
  CONSTRAINT fk_empresa_ciudad FOREIGN KEY (ciudad_id)
        REFERENCES avalsoft.ciudad (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE);

ALTER TABLE avalsoft.empresa
    OWNER to postgres;

-- -----------------------------------------------------
-- Table avalsoft.usuario
-- -----------------------------------------------------
DROP TABLE avalsoft.usuario ;

DROP SEQUENCE avalsoft.usuario_seq;
CREATE SEQUENCE avalsoft.usuario_seq;
ALTER SEQUENCE avalsoft.usuario_seq
    OWNER TO postgres; 

CREATE TABLE avalsoft.usuario (
  id integer NOT NULL DEFAULT nextval('avalsoft.usuario_seq'::regclass),
  tipo_documento VARCHAR(10) NULL,
  identificacion VARCHAR(20) NULL,
  nombre VARCHAR(50) NULL,
  correo VARCHAR(80) NULL,
  fecha_nacimiento date NULL,
  direccion VARCHAR(150) NULL,
  telefono VARCHAR(20) NULL,
  celular VARCHAR(30) NULL,
  contrasena VARCHAR(150) NULL,
  estado boolean not NULL,
  lenguaje VARCHAR(2) NULL,
  profesion VARCHAR(150) NULL,
  rol_id integer NOT NULL,
  empresa_id integer NOT NULL,
  CONSTRAINT pk_usuario PRIMARY KEY (id),
  CONSTRAINT fk_usuario_rol FOREIGN KEY (rol_id)
        REFERENCES avalsoft.rol (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
  CONSTRAINT fk_usuario_empresa FOREIGN KEY (empresa_id)
        REFERENCES avalsoft.empresa (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE);

ALTER TABLE avalsoft.usuario
    OWNER to postgres;
    
-- -----------------------------------------------------
-- Table avalsoft.pais
-- -----------------------------------------------------
DROP TABLE avalsoft.pais ;

DROP SEQUENCE avalsoft.pais_seq;
CREATE SEQUENCE avalsoft.pais_seq;
ALTER SEQUENCE avalsoft.pais_seq
    OWNER TO postgres; 
    
CREATE TABLE avalsoft.pais (
  id integer NOT NULL DEFAULT nextval('avalsoft.pais_seq'::regclass),
  nombre VARCHAR(100) NULL,
  codigo VARCHAR(10) NULL,
  estado boolean not NULL,
  CONSTRAINT pk_pais PRIMARY KEY (id));

ALTER TABLE avalsoft.pais
    OWNER to postgres;

-- -----------------------------------------------------
-- Table avalsoft.departamento
-- -----------------------------------------------------
DROP TABLE avalsoft.departamento ;

DROP SEQUENCE avalsoft.departamento_seq;
CREATE SEQUENCE avalsoft.departamento_seq;
ALTER SEQUENCE avalsoft.departamento_seq
    OWNER TO postgres; 

CREATE TABLE avalsoft.departamento (
  id integer NOT NULL DEFAULT nextval('avalsoft.departamento_seq'::regclass),
  nombre VARCHAR(100) NULL,
  codigo VARCHAR(10) NULL,
  estado boolean not NULL,
  pais_id integer NOT NULL,
  CONSTRAINT pk_departamento PRIMARY KEY (id),
  CONSTRAINT fk_departamento_pais FOREIGN KEY (pais_id)
        REFERENCES avalsoft.pais (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE);

ALTER TABLE avalsoft.departamento
    OWNER to postgres;

-- -----------------------------------------------------
-- Table avalsoft.ciudad
-- -----------------------------------------------------
DROP TABLE avalsoft.ciudad ;

DROP SEQUENCE avalsoft.ciudad_seq;
CREATE SEQUENCE avalsoft.ciudad_seq;
ALTER SEQUENCE avalsoft.ciudad_seq
    OWNER TO postgres; 

CREATE TABLE avalsoft.ciudad (
  id integer NOT NULL DEFAULT nextval('avalsoft.ciudad_seq'::regclass),
  nombre VARCHAR(100) NULL,
  codigo VARCHAR(10) NULL,
  estado boolean NULL,
  departamento_id integer NOT NULL,
  CONSTRAINT pk_ciudad PRIMARY KEY (id),
  CONSTRAINT fk_ciudad_departamento FOREIGN KEY (departamento_id)
        REFERENCES avalsoft.departamento (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE);

ALTER TABLE avalsoft.ciudad
    OWNER to postgres;
    
-- -----------------------------------------------------
-- Table avalsoft.estrato
-- -----------------------------------------------------
DROP TABLE avalsoft.estrato ;

DROP SEQUENCE avalsoft.estrato_seq;
CREATE SEQUENCE avalsoft.estrato_seq;
ALTER SEQUENCE avalsoft.estrato_seq
    OWNER TO postgres; 

CREATE TABLE avalsoft.estrato (
  id integer NOT NULL DEFAULT nextval('avalsoft.estrato_seq'::regclass),
  nombre VARCHAR(50) NULL,
  valor numeric not NULL,
  porcentaje numeric not NULL,
  empresa_id integer NOT NULL,
  CONSTRAINT pk_estrato PRIMARY KEY (id),
  CONSTRAINT fk_estrato_empresa FOREIGN KEY (empresa_id)
        REFERENCES avalsoft.empresa (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE);

ALTER TABLE avalsoft.estrato
    OWNER to postgres;

-- -----------------------------------------------------
-- Table avalsoft.propiedad
-- -----------------------------------------------------
DROP TABLE avalsoft.propiedad ;

DROP SEQUENCE avalsoft.propiedad_seq;
CREATE SEQUENCE avalsoft.propiedad_seq;
ALTER SEQUENCE avalsoft.propiedad_seq
    OWNER TO postgres; 

CREATE TABLE avalsoft.propiedad (
  id integer NOT NULL DEFAULT nextval('avalsoft.propiedad_seq'::regclass),
  tipo VARCHAR(25) NULL,
  registro VARCHAR(100) NULL,
  direccion VARCHAR(150) NULL,
  unidad_medida VARCHAR(20) NULL,
  valor_medida numeric NULL,
  ciudad_id integer NOT NULL,
  usuario_id integer NOT NULL,
  estrato_id integer NOT NULL,
  CONSTRAINT pk_propiedad PRIMARY KEY (id),
  CONSTRAINT fk_propiedad_ciudad FOREIGN KEY (ciudad_id)
        REFERENCES avalsoft.ciudad (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
  CONSTRAINT fk_propiedad_usuario FOREIGN KEY (usuario_id)
        REFERENCES avalsoft.usuario (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,     
  CONSTRAINT fk_propiedad_estrato FOREIGN KEY (estrato_id)
        REFERENCES avalsoft.estrato (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE);

ALTER TABLE avalsoft.propiedad
    OWNER to postgres;
    
-- -----------------------------------------------------
-- Table avalsoft.cotizacion
-- -----------------------------------------------------
DROP TABLE avalsoft.cotizacion ;

DROP SEQUENCE avalsoft.cotizacion_seq;
CREATE SEQUENCE avalsoft.cotizacion_seq;
ALTER SEQUENCE avalsoft.cotizacion_seq
    OWNER TO postgres; 

CREATE TABLE avalsoft.cotizacion (
  id integer NOT NULL DEFAULT nextval('avalsoft.cotizacion_seq'::regclass),
  tipo VARCHAR(25) NULL,
  nombre VARCHAR(100) NULL,
  desde numeric NULL,
  hasta numeric NULL,
  delta numeric NULL,
  factor numeric NULL,
  base numeric NULL,
  valor numeric NULL,
  area_rango numeric NULL,
  valor_area_rango numeric NULL,
  residuo numeric NULL,
  estado boolean not NULL,
  empresa_id integer NOT NULL,
  propiedad_id integer NOT NULL,
  CONSTRAINT pk_cotizacion PRIMARY KEY (id),
  CONSTRAINT fk_cotizacion_empresa FOREIGN KEY (empresa_id)
        REFERENCES avalsoft.empresa (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
  CONSTRAINT fk_cotizacion_propiedad FOREIGN KEY (propiedad_id)
        REFERENCES avalsoft.propiedad (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE);
        
ALTER TABLE avalsoft.cotizacion
    OWNER to postgres;

-- -----------------------------------------------------
-- Table avalsoft.contacto
-- -----------------------------------------------------
DROP TABLE avalsoft.contacto ;

DROP SEQUENCE avalsoft.contacto_seq;
CREATE SEQUENCE avalsoft.contacto_seq;
ALTER SEQUENCE avalsoft.contacto_seq
    OWNER TO postgres; 

CREATE TABLE avalsoft.contacto (
  id integer NOT NULL DEFAULT nextval('avalsoft.contacto_seq'::regclass),
  usuario_id integer NOT NULL,
  CONSTRAINT pk_contacto PRIMARY KEY (id),
  CONSTRAINT fk_contacto_usuario FOREIGN KEY (usuario_id)
        REFERENCES avalsoft.usuario (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE);

ALTER TABLE avalsoft.contacto
    OWNER to postgres;
-- -----------------------------------------------------
-- Table Avalsoft.licencia
-- -----------------------------------------------------
DROP TABLE Avalsoft.licencia ;

DROP SEQUENCE avalsoft.licencia_seq;
CREATE SEQUENCE avalsoft.licencia_seq;
ALTER SEQUENCE avalsoft.licencia_seq
    OWNER TO postgres; 

CREATE TABLE avalsoft.licencia (
  id integer NOT NULL DEFAULT nextval('avalsoft.licencia_seq'::regclass),
  nombre VARCHAR(100) NULL,
  ultimo_pago date NULL,
  fecha_expiracion date NULL,
  fecha_ultima_conn date NULL,
  ciudad_id integer NOT NULL,
  empresa_id integer NOT NULL,
  CONSTRAINT pk_licencia PRIMARY KEY (id),
  CONSTRAINT fk_licencia_ciudad FOREIGN KEY (ciudad_id)
        REFERENCES avalsoft.ciudad (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
  CONSTRAINT fk_licencia_empresa FOREIGN KEY (empresa_id)
        REFERENCES avalsoft.empresa (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE);

ALTER TABLE avalsoft.licencia
    OWNER to postgres;

