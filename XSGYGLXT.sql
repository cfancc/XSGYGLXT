-- MySQL dump 10.10
--
-- Host: localhost    Database: XSGYGLXT
-- ------------------------------------------------------
-- Server version	5.0.22-community-nt

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
-- Table structure for table `ssxxb`
--

DROP TABLE IF EXISTS `ssxxb`;
CREATE TABLE `ssxxb` (
  `SSID` int(10) NOT NULL auto_increment,
  `SSBH` varchar(50) default NULL,
  `SSYQ` varchar(50) default NULL,
  `SSLD` varchar(50) default NULL,
  `SSLC` varchar(50) default NULL,
  `SSBZ` varchar(100) default NULL,
  `ZDRS` int(5) default NULL,
  `SFYM` varchar(20) default NULL,
  PRIMARY KEY  (`SSID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ssxxb`
--


/*!40000 ALTER TABLE `ssxxb` DISABLE KEYS */;
LOCK TABLES `ssxxb` WRITE;
INSERT INTO `ssxxb` VALUES (1,'101','恬园','1','1','1',4,'否'),(2,'201','恬园','1','2','2',4,'否'),(3,'301','恬园','1','3','3',4,'否'),(4,'101','憬园','1','1','1',4,'否'),(5,'201','憬园','1','2','2',4,'否'),(6,'501','怡园','1','5','5',4,'否');
UNLOCK TABLES;
/*!40000 ALTER TABLE `ssxxb` ENABLE KEYS */;

--
-- Table structure for table `xsssb`
--

DROP TABLE IF EXISTS `xsssb`;
CREATE TABLE `xsssb` (
  `XSSSID` int(16) NOT NULL auto_increment,
  `XSID` int(10) default NULL,
  `SSID` int(10) default NULL,
  PRIMARY KEY  (`XSSSID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `xsssb`
--


/*!40000 ALTER TABLE `xsssb` DISABLE KEYS */;
LOCK TABLES `xsssb` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `xsssb` ENABLE KEYS */;

--
-- Table structure for table `xsxxb`
--

DROP TABLE IF EXISTS `xsxxb`;
CREATE TABLE `xsxxb` (
  `XSID` int(10) NOT NULL auto_increment,
  `XM` varchar(50) default NULL,
  `XH` varchar(50) default NULL,
  `ZY` varchar(50) default NULL,
  `BJ` varchar(50) default NULL,
  `LXFS` varchar(50) default NULL,
  `SFYYSS` varchar(20) default NULL,
  PRIMARY KEY  (`XSID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `xsxxb`
--


/*!40000 ALTER TABLE `xsxxb` DISABLE KEYS */;
LOCK TABLES `xsxxb` WRITE;
INSERT INTO `xsxxb` VALUES (1,'1','1','1','1','1','否'),(2,'2','2','2','2','2','否'),(3,'3','3','3','3','3','否'),(4,'4','4','4','4','4','否'),(5,'5','5','5','5','5','否'),(6,'6','6','6','6','6','否'),(7,'7','7','7','7','7','否'),(8,'8','8','8','8','8','否');
UNLOCK TABLES;
/*!40000 ALTER TABLE `xsxxb` ENABLE KEYS */;

--
-- Table structure for table `yhxxb`
--

DROP TABLE IF EXISTS `yhxxb`;
CREATE TABLE `yhxxb` (
  `YHID` int(10) NOT NULL auto_increment,
  `DLM` varchar(50) default NULL,
  `MM` varchar(50) default NULL,
  `YHLX` varchar(50) default NULL,
  `LXFS` varchar(50) default NULL,
  PRIMARY KEY  (`YHID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `yhxxb`
--


/*!40000 ALTER TABLE `yhxxb` DISABLE KEYS */;
LOCK TABLES `yhxxb` WRITE;
INSERT INTO `yhxxb` VALUES (1,'admin','admin','管理员','18659484567'),(4,'111','111','111111','111');
UNLOCK TABLES;
/*!40000 ALTER TABLE `yhxxb` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

