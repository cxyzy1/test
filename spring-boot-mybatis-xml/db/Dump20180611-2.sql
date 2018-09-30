-- MySQL dump 10.13  Distrib 8.0.11, for Win64 (x86_64)
--
-- Host: localhost    Database: bookmark-server
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bookmark`
--

DROP TABLE IF EXISTS `bookmark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `bookmark` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(45) DEFAULT NULL,
  `url_md5` varchar(45) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `logo_url` varchar(45) DEFAULT NULL,
  `img_list` varchar(45) DEFAULT NULL,
  `summary` varchar(45) DEFAULT NULL,
  `extension` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookmark`
--

LOCK TABLES `bookmark` WRITE;
/*!40000 ALTER TABLE `bookmark` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookmark` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `obj_id` int(11) DEFAULT NULL,
  `content` varchar(45) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expunged_obj`
--

DROP TABLE IF EXISTS `expunged_obj`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `expunged_obj` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` date DEFAULT NULL,
  `expunged_objcol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expunged_obj`
--

LOCK TABLES `expunged_obj` WRITE;
/*!40000 ALTER TABLE `expunged_obj` DISABLE KEYS */;
/*!40000 ALTER TABLE `expunged_obj` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `machine_info`
--

DROP TABLE IF EXISTS `machine_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `machine_info` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `data_center` smallint(6) DEFAULT NULL,
  `mac` varchar(12) NOT NULL,
  `created_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `mac_UNIQUE` (`mac`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `machine_info`
--

LOCK TABLES `machine_info` WRITE;
/*!40000 ALTER TABLE `machine_info` DISABLE KEYS */;
INSERT INTO `machine_info` VALUES (3,0,'14ABC56679AC','2018-06-11 06:56:17');
/*!40000 ALTER TABLE `machine_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `obj_tag`
--

DROP TABLE IF EXISTS `obj_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `obj_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `obj_id` int(11) DEFAULT NULL,
  `obj_type` varchar(45) DEFAULT NULL,
  `tag_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obj_tag`
--

LOCK TABLES `obj_tag` WRITE;
/*!40000 ALTER TABLE `obj_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `obj_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(45) NOT NULL,
  `full_sync_before` bigint(20) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `updated_time` bigint(20) DEFAULT NULL,
  `created_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='user';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (8,'a123456',NULL,'kms123',NULL,NULL),(9,'123456',NULL,'yin1',NULL,NULL),(10,'123456',NULL,'yin2',NULL,NULL),(11,'123456',NULL,'yin1',NULL,NULL),(12,'123456',NULL,'yin2',NULL,NULL),(13,'123456',NULL,'yin1',NULL,NULL),(14,'123456',NULL,'yin2',NULL,NULL),(15,'123456',NULL,'yin1',NULL,NULL),(16,'123456',NULL,'yin2',NULL,NULL),(17,'123456',NULL,'123456',NULL,NULL),(18,'123456',NULL,'123456',NULL,NULL),(19,'123456',NULL,'123456',NULL,NULL),(20,'123456',NULL,'123456',NULL,NULL),(21,'123456',NULL,'123456',NULL,NULL),(22,'123456',NULL,'123456',NULL,NULL),(23,'123456',NULL,'123456',NULL,NULL),(24,'123456',NULL,'123456',NULL,NULL),(25,'123456',NULL,'123456',NULL,NULL),(26,'123456',NULL,'123456',NULL,NULL),(27,'123456',NULL,'123456',NULL,NULL),(28,'123456',NULL,'123456',NULL,NULL),(29,'123456',NULL,'testUser',NULL,NULL),(30,'123456',NULL,'yin',NULL,NULL),(31,'123456',NULL,'yin',NULL,NULL),(32,'123456',NULL,'testUser',NULL,NULL),(33,'123456',NULL,'testUser',NULL,NULL),(34,'123456',NULL,'testUser',NULL,NULL),(35,'123456',NULL,'testUser',NULL,NULL),(36,'123456',NULL,'yin1',NULL,NULL),(37,'123456',NULL,'yin2',NULL,NULL),(38,'123456',NULL,'yin',NULL,NULL),(39,'123456',NULL,'yin',NULL,NULL),(40,'123456',NULL,'yin',NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_bookmark`
--

DROP TABLE IF EXISTS `user_bookmark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user_bookmark` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `bookmark_id` bigint(20) DEFAULT NULL,
  `is_private` tinyint(4) DEFAULT NULL,
  `bookmark_url` varchar(1000) DEFAULT NULL,
  `bookmark_title` varchar(45) DEFAULT NULL,
  `tag_names` varchar(45) DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `updated_time` bigint(20) DEFAULT NULL,
  `created_time` bigint(20) DEFAULT NULL,
  `progress` smallint(6) DEFAULT NULL,
  `progress_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_bookmark`
--

LOCK TABLES `user_bookmark` WRITE;
/*!40000 ALTER TABLE `user_bookmark` DISABLE KEYS */;
INSERT INTO `user_bookmark` VALUES (8,NULL,NULL,NULL,'http://www.baidu.com','百度一下','',NULL,NULL,NULL,NULL,NULL),(9,NULL,NULL,NULL,'http://www.baidu.com','百度一下','',NULL,NULL,NULL,NULL,NULL),(10,5,NULL,NULL,'http://www.baidu.com','百度一下','',NULL,NULL,NULL,NULL,NULL),(11,7,NULL,NULL,'http://www.baidu.com','百度一下','',NULL,NULL,NULL,NULL,NULL),(12,7,NULL,NULL,'http://www.baidu.com','百度一下','',NULL,NULL,NULL,NULL,NULL),(13,7,NULL,NULL,'http://www.baidu.com','百度一下','',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user_bookmark` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-06-11  7:10:37
