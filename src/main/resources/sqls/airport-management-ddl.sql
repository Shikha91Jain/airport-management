-- Create COUNTRY table

create table airport_data.COUNTRY
(
	OBJECT_ID NUMERIC(10,0) NOT NULL,
	COUNTRY_CODE VARCHAR(5) NOT NULL,
	NAME VARCHAR(100) NOT NULL,
	CONTINENT VARCHAR(5) NOT NULL,
	WIKIPEDIA_LINK VARCHAR(1000),
	KEYWORDS VARCHAR(1000),
	CONSTRAINT COUNTRY_PK PRIMARY KEY (OBJECT_ID),
	CONSTRAINT COUNTRY_CODE_UNIQUE_IDX UNIQUE (COUNTRY_CODE)
);

-- Create AIRPORT table

create table airport_data.AIRPORT
(
	OBJECT_ID NUMERIC(10,0) NOT NULL,
	AIRPORT_IDENTIFIER VARCHAR(10) NOT NULL,
	TYPE VARCHAR(50) NOT NULL,
	NAME VARCHAR(256) NOT NULL,
	LATITUDE VARCHAR(50) NOT NULL,
	LONGITUDE VARCHAR(50) NOT NULL,
	ELEVATION VARCHAR(25),
	FK2COUNTRY NUMERIC(10,0) NOT NULL,
	ISO_REGION VARCHAR(10) NOT NULL,
	MUNICIPALITY VARCHAR(100),
	SCHEDULED_SERVICE VARCHAR(5) NOT NULL,
	GPS_CODE VARCHAR(10),
	IATA_CODE VARCHAR(5),
	LOCAL_CODE VARCHAR(10),
	HOME_LINK VARCHAR(1000),
	WIKIPEDIA_LINK VARCHAR(1000),
	KEYWORDS VARCHAR(1000),
	CONSTRAINT AIRPORT_PK PRIMARY KEY (OBJECT_ID),
	CONSTRAINT COUNTRY_FK FOREIGN KEY(FK2COUNTRY) REFERENCES airport_data.COUNTRY(OBJECT_ID)
);

-- Create RUNWAY table

create table airport_data.RUNWAY
(
	OBJECT_ID NUMERIC(10,0) NOT NULL,
	FK2AIRPORT NUMERIC(10,0) NOT NULL,
	LENGTH NUMERIC(8,0),
	WIDTH NUMERIC(8,0),
	SURFACE VARCHAR(256),
	LIGHTED BOOLEAN,
	CLOSED BOOLEAN,
	LENGTH_IDENTIFIER VARCHAR(25),
	LENGTH_LATITUDE VARCHAR(100),
	LENGTH_LONGITUDE VARCHAR(100),
	LENGTH_ELEVATION VARCHAR(50),
	LENGTH_HEADING VARCHAR(100),
	LENGTH_DISPLACEMENT_THRESHOLD VARCHAR(25),
	HEIGHT_IDENTIFIER VARCHAR(25),
	HEIGHT_LATITUDE VARCHAR(100),
	HEIGHT_LONGITUDE VARCHAR(100),
	HEIGHT_ELEVATION VARCHAR(50),
	HEIGHT_HEADING VARCHAR(100),
	HEIGHT_DISPLACEMENT_THRESHOLD VARCHAR(25),
	CONSTRAINT RUNWAY_PK PRIMARY KEY (OBJECT_ID),
	CONSTRAINT AIRPORT_FK FOREIGN KEY(FK2AIRPORT) REFERENCES airport_data.AIRPORT(OBJECT_ID)
);

GRANT ALL ON ALL TABLES IN SCHEMA airport_data TO airport_user ;