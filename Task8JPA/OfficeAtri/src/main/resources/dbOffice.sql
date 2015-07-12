CREATE SCHEMA `task8`;

CREATE TABLE `task8`.`unit` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,  
  PRIMARY KEY (`id`)
);


CREATE TABLE `task8`.`project` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`)
);
  
CREATE TABLE `task8`.`employee` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,  
  `status` VARCHAR(45) NOT NULL,
  `country` VARCHAR(45) NULL,
  `city` VARCHAR(45) NULL,  
  `street` VARCHAR(45) NULL,
  `number` VARCHAR(45) NULL,
  `unitId` INT(11) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  CONSTRAINT `FK_unit`
    FOREIGN KEY (`unitId`)
    REFERENCES `task8`.`unit` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    INDEX `FK_unit_idx` (`unitId` ASC)
);
	
CREATE TABLE `task8`.`personalInfo` (
  `id` INT NOT NULL,
  `gender` INT NOT NULL DEFAULT 0,
  `birthday` DATE NULL,  
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_Employee`
    FOREIGN KEY (`id`)
    REFERENCES `task8`.`employee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
);
	
CREATE TABLE `task8`.`employee_project` (
  `employeeId` INT NOT NULL,
  `projectId` INT NOT NULL,
  PRIMARY KEY (`employeeId`, `projectId`),
  INDEX `FK_employeeproject_project_idx` (`projectId` ASC),
  CONSTRAINT `FK_employeeproject_employee`
	 FOREIGN KEY (`employeeId`)
	 REFERENCES `task8`.`employee` (`id`)
	 ON DELETE NO ACTION
	 ON UPDATE NO ACTION,
  CONSTRAINT `FK_employeeproject_project`
	 FOREIGN KEY (`projectId`)
	 REFERENCES `task8`.`project` (`id`)
	 ON DELETE NO ACTION
	 ON UPDATE NO ACTION
);


INSERT INTO `task8`.`unit` (`id`, `name`) VALUES ('1', 'unit1');
INSERT INTO `task8`.`unit` (`id`, `name`) VALUES ('2', 'unit2');
INSERT INTO `task8`.`unit` (`id`, `name`) VALUES ('3', 'unit3');

INSERT INTO `task8`.`employee` (`id`, `firstname`, `lastname`, `status`, `country`, `city`, `street`, `number`, `unitId`) VALUES ('1', 'f1', 'l1', 'FULL', 'Belarus', 'Minsk', 'str1', '1', '1');
INSERT INTO `task8`.`employee` (`id`, `firstname`, `lastname`, `status`, `country`, `city`, `street`, `number`, `unitId`) VALUES ('2', 'f2', 'l2', 'PART', 'Belarus', 'Minsk', 'str1', '2', '1');
INSERT INTO `task8`.`employee` (`id`, `firstname`, `lastname`, `status`, `country`, `city`, `street`, `number`, `unitId`) VALUES ('3', 'f3', 'l3', 'FULL', 'Russia', 'Moskow', 'str3', '3', '2');
INSERT INTO `task8`.`employee` (`id`, `firstname`, `lastname`, `status`, `country`, `city`, `street`, `number`, `unitId`) VALUES ('4', 'f4', 'l4', 'FULL', 'Russia', 'StPit', 'str4', '4', '1');

INSERT INTO `task8`.`personalinfo` (`id`, `gender`, `birthday`) VALUES ('1', '1', '1991-01-20');
INSERT INTO `task8`.`personalinfo` (`id`, `gender`, `birthday`) VALUES ('2', '0', '1987-02-01');
INSERT INTO `task8`.`personalinfo` (`id`, `gender`, `birthday`) VALUES ('3', '0', '1990-08-25');
INSERT INTO `task8`.`personalinfo` (`id`, `gender`, `birthday`) VALUES ('4', '1', '1985-06-05');


INSERT INTO `task8`.`project` (`id`, `name`) VALUES ('1', 'project1');
INSERT INTO `task8`.`project` (`id`, `name`) VALUES ('2', 'project2');
INSERT INTO `task8`.`project` (`id`, `name`) VALUES ('3', 'project3');
INSERT INTO `task8`.`project` (`id`, `name`) VALUES ('4', 'project4');
INSERT INTO `task8`.`project` (`id`, `name`) VALUES ('5', 'project5');

INSERT INTO `task8`.`employee_project` (`employeeId`, `projectId`) VALUES ('1', '1');
INSERT INTO `task8`.`employee_project` (`employeeId`, `projectId`) VALUES ('2', '1');
INSERT INTO `task8`.`employee_project` (`employeeId`, `projectId`) VALUES ('3', '2');
INSERT INTO `task8`.`employee_project` (`employeeId`, `projectId`) VALUES ('4', '3');
INSERT INTO `task8`.`employee_project` (`employeeId`, `projectId`) VALUES ('1', '2');
INSERT INTO `task8`.`employee_project` (`employeeId`, `projectId`) VALUES ('3', '4');



