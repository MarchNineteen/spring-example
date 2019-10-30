DROP TABLE IF EXISTS `bookcase`;
CREATE TABLE `bookcase` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

LOCK TABLES `bookcase` WRITE;
INSERT INTO `bookcase` VALUES
(1,'社会'),
(2,'情感'),
(3,'国学'),
(4,'推理'),
(5,'绘画'),
(6,'心理学'),
(7,'传记'),
(8,'科技'),
(9,'计算机'),
(10,'小说');
UNLOCK TABLES;

DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `author` varchar(20) DEFAULT NULL,
  `publish` varchar(20) DEFAULT NULL,
  `pages` int(10) DEFAULT NULL,
  `price` float(10,2) DEFAULT NULL,
  `bookcaseid` int(10) DEFAULT NULL,
  `abled` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ieh6qsxp6q7oydadktc9oc8t2` (`bookcaseid`),
  CONSTRAINT `FK_ieh6qsxp6q7oydadktc9oc8t2` FOREIGN KEY (`bookcaseid`) REFERENCES `bookcase` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;

LOCK TABLES `book` WRITE;
INSERT INTO `book` VALUES
(1,'解忧杂货店','东野圭吾','电子工业出版社',102,27.30,9,0),
(2,'追风筝的人','卡勒德·胡赛尼','上海人民出版社',230,33.50,3,0),
(3,'人间失格','太宰治','作家出版社',150,17.30,1,1),
(4,'这就是二十四节气','高春香','电子工业出版社',220,59.00,3,1),
(5,'白夜行','东野圭吾','南海出版公司',300,27.30,4,1),
(6,'摆渡人','克莱儿·麦克福尔','百花洲文艺出版社',225,22.80,1,1),
(7,'暖暖心绘本','米拦弗特毕','湖南少儿出版社',168,131.60,5,1),
(8,'天才在左疯子在右','高铭','北京联合出版公司',330,27.50,6,1),
(9,'我们仨','杨绛','生活.读书.新知三联书店',89,17.20,7,1),
(10,'活着','余华','作家出版社',100,100.00,6,1),
(11,'水浒传','施耐庵','三联出版社',300,50.00,1,1),
(12,'三国演义','罗贯中','三联出版社',300,50.00,2,1),
(13,'红楼梦','曹雪芹','三联出版社',300,50.00,5,1),
(14,'西游记','吴承恩','三联出版社',300,60.00,3,1);
UNLOCK TABLES;
