DROP DATABASE IF EXISTS Scale;
CREATE DATABASE Scale;
USE Scale;

CREATE TABLE Person (
    id int primary key NOT NULL auto_increment,
    name varchar(50) UNIQUE NOT NULL,
    age int NOT NULL CHECK (age > 0),
    gender ENUM('M', 'F')
);

CREATE TABLE weightData (
    id int primary key NOT NULL auto_increment,
    person_id int REFERENCES Person(id),
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    weight float NOT NULL CHECK (weight > 0)
);


INSERT INTO Person (name, age, gender)
VALUES
('Kris', 17, 'M'),
('Alex', 27, 'F'),
('Mihail', 44, 'M'),
('Stela', 69, 'F');

INSERT INTO weightData (person_id, weight)
VALUES
(1, 87.6),
(2, 52),
(3, 83.2),
(4, 69);

SELECT AVG(weight) as AverageWeight FROM weightData
WHERE person_id = 1 and EXTRACT(MONTH FROM created_at) = 2 and EXTRACT(YEAR FROM created_at) = 2019;

SELECT AVG(weight) as AverageFemaleWeight FROM weightData
LEFT JOIN Person on weightData.person_id = Person.id
WHERE gender = 'F';

SELECT name as HeaviestMan from weightData
LEFT JOIN Person on weightData.person_id = Person.id
WHERE gender = 'M' and EXTRACT(MONTH FROM created_at) BETWEEN 3 and 4 and EXTRACT(YEAR FROM created_at) = 2021 ORDER BY weight DESC limit 1;

SELECT gender, COUNT(gender) as GenderCount FROM Person
GROUP BY gender;

SELECT name as Name, AVG(weight) as AverageWeight FROM weightData
LEFT JOIN Person on weightData.person_id = Person.id
GROUP BY name;

DROP DATABASE Scale;
