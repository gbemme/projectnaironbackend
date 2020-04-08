-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


-- -----------------------------------------------------
-- Schema nairondb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `nairondb` ;

-- -----------------------------------------------------
-- Schema nairondb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `nairondb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nairondb`.`roles` ;

CREATE TABLE IF NOT EXISTS `nairondb`.`roles` (
  `roles_id` INT NOT NULL AUTO_INCREMENT,
  `roles_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`roles_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `nairondb`.`nairon_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nairondb`.`nairon_user` ;

CREATE TABLE IF NOT EXISTS `nairondb`.`nairon_user` (
  `nairon_user_id` INT(10) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(100) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  `password` VARCHAR(150) NOT NULL,
  `full_name` VARCHAR(100) NULL DEFAULT NULL,
  `business_sector` VARCHAR(100) NULL DEFAULT NULL,
  `business_name` VARCHAR(100) NULL DEFAULT NULL,
  `gender` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`nairon_user_id`, `email`),
  INDEX `email_index` (`email` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`roles_has_nairon_user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nairondb`.`roles_has_nairon_user` ;

CREATE TABLE IF NOT EXISTS `nairondb`.`roles_has_nairon_user` (
  `roles_roles_id` INT NOT NULL,
  `nairon_user__id` INT(10) NOT NULL,
  PRIMARY KEY (`roles_roles_id`, `nairon_user__id`),
  INDEX `fk_roles_has_nairon_user_nairon_user1_idx` (`nairon_user__id` ASC),
  INDEX `fk_roles_has_nairon_user_roles_idx` (`roles_roles_id` ASC),
  CONSTRAINT `fk_roles_has_nairon_user_roles`
    FOREIGN KEY (`roles_roles_id`)
    REFERENCES `nairondb`.`roles` (`roles_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_roles_has_nairon_user_nairon_user1`
    FOREIGN KEY (`nairon_user__id`)
    REFERENCES `nairondb`.`nairon_user` (`nairon_user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `nairondb` ;

-- -----------------------------------------------------
-- Table `nairondb`.`admin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nairondb`.`admin` ;

CREATE TABLE IF NOT EXISTS `nairondb`.`admin` (
  `adminId` INT(11) NOT NULL,
  `naironuserEmail` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`adminId`),
  INDEX `fk_admin_nairon_user1_idx` (`naironuserEmail` ASC),
  CONSTRAINT `fk_admin_nairon_user1`
    FOREIGN KEY (`naironuserEmail`)
    REFERENCES `nairondb`.`nairon_user` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nairondb`.`advert_budget`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nairondb`.`advert_budget` ;

CREATE TABLE IF NOT EXISTS `nairondb`.`advert_budget` (
  `advert_budgetId` INT(11) NOT NULL,
  `budgetAmount` DOUBLE NULL DEFAULT NULL,
  `budgetBalance` DOUBLE NULL DEFAULT NULL,
  `budgetPerDay` DOUBLE NULL DEFAULT NULL,
  `standingOrder` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`advert_budgetId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nairondb`.`advertiser_wallet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nairondb`.`advertiser_wallet` ;

CREATE TABLE IF NOT EXISTS `nairondb`.`advertiser_wallet` (
  `wallet_id` INT(11) NOT NULL,
  `wallet_balance` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`wallet_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nairondb`.`advertiser`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nairondb`.`advertiser` ;

CREATE TABLE IF NOT EXISTS `nairondb`.`advertiser` (
  `advertiserId` INT(11) NOT NULL AUTO_INCREMENT,
  `advertiser_walletId` INT(11) NOT NULL,
  `naironuserEmail` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`advertiserId`, `naironuserEmail`),
  INDEX `fk_advertiserUser_Awallet1_idx` (`advertiser_walletId` ASC),
  INDEX `fk_advertiserUser_nairon_user1_idx` (`naironuserEmail` ASC),
  CONSTRAINT `fk_advertiserUser_Awallet1`
    FOREIGN KEY (`advertiser_walletId`)
    REFERENCES `nairondb`.`advertiser_wallet` (`wallet_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_advertiserUser_nairon_user1`
    FOREIGN KEY (`naironuserEmail`)
    REFERENCES `nairondb`.`nairon_user` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nairondb`.`advert`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nairondb`.`advert` ;

CREATE TABLE IF NOT EXISTS `nairondb`.`advert` (
  `advertId` INT(11) NOT NULL,
  `advertiserUser` INT(11) NOT NULL,
  `advert_budgetId` INT(11) NOT NULL,
  `advertDuration` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`advertId`),
  INDEX `fk_advert_advertiserUser1_idx` (`advertiserUser` ASC),
  INDEX `fk_advert_advert_budget1_idx` (`advert_budgetId` ASC),
  CONSTRAINT `fk_advert_advert_budget1`
    FOREIGN KEY (`advert_budgetId`)
    REFERENCES `nairondb`.`advert_budget` (`advert_budgetId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_advert_advertiserUser1`
    FOREIGN KEY (`advertiserUser`)
    REFERENCES `nairondb`.`advertiser` (`advertiserId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nairondb`.`advertQuestion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nairondb`.`advertQuestion` ;

CREATE TABLE IF NOT EXISTS `nairondb`.`advertQuestion` (
  `advertQuestionId` INT(11) NOT NULL,
  `advertId` INT(11) NOT NULL,
  `question` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`advertQuestionId`),
  INDEX `fk_advertQuestion_advert1_idx` (`advertId` ASC),
  CONSTRAINT `fk_advertQuestion_advert1`
    FOREIGN KEY (`advertId`)
    REFERENCES `nairondb`.`advert` (`advertId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nairondb`.`advertAnswer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nairondb`.`advertAnswer` ;

CREATE TABLE IF NOT EXISTS `nairondb`.`advertAnswer` (
  `advertAnswerId` INT(11) NOT NULL,
  `advertQuestionId` INT(11) NOT NULL,
  `answer` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`advertAnswerId`),
  UNIQUE INDEX `answer_UNIQUE` (`answer` ASC),
  INDEX `fk_advertAnswer_advertQuestion1_idx` (`advertQuestionId` ASC),
  CONSTRAINT `fk_advertAnswer_advertQuestion1`
    FOREIGN KEY (`advertQuestionId`)
    REFERENCES `nairondb`.`advertQuestion` (`advertQuestionId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nairondb`.`publisher_wallet`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nairondb`.`publisher_wallet` ;

CREATE TABLE IF NOT EXISTS `nairondb`.`publisher_wallet` (
  `walletId` INT(11) NOT NULL,
  `walletBalance` DECIMAL(10,0) NULL DEFAULT NULL,
  PRIMARY KEY (`walletId`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nairondb`.`publisher`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nairondb`.`publisher` ;

CREATE TABLE IF NOT EXISTS `nairondb`.`publisher` (
  `publisherId` INT(11) NOT NULL,
  `publisher_walletId` INT(11) NOT NULL,
  `naironuserEmail` VARCHAR(100) NOT NULL,
  `publishedadvertId` INT(11) NULL DEFAULT NULL,
  `blogUrl` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`publisherId`, `naironuserEmail`),
  INDEX `fk_publisherUser_Pwallet1_idx` (`publisher_walletId` ASC),
  INDEX `fk_publisherUser_nairon_user1_idx` (`naironuserEmail` ASC),
  INDEX `fk_publisher_advert1_idx` (`publishedadvertId` ASC),
  CONSTRAINT `fk_publisherUser_Pwallet1`
    FOREIGN KEY (`publisher_walletId`)
    REFERENCES `nairondb`.`publisher_wallet` (`walletId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_publisherUser_nairon_user1`
    FOREIGN KEY (`naironuserEmail`)
    REFERENCES `nairondb`.`nairon_user` (`email`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_publisher_advert1`
    FOREIGN KEY (`publishedadvertId`)
    REFERENCES `nairondb`.`advert` (`advertId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `nairondb`.`transaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `nairondb`.`transaction` ;

CREATE TABLE IF NOT EXISTS `nairondb`.`transaction` (
  `transactionId` INT(11) NOT NULL,
  `transactionType` VARCHAR(45) NOT NULL,
  `transactionDate` VARCHAR(45) NOT NULL,
  `advertiser_walletId` INT(11) NOT NULL,
  `publisher_walletId` INT(11) NOT NULL,
  PRIMARY KEY (`transactionId`),
  INDEX `fk_transaction_advertiser_wallet1_idx` (`advertiser_walletId` ASC),
  INDEX `fk_transaction_publisher_wallet1_idx` (`publisher_walletId` ASC),
  CONSTRAINT `fk_transaction_advertiser_wallet1`
    FOREIGN KEY (`advertiser_walletId`)
    REFERENCES `nairondb`.`advertiser_wallet` (`wallet_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transaction_publisher_wallet1`
    FOREIGN KEY (`publisher_walletId`)
    REFERENCES `nairondb`.`publisher_wallet` (`walletId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
