-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema sprava_ucebni
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sprava_ucebni
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sprava_ucebni` DEFAULT CHARACTER SET utf8 ;
USE `sprava_ucebni` ;

-- -----------------------------------------------------
-- Table `sprava_ucebni`.`pouzivatel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sprava_ucebni`.`pouzivatel` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `meno` VARCHAR(200) NOT NULL UNIQUE,
  `heslo` CHAR(60) NOT NULL,
  `sol` CHAR(29) NOT NULL,
  `posledne_prihlasenie` DATETIME NULL,
  `email` VARCHAR(100) NOT NULL UNIQUE,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sprava_ucebni`.`ucebna`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sprava_ucebni`.`ucebna` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nazov` VARCHAR(100) NOT NULL UNIQUE,
  `pouzivatel_id` INT,
  PRIMARY KEY (`id`),
  INDEX `fk_ucebna_pouzivatel1_idx` (`pouzivatel_id` ASC),
  CONSTRAINT `fk_ucebna_pouzivatel1`
    FOREIGN KEY (`pouzivatel_id`)
    REFERENCES `sprava_ucebni`.`pouzivatel` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sprava_ucebni`.`projektor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sprava_ucebni`.`projektor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `pocet_nasvietenych_hodin` INT NOT NULL,
  `kvalita_obrazu` VARCHAR(200) NOT NULL,
  `nazov_modelu` VARCHAR(100) NOT NULL,
  `ocakavana_zivotnost_lampy` INT NOT NULL,
  `ucebna_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_projektor_ucebna_idx` (`ucebna_id` ASC),
  CONSTRAINT `fk_projektor_ucebna`
    FOREIGN KEY (`ucebna_id`)
    REFERENCES `sprava_ucebni`.`ucebna` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sprava_ucebni`.`pocitac`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sprava_ucebni`.`pocitac` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `seriove_cislo` VARCHAR(45) NOT NULL UNIQUE,
  `mac_adresa` VARCHAR(100) NOT NULL UNIQUE,
  `posledne_pouzitie` DATETIME,
  `ucebna_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_pocitac_ucebna1_idx` (`ucebna_id` ASC),
  CONSTRAINT `fk_pocitac_ucebna1`
    FOREIGN KEY (`ucebna_id`)
    REFERENCES `sprava_ucebni`.`ucebna` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sprava_ucebni`.`tabula`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sprava_ucebni`.`tabula` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `typ` VARCHAR(100) NOT NULL,
  `pocet_pisatiek` INT NOT NULL,
  `ucebna_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tabula_ucebna1_idx` (`ucebna_id` ASC),
  CONSTRAINT `fk_tabula_ucebna1`
    FOREIGN KEY (`ucebna_id`)
    REFERENCES `sprava_ucebni`.`ucebna` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sprava_ucebni`.`chyba`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sprava_ucebni`.`chyba` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `poznamka` VARCHAR(500) NULL,
  `cas` DATETIME NOT NULL,
  `hlasatel_chyby` VARCHAR(100) NOT NULL,
  `ucebna_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_chyba_ucebna1_idx` (`ucebna_id` ASC),
  CONSTRAINT `fk_chyba_ucebna1`
    FOREIGN KEY (`ucebna_id`)
    REFERENCES `sprava_ucebni`.`ucebna` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sprava_ucebni`.`spotreba`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sprava_ucebni`.`spotreba` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `datum` DATE NOT NULL,
  `hodnota` INT NOT NULL,
  `ucebna_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_spotreba_ucebna1_idx` (`ucebna_id` ASC),
  CONSTRAINT `fk_spotreba_ucebna1`
    FOREIGN KEY (`ucebna_id`)
    REFERENCES `sprava_ucebni`.`ucebna` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

INSERT INTO `sprava_ucebni`.`pouzivatel` (`meno`, `heslo`, `sol`, `posledne_prihlasenie`, `email`) VALUES ('Dzama', '$2a$10$dHulBDcXGdqjIICmSVnBUO9Za0ahpc1qW.0F7g.8eSGV0AyFZbr3i', '$2a$10$dHulBDcXGdqjIICmSVnBUO', '2017-12-06 12:45', 'dzama@sranda.ks');
INSERT INTO `sprava_ucebni`.`pouzivatel` (`meno`, `heslo`, `sol`, `posledne_prihlasenie`, `email`) VALUES ('Karas', '$2a$10$LT82ytlN6ngYqSk.YexZreMV6zC9EMMnvIFCA/du3OZFOFu6/MZNy', '$2a$10$LT82ytlN6ngYqSk.YexZre', '2017-10-23 20:34', 'karas@jaja.aj');

INSERT INTO `sprava_ucebni`.`ucebna` (`nazov`, `pouzivatel_id`) VALUES ('SJSP19', '1');
INSERT INTO `sprava_ucebni`.`ucebna` (`nazov`, `pouzivatel_id`) VALUES ('MP5', '2');

INSERT INTO `sprava_ucebni`.`tabula` (`typ`, `pocet_pisatiek`, `ucebna_id`) VALUES ('na kriedy', '10', '1');
INSERT INTO `sprava_ucebni`.`tabula` (`typ`, `pocet_pisatiek`, `ucebna_id`) VALUES ('na fixy', '3', '2');

INSERT INTO `sprava_ucebni`.`spotreba` (`datum`, `hodnota`, `ucebna_id`) VALUES ('2017-05-12', '78', '1');
INSERT INTO `sprava_ucebni`.`spotreba` (`datum`, `hodnota`, `ucebna_id`) VALUES ('2017-09-09', '23', '2');

INSERT INTO `sprava_ucebni`.`projektor` (`pocet_nasvietenych_hodin`, `kvalita_obrazu`, `nazov_modelu`, `ocakavana_zivotnost_lampy`, `ucebna_id`) VALUES ('12', 'velmi dobra', 'ARK-87', '8655', '1');
INSERT INTO `sprava_ucebni`.`projektor` (`pocet_nasvietenych_hodin`, `kvalita_obrazu`, `nazov_modelu`, `ocakavana_zivotnost_lampy`, `ucebna_id`) VALUES ('32', 'fajn', 'A-K-A', '1245', '2');

INSERT INTO `sprava_ucebni`.`pocitac` (`seriove_cislo`, `mac_adresa`, `posledne_pouzitie`, `ucebna_id`) VALUES ('AMD487', '78-sa-gh-1g-12-er', '2012-12-12 12:12', '1');
INSERT INTO `sprava_ucebni`.`pocitac` (`seriove_cislo`, `mac_adresa`, `posledne_pouzitie`, `ucebna_id`) VALUES ('FOR745', 'a4-5s-54-sa-qw-45', '2017-03-25 11:45', '2');

INSERT INTO `sprava_ucebni`.`chyba` (`poznamka`, `cas`, `hlasatel_chyby`, `ucebna_id`) VALUES ('piska tabula', '2017-12-06 17:00', 'Jakub Dzama', '1');
INSERT INTO `sprava_ucebni`.`chyba` (`poznamka`, `cas`, `hlasatel_chyby`, `ucebna_id`) VALUES ('negunguje pocitac AMD478', '2017-12-07 14:48', 'Viktor Olejar', '2');
