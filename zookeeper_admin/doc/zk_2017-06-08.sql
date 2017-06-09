DROP TABLE IF EXISTS `zk_instance`;

CREATE TABLE `zk_instance` (
  `ID` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `NAME` varchar(256) NOT NULL COMMENT 'ZK实例名',
  `IP` varchar(20) NOT NULL COMMENT 'IP',
  `PORT` int(10) NOT NULL COMMENT '端口',
  `AUTH` varchar(64) DEFAULT NULL COMMENT 'ID',
  `PASS` varchar(64) DEFAULT NULL COMMENT '密码',
  `USE` tinyint(1) NOT NULL COMMENT '是否可用',
  `operate_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

insert  into `zk_instance`(`ID`,`NAME`,`IP`,`PORT`,`AUTH`,`PASS`,`USE`,`operate_date`) values (1,'hi','as',30,'as','asas',1,'2017-06-08 15:31:23');
