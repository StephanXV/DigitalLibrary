CREATE DATABASE  IF NOT EXISTS `libreria` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */;
USE `libreria`;
-- MySQL dump 10.13  Distrib 8.0.13, for macos10.14 (x86_64)
--
-- Host: localhost    Database: libreria
-- ------------------------------------------------------
-- Server version	8.0.13
set global log_bin_trust_function_creators=1;
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
-- Table structure for table `assegna_trascrizione`
--

DROP TABLE IF EXISTS `assegna_trascrizione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `assegna_trascrizione` (
  `usern` varchar(200) NOT NULL,
  `IDimmagine` int(10) unsigned NOT NULL,
  PRIMARY KEY (`usern`,`IDimmagine`),
  KEY `assegna_immagine` (`IDimmagine`),
  CONSTRAINT `assegna_immagine` FOREIGN KEY (`IDimmagine`) REFERENCES `immagine` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `assegna_utente` FOREIGN KEY (`usern`) REFERENCES `utente` (`email`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assegna_trascrizione`
--

LOCK TABLES `assegna_trascrizione` WRITE;
/*!40000 ALTER TABLE `assegna_trascrizione` DISABLE KEYS */;
INSERT INTO `assegna_trascrizione` VALUES ('step@ok.it',6),('step@ok.it',44),('step@ok.it',46),('step@ok.it',67);
/*!40000 ALTER TABLE `assegna_trascrizione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `autore`
--

DROP TABLE IF EXISTS `autore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `autore` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `cognome` varchar(50) NOT NULL,
  `data_nascita` date NOT NULL,
  `nazionalita` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `autore_unico` (`nome`,`cognome`,`data_nascita`,`nazionalita`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autore`
--

LOCK TABLES `autore` WRITE;
/*!40000 ALTER TABLE `autore` DISABLE KEYS */;
INSERT INTO `autore` VALUES (6,'Alexis','Suarez','1990-02-16','Portogallo'),(5,'Dante ','Alighieri','1900-02-15','Italia'),(7,'Eugenio','Montale','2019-02-05','italia'),(3,'Giulio','York','1934-02-03','Inghilterra'),(4,'Giuseppe','Paolilli','1995-02-16','Italia'),(1,'Giuseppe','Ungaretti','1923-01-01','Italia'),(8,'Omero','Neri','2019-02-12','francese'),(2,'Virgilio','Scipione','0000-01-01','Italia');
/*!40000 ALTER TABLE `autore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `categoria` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (5,'Armi'),(8,'Arte'),(6,'Artigianato'),(2,'Economia'),(10,'Epigrafi'),(7,'Filosofia'),(4,'Guerre'),(11,'Istruzione'),(1,'Letteratura'),(3,'Mitologia'),(9,'Musica'),(12,'Poesia');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorizzazione`
--

DROP TABLE IF EXISTS `categorizzazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `categorizzazione` (
  `codiceOpera` varchar(50) NOT NULL,
  `nomeCategoria` varchar(50) NOT NULL,
  PRIMARY KEY (`codiceOpera`,`nomeCategoria`),
  KEY `categorizzazione_categoria` (`nomeCategoria`),
  CONSTRAINT `categorizzazione_categoria` FOREIGN KEY (`nomeCategoria`) REFERENCES `categoria` (`nome`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `categorizzazione_opera` FOREIGN KEY (`codiceOpera`) REFERENCES `opera` (`codice`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorizzazione`
--

LOCK TABLES `categorizzazione` WRITE;
/*!40000 ALTER TABLE `categorizzazione` DISABLE KEYS */;
INSERT INTO `categorizzazione` VALUES ('frt4','Arte'),('zzz34','Artigianato'),('qaz10','Economia'),('znb21','Economia'),('cd45','Filosofia'),('rtfg1','Filosofia'),('abc1','Guerre'),('znb21','Guerre'),('cd45','Istruzione'),('abc1','Mitologia'),('cxd43','Mitologia'),('pxt2','Mitologia'),('qwerty','Poesia');
/*!40000 ALTER TABLE `categorizzazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `immagine`
--

DROP TABLE IF EXISTS `immagine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `immagine` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `numero_pagina` int(10) unsigned NOT NULL,
  `sorgente` varchar(100) NOT NULL,
  `validazione` int(1) DEFAULT '0',
  `time_stamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `IDopera` int(10) unsigned NOT NULL,
  `utente` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `img_unica` (`numero_pagina`,`IDopera`),
  KEY `immagine_opera` (`IDopera`),
  CONSTRAINT `immagine_opera` FOREIGN KEY (`IDopera`) REFERENCES `opera` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `immagine`
--

LOCK TABLES `immagine` WRITE;
/*!40000 ALTER TABLE `immagine` DISABLE KEYS */;
INSERT INTO `immagine` VALUES (1,1,'/resources/images/abc1/1.jpg',1,'2019-02-11 17:14:13',5,'step@ok.it'),(6,1,'/resources/images/cxd43/1.jpg',1,'2019-02-12 17:54:42',1,'step@ok.it'),(7,1,'/resources/images/cd45/1.jpg',1,'2019-02-12 17:54:58',2,'step@ok.it'),(8,1,'/resources/images/frt4/1.jpg',1,'2019-02-12 17:55:13',3,'step@ok.it'),(9,1,'/resources/images/znb21/1.jpg',1,'2019-02-12 17:55:26',4,'step@ok.it'),(35,1,'/resources/images/zzz34/1.jpg',0,'2019-02-13 13:35:21',8,'step@ok.it'),(44,2,'/resources/images/cxd43/2.jpg',1,'2019-02-14 15:57:16',1,'step@ok.it'),(45,3,'/resources/images/cxd43/3.jpg',1,'2019-02-14 15:58:04',1,'step@ok.it'),(46,4,'/resources/images/cxd43/4.jpg',1,'2019-02-14 15:58:45',1,'step@ok.it'),(47,5,'/resources/images/cxd43/5.jpg',1,'2019-02-14 16:02:04',1,'step@ok.it'),(48,6,'/resources/images/cxd43/6.jpg',1,'2019-02-14 16:03:00',1,'step@ok.it'),(49,7,'/resources/images/cxd43/7.jpg',1,'2019-02-14 16:03:43',1,'step@ok.it'),(50,8,'/resources/images/cxd43/8.jpg',1,'2019-02-14 16:03:58',1,'step@ok.it'),(51,9,'/resources/images/cxd43/9.jpg',1,'2019-02-14 16:04:29',1,'step@ok.it'),(52,10,'/resources/images/cxd43/10.jpg',1,'2019-02-14 16:05:11',1,'step@ok.it'),(53,1,'/resources/images/pxt2/1.jpg',0,'2019-02-14 16:31:34',7,'step@ok.it'),(54,2,'/resources/images/pxt2/2.jpg',0,'2019-02-14 16:32:13',7,'step@ok.it'),(55,2,'/resources/images/cd45/2.jpg',1,'2019-02-14 16:36:27',2,'step@ok.it'),(56,2,'/resources/images/frt4/2.jpg',1,'2019-02-14 16:36:34',3,'step@ok.it'),(57,5,'/resources/images/abc1/5.jpg',1,'2019-02-14 16:36:55',5,'step@ok.it'),(58,6,'/resources/images/abc1/6.jpg',1,'2019-02-14 16:37:08',5,'step@ok.it'),(59,4,'/resources/images/abc1/4.jpg',1,'2019-02-14 16:37:19',5,'step@ok.it'),(60,3,'/resources/images/abc1/3.jpg',1,'2019-02-14 16:37:28',5,'step@ok.it'),(61,2,'/resources/images/abc1/2.jpg',1,'2019-02-14 16:37:37',5,'step@ok.it'),(62,3,'/resources/images/cd45/3.jpg',1,'2019-02-14 16:37:48',2,'step@ok.it'),(63,4,'/resources/images/cd45/4.jpg',1,'2019-02-14 16:38:06',2,'step@ok.it'),(64,5,'/resources/images/cd45/5.jpg',1,'2019-02-14 16:38:34',2,'step@ok.it'),(65,3,'/resources/images/znb21/3.jpg',1,'2019-02-14 16:39:05',4,'step@ok.it'),(66,4,'/resources/images/znb21/4.jpg',1,'2019-02-14 16:39:18',4,'step@ok.it'),(67,5,'/resources/images/znb21/5.jpg',1,'2019-02-14 16:39:28',4,'step@ok.it'),(68,6,'/resources/images/znb21/6.jpg',1,'2019-02-14 16:39:44',4,'step@ok.it'),(69,3,'/resources/images/frt4/3.jpg',1,'2019-02-14 16:40:59',3,'step@ok.it'),(70,4,'/resources/images/frt4/4.jpg',1,'2019-02-14 16:41:09',3,'step@ok.it'),(72,5,'/resources/images/frt4/5.jpg',1,'2019-02-14 16:41:30',3,'step@ok.it'),(73,6,'/resources/images/frt4/6.jpg',1,'2019-02-14 16:41:45',3,'step@ok.it'),(74,7,'/resources/images/znb21/7.jpg',1,'2019-02-14 16:43:14',4,'step@ok.it'),(75,2,'/resources/images/znb21/2.jpg',1,'2019-02-14 16:43:24',4,'step@ok.it'),(76,10,'/resources/images/pxt2/10.jpg',1,'2019-02-14 17:14:45',7,'step@ok.it'),(78,1,'/resources/images/qaz10/1.jpg',1,'2019-02-14 17:43:56',9,'step@ok.it'),(79,1,'/resources/images/qwerty/1.jpg',1,'2019-02-14 17:57:31',11,'step@ok.it'),(83,13,'/resources/images/pxt2/13.jpg',0,'2019-02-16 14:56:23',7,'step@ok.it'),(84,14,'/resources/images/pxt2/14.jpg',0,'2019-02-16 16:00:36',7,'step@ok.it');
/*!40000 ALTER TABLE `immagine` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `check_completaIMG` AFTER UPDATE ON `immagine` FOR EACH ROW begin
        
        if ((select count(ID) from immagine where (IDopera = new.IDopera and validazione = 1)) like (select pagine from opera where ID = new.IDopera)) then
			update opera set completaIMG = 'si' where ID = new.IDopera;
		end if;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `messaggio`
--

DROP TABLE IF EXISTS `messaggio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `messaggio` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `testo` text NOT NULL,
  `letto` enum('si','no') DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `mess_utente` (`email`),
  CONSTRAINT `mess_utente` FOREIGN KEY (`email`) REFERENCES `utente` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messaggio`
--

LOCK TABLES `messaggio` WRITE;
/*!40000 ALTER TABLE `messaggio` DISABLE KEYS */;
INSERT INTO `messaggio` VALUES (2,'step@ok.it','L\'immagine da lei acquisita dell\'opera: Bancarotta della Scienza, pagina: 2 NON è stata convalidata!','si'),(4,'step@ok.it','Il privilegio da lei richiesto è stato accettato! Ora è un: privilegiato.','si'),(5,'step@ok.it','L\'immagine da lei acquisita dell\'opera: El Dorado, pagina: 3 NON è stata convalidata!','si'),(6,'step@ok.it','Il privilegio da lei richiesto è stato accettato! Ora è un: acquisitore.',NULL),(7,'step@ok.it','Il privilegio da lei richiesto è stato accettato! Ora è un: trascrittore.',NULL);
/*!40000 ALTER TABLE `messaggio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `opera`
--

DROP TABLE IF EXISTS `opera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `opera` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `codice` varchar(15) NOT NULL,
  `titolo` varchar(50) NOT NULL,
  `descrizione` text,
  `anno` int(11) NOT NULL,
  `lingua` varchar(2) NOT NULL,
  `pagine` int(10) unsigned NOT NULL,
  `pubblicazione` enum('no','si') DEFAULT 'no',
  `completaIMG` enum('no','si') DEFAULT 'no',
  `completaTRS` enum('no','si') DEFAULT 'no',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `codice` (`codice`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `opera`
--

LOCK TABLES `opera` WRITE;
/*!40000 ALTER TABLE `opera` DISABLE KEYS */;
INSERT INTO `opera` VALUES (1,'cxd43','Bancarotta della Scienza','Le risposte di un grande professore',1934,'IT',10,'si','si','no'),(2,'cd45','Albanesi d\'Italia','Loro costumi e poesie popolari',1960,'IT',5,'si','si','no'),(3,'frt4','Ricami Italiani','Antichi e Moderni',1786,'IT',6,'si','si','no'),(4,'znb21','Il Porto Sepolto','Poesie di Giuseppe Ungaretti',1914,'IT',7,'si','si','no'),(5,'abc1','Bucoliche','È costituita da una raccolta di dieci egloghe esametriche con trattazione e intonazione pastorali',1600,'LA',6,'si','si','si'),(7,'pxt2','La Divina Commedia','Inferno, Purgatorio e Paradiso',1543,'IT',134,'no','no','no'),(8,'zzz34','El Dorado','La ricerca del tesoro',2017,'ES',3,'no','no','no'),(9,'qaz10','Impero','Guerre Persiane',1300,'LA',1,'no','si','no'),(10,'rtfg1','Aristotele','Pensiero',1300,'LA',2,'no','no','si'),(11,'qwerty','Ulisse','Il navigatore',200,'RU',1,'no','si','no');
/*!40000 ALTER TABLE `opera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permesso`
--

DROP TABLE IF EXISTS `permesso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `permesso` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tipo` enum('base','privilegiato','acquisitore','trascrittore','supervisore','amministratore') NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `tipo` (`tipo`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permesso`
--

LOCK TABLES `permesso` WRITE;
/*!40000 ALTER TABLE `permesso` DISABLE KEYS */;
INSERT INTO `permesso` VALUES (1,'base'),(2,'privilegiato'),(3,'acquisitore'),(4,'trascrittore'),(5,'supervisore'),(6,'amministratore');
/*!40000 ALTER TABLE `permesso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `privilegio`
--

DROP TABLE IF EXISTS `privilegio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `privilegio` (
  `email` varchar(50) NOT NULL,
  `tipo` enum('base','privilegiato','acquisitore','trascrittore','supervisore','amministratore') NOT NULL,
  `assegnato` enum('si','no') DEFAULT 'no',
  PRIMARY KEY (`email`,`tipo`),
  KEY `privilegio_permesso` (`tipo`),
  CONSTRAINT `privilegio_permesso` FOREIGN KEY (`tipo`) REFERENCES `permesso` (`tipo`) ON UPDATE CASCADE,
  CONSTRAINT `privilegio_utente` FOREIGN KEY (`email`) REFERENCES `utente` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `privilegio`
--

LOCK TABLES `privilegio` WRITE;
/*!40000 ALTER TABLE `privilegio` DISABLE KEYS */;
INSERT INTO `privilegio` VALUES ('admin','amministratore','si'),('ang31@a.com','acquisitore','no'),('ang31@a.com','trascrittore','si'),('enry@gmail.com','base','si'),('enry@gmail.com','privilegiato','no'),('gian@trivi.it','base','si'),('loreto@l.com','trascrittore','si'),('pavan05@libero.com','supervisore','si'),('pavaniello@gmail.com','base','si'),('pip@bau.it','base','si'),('pip@bau.it','acquisitore','no'),('step@ok.it','privilegiato','si'),('step@ok.it','acquisitore','si'),('step@ok.it','trascrittore','si');
/*!40000 ALTER TABLE `privilegio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stesura`
--

DROP TABLE IF EXISTS `stesura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `stesura` (
  `IDautore` int(10) unsigned NOT NULL,
  `IDopera` int(10) unsigned NOT NULL,
  PRIMARY KEY (`IDautore`,`IDopera`),
  KEY `stesura_opera` (`IDopera`),
  CONSTRAINT `stesura_autore` FOREIGN KEY (`IDautore`) REFERENCES `autore` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `stesura_opera` FOREIGN KEY (`IDopera`) REFERENCES `opera` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stesura`
--

LOCK TABLES `stesura` WRITE;
/*!40000 ALTER TABLE `stesura` DISABLE KEYS */;
INSERT INTO `stesura` VALUES (3,1),(1,2),(3,3),(1,4),(2,5),(5,7),(6,8),(7,9),(7,10),(8,11);
/*!40000 ALTER TABLE `stesura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trascrizione`
--

DROP TABLE IF EXISTS `trascrizione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `trascrizione` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `numero_pagina` int(10) unsigned NOT NULL,
  `testo` text NOT NULL,
  `validazione` int(1) DEFAULT '0',
  `time_stamp` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `IDopera` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `trs_unica` (`numero_pagina`,`IDopera`),
  KEY `trascrizione_opera` (`IDopera`),
  CONSTRAINT `trascrizione_opera` FOREIGN KEY (`IDopera`) REFERENCES `opera` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trascrizione`
--

LOCK TABLES `trascrizione` WRITE;
/*!40000 ALTER TABLE `trascrizione` DISABLE KEYS */;
INSERT INTO `trascrizione` VALUES (1,1,'Tuttavia, perché voi intendiate da dove sia nato tutto questo errore, di quelli che incolpano il piacere ed esaltano il dolore, io spiegherò tutta la questione, e presenterò le idee espresse dal famoso esploratore della verità, vorrei quasi dire dal costruttore della felicità umana. Nessuno, infatti, detesta, odia, o rifugge il piacere in quanto tale, solo perché è piacere, ma perché grandi sofferenze colpiscono quelli che non sono capaci di raggiungere il piacere attraverso la ragione; e al contrario, non c\'è nessuno che ami, insegua, voglia raggiungere il dolore in se stesso, soltanto perché è dolore, ma perché qualche volta accadono situazioni tali per cui attraverso la sofferenza o il dolore si cerca di raggiungere un qualche grande piacere. Concentrandoci su casi di piccola importanza: chi di noi intraprende un esercizio ginnico, se non per ottenerne un qualche vantaggio? E d\'altra parte, chi avrebbe motivo di criticare colui che desidera provare un piacere cui non segua nessun fastidio, o colui che fugge un dolore che non produce nessun piacere?\nAl contrario, però, noi con indignazione denunciamo e riteniamo meritevoli di odio quelli che, rammolliti e corrotti dai piaceri del momento, accecati dal desiderio, non prevedono a quali dolori e a quali sofferenze andranno incontro, e uguale colpa hanno quelli che abbandonano i propri doveri per pigrizia d\'animo, cioè per evitare le fatiche e i dolori. Certamente è facile e rapido distinguere questi casi. Infatti nel tempo libero, quando abbiamo tutta la nostra possibilità di scegliere e niente ci ostacola dal fare ciò che ci piace di più, bisogna accogliere ogni piacere e respingere ogni dolore. Ma in altri momenti, o nei doveri inevitabili o negli obblighi che ci vengono dalle circostanze, spesso accadrà che si debba respingere il piacere e accogliere il fastidio. E così il saggio si regola scegliendo tra questi atteggiamenti, facendo in modo che o – respingendo il piacere – ne ottenga di più grandi, o – sopportando il dolore – ne eviti di peggiori.',1,'2019-02-12 11:08:58',5),(2,4,'Le motivazioni didattiche e le modalità di realizzazione a cui si ispirano queste pagine sono le stesse che tempo fa ho esposto nella presentazione dei testi greci interattivi.\n\nCertamente il sostegno che questo tipo di proposta didattica può fornire allo studente appare più evidente nell\'apprendimento del Greco che nello studio del Latino, soprattutto in ambito lessicale. La comprensione dei testi latini è certamente più a portata di mano per chi pratica nella vita quotidiana una lingua come l\'Italiano, il cui patrimonio lessicale è in gran parte di derivazione latina.\n\nCiò non toglie che anche nello studio della lingua di Roma antica la possibilità di controllare anche nel dettaglio le corrispondenze tra le singole parole del testo e quelle della traduzione proposta possa risultare didatticamente utile. La disposizione delle parole in un testo di prosa latina è spesso molto complessa e inevitabilmente molto diversa da quella di una qualsiasi traduzione italiana, almeno altrettanto quanto lo può essere la costruzione di un testo di prosa greca. D\'altra parte entrambe le lingue classiche condividono una spiccata tendenza all\'ipotassi.\n\nMa a parte queste considerazioni, l\'incoraggiamento ad avviare una sezione latina di testi interattivi da affiancare a quella greca mi è giunto anche da parte di alcuni docenti che sembrano apprezzare le possibilità offerte da questo strumento didattico. A questi docenti va il mio più sincero ringraziamento per l\'attenzione dedicata a queste pagine e al lavoro che sta dietro la loro produzione.\n\nAl momento il numero dei brani interattivi proposti nella sezione latina è certamente esiguo. Ma, almeno nelle mie intenzioni, si tratta solo di un inizio.',1,'2019-02-12 18:05:48',5),(3,1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent mattis velit in nunc placerat finibus vel ut arcu. Pellentesque vehicula luctus venenatis. Integer sit amet pharetra urna. Vivamus dapibus justo eros, id laoreet ex commodo at. Sed volutpat risus ac porttitor ullamcorper. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Mauris vitae tempus odio. Suspendisse varius neque ut leo porta, ac molestie dui gravida. Interdum et malesuada fames ac ante ipsum primis in faucibus.',0,'2019-02-13 15:12:44',1),(4,1,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent mattis velit in nunc placerat finibus vel ut arcu. Pellentesque vehicula luctus venenatis. Integer sit amet pharetra urna. Vivamus dapibus justo eros, id laoreet ex commodo at. Sed volutpat risus ac porttitor ullamcorper. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Mauris vitae tempus odio. Suspendisse varius neque ut leo porta, ac molestie dui gravida. Interdum et malesuada fames ac ante ipsum primis in faucibus!.',0,'2019-02-13 15:13:02',3),(5,5,'Duis sed mi nec magna lacinia accumsan at at quam. Duis est justo, ultrices eu augue ut, eleifend blandit ligula. Fusce orci ipsum, aliquam ut mauris et, aliquam ultricies ipsum. Maecenas a ex lacinia, imperdiet nisl at, porta augue. Nam rhoncus neque tellus, eu porta tortor dictum sit amet. Nam luctus dignissim urna, sit amet dapibus risus pellentesque et. Etiam posuere rutrum ante et elementum. Suspendisse potenti. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Quisque ut odio arcu. Mauris quis condimentum turpis, eget vestibulum metus. Cras sollicitudin nunc ipsum, ut rhoncus diam egestas et. Sed eget consequat metus. Curabitur a leo diam.',1,'2019-02-13 15:13:49',5),(6,1,'Duis sed mi nec magna lacinia accumsan at at quam. Duis est justo, ultrices eu augue ut, eleifend blandit ligula. Fusce orci ipsum, aliquam ut mauris et, aliquam ultricies ipsum. Maecenas a ex lacinia, imperdiet nisl at, porta augue. Nam rhoncus neque tellus, eu porta tortor dictum sit amet. Nam luctus dignissim urna, sit amet dapibus risus pellentesque et. Etiam posuere rutrum ante et elementum. Suspendisse potenti. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Quisque ut odio arcu. Mauris quis condimentum turpis, eget vestibulum metus. Cras sollicitudin nunc ipsum, ut rhoncus diam egestas et. Sed eget consequat metus. Curabitur a leo diam.',0,'2019-02-13 15:13:56',4),(7,3,'Duis sed mi nec magna lacinia accumsan at at quam. Duis est justo, ultrices eu augue ut, eleifend blandit ligula. Fusce orci ipsum, aliquam ut mauris et, aliquam ultricies ipsum. Maecenas a ex lacinia, imperdiet nisl at, porta augue. Nam rhoncus neque tellus, eu porta tortor dictum sit amet. Nam luctus dignissim urna, sit amet dapibus risus pellentesque et. Etiam posuere rutrum ante et elementum. Suspendisse potenti. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Quisque ut odio arcu. Mauris quis condimentum turpis, eget vestibulum metus. Cras sollicitudin nunc ipsum, ut rhoncus diam egestas et. Sed eget consequat metus. Curabitur a leo diam.',1,'2019-02-13 15:14:02',1),(8,2,'Duis sed mi nec magna lacinia accumsan at at quam. Duis est justo, ultrices eu augue ut, eleifend blandit ligula. Fusce orci ipsum, aliquam ut mauris et, aliquam ultricies ipsum. Maecenas a ex lacinia, imperdiet nisl at, porta augue. Nam rhoncus neque tellus, eu porta tortor dictum sit amet. Nam luctus dignissim urna, sit amet dapibus risus pellentesque et. Etiam posuere rutrum ante et elementum. Suspendisse potenti. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Quisque ut odio arcu. Mauris quis condimentum turpis, eget vestibulum metus. Cras sollicitudin nunc ipsum, ut rhoncus diam egestas et. Sed eget consequat metus. Curabitur a leo diam.',1,'2019-02-13 15:14:09',5),(15,3,'Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam eaque ipsa, quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt, explicabo. Nemo enim ipsam voluptatem, quia voluptas sit, aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos, qui ratione voluptatem sequi nesciunt, neque porro quisquam est, qui dolorem ipsum, quia dolor sit, amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt, ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit, qui in ea voluptate velit esse, quam nihil molestiae consequatur, vel illum, qui dolorem eum fugiat, quo voluptas nulla pariatur? [33] At vero eos et accusamus et iusto odio dignissimos ducimus, qui blanditiis praesentium voluptatum deleniti atque corrupti, quos dolores et quas molestias excepturi sint, obcaecati cupiditate non provident, similique sunt in culpa, qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio, cumque nihil impedit, quo minus id, quod maxime placeat, facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet, ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.',1,'2019-02-14 17:25:58',5),(16,6,'Spicea si Cereris templo suspensa corona\nDonum erat agricolae quondam; si vinitor uvam\nSeposuit Bromio, quoties praedivite cornu\nCopia se fudit; placidam si lacte recenti\nPastores sparsere Palem, spumantia postquam5\nComplerant olidam supra caput ubera mulctram;\n\n[p. 96 modifica]\nPrimitias et quisque sui fert muneris auctor;\nCur ego non vocem hanc, aut si quid spiritus olim\nConcipit egregium, si quid mens ardua conscit\nRarum insigne sibi, si quo se murmure jactat10\nLingua potens, cur non totum in praeconia solvam\nMaeonidae magni, cujus de gurgite vivo\nCombibit arcanos vatum omnis turba furores?\nUtque laboriferi ferrum lapis Herculis alte\nErigit, et longos chalybum procul implicai orbes15\nVimque suam aspirat cunctis; ita prorsus ab uno\nImpetus ille sacer vatum dependet Homero.',1,'2019-02-14 17:25:58',5),(17,2,'Ille Jovis mensae accumbens, dat pocula nobis   Iliaca porrecta manu, quae triste repellant     Annorum senium vitamque in saecla propagent Ille deûm vultus, ille ardua semina laudum Ostentat populis, ac mentis praepete nisu Pervolitat chaos immensum coelum aequora terras, Vimque omnem exsinuat rerum, vocesque refundit Quas fera quas volucris quas venti atque aetheris ignes',0,'2019-02-14 18:12:37',2);
/*!40000 ALTER TABLE `trascrizione` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `check_completaTRS` AFTER UPDATE ON `trascrizione` FOR EACH ROW begin
        
        if ((select count(ID) from trascrizione where (IDopera = new.IDopera and validazione = 1)) like (select pagine from opera where ID = new.IDopera)) then
			update opera set completaTRS = 'si' where ID = new.IDopera;
		end if;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `utente`
--

DROP TABLE IF EXISTS `utente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `utente` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `cognome` varchar(50) NOT NULL,
  `data_nascita` date NOT NULL,
  `data_iscrizione` date DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `pass` varchar(50) NOT NULL,
  `sesso` enum('M','F') NOT NULL,
  `esperienza` enum('zero','one','two','three','four','five') DEFAULT 'zero',
  `domanda_sicurezza` enum('Quale è il cognome di tua madre da nubile?','Quale è il nome del tuo primo animale domestico?') DEFAULT NULL,
  `risposta_sicurezza` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utente`
--

LOCK TABLES `utente` WRITE;
/*!40000 ALTER TABLE `utente` DISABLE KEYS */;
INSERT INTO `utente` VALUES (1,'Enrico','Monte','1997-05-15','2019-02-11','enry@gmail.com','qwerty','M','zero','Quale è il cognome di tua madre da nubile?','pelliccia'),(2,'Angelo','Monte','1993-04-22','2019-02-11','ang31@a.com','ciao15','M','zero','Quale è il cognome di tua madre da nubile?','marianacci'),(3,'Fabrizio','Pavan','1991-05-15','2019-02-11','pavan05@libero.com','ciao15','M','one','Quale è il cognome di tua madre da nubile?','carla'),(4,'Stefano','Florio','1997-06-08','2019-02-11','step@ok.it','ciao','M','three','Quale è il cognome di tua madre da nubile?','rossi'),(5,'Gianluca','Trivisonne','1997-08-27','2019-02-12','gian@trivi.it','ciao','M','zero','Quale è il cognome di tua madre da nubile?','simonetti'),(6,'Giovanni','pratolino','1954-08-27','2019-02-12','admin','123','M','zero','Quale è il cognome di tua madre da nubile?','marianacci'),(7,'Pippo','Baudo','1998-02-12','2019-02-12','pip@bau.it','123','M','zero','Quale è il cognome di tua madre da nubile?','maria'),(8,'Loreto','Cicerone','1990-02-01','2019-02-13','loreto@l.com','lillo','M','one','Quale è il nome del tuo primo animale domestico?','Lilli'),(9,'Fabrizio','Pavan','2001-02-20','2019-02-13','pavaniello@gmail.com','ciao21','M','zero','Quale è il cognome di tua madre da nubile?','pavoncina');
/*!40000 ALTER TABLE `utente` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `iscr_data` BEFORE INSERT ON `utente` FOR EACH ROW begin
		Set new.data_iscrizione = now();
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Dumping routines for database 'libreria'
--
/*!50003 DROP FUNCTION IF EXISTS `tras` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `tras`(operaID integer, pag integer) RETURNS varchar(10) CHARSET utf8mb4
begin
	if not exists (select ID from trascrizione where (IDopera like operaID and numero_pagina like pag and validazione like 1)) then return "false";
	else if not exists (select ID from trascrizione where (IDopera = operaID and numero_pagina = pag)) then return "false";
	else return "true";
	end if;
    end if;
  end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `autore_opera` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `autore_opera`(idOP integer)
begin 
    
    select autore.* from autore join stesura on autore.ID = stesura.IDautore where stesura.IDopera like idOP;
    
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `categoria_opera` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `categoria_opera`(codOP varchar(50))
begin 
    
    select categoria.* from categoria join categorizzazione on categoria.nome = categorizzazione.nomeCategoria where categorizzazione.codiceOpera like codOP;
    
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `eliminaPrivilegio` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminaPrivilegio`(emailu varchar(50))
begin 
    
    delete from privilegio where (email = emailu and tipo = 'base');
    
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ricerca_opera` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ricerca_opera`(stringa varchar(100))
begin
   
   (select o.* from (select opera.* from opera where opera.pubblicazione like 'si') as o inner join (select s.IDopera from 
   (select ID from autore where nome like concat(stringa,'%') or cognome like concat(stringa,'%') or concat(nome,' ',cognome) like concat(stringa,'%')) as a1 
   inner join stesura as s on a1.ID=s.IDautore) as s1 on o.ID=s1.IDopera)
   union
   (select o1.* from (select opera.* from opera where opera.pubblicazione like 'si') as o1 where o1.titolo like concat(stringa,'%'));

end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `verifica_utente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `verifica_utente`(usern varchar (100), pass varchar(100))
begin 
    
    if pass is null or usern is null then
		signal sqlstate '45000' set message_text = 'errore: inserire tutti i dati richiesti';
	end if;	#controlla che l'utente esista
    
    if not exists (select u.ID from utente as u where u.email like usern and u.pass like pass) then
	signal sqlstate '45000' set message_text='inserire email e password valide';
    end if;
    
	select privilegio.tipo from privilegio where (privilegio.email=usern and privilegio.assegnato='si');
    
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-16 21:22:54
