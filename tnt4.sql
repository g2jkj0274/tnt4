/*
SQLyog Community v13.2.0 (64 bit)
MySQL - 10.4.28-MariaDB : Database - tnt4
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`tnt4` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `tnt4`;

/*Table structure for table `board` */

DROP TABLE IF EXISTS `board`;

CREATE TABLE `board` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `code` char(100) NOT NULL,
  `name` char(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `board` */

insert  into `board`(`id`,`regDate`,`updateDate`,`code`,`name`) values 
(1,'2023-07-17 09:49:44','2023-07-17 09:49:44','메인','메인'),
(2,'2023-07-17 09:49:44','2023-07-17 09:49:44','운동','운동'),
(3,'2023-07-17 09:49:44','2023-07-17 09:49:44','식단','식단'),
(4,'2023-07-17 09:49:44','2023-07-17 09:49:44','유저','유저');

/*Table structure for table `exercise` */

DROP TABLE IF EXISTS `exercise`;

CREATE TABLE `exercise` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `boardId` int(1) unsigned NOT NULL,
  `name` char(100) NOT NULL,
  `location` char(100) NOT NULL,
  `kind` char(100) NOT NULL,
  `link` char(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `boardId` (`boardId`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `exercise` */

insert  into `exercise`(`id`,`regDate`,`updateDate`,`boardId`,`name`,`location`,`kind`,`link`) values 
(2,'2023-07-20 00:17:36','2023-07-20 00:17:36',2,'슬로우 버피테스트','홈트','유산소','https://www.youtube.com/watch?v=Uly8jUuscOw'),
(3,'2023-07-20 10:03:56','2023-07-20 10:03:56',2,'초급 유산소','홈트','유산소','https://www.youtube.com/watch?v=OoytN1a8Klc'),
(4,'2023-07-20 10:03:56','2023-07-20 10:03:56',2,'왕초보달리기 루틴','홈트','유산소','https://www.youtube.com/watch?v=rOoYrqdCVc0'),
(5,'2023-07-20 10:03:56','2023-07-20 10:03:56',2,' 체지방 태우는 루틴','홈트','유산소','https://www.youtube.com/watch?v=CYcLODSeC-c'),
(6,'2023-07-20 10:03:56','2023-07-20 10:03:56',2,'홈트 스쿼트','홈트','무산소','https://www.youtube.com/watch?v=KXYi6bI-UPE'),
(7,'2023-07-20 10:03:56','2023-07-20 10:03:56',2,'푸쉬업 자세 설명','홈트','무산소','https://www.youtube.com/watch?v=DMU6bat46SM'),
(8,'2023-07-20 10:03:56','2023-07-20 10:03:56',2,' 푸쉬업 루틴','홈트','무산소','https://www.youtube.com/watch?v=c_5ENJWekbQ'),
(9,'2023-07-20 10:03:56','2023-07-20 10:03:56',2,'초보자를 위한 푸쉬업','홈트','무산소','https://www.youtube.com/watch?v=8oYB62z3sVs'),
(10,'2023-07-20 10:03:56','2023-07-20 10:03:56',2,'런지 자세','홈트','무산소','https://www.youtube.com/watch?v=oYiBDWhmrX8'),
(11,'2023-07-20 10:03:56','2023-07-20 10:03:56',2,'홈트 하체 루틴','홈트','무산소','https://www.youtube.com/watch?v=KXYi6bI-UPE'),
(12,'2023-07-20 10:03:56','2023-07-20 10:03:56',2,' 홈트 초보 코어 운동','홈트','무산소','https://www.youtube.com/watch?v=C7gPeAgeBAk'),
(13,'2023-07-20 10:03:56','2023-07-20 10:03:56',2,' 헬스장 유산소팁','헬스장','유산소','https://www.youtube.com/watch?v=RDIZLrVVA-Q'),
(14,'2023-07-20 10:03:56','2023-07-20 10:03:56',2,' 체지방 태우는 런닝머신 루틴','헬스장','유산소','https://www.youtube.com/watch?v=IJZvF8XITWo'),
(15,'2023-07-20 10:03:56','2023-07-20 10:03:56',2,' 살빠지는 실내 자전거 루틴','헬스장','유산소','https://www.youtube.com/watch?v=weOd8r9JHdU'),
(16,'2023-07-20 10:03:56','2023-07-20 10:03:56',2,' 벤치프레스 자세','헬스장','무산소','https://www.youtube.com/watch?v=0DsXTSHo3lU'),
(17,'2023-07-20 10:03:56','2023-07-20 10:03:56',2,'오버 헤드 프레스 자세','헬스장','무산소','https://www.youtube.com/watch?v=EoGMVSORHtM'),
(18,'2023-07-20 10:03:56','2023-07-20 10:03:56',2,'바벨 스쿼트 자세','헬스장','무산소','https://www.youtube.com/watch?v=kz84Fc6HGu4'),
(19,'2023-07-20 10:03:56','2023-07-20 10:03:56',2,'데드리프트 자세','헬스장','무산소','https://www.youtube.com/watch?v=EBjYQeeBI-0'),
(20,'2023-07-20 10:03:56','2023-07-20 10:03:56',2,'턱걸이 자세','헬스장','무산소','https://www.youtube.com/watch?v=nWhS28U6bCY'),
(21,'2023-07-20 10:03:56','2023-07-20 10:03:56',2,' 바벨로우 자세','헬스장','무산소','https://www.youtube.com/watch?v=EEqGCoTuYfQ'),
(22,'2023-07-20 10:03:56','2023-07-20 10:03:56',2,'초보자를 위한 벌크업 루틴','헬스장','무산소','https://www.youtube.com/watch?v=KCAwey51gUc'),
(23,'2023-07-20 10:03:56','2023-07-20 10:03:56',2,'기본적인 머신 사용법','헬스장','무산소','https://www.youtube.com/watch?v=ztGFHUsjFQo'),
(24,'2023-07-20 10:03:56','2023-07-20 10:03:56',2,' 초보자를 위한 어꺠 ,팔, 하체 운동','헬스장','무산소','https://www.youtube.com/watch?v=D7iZMIy0C5E'),
(25,'2023-07-20 10:03:56','2023-07-20 10:03:56',2,' 초보자를 위한 가슴,등,복근 운동','헬스장','무산소','https://www.youtube.com/watch?v=iv8reqGucYE');

/*Table structure for table `food` */

DROP TABLE IF EXISTS `food`;

CREATE TABLE `food` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `boardId` int(1) unsigned NOT NULL,
  `name` char(100) NOT NULL,
  `kal` int(100) NOT NULL,
  `pro` int(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `boardId` (`boardId`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `food` */

insert  into `food`(`id`,`regDate`,`updateDate`,`boardId`,`name`,`kal`,`pro`) values 
(4,'2023-07-20 00:22:27','2023-07-20 00:22:27',3,'사과',52,0),
(5,'2023-07-20 10:07:09','2023-07-20 10:07:09',3,'요거트',58,10),
(6,'2023-07-20 10:07:09','2023-07-20 10:07:09',3,'당근',41,1),
(7,'2023-07-20 10:07:09','2023-07-20 10:07:09',3,'바나나',88,1),
(8,'2023-07-20 10:07:09','2023-07-20 10:07:09',3,'토마토',16,1),
(9,'2023-07-20 10:07:09','2023-07-20 10:07:09',3,'두부',76,8),
(10,'2023-07-20 10:07:09','2023-07-20 10:07:09',3,'우유',42,3),
(11,'2023-07-20 10:07:09','2023-07-20 10:07:09',3,'고구마',85,2),
(12,'2023-07-20 10:07:09','2023-07-20 10:07:09',3,'오트밀',67,2),
(13,'2023-07-20 10:07:09','2023-07-20 10:07:09',3,'브로콜리',33,3),
(14,'2023-07-20 10:07:09','2023-07-20 10:07:09',3,'블루베리',57,1),
(15,'2023-07-20 10:07:09','2023-07-20 10:07:09',3,'닭가슴살',164,31),
(16,'2023-07-20 10:07:09','2023-07-20 10:07:09',3,'참치',131,28),
(17,'2023-07-20 10:07:09','2023-07-20 10:07:09',3,'달걀',155,13),
(18,'2023-07-20 10:07:09','2023-07-20 10:07:09',3,'연어',120,20),
(19,'2023-07-20 10:07:09','2023-07-20 10:07:09',3,'아몬드',575,21),
(20,'2023-07-20 10:07:09','2023-07-20 10:07:09',3,'통밀빵',247,13),
(21,'2023-07-20 10:07:09','2023-07-20 10:07:09',3,'쿼터파운드치즈버거',244,14),
(22,'2023-07-20 10:07:09','2023-07-20 10:07:09',3,'쌀밥',130,3);

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `regDate` datetime NOT NULL,
  `updateDate` datetime NOT NULL,
  `loginId` char(100) NOT NULL,
  `loginPw` char(100) NOT NULL,
  `name` char(100) NOT NULL,
  `gender` char(100) NOT NULL,
  `height` int(200) NOT NULL,
  `weight` int(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `loginId` (`loginId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `member` */

insert  into `member`(`id`,`regDate`,`updateDate`,`loginId`,`loginPw`,`name`,`gender`,`height`,`weight`) values 
(1,'2023-07-20 00:05:58','2023-07-20 00:05:58','admin','admin','admin','admin',0,0),
(2,'2023-07-20 00:05:58','2023-07-20 00:05:58','b','b','admin','여자',160,60),
(3,'2023-07-20 00:07:58','2023-07-20 00:07:58','c','d','철수','남자',170,70);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
