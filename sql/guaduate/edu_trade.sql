/*
MySQL Data Transfer
Source Host: localhost
Source Database: edu_trade
Target Host: localhost
Target Database: edu_trade
Date: 2021/5/14 23:03:36
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for trade_order
-- ----------------------------
CREATE TABLE `trade_order` (
  `id` char(19) NOT NULL DEFAULT '',
  `order_no` varchar(20) NOT NULL DEFAULT '' COMMENT '订单号',
  `course_id` varchar(19) NOT NULL DEFAULT '' COMMENT '课程id',
  `course_title` varchar(100) DEFAULT NULL COMMENT '课程名称',
  `course_cover` varchar(255) DEFAULT NULL COMMENT '课程封面',
  `teacher_name` varchar(20) DEFAULT NULL COMMENT '讲师名称',
  `member_id` varchar(19) NOT NULL DEFAULT '' COMMENT '会员id',
  `nickname` varchar(50) DEFAULT NULL COMMENT '会员昵称',
  `mobile` varchar(11) DEFAULT NULL COMMENT '会员手机',
  `total_fee` decimal(20,2) DEFAULT NULL COMMENT '订单金额（分）',
  `pay_type` tinyint(4) DEFAULT NULL COMMENT '支付类型（1：微信 2：支付宝）',
  `status` tinyint(4) DEFAULT NULL COMMENT '订单状态（0：未支付 1：已支付）',
  `is_deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `ux_order_no` (`order_no`) USING BTREE,
  KEY `idx_course_id` (`course_id`) USING BTREE,
  KEY `idx_member_id` (`member_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='订单';

-- ----------------------------
-- Table structure for trade_pay_log
-- ----------------------------
CREATE TABLE `trade_pay_log` (
  `id` char(19) NOT NULL DEFAULT '',
  `order_no` varchar(20) NOT NULL DEFAULT '' COMMENT '订单号',
  `pay_time` datetime DEFAULT NULL COMMENT '支付完成时间',
  `total_fee` bigint(20) DEFAULT NULL COMMENT '支付金额（分）',
  `transaction_id` varchar(30) DEFAULT NULL COMMENT '交易流水号',
  `trade_state` char(20) DEFAULT NULL COMMENT '交易状态',
  `pay_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '支付类型（1：微信 2：支付宝）',
  `attr` text COMMENT '其他属性',
  `is_deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_order_no` (`order_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='支付日志表';

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `trade_order` VALUES ('1380542967764312066', '20210409232747377', '1378993944507043841', '考研政治', 'https://grad-file.oss-cn-chengdu.aliyuncs.com/cover/2021/04/05/20210405165227.jpg', '肖秀荣', '1366743188068417538', '忘忧', '17313682149', '100.00', '1', '1', '1', '2021-04-09 23:27:47', '2021-04-09 23:27:47');
INSERT INTO `trade_order` VALUES ('1390366003480797186', '20210507020101473', '1378993944507043841', '考研政治', 'https://grad-file.oss-cn-chengdu.aliyuncs.com/cover/2021/04/05/20210405165227.jpg', '肖秀荣', '1366743188068417538', '忘忧', '17313682149', '100.00', '1', '1', '0', '2021-05-07 02:01:02', '2021-05-07 02:01:02');
INSERT INTO `trade_order` VALUES ('1390366003480797187', '20210507020101987', '1378993944507043841', '考研政治', 'https://grad-file.oss-cn-chengdu.aliyuncs.com/cover/2021/04/05/20210405165227.jpg', '肖秀荣', '1366743188068417538', '忘忧', '17313682149', '100.00', '1', '0', '1', '2021-05-07 02:01:02', '2021-05-07 02:01:02');
INSERT INTO `trade_order` VALUES ('1390537544344576001', '20210507132240331', '1378993944507043841', '考研政治', 'https://grad-file.oss-cn-chengdu.aliyuncs.com/cover/2021/04/05/20210405165227.jpg', '肖秀荣', '1366751784898469890', '蛾眉', '', '100.00', '1', '1', '1', '2021-05-07 13:22:40', '2021-05-07 13:22:40');
INSERT INTO `trade_order` VALUES ('1390668113795653634', '20210507220130591', '1378993944507043841', '考研政治', 'https://grad-file.oss-cn-chengdu.aliyuncs.com/cover/2021/04/05/20210405165227.jpg', '肖秀荣', '1366751784898469890', '蛾眉', '17313682149', '100.00', '1', '1', '1', '2021-05-07 22:01:31', '2021-05-07 22:01:31');
INSERT INTO `trade_order` VALUES ('1390845540761096194', '20210508094632219', '1390838448964784130', '数据结构基础学习', 'https://grad-file.oss-cn-chengdu.aliyuncs.com/cover/2021/05/08/20210508091810.png', '张宇', '1366751784898469890', '蛾眉', '17313682149', '200.00', '1', '1', '1', '2021-05-08 09:46:32', '2021-05-08 09:46:32');
INSERT INTO `trade_order` VALUES ('1390900485216555010', '20210508132451304', '1390838448964784130', '数据结构基础学习', 'https://grad-file.oss-cn-chengdu.aliyuncs.com/cover/2021/05/08/20210508091810.png', '张宇', '1366751784898469890', '蛾眉', '17313682149', '200.00', '1', '1', '1', '2021-05-08 13:24:52', '2021-05-08 13:24:52');
INSERT INTO `trade_order` VALUES ('1390904309775880194', '20210508134003133', '1390838448964784130', '数据结构基础学习', 'https://grad-file.oss-cn-chengdu.aliyuncs.com/cover/2021/05/08/20210508091810.png', '张宇', '1366751784898469890', '蛾眉', '17313682149', '200.00', '1', '1', '1', '2021-05-08 13:40:04', '2021-05-08 13:40:04');
INSERT INTO `trade_order` VALUES ('1390930395792535554', '20210508152343819', '1390838448964784130', '数据结构基础学习', 'https://grad-file.oss-cn-chengdu.aliyuncs.com/cover/2021/05/08/20210508091810.png', '张宇', '1366751784898469890', '蛾眉', '17313682149', '200.00', '1', '1', '1', '2021-05-08 15:23:43', '2021-05-08 15:23:43');
INSERT INTO `trade_order` VALUES ('1390931375154130945', '20210508152736920', '1390838448964784130', '数据结构基础学习', 'https://grad-file.oss-cn-chengdu.aliyuncs.com/cover/2021/05/08/20210508091810.png', '张宇', '1366751784898469890', '蛾眉', '17313682149', '200.00', '1', '1', '1', '2021-05-08 15:27:37', '2021-05-08 15:27:37');
INSERT INTO `trade_order` VALUES ('1390933234229014530', '20210508153459005', '1390838448964784130', '数据结构基础学习', 'https://grad-file.oss-cn-chengdu.aliyuncs.com/cover/2021/05/08/20210508091810.png', '张宇', '1366751784898469890', '蛾眉', '17313682149', '200.00', '1', '1', '1', '2021-05-08 15:35:00', '2021-05-08 15:35:00');
INSERT INTO `trade_order` VALUES ('1390935019706486786', '20210508154205976', '1378993944507043841', '考研政治', 'https://grad-file.oss-cn-chengdu.aliyuncs.com/cover/2021/04/05/20210405165227.jpg', '肖秀荣', '1366751784898469890', '蛾眉', '17313682149', '100.00', '1', '1', '0', '2021-05-08 15:42:06', '2021-05-08 15:42:06');
INSERT INTO `trade_order` VALUES ('1390938489842929665', '20210508155553335', '1379025475371913217', '高数18讲', 'https://grad-file.oss-cn-chengdu.aliyuncs.com/cover/2021/04/05/20210405185745.jpg', '张宇', '1366751784898469890', '蛾眉', '17313682149', '29900.00', '1', '1', '0', '2021-05-08 15:55:53', '2021-05-08 15:55:53');
INSERT INTO `trade_order` VALUES ('1391255073543221249', '20210509125352444', '1390838448964784130', '数据结构基础学习', 'https://grad-file.oss-cn-chengdu.aliyuncs.com/cover/2021/05/08/20210508091810.png', '张宇', '1366751784898469890', '蛾眉', '17313682149', '19900.00', '1', '1', '1', '2021-05-09 12:53:52', '2021-05-09 12:53:52');
INSERT INTO `trade_pay_log` VALUES ('1380542974210957313', '20210409232747377', '2021-04-09 23:27:49', '1', '666666666666666', '1', '1', '{\"transaction_id\":\"666666666666666\",\"out_trade_no\":\"20210409232747377\",\"total_fee\":\"1\",\"result_code\":\"1\"}', '0', '2021-04-09 23:27:49', '2021-04-09 23:27:49');
INSERT INTO `trade_pay_log` VALUES ('1390366023852531713', '20210507020101473', '2021-05-07 02:01:06', '1', '666666666666666', '1', '1', '{\"transaction_id\":\"666666666666666\",\"out_trade_no\":\"20210507020101473\",\"total_fee\":\"1\",\"result_code\":\"1\"}', '0', '2021-05-07 02:01:06', '2021-05-07 02:01:06');
INSERT INTO `trade_pay_log` VALUES ('1390537552263421954', '20210507132240331', '2021-05-07 13:22:42', '1', '666666666666666', '1', '1', '{\"transaction_id\":\"666666666666666\",\"out_trade_no\":\"20210507132240331\",\"total_fee\":\"1\",\"result_code\":\"1\"}', '0', '2021-05-07 13:22:42', '2021-05-07 13:22:42');
INSERT INTO `trade_pay_log` VALUES ('1390668139070529538', '20210507220130591', '2021-05-07 22:01:36', '1', '666666666666666', '1', '1', '{\"transaction_id\":\"666666666666666\",\"out_trade_no\":\"20210507220130591\",\"total_fee\":\"1\",\"result_code\":\"1\"}', '0', '2021-05-07 22:01:36', '2021-05-07 22:01:36');
INSERT INTO `trade_pay_log` VALUES ('1390845548885463042', '20210508094632219', '2021-05-08 09:46:34', '1', '666666666666666', '1', '1', '{\"transaction_id\":\"666666666666666\",\"out_trade_no\":\"20210508094632219\",\"total_fee\":\"1\",\"result_code\":\"1\"}', '0', '2021-05-08 09:46:34', '2021-05-08 09:46:34');
INSERT INTO `trade_pay_log` VALUES ('1390900505064001538', '20210508132451304', '2021-05-08 13:24:57', '1', '666666666666666', '1', '1', '{\"transaction_id\":\"666666666666666\",\"out_trade_no\":\"20210508132451304\",\"total_fee\":\"1\",\"result_code\":\"1\"}', '0', '2021-05-08 13:24:57', '2021-05-08 13:24:57');
INSERT INTO `trade_pay_log` VALUES ('1390904316243496962', '20210508134003133', '2021-05-08 13:40:05', '200', '666666666666666', '1', '1', '{\"transaction_id\":\"666666666666666\",\"out_trade_no\":\"20210508134003133\",\"total_fee\":\"1\",\"result_code\":\"1\"}', '0', '2021-05-08 13:40:05', '2021-05-08 13:40:05');
INSERT INTO `trade_pay_log` VALUES ('1390930433344139265', '20210508152343819', '2021-05-08 15:23:52', '200', '666666666666666', '1', '1', '{\"transaction_id\":\"666666666666666\",\"out_trade_no\":\"20210508152343819\",\"total_fee\":\"200\",\"result_code\":\"1\"}', '0', '2021-05-08 15:23:52', '2021-05-08 15:23:52');
INSERT INTO `trade_pay_log` VALUES ('1390931381240066049', '20210508152736920', '2021-05-08 15:27:38', '200', '666666666666666', '1', '1', '{\"transaction_id\":\"666666666666666\",\"out_trade_no\":\"20210508152736920\",\"total_fee\":\"200\",\"result_code\":\"1\"}', '0', '2021-05-08 15:27:38', '2021-05-08 15:27:38');
INSERT INTO `trade_pay_log` VALUES ('1390934573755502594', '20210508153459005', '2021-05-08 15:40:19', '200', '666666666666666', '1', '1', '{\"transaction_id\":\"666666666666666\",\"out_trade_no\":\"20210508153459005\",\"total_fee\":\"200\",\"result_code\":\"1\"}', '0', '2021-05-08 15:40:19', '2021-05-08 15:40:19');
INSERT INTO `trade_pay_log` VALUES ('1390935024450244610', '20210508154205976', '2021-05-08 15:42:07', '100', '666666666666666', '1', '1', '{\"transaction_id\":\"666666666666666\",\"out_trade_no\":\"20210508154205976\",\"total_fee\":\"100\",\"result_code\":\"1\"}', '0', '2021-05-08 15:42:07', '2021-05-08 15:42:07');
INSERT INTO `trade_pay_log` VALUES ('1390938498797768705', '20210508155553335', '2021-05-08 15:55:55', '29900', '666666666666666', '1', '1', '{\"transaction_id\":\"666666666666666\",\"out_trade_no\":\"20210508155553335\",\"total_fee\":\"29900\",\"result_code\":\"1\"}', '0', '2021-05-08 15:55:55', '2021-05-08 15:55:55');
INSERT INTO `trade_pay_log` VALUES ('1391255079675293698', '20210509125352444', '2021-05-09 12:53:54', '19900', '666666666666666', '1', '1', '{\"transaction_id\":\"666666666666666\",\"out_trade_no\":\"20210509125352444\",\"total_fee\":\"19900\",\"result_code\":\"1\"}', '0', '2021-05-09 12:53:54', '2021-05-09 12:53:54');
