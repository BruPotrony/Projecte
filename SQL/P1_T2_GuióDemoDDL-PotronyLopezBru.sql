INSERT INTO TEMPORADA (anys) VALUES (2024);





INSERT INTO Usuari (login, nom, password) VALUES ('bruuPot', 'Bru', '5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8');
INSERT INTO Usuari (login, nom, password) VALUES ('Ronix888', 'Roc', 'b6d81b360a5672d80c27430f39153e2c4a01521b');
INSERT INTO Usuari (login, nom, password) VALUES ('Joe_J77', 'Jonathan', '7c4a8d09ca3762af61e59520943dc26494f8941b');
INSERT INTO Usuari (login, nom, password) VALUES ('Joker', 'David', '2d25ed0bf0308ecb9c47b8d381665566f988ecb6');




INSERT INTO CATEGORIA (nom, edat_min, edat_max) VALUES ('Benjami', 7, 8);
INSERT INTO CATEGORIA (nom, edat_min, edat_max) VALUES ('Alevi', 9, 10);
INSERT INTO CATEGORIA (nom, edat_min, edat_max) VALUES ('Infantil', 11, 13);
INSERT INTO CATEGORIA (nom, edat_min, edat_max) VALUES ('Cadet', 14, 15);
INSERT INTO CATEGORIA (nom, edat_min, edat_max) VALUES ('Juvenil', 16, 17);
INSERT INTO CATEGORIA (nom, edat_min, edat_max) VALUES ('Senior', 18, 149);




INSERT INTO EQUIP (id_categoria, any_temporada, nom, tipus) VALUES (1, 2024, 'Benjamí Mixt', 'M');

INSERT INTO EQUIP (id_categoria, any_temporada, nom, tipus) VALUES (2, 2024, 'Aleví Femení', 'D');

INSERT INTO EQUIP (id_categoria, any_temporada, nom, tipus) VALUES (3, 2024, 'Infantil Femení', 'D');

INSERT INTO EQUIP (id_categoria, any_temporada, nom, tipus) VALUES (4, 2024, 'Cadet Masculí A', 'H');

INSERT INTO EQUIP (id_categoria, any_temporada, nom, tipus) VALUES (5, 2024, 'Juvenil A', 'H');

INSERT INTO EQUIP (id_categoria, any_temporada, nom, tipus) VALUES (6, 2024, 'Senior Femení', 'D');



-- Jugadors Benjamí Mixt
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('David', 'Moreno', 'H', 'C:\Users\isard\Pictures\noi.jpg', TO_DATE('2016-01-01', 'YYYY-MM-DD'), 'Carrer H, 8', 2025, 'ES6621000418401231', '23456789B');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Ana', 'Sánchez', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2017-01-01', 'YYYY-MM-DD'), 'Carrer I, 3', 2025, 'ES5521000418401232', '33456789A');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Carlos', 'López', 'H', 'C:\Users\isard\Pictures\noi.jpg', TO_DATE('2016-01-01', 'YYYY-MM-DD'), 'Carrer J, 12', 2025, 'ES4421000418401233', '43456789C');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Laura', 'Martínez', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2017-01-01', 'YYYY-MM-DD'), 'Carrer K, 7', 2025, 'ES3321000418401234', '53456789D');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Jorge', 'Pérez', 'H', 'C:\Users\isard\Pictures\noi.jpg', TO_DATE('2016-01-01', 'YYYY-MM-DD'), 'Carrer L, 14', 2025, 'ES2221000418401235', '63456789E');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Lucía', 'González', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2017-01-01', 'YYYY-MM-DD'), 'Carrer M, 2', 2025, 'ES1121000418401236', '73456789F');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Pablo', 'Ramírez', 'H', 'C:\Users\isard\Pictures\noi.jpg', TO_DATE('2016-01-01', 'YYYY-MM-DD'), 'Carrer N, 5', 2025, 'ES0021000418401237', '83456789G');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Marta', 'Torres', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2017-01-01', 'YYYY-MM-DD'), 'Carrer O, 1', 2025, 'ES7721000418401238', '93456789H');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Sergio', 'Hernández', 'H', 'C:\Users\isard\Pictures\noi.jpg', TO_DATE('2016-01-01', 'YYYY-MM-DD'), 'Carrer P, 6', 2025, 'ES6621000418401239', '13456789J');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Clara', 'Ruiz', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2017-01-01', 'YYYY-MM-DD'), 'Carrer Q, 11', 2025, 'ES5521000418401240', '23456789K');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Javier', 'Díaz', 'H', 'C:\Users\isard\Pictures\noi.jpg', TO_DATE('2016-01-01', 'YYYY-MM-DD'), 'Carrer R, 9', 2025, 'ES4421000418401241', '33456789L');



