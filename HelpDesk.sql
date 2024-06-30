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
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `customer_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKcc3mnlwb8b9k2mek5e42bttwu` (`customer_id`),
  CONSTRAINT `FKcc3mnlwb8b9k2mek5e42bttwu` FOREIGN KEY (`customer_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'Project Alpha',6),(2,'Project Beta',7),(3,'Project Gamma',8),(4,'Project Delta',9),(5,'Project Epsilon',10),(6,'Project Test Example',6),(8,'Project Test',10);
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `project_id` bigint DEFAULT NULL,
  `type_id` bigint DEFAULT NULL,
  `approveStatus` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkkcat6aybe3nbvhc54unstxm6` (`project_id`),
  KEY `FKdmpo8tt1lyloysqbfgyaliuwc` (`type_id`),
  CONSTRAINT `FKdmpo8tt1lyloysqbfgyaliuwc` FOREIGN KEY (`type_id`) REFERENCES `tasktype` (`id`),
  CONSTRAINT `FKkkcat6aybe3nbvhc54unstxm6` FOREIGN KEY (`project_id`) REFERENCES `project` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (1,'Simple description for Task 1','COMPLETED','Task 1',1,1,'APPROVED'),(2,'Sample description for task 2','COMPLETED','Task 2',1,1,'APPROVED'),(3,'Simple description for Task 3','COMPLETED','Task 3',6,1,'APPROVED'),(4,'Test description','COMPLETED','Task Example 5',1,2,'APPROVED'),(5,'Release Version v1.0 Bugs','COMPLETED','Release Version v1.0 Bugs',5,1,'APPROVED'),(6,'Release Version v1.1 Bugs','COMPLETED','Release Version v1.1 Bugs',5,1,'APPROVED'),(7,'Release Version v1.0 Bugs','COMPLETED','Release Version v1.0 Bugs',1,2,'APPROVED'),(8,'Release Version v1.1 Bugs','IN_PROGRESS','Release Version v1.1 Bugs',1,3,NULL),(9,'','COMPLETED','Release Version v1.2',6,3,'APPROVED'),(10,'','IN_PROGRESS','Release Version v1.3',6,2,'REJECTED');
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tasktype`
--

DROP TABLE IF EXISTS `tasktype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tasktype` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tasktype`
--

LOCK TABLES `tasktype` WRITE;
/*!40000 ALTER TABLE `tasktype` DISABLE KEYS */;
INSERT INTO `tasktype` VALUES (1,'Bug'),(2,'Enhancement'),(3,'Upgrade');
/*!40000 ALTER TABLE `tasktype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin1@example.com','password1','ADMIN','admin1'),(2,'admin2@example.com','password2','ADMIN','admin2'),(3,'admin3@example.com','password3','ADMIN','admin3'),(4,'admin4@example.com','password4','ADMIN','admin4'),(5,'admin5@example.com','password5','ADMIN','admin5'),(6,'customer1@example.com','password1','CUSTOMER','customer1'),(7,'customer2@example.com','password2','CUSTOMER','customer2'),(8,'customer3@example.com','password3','CUSTOMER','customer3'),(9,'customer4@example.com','password4','CUSTOMER','customer4'),(10,'customer5@example.com','password5','CUSTOMER','customer5'),(11,'employee1@example.com','password1','EMPLOYEE','employee1'),(12,'employee2@example.com','password2','EMPLOYEE','employee2'),(13,'employee3@example.com','password3','EMPLOYEE','employee3'),(14,'employee4@example.com','password4','EMPLOYEE','employee4'),(15,'employee5@example.com','password5','EMPLOYEE','employee5');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `worklog`
--

LOCK TABLES `worklog` WRITE;
/*!40000 ALTER TABLE `worklog` DISABLE KEYS */;
INSERT INTO `worklog` VALUES (1,'Work logged through employee dashboard','2024-06-29 15:03:55.059799',5,11,2),(2,'Work logged through employee dashboard','2024-06-29 15:04:01.525125',10,11,2),(3,'Work logged through employee dashboard','2024-06-29 15:05:57.523587',10,11,2),(4,'Work logged through employee dashboard','2024-06-29 15:06:11.594644',5,11,2),(5,'Work logged through employee dashboard','2024-06-29 21:09:27.547060',23,11,2),(6,'Work logged through employee dashboard','2024-06-29 21:11:59.672602',5,11,2),(7,'Work logged through employee dashboard','2024-06-29 21:13:25.224085',8,11,2),(8,'Work logged through employee dashboard','2024-06-29 21:22:48.433170',5,12,6),(9,'Work logged through employee dashboard','2024-06-29 21:23:05.397622',7,12,6),(10,'Work logged through employee dashboard','2024-06-29 21:26:10.801934',2,13,6),(11,'Work logged through employee dashboard','2024-06-29 21:27:21.650533',10,14,6),(12,'Work logged through employee dashboard','2024-06-29 21:28:01.004604',4,11,6),(13,'Work logged through employee dashboard','2024-06-29 21:29:27.216745',4,11,6),(14,'Work logged through employee dashboard','2024-06-29 21:29:38.383542',3,12,6),(15,'Work logged through employee dashboard','2024-06-30 12:25:18.442041',5,15,7),(16,'Work logged through employee dashboard','2024-06-30 12:25:22.513907',9,15,3),(17,'Work logged through employee dashboard','2024-06-30 19:26:48.393363',10,11,9),(18,'Work logged through employee dashboard','2024-06-30 19:27:14.742148',10,13,10);
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

-- Dump completed on 2024-06-30 19:32:53
