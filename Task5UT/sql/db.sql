CREATE TABLE IF NOT EXISTS `task5`.`Person` (
  `idPerson` INT NOT NULL AUTO_INCREMENT,
  `firstname` VARCHAR(45) NOT NULL,
  `lastname` VARCHAR(45) NOT NULL,
  `birthday` DATE NULL,
  PRIMARY KEY (`idPerson`),
  INDEX `idPerson` (`idPerson` ASC));
  
  
  CREATE TABLE IF NOT EXISTS `task5`.`Bank` (
  `idBank` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `street` VARCHAR(45) NOT NULL,
  `number` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NULL,
  PRIMARY KEY (`idBank`));
  
  CREATE TABLE IF NOT EXISTS `task5`.`CurrencyRatio` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `idBank` INT NOT NULL,
  `initial` VARCHAR(5) NOT NULL,
  `result` VARCHAR(5) NOT NULL,
  `ratio` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `idBank_idx` (`idBank` ASC),
  CONSTRAINT `idBank`
    FOREIGN KEY (`idBank`)
    REFERENCES `task5`.`Bank` (`idBank`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
CREATE TABLE IF NOT EXISTS `task5`.`Account` (
  `idAccount` INT NOT NULL AUTO_INCREMENT,
  `idBank` INT NOT NULL,
  `idPerson` INT NOT NULL,
  `currencyCode` VARCHAR(5) NOT NULL,
  `value` DOUBLE NOT NULL DEFAULT 0,
  PRIMARY KEY (`idAccount`),
  INDEX `idBank_idx` (`idBank` ASC),
  INDEX `idPerson_idx` (`idPerson` ASC),
  CONSTRAINT `idAccountBank`
    FOREIGN KEY (`idBank`)
    REFERENCES `task5`.`Bank` (`idBank`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idAccountPerson`
    FOREIGN KEY (`idPerson`)
    REFERENCES `task5`.`Person` (`idPerson`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);




INSERT INTO `task5`.`person` (`firstname`, `lastname`, `birthday`) VALUES ('name1', 'last1', '1980-05-01');
INSERT INTO `task5`.`person` (`firstname`, `lastname`, `birthday`) VALUES ('name2', 'last2', '1991-06-12');
INSERT INTO `task5`.`person` (`firstname`, `lastname`, `birthday`) VALUES ('name3', 'last3', '1986-10-25');

INSERT INTO `task5`.`bank` (`name`, `street`, `number`, `phone`) VALUES ('bank1', 'street1', '1', '11-11-11');
INSERT INTO `task5`.`bank` (`name`, `street`, `number`, `phone`) VALUES ('bank2', 'street2', '2', '22-22-22');

INSERT INTO `task5`.`currencyratio` (`idBank`, `initial`, `result`, `ratio`) VALUES ('1', 'BYR', 'USD', '15300');
INSERT INTO `task5`.`currencyratio` (`idBank`, `initial`, `result`, `ratio`) VALUES ('1', 'USD', 'BYR', '0.00006535');
INSERT INTO `task5`.`currencyratio` (`idBank`, `initial`, `result`, `ratio`) VALUES ('1', 'USD', 'EUR', '0.8834');
INSERT INTO `task5`.`currencyratio` (`idBank`, `initial`, `result`, `ratio`) VALUES ('1', 'EUR', 'USD', '1.132');
INSERT INTO `task5`.`currencyratio` (`idBank`, `initial`, `result`, `ratio`) VALUES ('1', 'RUR', 'EUR', '0.161');
INSERT INTO `task5`.`currencyratio` (`idBank`, `initial`, `result`, `ratio`) VALUES ('1', 'EUR', 'RUR', '61.8571');
INSERT INTO `task5`.`currencyratio` (`idBank`, `initial`, `result`, `ratio`) VALUES ('2', 'BYR', 'USD', '15500');
INSERT INTO `task5`.`currencyratio` (`idBank`, `initial`, `result`, `ratio`) VALUES ('2', 'USD', 'BYR', '0.00006451');
INSERT INTO `task5`.`currencyratio` (`idBank`, `initial`, `result`, `ratio`) VALUES ('2', 'USD', 'EUR', '0.797');
INSERT INTO `task5`.`currencyratio` (`idBank`, `initial`, `result`, `ratio`) VALUES ('2', 'EUR', 'USD', '1.254');
INSERT INTO `task5`.`currencyratio` (`idBank`, `initial`, `result`, `ratio`) VALUES ('2', 'RUR', 'EUR', '0.168');
INSERT INTO `task5`.`currencyratio` (`idBank`, `initial`, `result`, `ratio`) VALUES ('2', 'EUR', 'RUR', '59.25');


INSERT INTO `task5`.`account` (`idBank`, `idPerson`, `currencyCode`, `value`) VALUES ('1', '1', 'BYR', '25000000');
INSERT INTO `task5`.`account` (`idBank`, `idPerson`, `currencyCode`, `value`) VALUES ('1', '2', 'USD', '1500');
INSERT INTO `task5`.`account` (`idBank`, `idPerson`, `currencyCode`, `value`) VALUES ('1', '3', 'EUR', '900');
INSERT INTO `task5`.`account` (`idBank`, `idPerson`, `currencyCode`, `value`) VALUES ('2', '1', 'RUR', '70000');
INSERT INTO `task5`.`account` (`idBank`, `idPerson`, `currencyCode`, `value`) VALUES ('2', '2', 'BYR', '31000000');
INSERT INTO `task5`.`account` (`idBank`, `idPerson`, `currencyCode`, `value`) VALUES ('2', '3', 'USD', '850');



