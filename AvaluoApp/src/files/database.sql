-- Database: avalsoft
-- DROP DATABASE avalsoft;
CREATE DATABASE avalsoft
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Colombia.1252'
    LC_CTYPE = 'Spanish_Colombia.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

-- SCHEMA: avalsoft
-- DROP SCHEMA avalsoft ;
CREATE SCHEMA avalsoft
    AUTHORIZATION postgres;
    
-----------------------------------------------------
--------------- SECUENCIAS --------------------------
-----------------------------------------------------

-- SEQUENCE: avalsoft.customer_id_seq
-- DROP SEQUENCE avalsoft.customer_id_seq;
CREATE SEQUENCE avalsoft.customer_id_seq;

ALTER SEQUENCE avalsoft.customer_id_seq
    OWNER TO postgres; 
	
-- SEQUENCE: avalsoft.detail_table_id_seq
-- DROP SEQUENCE avalsoft.detail_table_id_seq;
CREATE SEQUENCE avalsoft.detail_table_id_seq;

ALTER SEQUENCE avalsoft.detail_table_id_seq
    OWNER TO postgres;
	
-- SEQUENCE: avalsoft.market_categories_id_seq
-- DROP SEQUENCE avalsoft.market_categories_id_seq;
CREATE SEQUENCE avalsoft.market_categories_id_seq;

ALTER SEQUENCE avalsoft.market_categories_id_seq
    OWNER TO postgres;
	
-- SEQUENCE: avalsoft.profile_account_id_seq
-- DROP SEQUENCE avalsoft.profile_account_id_seq;
CREATE SEQUENCE avalsoft.profile_account_id_seq;

ALTER SEQUENCE avalsoft.profile_account_id_seq
    OWNER TO postgres;	
	
-- SEQUENCE: avalsoft.properties_id_seq
-- DROP SEQUENCE avalsoft.properties_id_seq;
CREATE SEQUENCE avalsoft.properties_id_seq;

ALTER SEQUENCE avalsoft.properties_id_seq
    OWNER TO postgres;	
	
-- SEQUENCE: avalsoft.registry_id_seq
-- DROP SEQUENCE avalsoft.registry_id_seq;
CREATE SEQUENCE avalsoft.registry_id_seq;

ALTER SEQUENCE avalsoft.registry_id_seq
    OWNER TO postgres;	
	
-- SEQUENCE: avalsoft.services_id_seq
-- DROP SEQUENCE avalsoft.services_id_seq;
CREATE SEQUENCE avalsoft.services_id_seq;

ALTER SEQUENCE avalsoft.services_id_seq
    OWNER TO postgres;	
	
-- SEQUENCE: avalsoft.software_account_id_seq
-- DROP SEQUENCE avalsoft.software_account_id_seq;
CREATE SEQUENCE avalsoft.software_account_id_seq;

ALTER SEQUENCE avalsoft.software_account_id_seq
    OWNER TO postgres;	
	
-- SEQUENCE: avalsoft.tables_id_seq
-- DROP SEQUENCE avalsoft.tables_id_seq;
CREATE SEQUENCE avalsoft.tables_id_seq;

ALTER SEQUENCE avalsoft.tables_id_seq
    OWNER TO postgres;
	
-- SEQUENCE: avalsoft.property_type_id_seq
-- DROP SEQUENCE avalsoft.property_type_id_seq;
CREATE SEQUENCE avalsoft.property_type_id_seq;

ALTER SEQUENCE avalsoft.property_type_id_seq
    OWNER TO postgres;
    
-----------------------------------------------------
--------------- FIN SECUENCIAS ----------------------
-----------------------------------------------------

-----------------------------------------------------
--------------------- TABLAS ------------------------
-----------------------------------------------------
    
