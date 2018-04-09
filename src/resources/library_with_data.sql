-- MySQL dump 10.16  Distrib 10.2.13-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	10.2.13-MariaDB

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
-- Table structure for table `authors`
--

DROP TABLE IF EXISTS `authors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authors` (
  `AuthorID` int(11) NOT NULL AUTO_INCREMENT,
  `AuthorName` varchar(50) NOT NULL,
  PRIMARY KEY (`AuthorID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='Book writers';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authors`
--

LOCK TABLES `authors` WRITE;
/*!40000 ALTER TABLE `authors` DISABLE KEYS */;
INSERT INTO `authors` VALUES (1,'Miguel de Cervantes'),(2,'Eduardo Mendoza'),(3,'J.R.R. Tolkien'),(4,'Pedro Calderón de la Barca'),(8,'Francisco Ibañez');
/*!40000 ALTER TABLE `authors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `books` (
  `BookID` int(11) NOT NULL AUTO_INCREMENT,
  `BookTitle` varchar(255) NOT NULL,
  `AuthorID` int(11) NOT NULL,
  `Lent` tinyint(4) DEFAULT 0,
  PRIMARY KEY (`BookID`),
  KEY `books_authors_fk` (`AuthorID`),
  CONSTRAINT `books_authors_fk` FOREIGN KEY (`AuthorID`) REFERENCES `authors` (`AuthorID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='Store book details';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'El ingenioso hidalgo don Quijote de la Mancha',1,1),(2,'Sin noticias de Gurb',2,1),(3,'La verdad sobre el caso Savolta',2,1),(4,'El misterio de la cripta embrujada',2,0),(5,'La aventura del tocador de señoras',2,0),(6,'La vida es sueño',4,0),(7,'El señor de los anillos: La comunidad del anillo',3,0),(8,'El señor de los anillos: Las dos torres',3,0),(9,'El señor de los anillos: El retorno del rey',3,0),(10,'El Hobbit',3,0),(11,'Novelas ejemplares',1,0),(12,'La galatea',1,0),(13,'El alcalde de Zalamea',4,0);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loans`
--

DROP TABLE IF EXISTS `loans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loans` (
  `BookID` int(11) NOT NULL,
  `UserID` int(11) NOT NULL,
  `LoanStartDate` date NOT NULL,
  `LoanEndDate` date NOT NULL,
  PRIMARY KEY (`BookID`,`UserID`),
  KEY `loans_users_fk` (`UserID`),
  CONSTRAINT `loans_books_fk` FOREIGN KEY (`BookID`) REFERENCES `books` (`BookID`),
  CONSTRAINT `loans_users_fk` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Book loans. Link between Books and Users.';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loans`
--

LOCK TABLES `loans` WRITE;
/*!40000 ALTER TABLE `loans` DISABLE KEYS */;
INSERT INTO `loans` VALUES (1,1,'2018-03-05','2018-04-05'),(2,1,'2018-03-06','2018-04-06'),(3,2,'2018-03-12','2018-04-12');
/*!40000 ALTER TABLE `loans` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `UserID` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(50) NOT NULL,
  `BorrowedBooks` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='Library users';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Peter Parker',2),(2,'Bruce Banner',1),(3,'Bruce Wayne',0),(4,'Clark Kent',0),(5,'Tony Stark',0),(6,'Mortadelo',0);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'library'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-19 18:46:14
