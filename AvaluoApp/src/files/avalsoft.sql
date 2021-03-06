------CAMBIOS------
--avaluos
--categorias_avaluo
--cotiacion
--detalle_cotizacion
--propiedad
--usuario
--visitas
--documentos

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

-- DROP SEQUENCE avalsoft.rol_seq;
CREATE SEQUENCE avalsoft.rol_seq;
ALTER SEQUENCE avalsoft.rol_seq
    OWNER TO postgres; 

-- DROP TABLE avalsoft.rol ;
CREATE TABLE avalsoft.rol (
  id integer NOT NULL DEFAULT nextval('avalsoft.rol_seq'::regclass),
  nombre VARCHAR(50) NULL,
  descripcion VARCHAR(100) NULL,
  estado boolean not NULL,
  CONSTRAINT pk_rol PRIMARY KEY (id));

ALTER TABLE avalsoft.rol
    OWNER to postgres;
    
-- -----------------------------------------------------
-- Table avalsoft.pais
-- -----------------------------------------------------

-- DROP SEQUENCE avalsoft.pais_seq;
CREATE SEQUENCE avalsoft.pais_seq;
ALTER SEQUENCE avalsoft.pais_seq
    OWNER TO postgres; 
    
-- DROP TABLE avalsoft.pais ;
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

-- DROP SEQUENCE avalsoft.departamento_seq;
CREATE SEQUENCE avalsoft.departamento_seq;
ALTER SEQUENCE avalsoft.departamento_seq
    OWNER TO postgres;
    
-- DROP TABLE avalsoft.departamento ;
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

-- DROP SEQUENCE avalsoft.ciudad_seq;
CREATE SEQUENCE avalsoft.ciudad_seq;
ALTER SEQUENCE avalsoft.ciudad_seq
    OWNER TO postgres; 