-- Table: avalsoft.profile_account
-- DROP TABLE avalsoft.profile_account;
CREATE TABLE avalsoft.profile_account(
    id integer NOT NULL DEFAULT nextval('avalsoft.profile_account_id_seq'::regclass),
    user_name character varying(200) COLLATE pg_catalog."default",
    id_cart character varying(50) COLLATE pg_catalog."default",
    profession character varying(200) COLLATE pg_catalog."default",
    id_prof character varying(50) COLLATE pg_catalog."default",
    "profession_II" character varying(200) COLLATE pg_catalog."default",
    "id_prof_II" character varying(50) COLLATE pg_catalog."default",
    expiration_date date,
    company character varying(200) COLLATE pg_catalog."default",
    id_company character varying(50) COLLATE pg_catalog."default",
    email character varying(200) COLLATE pg_catalog."default",
    contact character varying(200) COLLATE pg_catalog."default",
    CONSTRAINT profile_account_pkey PRIMARY KEY (id)
)
WITH (OIDS = FALSE)
TABLESPACE pg_default;

ALTER TABLE avalsoft.profile_account
    OWNER to postgres;
	
-- Table: avalsoft.properties
-- DROP TABLE avalsoft.properties;
CREATE TABLE avalsoft.properties(
    id integer NOT NULL DEFAULT nextval('avalsoft.properties_id_seq'::regclass),
    property_type numeric(20,0),
    zone character varying(50) COLLATE pg_catalog."default",
    real_estate_registration character varying(100) COLLATE pg_catalog."default",
    stratum numeric(20,0),
    department character varying(200) COLLATE pg_catalog."default",
    municipality character varying(200) COLLATE pg_catalog."default",
    CONSTRAINT properties_pkey PRIMARY KEY (id)
)
WITH ( OIDS = FALSE)
TABLESPACE pg_default;

ALTER TABLE avalsoft.properties
    OWNER to postgres;

