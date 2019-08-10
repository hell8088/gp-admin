/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50638
Source Host           : localhost:3306
Source Database       : gp_admin

Target Server Type    : MYSQL
Target Server Version : 50638
File Encoding         : 65001

Date: 2019-07-29 17:50:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `shiro_resource`
-- ----------------------------
DROP TABLE IF EXISTS `shiro_resource`;
CREATE TABLE `shiro_resource` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `creater` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` int(11) DEFAULT NULL COMMENT '数据状态 1:已删除；0：未删除',
  `pid` bigint(20) DEFAULT NULL COMMENT '父节点',
  `level` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '节点级别',
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '资源名称',
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '资源地址',
  `res_desc` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '资源描述',
  `res_type` int(11) DEFAULT NULL COMMENT '资源类型',
  `code` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '资源代码',
  `ext1` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '扩展1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='资源表';

-- ----------------------------
-- Records of shiro_resource
-- ----------------------------
INSERT INTO `shiro_resource` VALUES ('1', 'admin', 'admin', '2019-05-24 15:05:47', '2019-05-24 15:05:50', '0', '0', '0', 'feature-admin', null, null, null, null, null);
INSERT INTO `shiro_resource` VALUES ('2', 'wjh_feature', 'wjh_feature', '2019-05-24 15:06:19', '2019-05-24 15:06:29', '0', '1', null, '系统管理', '', '', null, '', null);
INSERT INTO `shiro_resource` VALUES ('3', 'wjh_feature', 'wjh_feature', '2019-05-24 15:06:31', '2019-05-24 15:07:45', '0', '2', null, '用户管理', '/user/list', '用户管理', null, '', null);
INSERT INTO `shiro_resource` VALUES ('4', 'wjh_feature', 'wjh_feature', '2019-05-24 15:07:47', '2019-05-24 15:08:04', '0', '2', null, '角色管理', '/role/list', '角色管理', null, '', null);
INSERT INTO `shiro_resource` VALUES ('5', 'wjh_feature', 'wjh_feature', '2019-05-24 15:08:06', '2019-05-24 15:08:19', '0', '2', null, '资源管理', '/resource/list', '资源管理', null, '', null);
INSERT INTO `shiro_resource` VALUES ('6', 'wjh_feature', 'wjh_feature', '2019-05-24 17:52:23', '2019-05-24 17:54:04', '1', '5', null, '文件管理', '', '文件管理', null, '', null);
INSERT INTO `shiro_resource` VALUES ('7', 'wjh_feature', 'wjh_feature', '2019-05-24 17:54:15', '2019-05-24 18:04:15', '1', '2', null, '权限管理', '', '', null, '', null);
INSERT INTO `shiro_resource` VALUES ('8', 'wjh_feature', 'wjh_feature', '2019-05-24 17:54:50', '2019-05-24 18:04:24', '1', '7', null, '角色与权限', '/resource/permission', '角色与权限', null, '', null);

-- ----------------------------
-- Table structure for `shiro_role`
-- ----------------------------
DROP TABLE IF EXISTS `shiro_role`;
CREATE TABLE `shiro_role` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `creater` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` int(11) DEFAULT NULL COMMENT '数据状态 1:已删除；0：未删除',
  `role_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '角色名',
  `role_desc` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色表';

-- ----------------------------
-- Records of shiro_role
-- ----------------------------
INSERT INTO `shiro_role` VALUES ('1', 'admin', 'admin', '2019-05-08 08:57:07', '2019-05-08 08:57:09', '0', '超级管理员', '超级管理员');
INSERT INTO `shiro_role` VALUES ('2', 'wjh_feature', 'wjh_feature', '2019-05-13 12:15:09', '2019-05-13 14:50:20', '0', 'guest', 'guest');
INSERT INTO `shiro_role` VALUES ('3', 'wjh_feature', 'wjh_feature', '2019-05-13 12:19:28', '2019-05-13 12:19:31', '1', 'guest', 'guest2');
INSERT INTO `shiro_role` VALUES ('4', 'wjh_feature', 'wjh_feature', '2019-05-14 12:16:47', '2019-05-14 12:16:47', '0', 'test', 'test');

-- ----------------------------
-- Table structure for `shiro_role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `shiro_role_resource`;
CREATE TABLE `shiro_role_resource` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `creater` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` int(11) DEFAULT NULL COMMENT '数据状态 1:已删除；0：未删除',
  `res_id` bigint(64) DEFAULT NULL COMMENT '资源Id',
  `role_id` bigint(64) DEFAULT NULL COMMENT '角色Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户角色关系表';

-- ----------------------------
-- Records of shiro_role_resource
-- ----------------------------

-- ----------------------------
-- Table structure for `shiro_user`
-- ----------------------------
DROP TABLE IF EXISTS `shiro_user`;
CREATE TABLE `shiro_user` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `creater` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` int(11) DEFAULT NULL COMMENT '数据状态 1:已删除；0：未删除',
  `user_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';

-- ----------------------------
-- Records of shiro_user
-- ----------------------------
INSERT INTO `shiro_user` VALUES ('1', 'wjh', 'wjh', '2019-01-17 15:25:31', '2019-05-10 17:11:17', '0', 'admin', 'admin');
INSERT INTO `shiro_user` VALUES ('2', 'wjh', 'wjh_feature', '2019-02-15 14:05:40', '2019-05-10 17:11:18', '0', 'wjh', '7654');
INSERT INTO `shiro_user` VALUES ('6', 'wjh_feature', 'wjh_feature', '2019-05-09 18:22:42', '2019-05-10 17:11:18', '0', 'ADFAD', 'SDFASDF');
INSERT INTO `shiro_user` VALUES ('7', 'wjh_feature', 'wjh_feature', '2019-05-09 19:44:09', '2019-05-10 17:11:19', '0', 'cdsaw', 'dsafe4waewf');
INSERT INTO `shiro_user` VALUES ('10', 'wjh_feature', 'wjh_feature', '2019-05-09 20:28:54', '2019-05-10 17:24:40', '1', '1qa', '213');
INSERT INTO `shiro_user` VALUES ('26', 'wjh_feature', 'wjh_feature', '2019-05-09 20:47:31', '2019-05-10 17:24:39', '1', 'ccc', 'ccc');
INSERT INTO `shiro_user` VALUES ('31', 'wjh_feature', 'wjh_feature', '2019-05-10 14:41:04', '2019-05-10 17:24:33', '1', 'fasfewa', 'asdewWD');
INSERT INTO `shiro_user` VALUES ('34', 'wjh_feature', 'wjh_feature', '2019-05-14 12:16:35', '2019-05-14 12:16:35', '0', 'aaa', '111');
INSERT INTO `shiro_user` VALUES ('35', 'wjh_feature', 'wjh_feature', '2019-05-14 16:28:27', '2019-05-14 16:28:27', '0', '特殊', '123456');
INSERT INTO `shiro_user` VALUES ('36', 'wjh_feature', 'wjh_feature', '2019-07-12 03:23:01', '2019-07-12 03:23:01', '0', 'test1', 'test1');
INSERT INTO `shiro_user` VALUES ('37', 'wjh_feature', 'wjh_feature', '2019-07-12 03:23:05', '2019-07-12 03:23:05', '0', 'test2', 'test2');
INSERT INTO `shiro_user` VALUES ('38', 'wjh_feature', 'wjh_feature', '2019-07-12 03:23:13', '2019-07-12 03:23:13', '0', 'test3', 'test3');
INSERT INTO `shiro_user` VALUES ('39', 'wjh_feature', 'wjh_feature', '2019-07-12 03:23:18', '2019-07-12 03:23:18', '0', 'test4', 'test4');
INSERT INTO `shiro_user` VALUES ('40', 'wjh_feature', 'wjh_feature', '2019-07-12 03:23:45', '2019-07-12 03:23:45', '0', 'test5', 'test5');

-- ----------------------------
-- Table structure for `shiro_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `shiro_user_role`;
CREATE TABLE `shiro_user_role` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `creater` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` int(11) DEFAULT NULL COMMENT '数据状态 1:已删除；0：未删除',
  `user_id` bigint(64) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(64) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户角色关系表';

-- ----------------------------
-- Records of shiro_user_role
-- ----------------------------
INSERT INTO `shiro_user_role` VALUES ('1', 'wjh_feature', 'wjh_feature', '2019-05-14 11:14:49', '2019-05-14 11:14:49', '0', '7', '2');
INSERT INTO `shiro_user_role` VALUES ('2', 'wjh_feature', 'wjh_feature', '2019-05-14 11:17:02', '2019-05-14 16:10:56', '1', '1', '1');
INSERT INTO `shiro_user_role` VALUES ('3', 'wjh_feature', 'wjh_feature', '2019-05-14 11:17:02', '2019-05-14 16:10:56', '1', '1', '2');
INSERT INTO `shiro_user_role` VALUES ('4', 'wjh_feature', 'wjh_feature', '2019-05-14 12:37:27', '2019-05-14 12:37:27', '0', '2', '1');
INSERT INTO `shiro_user_role` VALUES ('5', 'wjh_feature', 'wjh_feature', '2019-05-14 12:40:47', '2019-05-14 12:40:47', '0', '2', '2');
INSERT INTO `shiro_user_role` VALUES ('6', 'wjh_feature', 'wjh_feature', '2019-05-14 12:40:47', '2019-05-14 12:40:47', '0', '2', '4');
INSERT INTO `shiro_user_role` VALUES ('7', 'wjh_feature', 'wjh_feature', '2019-05-14 12:46:36', '2019-05-14 12:46:36', '0', '6', '2');
INSERT INTO `shiro_user_role` VALUES ('8', 'wjh_feature', 'wjh_feature', '2019-05-14 12:46:55', '2019-05-14 12:46:55', '0', '6', '2');
INSERT INTO `shiro_user_role` VALUES ('9', 'wjh_feature', 'wjh_feature', '2019-05-14 12:46:55', '2019-05-14 12:46:55', '0', '6', '4');
INSERT INTO `shiro_user_role` VALUES ('10', 'wjh_feature', 'wjh_feature', '2019-05-14 16:10:56', '2019-05-14 16:11:02', '1', '1', '1');
INSERT INTO `shiro_user_role` VALUES ('11', 'wjh_feature', 'wjh_feature', '2019-05-14 16:10:56', '2019-05-14 16:11:02', '1', '1', '2');
INSERT INTO `shiro_user_role` VALUES ('12', 'wjh_feature', 'wjh_feature', '2019-05-14 16:10:56', '2019-05-14 16:11:02', '1', '1', '4');
INSERT INTO `shiro_user_role` VALUES ('13', 'wjh_feature', 'wjh_feature', '2019-05-14 16:11:02', '2019-05-14 16:11:02', '0', '1', '4');
INSERT INTO `shiro_user_role` VALUES ('14', 'wjh_feature', 'wjh_feature', '2019-05-14 16:28:39', '2019-05-14 16:28:39', '0', '35', '2');

-- ----------------------------
-- Table structure for `sys_resource`
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `creater` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` int(11) DEFAULT NULL COMMENT '数据状态 1:已删除；0：未删除',
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '资源名称',
  `type` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '资源类型',
  `url` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '资源路径',
  `permission` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '资源码',
  `parent_id` bigint(64) DEFAULT NULL COMMENT '父编号',
  `parent_ids` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '父编号列表',
  `icon` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '图标样式',
  `priority` int(11) DEFAULT NULL COMMENT '优先级',
  `available` bit(1) DEFAULT NULL COMMENT '是否可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='资源管理';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1', null, null, '2017-12-28 20:23:06', '2019-07-17 10:01:04', '0', '资源', 'menu', '', null, '0', '0/', null, '0', '');
