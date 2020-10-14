

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for carriers
-- ----------------------------
DROP TABLE IF EXISTS `carriers`;
CREATE TABLE `carriers` (
  `carriersID` int(11) NOT NULL AUTO_INCREMENT,
  `sendCompany` varchar(63) DEFAULT NULL,
  `sendAddress` varchar(127) DEFAULT NULL,
  `sendLinkMan` varchar(31) DEFAULT NULL,
  `sendPhone` varchar(15) DEFAULT NULL,
  `receiveCompany` varchar(127) DEFAULT NULL,
  `fk_receiveAddress` varchar(127) DEFAULT NULL,
  `receiveLinkMan` varchar(31) DEFAULT NULL,
  `receivePhone` varchar(31) DEFAULT NULL,
  `leaverDate` datetime DEFAULT NULL,
  `receiveDate` datetime DEFAULT NULL,
  `finishedState` int(11) DEFAULT NULL,
  `insuranceCost` float DEFAULT NULL,
  `transportCost` float DEFAULT NULL,
  `otherCost` float DEFAULT NULL,
  `totalCost` float DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `fk_userID` int(11) DEFAULT NULL,
  `checkInTime` datetime DEFAULT NULL,
  `isDelete` int(11) DEFAULT NULL,
  `alterTime` datetime DEFAULT NULL,
  PRIMARY KEY (`carriersID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for contact
-- ----------------------------
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `contactID` int(11) NOT NULL AUTO_INCREMENT,
  `fk_truckID` int(11) DEFAULT NULL,
  `fk_driverID` int(11) DEFAULT NULL,
  PRIMARY KEY (`contactID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for driver
-- ----------------------------
DROP TABLE IF EXISTS `driver`;
CREATE TABLE `driver` (
  `driverID` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(31) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `birth` date DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `IDCard` varchar(31) DEFAULT NULL,
  `fk_teamID` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `checkInTime` datetime DEFAULT NULL,
  `isDelete` int(11) DEFAULT NULL,
  `alterTime` datetime DEFAULT NULL,
  PRIMARY KEY (`driverID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `goodsID` int(11) NOT NULL AUTO_INCREMENT,
  `goodsName` varchar(31) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `weight` float DEFAULT NULL,
  `volume` float DEFAULT NULL,
  `fk_carriersID` int(11) DEFAULT NULL,
  `isDelete` int(11) DEFAULT NULL,
  PRIMARY KEY (`goodsID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for logdic
-- ----------------------------
DROP TABLE IF EXISTS `logdic`;
CREATE TABLE `logdic` (
  `typeID` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(31) DEFAULT NULL,
  PRIMARY KEY (`typeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for peng_loginlog
-- ----------------------------
DROP TABLE IF EXISTS `peng_loginlog`;
CREATE TABLE `peng_loginlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '登陆日志id',
  `username` varchar(50) DEFAULT NULL COMMENT '登陆人姓名',
  `account` varchar(50) DEFAULT NULL COMMENT '登陆人账号',
  `login_ip` varchar(255) DEFAULT NULL COMMENT '登陆人当前ip',
  `login_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '登陆时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of peng_loginlog
-- ----------------------------

-- ----------------------------
-- Table structure for peng_menu
-- ----------------------------
DROP TABLE IF EXISTS `peng_menu`;
CREATE TABLE `peng_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `path` varchar(50) DEFAULT NULL COMMENT '菜单路径',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `keep_alive` tinyint(4) DEFAULT '1' COMMENT '菜单是否显示:0-不现实,1-显示',
  `parent_id` int(11) DEFAULT NULL COMMENT '父id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of peng_menu
-- ----------------------------
BEGIN;
INSERT INTO `peng_menu` VALUES (2, '/home', '首页', 'el-icon-s-home', 1, 1);
INSERT INTO `peng_menu` VALUES (3, '/home', '车辆管理', 'el-icon-truck', 1, 1);
INSERT INTO `peng_menu` VALUES (4, '/truck', '车辆信息维护', NULL, 1, 3);
INSERT INTO `peng_menu` VALUES (5, '/bindTruck', '绑定车辆信息', NULL, 1, 3);
INSERT INTO `peng_menu` VALUES (6, '/truckTeam', '车队信息维护', NULL, 1, 3);
INSERT INTO `peng_menu` VALUES (7, '/home', '驾驶员管理', 'el-icon-user', 1, 1);
INSERT INTO `peng_menu` VALUES (8, '/driver', '驾驶员信息维护', NULL, 1, 7);
INSERT INTO `peng_menu` VALUES (9, '/home', '运力查询', 'el-icon-wind-power', 1, 1);
INSERT INTO `peng_menu` VALUES (10, '/capacity', '运力综合查询', NULL, 1, 9);
INSERT INTO `peng_menu` VALUES (11, '/capacityHistory', '历史承运任务查询', NULL, 1, 9);
INSERT INTO `peng_menu` VALUES (12, '/home', '运输成本核算', 'el-icon-circle-check', 1, 1);
INSERT INTO `peng_menu` VALUES (13, '/costMaintenance', '车队运输成本维护', NULL, 1, 12);
INSERT INTO `peng_menu` VALUES (14, '/costAccounting', '车队运输成本核算', NULL, 1, 12);
INSERT INTO `peng_menu` VALUES (15, '/home', '调度任务', 'el-icon-office-building', 1, 1);
INSERT INTO `peng_menu` VALUES (16, '/schedule', '调度承运任务', NULL, 1, 15);
INSERT INTO `peng_menu` VALUES (17, '/home', '承运任务管理', 'el-icon-document', 1, 1);
INSERT INTO `peng_menu` VALUES (18, '/billCreate', '承运单开出', NULL, 1, 17);
INSERT INTO `peng_menu` VALUES (19, '/billReceive', '承运单接收', NULL, 1, 17);
INSERT INTO `peng_menu` VALUES (20, '/home', '系统维护', 'el-icon-s-custom', 1, 1);
INSERT INTO `peng_menu` VALUES (21, '/user', '用户维护', NULL, 1, 20);
INSERT INTO `peng_menu` VALUES (22, '/logDic', '日志字典', NULL, 1, 20);
INSERT INTO `peng_menu` VALUES (23, '/sysLog', '系统日志', NULL, 1, 20);
INSERT INTO `peng_menu` VALUES (24, '/loginLog', '登陆日志', NULL, 1, 20);
COMMIT;

-- ----------------------------
-- Table structure for peng_operatelog
-- ----------------------------
DROP TABLE IF EXISTS `peng_operatelog`;
CREATE TABLE `peng_operatelog` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `operation_type` varchar(50) DEFAULT NULL COMMENT '操作类型',
  `description` varchar(50) DEFAULT NULL COMMENT '操作具体描述',
  `user_account` varchar(50) DEFAULT NULL COMMENT '登陆账户',
  `ip` varchar(50) DEFAULT NULL COMMENT '登陆IP',
  `username` varchar(50) DEFAULT NULL COMMENT '登陆姓名',
  `params` varchar(2000) DEFAULT NULL COMMENT '参数信息',
  `creat_time` datetime DEFAULT NULL COMMENT '操作时间',
  `exception_flag` tinyint(50) DEFAULT '0' COMMENT '是否异常',
  `exception_error` varchar(2000) DEFAULT NULL COMMENT '异常信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of peng_operatelog
-- ----------------------------

-- ----------------------------
-- Table structure for peng_role
-- ----------------------------
DROP TABLE IF EXISTS `peng_role`;
CREATE TABLE `peng_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(50) DEFAULT NULL COMMENT '角色描述',
  `editor_account` varchar(50) DEFAULT NULL COMMENT '编辑人账号',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of peng_role
-- ----------------------------
BEGIN;
INSERT INTO `peng_role` VALUES (9, '管理员', '管理用户系统', 'admin', '2020-09-03 10:35:17');
COMMIT;

-- ----------------------------
-- Table structure for peng_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `peng_role_menu`;
CREATE TABLE `peng_role_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `menu_id` int(11) DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for peng_user
-- ----------------------------
DROP TABLE IF EXISTS `peng_user`;
CREATE TABLE `peng_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `username` varchar(50) DEFAULT NULL COMMENT '用户姓名',
  `user_account` varchar(50) DEFAULT NULL COMMENT '用户登陆账号，唯一',
  `password` varchar(500) DEFAULT NULL COMMENT '用户登陆密码',
  `sex` tinyint(2) DEFAULT NULL COMMENT '用户性别: 0 女,1 男',
  `role_id` int(11) DEFAULT NULL COMMENT '用户角色',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `cancel_flag` tinyint(2) unsigned zerofill NOT NULL DEFAULT '00' COMMENT '是否已注销: 0 未注销,1 注销',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of peng_user
-- ----------------------------
BEGIN;
INSERT INTO `peng_user` VALUES (1, 'admin', 'admin', '123456', 1, 9, '2020-09-02 11:50:48', '2020-09-02 11:50:48', 00);
COMMIT;

-- ----------------------------
-- Table structure for scheduling
-- ----------------------------
DROP TABLE IF EXISTS `scheduling`;
CREATE TABLE `scheduling` (
  `schedulingID` int(11) NOT NULL AUTO_INCREMENT,
  `startTime` datetime DEFAULT NULL,
  `fk_carriersID` int(11) DEFAULT NULL,
  `fk_truckID` int(11) DEFAULT NULL,
  `oilCost` float DEFAULT NULL,
  `toll` float DEFAULT NULL,
  `fine` float DEFAULT NULL,
  `otherCost` float DEFAULT NULL,
  `totalCost` float DEFAULT NULL,
  `fk_userID` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `checkInTime` datetime DEFAULT NULL,
  `isDelete` int(11) DEFAULT NULL,
  `alterTime` datetime DEFAULT NULL,
  PRIMARY KEY (`schedulingID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for truck
-- ----------------------------
DROP TABLE IF EXISTS `truck`;
CREATE TABLE `truck` (
  `truckID` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(63) DEFAULT NULL,
  `buyDate` datetime DEFAULT NULL,
  `type` varchar(31) DEFAULT NULL,
  `length` varchar(31) DEFAULT NULL,
  `tonnage` int(11) DEFAULT NULL,
  `fk_teamID` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `checkInTime` datetime DEFAULT NULL,
  `isDelete` int(11) DEFAULT NULL,
  `alterTime` datetime DEFAULT NULL,
  PRIMARY KEY (`truckID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for truckteam
-- ----------------------------
DROP TABLE IF EXISTS `truckteam`;
CREATE TABLE `truckteam` (
  `teamID` int(11) NOT NULL AUTO_INCREMENT,
  `teamName` varchar(63) DEFAULT NULL,
  `leader` varchar(63) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `checkInTime` datetime DEFAULT NULL,
  `isDelete` int(11) DEFAULT NULL,
  `alterTime` datetime DEFAULT NULL,
  PRIMARY KEY (`teamID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
