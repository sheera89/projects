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
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `food` (
  `food_id` int(11) NOT NULL,
  `food_name` varchar(20) DEFAULT NULL,
  `food_category` varchar(20) DEFAULT NULL,
  `calories` int(11) DEFAULT NULL,
  PRIMARY KEY (`food_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` (`food_id`, `food_name`, `food_category`, `calories`) VALUES (0,'Chicken Sandwich','Sandwiches',100),(5,'Banana','Fruits',150),(25,'Cheeseburger','Sandwiches',450),(48,'Beef','Burritos',550),(211,'Orange','Fruits',100),(290,'Garlic Bread','Grains',250),(363,'Cheese','Pizza',300),(587,'Fruit Salad','Salads',250),(687,'Pepperoni','Pizza',350),(989,'T-Bone Steak','Beef',700),(4854,'Grilled Cheese','Sandwiches',500),(7878,'Apple','Fruits',100),(8768,'Stir Fry','Vegetables',350),(19289,'Fried Chicken','Chicken',700),(21390,'Caesar Salad','Salads',250),(78254,'Chicken','Burritos',550),(78785,'Baked Potato','Vegetables',300),(78799,'Fish Fillet','Fish',450),(89324,'Vegetarian','Burritos',400),(98752,'Rice','Grains',250);
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-05 21:51:30
