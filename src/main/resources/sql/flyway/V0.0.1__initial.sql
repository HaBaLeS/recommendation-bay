create table recomendation(
	id INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 100) PRIMARY KEY,
	uri VARCHAR(4000),
	cnt integer,
	views integer
);

create table tag(
	id INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 100) PRIMARY KEY,
	name VARCHAR(4000),
	cnt integer,
	tagtype integer
);

create table tagtorec(
	tagid integer,
	recid integer,
	cnt integer
);