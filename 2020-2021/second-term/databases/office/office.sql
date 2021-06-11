DROP DATABASE IF EXISTS NqmaNikakvoZnachenie;
CREATE DATABASE NqmaNikakvoZnachenie;
USE NqmaNikakvoZnachenie;

CREATE TABLE Office (
    id int primary key NOT NULL auto_increment,
    name varchar(50) UNIQUE NOT NULL
);

CREATE TABLE Person (
    id int primary key NOT NULL auto_increment,
    name varchar(50) UNIQUE NOT NULL,
    age int NOT NULL CHECK (age > 0),
    office_id int NOT NULL,
    FOREIGN KEY (office_id) REFERENCES Office(id)
);

CREATE TABLE Entered (
    id int primary key NOT NULL auto_increment,
    date_of_entering DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    person_id int NOT NULL,
    FOREIGN KEY (person_id) REFERENCES Person(id)
);

INSERT INTO Office(name) VALUES("Robopartans");
INSERT INTO Office(name) VALUES("Work and Share");
INSERT INTO Office(name) VALUES("Deliveroo");


INSERT INTO Person(name, age, office_id) VALUES("Misho", 25, 1);
INSERT INTO Person(name, age, office_id) VALUES("Ivan", 28, 1);
INSERT INTO Person(name, age, office_id) VALUES("Kiko", 35, 1);
INSERT INTO Person(name, age, office_id) VALUES("Georgi", 25, 2);
INSERT INTO Person(name, age, office_id) VALUES("Alex", 25, 2);


INSERT INTO Entered(date_of_entering, person_id) VALUES("2020-01-01", 1);
INSERT INTO Entered(date_of_entering, person_id) VALUES("2020-01-02", 1);
INSERT INTO Entered(date_of_entering, person_id) VALUES("2020-01-03", 1);
INSERT INTO Entered(date_of_entering, person_id) VALUES("2020-01-04", 1);
INSERT INTO Entered(date_of_entering, person_id) VALUES("2020-02-01", 2);
INSERT INTO Entered(date_of_entering, person_id) VALUES("2020-02-02", 2);
INSERT INTO Entered(date_of_entering, person_id) VALUES("2020-02-03", 2);
INSERT INTO Entered(date_of_entering, person_id) VALUES("2020-02-04", 2);
INSERT INTO Entered(date_of_entering, person_id) VALUES("2020-01-01", 3);
INSERT INTO Entered(date_of_entering, person_id) VALUES("2020-02-02", 3);
INSERT INTO Entered(date_of_entering, person_id) VALUES("2020-03-03", 3);
INSERT INTO Entered(date_of_entering, person_id) VALUES("2020-04-04", 3);
INSERT INTO Entered(date_of_entering, person_id) VALUES("2020-04-01", 3);
INSERT INTO Entered(date_of_entering, person_id) VALUES("2020-01-01", 4);
INSERT INTO Entered(date_of_entering, person_id) VALUES("2020-02-02", 4);
INSERT INTO Entered(date_of_entering, person_id) VALUES("2020-03-03", 4);
INSERT INTO Entered(date_of_entering, person_id) VALUES("2020-04-01", 4);
INSERT INTO Entered(date_of_entering, person_id) VALUES("2020-04-04", 4);
INSERT INTO Entered(date_of_entering, person_id) VALUES("2020-04-30", 4);
INSERT INTO Entered(date_of_entering, person_id) VALUES("2020-04-05", 4);

SELECT Entered.date_of_entering, Office.name FROM Entered
LEFT JOIN Person ON Entered.person_id = Person.id
LEFT JOIN Office ON Person.office_id = Office.id;

SELECT Count(Entered.date_of_entering) FROM Entered
LEFT JOIN Person ON Entered.person_id = Person.id
WHERE Person.name = "Georgi";

SELECT Count(Entered.date_of_entering), Person.name FROM Entered
LEFT JOIN Person ON Entered.person_id = Person.id
WHERE EXTRACT(MONTH FROM Entered.date_of_entering) = 4 and EXTRACT(YEAR FROM Entered.date_of_entering) = 2020 GROUP BY Person.name ORDER BY Count(Entered.date_of_entering) DESC limit 1;

SELECT Count(Entered.date_of_entering), Office.name FROM Entered
LEFT JOIN Person ON Entered.person_id = Person.id
LEFT JOIN Office ON Person.office_id = Office.id GROUP BY Office.name;

SELECT Office.name FROM Office
LEFT JOIN Person ON Person.office_id = Office.id
WHERE Person.id is NULL;

SELECT Person.name FROM Entered
LEFT JOIN Person ON Entered.person_id = Person.id
WHERE Entered.date_of_entering IS NOT NULL GROUP BY Person.name;
