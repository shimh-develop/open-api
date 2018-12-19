/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Version : 50721
 Source Host           : localhost
 Source Database       : open-api

 Target Server Version : 50721
 File Encoding         : utf-8

 Date: 12/19/2018 09:37:28 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `catalog`
-- ----------------------------
DROP TABLE IF EXISTS `catalog`;
CREATE TABLE `catalog` (
  `cat_id` int(11) NOT NULL,
  `cat_name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `s_number` int(11) DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `is_del` int(11) DEFAULT 0,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`cat_id`),
  KEY `FK39b3qihhe553kmin2r25gxthm` (`item_id`),
  KEY `FK2n9sihbblanh07i8girqltn1m` (`parent_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `hibernate_sequence`
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `item`
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `item_id` int(11) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `is_del` int(11) DEFAULT 0,
  `item_description` varchar(200) COLLATE utf8_bin DEFAULT NULL,
  `item_name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `item_type` int(11) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `password` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `top` int(11) DEFAULT 99,
  PRIMARY KEY (`item_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `page`
-- ----------------------------
DROP TABLE IF EXISTS `page`;
CREATE TABLE `page` (
  `page_id` int(11) NOT NULL,
  `page_name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `s_number` int(11) DEFAULT 99,
  `catalog_id` int(11) DEFAULT NULL,
  `item_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `is_del` int(11) DEFAULT 0,
  `page_content` longtext COLLATE utf8_bin,
  `page_title` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`page_id`),
  KEY `FKhpci64c52j3v9mf561ymmysl6` (`catalog_id`),
  KEY `FKdv36u3gtgsejrl633u0b93kgy` (`item_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL,
  `admin` bit(1) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `deleted` bit(1) DEFAULT NULL,
  `last_login` datetime DEFAULT NULL,
  `password` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `salt` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `username` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `is_del` int(11) DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

SET FOREIGN_KEY_CHECKS = 1;
