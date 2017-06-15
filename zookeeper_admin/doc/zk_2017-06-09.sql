/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.5.44-log : Database - zk_admin
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zk_admin` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `zk_admin`;

/*Table structure for table `zk_auth` */

DROP TABLE IF EXISTS `zk_auth`;

CREATE TABLE `zk_auth` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `instance_id` int(20) DEFAULT NULL,
  `auth` varchar(64) DEFAULT NULL,
  `pass` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table `zk_auth` */

/*Table structure for table `zk_instance` */

DROP TABLE IF EXISTS `zk_instance`;

CREATE TABLE `zk_instance` (
  `ID` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `NAME` varchar(256) NOT NULL COMMENT 'ZK实例名',
  `IP` varchar(20) NOT NULL COMMENT 'IP',
  `PORT` int(10) NOT NULL COMMENT '端口',
  `USE` tinyint(1) NOT NULL COMMENT '是否可用',
  `operate_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `zk_instance` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