-- Table: avalsoft.property_type
-- DROP TABLE avalsoft.property_type;
CREATE TABLE avalsoft.property_type(
    id integer NOT NULL DEFAULT nextval('avalsoft.property_type_id_seq'::regclass),
    name_type character varying(200) COLLATE pg_catalog."default",
    tax double precision,
    zone character varying(20) COLLATE pg_catalog."default",
    id_users integer NOT NULL,
    CONSTRAINT property_type_pkey PRIMARY KEY (id),
    CONSTRAINT "FK_property_type_USERS" FOREIGN KEY (id_users)
        REFERENCES avalsoft.users (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (OIDS = FALSE)
TABLESPACE pg_default;

ALTER TABLE avalsoft.property_type
    OWNER to postgres;	
    
-- Table: avalsoft.market_categories
-- DROP TABLE avalsoft.market_categories;
CREATE TABLE avalsoft.market_categories(
    id integer NOT NULL DEFAULT nextval('avalsoft.market_categories_id_seq'::regclass),
    name character varying(50) COLLATE pg_catalog."default",
    valor double precision,
    minimum double precision,
	id_users integer NOT NULL,
    CONSTRAINT market_categories_pkey PRIMARY KEY (id),
	CONSTRAINT "FK_property_type_USERS" FOREIGN KEY (id_users)
        REFERENCES avalsoft.users (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH ( OIDS = FALSE)
TABLESPACE pg_default;

ALTER TABLE avalsoft.market_categories
    OWNER to postgres;
	
-- Table: avalsoft.registry
-- DROP TABLE avalsoft.registry;
CREATE TABLE avalsoft.registry(
    id integer NOT NULL DEFAULT nextval('avalsoft.registry_id_seq'::regclass),
    id_client character(20) COLLATE pg_catalog."default",
    name character varying(300) COLLATE pg_catalog."default",
    address character varying(200) COLLATE pg_catalog."default",
    phone character varying(20) COLLATE pg_catalog."default",
    name_contact character varying(300) COLLATE pg_catalog."default",
    phone_contact character varying(20) COLLATE pg_catalog."default",
    email_contact character varying(200) COLLATE pg_catalog."default",
    CONSTRAINT registry_pkey PRIMARY KEY (id)
)
WITH ( OIDS = FALSE)
TABLESPACE pg_default;

ALTER TABLE avalsoft.registry
    OWNER to postgres;	
	
	
-- Table: avalsoft.rol
-- DROP TABLE avalsoft.rol;
CREATE TABLE avalsoft.rol(
    id integer NOT NULL,
    name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    description character varying(200) COLLATE pg_catalog."default",
    state boolean NOT NULL,
    CONSTRAINT rol_pkey PRIMARY KEY (id)
)
WITH (OIDS = FALSE)
TABLESPACE pg_default;

ALTER TABLE avalsoft.rol
    OWNER to postgres;	
	
-- Table: avalsoft.services
-- DROP TABLE avalsoft.services;
CREATE TABLE avalsoft.services(
    id integer NOT NULL DEFAULT nextval('avalsoft.services_id_seq'::regclass),
    name character varying(2000) COLLATE pg_catalog."default"
)
WITH ( OIDS = FALSE)
TABLESPACE pg_default;

ALTER TABLE avalsoft.services
    OWNER to postgres;	

	
-- Table: avalsoft.software_account
-- DROP TABLE avalsoft.software_account;
CREATE TABLE avalsoft.software_account(
    id integer NOT NULL DEFAULT nextval('avalsoft.software_account_id_seq'::regclass),
    download numeric(15,0),
    license character varying(50) COLLATE pg_catalog."default",
    version character varying(50) COLLATE pg_catalog."default",
    country character varying(200) COLLATE pg_catalog."default",
    state character varying(200) COLLATE pg_catalog."default",
    city character varying(200) COLLATE pg_catalog."default",
    money character varying(10) COLLATE pg_catalog."default",
    language character varying(100) COLLATE pg_catalog."default",
    expiration_date date,
    last_date_conection date,
    CONSTRAINT software_account_pkey PRIMARY KEY (id)
)
WITH (OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE avalsoft.software_account
    OWNER to postgres;	
	
-- Table: avalsoft.tables
-- DROP TABLE avalsoft.tables;
CREATE TABLE avalsoft.tables(
    id integer NOT NULL DEFAULT nextval('avalsoft.tables_id_seq'::regclass),
    table_type character varying(200) COLLATE pg_catalog."default",
    name character varying(200) COLLATE pg_catalog."default",
    id_services numeric(20,0),
    uom character varying(50) COLLATE pg_catalog."default",
    uom_alt character varying(50) COLLATE pg_catalog."default",
    conver numeric(50,0),
    base numeric(50,0),
    expenses double precision,
    day_of_job numeric(5,0),
    minimum numeric(20,0),
    law character varying(200) COLLATE pg_catalog."default",
    entity character varying(200) COLLATE pg_catalog."default",
    note character varying(200) COLLATE pg_catalog."default",
    state boolean,
    year numeric(5,0),
    CONSTRAINT tables_pkey PRIMARY KEY (id)
)
WITH ( OIDS = FALSE)
TABLESPACE pg_default;

ALTER TABLE avalsoft.tables
    OWNER to postgres;	
	
-- Table: avalsoft.users
-- DROP TABLE avalsoft.users;
CREATE TABLE avalsoft.users(
    id integer NOT NULL,
    name character varying(150) COLLATE pg_catalog."default" NOT NULL,
    email character varying(100) COLLATE pg_catalog."default" NOT NULL,
    address character varying(150) COLLATE pg_catalog."default",
    phone character varying(20) COLLATE pg_catalog."default",
    celphone character varying(20) COLLATE pg_catalog."default",
    password character varying(200) COLLATE pg_catalog."default" NOT NULL,
    state boolean NOT NULL,
    id_rol integer NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT "FK_USERS_ROL" FOREIGN KEY (id_rol)
        REFERENCES avalsoft.rol (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH (OIDS = FALSE)
TABLESPACE pg_default;

ALTER TABLE avalsoft.users
    OWNER to postgres;	
	
-- Table: avalsoft.customer
-- DROP TABLE avalsoft.customer;
CREATE TABLE avalsoft.customer(
    id integer NOT NULL DEFAULT nextval('avalsoft.customer_id_seq'::regclass),
    customer_identification character varying(20) COLLATE pg_catalog."default" NOT NULL,
    name character varying(200) COLLATE pg_catalog."default",
    first_last_name character varying(200) COLLATE pg_catalog."default",
    second_last_name character varying(200) COLLATE pg_catalog."default",
    phone character varying(20) COLLATE pg_catalog."default",
    address character varying(300) COLLATE pg_catalog."default",
    email character varying(300) COLLATE pg_catalog."default",
    person_type character varying(20)[] COLLATE pg_catalog."default",
    CONSTRAINT customer_pkey PRIMARY KEY (customer_identification)
)
WITH ( OIDS = FALSE)
TABLESPACE pg_default;

ALTER TABLE avalsoft.customer
    OWNER to postgres;

-- Table: avalsoft.detail_table
-- DROP TABLE avalsoft.detail_table;
CREATE TABLE avalsoft.detail_table(
    id integer NOT NULL DEFAULT nextval('avalsoft.detail_table_id_seq'::regclass),
    id_table integer,
    low numeric(10,0),
    up numeric(10,0),
    apply double precision,
    CONSTRAINT detail_table_pkey PRIMARY KEY (id),
    CONSTRAINT "FK_TABLES" FOREIGN KEY (id_table)
        REFERENCES avalsoft.tables (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)
WITH ( OIDS = FALSE)
TABLESPACE pg_default;

ALTER TABLE avalsoft.detail_table
    OWNER to postgres;

-----------------------------------------------------
------------------- FIN TABLAS ----------------------
-----------------------------------------------------

-----------------------------------------------------
--------------------- DATOS -------------------------
-----------------------------------------------------

-- TABLA ROL
INSERT INTO avalsoft.rol(id, name, description, state)
	VALUES (1, 'Administrador', 'Administrador del sistema', true),
	(2, 'Empleado', 'Empleado avaluador', true),
	(3, 'Cliente', 'Cliente', true);

-- TABLA USERS
INSERT INTO avalsoft.users(id, name, email, address, phone, celphone, password, state, id_rol)
	VALUES (1, 'Jarrison Garcia', 'aaa', 'Calle falsa 123', '3214534', '3112345464', 'aaa', TRUE, 1),
	(2, 'Hernan Ossa', 'bbb', 'Calle falsa 321', '5656765', '34554565', 'bbb', TRUE, 1),
	(3, 'Cliente 1', 'ccc', 'Calle falsa 456', '3243344', '31245645', 'ccc', TRUE, 1);
	
-- TABLA PROPERTY_TYPE
INSERT INTO avalsoft.property_type(id, name_type, tax, zone, id_users)
	VALUES (1,	'OFICINA',	'10',	'URBANO',2),
			(2,	'VIP',	'0',	'URBANO',2),
			(3,	'LOCAL-EXT',	'15',	'URBANO',2),
			(4,	'LOCAL-CC',	'10',	'URBANO',2),
			(5,	'AREA-COMUN',	'25'	'URBANO',2),
			(6,	'CANON-ARRENDAMIENTO',	'15'	'URBANO',2),
			(7,	'APARTAESTUDIO',	'10'	'URBANO',2),
			(8,	'APARTAMENTO',	'10'	'URBANO',2),
			(9,	'CASA',	'10'	'URBANO',2),
			(10,	'APARTAOFICINA',	'12',	'URBANO',2),
			(11,	'LOTE-URBANO',	'15',	'URBANO',2),
			(12,	'CASA-LOTE',	'15',	'URBANO',2),
			(13,	'BODEGA.100',	'15',	'URBANO',2),
			(14,	'TERRAZA',	'10',	'URBANO',2),
			(15,	'VIS',	'0'	'URBANO',2),
			(16,	'BODEGA-1000',	'30',	'URBANO',2),
			(17,	'FINCA-PRODUCTIVA',	'25',	'RURAL',2),
			(18,	'FINCA DE RECREO',	'15',	'RURAL',2),
			(19,	'FINCA CAMPESINA',	'0',	'RURAL',2),
			(20,	'LOTE-RURAL',	'0',	'RURAL',2);
			
			
-----------------------------------------------------
--------------------- FIN DATOS ---------------------
-----------------------------------------------------