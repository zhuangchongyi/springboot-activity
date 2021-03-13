/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : act_db

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 12/03/2021 10:25:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for leave_form
-- ----------------------------
DROP TABLE IF EXISTS `leave_form`;
CREATE TABLE `leave_form`  (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `proposer_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `proc_instan_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `agent_Id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `leave_content` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `reply` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of leave_form
-- ----------------------------
INSERT INTO `leave_form` VALUES ('2647736cacdbfd928c48d6701a4d2184', '1', NULL, '2', '请假申请', '请假事由', NULL, 1);
INSERT INTO `leave_form` VALUES ('272e29bcffad4497588fa82546e43c79', '1', '7541', '2', '请假申请', '请假事由', NULL, 1);
INSERT INTO `leave_form` VALUES ('2ff9b1942451ec52e926c6f73fd19cfa', '1', '7521', '2', '请假申请', '请假事由', NULL, 1);
INSERT INTO `leave_form` VALUES ('3ebfc530f05e6820723578c3d92bd67c', '1', '2501', '1', '请假申请', '请假事由', NULL, 2);
INSERT INTO `leave_form` VALUES ('6264eebbfbe9b19ec9e8989cbb8a4d1b', '1', '5001', '3', '请假申请', '请假事由', NULL, 2);
INSERT INTO `leave_form` VALUES ('be468c52a18145c3895376388f282848', '1', '7501', '2', '请假申请', '请假事由', NULL, 4);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pwd` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '123456', 'user', '张三', '1');
INSERT INTO `user` VALUES ('2', '123456', 'manager', '经理', '2');
INSERT INTO `user` VALUES ('3', '123456', 'boss', 'boss', '3');

SET FOREIGN_KEY_CHECKS = 1;