-- Jugadors Aleví Femení
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('María', 'López', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2014-01-01', 'YYYY-MM-DD'), 'Carrer S, 10', 2025, 'ES8821000418401242', '44556789A');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Sara', 'Martín', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2015-01-01', 'YYYY-MM-DD'), 'Carrer T, 22', 2025, 'ES9921000418401243', '54556789B');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Paula', 'Fernández', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2014-01-01', 'YYYY-MM-DD'), 'Carrer U, 5', 2025, 'ES7721000418401244', '64556789C');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Cristina', 'García', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2015-01-01', 'YYYY-MM-DD'), 'Carrer V, 9', 2025, 'ES6621000418401245', '74556789D');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Claudia', 'Serrano', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2014-01-01', 'YYYY-MM-DD'), 'Carrer W, 7', 2025, 'ES5521000418401246', '84556789E');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Lucía', 'Romero', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2015-01-01', 'YYYY-MM-DD'), 'Carrer X, 15', 2025, 'ES4421000418401247', '94556789F');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Carmen', 'Ortiz', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2014-01-01', 'YYYY-MM-DD'), 'Carrer Y, 11', 2025, 'ES3321000418401248', '14556789G');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Patricia', 'Molina', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2015-01-01', 'YYYY-MM-DD'), 'Carrer Z, 6', 2025, 'ES2221000418401249', '24556789H');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Isabel', 'Reyes', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2014-01-01', 'YYYY-MM-DD'), 'Carrer A, 18', 2025, 'ES1121000418401250', '34556789I');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Raquel', 'Gil', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2015-01-01', 'YYYY-MM-DD'), 'Carrer B, 4', 2025, 'ES0021000418401251', '44556789J');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Eva', 'Vargas', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2014-01-01', 'YYYY-MM-DD'), 'Carrer C, 21', 2025, 'ES9921000418401252', '54556789K');



-- Jugadors Infantil Femení
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Marina', 'Hernández', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2011-01-01', 'YYYY-MM-DD'), 'Carrer D, 14', 2025, 'ES8821000418401253', '64556789A');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Alba', 'Cano', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2012-01-01', 'YYYY-MM-DD'), 'Carrer E, 30', 2025, 'ES9921000418401254', '74556789B');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Noa', 'Salas', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2013-01-01', 'YYYY-MM-DD'), 'Carrer F, 16', 2025, 'ES7721000418401255', '84556789C');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Irene', 'Paz', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2011-01-01', 'YYYY-MM-DD'), 'Carrer G, 2', 2025, 'ES6621000418401256', '94556789D');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Bianca', 'Ferrer', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2012-01-01', 'YYYY-MM-DD'), 'Carrer H, 17', 2025, 'ES5521000418401257', '04556789E');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Olivia', 'Bravo', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2013-01-01', 'YYYY-MM-DD'), 'Carrer I, 8', 2025, 'ES4421000418401258', '14556789F');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Silvia', 'Navas', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2011-01-01', 'YYYY-MM-DD'), 'Carrer J, 19', 2025, 'ES3321000418401259', '24556789G');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Raquel', 'Santos', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2012-01-01', 'YYYY-MM-DD'), 'Carrer K, 3', 2025, 'ES2221000418401260', '34556789H');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Lola', 'Quintana', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2013-01-01', 'YYYY-MM-DD'), 'Carrer L, 12', 2025, 'ES1121000418401261', '44556789I');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Tania', 'Alonso', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2011-01-01', 'YYYY-MM-DD'), 'Carrer M, 22', 2025, 'ES0021000418401262', '54556789J');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Aitana', 'Valdés', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2012-01-01', 'YYYY-MM-DD'), 'Carrer N, 13', 2025, 'ES9921000418401263', '64556789K');



