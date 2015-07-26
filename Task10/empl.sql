CREATE SCHEMA `employee10`;

CREATE TABLE `employee10`.`employee` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `gender` VARCHAR(5) NOT NULL,
  `position` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`id`));
  
INSERT INTO `employee10`.`employee` (`name`, `gender`, `position`) VALUES ('name1', 'man', 'pos1');
INSERT INTO `employee10`.`employee` (`name`, `gender`, `position`) VALUES ('name2', 'woman', 'pos2');
INSERT INTO `employee10`.`employee` (`name`, `gender`, `position`) VALUES ('name3', 'man', 'pos3');
INSERT INTO `employee10`.`employee` (`name`, `gender`, `position`) VALUES ('name4', 'woman', 'pos4');

