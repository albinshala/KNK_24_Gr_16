create database menaxhimi_i_fluturimeve_airoport;

use menaxhimi_i_fluturimeve_airoport;

CREATE TABLE perdoruesit (
  id INT PRIMARY KEY AUTO_INCREMENT,
  emri VARCHAR(255),
  mbiemri VARCHAR(255),
  email VARCHAR(255),
  fjalekalimi VARCHAR(255),
  mosha INT,
  gjinia VARCHAR(10),
  adresa VARCHAR(255),
  numri_telefonit VARCHAR(20),
  admin boolean
);


CREATE TABLE pasagjeret (
  id INT PRIMARY KEY AUTO_INCREMENT,
  perdoruesi_id INT,
  emri VARCHAR(255),
  mbiemri VARCHAR(255),
  email VARCHAR(255),
  mosha INT,
  adresa VARCHAR(255),
  numri_telefonit VARCHAR(20),
  nacionaliteti VARCHAR(40),
  FOREIGN KEY (perdoruesi_id) REFERENCES perdoruesit(id)
);

CREATE TABLE rezervimet(
   id INT PRIMARY KEY AUTO_INCREMENT,
   pasagjeri_id INT,
   fluturimi_id INT,
   aeroplani_id INT,
   numri_uleses INT,
   FOREIGN KEY (pasagjeri_id) REFERENCES pasagjeret(id),
   FOREIGN KEY (fluturimi_id) REFERENCES fluturimet(id),
   FOREIGN KEY (aeroplani_id) REFERENCES aeroplanet(id)
);

CREATE TABLE fluturimet (
  id INT PRIMARY KEY AUTO_INCREMENT,
  airline VARCHAR(50),
  qyteti_nisjes VARCHAR(50),
  nisja TIMESTAMP,  # data dhe ora e nisjes
  qyteti_arritjes VARCHAR(50),
  arritja TIMESTAMP, # data dhe ora e arritjes
  status VARCHAR(20)
);

CREATE TABLE aeroplanet(
   id INT PRIMARY KEY AUTO_INCREMENT,
   kompania VARCHAR(150),
   kapaciteti INT,
   tipi VARCHAR(50)
);

CREATE TABLE bileta(
   id INT PRIMARY KEY,
   çmimi INT
);

