-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: helpdesk
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `worklog`
--

DROP TABLE IF EXISTS `worklog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `worklog` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `hours` int NOT NULL,
  `employee_id` bigint DEFAULT NULL,
  `task_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdvgf7tgp74oox7vdvgto3j2aa` (`employee_id`),
  KEY `FKk5jq19yhgvjf31mb23topb9i7` (`task_id`),
  CONSTRAINT `FKdvgf7tgp74oox7vdvgto3j2aa` FOREIGN KEY (`employee_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKk5jq19yhgvjf31mb23topb9i7` FOREIGN KEY (`task_id`) REFERENCES `task` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `worklog`
--

LOCK TABLES `worklog` WRITE;
/*!40000 ALTER TABLE `worklog` DISABLE KEYS */;
INSERT INTO `worklog` VALUES (1,'Work logged through employee dashboard','2024-06-29 15:03:55.059799',5,11,2),(2,'Work logged through employee dashboard','2024-06-29 15:04:01.525125',10,11,2),(3,'Work logged through employee dashboard','2024-06-29 15:05:57.523587',10,11,2),(4,'Work logged through employee dashboard','2024-06-29 15:06:11.594644',5,11,2),(5,'Work logged through employee dashboard','2024-06-29 21:09:27.547060',23,11,2),(6,'Work logged through employee dashboard','2024-06-29 21:11:59.672602',5,11,2),(7,'Work logged through employee dashboard','2024-06-29 21:13:25.224085',8,11,2),(8,'Work logged through employee dashboard','2024-06-29 21:22:48.433170',5,12,6),(9,'Work logged through employee dashboard','2024-06-29 21:23:05.397622',7,12,6),(10,'Work logged through employee dashboard','2024-06-29 21:26:10.801934',2,13,6),(11,'Work logged through employee dashboard','2024-06-29 21:27:21.650533',10,14,6),(12,'Work logged through employee dashboard','2024-06-29 21:28:01.004604',4,11,6),(13,'Work logged through employee dashboard','2024-06-29 21:29:27.216745',4,11,6),(14,'Work logged through employee dashboard','2024-06-29 21:29:38.383542',3,12,6);
/*!40000 ALTER TABLE `worklog` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-30  0:09:59
