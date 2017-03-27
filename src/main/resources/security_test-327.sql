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

-- 正在导出表  security_test.t_resource_info 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `t_resource_info` DISABLE KEYS */;
INSERT INTO `t_resource_info` (`resource_id`, `resource_name`, `type`, `url`, `parent_id`, `parent_ids`, `permision`, `remark`) VALUES
	(1, 'HOME', 1, '/index', 0, '0/', '/index', NULL),
	(2, 'Admin', 1, '#', 0, '0/', '/admin/*', NULL),
	(3, '用户管理', 1, '/admin/user', 2, '0/2/', '/admin/user/*', NULL),
	(4, '角色管理', 1, '/admin/role', 2, '0/2/', '/admin/role/*', NULL),
	(5, '资源管理', 1, '/admin/resource', 2, '0/2/', '/admin/resource/*', NULL);
/*!40000 ALTER TABLE `t_resource_info` ENABLE KEYS */;

-- 导出  表 security_test.t_role_info 结构
CREATE TABLE IF NOT EXISTS `t_role_info` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(30) DEFAULT NULL,
  `description` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- 正在导出表  security_test.t_role_info 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `t_role_info` DISABLE KEYS */;
INSERT INTO `t_role_info` (`role_id`, `role_name`, `description`) VALUES
	(1, 'ROLE_ANONYMOUS', '游客'),
	(2, 'ROLE_ADMIN', '管理员'),
	(3, 'ROLE_VIP', 'vip'),
	(4, 'ROLE_PLAIN', '普通用户');
/*!40000 ALTER TABLE `t_role_info` ENABLE KEYS */;

-- 导出  表 security_test.t_role_resource_link 结构
CREATE TABLE IF NOT EXISTS `t_role_resource_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- 正在导出表  security_test.t_role_resource_link 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `t_role_resource_link` DISABLE KEYS */;
INSERT INTO `t_role_resource_link` (`id`, `role_id`, `resource_id`) VALUES
	(1, 2, 1),
	(2, 2, 2),
	(3, 1, 1);
/*!40000 ALTER TABLE `t_role_resource_link` ENABLE KEYS */;

-- 导出  表 security_test.t_user_info 结构
CREATE TABLE IF NOT EXISTS `t_user_info` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL DEFAULT '0',
  `password` varchar(20) NOT NULL DEFAULT '0',
  `salt` varchar(6) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT '0' COMMENT '0 不锁定 1锁定',
  `expire_at` bigint(13) unsigned DEFAULT '0' COMMENT '0 永久账号',
  `status` tinyint(1) DEFAULT '0' COMMENT '0 正常 9 删除',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- 正在导出表  security_test.t_user_info 的数据：~10 rows (大约)
/*!40000 ALTER TABLE `t_user_info` DISABLE KEYS */;
INSERT INTO `t_user_info` (`user_id`, `username`, `password`, `salt`, `locked`, `expire_at`, `status`) VALUES
	(1, 'admin', '123456', NULL, 0, 0, 0),
	(2, 'cjk', '123456', NULL, 0, 0, 0),
	(3, 'test', 'test', NULL, 1, 0, 0),
	(6, 'abc', 'abc', NULL, 1, 0, 0),
	(8, 'abcd', 'abc', NULL, 1, 0, 0),
	(9, 'abcde', 'abc', NULL, 1, 0, 0),
	(11, 'bad', 'abc', NULL, 1, 0, 0),
	(12, 'bada', 'abc', NULL, 1, 0, 0),
	(14, 'tttt', '123', NULL, 1, 0, 9),
	(16, 'aaabc', 'abc', NULL, 1, 0, 0);
/*!40000 ALTER TABLE `t_user_info` ENABLE KEYS */;

-- 导出  表 security_test.t_user_role_link 结构
CREATE TABLE IF NOT EXISTS `t_user_role_link` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- 正在导出表  security_test.t_user_role_link 的数据：~9 rows (大约)
/*!40000 ALTER TABLE `t_user_role_link` DISABLE KEYS */;
INSERT INTO `t_user_role_link` (`id`, `user_id`, `role_id`) VALUES
	(1, 1, 2),
	(2, 11, 1),
	(3, 11, 2),
	(4, 12, 1),
	(5, 12, 2),
	(8, 16, 1),
	(9, 16, 2),
	(10, 14, 1),
	(11, 14, 3);
/*!40000 ALTER TABLE `t_user_role_link` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
