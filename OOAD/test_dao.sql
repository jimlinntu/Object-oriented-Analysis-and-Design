-- MySQL dump 10.13  Distrib 5.7.24, for Linux (x86_64)
--
-- Host: localhost    Database: ooad
-- ------------------------------------------------------
-- Server version	5.7.24-0ubuntu0.18.04.1-log

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
-- Current Database: `ooad`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `ooad` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `ooad`;

--
-- Table structure for table `Froms`
--

DROP TABLE IF EXISTS `Froms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Froms` (
  `TrainId` int(11) NOT NULL,
  `Date` date NOT NULL,
  `StationId` int(11) NOT NULL,
  `Time` time NOT NULL,
  PRIMARY KEY (`TrainId`,`Date`,`StationId`),
  CONSTRAINT `Froms_ibfk_1` FOREIGN KEY (`TrainId`, `Date`) REFERENCES `Trains` (`TrainId`, `Date`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Froms`
--

LOCK TABLES `Froms` WRITE;
/*!40000 ALTER TABLE `Froms` DISABLE KEYS */;
INSERT INTO `Froms` VALUES (100,'2019-01-11',0,'00:00:00');
/*!40000 ALTER TABLE `Froms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Orders`
--

DROP TABLE IF EXISTS `Orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Orders` (
  `OrderId` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` varchar(32) NOT NULL,
  PRIMARY KEY (`OrderId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orders`
--

LOCK TABLES `Orders` WRITE;
/*!40000 ALTER TABLE `Orders` DISABLE KEYS */;
INSERT INTO `Orders` VALUES (1,'yao'),(2,'OAO'),(3,'QAO');
/*!40000 ALTER TABLE `Orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Seats`
--

DROP TABLE IF EXISTS `Seats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Seats` (
  `SeatId` varchar(32) NOT NULL,
  `Type` int(11) NOT NULL,
  PRIMARY KEY (`SeatId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Seats`
--

LOCK TABLES `Seats` WRITE;
/*!40000 ALTER TABLE `Seats` DISABLE KEYS */;
INSERT INTO `Seats` VALUES ('0',0),('1',0),('2',0),('3',0),('4',0),('5',0),('6',0),('7',0),('8',0),('9',0);
/*!40000 ALTER TABLE `Seats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tickets`
--

DROP TABLE IF EXISTS `Tickets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Tickets` (
  `OrderId` int(11) NOT NULL,
  `TrainId` int(11) NOT NULL,
  `Date` date NOT NULL,
  `SeatId` varchar(32) NOT NULL,
  `FromStation` int(11) NOT NULL,
  `ToStation` int(11) NOT NULL,
  `Type` int(11) NOT NULL,
  KEY `TrainId` (`TrainId`,`Date`),
  KEY `OrderId` (`OrderId`),
  KEY `SeatId` (`SeatId`),
  CONSTRAINT `Tickets_ibfk_1` FOREIGN KEY (`TrainId`, `Date`) REFERENCES `Trains` (`TrainId`, `Date`),
  CONSTRAINT `Tickets_ibfk_2` FOREIGN KEY (`OrderId`) REFERENCES `Orders` (`OrderId`),
  CONSTRAINT `Tickets_ibfk_3` FOREIGN KEY (`SeatId`) REFERENCES `Seats` (`SeatId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tickets`
--

LOCK TABLES `Tickets` WRITE;
/*!40000 ALTER TABLE `Tickets` DISABLE KEYS */;
INSERT INTO `Tickets` VALUES (1,100,'2019-01-11','0',0,1,0),(1,100,'2019-01-11','1',0,1,0),(2,100,'2019-01-11','2',0,1,0);
/*!40000 ALTER TABLE `Tickets` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Tos`
--

DROP TABLE IF EXISTS `Tos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Tos` (
  `TrainId` int(11) NOT NULL,
  `Date` date NOT NULL,
  `StationId` int(11) NOT NULL,
  `Time` time NOT NULL,
  PRIMARY KEY (`TrainId`,`Date`,`StationId`),
  CONSTRAINT `Tos_ibfk_1` FOREIGN KEY (`TrainId`, `Date`) REFERENCES `Trains` (`TrainId`, `Date`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Tos`
--

LOCK TABLES `Tos` WRITE;
/*!40000 ALTER TABLE `Tos` DISABLE KEYS */;
INSERT INTO `Tos` VALUES (100,'2019-01-11',1,'01:00:00');
/*!40000 ALTER TABLE `Tos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Trains`
--

DROP TABLE IF EXISTS `Trains`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Trains` (
  `TrainId` int(11) NOT NULL,
  `Date` date NOT NULL,
  PRIMARY KEY (`TrainId`,`Date`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Trains`
--

LOCK TABLES `Trains` WRITE;
/*!40000 ALTER TABLE `Trains` DISABLE KEYS */;
INSERT INTO `Trains` VALUES (100,'2019-01-11');
/*!40000 ALTER TABLE `Trains` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-01-12  3:21:13
