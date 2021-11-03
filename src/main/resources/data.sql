create table vartotojas (id integer generated by default as identity, tel_nr varchar(255), vardas varchar(255), primary key (id));
create table veiksmai (id integer generated by default as identity, data varchar(255), vartotojo_id integer, veiksmas varchar(255), primary key (id));

INSERT INTO Vartotojas VALUES(1, '37054', 'Tomas');
INSERT INTO Vartotojas VALUES(2, '38045', 'Jonas');
INSERT INTO Vartotojas VALUES(3, '39074', 'Petras');


INSERT INTO Veiksmai VALUES(1, '01.10.21', 1,'insert');
INSERT INTO Veiksmai VALUES(2, '01.10.21', 2,'update');
INSERT INTO Veiksmai VALUES(3, '01.10.21', 3,'delete');
INSERT INTO Veiksmai VALUES(4, '01.11.21', 1,'insert');
INSERT INTO Veiksmai VALUES(5, '01.11.21', 2,'delete');
INSERT INTO Veiksmai VALUES(6, '01.12.21', 3,'update');

