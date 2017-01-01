-- MySQL Script generated by MySQL Workbench
-- 12/20/16 00:53:15
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`company`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`company` ;

CREATE TABLE IF NOT EXISTS `mydb`.`company` (
  `companyId` INT NOT NULL,
  `companyName` VARCHAR(45) NOT NULL,
  `companyPurpose` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`companyId`),
  UNIQUE INDEX `companyId_UNIQUE` (`companyId` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`user` ;

CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `userId` INT NOT NULL AUTO_INCREMENT COMMENT 'Users unique ID 1 -> inf. ',
  `userIsLocked` INT NULL,
  `userLoginAttempt` INT NULL,
  `companyId` INT NOT NULL,
  `userPassword` VARCHAR(50) NOT NULL,
  `userEmail` VARCHAR(45) NOT NULL,
  `userUnlockCode` VARCHAR(45) NULL,
  `userType` VARCHAR(45) NOT NULL DEFAULT 'normal',
  `company_companyId` INT NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE INDEX `userId_UNIQUE` (`userId` ASC),
  INDEX `fk_user_company_idx` (`company_companyId` ASC),
  CONSTRAINT `fk_user_company`
    FOREIGN KEY (`company_companyId`)
    REFERENCES `mydb`.`company` (`companyId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`fileLocator`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`fileLocator` ;

CREATE TABLE IF NOT EXISTS `mydb`.`fileLocator` (
  `fileLocatorId` INT NOT NULL AUTO_INCREMENT,
  `fileUrl` VARCHAR(500) NOT NULL,
  `fileType` VARCHAR(500) NOT NULL,
  `fileName` VARCHAR(500) NOT NULL,
  `folderName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`fileLocatorId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`fileLocator_has_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`fileLocator_has_user` ;

CREATE TABLE IF NOT EXISTS `mydb`.`fileLocator_has_user` (
  `fileLocator_fileLocatorId` INT NOT NULL,
  `user_userId` INT NOT NULL,
  PRIMARY KEY (`fileLocator_fileLocatorId`, `user_userId`),
  INDEX `fk_fileLocator_has_user_user1_idx` (`user_userId` ASC),
  INDEX `fk_fileLocator_has_user_fileLocator1_idx` (`fileLocator_fileLocatorId` ASC),
  CONSTRAINT `fk_fileLocator_has_user_fileLocator1`
    FOREIGN KEY (`fileLocator_fileLocatorId`)
    REFERENCES `mydb`.`fileLocator` (`fileLocatorId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_fileLocator_has_user_user1`
    FOREIGN KEY (`user_userId`)
    REFERENCES `mydb`.`user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`convertedFile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`convertedFile` ;

CREATE TABLE IF NOT EXISTS `mydb`.`convertedFile` (
  `convertedFileId` INT NOT NULL AUTO_INCREMENT,
  `convertedFileName` VARCHAR(500) NOT NULL,
  `convertAlgorithmUsed` VARCHAR(500) NOT NULL,
  `convertedFileUrl` VARCHAR(500) NOT NULL,
  `originalFileId` INT NOT NULL,
  PRIMARY KEY (`convertedFileId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`user_has_convertedFile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`user_has_convertedFile` ;

CREATE TABLE IF NOT EXISTS `mydb`.`user_has_convertedFile` (
  `user_userId` INT NOT NULL,
  `convertedFile_convertedFileId` INT NOT NULL,
  PRIMARY KEY (`user_userId`, `convertedFile_convertedFileId`),
  INDEX `fk_user_has_convertedFile_convertedFile1_idx` (`convertedFile_convertedFileId` ASC),
  INDEX `fk_user_has_convertedFile_user1_idx` (`user_userId` ASC),
  CONSTRAINT `fk_user_has_convertedFile_user1`
    FOREIGN KEY (`user_userId`)
    REFERENCES `mydb`.`user` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_convertedFile_convertedFile1`
    FOREIGN KEY (`convertedFile_convertedFileId`)
    REFERENCES `mydb`.`convertedFile` (`convertedFileId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