INSERT INTO `sys_resource` VALUES ('2', null, null, '2017-12-29 08:43:53', '2017-12-29 12:58:40', '0', '系统管理', 'menu', null, null, '1', '0/1/', 'fa fa-desktop', '99', '');
INSERT INTO `sys_resource` VALUES ('3', null, null, '2017-12-29 08:45:57', '2019-07-17 17:44:35', '0', '资源管理', 'menu', '/resource/list', 'resource:*', '2', '0/1/2/', null, '1', '');
INSERT INTO `sys_resource` VALUES ('4', null, null, '2017-12-29 08:46:23', '2019-07-17 17:44:51', '0', '角色管理', 'menu', '/role/list', 'role:*', '2', '0/1/2/', null, '2', '');
INSERT INTO `sys_resource` VALUES ('5', null, null, '2017-12-29 08:46:41', '2019-07-17 17:37:47', '0', '用户管理', 'menu', '/user/list', 'user:*', '2', '0/1/2/', null, '3', '');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `creater` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` int(11) DEFAULT NULL COMMENT '数据状态 1:已删除；0：未删除',
  `role` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '角色标识',
  `description` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '角色描述',
  `resource_ids` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '拥有的资源',
  `available` bit(1) DEFAULT NULL COMMENT '是否可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色管理';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', 'admin', 'admin', '2019-07-23 06:10:05', '2019-07-23 06:10:05', '0', 'admin', '超级管理员', '1,2,3,5,', '');
INSERT INTO `sys_role` VALUES ('11', 'admin', 'admin', '2019-07-22 10:58:26', '2019-07-22 10:58:26', '0', 'test1', 'test1', '1,2,5,', '');
INSERT INTO `sys_role` VALUES ('12', 'admin', 'admin', '2019-07-22 11:09:37', '2019-07-22 11:09:37', '0', 'wjh', 'wjh2', '1,2,4,', '');
INSERT INTO `sys_role` VALUES ('13', 'admin', 'admin', '2019-07-22 11:09:55', '2019-07-23 11:30:41', '0', 'llx', 'llx', '1,2,3,4,5,', '');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `creater` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '创建人',
  `modifier` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '修改人',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `deleted` int(11) DEFAULT NULL COMMENT '数据状态 1:已删除；0：未删除',
  `username` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `role_ids` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '角色列表',
  `locked` bit(1) DEFAULT NULL COMMENT '是否可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户管理';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('2', null, 'yushikai3', '2018-04-10 16:25:24', '2018-05-29 16:28:37', '0', 'yushikai3', 'yushikai3', '1,2,', '');
INSERT INTO `sys_user` VALUES ('3', 'yushikai3', 'yushikai3', '2018-04-10 17:31:33', '2018-05-23 14:53:35', '0', 'wangzhimin8', 'wangzhimin8', '1,', '');
INSERT INTO `sys_user` VALUES ('4', null, null, '2018-05-23 14:52:04', '2018-05-23 14:53:33', '0', 'duanmanfu', 'duanmanfu', '1,', '');
INSERT INTO `sys_user` VALUES ('5', null, null, '2018-05-23 14:52:27', '2018-05-23 14:53:36', '0', 'liuwenlong21', 'liuwenlong21', '1,', '');
INSERT INTO `sys_user` VALUES ('6', null, null, '2018-05-23 14:52:40', '2018-05-23 14:53:38', '0', 'wangjiehan', 'wangjiehan', '1,', '');
INSERT INTO `sys_user` VALUES ('7', null, 'zhouweili3', '2018-05-23 14:52:55', '2018-06-11 15:02:50', '0', 'zhouweili3', 'zhouweili3', '1,2,', '');
INSERT INTO `sys_user` VALUES ('8', null, 'wangtianyu9', '2018-05-23 14:53:05', '2018-06-05 10:26:45', '0', 'wangtianyu9', 'wangtianyu9', '1,2,', '');
INSERT INTO `sys_user` VALUES ('9', null, null, '2018-05-23 14:54:15', '2018-05-23 14:54:25', '0', 'bjadmin', 'bjadmin', '1,', '');
INSERT INTO `sys_user` VALUES ('10', null, null, '2018-05-23 15:51:20', '2018-05-23 15:51:26', '0', 'bjsunge', 'bjsunge', '1,', '');
INSERT INTO `sys_user` VALUES ('11', 'yushikai3', 'yushikai3', '2018-05-29 16:28:49', '2018-05-29 16:28:49', '0', 'zhangkaixiang6', null, '2,', '');
INSERT INTO `sys_user` VALUES ('12', 'yushikai3', 'yushikai3', '2018-06-04 10:34:03', '2018-06-04 10:59:24', '1', 'wangying328', null, '1,2,', '');
INSERT INTO `sys_user` VALUES ('13', 'yushikai3', 'yushikai3', '2018-06-04 11:03:31', '2018-06-04 11:03:56', '1', 'wangying328', null, '1,2,', '');
INSERT INTO `sys_user` VALUES ('14', 'wangtianyu9', 'wangtianyu9', '2018-06-13 15:58:35', '2018-06-13 15:58:35', '0', 'sunbo10', null, '2,', '');
INSERT INTO `sys_user` VALUES ('15', 'addUser', 'updateUser', '2019-07-23 10:28:49', '2019-07-29 09:35:41', '0', 'testuser1', '', '1,', '');
