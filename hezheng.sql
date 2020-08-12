/*
Navicat MySQL Data Transfer

Source Server         : 张谦1802231021
Source Server Version : 50727
Source Host           : localhost:3306
Source Database       : hezheng

Target Server Type    : MYSQL
Target Server Version : 50727
File Encoding         : 65001

Date: 2020-08-12 15:35:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for backform
-- ----------------------------
DROP TABLE IF EXISTS `backform`;
CREATE TABLE `backform` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `backcontent` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `backtype` int(11) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of backform
-- ----------------------------

-- ----------------------------
-- Table structure for backtype
-- ----------------------------
DROP TABLE IF EXISTS `backtype`;
CREATE TABLE `backtype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `backid` int(11) DEFAULT NULL,
  `backtype` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of backtype
-- ----------------------------
INSERT INTO `backtype` VALUES ('1', '1', '意见反馈');
INSERT INTO `backtype` VALUES ('2', '2', '服务问题');
INSERT INTO `backtype` VALUES ('3', '3', '内容纠错');
INSERT INTO `backtype` VALUES ('4', '4', '其他');

-- ----------------------------
-- Table structure for basetext
-- ----------------------------
DROP TABLE IF EXISTS `basetext`;
CREATE TABLE `basetext` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` longblob,
  `title` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `textentityid` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=299 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of basetext
-- ----------------------------
INSERT INTO `basetext` VALUES ('235', 0x3C703E266E6273703B58442044204120E998BFE8BEBE4420E5958AE5A4A7534420E5958A444144266E6273703B3C2F703E, '公司简介', '1');
INSERT INTO `basetext` VALUES ('236', 0x3C703EE5958AE8BF9966647361E6B395E998BFE696AFE89282E88AAC6173E6B3956173E6B3953C2F703E, '子公司1', '5');
INSERT INTO `basetext` VALUES ('237', 0x3C703EE694BEE588B06173E6B395E6B395E998BFE58F91E6B395E5958AE5958AE5958AE6B395613C2F703E, '子公司2', '6');
INSERT INTO `basetext` VALUES ('263', 0x3C68313EE4BDA0E58FAFE4BBA5E59CA8E6ADA4E58CBAE59F9FE5A1ABE58699E58685E5AEB92CE58AA0E585A5E59BBEE78987E79BB4E68EA5E68B96E68BBDE58DB3E58FAF28E8AFB7E4B88DE8A681E4BDBFE794A8E5B7A5E585B7E69DA1E4B8ADE79A84E59BBEE78987E68C89E992AEE8BF9BE8A18CE4B88AE4BCA0293C2F68313E, '政策法规', '9');
INSERT INTO `basetext` VALUES ('252', 0x3C68313EE4BDA0E58FAFE4BBA5E59CA8E6ADA4E58CBAE59F9FE5A1ABE58699E58685E5AEB92CE58AA0E585A5E59BBEE78987E79BB4E68EA5E68B96E68BBDE58DB3E58FAF28E8AFB7E4B88DE8A681E4BDBFE794A8E5B7A5E585B7E69DA1E4B8ADE79A84E59BBEE78987E68C89E992AEE8BF9BE8A18CE4B88AE4BCA0293C2F68313E, '荣誉资质', '2');
INSERT INTO `basetext` VALUES ('253', 0x3C68313EE4BDA0E58FAFE4BBA5E59CA8E6ADA4E58CBAE59F9FE5A1ABE58699E58685E5AEB92CE58AA0E585A5E59BBEE78987E79BB4E68EA5E68B96E68BBDE58DB3E58FAF28E8AFB7E4B88DE8A681E4BDBFE794A8E5B7A5E585B7E69DA1E4B8ADE79A84E59BBEE78987E68C89E992AEE8BF9BE8A18CE4B88AE4BCA0293C2F68313E, '荣誉资质', '2');

-- ----------------------------
-- Table structure for form
-- ----------------------------
DROP TABLE IF EXISTS `form`;
CREATE TABLE `form` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  `birth` datetime DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `filepath` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `history` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `introduction` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `job` int(8) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `pay` int(8) DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `record` int(8) DEFAULT NULL,
  `sex` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of form
-- ----------------------------

-- ----------------------------
-- Table structure for html
-- ----------------------------
DROP TABLE IF EXISTS `html`;
CREATE TABLE `html` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `titlesign` int(11) DEFAULT NULL,
  `alia` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of html
-- ----------------------------
INSERT INTO `html` VALUES ('2', '关于和正', '2', 'About Us');
INSERT INTO `html` VALUES ('3', '实时资讯', '3', 'Company News');
INSERT INTO `html` VALUES ('1', '首页', '1', null);
INSERT INTO `html` VALUES ('7', '关于我们', '7', null);
INSERT INTO `html` VALUES ('4', '业务范围', '4', 'Company Business Scope');
INSERT INTO `html` VALUES ('5', '文化党建', '5', 'Company Culture');
INSERT INTO `html` VALUES ('6', '招贤纳士', '6', null);
INSERT INTO `html` VALUES ('8', '预览页面', '8', null);
INSERT INTO `html` VALUES ('9', '荣誉资质', '9', 'Company Honor');

-- ----------------------------
-- Table structure for imgtext
-- ----------------------------
DROP TABLE IF EXISTS `imgtext`;
CREATE TABLE `imgtext` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `abstracttext` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `content` varchar(1024) CHARACTER SET utf8mb4 DEFAULT NULL,
  `imgurl` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `textentityid` int(8) NOT NULL,
  `textid` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=888689 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of imgtext
-- ----------------------------
INSERT INTO `imgtext` VALUES ('888664', '', null, null, '', '/about/birefing', '1', '235');
INSERT INTO `imgtext` VALUES ('888670', '', null, null, '', '', '5', '236');
INSERT INTO `imgtext` VALUES ('888671', '', null, null, '', '', '6', '237');

-- ----------------------------
-- Table structure for job
-- ----------------------------
DROP TABLE IF EXISTS `job`;
CREATE TABLE `job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `jobid` int(8) DEFAULT NULL,
  `jobname` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of job
-- ----------------------------

-- ----------------------------
-- Table structure for otherimg
-- ----------------------------
DROP TABLE IF EXISTS `otherimg`;
CREATE TABLE `otherimg` (
  `url` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `picid` int(8) NOT NULL,
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of otherimg
-- ----------------------------
INSERT INTO `otherimg` VALUES ('D:/hezheng/otherimg/你的名字4k壁纸_彼岸图网.jpg', '1', '1');
INSERT INTO `otherimg` VALUES ('D:/hezheng/otherimg/timg (4).jpg', '2', '2');
INSERT INTO `otherimg` VALUES ('D:/hezheng/otherimg/u=2227293904,2768762169&fm=26&gp=0.jpg', '3', '3');
INSERT INTO `otherimg` VALUES ('D:/hezheng/otherimg/bajihuo.jpg', '4', '4');
INSERT INTO `otherimg` VALUES ('D:/hezheng/otherimg/u=2778388667,3852528567&fm=26&gp=0.jpg', '5', '5');
INSERT INTO `otherimg` VALUES ('D:/hezheng/otherimg/84648.jpg', '6', '6');
INSERT INTO `otherimg` VALUES ('D:/hezheng/otherimg/236.jpg', '7', '7');
INSERT INTO `otherimg` VALUES ('D:/hezheng/otherimg/鬼刀 dnf 赫尔德 鬼刀4k壁纸_彼岸图网.jpg', '8', '8');

-- ----------------------------
-- Table structure for pay
-- ----------------------------
DROP TABLE IF EXISTS `pay`;
CREATE TABLE `pay` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `payid` int(8) DEFAULT NULL,
  `payzoom` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of pay
-- ----------------------------
INSERT INTO `pay` VALUES ('3', '3', '4000-6000');
INSERT INTO `pay` VALUES ('4', '4', '6000-8000');
INSERT INTO `pay` VALUES ('5', '5', '8000-10000');
INSERT INTO `pay` VALUES ('12', '4', '2000-3000');

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `recordid` int(8) DEFAULT NULL,
  `recordname` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES ('2', '2', '初中');
INSERT INTO `record` VALUES ('3', '3', '高中');
INSERT INTO `record` VALUES ('4', '4', '专科');
INSERT INTO `record` VALUES ('5', '5', '本科');
INSERT INTO `record` VALUES ('6', '6', '研究生');
INSERT INTO `record` VALUES ('7', '1', '博士');
INSERT INTO `record` VALUES ('9', '7', '小学');

-- ----------------------------
-- Table structure for sidebar
-- ----------------------------
DROP TABLE IF EXISTS `sidebar`;
CREATE TABLE `sidebar` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `icon` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `groupid` int(8) NOT NULL,
  `parentid` int(8) DEFAULT NULL,
  `sortid` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sidebar
-- ----------------------------
INSERT INTO `sidebar` VALUES ('1', 'fa-address-card', '公司简介', '/about/birefing', '2', '0', '1');
INSERT INTO `sidebar` VALUES ('2', 'fa-folder-open', '荣誉资质', '/about/honor/1', '2', '0', '2');
INSERT INTO `sidebar` VALUES ('3', 'fa-file-photo-o', '业绩展示', '/about/achievement/1', '2', '0', '3');
INSERT INTO `sidebar` VALUES ('4', 'fa-building-o', '子公司', '', '2', '0', '4');
INSERT INTO `sidebar` VALUES ('5', '', '安徽聚诚慧信息技术有限公司', '/about/sonCompany1', '2', '4', '1');
INSERT INTO `sidebar` VALUES ('6', null, '安徽和正造价专业技术培训公司', '/about/sonCompany2', '2', '4', '2');
INSERT INTO `sidebar` VALUES ('7', 'fa-newspaper-o', '公司新闻', '/news/companyNews/1', '3', '0', '1');
INSERT INTO `sidebar` VALUES ('8', 'fa-hacker-news', '行业新闻', '/news/hangyeNews/1', '3', '0', '2');
INSERT INTO `sidebar` VALUES ('9', 'fa-ban', '政策法规', '', '3', '0', '3');
INSERT INTO `sidebar` VALUES ('10', null, '地方政策', '/news/rule/10/1', '3', '9', '1');
INSERT INTO `sidebar` VALUES ('11', null, '国家政策', '/news/rule/11/1', '3', '9', '1');
INSERT INTO `sidebar` VALUES ('12', 'fa-book', '学习园地', '/news/learning/1', '3', '0', '4');
INSERT INTO `sidebar` VALUES ('13', 'fa-bandcamp', '招标代理', '/business/proxy/1', '4', '0', '1');
INSERT INTO `sidebar` VALUES ('14', 'fa-id-badge', '工程监理', '/business/supervision/1', '4', '0', '2');
INSERT INTO `sidebar` VALUES ('15', 'fa-diamond', '造价咨询', '/business/cost/1', '4', '0', '3');
INSERT INTO `sidebar` VALUES ('16', 'fa-building', '工程设计', '/business/project/1', '4', '0', '4');
INSERT INTO `sidebar` VALUES ('17', 'fa-bank', '党的建设', '/culture/1', '5', '0', '1');
INSERT INTO `sidebar` VALUES ('18', 'fa-home', '网站首页', '/index', '7', '0', '1');
INSERT INTO `sidebar` VALUES ('19', 'fa-users', '关于和正', '/about/birefing', '7', '0', '2');
INSERT INTO `sidebar` VALUES ('20', 'fa-meetup', '实时资讯', '/news/companyNews/1', '7', '0', '3');
INSERT INTO `sidebar` VALUES ('21', 'fa-snowflake-o', '业务范围', '/business/proxy/1', '7', '0', '4');
INSERT INTO `sidebar` VALUES ('22', 'fa-cc', '文化党建', '/culture/1', '7', '0', '5');
INSERT INTO `sidebar` VALUES ('23', 'fa-edit', '招贤纳士', '/recruit/1', '7', '0', '6');
INSERT INTO `sidebar` VALUES ('24', 'fa-user', '联系我们', '/aboutme/callme', '7', '0', '7');
INSERT INTO `sidebar` VALUES ('25', 'fa-edit', '编辑页面', '/', '8', '0', '1');
INSERT INTO `sidebar` VALUES ('26', 'fa-cc', '预览页面', '/', '8', '0', '2');
INSERT INTO `sidebar` VALUES ('27', 'fa-meetup', '招贤纳士', '/recruit/1', '6', '0', '1');

-- ----------------------------
-- Table structure for text
-- ----------------------------
DROP TABLE IF EXISTS `text`;
CREATE TABLE `text` (
  `groupid` int(8) NOT NULL,
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `abstracttext` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `alia` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `sideid` int(8) DEFAULT NULL,
  PRIMARY KEY (`id`,`groupid`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of text
-- ----------------------------
INSERT INTO `text` VALUES ('1', '1', null, 'index', '首页', '0');
INSERT INTO `text` VALUES ('2', '2', null, 'company honor', '荣誉资质', '2');
INSERT INTO `text` VALUES ('2', '3', null, 'company achievement', '业绩展示', '3');
INSERT INTO `text` VALUES ('3', '4', null, 'company learning', '学习园地', '12');
INSERT INTO `text` VALUES ('3', '5', null, 'company rule', '政策法规', '9');
INSERT INTO `text` VALUES ('4', '6', null, 'company cost', '造价咨询', '15');
INSERT INTO `text` VALUES ('4', '7', null, 'company proxy', '招标代理', '13');
INSERT INTO `text` VALUES ('4', '8', null, 'company supervision', '工程监理', '14');
INSERT INTO `text` VALUES ('4', '9', null, 'company project', '工程设计', '16');
INSERT INTO `text` VALUES ('5', '10', null, 'company culture', '党的建设', '17');
INSERT INTO `text` VALUES ('6', '11', '如果您想要应聘某职位,可以在这里写下您的简历,我们会在7天之内和您联系', 'company recruit', '招贤纳士', '18');
INSERT INTO `text` VALUES ('2', '12', null, 'company birefing', '公司简介', '1');
INSERT INTO `text` VALUES ('3', '18', null, 'company news', '公司新闻', '7');
INSERT INTO `text` VALUES ('7', '13', null, 'about us', '联系我们', '24');
INSERT INTO `text` VALUES ('8', '14', null, 'bianjiyemian', '编辑页面', '-1');
INSERT INTO `text` VALUES ('8', '15', null, 'yulanyemian', '预览页面', '-1');
INSERT INTO `text` VALUES ('3', '16', null, 'local rule', '地方政策法规', '10');
INSERT INTO `text` VALUES ('3', '17', null, 'country rule', '国家政策法规', '11');
INSERT INTO `text` VALUES ('3', '19', null, 'hangye news', '行业新闻', '8');
INSERT INTO `text` VALUES ('2', '20', null, 'son company ', '子公司', '4');
INSERT INTO `text` VALUES ('2', '21', null, null, '安徽聚成慧信息技术有限公司', '5');
INSERT INTO `text` VALUES ('2', '22', null, null, '安徽和正造价专业技术培训公司', '6');
