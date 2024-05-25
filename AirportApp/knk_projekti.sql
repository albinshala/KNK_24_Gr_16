create database menaxhimi_i_fluturimeve_airoport;
use menaxhimi_i_fluturimeve_airoport;

CREATE TABLE perdoruesit(
  id INT PRIMARY KEY AUTO_INCREMENT,
  emri VARCHAR(255) ,
  mbiemri VARCHAR(255),
  username VARCHAR(255),
  fjalekalimi_salted VARCHAR(255),
  salt  VARCHAR(255),
  gjinia VARCHAR(1),
  admin boolean,
  ditelindja DATE
);

CREATE TABLE pasagjeret (
  id INT PRIMARY KEY AUTO_INCREMENT,
  perdoruesi_id INT,
  adresa VARCHAR(255),
  nacionaliteti VARCHAR(40),
  numri_telefonit DOUBLE,
  numri_pasaportes DOUBLE,
  FOREIGN KEY (perdoruesi_id) REFERENCES perdoruesit(id)
);

CREATE TABLE rezervimet(
   id INT PRIMARY KEY AUTO_INCREMENT,
   pasagjeri_id INT,
   fluturimi_id INT,
   numri_uleses INT,
   kategoria VARCHAR(50),
   bileta_id INT,
   FOREIGN KEY (pasagjeri_id) REFERENCES pasagjeret(id),
   FOREIGN KEY (fluturimi_id) REFERENCES fluturimet(id),
   FOREIGN KEY (bileta_id) REFERENCES bileta(bileta_id)
);

CREATE TABLE fluturimet(
  id INT PRIMARY KEY AUTO_INCREMENT,
  aeroplani_id INT,
  aeroporti_nisjes_id INT,
  nisja TIMESTAMP,
  aeroporti_arritjes_id INT,
  kthimi TIMESTAMP NULL,
  status VARCHAR(20),
  dy_drejtimeshe boolean,
  kohezgjatja INT,
  bileta_id int,
  FOREIGN KEY (bileta_id) REFERENCES bileta(bileta_id),
  FOREIGN KEY (aeroplani_id) REFERENCES aeroplanet(id),
  FOREIGN KEY (aeroporti_nisjes_id) REFERENCES aeroporti(id),
  FOREIGN KEY (aeroporti_arritjes_id) REFERENCES aeroporti(id)
);

CREATE TABLE aeroporti(
   id INT AUTO_INCREMENT PRIMARY KEY,
   emri VARCHAR(60),
   qyteti VARCHAR(40)
);

CREATE TABLE aeroplanet(
   id INT PRIMARY KEY AUTO_INCREMENT,
   kompania VARCHAR(150),
   kapaciteti INT,
   tipi VARCHAR(50)
);

CREATE TABLE bagazhet (
id INT PRIMARY KEY AUTO_INCREMENT,
pasagjeri_id INT,
numri_bagazhit INT,
pesha_bagazhit DECIMAL(5,2),
FOREIGN KEY (pasagjeri_id) REFERENCES pasagjeret(id)
);

CREATE TABLE bileta(
   bileta_id INT PRIMARY KEY AUTO_INCREMENT,
   çmimi INT
);

CREATE TABLE pagesa(
    id INT PRIMARY KEY AUTO_INCREMENT,
    menyra_pageses VARCHAR(20),
    emri_kartes VARCHAR(20),
    numri_kartes VARCHAR(20),
    data_skadimit DATE,
    kodi_cvv VARCHAR(4),
    bileta_id INT,
    FOREIGN KEY (bileta_id) REFERENCES bileta(id)
);

CREATE TABLE pyetjet (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pyetja VARCHAR(255) NOT NULL,
    perdoruesi_id INT NOT NULL,
    FOREIGN KEY (perdoruesi_id) REFERENCES perdoruesit(id)
);


