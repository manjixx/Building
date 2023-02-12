
-- ----------------------------
-- Records of `user`
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '7406a2df6f173301fbac82fe6c401a01', 'SnGvSjPVSSAg8gZjgHhBPA==','管理员',1,'2023-01-14 16:19:28', '2023-01-14 16:19:28');
INSERT INTO `user` VALUES (2, 'user', '16bec4d5163d170d2692daf7ee8ee79c', 'ID+CmW9lai04skjI80V0Pg==','实验人员',1,'2023-01-14 16:19:28', '2023-01-14 16:19:28');

-- ----------------------------
-- Records of role
-- id
-- role_name
-- role_menu
-- role_state
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin', '系统管理员', '1', '2023-01-14 16:19:28', '2023-01-14 16:19:28');
INSERT INTO `role` VALUES ('2', 'user', '实验人员', '1', '2023-01-14 16:19:28', '2023-01-14 16:19:28');

-- ----------------------------
-- Records of `right`
-- id
-- right_name 即权限的名称，推荐使用英文
-- right_desc 即对权限功能的具体描述
-- right_state 权限状态
-- right_url 即权限对应的接口，是实现功能控制的关键
-- ----------------------------
INSERT INTO `permission` VALUES ('1', 'user_management', '用户管理', '/api/admin/user','1','2023-01-14 16:19:28', '2023-01-14 16:19:28');
INSERT INTO `permission` VALUES ('2', 'role_management', '角色管理', '/api/admin/role','1','2023-01-14 16:19:28', '2023-01-14 16:19:28');
INSERT INTO `permission` VALUES ('3', 'sensor_management', '设备管理','/api/admin/device','1','2023-01-14 16:19:28', '2023-01-14 16:19:28');
-- INSERT INTO `permission` VALUES ('1', 'data_collection', '数据采集','1', '/api/admin/questionnaire','2023-01-14 16:19:28', '2023-01-14 16:19:28');
-- INSERT INTO `permission` VALUES ('4', 'data_analysis', '数据分析', '1','/api/admin/data','2023-01-14 16:19:28', '2023-01-14 16:19:28');

-- ----------------------------
-- Records of role_right
-- id
-- role_id 
-- right_id 即对权限功能的具体描述
-- ----------------------------
INSERT INTO `role_permission` VALUES ('1', '1', '1', '2023-01-14 16:19:28', '2023-01-14 16:19:28');
INSERT INTO `role_permission` VALUES ('2', '1', '2', '2023-01-14 16:19:28', '2023-01-14 16:19:28');
INSERT INTO `role_permission` VALUES ('3', '1', '3', '2023-01-14 16:19:28', '2023-01-14 16:19:28');
-- INSERT INTO `role_right` VALUES ('4', '2', '1', '2023-01-14 16:19:28', '2023-01-14 16:19:28');
-- INSERT INTO `role_right` VALUES ('5', '2', '2', '2023-01-14 16:19:28', '2023-01-14 16:19:28');
-- INSERT INTO `role_right` VALUES ('6', '2', '3', '2023-01-14 16:19:28', '2023-01-14 16:19:28');
-- INSERT INTO `role_right` VALUES ('4', '1', '4', '2023-01-14 16:19:28', '2023-01-14 16:19:28');
-- INSERT INTO `role_right` VALUES ('5', '2', '1', '2023-01-14 16:19:28', '2023-01-14 16:19:28');

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1', '2023-01-14 16:19:28', '2023-01-14 16:19:28');
INSERT INTO `user_role` VALUES ('2', '2', '2', '2023-01-14 16:19:28', '2023-01-14 16:19:28');

