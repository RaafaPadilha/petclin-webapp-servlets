/**
 * Author:  RaafaPadilha
 * Created: 10 de nov de 2022
 */
CREATE DATABASE IF NOT EXISTS petclin;

USE petclin;

CREATE TABLE IF NOT EXISTS animals (
    ani_id INT PRIMARY KEY AUTO_INCREMENT,
    ani_name VARCHAR(45) NOT NULL,
    ani_breed VARCHAR(45) NOT NULL,
    ani_color VARCHAR(45) NOT NULL,
    ani_gender ENUM('MALE', 'FEMALE') NOT NULL,
    ani_birth_date DATE NOT NULL,
    ani_weigth FLOAT NOT NULL,
    ani_type ENUM('DOG', 'CAT')
);

CREATE TABLE IF NOT EXISTS appointments (
    app_id INT PRIMARY KEY AUTO_INCREMENT,
    app_day DATE NOT NULL,
    app_time TIME NOT NULL,
    app_description VARCHAR(75) NOT NULL,
    app_animal_id INT NOT NULL,
    FOREIGN KEY (app_animal_id) REFERENCES animals (ani_id)
    ON DELETE CASCADE
);
