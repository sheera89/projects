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
-- Table structure for table `workouts`
--

DROP TABLE IF EXISTS `workouts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workouts` (
  `user_id` int(11) NOT NULL,
  `workout_id` int(11) NOT NULL,
  `exercise_id` int(11) NOT NULL,
  `exercise_sets` int(11) DEFAULT NULL,
  `exercise_reps` int(11) DEFAULT NULL,
  `workout_name` varchar(20) DEFAULT NULL,
  `completed_sets` int(11) DEFAULT NULL,
  `completed_reps` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`,`workout_id`,`exercise_id`),
  KEY `exercise_id_idx` (`exercise_id`),
  CONSTRAINT `exercise_id` FOREIGN KEY (`exercise_id`) REFERENCES `exercises` (`exercise_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workouts`
--

LOCK TABLES `workouts` WRITE;
/*!40000 ALTER TABLE `workouts` DISABLE KEYS */;
INSERT INTO `workouts` (`user_id`, `workout_id`, `exercise_id`, `exercise_sets`, `exercise_reps`, `workout_name`, `completed_sets`, `completed_reps`) VALUES (13990,0,0,2,3,'Ironman',1,1),(13990,0,14,4,4,'Ironman',1,1),(113895,0,0,2,3,'Ironman',2,3),(113895,0,14,4,3,'Ironman',1,1),(128201,0,14,4,3,'Ironman',1,1),(128201,1,0,2,3,'Ironman II',2,3),(128201,1,123,1,4,'Ironman II',0,0),(128201,1,23498,1,7,'Ironman II',0,0),(191528,0,0,2,3,'Ironman',2,1),(191528,0,14,4,3,'Ironman',1,1),(655068,0,0,2,3,'Ironman',2,3),(655068,0,14,4,3,'Ironman',1,1),(810391,0,14,4,3,'Ironman',1,1),(810391,0,23498,1,1,'Ironman',0,0),(810391,1,0,2,3,'Ironman II',1,1);
/*!40000 ALTER TABLE `workouts` ENABLE KEYS */;
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
