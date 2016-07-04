/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.6.26 : Database - ttw
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ttw` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `ttw`;

/*Table structure for table `sys_authorities_resources_t` */

DROP TABLE IF EXISTS `sys_authorities_resources_t`;

CREATE TABLE `sys_authorities_resources_t` (
  `ID` varchar(38) NOT NULL,
  `RESOURCE_ID` varchar(38) NOT NULL COMMENT '资源ID',
  `AUTHORITY_ID` varchar(38) NOT NULL COMMENT '权限Id',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建日期',
  `CREATOR` varchar(100) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限资源表';

/*Table structure for table `sys_authorities_t` */

DROP TABLE IF EXISTS `sys_authorities_t`;

CREATE TABLE `sys_authorities_t` (
  `AUTHORITY_ID` varchar(38) NOT NULL COMMENT '权限Id',
  `AUTHORITY_MARK` varchar(100) NOT NULL COMMENT '权限标识\n            模块简称_操作权限，例如：USER_LIST，表示用户查询权限',
  `AUTHORITY_NAME` varchar(100) NOT NULL COMMENT '权限名称',
  `AUTHORITY_DESC` varchar(200) DEFAULT NULL COMMENT '权限说明',
  `MESSAGE` varchar(100) DEFAULT NULL COMMENT '提示信息',
  `ENABLE` decimal(2,0) DEFAULT NULL COMMENT '是否可用',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建日期',
  `CREATOR` varchar(100) DEFAULT NULL COMMENT '创建人',
  `LAST_UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改日期',
  `LAST_UPDATER` varchar(100) DEFAULT NULL COMMENT '修改人',
  `IS_DEL` decimal(1,0) DEFAULT NULL COMMENT '是否删除(用作逻辑删除)，0：未删除，1：已删除',
  `roleId` varchar(38) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`AUTHORITY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限';

/*Table structure for table `sys_resources_t` */

DROP TABLE IF EXISTS `sys_resources_t`;

CREATE TABLE `sys_resources_t` (
  `RESOURCE_ID` varchar(38) NOT NULL COMMENT '资源ID',
  `RESOURCE_TYPE` decimal(2,0) DEFAULT NULL COMMENT '1：URL\n            2：METHOD',
  `RESOURCE_NAME` varchar(100) NOT NULL COMMENT '资源名称',
  `RESOURCE_DESC` varchar(200) DEFAULT NULL COMMENT '资源描述',
  `RESOURCE_PATH` varchar(200) DEFAULT NULL COMMENT '资源路径',
  `PRIORITY` varchar(3) DEFAULT NULL COMMENT '优先级',
  `ENABLE` decimal(2,0) DEFAULT NULL COMMENT '是否可用',
  `MODULE_ID` varchar(38) DEFAULT NULL COMMENT '所属模块id',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建日期',
  `CREATOR` varchar(100) DEFAULT NULL COMMENT '创建人',
  `LAST_UPDATE_DATE` datetime DEFAULT NULL COMMENT '修改日期',
  `LAST_UPDATER` varchar(100) DEFAULT NULL COMMENT '修改人',
  `IS_DEL` decimal(1,0) DEFAULT NULL COMMENT '是否删除(用作逻辑删除)，0：未删除，1：已删除',
  PRIMARY KEY (`RESOURCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源';

/*Table structure for table `sys_roles_authorities_t` */

DROP TABLE IF EXISTS `sys_roles_authorities_t`;

CREATE TABLE `sys_roles_authorities_t` (
  `ID` varchar(38) NOT NULL,
  `AUTHORITY_ID` varchar(38) NOT NULL COMMENT '权限Id',
  `ROLE_ID` varchar(38) NOT NULL COMMENT '角色ID',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建日期',
  `CREATOR` varchar(100) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

/*Table structure for table `sys_roles_t` */

DROP TABLE IF EXISTS `sys_roles_t`;

CREATE TABLE `sys_roles_t` (
  `ROLE_ID` varchar(38) NOT NULL COMMENT '角色ID',
  `ROLE_NAME` varchar(100) NOT NULL COMMENT '角色名称',
  `ROLE_DESC` varchar(200) DEFAULT NULL COMMENT '角色说明',
  `ENABLE` decimal(2,0) DEFAULT NULL COMMENT '是否可用',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建日期',
  `CREATOR` varchar(100) DEFAULT NULL COMMENT '创建人',
  `LAST_UPDATE_DATE` datetime DEFAULT NULL COMMENT '最后修改日期',
  `LAST_UPDATER` varchar(100) DEFAULT NULL COMMENT '最后修改人',
  `IS_DEL` decimal(1,0) DEFAULT NULL COMMENT '是否删除(用作逻辑删除)，0：未删除，1：已删除',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

/*Table structure for table `sys_users_roles_t` */

DROP TABLE IF EXISTS `sys_users_roles_t`;

CREATE TABLE `sys_users_roles_t` (
  `ID` varchar(38) NOT NULL,
  `ROLE_ID` varchar(38) NOT NULL COMMENT '角色ID',
  `USER_ID` varchar(38) NOT NULL COMMENT '用户ID',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建日期',
  `CREATOR` varchar(100) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sys_users_t` */

DROP TABLE IF EXISTS `sys_users_t`;

CREATE TABLE `sys_users_t` (
  `USER_ID` varchar(38) NOT NULL COMMENT '用户ID',
  `NAME` varchar(100) NOT NULL COMMENT '用户名',
  `ACCOUNT` varchar(20) NOT NULL COMMENT '帐号',
  `PASSWORD` varchar(40) NOT NULL COMMENT '密码',
  `LAST_LOGIN_DATE` datetime DEFAULT NULL COMMENT '最后登录日期',
  `LOGOUT_DATE` datetime DEFAULT NULL COMMENT '注销日期',
  `LAST_LOGIN_IP` varchar(40) DEFAULT NULL COMMENT '最后登录IP',
  `ORG_ID` varchar(38) DEFAULT NULL COMMENT '部门id',
  `TYPE` varchar(10) DEFAULT NULL COMMENT '类型（1：TTW后台用户；2：会员）',
  `ENABLED` decimal(2,0) DEFAULT NULL COMMENT '1：可用\n            0：不可用',
  `ACCOUNT_NON_EXPIRED` decimal(2,0) DEFAULT NULL COMMENT '1：没过期\n            0：过期',
  `ACCOUNT_NON_LOCKED` decimal(2,0) DEFAULT NULL COMMENT '1：未锁定\n            0：已锁定',
  `CREDENTIALS_NON_EXPIRED` decimal(2,0) DEFAULT NULL COMMENT '1：未失效\n            0：已失效',
  `CREATE_DATE` datetime DEFAULT NULL COMMENT '创建日期',
  `CREATOR` varchar(100) DEFAULT NULL COMMENT '创建人',
  `LAST_UPDATE_DATE` datetime DEFAULT NULL COMMENT '最后修改日期',
  `LAST_UPDATER` varchar(100) DEFAULT NULL COMMENT '最后修改人',
  `IS_DEL` decimal(1,0) DEFAULT NULL COMMENT '是否删除(用作逻辑删除)，0：未删除，1：已删除',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='[系统管理]用户';

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
