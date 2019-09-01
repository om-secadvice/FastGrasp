-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema courseportal
-- -----------------------------------------------------
-- Database for Online course portal Application.
DROP SCHEMA IF EXISTS `courseportal` ;

-- -----------------------------------------------------
-- Schema courseportal
--
-- Database for Online course portal Application.
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `courseportal` DEFAULT CHARACTER SET ucs2 COLLATE ucs2_unicode_ci ;
USE `courseportal` ;

-- -----------------------------------------------------
-- Table `courseportal`.`department`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `courseportal`.`department` ;

CREATE TABLE IF NOT EXISTS `courseportal`.`department` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `courseportal`.`student`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `courseportal`.`student` ;

CREATE TABLE IF NOT EXISTS `courseportal`.`student` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `department_roll` VARCHAR(45) NOT NULL,
  `university_roll` VARCHAR(45) NOT NULL,
  `contact` VARCHAR(13) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `date_created` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `password` TEXT(600) NOT NULL,
  `department_id` INT UNSIGNED NULL,
  `semester` INT(1) UNSIGNED NOT NULL,
  `lateral_entry` TINYINT(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_student_department1_idx` (`department_id` ASC),
  CONSTRAINT `fk_student_department1`
    FOREIGN KEY (`department_id`)
    REFERENCES `courseportal`.`department` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Stores student information.';


-- -----------------------------------------------------
-- Table `courseportal`.`faculty`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `courseportal`.`faculty` ;

CREATE TABLE IF NOT EXISTS `courseportal`.`faculty` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `designation` VARCHAR(45) NOT NULL,
  `institution` VARCHAR(45) NOT NULL,
  `contact` VARCHAR(13) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `date_created` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `password` TEXT(600) NOT NULL,
  `department_id` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  INDEX `fk_faculty_department1_idx` (`department_id` ASC),
  CONSTRAINT `fk_faculty_department1`
    FOREIGN KEY (`department_id`)
    REFERENCES `courseportal`.`department` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Stores faculty information.';


-- -----------------------------------------------------
-- Table `courseportal`.`administrator`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `courseportal`.`administrator` ;

CREATE TABLE IF NOT EXISTS `courseportal`.`administrator` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `contact` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `date_created` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `password` TEXT(600) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB
COMMENT = 'Stores administrator information.';


-- -----------------------------------------------------
-- Table `courseportal`.`course`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `courseportal`.`course` ;

CREATE TABLE IF NOT EXISTS `courseportal`.`course` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `description` TEXT NOT NULL,
  `date_created` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `start_date` DATE NOT NULL,
  `end_date` DATE NOT NULL,
  `department_id` INT UNSIGNED NOT NULL,
  `number_of_hours` INT(2) UNSIGNED NOT NULL,
  `number_of_test` INT(1) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_course_department1_idx` (`department_id` ASC),
  CONSTRAINT `fk_course_department1`
    FOREIGN KEY (`department_id`)
    REFERENCES `courseportal`.`department` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Stores course information.';


-- -----------------------------------------------------
-- Table `courseportal`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `courseportal`.`user` ;

CREATE TABLE IF NOT EXISTS `courseportal`.`user` (
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`type`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `courseportal`.`issue`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `courseportal`.`issue` ;

CREATE TABLE IF NOT EXISTS `courseportal`.`issue` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `content` TEXT(500) NOT NULL,
  `resolved` TINYINT(1) NULL,
  `course_id` INT UNSIGNED NOT NULL,
  `reporter_id` INT UNSIGNED NOT NULL,
  `reporter_name` VARCHAR(45) NOT NULL,
  `user_type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_issue_course1_idx` (`course_id` ASC),
  INDEX `fk_issue_user1_idx` (`user_type` ASC),
  CONSTRAINT `fk_issue_course1`
    FOREIGN KEY (`course_id`)
    REFERENCES `courseportal`.`course` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_issue_user1`
    FOREIGN KEY (`user_type`)
    REFERENCES `courseportal`.`user` (`type`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Stores issue raised by members either faculty or students.';


-- -----------------------------------------------------
-- Table `courseportal`.`feedback`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `courseportal`.`feedback` ;

CREATE TABLE IF NOT EXISTS `courseportal`.`feedback` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `content` TEXT NOT NULL,
  `date_created` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `feedback_point` INT(1) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = 'Stores feedback from student or faculty.';


-- -----------------------------------------------------
-- Table `courseportal`.`course_content`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `courseportal`.`course_content` ;

CREATE TABLE IF NOT EXISTS `courseportal`.`course_content` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `link` VARCHAR(255) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `course_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_course_content_course1_idx` (`course_id` ASC),
  CONSTRAINT `fk_course_content_course1`
    FOREIGN KEY (`course_id`)
    REFERENCES `courseportal`.`course` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Stores course content links and other information.';


-- -----------------------------------------------------
-- Table `courseportal`.`announcement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `courseportal`.`announcement` ;

CREATE TABLE IF NOT EXISTS `courseportal`.`announcement` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `content` MEDIUMTEXT NOT NULL,
  `date_created` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `course_id` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_announcement_course1_idx` (`course_id` ASC),
  CONSTRAINT `fk_announcement_course1`
    FOREIGN KEY (`course_id`)
    REFERENCES `courseportal`.`course` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Stores announcement';


-- -----------------------------------------------------
-- Table `courseportal`.`test`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `courseportal`.`test` ;

CREATE TABLE IF NOT EXISTS `courseportal`.`test` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `course_id` INT UNSIGNED NOT NULL,
  `full_marks` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_test_course1_idx` (`course_id` ASC),
  CONSTRAINT `fk_test_course1`
    FOREIGN KEY (`course_id`)
    REFERENCES `courseportal`.`course` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `courseportal`.`test_question`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `courseportal`.`test_question` ;

CREATE TABLE IF NOT EXISTS `courseportal`.`test_question` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `question` TEXT NOT NULL,
  `test_id` INT UNSIGNED NOT NULL,
  `choice_one` TEXT NOT NULL,
  `choice_two` TEXT NOT NULL,
  `choice_three` TEXT NOT NULL,
  `choice_four` TEXT NOT NULL,
  `correct_choice` TINYINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_test_question_test_idx` (`test_id` ASC),
  CONSTRAINT `fk_test_question_test`
    FOREIGN KEY (`test_id`)
    REFERENCES `courseportal`.`test` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
COMMENT = 'Stores question and answers';


-- -----------------------------------------------------
-- Table `courseportal`.`course_faculty`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `courseportal`.`course_faculty` ;

CREATE TABLE IF NOT EXISTS `courseportal`.`course_faculty` (
  `course_id` INT UNSIGNED NOT NULL,
  `faculty_id` INT UNSIGNED NOT NULL,
  `approval` TINYINT(1) NULL DEFAULT 0 COMMENT 'Whether admin has approved this faculty for joining a particular course.',
  `feedback_id` INT UNSIGNED NULL,
  PRIMARY KEY (`course_id`, `faculty_id`),
  INDEX `fk_course_has_faculty_faculty1_idx` (`faculty_id` ASC),
  INDEX `fk_course_has_faculty_course1_idx` (`course_id` ASC),
  INDEX `fk_course_faculty_feedback1_idx` (`feedback_id` ASC),
  CONSTRAINT `fk_course_has_faculty_course1`
    FOREIGN KEY (`course_id`)
    REFERENCES `courseportal`.`course` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_course_has_faculty_faculty1`
    FOREIGN KEY (`faculty_id`)
    REFERENCES `courseportal`.`faculty` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_course_faculty_feedback1`
    FOREIGN KEY (`feedback_id`)
    REFERENCES `courseportal`.`feedback` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `courseportal`.`discussion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `courseportal`.`discussion` ;

CREATE TABLE IF NOT EXISTS `courseportal`.`discussion` (
  `id` INT NOT NULL,
  `comment` TEXT NOT NULL,
  `date_created` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `parent_comment_id` INT NULL DEFAULT 0,
  `course_id` INT UNSIGNED NOT NULL,
  `author` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_discussion_course1_idx` (`course_id` ASC),
  CONSTRAINT `fk_discussion_course1`
    FOREIGN KEY (`course_id`)
    REFERENCES `courseportal`.`course` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `courseportal`.`student_certificate`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `courseportal`.`student_certificate` ;

CREATE TABLE IF NOT EXISTS `courseportal`.`student_certificate` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `course_title` VARCHAR(45) NOT NULL,
  `final_result` DECIMAL(4,2) NOT NULL,
  `student_id` INT UNSIGNED NOT NULL,
  `date_of_joining` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `number_of_hours` INT UNSIGNED NOT NULL,
  `feedback_id` INT UNSIGNED NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_student_joined_course_student1_idx` (`student_id` ASC),
  INDEX `fk_student_certificate_feedback1_idx` (`feedback_id` ASC),
  CONSTRAINT `fk_student_joined_course_student1`
    FOREIGN KEY (`student_id`)
    REFERENCES `courseportal`.`student` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_student_certificate_feedback1`
    FOREIGN KEY (`feedback_id`)
    REFERENCES `courseportal`.`feedback` (`id`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `courseportal`.`student_certification_course`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `courseportal`.`student_certification_course` ;

CREATE TABLE IF NOT EXISTS `courseportal`.`student_certification_course` (
  `student_joined_course_id` INT UNSIGNED NOT NULL,
  `course_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`student_joined_course_id`, `course_id`),
  INDEX `fk_student_joined_course_has_course_course1_idx` (`course_id` ASC),
  INDEX `fk_student_joined_course_has_course_student_joined_course1_idx` (`student_joined_course_id` ASC),
  CONSTRAINT `fk_student_joined_course_has_course_student_joined_course1`
    FOREIGN KEY (`student_joined_course_id`)
    REFERENCES `courseportal`.`student_certificate` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_student_joined_course_has_course_course1`
    FOREIGN KEY (`course_id`)
    REFERENCES `courseportal`.`course` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `courseportal`.`student_test_response`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `courseportal`.`student_test_response` ;

CREATE TABLE IF NOT EXISTS `courseportal`.`student_test_response` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `response` INT(1) UNSIGNED NOT NULL,
  `test_question_id` INT UNSIGNED NOT NULL,
  `student_certificate_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_student_test_response_test_question1_idx` (`test_question_id` ASC),
  INDEX `fk_student_test_response_student_certificate1_idx` (`student_certificate_id` ASC),
  CONSTRAINT `fk_student_test_response_test_question1`
    FOREIGN KEY (`test_question_id`)
    REFERENCES `courseportal`.`test_question` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_student_test_response_student_certificate1`
    FOREIGN KEY (`student_certificate_id`)
    REFERENCES `courseportal`.`student_certificate` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
