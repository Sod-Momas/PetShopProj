/*
Navicat MySQL Data Transfer

Source Server         : 3部
Source Server Version : 50525
Source Host           : 10.10.23.221:3306
Source Database       : petshop

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2017-08-08 09:50:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `deal_type` int(4) DEFAULT NULL,
  `pet_id` int(4) DEFAULT NULL,
  `seller_id` int(4) DEFAULT NULL,
  `buyer_id` int(4) DEFAULT NULL,
  `price` int(4) DEFAULT NULL,
  `deal_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_account_pet` (`pet_id`),
  KEY `fk_account_petOwner` (`seller_id`),
  CONSTRAINT `fk_account_pet` FOREIGN KEY (`pet_id`) REFERENCES `pet` (`id`),
  CONSTRAINT `fk_account_petOwner` FOREIGN KEY (`seller_id`) REFERENCES `petowner` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('2', '1', '4', '1', '1', '5', '2016-08-20 00:00:00');
INSERT INTO `account` VALUES ('3', '1', '3', '1', '1', '5', '2016-08-20 00:00:00');
INSERT INTO `account` VALUES ('4', '1', '3', '1', '1', '5', '2016-09-10 00:00:00');
INSERT INTO `account` VALUES ('5', '2', '2', '2', '1', '3', '2016-09-10 00:00:00');
INSERT INTO `account` VALUES ('6', '2', '3', '1', '1', '3', '2016-10-15 00:00:00');
INSERT INTO `account` VALUES ('7', '1', '2', '2', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('8', '1', '4', '1', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('9', '1', '6', '1', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('10', '1', '3', '1', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('11', '1', '7', '1', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('12', '1', '3', '1', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('13', '1', '8', '2', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('14', '2', '5', '2', '1', '3', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('15', '2', '1', '1', '1', '3', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('16', '1', '5', '1', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('17', '1', '1', '1', '1', '5', '2009-12-28 00:00:00');
INSERT INTO `account` VALUES ('18', '1', '9', '1', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('19', '1', '9', '1', '2', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('20', '2', '1', '1', '1', '3', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('21', '2', '1', '1', '1', '3', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('22', '1', '1', '1', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('23', '2', '1', '1', '1', '3', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('24', '1', '1', '1', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('25', '2', '2', '1', '2', '3', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('26', '1', '2', '2', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('27', '1', '2', '2', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('28', '2', '3', '1', '1', '3', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('29', '2', '9', '2', '1', '3', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('30', '2', '8', '1', '1', '3', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('31', '1', '3', '1', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('32', '2', '3', '1', '1', '3', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('33', '2', '7', '1', '2', '3', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('34', '2', '1', '1', '1', '3', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('35', '2', '2', '1', '1', '3', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('36', '1', '3', '1', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('37', '1', '3', '1', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('38', '1', '7', '1', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('39', '1', '4', '1', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('40', '1', '4', '1', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('41', '2', '1', '1', '1', '3', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('42', '1', '1', '1', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('43', '1', '1', '1', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('44', '1', '1', '1', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('45', '1', '2', '2', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('46', '1', '3', '1', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('47', '1', '8', '2', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('48', '1', '1', '1', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('49', '1', '1', '1', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('50', '1', '1', '1', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('51', '1', '2', '2', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('52', '1', '11', '2', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('53', '1', '1', '1', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('54', '1', '2', '2', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('55', '1', '9', '1', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('56', '1', '10', '2', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('57', '1', '1', '1', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('58', '1', '11', '2', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('59', '1', '10', '2', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('60', '1', '1', '1', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('61', '2', '2', '1', '4', '3', '2009-12-29 00:00:00');
INSERT INTO `account` VALUES ('62', '1', '2', '2', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('63', '2', '2', '1', '2', '3', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('64', '1', '1', '1', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('65', '1', '2', '2', '1', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('66', '2', '1', '1', '1', '3', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('67', '1', '1', '1', '2', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('68', '1', '10', '2', '2', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('69', '1', '1', '1', '2', '5', '2017-08-07 00:00:00');
INSERT INTO `account` VALUES ('70', '1', '1', '1', '1', '5', '2017-08-07 00:00:00');

-- ----------------------------
-- Table structure for `pet`
-- ----------------------------
DROP TABLE IF EXISTS `pet`;
CREATE TABLE `pet` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` char(10) NOT NULL,
  `typeName` char(10) DEFAULT NULL,
  `health` int(4) DEFAULT NULL,
  `love` int(4) DEFAULT NULL,
  `birthday` timestamp NULL DEFAULT NULL,
  `owner_id` int(4) DEFAULT NULL,
  `store_id` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_pet_petOwner` (`owner_id`),
  KEY `fk_pet_petStore` (`store_id`),
  CONSTRAINT `fk_pet_petOwner` FOREIGN KEY (`owner_id`) REFERENCES `petowner` (`id`),
  CONSTRAINT `fk_pet_petStore` FOREIGN KEY (`store_id`) REFERENCES `petstore` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pet
-- ----------------------------
INSERT INTO `pet` VALUES ('1', '花花', 'dog', '1', '50', '2015-08-20 00:00:00', '1', null);
INSERT INTO `pet` VALUES ('2', '贝贝', 'penguin', '1', '60', '2015-08-20 00:00:00', '1', '2');
INSERT INTO `pet` VALUES ('3', '成成', 'dog', '1', '60', '2015-09-10 00:00:00', '1', '1');
INSERT INTO `pet` VALUES ('4', '露露', 'bird', '1', '70', '2016-01-10 00:00:00', '1', '1');
INSERT INTO `pet` VALUES ('5', '老虎', 'tiger', '1', '2', '2016-02-10 00:00:00', '1', '1');
INSERT INTO `pet` VALUES ('6', '老虎', 'tiger', '1', '2', '2016-03-15 00:00:00', '1', '1');
INSERT INTO `pet` VALUES ('7', '老虎', 'tiger', '1', '11', '2016-02-15 00:00:00', '1', '1');
INSERT INTO `pet` VALUES ('8', '狮子', 'lion', '1', '2', '2016-04-15 00:00:00', null, '2');
INSERT INTO `pet` VALUES ('9', '旺财', 'dog', '10', '10', '2017-08-07 00:00:00', null, '1');
INSERT INTO `pet` VALUES ('10', '含', 'dog', '12', '21', '2017-08-07 00:00:00', '2', '2');
INSERT INTO `pet` VALUES ('11', 'yf', 'dog', '50', '13', '2017-08-07 00:00:00', null, '2');
INSERT INTO `pet` VALUES ('12', '欢欢', 'penguim2', '100', '10', '2017-08-07 00:00:00', null, '2');
INSERT INTO `pet` VALUES ('13', 'tom', 'snake', '100', '20', '2017-08-07 00:00:00', null, '1');
INSERT INTO `pet` VALUES ('14', 'tom', '狮子', '20', '15', '2017-08-07 00:00:00', null, '1');
INSERT INTO `pet` VALUES ('15', 'tom', 'snake', '100', '40', '2017-08-07 00:00:00', null, '1');

-- ----------------------------
-- Table structure for `petowner`
-- ----------------------------
DROP TABLE IF EXISTS `petowner`;
CREATE TABLE `petowner` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `name` char(10) DEFAULT NULL,
  `password` char(10) DEFAULT NULL,
  `money` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of petowner
-- ----------------------------
INSERT INTO `petowner` VALUES ('1', '小明', '123456', '98');
INSERT INTO `petowner` VALUES ('2', '小强', '123456', '494');

-- ----------------------------
-- Table structure for `petstore`
-- ----------------------------
DROP TABLE IF EXISTS `petstore`;
CREATE TABLE `petstore` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(10) DEFAULT NULL,
  `password` char(10) DEFAULT NULL,
  `balance` int(4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of petstore
-- ----------------------------
INSERT INTO `petstore` VALUES ('1', '北京信息中心', '123456', '675');
INSERT INTO `petstore` VALUES ('2', '重庆观音桥', '123456', '836');
INSERT INTO `petstore` VALUES ('3', '小新', '123456', '2000');
INSERT INTO `petstore` VALUES ('4', '唐诗逸', '123456', '1997');
