SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
	`name` varchar(255) DEFAULT NULL,
	`status` tinyint(1) DEFAULT '1' COMMENT '角色状态，0 禁用， 1 启用',
	`gmt_create` datetime DEFAULT NULL COMMENT 'create time',
  `gmt_modified` datetime DEFAULT NULL COMMENT 'modified time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `role_desc` varchar(255) DEFAULT NULL COMMENT '角色中文描述',
  `role_state` tinyint(1) DEFAULT '1' COMMENT '角色状态，0 禁用， 1 启用',
  `gmt_create` datetime DEFAULT NULL COMMENT 'create time',
  `gmt_modified` datetime DEFAULT NULL COMMENT 'modified time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for right
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(255) DEFAULT NULL COMMENT '权限名称',
  `permission_desc` varchar(255) DEFAULT NULL COMMENT '权限描述',
	`permission_url` varchar(255) DEFAULT NULL COMMENT '权限路由',
  `permission_state` tinyint(1) DEFAULT '1' COMMENT '权限状态，0 禁用， 1 启用',
  `gmt_create` datetime DEFAULT NULL COMMENT 'create time',
  `gmt_modified` datetime DEFAULT NULL COMMENT 'modified time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int unsigned DEFAULT NULL,
  `role_id` int unsigned DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL COMMENT 'create time',
  `gmt_modified` datetime DEFAULT NULL COMMENT 'modified time',
  PRIMARY KEY (`id`),
  KEY `fk_user_role_user_1` (`user_id`),
  KEY `fk_user_role_role_1` (`role_id`),
  CONSTRAINT `fk_user_role_role_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user_role_user_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- ----------------------------
-- Table structure for role_right
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int unsigned DEFAULT NULL,
  `permission_id` int unsigned DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL COMMENT 'create time',
  `gmt_modified` datetime DEFAULT NULL COMMENT 'modified time',
  PRIMARY KEY (`id`),
  KEY `fk_role_permission_role_1` (`role_id`),
  KEY `fk_role_permission_permission_1` (`permission_id`),
  CONSTRAINT `fk_role_permission_role_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_role_permission_permission_1` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- ----------------------------
-- Table structure for person_info
-- ----------------------------
DROP TABLE IF EXISTS `person_info`;
CREATE TABLE `person_info` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int unsigned DEFAULT '0' COMMENT 'user id',
  `gender` char(2) DEFAULT '0' COMMENT '男：1， 女：0',
  `age` tinyint unsigned DEFAULT '0' COMMENT 'user age',
  `region` varchar(255) DEFAULT NULL COMMENT 'user region',
  `pref` tinyint DEFAULT '0' COMMENT 'pre cool -1, pref normal 0, pref warm 1',
  `sen` tinyint DEFAULT '0' COMMENT 'insensitive -1, normal 0, sensitive 1',
  `eval` tinyint DEFAULT '0' COMMENT 'insensitive -1, normal 0, sensitive 1',
  `imp_indoor_tmp` tinyint unsigned DEFAULT '0' COMMENT '1 very unimportant, 2 unimportant, 3 noraml, 4 important, 5 very important ',
  `imp_indoor_rh` tinyint unsigned DEFAULT '0' COMMENT '1 very unimportant, 2 unimportant, 3 noraml, 4 important, 5 very important ',
  `imp_indoor_aq` tinyint unsigned DEFAULT '0' COMMENT '1 very unimportant, 2 unimportant, 3 noraml, 4 important, 5 very important ',
  `imp_indoor_av` tinyint unsigned DEFAULT '0' COMMENT '1 very unimportant, 2 unimportant, 3 noraml, 4 important, 5 very important ',
  `imp_weather` tinyint unsigned DEFAULT '0' COMMENT '1 very unimportant, 2 unimportant, 3 noraml, 4 important, 5 very important ',
  `imp_outdoor_tmp` tinyint unsigned DEFAULT '0' COMMENT '1 very unimportant, 2 unimportant, 3 noraml, 4 important, 5 very important ',
  `imp_outdoor_rh` tinyint unsigned DEFAULT '0' COMMENT '1 very unimportant, 2 unimportant, 3 noraml, 4 important, 5 very important ',
  `imp_mood` tinyint unsigned DEFAULT '0' COMMENT '1 very unimportant, 2 unimportant, 3 noraml, 4 important, 5 very important ',
  `tolerance` tinyint unsigned DEFAULT '0' COMMENT '1 very unimportant, 2 unimportant, 3 noraml, 4 important, 5 very important ',
  `gmt_create` datetime DEFAULT NULL COMMENT 'create time',
  `gmt_modified` datetime DEFAULT NULL COMMENT 'modified time',
  PRIMARY KEY (`id`),
  KEY `fk_user_person_info_1` (`user_id`),
  CONSTRAINT `fk_user_person_info_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `person_info_chk_1` CHECK ((`gender` in (_utf8mb4'0',_utf8mb4'1'))),
  CONSTRAINT `person_info_chk_10` CHECK ((`imp_outdoor_tmp` in (1,2,3,4,5))),
  CONSTRAINT `person_info_chk_11` CHECK ((`imp_outdoor_rh` in (1,2,3,4,5))),
  CONSTRAINT `person_info_chk_12` CHECK ((`imp_mood` in (1,2,3,4,5))),
  CONSTRAINT `person_info_chk_13` CHECK ((`tolerance` in (1,2,3,4,5))),
  CONSTRAINT `person_info_chk_2` CHECK ((`pref` in (-(1),0,1))),
  CONSTRAINT `person_info_chk_3` CHECK ((`sen` in (-(1),0,1))),
  CONSTRAINT `person_info_chk_4` CHECK ((`eval` in (-(1),0,1))),
  CONSTRAINT `person_info_chk_5` CHECK ((`imp_indoor_tmp` in (1,2,3,4,5))),
  CONSTRAINT `person_info_chk_6` CHECK ((`imp_indoor_rh` in (1,2,3,4,5))),
  CONSTRAINT `person_info_chk_7` CHECK ((`imp_indoor_aq` in (1,2,3,4,5))),
  CONSTRAINT `person_info_chk_8` CHECK ((`imp_indoor_av` in (1,2,3,4,5))),
  CONSTRAINT `person_info_chk_9` CHECK ((`imp_weather` in (1,2,3,4,5)))
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ------------------------------------
-- Table structure for person_feedback
-- ------------------------------------
DROP TABLE IF EXISTS `person_feedback`;
CREATE TABLE `person_feedback` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int unsigned DEFAULT '0' COMMENT 'user id',
  `thermal_sen` float DEFAULT '0' COMMENT '-3~3',
  `humid_sen` float DEFAULT '0' COMMENT '-3~3',
  `thermal_accept` tinyint DEFAULT '0' COMMENT 'unaccept 0,accept 1',
  `thermal_comfort` tinyint DEFAULT '0' COMMENT 'uncomfort 0,comfort 1',
  `av_accept` tinyint DEFAULT '0' COMMENT 'unaccept 0,accept 1',
  `thermal_pref` tinyint DEFAULT '0' COMMENT 'cooler -1, unchange 0, warmer 1',
  `mood` tinyint DEFAULT '0' COMMENT 'negative -1, normal 0, positive 1',
  `gmt_create` datetime DEFAULT NULL COMMENT 'create time',
  PRIMARY KEY (`id`),
  KEY `fk_user_person_feedback_1` (`user_id`),
  CONSTRAINT `fk_user_person_feedback_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `person_feedback_chk_1` CHECK ((`thermal_accept` in (0,1))),
  CONSTRAINT `person_feedback_chk_2` CHECK ((`thermal_comfort` in (0,1))),
  CONSTRAINT `person_feedback_chk_3` CHECK ((`av_accept` in (0,1))),
  CONSTRAINT `person_feedback_chk_4` CHECK ((`thermal_pref` in (-(1),0,1))),
  CONSTRAINT `person_feedback_chk_5` CHECK ((`mood` in (-(1),0,1)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `url` varchar(255) DEFAULT NULL COMMENT '路由',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `name_zh` varchar(255) DEFAULT NULL COMMENT '中文描述',
  `icon_cls` varchar(255) DEFAULT NULL COMMENT 'icons',
  `component` varchar(255) DEFAULT NULL COMMENT 'component name',
  `parent_id` int(11) DEFAULT NULL COMMENT 'parent id',
	`gmt_create` datetime DEFAULT NULL COMMENT 'create time',
  `gmt_modified` datetime DEFAULT NULL COMMENT 'modified time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT 'role id',
  `menu_id` int(11) DEFAULT NULL COMMENT 'menu id',
	`gmt_create` datetime DEFAULT NULL COMMENT 'create time',
  `gmt_modified` datetime DEFAULT NULL COMMENT 'modified time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;