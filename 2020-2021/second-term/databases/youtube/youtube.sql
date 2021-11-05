DROP DATABASE IF EXISTS Youtube;
CREATE DATABASE Youtube;
USE Youtube;

CREATE TABLE Potrebitel (
    id int auto_increment not null primary key,
    name varchar(20) unique not null
);

CREATE TABLE Kanal (
    id int auto_increment not null primary key,
    name varchar(20) unique not null
);

CREATE TABLE PotrebitelKanal (
    id int auto_increment not null primary key,
    potrebitel_id int not null,
    FOREIGN KEY (potrebitel_id) REFERENCES Potrebitel(id),
    kanal_id int not null,
    FOREIGN KEY (kanal_id) REFERENCES Kanal(id)
);

INSERT INTO Potrebitel (name) Values ("Kristiyan Petrov");
INSERT INTO Potrebitel (name) Values ("Bogomil Todorov");
INSERT INTO Potrebitel (name) Values ("Milena Todorova");

INSERT INTO Kanal (name) Values ("Happy Tree Friends");
INSERT INTO Kanal (name) Values ("DJCON");
INSERT INTO Kanal (name) Values ("ElsysLearn");
INSERT INTO Kanal (name) Values ("Sex Laf4e");

INSERT INTO PotrebitelKanal (potrebitel_id, kanal_id) VALUES (1, 1);
INSERT INTO PotrebitelKanal (potrebitel_id, kanal_id) VALUES (2, 1);
INSERT INTO PotrebitelKanal (potrebitel_id, kanal_id) VALUES (1, 2);
INSERT INTO PotrebitelKanal (potrebitel_id, kanal_id) VALUES (3, 3);

SELECT Potrebitel.name, Kanal.name FROM Potrebitel
INNER JOIN PotrebitelKanal
ON Potrebitel.id = PotrebitelKanal.potrebitel_id
INNER JOIN Kanal
ON Kanal.id = PotrebitelKanal.kanal_id