-- ----------------------------
-- Records of menu
-- id
-- path:	与 Vue 路由中的 path 对应，即地址路径
-- name:	与 Vue 路由中的 name 属性对应
-- name_zh:	中文名称，用于渲染导航栏（菜单）界面
-- icon_cls:	element 图标类名，用于渲染菜单名称前的小图标
-- component:	组件名，用于解析路由对应的组件
-- parent_id:	父节点 id，用于存储导航栏层级关系
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '/admin', 'Data', '数据采集', 'el-icon-s-claim', 'AdminIndex', '0', '2023-01-15 16:19:28', '2023-01-15 16:19:28');
INSERT INTO `menu` VALUES ('2', '/admin', 'User', '用户管理', 'el-icon-user', 'AdminIndex', '0', '2023-01-15 16:19:28', '2023-01-15 16:19:28');
INSERT INTO `menu` VALUES ('3', '/admin', 'Device', '设备管理', 'el-icon-s-platform', 'AdminIndex', '0', '2023-01-15 16:19:28', '2023-01-15 16:19:28');
INSERT INTO `menu` VALUES ('4', '/admin', 'Statistic', '数据统计', 'el-icon-data-analysis', 'AdminIndex', '0','2023-01-15 16:19:28', '2023-01-15 16:19:28');
INSERT INTO `menu` VALUES ('5', '/admin/questionnaire/information', 'Information', '基本信息', null, 'questionnaire/Information', '1', '2023-01-15 16:19:28', '2023-01-15 16:19:28');
INSERT INTO `menu` VALUES ('6', '/admin/questionnaire/feedback', 'Feedback', '反馈信息', null, 'questionnaire/Feedback', '1', '2023-01-15 16:19:28', '2023-01-15 16:19:28');
INSERT INTO `menu` VALUES ('7', '/admin/user/profile', 'UserProfile', '用户信息', null, 'user/UserProfile', '2', '2023-01-15 16:19:28', '2023-01-15 16:19:28');
INSERT INTO `menu` VALUES ('8', '/admin/user/role', 'Role', '角色配置', null, 'user/Role', '2', '2023-01-15 16:19:28', '2023-01-15 16:19:28');
INSERT INTO `menu` VALUES ('9', '/admin/device/management', 'DeviceManagement', '设备信息', null, 'device/DeviceManagement', '3', '2023-01-15 16:19:28', '2023-01-15 16:19:28');
INSERT INTO `menu` VALUES ('10', '/admin/device/profile', 'DeviceProfile', '设备监控', null, 'device/DeviceProfile', '3', '2023-01-15 16:19:28', '2023-01-15 16:19:28');
INSERT INTO `menu` VALUES ('11', '/admin/data/environment', 'EnvironmentData', '环境数据', null, 'data/EnvironmentData', '4', '2023-01-15 16:19:28', '2023-01-15 16:19:28');
INSERT INTO `menu` VALUES ('12', '/admin/data/feedback', 'FeedbackData', '环境数据', null, 'data/FeedbackData', '4', '2023-01-15 16:19:28', '2023-01-15 16:19:28');


-- ----------------------------
-- Records of role_menu
-- id
-- role_id
-- menu_id
-- gmt_create
-- gmt_modified
-- ----------------------------
INSERT INTO `role_menu` VALUES ('1', '1', '1', '2023-01-15 16:19:28', '2023-01-15 16:19:28');
INSERT INTO `role_menu` VALUES ('2', '1', '2', '2023-01-15 16:19:28', '2023-01-15 16:19:28');
INSERT INTO `role_menu` VALUES ('3', '1', '3', '2023-01-15 16:19:28', '2023-01-15 16:19:28');
INSERT INTO `role_menu` VALUES ('4', '1', '4', '2023-01-15 16:19:28', '2023-01-15 16:19:28');
INSERT INTO `role_menu` VALUES ('5', '1', '5', '2023-01-15 16:19:28', '2023-01-15 16:19:28');
INSERT INTO `role_menu` VALUES ('6', '1', '6', '2023-01-15 16:19:28', '2023-01-15 16:19:28');
INSERT INTO `role_menu` VALUES ('7', '1', '7', '2023-01-15 16:19:28', '2023-01-15 16:19:28');
INSERT INTO `role_menu` VALUES ('8', '1', '8', '2023-01-15 16:19:28', '2023-01-15 16:19:28');
INSERT INTO `role_menu` VALUES ('9', '1', '9', '2023-01-15 16:19:28', '2023-01-15 16:19:28');
INSERT INTO `role_menu` VALUES ('10', '1', '10', '2023-01-15 16:19:28', '2023-01-15 16:19:28');
INSERT INTO `role_menu` VALUES ('11', '1', '11', '2023-01-15 16:19:28', '2023-01-15 16:19:28');
INSERT INTO `role_menu` VALUES ('12', '1', '12', '2023-01-15 16:19:28', '2023-01-15 16:19:28');
INSERT INTO `role_menu` VALUES ('13', '2', '1', '2023-01-15 16:19:28', '2023-01-15 16:19:28');
INSERT INTO `role_menu` VALUES ('14', '2', '4', '2023-01-15 16:19:28', '2023-01-15 16:19:28');
INSERT INTO `role_menu` VALUES ('15', '2', '5', '2023-01-15 16:19:28', '2023-01-15 16:19:28');
INSERT INTO `role_menu` VALUES ('16', '2', '6', '2023-01-15 16:19:28', '2023-01-15 16:19:28');
INSERT INTO `role_menu` VALUES ('17', '2', '11', '2023-01-15 16:19:28', '2023-01-15 16:19:28');
INSERT INTO `role_menu` VALUES ('18', '2', '12', '2023-01-15 16:19:28', '2023-01-15 16:19:28');