-- Jugadors Cadet Masculí
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Lucas', 'Moreno', 'H', 'C:\Users\isard\Pictures\noi.jpg', TO_DATE('2009-01-01', 'YYYY-MM-DD'), 'Carrer O, 4', 2025, 'ES8821000418401264', '74556789A');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Santiago', 'Martínez', 'H', 'C:\Users\isard\Pictures\noi.jpg', TO_DATE('2010-01-01', 'YYYY-MM-DD'), 'Carrer P, 9', 2025, 'ES9921000418401265', '84556789B');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Diego', 'Sánchez', 'H', 'C:\Users\isard\Pictures\noi.jpg', TO_DATE('2009-01-01', 'YYYY-MM-DD'), 'Carrer Q, 10', 2025, 'ES7721000418401266', '94556789C');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('David', 'Ramírez', 'H', 'C:\Users\isard\Pictures\noi.jpg', TO_DATE('2010-01-01', 'YYYY-MM-DD'), 'Carrer R, 12', 2025, 'ES6621000418401267', '04556789D');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Andrés', 'García', 'H', 'C:\Users\isard\Pictures\noi.jpg', TO_DATE('2009-01-01', 'YYYY-MM-DD'), 'Carrer S, 11', 2025, 'ES5521000418401268', '14556789E');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Javier', 'Cruz', 'H', 'C:\Users\isard\Pictures\noi.jpg', TO_DATE('2010-01-01', 'YYYY-MM-DD'), 'Carrer T, 5', 2025, 'ES4421000418401269', '24556789F');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Álvaro', 'Hernández', 'H', 'C:\Users\isard\Pictures\noi.jpg', TO_DATE('2009-01-01', 'YYYY-MM-DD'), 'Carrer U, 20', 2025, 'ES3321000418401270', '34556789G');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Felipe', 'Mendoza', 'H', 'C:\Users\isard\Pictures\noi.jpg', TO_DATE('2010-01-01', 'YYYY-MM-DD'), 'Carrer V, 6', 2025, 'ES2221000418401271', '44556789H');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Gabriel', 'Reyes', 'H', 'C:\Users\isard\Pictures\noi.jpg', TO_DATE('2009-01-01', 'YYYY-MM-DD'), 'Carrer W, 1', 2025, 'ES1121000418401272', '54556789I');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Manuel', 'Vidal', 'H', 'C:\Users\isard\Pictures\noi.jpg', TO_DATE('2010-01-01', 'YYYY-MM-DD'), 'Carrer X, 3', 2025, 'ES0021000418401273', '64556789J');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Samuel', 'González', 'H', 'C:\Users\isard\Pictures\noi.jpg', TO_DATE('2009-01-01', 'YYYY-MM-DD'), 'Carrer Y, 15', 2025, 'ES9921000418401274', '74556789K');



-- Jugadors Juvenil Masculí
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Pablo', 'Cordero', 'H', 'C:\Users\isard\Pictures\noi.jpg', TO_DATE('2007-01-01', 'YYYY-MM-DD'), 'Carrer Z, 8', 2025, 'ES8821000418401275', '84556789A');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Marc', 'López', 'H', 'C:\Users\isard\Pictures\noi.jpg', TO_DATE('2008-01-01', 'YYYY-MM-DD'), 'Carrer AA, 3', 2025, 'ES9921000418401276', '94556789B');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Iván', 'Cáceres', 'H', 'C:\Users\isard\Pictures\noi.jpg', TO_DATE('2008-01-01', 'YYYY-MM-DD'), 'Carrer BB, 14', 2025, 'ES7721000418401277', '04556789C');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Carlos', 'Ruiz', 'H', 'C:\Users\isard\Pictures\noi.jpg', TO_DATE('2007-01-01', 'YYYY-MM-DD'), 'Carrer CC, 11', 2025, 'ES6621000418401278', '14556789D');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Nicolás', 'Soler', 'H', 'C:\Users\isard\Pictures\noi.jpg', TO_DATE('2008-01-01', 'YYYY-MM-DD'), 'Carrer DD, 6', 2025, 'ES5521000418401279', '24556789E');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Adrián', 'García', 'H', 'C:\Users\isard\Pictures\noi.jpg', TO_DATE('2007-01-01', 'YYYY-MM-DD'), 'Carrer EE, 7', 2025, 'ES4421000418401280', '34556789F');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Fernando', 'Alvarez', 'H', 'C:\Users\isard\Pictures\noi.jpg', TO_DATE('2007-01-01', 'YYYY-MM-DD'), 'Carrer FF, 2', 2025, 'ES3321000418401281', '44556789G');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Jorge', 'Montes', 'H', 'C:\Users\isard\Pictures\noi.jpg', TO_DATE('2007-01-01', 'YYYY-MM-DD'), 'Carrer GG, 18', 2025, 'ES2221000418401282', '54556789H');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Raúl', 'Pérez', 'H', 'C:\Users\isard\Pictures\noi.jpg', TO_DATE('2008-01-01', 'YYYY-MM-DD'), 'Carrer HH, 16', 2025, 'ES1121000418401283', '64556789I');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Hugo', 'Torres', 'H', 'C:\Users\isard\Pictures\noi.jpg', TO_DATE('2007-01-01', 'YYYY-MM-DD'), 'Carrer II, 4', 2025, 'ES0021000418401284', '74556789J');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Álvaro', 'Moreno', 'H', 'C:\Users\isard\Pictures\noi.jpg', TO_DATE('2008-01-01', 'YYYY-MM-DD'), 'Carrer JJ, 5', 2025, 'ES9921000418401285', '84556789K');




