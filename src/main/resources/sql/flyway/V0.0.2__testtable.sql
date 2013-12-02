create table sometest (
	
	id  INTEGER GENERATED ALWAYS AS IDENTITY(START WITH 100) PRIMARY KEY,
	sometext VARCHAR(4000)
);



insert into sometest (sometext) values 'Hans1';
insert into sometest (sometext) values 'Hans2';
insert into sometest (sometext) values 'Hans3';
insert into sometest (sometext) values 'Hans4';
insert into sometest (sometext) values 'Hans5';