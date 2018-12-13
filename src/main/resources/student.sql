/*
Navicat MySQL Data Transfer
Source Server         : MyLocalMySQL192.168.9.107
Source Server Version : 50718
Source Host           : 192.168.9.107:3306
Source Database       : jack
Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001
Date: 2017-07-24 15:06:07
*/
CREATE DATABASE mytest;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(12) DEFAULT NULL COMMENT '姓名',
  `sex` tinyint(255) DEFAULT NULL COMMENT '性别，女为0，男为1',
  `note` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `t_user`;
CREATE TABLE t_user(
  user_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  user_name VARCHAR(255) NOT NULL ,
  password VARCHAR(255) NOT NULL ,
  phone VARCHAR(255) NOT NULL
) ENGINE=INNODB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;