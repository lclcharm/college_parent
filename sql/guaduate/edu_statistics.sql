/*
MySQL Data Transfer
Source Host: localhost
Source Database: edu_statistics
Target Host: localhost
Target Database: edu_statistics
Date: 2021/5/14 23:03:27
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for statistics_daily
-- ----------------------------
CREATE TABLE `statistics_daily` (
  `id` char(19) NOT NULL COMMENT '主键',
  `date_calculated` varchar(20) NOT NULL COMMENT '统计日期',
  `register_num` int(11) NOT NULL DEFAULT '0' COMMENT '注册人数',
  `login_num` int(11) NOT NULL DEFAULT '0' COMMENT '登录人数',
  `video_view_num` int(11) NOT NULL DEFAULT '0' COMMENT '每日播放视频数',
  `course_num` int(11) NOT NULL DEFAULT '0' COMMENT '每日新增课程数',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `statistics_day` (`date_calculated`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='网站统计日数据';

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `statistics_daily` VALUES ('1390337440610275329', '2021-01-01', '39', '147', '177', '132', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337440799019009', '2021-01-02', '55', '152', '142', '149', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337440891293698', '2021-01-03', '70', '195', '108', '121', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337440970985474', '2021-01-04', '88', '191', '152', '189', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337441029705729', '2021-01-05', '25', '142', '192', '196', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337441109397506', '2021-01-06', '81', '189', '171', '182', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337441168117762', '2021-01-07', '99', '181', '153', '122', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337441239420929', '2021-01-08', '63', '150', '157', '148', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337441348472833', '2021-01-09', '55', '108', '116', '134', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337441415581697', '2021-01-10', '47', '156', '159', '159', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337441478496258', '2021-01-11', '94', '149', '120', '195', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337441562382337', '2021-01-12', '97', '180', '166', '199', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337441646268417', '2021-01-13', '30', '189', '117', '176', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337441721765890', '2021-01-14', '21', '199', '126', '124', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337441814040577', '2021-01-15', '67', '160', '101', '123', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337441906315265', '2021-01-16', '48', '175', '122', '116', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337441998589954', '2021-01-17', '41', '167', '186', '193', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337442095058946', '2021-01-18', '24', '169', '177', '129', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337442174750722', '2021-01-19', '63', '185', '176', '145', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337442246053889', '2021-01-20', '35', '102', '141', '118', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337442350911489', '2021-01-21', '21', '120', '133', '118', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337442447380482', '2021-01-22', '60', '128', '171', '118', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337442556432386', '2021-01-23', '46', '179', '167', '143', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337442669678594', '2021-01-24', '62', '183', '165', '102', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337442766147586', '2021-01-25', '31', '111', '115', '199', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337442845839361', '2021-01-26', '54', '178', '151', '165', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337442929725442', '2021-01-27', '93', '172', '138', '117', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337442996834306', '2021-01-28', '36', '161', '182', '104', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337443089108993', '2021-01-29', '34', '187', '106', '114', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337443189772290', '2021-01-30', '58', '179', '127', '149', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337443290435586', '2021-01-31', '57', '191', '152', '183', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337443462402049', '2021-02-01', '59', '139', '124', '141', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337443542093826', '2021-02-02', '93', '102', '153', '114', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337443621785601', '2021-02-03', '93', '120', '101', '140', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337443693088769', '2021-02-04', '45', '122', '128', '121', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337443756003330', '2021-02-05', '23', '150', '136', '153', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337443814723586', '2021-02-06', '58', '126', '185', '184', '2021-05-07 00:07:32', '2021-05-07 00:07:32');
INSERT INTO `statistics_daily` VALUES ('1390337443890221057', '2021-02-07', '67', '140', '129', '100', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337443961524226', '2021-02-08', '71', '156', '124', '138', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337444024438785', '2021-02-09', '92', '122', '142', '130', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337444083159041', '2021-02-10', '56', '114', '146', '168', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337444133490690', '2021-02-11', '49', '158', '119', '185', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337444196405250', '2021-02-12', '83', '136', '179', '107', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337444259319809', '2021-02-13', '48', '137', '111', '134', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337444322234369', '2021-02-14', '33', '137', '153', '129', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337444385148930', '2021-02-15', '27', '100', '197', '193', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337444448063490', '2021-02-16', '77', '198', '159', '140', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337444502589442', '2021-02-17', '86', '157', '103', '197', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337444573892609', '2021-02-18', '99', '157', '167', '151', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337444636807170', '2021-02-19', '86', '137', '126', '177', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337444699721730', '2021-02-20', '98', '175', '166', '119', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337444758441985', '2021-02-21', '83', '180', '104', '199', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337444817162242', '2021-02-22', '26', '172', '148', '155', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337444875882497', '2021-02-23', '38', '138', '108', '170', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337444934602754', '2021-02-24', '46', '149', '137', '125', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337445005905921', '2021-02-25', '91', '186', '100', '193', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337445098180610', '2021-02-26', '83', '129', '176', '130', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337445182066690', '2021-02-27', '32', '115', '165', '176', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337445270147073', '2021-02-28', '63', '174', '187', '195', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337445345644545', '2021-03-01', '99', '177', '197', '168', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337445442113537', '2021-03-02', '26', '199', '164', '140', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337445546971138', '2021-03-03', '40', '151', '152', '155', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337445622468610', '2021-03-04', '95', '161', '145', '182', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337445710548994', '2021-03-05', '96', '128', '144', '119', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337445790240770', '2021-03-06', '59', '155', '125', '190', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337445857349633', '2021-03-07', '35', '115', '153', '170', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337445932847106', '2021-03-08', '61', '156', '132', '104', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337446008344577', '2021-03-09', '22', '128', '188', '164', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337446092230658', '2021-03-10', '20', '117', '162', '187', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337446163533825', '2021-03-11', '85', '182', '123', '166', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337446247419906', '2021-03-12', '36', '142', '196', '129', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337446314528770', '2021-03-13', '90', '137', '199', '182', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337446394220545', '2021-03-14', '64', '103', '189', '133', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337446461329410', '2021-03-15', '83', '198', '188', '150', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337446528438273', '2021-03-16', '65', '161', '158', '183', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337446633295874', '2021-03-17', '47', '156', '141', '108', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337446704599041', '2021-03-18', '93', '184', '138', '176', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337446796873730', '2021-03-19', '97', '159', '185', '188', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337446884954113', '2021-03-20', '48', '171', '186', '160', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337446960451585', '2021-03-21', '91', '141', '106', '108', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337447031754754', '2021-03-22', '38', '185', '101', '141', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337447103057921', '2021-03-23', '84', '163', '148', '108', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337447182749698', '2021-03-24', '94', '188', '141', '194', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337447262441473', '2021-03-25', '82', '169', '190', '108', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337447325356033', '2021-03-26', '40', '158', '123', '131', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337447396659202', '2021-03-27', '48', '199', '156', '128', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337447463768066', '2021-03-28', '87', '194', '174', '134', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337447539265537', '2021-03-29', '73', '192', '183', '150', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337447618957313', '2021-03-30', '32', '141', '157', '128', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337447690260482', '2021-03-31', '90', '115', '176', '118', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337447778340865', '2021-04-01', '89', '115', '140', '147', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337447849644033', '2021-04-02', '67', '192', '145', '171', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337447920947201', '2021-04-03', '90', '156', '151', '196', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337447996444674', '2021-04-04', '22', '109', '150', '148', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337448067747841', '2021-04-05', '94', '185', '110', '114', '2021-05-07 00:07:33', '2021-05-07 00:07:33');
INSERT INTO `statistics_daily` VALUES ('1390337448134856706', '2021-04-06', '86', '179', '136', '131', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337448197771265', '2021-04-07', '99', '147', '177', '128', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337448264880130', '2021-04-08', '41', '112', '145', '126', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337448323600386', '2021-04-09', '53', '100', '192', '133', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337448373932034', '2021-04-10', '38', '140', '197', '102', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337448449429505', '2021-04-11', '86', '103', '168', '108', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337448533315585', '2021-04-12', '71', '162', '102', '145', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337448617201666', '2021-04-13', '25', '194', '128', '183', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337448684310529', '2021-04-14', '40', '169', '165', '181', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337448747225090', '2021-04-15', '98', '126', '132', '116', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337448814333953', '2021-04-16', '53', '177', '146', '129', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337448864665601', '2021-04-17', '71', '176', '153', '177', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337448935968769', '2021-04-18', '96', '154', '151', '176', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337448986300417', '2021-04-19', '49', '117', '191', '149', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337449045020673', '2021-04-20', '40', '181', '117', '163', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337449099546625', '2021-04-21', '75', '163', '173', '118', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337449149878274', '2021-04-22', '82', '195', '136', '169', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337449204404225', '2021-04-23', '33', '116', '136', '117', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337449267318785', '2021-04-24', '51', '195', '171', '114', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337449334427650', '2021-04-25', '66', '156', '139', '161', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337449409925122', '2021-04-26', '99', '129', '190', '150', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337449464451074', '2021-04-27', '31', '199', '123', '177', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337449523171330', '2021-04-28', '75', '174', '113', '137', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337449581891586', '2021-04-29', '41', '121', '173', '112', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337449657389058', '2021-04-30', '55', '180', '131', '182', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337449711915009', '2021-05-01', '21', '132', '191', '129', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337449766440961', '2021-05-02', '69', '109', '144', '142', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337449816772609', '2021-05-03', '27', '174', '154', '194', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337449867104258', '2021-05-04', '84', '113', '194', '196', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337449909047297', '2021-05-05', '35', '117', '110', '167', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337450013904898', '2021-05-07', '54', '160', '175', '122', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337450068430849', '2021-05-08', '45', '173', '113', '146', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390337450131345409', '2021-05-09', '66', '117', '110', '165', '2021-05-07 00:07:34', '2021-05-07 00:07:34');
INSERT INTO `statistics_daily` VALUES ('1390350649643794434', '2021-05-06', '40', '114', '145', '138', '2021-05-07 01:00:01', '2021-05-07 01:00:01');
