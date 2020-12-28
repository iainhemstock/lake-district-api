--spring.datasource.separator = ^;

DROP DATABASE IF EXISTS wainwright_fells^;
CREATE DATABASE wainwright_fells^;
SET collation_connection = 'utf8_general_ci'^;
ALTER DATABASE wainwright_fells CHARACTER SET utf8 collate utf8_general_ci^;
USE wainwright_fells^;

CREATE TABLE lakes (
    id INT NOT NULL auto_increment,
    name VARCHAR(25) NOT NULL,
    PRIMARY KEY (id)
)^;

CREATE TABLE regions (
    id INT NOT NULL auto_increment,
    name VARCHAR(30) NOT NULL,
    PRIMARY KEY (id)
)^;

CREATE TABLE os_maps (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(25) NOT NULL,
    PRIMARY KEY (id)
)^;

CREATE TABLE classificationEntities (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(25) NOT NULL,
    PRIMARY KEY (id)
)^;

CREATE TABLE fellEntities (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(25) NOT NULL,
    height_meters INT NOT NULL,
    prominence_meters INT NOT NULL,
    region_id INT NOT NULL,
    parent_peak_id INT, -- is modified to be NOT NULL after all the fellEntities are inserted
    PRIMARY KEY (id),
    FOREIGN KEY (region_id) REFERENCES regions(id),
    FOREIGN KEY (parent_peak_id) REFERENCES fellEntities(id)
)^;

CREATE TABLE locations (
    fell_id INT NOT NULL UNIQUE,
    latitude double NOT NULL,
    longitude double NOT NULL,
    os_map_ref VARCHAR(8) NOT NULL UNIQUE,
    FOREIGN KEY (fell_id) REFERENCES fellEntities(id),
    UNIQUE (latitude, longitude)
)^;

CREATE TABLE fells_classifications (
    fell_id INT NOT NULL,
    classification_id INT NOT NULL,
    FOREIGN KEY (fell_id) REFERENCES fellEntities(id),
    FOREIGN KEY (classification_id) REFERENCES classificationEntities(id),
    UNIQUE (fell_id, classification_id)
)^;

CREATE TABLE fells_osmaps (
    fell_id INT NOT NULL,
    os_map_id INT NOT NULL,
    FOREIGN KEY (fell_id) REFERENCES fellEntities(id),
    FOREIGN KEY (os_map_id) REFERENCES os_maps(id),
    UNIQUE (fell_id, os_map_id)
)^;
