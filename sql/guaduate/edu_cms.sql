/*
MySQL Data Transfer
Source Host: localhost
Source Database: edu_cms
Target Host: localhost
Target Database: edu_cms
Date: 2021/5/14 23:03:12
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for cms_ad
-- ----------------------------
CREATE TABLE `cms_ad` (
  `id` char(19) NOT NULL DEFAULT '' COMMENT 'ID',
  `title` varchar(20) DEFAULT '' COMMENT '标题',
  `type_id` char(19) NOT NULL COMMENT '类型ID',
  `image_url` varchar(500) NOT NULL DEFAULT '' COMMENT '图片地址',
  `color` varchar(10) DEFAULT NULL COMMENT '背景颜色',
  `link_url` varchar(500) DEFAULT '' COMMENT '链接地址',
  `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_name` (`title`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='广告推荐';

-- ----------------------------
-- Table structure for cms_ad_type
-- ----------------------------
CREATE TABLE `cms_ad_type` (
  `id` char(19) NOT NULL COMMENT 'ID',
  `title` varchar(20) NOT NULL COMMENT '标题',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='推荐位';

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `cms_ad` VALUES ('1378989021354549250', '考研助力成功4', '1', 'https://grad-file.oss-cn-chengdu.aliyuncs.com/ad/2021/04/05/20210405163256.jpg', null, '', '4', '2021-04-05 16:32:58', '2021-04-05 16:32:58');
INSERT INTO `cms_ad` VALUES ('1378989097128845314', '考研助力成功3', '1', 'https://grad-file.oss-cn-chengdu.aliyuncs.com/ad/2021/04/05/20210405163314.jpg', null, '', '3', '2021-04-05 16:33:16', '2021-04-05 16:33:16');
INSERT INTO `cms_ad` VALUES ('1378989257997180929', '考研助力成功5', '1', 'https://grad-file.oss-cn-chengdu.aliyuncs.com/ad/2021/04/05/20210405163353.jpg', null, '', '5', '2021-04-05 16:33:54', '2021-04-05 16:33:54');
INSERT INTO `cms_ad` VALUES ('1390858502712455169', '考研助力成功1', '1', 'https://grad-file.oss-cn-chengdu.aliyuncs.com/ad/2021/05/08/20210508103918.jpg', null, '', '1', '2021-05-08 10:38:03', '2021-05-08 10:38:03');
INSERT INTO `cms_ad` VALUES ('1390861279404617729', '考研助力成功2', '1', 'https://grad-file.oss-cn-chengdu.aliyuncs.com/ad/2021/05/08/20210508104853.jpg', null, '', '2', '2021-05-08 10:49:05', '2021-05-08 10:49:05');
INSERT INTO `cms_ad_type` VALUES ('1', '广告1', '2021-03-02 12:46:52', '2021-03-02 12:46:52');
