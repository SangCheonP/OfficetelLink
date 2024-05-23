-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: ssafydb
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `notices`
--

DROP TABLE IF EXISTS `notices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notices` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_email` varchar(50) DEFAULT NULL,
  `title` varchar(45) NOT NULL,
  `content` text,
  `views` int DEFAULT '0',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL,
  `is_pinned` tinyint(1) DEFAULT NULL,
  `is_like` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`user_email`),
  CONSTRAINT `fk_notices_user_email_users_email` FOREIGN KEY (`user_email`) REFERENCES `users` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notices`
--

LOCK TABLES `notices` WRITE;
/*!40000 ALTER TABLE `notices` DISABLE KEYS */;
INSERT INTO `notices` VALUES (1,'pswlove38@naver.com','안녕','하세요',6,'2024-05-20 04:38:33','2024-05-20 04:38:33',NULL,NULL),(2,'pswlove38@naver.com','안녕','하세요하세요하세요하세요',4,'2024-05-20 13:21:04','2024-05-20 13:21:04',NULL,NULL),(3,'pswlove38@naver.com','안녕','하세요하세요하세요하세요1232123123',10,'2024-05-20 13:21:04','2024-05-20 13:21:04',NULL,NULL),(4,'pswlove38@naver.com','안녕','하세요하세요하세요하세요',2,'2024-05-20 13:21:04','2024-05-20 13:21:04',NULL,NULL),(5,'pswlove38@naver.com','안녕','하세요하세요하세요하세요',4,'2024-05-20 13:21:04','2024-05-20 13:21:04',NULL,NULL),(6,'pswlove38@naver.com','안녕','하세요하세요하세요하세요',1,'2024-05-20 13:21:04','2024-05-20 13:21:04',NULL,NULL),(7,'pswlove38@naver.com','안녕','하세요하세요하세요하세요',2,'2024-05-20 15:50:43','2024-05-20 15:50:43',NULL,NULL),(8,'pswlove38@naver.com','안녕','하세요하세요하세요하세요',0,'2024-05-20 15:50:43','2024-05-20 15:50:43',NULL,NULL),(9,'pswlove38@naver.com','안녕','하세요하세요하세요하세요',0,'2024-05-20 18:08:34','2024-05-20 18:08:34',NULL,NULL),(10,'pswlove38@naver.com','안녕','하세요하세요하세요하세요',0,'2024-05-20 18:08:34','2024-05-20 18:08:34',NULL,NULL),(11,'pswlove38@naver.com','안녕','하세요하세요하세요하세요',0,'2024-05-20 18:08:34','2024-05-20 18:08:34',NULL,NULL),(12,'pswlove38@naver.com','안녕','하세요하세요하세요하세요',0,'2024-05-20 18:08:44','2024-05-20 18:08:44',NULL,NULL),(13,'pswlove38@naver.com','안녕','하세요하세요하세요하세요',0,'2024-05-20 18:08:44','2024-05-20 18:08:44',NULL,NULL),(14,'pswlove38@naver.com','안녕','하세요하세요하세요하세요',0,'2024-05-20 18:08:44','2024-05-20 18:08:44',NULL,NULL),(15,'admin@naver.com','중요 공지사항','하세요하세요하세요하세요',18,'2024-05-21 04:38:42','2024-05-21 04:38:42',NULL,NULL),(17,NULL,'3213','123',1,'2024-05-20 17:10:17','2024-05-20 17:10:17',NULL,NULL),(18,NULL,'312','312',1,'2024-05-21 03:37:28','2024-05-21 03:37:28',NULL,NULL),(19,NULL,'3','3',0,'2024-05-21 03:37:50','2024-05-21 03:37:50',NULL,NULL),(23,NULL,'3','3',0,'2024-05-21 04:02:20','2024-05-21 04:02:20',NULL,NULL),(24,NULL,'3','3',0,'2024-05-21 04:02:21','2024-05-21 04:02:21',NULL,NULL),(25,NULL,'ㄴ','ㄴ',1,'2024-05-21 04:04:25','2024-05-21 04:04:25',NULL,NULL),(26,NULL,'아이디','아이디',2,'2024-05-21 04:07:08','2024-05-21 04:07:08',NULL,NULL),(27,NULL,'ㄷ2ㄷ2','ㄷ2ㄷ2',0,'2024-05-21 04:08:01','2024-05-21 04:08:01',NULL,NULL),(28,NULL,'1232','1232',1,'2024-05-21 04:09:04','2024-05-21 04:09:04',NULL,NULL),(29,NULL,'ㅁㄴ','ㅁㄴ',0,'2024-05-21 04:09:43','2024-05-21 04:09:43',NULL,NULL),(30,NULL,'ㄴ','ㄴ',1,'2024-05-21 04:11:20','2024-05-21 04:11:20',NULL,NULL),(31,NULL,'ㄴ','ㄴ',1,'2024-05-21 04:15:34','2024-05-21 04:15:34',NULL,NULL),(32,NULL,'1232','1232',0,'2024-05-21 04:19:40','2024-05-21 04:19:40',NULL,NULL),(33,NULL,'213','213',0,'2024-05-21 04:21:05','2024-05-21 04:21:05',NULL,NULL),(34,NULL,'ㄻㄴㅇㄹㅇㄴㄹ','ㄻㄴㅇㄹㅇㄴㄹ',2,'2024-05-21 04:22:15','2024-05-21 04:22:15',NULL,NULL),(35,NULL,'12312','12312',0,'2024-05-21 04:22:52','2024-05-21 04:22:52',NULL,NULL),(36,NULL,'23','23',3,'2024-05-21 04:29:48','2024-05-21 04:29:48',NULL,NULL),(37,'pswlove38@naver.com','ㄻㄴㅇㄹㅇㄴ','ㄻㄴㅇㄹㅇㄴ',14,'2024-05-21 04:34:55','2024-05-21 04:34:55',NULL,NULL),(38,'pswlove38@naver.com','1232','22',15,'2024-05-21 04:37:14','2024-05-21 04:37:14',NULL,NULL),(39,'admin@naver.com','중요 공지사항 2','ㄴㄴㄴ',34,'2024-05-21 07:37:38','2024-05-21 07:37:38',NULL,NULL),(41,'admin@naver.com','1','2',38,'2024-05-21 08:13:39','2024-05-21 08:13:39',0,8),(42,'pswlove38@naver.com','12323','231',14,'2024-05-21 08:26:05','2024-05-21 08:26:05',0,1),(62,'pswlove38@naver.com','3','<p>232</p>',1,'2024-05-22 02:26:24','2024-05-22 02:26:24',0,0),(63,'pswlove38@naver.com','22','<p>323123</p>',1,'2024-05-22 02:35:29','2024-05-22 02:35:29',0,0),(64,'pswlove38@naver.com','ㅇㄹㅇ','<p>ㄴㅇ</p>',2,'2024-05-22 02:36:31','2024-05-22 02:36:31',0,0),(66,'pswlove38@naver.com','123','<p>23213</p>',4,'2024-05-22 02:53:31','2024-05-22 02:53:31',0,0),(67,'pswlove38@naver.com','23','<p>312</p>',0,'2024-05-22 04:21:44','2024-05-22 04:21:44',0,0),(68,'pswlove38@naver.com','ㅁㄴㅇ','<p>ㄴㄴㅇ</p>',0,'2024-05-22 04:23:20','2024-05-22 04:23:20',0,0),(69,'pswlove38@naver.com','ㅇ','<p>ㄴㅇ</p>',1,'2024-05-22 04:26:08','2024-05-22 04:26:08',0,0),(70,'pswlove38@naver.com','ㅇ','<p>ㅇ</p>',0,'2024-05-22 04:31:49','2024-05-22 04:31:49',0,0),(71,'pswlove38@naver.com','2','<p>3</p>',2,'2024-05-22 04:37:49','2024-05-22 04:37:49',0,0),(72,'pswlove38@naver.com','22','<p>22</p>',12,'2024-05-22 04:42:51','2024-05-22 04:42:51',0,2),(73,'pswlove38@naver.com','3','<p>33</p>',3,'2024-05-22 04:56:28','2024-05-22 04:56:28',0,0),(74,'pswlove38@naver.com','2321','<p>2312</p>',0,'2024-05-22 05:09:02','2024-05-22 05:09:02',0,0),(75,'pswlove38@naver.com','2321','<p>2312</p>',1,'2024-05-22 05:09:09','2024-05-22 05:09:09',0,0),(78,'pswlove38@naver.com','11','<p>1</p>',6,'2024-05-22 05:43:42','2024-05-22 05:43:42',0,0),(79,'pswlove38@naver.com','212','<p>3213</p>',12,'2024-05-22 05:51:50','2024-05-22 05:51:50',0,0),(83,'pswlove38@naver.com','1','<p>23222</p>',36,'2024-05-22 07:01:19','2024-05-22 07:01:19',0,0);
/*!40000 ALTER TABLE `notices` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-22 17:37:41
