-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.6.17 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 security_test 的数据库结构
CREATE DATABASE IF NOT EXISTS `security_test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `security_test`;

-- 导出  表 security_test.t_resource_info 结构
CREATE TABLE IF NOT EXISTS `t_resource_info` (
  `resource_id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_name` varchar(20) NOT NULL,
  `type` tinyint(1) NOT NULL COMMENT '1 menu 2 button',
  `url` varchar(500) NOT NULL,
  `parent_id` int(11) NOT NULL,
  `parent_ids` varchar(200) NOT NULL,
  `permision` varchar(50) DEFAULT NULL,
  `remark` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`resource_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- 正在导出表  security_test.t_resource_info 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_resource_info` DISABLE KEYS */;
INSERT INTO `t_resource_info` (`resource_id`, `resource_name`, `type`, `url`, `parent_id`, `parent_ids`, `permision`, `remark`) VALUES
	(1, '主页', 1, '/index', 0, '0/1', '/index', NULL),
	(2, 'Admin', 1, '/admin/*', 0, '0/2', '/admin/*', NULL),
	(3, '用户管理', 1, '/admin/user', 2, '0/2/3', '/admin/user', NULL),
	(4, '角色管理', 1, '/admin/role', 2, '0/2/4', '/admin/role', NULL),
	(5, '资源管理', 1, '/admin/resource', 2, '0/2/5', '/admin/resource', NULL);
/*!40000 ALTER TABLE `t_resource_info` ENABLE KEYS */;

-- 导出  表 security_test.t_role_info 结构
CREATE TABLE IF NOT EXISTS `t_role_info` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 正在导出表  security_test.t_role_info 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `t_role_info` DISABLE KEYS */;
INSERT INTO `t_role_info` (`role_id`, `role_name`) VALUES
	(1, 'ROLE_ANONYMOUS'),
	(2, 'ROLE_ADMIN'),
	(3, 'ROLE_VIP'),
	(4, 'ROLE_PLAIN');
/*!40000 ALTER TABLE `t_role_info` ENABLE KEYS */;

-- 导出  表 security_test.t_role_resource_link 结构
CREATE TABLE IF NOT EXISTS `t_role_resource_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  security_test.t_role_resource_link 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_role_resource_link` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_role_resource_link` ENABLE KEYS */;

-- 导出  表 security_test.t_user_info 结构
CREATE TABLE IF NOT EXISTS `t_user_info` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL DEFAULT '0',
  `password` varchar(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- 正在导出表  security_test.t_user_info 的数据：~1 rows (大约)
/*!40000 ALTER TABLE `t_user_info` DISABLE KEYS */;
INSERT INTO `t_user_info` (`user_id`, `username`, `password`) VALUES
	(1, 'admin', '123456');
/*!40000 ALTER TABLE `t_user_info` ENABLE KEYS */;

-- 导出  表 security_test.t_user_role_link 结构
CREATE TABLE IF NOT EXISTS `t_user_role_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  security_test.t_user_role_link 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `t_user_role_link` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_user_role_link` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
