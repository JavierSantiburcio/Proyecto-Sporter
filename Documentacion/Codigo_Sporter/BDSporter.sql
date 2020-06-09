CREATE DATABASE  IF NOT EXISTS `spoter` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `spoter`;
-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: spoter
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `deporte`
--

DROP TABLE IF EXISTS `deporte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `deporte` (
  `idDeporte` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `numParticipantes` int NOT NULL,
  PRIMARY KEY (`idDeporte`),
  UNIQUE KEY `idDeporte_UNIQUE` (`idDeporte`),
  UNIQUE KEY `nombre_UNIQUE` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `deporte`
--

LOCK TABLES `deporte` WRITE;
/*!40000 ALTER TABLE `deporte` DISABLE KEYS */;
INSERT INTO `deporte` VALUES (1,'Futbol Sala',10),(2,'Futbol 11',22),(3,'Futbol 7',14),(4,'Tenis Individual',2),(5,'Tenis Pareja',4),(6,'Padel individual',2),(7,'Padel Pareja',4),(8,'Badminton Individual',2),(9,'Badminton Pareja',4),(10,'Pin Pon Individual',2),(11,'Pin Pon Pareja',4),(12,'Pelota Vasca Individual	',2),(13,'Pelota Vasca Pareja',4),(14,'Voleibol ',12),(15,'Hockey Hielo',12),(16,'Hockey Hierba',22),(17,'Hockey Patines',10),(18,'Baloncesto',10),(19,'Balonmano',14),(20,'Ajedrez',2),(21,'Petanca',4),(22,'Waterpolo',14);
/*!40000 ALTER TABLE `deporte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evento`
--

DROP TABLE IF EXISTS `evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evento` (
  `id_Evento` int NOT NULL AUTO_INCREMENT,
  `ubicacion` varchar(50) NOT NULL,
  `numParticipantesAct` int NOT NULL,
  `fecha` datetime NOT NULL,
  `Creador` int NOT NULL,
  `Deporte` int NOT NULL,
  PRIMARY KEY (`id_Evento`),
  UNIQUE KEY `id_Evento_UNIQUE` (`id_Evento`),
  KEY `fk_evento_usuarios1_idx` (`Creador`),
  KEY `fk_evento_deporte1_idx` (`Deporte`),
  CONSTRAINT `creador` FOREIGN KEY (`Creador`) REFERENCES `usuarios` (`idUsuarios`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_evento_deporte1` FOREIGN KEY (`Deporte`) REFERENCES `deporte` (`idDeporte`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evento`
--

LOCK TABLES `evento` WRITE;
/*!40000 ALTER TABLE `evento` DISABLE KEYS */;
INSERT INTO `evento` VALUES (3,'Málaga',4,'2020-06-12 12:00:00',3,7),(5,'Málaga',10,'2020-06-25 18:00:00',2,18),(6,'Málaga',14,'2020-06-10 10:00:00',2,19),(7,'Málaga',10,'2020-06-13 17:00:00',1,1),(8,'Málaga',4,'2020-06-11 15:30:00',1,7),(9,'Málaga',2,'2020-06-22 18:00:00',1,20),(10,'Málaga',14,'2020-06-15 11:00:00',3,19),(11,'Málaga',4,'2020-06-28 19:00:00',3,7),(12,'Málaga',10,'2020-07-01 10:00:00',1,18),(13,'Málaga',14,'2020-07-02 22:53:00',1,19),(14,'Málaga',14,'2020-07-15 18:30:00',1,19),(15,'Málaga',10,'2020-07-20 17:00:00',3,1),(16,'Málaga',10,'2020-07-10 20:00:00',3,1),(17,'Málaga',14,'2020-07-05 12:00:00',3,19),(18,'Málaga',10,'2020-06-20 12:00:00',2,1),(19,'Málaga',4,'2020-06-12 23:00:00',2,7),(20,'Málaga',4,'2020-06-21 20:30:00',2,7);
/*!40000 ALTER TABLE `evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `idUsuarios` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(12) NOT NULL,
  `admin` tinyint(1) NOT NULL DEFAULT '0',
  `localidad` varchar(100) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idUsuarios`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `idUsuarios_UNIQUE` (`idUsuarios`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Daniel Cuevas Pérez','DanielCP89@gmail.com','1234',0,'Málaga',''),(2,'Francisco Javier Santiburcio','JavierJS@gmail.com','1234',0,'Málaga',''),(3,'Jose Luis González','JoseLG@gmail.com','1234',0,'Málaga',''),(4,'Rayan Chaves','RayanCDS@gmail.com','1234',0,'Málaga',''),(6,'admin','admin','admin',1,'Málaga',NULL),(7,'Rodrigo','Rodrigo@gmail.com','1234',0,'Málaga','');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios_has_deporte`
--

DROP TABLE IF EXISTS `usuarios_has_deporte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios_has_deporte` (
  `usuarios_idUsuarios` int NOT NULL,
  `deporte_idDeporte` int NOT NULL,
  PRIMARY KEY (`usuarios_idUsuarios`,`deporte_idDeporte`),
  KEY `fk_usuarios_has_deporte_deporte1_idx` (`deporte_idDeporte`),
  KEY `fk_usuarios_has_deporte_usuarios1_idx` (`usuarios_idUsuarios`),
  CONSTRAINT `fk_usuarios_has_deporte_deporte1` FOREIGN KEY (`deporte_idDeporte`) REFERENCES `deporte` (`idDeporte`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_usuarios_has_deporte_usuarios1` FOREIGN KEY (`usuarios_idUsuarios`) REFERENCES `usuarios` (`idUsuarios`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios_has_deporte`
--

LOCK TABLES `usuarios_has_deporte` WRITE;
/*!40000 ALTER TABLE `usuarios_has_deporte` DISABLE KEYS */;
INSERT INTO `usuarios_has_deporte` VALUES (1,1),(4,4),(3,7),(7,9),(2,19);
/*!40000 ALTER TABLE `usuarios_has_deporte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios_has_evento`
--

DROP TABLE IF EXISTS `usuarios_has_evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios_has_evento` (
  `usuarios_idUsuarios` int NOT NULL,
  `evento_id_Evento` int NOT NULL,
  PRIMARY KEY (`usuarios_idUsuarios`,`evento_id_Evento`),
  KEY `fk_usuarios_has_evento_evento1_idx` (`evento_id_Evento`),
  KEY `fk_usuarios_has_evento_usuarios1_idx` (`usuarios_idUsuarios`),
  CONSTRAINT `fk_usuarios_has_evento_evento1` FOREIGN KEY (`evento_id_Evento`) REFERENCES `evento` (`id_Evento`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_usuarios_has_evento_usuarios1` FOREIGN KEY (`usuarios_idUsuarios`) REFERENCES `usuarios` (`idUsuarios`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios_has_evento`
--

LOCK TABLES `usuarios_has_evento` WRITE;
/*!40000 ALTER TABLE `usuarios_has_evento` DISABLE KEYS */;
INSERT INTO `usuarios_has_evento` VALUES (3,3),(2,5),(2,6),(1,7),(1,8),(1,9),(3,10),(3,11),(1,12),(1,13),(1,14),(2,14),(3,15),(3,16),(3,17),(2,18),(2,19),(2,20);
/*!40000 ALTER TABLE `usuarios_has_evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'spoter'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-09 23:00:46
