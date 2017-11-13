CREATE DATABASE  IF NOT EXISTS `heera_sandeep_db` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `heera_sandeep_db`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: heera_sandeep_db
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `drinks_consumed`
--

DROP TABLE IF EXISTS `drinks_consumed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `drinks_consumed` (
  `user_id` int(11) NOT NULL,
  `workout_id` int(11) NOT NULL,
  `drink_id` int(11) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`workout_id`,`drink_id`),
  KEY `widd_idx` (`workout_id`),
  KEY `did_idx` (`drink_id`),
  CONSTRAINT `drink_id` FOREIGN KEY (`drink_id`) REFERENCES `drinks` (`drink_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `workout_id_90` FOREIGN KEY (`user_id`, `workout_id`) REFERENCES `workouts` (`user_id`, `workout_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drinks_consumed`
--

LOCK TABLES `drinks_consumed` WRITE;
/*!40000 ALTER TABLE `drinks_consumed` DISABLE KEYS */;
INSERT INTO `drinks_consumed` (`user_id`, `workout_id`, `drink_id`, `quantity`) VALUES (13990,0,23,5),(128201,0,0,1),(128201,0,12,1),(191528,0,899,10);
/*!40000 ALTER TABLE `drinks_consumed` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-05 21:51:31
