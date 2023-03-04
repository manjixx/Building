SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for device
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
	`device_id` varchar(255) NOT NULL COMMENT '设备编号',
	`device_name` varchar(255) DEFAULT NULL COMMENT '设备名称',
	`device_type`  tinyint(1) DEFAULT '1' COMMENT '设备类型，0：空调， 1：六合一传感器， 2：二合一传感器，3：风速计',
	`device_status` tinyint(1) DEFAULT '1' COMMENT '设备状态，0 禁用， 1 启用',
	`device_health` tinyint(1) DEFAULT '1' COMMENT '设备健康状况，0：在线， 1：离线，2：告警，3：故障',
  `location` varchar(255) DEFAULT NULL COMMENT '设备位置',
	`gmt_create` datetime DEFAULT NULL COMMENT 'create time',
  `gmt_modified` datetime DEFAULT NULL COMMENT 'modified time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for indoor_enviornment
-- ----------------------------
DROP TABLE IF EXISTS `indoor_enviornment`;
CREATE TABLE `indoor_enviornment` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `device_id` bigint NOT NULL COMMENT '设备表编号',
  `temp` decimal(3,1) DEFAULT NULL COMMENT '室内温度',
	`humid` decimal(4,1) DEFAULT NULL COMMENT '室内湿度',
	`air_vel` decimal(3,1) DEFAULT NULL COMMENT '室内风速',
	`co2` int(11) DEFAULT NULL COMMENT '二氧化碳浓度',
	`pm25` int(11) DEFAULT NULL COMMENT 'PM25浓度',
	`pm1` int(11) DEFAULT NULL COMMENT 'PM1浓度',
	`pm10` int(11) DEFAULT NULL COMMENT 'PM10浓度',
  `time` datetime DEFAULT NULL COMMENT 'time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- ----------------------------
-- Table structure for outdoor_enviornment
-- ----------------------------
DROP TABLE IF EXISTS `outdoor_enviornment`;
CREATE TABLE `outdoor_enviornment` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `temp` decimal(3,1) DEFAULT NULL COMMENT '室内温度',
	`humid` decimal(4,1) DEFAULT NULL COMMENT '室内湿度',
	`air_vel` decimal(3,1) DEFAULT NULL COMMENT '室内风速',
	`pm25` int(11) DEFAULT NULL COMMENT 'PM25浓度',
	`weather` int(11) DEFAULT NULL COMMENT '天气',
	`location` varchar(255) DEFAULT NULL COMMENT '地理位置',
  `time` datetime DEFAULT NULL COMMENT 'time',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- ----------------------------
-- Table structure for air_condition
-- ----------------------------
DROP TABLE IF EXISTS `air_condition`;
CREATE TABLE `air_condition` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `device_id` bigint unsigned NOT NULL COMMENT '空调编号',
	`mode` tinyint(1) DEFAULT NULL COMMENT '空调模式，0：制热模式，1:制冷模式，2:通风模式，3:自动模式',
	`air_vel` tinyint(1) DEFAULT NULL COMMENT '设定风速，1：1，2:2，3:3',
	`temp` int(11) DEFAULT NULL COMMENT '设定温度',
	`location` varchar(255) DEFAULT NULL COMMENT '空调位置',
  `time` datetime DEFAULT NULL COMMENT '设定时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for fault
-- ----------------------------
DROP TABLE IF EXISTS `fault`;
CREATE TABLE `fault` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `device_id` bigint unsigned NOT NULL COMMENT '设备编号',
  `fault_type` tinyint(1) DEFAULT NULL COMMENT '故障类型',
	`fault_desc` varchar(255) DEFAULT NULL COMMENT '故障描述',
	`fault_status` tinyint(1) DEFAULT NULL COMMENT '故障状态，0：未修复，1：已修复',
  `gmt_create` datetime DEFAULT NULL COMMENT '故障时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修复时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- ----------------------------
-- Table structure for warning
-- ----------------------------
DROP TABLE IF EXISTS `warning`;
CREATE TABLE `warning` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `device_id` bigint unsigned NOT NULL COMMENT '设备编号',
  `warning_type` tinyint(1) DEFAULT NULL COMMENT '预警类型',
	`warning_desc` varchar(255) DEFAULT NULL COMMENT '预警描述',
	`warning_status` tinyint(1) DEFAULT NULL COMMENT '预警状态，0：未处理，1：已处理',
  `gmt_create` datetime DEFAULT NULL COMMENT '故障时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修复时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `work_order`;
CREATE TABLE `work_order` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `device_id` bigint unsigned NOT NULL COMMENT '设备编号',
	`content` varchar(255) DEFAULT NULL COMMENT '工单内容',		
	`order_status` tinyint(1) DEFAULT NULL COMMENT '工单状态，0：未处理，1：已处理',
  `gmt_create` datetime DEFAULT NULL COMMENT '生成时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '处理时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
