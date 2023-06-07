/*
 Navicat Premium Data Transfer

 Source Server         : MySql
 Source Server Type    : MySQL
 Source Server Version : 50735
 Source Host           : localhost:3306
 Source Schema         : pmsystem

 Target Server Type    : MySQL
 Target Server Version : 50735
 File Encoding         : 65001

 Date: 11/02/2023 15:24:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admins
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins`  (
  `adminid` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`adminid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES ('20228736', 'admin', '管理员2');

-- ----------------------------
-- Table structure for papers
-- ----------------------------
DROP TABLE IF EXISTS `papers`;
CREATE TABLE `papers`  (
  `paperid` int(255) NOT NULL AUTO_INCREMENT,
  `filename` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teachermsg` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `date` datetime NULL DEFAULT NULL,
  `fileurl` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `studentid` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `teacherid` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`paperid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of papers
-- ----------------------------

-- ----------------------------
-- Table structure for students
-- ----------------------------
DROP TABLE IF EXISTS `students`;
CREATE TABLE `students`  (
  `studentid` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '123456',
  `username` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `department` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `specialty` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `studentclass` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `studenttelephone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `teacherid` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  PRIMARY KEY (`studentid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of students
-- ----------------------------
INSERT INTO `students` VALUES ('20201114001', '123456', '张三', '', '', '', '', '');
INSERT INTO `students` VALUES ('20201114269', '123456', '沈子异', '', '', '', '', '');
INSERT INTO `students` VALUES ('20201114270', '123456', '黄子异', '', '', '', '', '');
INSERT INTO `students` VALUES ('20201114271', '123456', '苏子韬', '', '', '', '', '');
INSERT INTO `students` VALUES ('20201114272', '123456', '严璐', '', '', '', '', '');
INSERT INTO `students` VALUES ('20201114273', '123456', '唐晓明', '', '', '', '', '');
INSERT INTO `students` VALUES ('20201114274', '123456', '郑岚', '', '', '', '', '');
INSERT INTO `students` VALUES ('20201114275', '123456', '戴子韬', '', '', '', '', '');
INSERT INTO `students` VALUES ('20201114276', '123456', '马睿', '', '', '', '', '');
INSERT INTO `students` VALUES ('20201114277', '123456', '于晓明', '', '', '', '', '');
INSERT INTO `students` VALUES ('20201114278', '123456', '陈秀英', '', '', '', '', '');
INSERT INTO `students` VALUES ('20201114279', '123456', '戴安琪', '', '', '', '', '');
INSERT INTO `students` VALUES ('20201114280', '123456', '魏岚', '', '', '', '', '');
INSERT INTO `students` VALUES ('20201115118', '123456', '任嘉伦', '', '', '', '', '');
INSERT INTO `students` VALUES ('20201115119', '123456', '汪子异', '', '', '', '', '');
INSERT INTO `students` VALUES ('20201115120', '123456', '邵子韬', '', '', '', '', '');
INSERT INTO `students` VALUES ('20201115121', '123456', '魏詩涵', '', '', '', '', '');
INSERT INTO `students` VALUES ('20201115122', '123456', '邱晓明', '', '', '', '', '');
INSERT INTO `students` VALUES ('20201115123', '123456', '侯嘉伦', '', '', '', '', '');
INSERT INTO `students` VALUES ('20201115124', '123456', '谢安琪', '', '', '', '', '');

-- ----------------------------
-- Table structure for teachers
-- ----------------------------
DROP TABLE IF EXISTS `teachers`;
CREATE TABLE `teachers`  (
  `teacherid` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '123456',
  `username` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `teachertitle` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `teachertelephone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `address` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  PRIMARY KEY (`teacherid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of teachers
-- ----------------------------
INSERT INTO `teachers` VALUES ('55350001', '111', '李四', '', '', '');

-- ----------------------------
-- Table structure for topics
-- ----------------------------
DROP TABLE IF EXISTS `topics`;
CREATE TABLE `topics`  (
  `topictitle` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题名',
  `topictype` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课题类型（老师发布/学生自拟）',
  `topicdepict` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '课题描述',
  `topicstatus` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '课题状态（已通过/未通过/未审核）',
  `studentid` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '选择此课题的学生id',
  `teacherid` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理此课题的老师',
  PRIMARY KEY (`topictitle`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of topics
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
