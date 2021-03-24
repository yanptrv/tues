DROP DATABASE IF EXISTS Weights;
CREATE DATABASE Weights;
USE Weights;

CREATE TABLE Person(
id int primary key not null auto_increment,
name varchar(100),
age int not null,
gender ENUM('M', 'F')
);

CREATE TABLE WeightData(
id int primary key not null auto_increment,
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
weight double not null,
person_id int,
FOREIGN KEY (person_id) references Person(id)
);

INSERT INTO Person(name, age, gender) VALUES("ALEX", 16, 'M');
INSERT INTO Person(name, age, gender) VALUES("Ivo", 18, 'F');
INSERT INTO Person(name, age, gender) VALUES("Petar", 19, 'F');
INSERT INTO Person(name, age, gender) VALUES("Ivan", 15, 'F');
INSERT INTO Person(name, age, gender) VALUES("Koko", 17, 'F');
INSERT INTO Person(name, age, gender) VALUES("Bobo", 14, 'M');

INSERT INTO Person(name, age, gender) VALUES("Iva", 17, 'F');
INSERT INTO Person(name, age, gender) VALUES("Bojidara", 15, 'F');
INSERT INTO Person(name, age, gender) VALUES("Kati", 19, 'F');
INSERT INTO Person(name, age, gender) VALUES("Ani", 17, 'F');

INSERT INTO WeightData(person_id, weight) VALUES(1, 70);
INSERT INTO WeightData(person_id, weight) VALUES(1, 80);
INSERT INTO WeightData(person_id, weight) VALUES(1, 85);
INSERT INTO WeightData(person_id, weight, created_at) VALUES(1, 85, "2019-02-12 00:00:00");
INSERT INTO WeightData(person_id, weight, created_at) VALUES(1, 85, "2019-02-12 00:00:00");
INSERT INTO WeightData(person_id, weight) VALUES(2, 65);
INSERT INTO WeightData(person_id, weight) VALUES(3, 91);
INSERT INTO WeightData(person_id, weight) VALUES(4, 81);
INSERT INTO WeightData(person_id, weight) VALUES(5, 52);

#Task 1

ALTER TABLE WeightData
ADD name varchar(50);

#Task 2

UPDATE WeightData
RIGHT JOIN Person on WeightData.person_id = Person.id
SET WeightData.name = Person.name;

#Task 3
ALTER TABLE Person
DROP COLUMN name;

#Task 4

ALTER TABLE WeightData
ADD gender ENUM('M', 'F');

#Task 5

UPDATE WeightData
RIGHT JOIN Person on WeightData.person_id = Person.id
SET WeightData.gender = Person.gender;

#Task 6

ALTER TABLE Person
DROP COLUMN gender;