-- DROP TABLE avalsoft.ciudad ;
CREATE TABLE avalsoft.ciudad
(
    id integer NOT NULL DEFAULT nextval('avalsoft.ciudad_seq'::regclass),
    nombre character varying(100) COLLATE pg_catalog."default",
    codigo character varying(10) COLLATE pg_catalog."default",
    estado boolean,
    departamento_id integer NOT NULL,
    CONSTRAINT pk_ciudad PRIMARY KEY (id),
    CONSTRAINT fk_ciudad_departamento FOREIGN KEY (departamento_id)
        REFERENCES avalsoft.departamento (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE avalsoft.ciudad
    OWNER to postgres;
    
-- -----------------------------------------------------
-- Table avalsoft.empresa
-- -----------------------------------------------------

-- DROP SEQUENCE avalsoft.empresa_seq;
CREATE SEQUENCE avalsoft.empresa_seq;
ALTER SEQUENCE avalsoft.empresa_seq
    OWNER TO postgres; 

-- DROP TABLE avalsoft.empresa ;
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
-- Table avalsoft.categorias_avaluo
-- -----------------------------------------------------    
CREATE SEQUENCE avalsoft.categorias_avaluo_id_seq;    
ALTER SEQUENCE avalsoft.categorias_avaluo_id_seq
    OWNER TO postgres;
    

CREATE TABLE avalsoft.categorias_avaluo
(
    id integer NOT NULL DEFAULT nextval('avalsoft.categorias_avaluo_id_seq'::regclass),
    nombre character varying(200) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT categorias_avaluo_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE avalsoft.categorias_avaluo
    OWNER to postgres;   
    
-- -----------------------------------------------------
-- Table avalsoft.usuario
-- -----------------------------------------------------

-- DROP SEQUENCE avalsoft.usuario_seq;
CREATE SEQUENCE avalsoft.usuario_seq;
ALTER SEQUENCE avalsoft.usuario_seq
    OWNER TO postgres;
    
-- DROP TABLE avalsoft.usuario ;
CREATE TABLE avalsoft.usuario
(
    id integer NOT NULL DEFAULT nextval('avalsoft.usuario_seq'::regclass),
    tipo_documento character varying(10) COLLATE pg_catalog."default",
    identificacion character varying COLLATE pg_catalog."default",
    nombre character varying(50) COLLATE pg_catalog."default",
    correo character varying(80) COLLATE pg_catalog."default",
    fecha_nacimiento date,
    direccion character varying(150) COLLATE pg_catalog."default",
    telefono character varying(20) COLLATE pg_catalog."default",
    celular character varying(30) COLLATE pg_catalog."default",
    contrasena character varying(150) COLLATE pg_catalog."default",
    estado boolean NOT NULL,
    lenguaje character varying(2) COLLATE pg_catalog."default",
    profesion character varying(150) COLLATE pg_catalog."default",
    rol_id integer NOT NULL,
    empresa_id integer NOT NULL,
    tipo_persona character varying(20) COLLATE pg_catalog."default",
    apellido1 character varying(50) COLLATE pg_catalog."default",
    apellido2 character varying(50) COLLATE pg_catalog."default",
    raa character varying COLLATE pg_catalog."default",
    categoria_id integer,
    CONSTRAINT pk_usuario PRIMARY KEY (id),
    CONSTRAINT fk_usuario_categorias_avaluo FOREIGN KEY (categoria_id)
        REFERENCES avalsoft.categorias_avaluo (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_usuario_empresa FOREIGN KEY (empresa_id)
        REFERENCES avalsoft.empresa (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_usuario_rol FOREIGN KEY (rol_id)
        REFERENCES avalsoft.rol (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE avalsoft.usuario
    OWNER to postgres;
	
-- -----------------------------------------------------
-- Table avalsoft.estrato
-- -----------------------------------------------------

-- DROP SEQUENCE avalsoft.estrato_seq;
CREATE SEQUENCE avalsoft.estrato_seq;
ALTER SEQUENCE avalsoft.estrato_seq
    OWNER TO postgres;
    
-- DROP TABLE avalsoft.estrato ;
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
-- Table: avalsoft.tablas
-- -----------------------------------------------------
-- DROP SEQUENCE avalsoft.cotizacion_seq;
CREATE SEQUENCE avalsoft.tablas_seq;
ALTER SEQUENCE avalsoft.tablas_seq
    OWNER TO postgres; 
    
-- DROP TABLE avalsoft.tablas;
CREATE TABLE avalsoft.tablas
(
    id integer NOT NULL DEFAULT nextval('avalsoft.tablas_seq'::regclass),
    tipo character varying(25) COLLATE pg_catalog."default",
    nombre character varying(100) COLLATE pg_catalog."default",
    conversion numeric,
    base numeric,
    gastos numeric,
    estado boolean NOT NULL,
    empresa_id integer NOT NULL,
    uom character varying(10) COLLATE pg_catalog."default",
    uom_alt character varying(10) COLLATE pg_catalog."default",
    dias_de_trabajo numeric(10,0),
    minimo numeric(10,0),
    CONSTRAINT pk_cotizacion PRIMARY KEY (id),
    CONSTRAINT fk_tablas_empresa FOREIGN KEY (empresa_id)
        REFERENCES avalsoft.empresa (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE avalsoft.tablas
    OWNER to postgres;

-- -----------------------------------------------------    
-- Table: avalsoft.tipo_propiedad
-- -----------------------------------------------------
-- DROP SEQUENCE avalsoft.tipo_propiedad_id_seq;
CREATE SEQUENCE avalsoft.tipo_propiedad_id_seq;
ALTER SEQUENCE avalsoft.tipo_propiedad_id_seq
    OWNER TO postgres; 

-- DROP TABLE avalsoft.tipo_propiedad;
CREATE TABLE avalsoft.tipo_propiedad(
    id integer NOT NULL DEFAULT nextval('avalsoft.tipo_propiedad_id_seq'::regclass),
    tipo_propiedad character varying(50) COLLATE pg_catalog."default",
    tipo_vivienda character varying(100) COLLATE pg_catalog."default",
    incremento double precision,
    empresa_id integer NOT NULL,
    CONSTRAINT tipo_propiedad_pkey PRIMARY KEY (id),
    CONSTRAINT fk_tipo_propiedad_empresa FOREIGN KEY (empresa_id)
        REFERENCES avalsoft.empresa (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE avalsoft.tipo_propiedad
    OWNER to postgres;    


-- -----------------------------------------------------
-- Table Avalsoft.direcciones
-- -----------------------------------------------------
CREATE SEQUENCE avalsoft.direcciones_id_seq AS INTEGER
                                   INCREMENT 1
                                   MINVALUE 1
                                   MAXVALUE 2147483647
                                   START 1
                                   CACHE 1
                                   NO CYCLE;
                                   
ALTER SEQUENCE avalsoft.direcciones_id_seq
    OWNER TO postgres;    
    
CREATE TABLE avalsoft.direcciones
(
   id                         INTEGER NOT NULL DEFAULT nextval ('avalsoft.direcciones_id_seq'::regclass),
   propiedad_id               INTEGER NOT NULL,
   tipo_via                   CHARACTER VARYING (20) NULL,
   numero_via                 INTEGER NULL,
   letra_via                  CHARACTER VARYING (20) NULL,
   nombre_via                 CHARACTER VARYING (20) NULL,
   posicion_via               CHARACTER VARYING (20) NULL,
   numero_interseccion        INTEGER NULL,
   letra_interseccion         CHARACTER VARYING (20) NULL,
   posicion_interseccion      CHARACTER VARYING (20) NULL,
   componente_interseccion    CHARACTER VARYING (20) NULL,
   tipo_ubicacion             CHARACTER VARYING (20) NULL,
   numero_ubicacion           INTEGER NULL,
   lugar_ubicacion            CHARACTER VARYING (20) NULL,
   numero_lugar               INTEGER NULL,
   apendice                   CHARACTER VARYING (500) NULL,
   aregio_id                  INTEGER NULL,
   tipo_urbanizacion          CHARACTER VARYING (20) NULL,
   descrip_urbanizacion       CHARACTER VARYING (20) NULL,
   tipo_manzana               CHARACTER VARYING (20) NULL,
   descrip_manzana            CHARACTER VARYING (20) NULL,
   tipo_prefijo               CHARACTER VARYING (20) NULL,
   tipo_componente            CHARACTER VARYING (20) NULL,
   descrip_componente         CHARACTER VARYING (20) NULL,
   CONSTRAINT direcciones_pkey PRIMARY KEY (id)
      NOT DEFERRABLE INITIALLY IMMEDIATE
)
WITH (OIDS = FALSE);    

ALTER TABLE avalsoft.direcciones
    OWNER to postgres; 

-- -----------------------------------------------------
-- Table avalsoft.propiedad
-- -----------------------------------------------------

-- DROP SEQUENCE avalsoft.propiedad_seq;
CREATE SEQUENCE avalsoft.propiedad_seq;
ALTER SEQUENCE avalsoft.propiedad_seq
    OWNER TO postgres; 
    
-- DROP TABLE avalsoft.propiedad ;   
CREATE TABLE avalsoft.propiedad
(
    id integer NOT NULL DEFAULT nextval('avalsoft.propiedad_seq'::regclass),
    tipo character varying(25) COLLATE pg_catalog."default",
    registro character varying(100) COLLATE pg_catalog."default",
    direccion character varying(150) COLLATE pg_catalog."default",
    unidad_medida character varying(20) COLLATE pg_catalog."default",
    valor_medida numeric,
    ciudad_id integer NOT NULL,
    usuario_id integer NOT NULL,
    estrato_id integer NOT NULL,
    tipo_propiedad_id integer NOT NULL,
    tablas_id integer NOT NULL,
    direccion_id integer NULL,
    CONSTRAINT pk_propiedad PRIMARY KEY (id),
    CONSTRAINT fk_propiedad_ciudad FOREIGN KEY (ciudad_id)
        REFERENCES avalsoft.ciudad (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT fk_propiedad_estrato FOREIGN KEY (estrato_id)
        REFERENCES avalsoft.estrato (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_propiedad_tablas FOREIGN KEY (tablas_id)
        REFERENCES avalsoft.tablas (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_propiedad_direccion FOREIGN KEY (direccion_id)
      REFERENCES avalsoft.direcciones (id)
         ON DELETE NO ACTION
         ON UPDATE NO ACTION,    
    CONSTRAINT fk_propiedad_tipo_propiedad FOREIGN KEY (tipo_propiedad_id)
        REFERENCES avalsoft.tipo_propiedad (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_propiedad_usuario FOREIGN KEY (usuario_id)
        REFERENCES avalsoft.usuario (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE avalsoft.propiedad
    OWNER to postgres;
 
    
    
-- -----------------------------------------------------
-- Table avalsoft.avaluos
-- -----------------------------------------------------    
CREATE SEQUENCE avalsoft.avaluos_avaluos_seq;

ALTER SEQUENCE avalsoft.avaluos_avaluos_seq
    OWNER TO postgres;
    
CREATE TABLE avalsoft.avaluos
(
    id integer NOT NULL DEFAULT nextval('avalsoft.avaluos_avaluos_seq'::regclass),
    usuario_id integer NOT NULL,
    empresa_id integer NOT NULL,
    propiedad_id integer NOT NULL,
    CONSTRAINT avaluos_pkey PRIMARY KEY (id),
    CONSTRAINT fk_avaluos_empresa_id FOREIGN KEY (empresa_id)
        REFERENCES avalsoft.empresa (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_avaluos_propiedad_id FOREIGN KEY (propiedad_id)
        REFERENCES avalsoft.propiedad (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_avaluos_usuario_id FOREIGN KEY (usuario_id)
        REFERENCES avalsoft.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE avalsoft.avaluos
    OWNER to postgres;       
    
-- -----------------------------------------------------
-- Table avalsoft.cotizacion
-- -----------------------------------------------------
-- DROP SEQUENCE avalsoft.cotizacion_id_seq;
CREATE SEQUENCE avalsoft.cotizacion_id_seq;
ALTER SEQUENCE avalsoft.cotizacion_id_seq
    OWNER TO postgres; 

-- DROP TABLE avalsoft.cotizacion;
CREATE TABLE avalsoft.cotizacion
(
    id integer NOT NULL DEFAULT nextval('avalsoft.cotizacion_id_seq'::regclass),
    empresa_id integer NOT NULL,
    cliente_id integer NOT NULL,
    valor numeric,
    remitente_id integer NOT NULL,
    ciudad_id integer NOT NULL,
    fecha date NOT NULL,
    motivo character varying COLLATE pg_catalog."default",
    estado character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT cotizacion_pkey PRIMARY KEY (id),
    CONSTRAINT fk_cotizacion_ciudad FOREIGN KEY (ciudad_id)
        REFERENCES avalsoft.ciudad (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_cotizacion_cliente FOREIGN KEY (cliente_id)
        REFERENCES avalsoft.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_cotizacion_empresa FOREIGN KEY (empresa_id)
        REFERENCES avalsoft.empresa (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_cotizacion_remitente FOREIGN KEY (remitente_id)
        REFERENCES avalsoft.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE avalsoft.cotizacion
    OWNER to postgres;

    
-- -----------------------------------------------------    
-- Table: avalsoft.detalle_cotizacion
-- -----------------------------------------------------
-- DROP SEQUENCE avalsoft.detalle_cotizacion_id_seq;
CREATE SEQUENCE avalsoft.detalle_cotizacion_id_seq;
ALTER SEQUENCE avalsoft.detalle_cotizacion_id_seq
    OWNER TO postgres; 
    
-- DROP TABLE avalsoft.detalle_cotizacion;
CREATE TABLE avalsoft.detalle_cotizacion
(
    id integer NOT NULL DEFAULT nextval('avalsoft.detalle_cotizacion_id_seq'::regclass),
    cotizacion_id integer NOT NULL,
    propiedad_id integer NOT NULL,
    valor numeric,
   CONSTRAINT detalle_cotizacion_pkey PRIMARY KEY (id),
    CONSTRAINT fk_detalle_cotizacion_cotizacion FOREIGN KEY (cotizacion_id)
        REFERENCES avalsoft.cotizacion (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_detalle_cotizacion_propiedad FOREIGN KEY (propiedad_id)
        REFERENCES avalsoft.propiedad (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE avalsoft.detalle_cotizacion
    OWNER to postgres;

    
-- -----------------------------------------------------    
-- Table: avalsoft.detalle_tabla
-- -----------------------------------------------------
-- DROP SEQUENCE avalsoft.detalle_tabla_id_seq;
CREATE SEQUENCE avalsoft.detalle_tabla_id_seq;
ALTER SEQUENCE avalsoft.detalle_tabla_id_seq
    OWNER TO postgres;

CREATE TABLE avalsoft.detalle_tabla
(
    id integer NOT NULL DEFAULT nextval('avalsoft.detalle_tabla_id_seq'::regclass),
    tabla_id integer,
    desde numeric(10,0),
    hasta numeric(10,0),
    porcentaje_aplicar double precision,
    CONSTRAINT detalle_tabla_pkey PRIMARY KEY (id),
    CONSTRAINT fk_detalle_tabla_tablas FOREIGN KEY (tabla_id)
        REFERENCES avalsoft.tablas (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE avalsoft.detalle_tabla
    OWNER to postgres;
	
    
-- -----------------------------------------------------
-- Table avalsoft.contacto
-- -----------------------------------------------------
--DROP SEQUENCE avalsoft.contacto_seq;
CREATE SEQUENCE avalsoft.contacto_seq;
ALTER SEQUENCE avalsoft.contacto_seq
    OWNER TO postgres; 
    
-- DROP TABLE avalsoft.contacto ;
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

-- DROP SEQUENCE avalsoft.licencia_seq;
CREATE SEQUENCE avalsoft.licencia_seq;
ALTER SEQUENCE avalsoft.licencia_seq
    OWNER TO postgres; 

-- DROP TABLE Avalsoft.licencia ;
CREATE TABLE avalsoft.licencia (
  id integer NOT NULL DEFAULT nextval('avalsoft.licencia_seq'::regclass),
  nombre VARCHAR(100) NULL,
  ultimo_pago date NULL,
  fecha_expiracion date NULL,
  fecha_ultima_conn date NULL,
  ciudad_id integer NOT NULL,
  usuario_id integer NOT NULL,
  CONSTRAINT pk_licencia PRIMARY KEY (id),
  CONSTRAINT fk_licencia_ciudad FOREIGN KEY (ciudad_id)
        REFERENCES avalsoft.ciudad (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
  CONSTRAINT fk_licencia_usuario FOREIGN KEY (usuario_id)
        REFERENCES avalsoft.usuario (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE);

ALTER TABLE avalsoft.licencia
    OWNER to postgres;
    
-- -----------------------------------------------------
-- Table Avalsoft.reporte
-- -----------------------------------------------------

-- DROP SEQUENCE avalsoft.reporte_seq;
CREATE SEQUENCE avalsoft.reporte_seq;
ALTER SEQUENCE avalsoft.reporte_seq
    OWNER TO postgres;    
    
-- DROP TABLE Avalsoft.reporte ;
CREATE TABLE avalsoft.reporte (
  id integer NOT NULL DEFAULT nextval('avalsoft.reporte_seq'::regclass),
  codigo VARCHAR(150) not NULL,
  contenido_id varchar(100) not null,
  visible boolean not NULL,
  empresa_id integer NOT NULL,
  CONSTRAINT pk_reporte PRIMARY KEY (id),
  CONSTRAINT fk_reporte_empresa FOREIGN KEY (empresa_id)
        REFERENCES avalsoft.empresa (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE);

ALTER TABLE avalsoft.reporte
    OWNER to postgres; 

    
-- Table: avalsoft.visitas

-- DROP TABLE avalsoft.visitas;

CREATE SEQUENCE avalsoft.visitas_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE avalsoft.visitas_id_seq
    OWNER TO postgres;    
    
CREATE TABLE avalsoft.visitas
(
    id integer NOT NULL DEFAULT nextval('avalsoft.visitas_id_seq'::regclass),
    avaluos_id integer NOT NULL,
    fecha_programacion date,
    avaluador_id integer,
    CONSTRAINT visitas_pkey PRIMARY KEY (id),
    CONSTRAINT visitas_avaluos_fk FOREIGN KEY (avaluos_id)
        REFERENCES avalsoft.avaluos (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT visitas_usuarios_fk FOREIGN KEY (avaluador_id)
        REFERENCES avalsoft.usuario (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE avalsoft.visitas
    OWNER to postgres;
    
    
-- Table: avalsoft.documentos

-- DROP TABLE avalsoft.documentos;

CREATE SEQUENCE avalsoft.documentos_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 2147483647
    CACHE 1;

ALTER SEQUENCE avalsoft.documentos_id_seq
    OWNER TO postgres;    
    
CREATE TABLE avalsoft.documentos
(
    id integer NOT NULL DEFAULT nextval('avalsoft.documentos_id_seq'::regclass),
    visitas_id integer NOT NULL,
    latitud character varying(30) COLLATE pg_catalog."default",
    longitud character varying(30) COLLATE pg_catalog."default",
    numero_escritura numeric(30,0),
    fecha_registro date,
    numero_notaria numeric(6,0),
    circuito_notarial integer,
    escritura_objeto character varying COLLATE pg_catalog."default",
    cedula_catastral numeric(30,0),
    entrega_planos character varying(2) COLLATE pg_catalog."default",
    licencia_construcc character varying(60) COLLATE pg_catalog."default",
    afectaciones character varying(4000) COLLATE pg_catalog."default",
    vetustez numeric(4,0),
    avaluo_lote numeric(20,0),
    avaluo_construccion numeric(20,0),
    registro_catastral numeric(20,0),
    regimen_ph character varying(2) COLLATE pg_catalog."default",
    escritura_publica character varying COLLATE pg_catalog."default",
    fecha_catastral date,
    "certificado-libertad" character varying(2) COLLATE pg_catalog."default",
    licencia_construccion character varying(2) COLLATE pg_catalog."default",
    certificado_estratificacion character varying(2) COLLATE pg_catalog."default",
    plano character varying(2) COLLATE pg_catalog."default",
    reglamento_ph character varying(2) COLLATE pg_catalog."default",
    pago_zonas_com character varying(2) COLLATE pg_catalog."default",
    CONSTRAINT documentos_pkey PRIMARY KEY (id),
    CONSTRAINT documentos_visitas_fk FOREIGN KEY (visitas_id)
        REFERENCES avalsoft.visitas (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE avalsoft.documentos
    OWNER to postgres;
    
    