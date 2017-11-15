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
  `meno` VARCHAR(100) NOT NULL,
  `heslo` VARCHAR(45) NOT NULL,
  `posledne_prihlasenie` DATETIME NULL,
  `email` VARCHAR(80) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sprava_ucebni`.`ucebna`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sprava_ucebni`.`ucebna` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nazov` VARCHAR(45) NOT NULL,
  `pouzivatel_id` INT NOT NULL,
  PRIMARY KEY (`id`, `pouzivatel_id`),
  INDEX `fk_ucebna_pouzivatel1_idx` (`pouzivatel_id` ASC),
  CONSTRAINT `fk_ucebna_pouzivatel1`
    FOREIGN KEY (`pouzivatel_id`)
    REFERENCES `sprava_ucebni`.`pouzivatel` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sprava_ucebni`.`projektor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sprava_ucebni`.`projektor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `pocet_nasvietenych_hodin` INT NOT NULL,
  `kvalita_obrazu` VARCHAR(100) NOT NULL,
  `nazov_modelu` VARCHAR(45) NOT NULL,
  `ocakavana_zivotnost_lampy` INT NOT NULL,
  `ucebna_id` INT NOT NULL,
  PRIMARY KEY (`id`, `ucebna_id`),
  INDEX `fk_projektor_ucebna_idx` (`ucebna_id` ASC),
  CONSTRAINT `fk_projektor_ucebna`
    FOREIGN KEY (`ucebna_id`)
    REFERENCES `sprava_ucebni`.`ucebna` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sprava_ucebni`.`pocitac`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sprava_ucebni`.`pocitac` (
  `seriove_cislo` VARCHAR(45) NOT NULL,
  `ucebna_id` INT NOT NULL,
  `funkcny` VARCHAR(1) NOT NULL,
  PRIMARY KEY (`seriove_cislo`, `ucebna_id`),
  INDEX `fk_pocitac_ucebna1_idx` (`ucebna_id` ASC),
  CONSTRAINT `fk_pocitac_ucebna1`
    FOREIGN KEY (`ucebna_id`)
    REFERENCES `sprava_ucebni`.`ucebna` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sprava_ucebni`.`tabula`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sprava_ucebni`.`tabula` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `typ` CHAR(1) NOT NULL,
  `pocet_pisatiek` INT NOT NULL,
  `ucebna_id` INT NOT NULL,
  PRIMARY KEY (`id`, `ucebna_id`),
  INDEX `fk_tabula_ucebna1_idx` (`ucebna_id` ASC),
  CONSTRAINT `fk_tabula_ucebna1`
    FOREIGN KEY (`ucebna_id`)
    REFERENCES `sprava_ucebni`.`ucebna` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sprava_ucebni`.`chyba`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sprava_ucebni`.`chyba` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `poznamka` VARCHAR(500) NULL,
  `cas` DATETIME NOT NULL,
  `hlasatel_chyby` VARCHAR(100) NULL,
  `ucebna_id` INT NOT NULL,
  PRIMARY KEY (`id`, `ucebna_id`),
  INDEX `fk_chyba_ucebna1_idx` (`ucebna_id` ASC),
  CONSTRAINT `fk_chyba_ucebna1`
    FOREIGN KEY (`ucebna_id`)
    REFERENCES `sprava_ucebni`.`ucebna` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `sprava_ucebni`.`spotreba`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sprava_ucebni`.`spotreba` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cas` DATETIME NOT NULL,
  `hodnota` INT NOT NULL,
  `ucebna_id` INT NOT NULL,
  PRIMARY KEY (`id`, `ucebna_id`),
  INDEX `fk_spotreba_ucebna1_idx` (`ucebna_id` ASC),
  CONSTRAINT `fk_spotreba_ucebna1`
    FOREIGN KEY (`ucebna_id`)
    REFERENCES `sprava_ucebni`.`ucebna` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
