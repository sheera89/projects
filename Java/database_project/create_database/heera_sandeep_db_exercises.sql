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
-- Table structure for table `exercises`
--

DROP TABLE IF EXISTS `exercises`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exercises` (
  `exercise_id` int(11) NOT NULL,
  `exercise_name` varchar(30) DEFAULT NULL,
  `exercise_type` varchar(20) DEFAULT NULL,
  `targeted_muscle_group` varchar(20) DEFAULT NULL,
  `exercise_description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`exercise_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercises`
--

LOCK TABLES `exercises` WRITE;
/*!40000 ALTER TABLE `exercises` DISABLE KEYS */;
INSERT INTO `exercises` (`exercise_id`, `exercise_name`, `exercise_type`, `targeted_muscle_group`, `exercise_description`) VALUES (0,'Bench Press','Weight Lifting','Chest','Press the bar up.'),(14,'Dumbell Curls','Weight Lifting','Arms','Curl the dumbells towards yourself.'),(56,'Squat','Weight Lifting','Legs','Squat down and then back up.'),(78,'Flys','Weight Lifting','Chest','Pull the dumbells together.'),(123,'Lunges','Weight Lifting','Legs','Lean forward and back up.'),(210,'Leg Extensions','Weight Lifting','Legs','Extend your legs outwards.'),(234,'Deadlift','Weight Lifting','Back','Lift the bar from the ground.'),(390,'Barbell Rows','Weight Lifting','Back','Pull the bar towards yourself.'),(548,'Leg Curls','Weight Lifting','Legs','Curl your legs inward.'),(908,'Boxing','Cardio','Heart','Box.'),(2343,'Jogging','Cardio','Heart','Run at a moderate pace.'),(23412,'Barbell Curls','Weight Lifting','Arms','Curl the bar towards yourself.'),(23498,'Sprints','Cardio','Heart','Run as fast as possible.'),(83672,'Triceps Extensions','Weight Lifting','Arms','Press the bar upwards.'),(91819,'Rock Climbing','Cardio','Heart','Climb rocks.'),(99881,'Jumping Jacks','Cardio','Heart','Jump up and down.'),(99999,'Deep Yoga','Cardio','Lungs','Breathe in and breath out.');
/*!40000 ALTER TABLE `exercises` ENABLE KEYS */;
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
