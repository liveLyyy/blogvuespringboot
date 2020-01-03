/*
Navicat MySQL Data Transfer

Source Server         : root
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : blogvuespringboot

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-01-03 17:51:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `ip` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '登录IP',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of login
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(99) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户名 不能重复',
  `password` varchar(99) COLLATE utf8_unicode_ci NOT NULL COMMENT '密码',
  `phone` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '联系电话',
  `email` varchar(99) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '电子邮箱',
  `status` int(11) DEFAULT NULL COMMENT '用户状态 0 封禁 1 正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_role
-- ----------------------------