-- Jugadors Senior Femení
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Lucía', 'Mora', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2005-01-01', 'YYYY-MM-DD'), 'Carrer KK, 10', 2025, 'ES8821000418401286', '94556789A');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Carmen', 'Jurado', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2000-01-01', 'YYYY-MM-DD'), 'Carrer LL, 12', 2025, 'ES9921000418401287', '04556789B');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Ana', 'Guerrero', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('1995-01-01', 'YYYY-MM-DD'), 'Carrer MM, 3', 2025, 'ES7721000418401288', '14556789C');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('María', 'Castaño', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('1990-01-01', 'YYYY-MM-DD'), 'Carrer NN, 14', 2025, 'ES6621000418401289', '24556789D');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Beatriz', 'Sierra', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('1980-01-01', 'YYYY-MM-DD'), 'Carrer OO, 7', 2025, 'ES5521000418401290', '34556789E');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Patricia', 'Martínez', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2005-01-01', 'YYYY-MM-DD'), 'Carrer PP, 8', 2025, 'ES4421000418401291', '44556789F');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Teresa', 'Maldonado', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2000-01-01', 'YYYY-MM-DD'), 'Carrer QQ, 1', 2025, 'ES3321000418401292', '54556789G');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Vanessa', 'Ríos', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('1995-01-01', 'YYYY-MM-DD'), 'Carrer RR, 9', 2025, 'ES2221000418401293', '64556789H');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Gloria', 'Torres', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('1990-01-01', 'YYYY-MM-DD'), 'Carrer SS, 5', 2025, 'ES1121000418401294', '74556789I');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Elena', 'Pérez', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('1980-01-01', 'YYYY-MM-DD'), 'Carrer TT, 6', 2025, 'ES0021000418401295', '84556789J');
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Cristina', 'Sánchez', 'D', 'C:\Users\isard\Pictures\noia.jpg', TO_DATE('2005-01-01', 'YYYY-MM-DD'), 'Carrer UU, 4', 2025, 'ES9921000418401296', '94556789K');




-- Jugadors Convidats
INSERT INTO JUGADOR (nom, cognom, sexe, foto, data_naix, adreca, any_fi_revisio_medica, IBAN, idLegal) 
VALUES ('Roc', 'Lopez', 'H', 'C:\Users\isard\Pictures\noi.jpg', TO_DATE('2009-01-01', 'YYYY-MM-DD'), 'Carrer Joan M, 15', 2025, 'ES9921000418404721', '55656789K');






-- Equip Benjamí Mixt
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (1, 1, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (1, 2, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (1, 3, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (1, 4, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (1, 5, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (1, 6, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (1, 7, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (1, 8, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (1, 9, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (1, 10, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (1, 11, 'T');


-- Equip Aleví Femení
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (2, 12, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (2, 13, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (2, 14, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (2, 15, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (2, 16, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (2, 17, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (2, 18, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (2, 19, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (2, 20, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (2, 21, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (2, 22, 'T');



-- Equip Infantil Femení
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (3, 23, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (3, 24, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (3, 25, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (3, 26, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (3, 27, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (3, 28, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (3, 29, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (3, 30, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (3, 31, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (3, 32, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (3, 33, 'T');





-- Equip Cadet Masculí
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (4, 34, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (4, 35, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (4, 36, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (4, 37, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (4, 38, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (4, 39, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (4, 40, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (4, 41, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (4, 42, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (4, 43, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (4, 44, 'T');




-- Equip Juvenil Masculí
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (5, 45, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (5, 46, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (5, 47, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (5, 48, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (5, 49, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (5, 50, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (5, 51, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (5, 52, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (5, 53, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (5, 54, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (5, 55, 'T');





-- Equip Senior Femení
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (6, 56, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (6, 57, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (6, 58, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (6, 59, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (6, 60, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (6, 61, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (6, 62, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (6, 63, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (6, 64, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (6, 65, 'T');
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (6, 66, 'T');




-- Cadet Convidat a juvenil
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (5, 37, 'C');

-- Benjami Convidat a cadet
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (4, 1, 'C');

-- Benjami Convidat a Aleví
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (2, 2, 'C');

-- Aleví Convidat a Infantil
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (3, 14, 'C');

-- Infantil Convidat a Senior
INSERT INTO MEMBRE (id_equip, id_jugador, titular_convidat) VALUES (6, 30, 'C');
