/*
SQLyog Community v13.2.0 (64 bit)
MySQL - 10.4.28-MariaDB : Database - sbs_proj
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sbs_proj` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `sbs_proj`;

/*Table structure for table `bmicategory` */

DROP TABLE IF EXISTS `bmicategory`;

CREATE TABLE `bmicategory` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `section` CHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `bmicategory` */

INSERT  INTO `bmicategory`(`id`,`section`) VALUES 
(1,'저체중'),
(2,'정상'),
(3,'과체중, 비만'),
(4,'기타');

/*Table structure for table `exercise` */

DROP TABLE IF EXISTS `exercise`;

CREATE TABLE `exercise` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `bmiId` INT(10) UNSIGNED NOT NULL,
  `name` CHAR(100) NOT NULL,
  `location` CHAR(100) NOT NULL,
  `kind` CHAR(100) NOT NULL,
  `link` CHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `exercise` */

INSERT  INTO `exercise`(`id`,`bmiId`,`name`,`location`,`kind`,`link`) VALUES 
(1,2,' 초보자를 위한 가슴,등,복근 운동','헬스장','무산소','https://www.youtube.com/watch?v=iv8reqGucYE'),
(2,3,'슬로우 버피테스트','홈트','유산소','https://www.youtube.com/watch?v=Uly8jUuscOw'),
(3,2,'초급 유산소','홈트','유산소','https://www.youtube.com/watch?v=OoytN1a8Klc'),
(4,2,'왕초보달리기 루틴','홈트','유산소','https://www.youtube.com/watch?v=rOoYrqdCVc0'),
(5,3,' 체지방 태우는 루틴','홈트','유산소','https://www.youtube.com/watch?v=CYcLODSeC-c'),
(6,2,'홈트 스쿼트','홈트','무산소','https://www.youtube.com/watch?v=KXYi6bI-UPE'),
(7,2,'푸쉬업 자세 설명','홈트','무산소','https://www.youtube.com/watch?v=DMU6bat46SM'),
(8,2,' 푸쉬업 루틴','홈트','무산소','https://www.youtube.com/watch?v=c_5ENJWekbQ'),
(9,2,'초보자를 위한 푸쉬업','홈트','무산소','https://www.youtube.com/watch?v=8oYB62z3sVs'),
(10,2,'런지 자세','홈트','무산소','https://www.youtube.com/watch?v=oYiBDWhmrX8'),
(11,2,'홈트 하체 루틴','홈트','무산소','https://www.youtube.com/watch?v=KXYi6bI-UPE'),
(12,2,' 홈트 초보 코어 운동','홈트','무산소','https://www.youtube.com/watch?v=C7gPeAgeBAk'),
(13,2,' 헬스장 유산소팁','헬스장','유산소','https://www.youtube.com/watch?v=RDIZLrVVA-Q'),
(14,3,' 체지방 태우는 런닝머신 루틴','헬스장','유산소','https://www.youtube.com/watch?v=IJZvF8XITWo'),
(15,3,' 살빠지는 실내 자전거 루틴','헬스장','유산소','https://www.youtube.com/watch?v=weOd8r9JHdU'),
(16,2,' 벤치프레스 자세','헬스장','무산소','https://www.youtube.com/watch?v=0DsXTSHo3lU'),
(17,2,'오버 헤드 프레스 자세','헬스장','무산소','https://www.youtube.com/watch?v=EoGMVSORHtM'),
(18,2,'바벨 스쿼트 자세','헬스장','무산소','https://www.youtube.com/watch?v=kz84Fc6HGu4'),
(19,2,'데드리프트 자세','헬스장','무산소','https://www.youtube.com/watch?v=EBjYQeeBI-0'),
(20,2,'턱걸이 자세','헬스장','무산소','https://www.youtube.com/watch?v=nWhS28U6bCY'),
(21,2,' 바벨로우 자세','헬스장','무산소','https://www.youtube.com/watch?v=EEqGCoTuYfQ'),
(22,1,'초보자를 위한 벌크업 루틴','헬스장','무산소','https://www.youtube.com/watch?v=KCAwey51gUc'),
(23,2,'기본적인 머신 사용법','헬스장','무산소','https://www.youtube.com/watch?v=ztGFHUsjFQo'),
(24,2,' 초보자를 위한 어꺠 ,팔, 하체 운동','헬스장','무산소','https://www.youtube.com/watch?v=D7iZMIy0C5E');

/*Table structure for table `food` */

DROP TABLE IF EXISTS `food`;

CREATE TABLE `food` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `bmiId` INT(10) UNSIGNED NOT NULL,
  `name` CHAR(100) NOT NULL,
  `kal` INT(100) NOT NULL,
  `pro` INT(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `food` */

INSERT  INTO `food`(`id`,`bmiId`,`name`,`kal`,`pro`) VALUES 
(1,1,'통밀빵',247,13),
(2,1,'쿼터파운드치즈버거',244,14),
(3,1,'쌀밥',130,3),
(4,2,'사과',52,0),
(5,2,'요거트',58,10),
(6,3,'당근',41,1),
(7,2,'바나나',88,1),
(8,1,'토마토',16,1),
(9,2,'두부',76,8),
(10,3,'우유',42,3),
(11,3,'고구마',85,2),
(12,1,'오트밀',67,2),
(13,3,'브로콜리',33,3),
(14,1,'블루베리',57,1),
(15,2,'닭가슴살',164,31),
(16,2,'참치',131,28),
(17,2,'달걀',155,13),
(18,2,'연어',120,20),
(19,1,'아몬드',575,21);

/*Table structure for table `member` */

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `bmiId` INT(10) NOT NULL,
  `loginId` CHAR(100) NOT NULL,
  `loginPw` CHAR(100) NOT NULL,
  `name` CHAR(100) NOT NULL,
  `gender` CHAR(100) NOT NULL,
  `height` INT(200) NOT NULL,
  `weight` INT(200) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `loginId` (`loginId`)
) ENGINE=INNODB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `member` */

INSERT  INTO `member`(`id`,`bmiId`,`loginId`,`loginPw`,`name`,`gender`,`height`,`weight`) VALUES 
(1,2,'admin','admin','admin','admin',0,0),
(2,3,'c','d','철수','남자',170,70);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


SELECT * FROM `board`;
SELECT * FROM `exercise`;
SELECT * FROM `food`;
SELECT * FROM `member`;

SELECT `name` FROM `exercise`
WHERE location = '헬스장' AND kind = '무산소';

DESC `member`;