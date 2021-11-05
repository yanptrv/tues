#Database

DROP DATABASE IF EXISTS Bands;
CREATE DATABASE Bands;
USE Bands;

#Table

CREATE TABLE Person (
    id int primary key NOT NULL auto_increment,
    person_name varchar(50) NOT NULL,
    type ENUM('student','teacher')
);

CREATE TABLE Band (
    id int primary key NOT NULL auto_increment,
    person_id int REFERENCES Person(id),
    name varchar(50) NOT NULL,
    first_meeting DATETIME DEFAULT CURRENT_TIMESTAMP
);

#Insert

INSERT INTO Person (person_name, type)
VALUES
('Kris','student'),
('Mihail','teacher'),
('Finn', 'student');

INSERT INTO Band (person_id, name)
VALUES
(1,'Group1'),
(2,'Group1'),
(3,'Group1'),
(2, 'Group2'),
(NULL,'Group3');

SELECT * FROM Band;

#Task 1

SELECT name as GroupName, person_name as PersonName, type as PersonType  from Band
LEFT JOIN Person on Person.id = Band.person_id;

#Task 2

SELECT name as GroupName, COUNT(person_id) as Members FROM Band
LEFT JOIN Person on Band.person_id = Person.id
GROUP BY name;

#Task 3

SELECT name as GroupName, COUNT(person_id) as Members FROM Band
LEFT JOIN Person on Band.person_id = Person.id
WHERE type = 'student'
GROUP BY name;

#Task 4

SELECT name as GroupName, COUNT(person_id) as Members FROM Band
LEFT JOIN Person on Band.person_id = Person.id
WHERE type = 'teacher'
GROUP BY name;

#Task 5

SELECT name as GroupName, first_meeting as FirstMeeting FROM Band
LEFT JOIN Person on Band.person_id = Person.id
WHERE first_meeting year(first_meeting) = 2010 and month(first_meeting) = 10 and day(first_meeting) = 10
GROUP BY name;
