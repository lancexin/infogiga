-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.40-community


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema mstore
--

CREATE DATABASE IF NOT EXISTS mstore;
USE mstore;

--
-- Definition of table `equipment`
--

DROP TABLE IF EXISTS `equipment`;
CREATE TABLE `equipment` (
  `equipmentid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `number` varchar(45) DEFAULT NULL,
  `birth` varchar(45) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `macNO` varchar(45) DEFAULT NULL,
  `code` varchar(45) DEFAULT NULL,
  `userID` varchar(45) DEFAULT NULL,
  `versionID` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`equipmentid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `equipment`
--

/*!40000 ALTER TABLE `equipment` DISABLE KEYS */;
INSERT INTO `equipment` (`equipmentid`,`number`,`birth`,`status`,`macNO`,`code`,`userID`,`versionID`) VALUES 
 (1,'ok','2010-09-12 00:00:00','开启','00f50601c901','09xdfw1','1',1),
 (2,'fuck','2010-09-12','未开启','000154781911','4556123','1',1),
 (3,'mygod','2001-12-12','未开启','213465131321','541321321','1',1),
 (4,'derr','2013-11-01','未开启','134213451235','32141234','1',1),
 (5,'mather','2014-11-15','未开启','451313213213','54132131','1',1),
 (6,'12313','2015-11-20','未开启','sdfasddasasgd','sdfafsgasdf','1',1),
 (7,'132123','2014-11-02','未开启','sdafasdasfd','sdf23fadf','1',1),
 (8,'3134','2011-02-01','未开启','sdafasdf','sdafasfd','1',1),
 (9,'fasdf','2013-11-23','未开启','asdfasfd','asdfasdf','1',1),
 (10,'asdfsadf','1990-12-17','未开启','asfdasfd','asdfasdfasd','1',1),
 (11,'sdfasdf','1956-14-12','未开启','1341515','dsafasdf','1',1),
 (12,'test','2010-05-17 13:21:22','未开启',NULL,'e436712e','1',NULL),
 (13,'mack','2010-05-17 14:02:24','未开启',NULL,'94a38041','1',NULL);
/*!40000 ALTER TABLE `equipment` ENABLE KEYS */;


--
-- Definition of table `menu`
--

DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `meunId` varchar(45) NOT NULL,
  `funcId` varchar(45) NOT NULL,
  `menuName` varchar(45) NOT NULL,
  `menuDesc` text NOT NULL,
  `domainId` varchar(45) NOT NULL,
  `menuUrl` varchar(45) NOT NULL,
  `menuPicUrl` varchar(45) NOT NULL,
  `menuIdx` varchar(45) NOT NULL,
  `needLogin` varchar(45) NOT NULL,
  `needContract` varchar(45) NOT NULL,
  `needSecPasswd` varchar(45) NOT NULL,
  `stopShow` varchar(45) NOT NULL,
  `notLoginShow` varchar(45) NOT NULL,
  `busiKind` varchar(45) NOT NULL,
  `isUsed` varchar(45) NOT NULL,
  `validDate` varchar(45) NOT NULL,
  `expreDate` varchar(45) NOT NULL,
  `lastDayCando` varchar(45) NOT NULL,
  `helpUrl` varchar(45) NOT NULL,
  `channelLevel` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `menu`
--

/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;


--
-- Definition of table `renewal`
--

DROP TABLE IF EXISTS `renewal`;
CREATE TABLE `renewal` (
  `renewalID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `version` varchar(45) DEFAULT NULL,
  `url` varchar(45) DEFAULT NULL,
  `uploadTime` varchar(45) DEFAULT NULL,
  `systemID` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`renewalID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `renewal`
--

/*!40000 ALTER TABLE `renewal` DISABLE KEYS */;
INSERT INTO `renewal` (`renewalID`,`version`,`url`,`uploadTime`,`systemID`) VALUES 
 (1,'1.0','http://localhost:8080/test','2011-12-02','1'),
 (2,'爱死','upload\\11s.apk','2010-05-17 09:46:32',NULL);
/*!40000 ALTER TABLE `renewal` ENABLE KEYS */;


--
-- Definition of table `scene`
--

DROP TABLE IF EXISTS `scene`;
CREATE TABLE `scene` (
  `sceneID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `sceneName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`sceneID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `scene`
--

/*!40000 ALTER TABLE `scene` DISABLE KEYS */;
INSERT INTO `scene` (`sceneID`,`sceneName`) VALUES 
 (1,'设备开启'),
 (2,'设备注册'),
 (3,'进入体验'),
 (4,'退出体验'),
 (5,'订购业务'),
 (6,'软件更新');
/*!40000 ALTER TABLE `scene` ENABLE KEYS */;


--
-- Definition of table `service`
--

DROP TABLE IF EXISTS `service`;
CREATE TABLE `service` (
  `serviceID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `serviceName` varchar(45) NOT NULL,
  `serviceCode` varchar(45) NOT NULL,
  `introduction` varchar(45) NOT NULL,
  `weburl` varchar(45) NOT NULL,
  PRIMARY KEY (`serviceID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `service`
--

/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` (`serviceID`,`serviceName`,`serviceCode`,`introduction`,`weburl`) VALUES 
 (1,'音乐随身听','1234','这是音乐随身听的介绍','http://www.ok.com'),
 (2,'移动互联','11001','				美妙的业务，不多说							','htt://www.ok.com'),
 (3,'飞信充值业务','test','											靠，想怎么开通就怎么开通','http://www.ok.com'),
 (4,'能不能添加业务','TEST','							测试数据				','http://localhost:8080/gun'),
 (5,'测试业务','Test01','				测试专用章							','http://www.ok.com');
/*!40000 ALTER TABLE `service` ENABLE KEYS */;


--
-- Definition of table `servicedetil`
--

DROP TABLE IF EXISTS `servicedetil`;
CREATE TABLE `servicedetil` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_service` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `openCode` varchar(45) DEFAULT NULL,
  `pay` varchar(45) DEFAULT NULL,
  `info` varchar(45) DEFAULT NULL,
  `method` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `servicedetil`
--

/*!40000 ALTER TABLE `servicedetil` DISABLE KEYS */;
INSERT INTO `servicedetil` (`id`,`id_service`,`name`,`openCode`,`pay`,`info`,`method`) VALUES 
 (1,'1','草泥马观光团','test04','500元/天','T054','发送T054到10251658'),
 (2,'1','小YY套餐','XYY','50000/元每月','test','					开通方式，测试专用		'),
 (3,'5','测试专用','test','5元/月','test','							测试专用');
/*!40000 ALTER TABLE `servicedetil` ENABLE KEYS */;


--
-- Definition of table `sms`
--

DROP TABLE IF EXISTS `sms`;
CREATE TABLE `sms` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_service` int(10) unsigned DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `context` varchar(45) DEFAULT NULL,
  `send_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sms`
--

/*!40000 ALTER TABLE `sms` DISABLE KEYS */;
INSERT INTO `sms` (`id`,`id_service`,`phone`,`context`,`send_time`) VALUES 
 (1,1,'13868140535','测试数据','2010-10-12 00:00:00');
/*!40000 ALTER TABLE `sms` ENABLE KEYS */;


--
-- Definition of table `statistics`
--

DROP TABLE IF EXISTS `statistics`;
CREATE TABLE `statistics` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_equip` int(10) unsigned DEFAULT NULL,
  `time_happen` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `id_service` int(10) unsigned DEFAULT NULL,
  `id_secene` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `statistics`
--

/*!40000 ALTER TABLE `statistics` DISABLE KEYS */;
INSERT INTO `statistics` (`id`,`id_equip`,`time_happen`,`phone`,`id_service`,`id_secene`) VALUES 
 (1,1,'2010-09-10','13868140535',1,1),
 (2,1,'2011-02-01','15927616602',1,1),
 (3,1,'2011-01-02','15927616602',1,1),
 (4,1,'2011-01-02','15927616602',1,1),
 (5,1,'2011-01-02','15927616602',1,1),
 (6,1,'2011-01-02','15927616602',1,1),
 (7,1,'2011-01-02','15927616602',1,1),
 (8,1,'2011-01-02','15927616602',1,1),
 (9,1,'2011-01-02','15927616602',1,1),
 (10,1,'2011-01-02','15927616602',1,1),
 (11,1,'2011-01-02','15927616602',1,1),
 (12,1,'2011-01-02','15927616602',1,1),
 (13,1,'2011-01-02','15927616602',1,1),
 (14,1,'2011-01-02','15927616602',1,1);
/*!40000 ALTER TABLE `statistics` ENABLE KEYS */;


--
-- Definition of table `sysinfo`
--

DROP TABLE IF EXISTS `sysinfo`;
CREATE TABLE `sysinfo` (
  `systemid` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `systemName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`systemid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sysinfo`
--

/*!40000 ALTER TABLE `sysinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `sysinfo` ENABLE KEYS */;


--
-- Definition of table `usertable`
--

DROP TABLE IF EXISTS `usertable`;
CREATE TABLE `usertable` (
  `user_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) NOT NULL,
  `user_password` varchar(45) NOT NULL,
  `user_type` varchar(45) DEFAULT NULL,
  `user_maxnum` varchar(45) DEFAULT NULL,
  `user_birthtime` datetime DEFAULT NULL,
  PRIMARY KEY (`user_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `usertable`
--

/*!40000 ALTER TABLE `usertable` DISABLE KEYS */;
INSERT INTO `usertable` (`user_ID`,`user_name`,`user_password`,`user_type`,`user_maxnum`,`user_birthtime`) VALUES 
 (1,'admin','admin','1','500','2010-10-12 00:00:00'),
 (6,'user01','123456','2','500','2010-05-17 12:34:39');
/*!40000 ALTER TABLE `usertable` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
