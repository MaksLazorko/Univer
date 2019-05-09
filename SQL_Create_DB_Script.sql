CREATE DATABASE `univer` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `degree`
(
  `id`   int(11)     NOT NULL AUTO_INCREMENT,
  `name` varchar(90) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;

CREATE TABLE `lector`
(
  `id`     int(11)         NOT NULL AUTO_INCREMENT,
  `name`   varchar(90)     NOT NULL,
  `degree` int(11)         NOT NULL,
  `salary` double unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `degree_fk_idx` (`degree`),
  CONSTRAINT `degree_fk` FOREIGN KEY (`degree`) REFERENCES `degree` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;

CREATE TABLE `department`
(
  `id`   int(11)     NOT NULL AUTO_INCREMENT,
  `name` varchar(90) NOT NULL,
  `head` int(11)     NOT NULL,
  PRIMARY KEY (`id`),
  KEY `daepartment_head_fk_idx` (`head`),
  CONSTRAINT `department_head_fk` FOREIGN KEY (`head`) REFERENCES `lector` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8;

CREATE TABLE `lector_department`
(
  `id`         int(11) NOT NULL AUTO_INCREMENT,
  `lector`     int(11) NOT NULL,
  `department` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `lector_fk_idx` (`lector`),
  KEY `department_fk_idx` (`department`),
  CONSTRAINT `department_fk` FOREIGN KEY (`department`) REFERENCES `department` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `lector_fk` FOREIGN KEY (`lector`) REFERENCES `lector` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8;