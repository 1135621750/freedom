/*
Navicat MySQL Data Transfer

Source Server         : mysql5.7
Source Server Version : 50724
Source Host           : localhost:3306
Source Database       : freedom

Target Server Type    : MYSQL
Target Server Version : 50699
File Encoding         : 65001

Date: 2019-02-14 17:45:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
`id`  bigint(20) NOT NULL COMMENT 'id' ,
`pid`  bigint(20) NULL DEFAULT NULL COMMENT '父菜单id，1级id为0' ,
`menu_name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称' ,
`url`  varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单路径' ,
`remark`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单备注' ,
`perms`  varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单授权(多个用逗号分隔，如：user:list,user:create)' ,
`type`  int(11) NOT NULL COMMENT '类型   0：目录   1：菜单   2：按钮' ,
`icon`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标' ,
`sort`  int(11) NULL DEFAULT NULL COMMENT '排序' ,
`create_time`  datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP ,
`update_time`  datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP ,
`update_user`  bigint(20) NULL DEFAULT NULL ,
`create_user`  bigint(20) NULL DEFAULT NULL ,
`is_delete`  bit(1) NULL DEFAULT NULL ,
`status`  bit(1) NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='菜单'

;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES ('1', '0', '系统管理', '#', '初始一级目录', null, '0', null, '0', '2019-02-12 11:59:52', '2019-02-12 11:59:52', null, null, '', ''), ('2', '1', '用户管理', '/sysUser', '测试菜单', null, '1', null, '1', '2019-02-14 17:21:01', '2019-02-14 17:21:01', null, null, '', ''), ('3', '2', '新增', '', null, 'sys:user:add', '2', null, '2', '2019-02-14 17:15:03', '2019-02-14 17:15:03', null, null, '', ''), ('4', '2', '修改', '', null, 'sys:user:update', '2', null, '3', '2019-02-14 17:15:04', '2019-02-14 17:15:04', null, null, '', ''), ('5', '2', '删除', '', null, 'sys:user:delete', '2', null, '4', '2019-02-14 17:15:05', '2019-02-14 17:15:05', null, null, '', ''), ('6', '2', '查询', '', null, 'sys:user:list,sys:user:one', '2', null, '5', '2019-02-14 17:15:06', '2019-02-14 17:15:06', null, null, '', '');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
`id`  bigint(20) NOT NULL COMMENT 'id' ,
`role_name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称' ,
`remark`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注' ,
`create_time`  datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP ,
`update_time`  datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP ,
`update_user`  bigint(20) NULL DEFAULT NULL ,
`create_user`  bigint(20) NULL DEFAULT NULL ,
`is_delete`  bit(1) NULL DEFAULT NULL ,
`status`  bit(1) NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='角色表'

;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('1', '超级管理员', '超级管理员角色，项目最初角色', '2019-02-12 11:54:15', '2019-02-12 11:54:15', null, null, '', ''), ('2', '管理员', null, '2019-02-14 17:26:42', '2019-02-14 17:26:42', null, null, '', ''), ('3', '测试管理员', null, null, null, null, null, '', '');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
`role_id`  bigint(20) NOT NULL COMMENT '角色id' ,
`menu_id`  bigint(20) NOT NULL COMMENT '菜单id' ,
PRIMARY KEY (`role_id`, `menu_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='角色与菜单对应关系表'

;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES ('2', '1'), ('2', '2'), ('2', '3'), ('2', '4'), ('2', '5'), ('2', '6'), ('3', '6');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
`id`  bigint(20) NOT NULL ,
`user_name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名' ,
`nick_name`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称' ,
`password`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码' ,
`salt`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '盐' ,
`email`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱' ,
`mobile`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号' ,
`picture`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像' ,
`sex`  int(11) NULL DEFAULT NULL COMMENT '性别0:未设置1:男2:女' ,
`create_time`  datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP ,
`update_time`  datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP ,
`update_user`  bigint(20) NULL DEFAULT NULL ,
`create_user`  bigint(20) NULL DEFAULT NULL ,
`is_delete`  bit(1) NULL DEFAULT NULL COMMENT '是否删除0:删除1未删除' ,
`status`  bit(1) NULL DEFAULT NULL COMMENT '状态0:禁用1启用' ,
PRIMARY KEY (`id`),
UNIQUE INDEX `username_noly` (`user_name`) USING BTREE COMMENT '用户名唯一'
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='用户表'

;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('1', 'admin', '超级用户', '963e492d006c9b7e973ff0ad0f920d42f3e3e112c22b524ee7310dbb8624905a', 'freedom', '1135621750@qq.com', '18810425435', null, '1', '2019-02-14 16:36:14', '2019-02-14 16:36:14', null, null, '', ''), ('1095966793069105152', 'bbb', '测试1111', '963e492d006c9b7e973ff0ad0f920d42f3e3e112c22b524ee7310dbb8624905a', 'freedom', null, null, null, null, '2019-02-14 17:23:38', '2019-02-14 17:23:38', null, '1', '', '');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
`user_id`  bigint(20) NOT NULL COMMENT '用户id' ,
`role_id`  bigint(20) NOT NULL COMMENT 'j角色id' ,
PRIMARY KEY (`user_id`, `role_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
COMMENT='用户与角色对应关系表'

;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES ('1', '1'), ('1095966793069105152', '2'), ('1095966793069105152', '3');
COMMIT;
