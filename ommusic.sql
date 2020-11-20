/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : ommusic

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2019-05-27 17:10:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `comments`
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '评论编号',
  `content` varchar(256) NOT NULL DEFAULT '' COMMENT '评论信息',
  `time` varchar(64) NOT NULL DEFAULT '' COMMENT '评论时间',
  `count` int(11) NOT NULL DEFAULT '0' COMMENT '点赞次数',
  `userid` int(11) NOT NULL COMMENT '用户编号',
  `songid` int(11) NOT NULL COMMENT '歌曲编号',
  `remark` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_userId` (`userid`),
  KEY `fk_songId` (`songid`),
  CONSTRAINT `fk_song_id` FOREIGN KEY (`songid`) REFERENCES `song` (`id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`userid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES ('1', '我是评论', '2019-03-31', '0', '1', '2', '1');
INSERT INTO `comments` VALUES ('2', '我也是评论', '2019-03-31 12：00：00', '0', '1', '2', '1');
INSERT INTO `comments` VALUES ('3', '%E4%BD%A0%E5%A5%BD', '2019-04-26 15:05:41', '0', '1', '18', '1');
INSERT INTO `comments` VALUES ('4', '123456', '2019-04-27 11:05:28', '2', '1', '38', '1');
INSERT INTO `comments` VALUES ('5', '%5Bem_4%5D', '2019-04-27 11:06:44', '1', '1', '38', '1');
INSERT INTO `comments` VALUES ('7', '%E4%BD%A0%E5%A5%BD', '2019-05-10 10:20:01', '0', '1', '38', '1');
INSERT INTO `comments` VALUES ('8', '%E4%BD%A0%E5%A5%BD', '2019-05-10 10:21:05', '0', '1', '38', '1');
INSERT INTO `comments` VALUES ('9', '好', '2019-05-10 10:51:55', '0', '1', '38', '1');
INSERT INTO `comments` VALUES ('10', '你好', '2019-05-10 10:53:50', '0', '1', '38', '1');
INSERT INTO `comments` VALUES ('11', '你好', '2019-05-10 10:54:37', '0', '1', '38', '1');
INSERT INTO `comments` VALUES ('12', '你好', '2019-05-10 10:54:43', '1', '1', '38', '1');
INSERT INTO `comments` VALUES ('13', '[em_75]', '2019-05-10 10:54:57', '2', '1', '38', '1');
INSERT INTO `comments` VALUES ('14', '很喜欢这首歌', '2019-05-10 20:29:09', '3', '1', '38', '1');
INSERT INTO `comments` VALUES ('15', '很好听', '2019-05-14 09:47:42', '1', '1', '83', '1');
INSERT INTO `comments` VALUES ('16', '[em_35]', '2019-05-14 09:48:15', '0', '1', '83', '1');
INSERT INTO `comments` VALUES ('17', '林俊杰', '2019-05-18 15:07:43', '0', '1', '17', '1');
INSERT INTO `comments` VALUES ('18', '[em_41]', '2019-05-18 15:07:53', '0', '1', '17', '1');
INSERT INTO `comments` VALUES ('19', '[em_59]', '2019-05-18 15:20:44', '2', '1', '14', '1');
INSERT INTO `comments` VALUES ('20', '你好[em_13]', '2019-05-27 15:24:37', '1', '1', '81', '1');

-- ----------------------------
-- Table structure for `reply_comment`
-- ----------------------------
DROP TABLE IF EXISTS `reply_comment`;
CREATE TABLE `reply_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '回复评论编号',
  `content` varchar(256) NOT NULL DEFAULT '' COMMENT '回复内容',
  `time` varchar(64) NOT NULL DEFAULT '' COMMENT '回复评论时间',
  `commentsid` int(11) NOT NULL COMMENT '评论编号',
  `userid` int(11) NOT NULL COMMENT '用户编号',
  `remark` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_commentId` (`commentsid`),
  KEY `fk_rep_userId` (`userid`),
  CONSTRAINT `fk_comments_id` FOREIGN KEY (`commentsid`) REFERENCES `comments` (`id`),
  CONSTRAINT `fk_user_id2` FOREIGN KEY (`userid`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of reply_comment
-- ----------------------------
INSERT INTO `reply_comment` VALUES ('1', '我是回复评论', '2019-03-31 12：14：13', '4', '1', '1');
INSERT INTO `reply_comment` VALUES ('2', '呵呵', '2019-05-10 20:28:27', '4', '1', '1');
INSERT INTO `reply_comment` VALUES ('3', '牛皮', '2019-05-10 20:28:37', '13', '1', '1');
INSERT INTO `reply_comment` VALUES ('4', '我也很喜欢啊', '2019-05-10 20:29:42', '14', '1', '1');
INSERT INTO `reply_comment` VALUES ('5', '我也觉得', '2019-05-14 09:47:56', '15', '1', '1');
INSERT INTO `reply_comment` VALUES ('6', '呵呵', '2019-05-18 15:08:03', '18', '1', '1');
INSERT INTO `reply_comment` VALUES ('7', '5720152043', '2019-05-18 15:25:18', '19', '1', '1');
INSERT INTO `reply_comment` VALUES ('8', '呵呵', '2019-05-27 15:30:46', '20', '7', '1');

-- ----------------------------
-- Table structure for `search`
-- ----------------------------
DROP TABLE IF EXISTS `search`;
CREATE TABLE `search` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '搜索编号',
  `content` varchar(256) NOT NULL DEFAULT '' COMMENT '搜索内容',
  `count` int(11) NOT NULL DEFAULT '0' COMMENT '搜索次数',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of search
-- ----------------------------
INSERT INTO `search` VALUES ('1', '很好', '9', '1');
INSERT INTO `search` VALUES ('2', '周杰伦', '11', '1');
INSERT INTO `search` VALUES ('3', '菊花台', '3', '1');
INSERT INTO `search` VALUES ('4', '呵呵', '6', '1');
INSERT INTO `search` VALUES ('5', '花', '38', '1');
INSERT INTO `search` VALUES ('6', '周', '2', '1');
INSERT INTO `search` VALUES ('7', '你', '7', '1');
INSERT INTO `search` VALUES ('9', '你好', '2', '1');
INSERT INTO `search` VALUES ('10', '和', '2', '1');
INSERT INTO `search` VALUES ('11', 'ni', '4', '1');
INSERT INTO `search` VALUES ('13', '当年', '1', '1');
INSERT INTO `search` VALUES ('14', '独一', '1', '1');
INSERT INTO `search` VALUES ('15', '你是我的', '1', '1');
INSERT INTO `search` VALUES ('16', '你是', '1', '1');
INSERT INTO `search` VALUES ('17', '七年', '1', '1');
INSERT INTO `search` VALUES ('18', 'ari', '1', '1');
INSERT INTO `search` VALUES ('19', '不能说的秘密', '1', '1');
INSERT INTO `search` VALUES ('20', '小', '2', '1');
INSERT INTO `search` VALUES ('21', '夜', '1', '1');
INSERT INTO `search` VALUES ('22', '一', '1', '1');
INSERT INTO `search` VALUES ('23', '光', '2', '1');
INSERT INTO `search` VALUES ('24', '林', '1', '1');
INSERT INTO `search` VALUES ('25', '王', '1', '1');
INSERT INTO `search` VALUES ('26', '真', '1', '1');
INSERT INTO `search` VALUES ('27', '贺', '1', '1');

-- ----------------------------
-- Table structure for `singer`
-- ----------------------------
DROP TABLE IF EXISTS `singer`;
CREATE TABLE `singer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '歌手编号',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '歌手姓名',
  `alias` varchar(64) DEFAULT NULL COMMENT '歌手别名',
  `language` varchar(64) DEFAULT NULL COMMENT '语言',
  `birthday` varchar(64) DEFAULT NULL COMMENT '生日',
  `height` double(9,2) DEFAULT NULL COMMENT '身高',
  `sex` varchar(64) NOT NULL DEFAULT '' COMMENT '性别',
  `nation` varchar(64) DEFAULT NULL COMMENT '国籍',
  `birthplace` varchar(64) DEFAULT NULL COMMENT '出身地',
  `constellation` varchar(64) DEFAULT NULL COMMENT '星座',
  `weight` double(9,2) DEFAULT NULL COMMENT '体重',
  `picture` varchar(512) NOT NULL DEFAULT '' COMMENT '图片',
  `singer_typeid` int(11) NOT NULL COMMENT '歌手类别编号',
  `job` varchar(64) DEFAULT NULL COMMENT '职业',
  `company` varchar(256) DEFAULT NULL COMMENT '经济公司',
  `representative` varchar(128) NOT NULL DEFAULT '' COMMENT '代表作品',
  `school` varchar(64) DEFAULT NULL COMMENT '毕业院校',
  `firstname` varchar(64) NOT NULL DEFAULT '' COMMENT '首字母',
  `remark` varchar(64) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `fk_singType` (`singer_typeid`),
  CONSTRAINT `fk_singer_type_id` FOREIGN KEY (`singer_typeid`) REFERENCES `singer_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of singer
-- ----------------------------
INSERT INTO `singer` VALUES ('1', '滨崎步', '', '日语', '1978/10/2', '156.00', '女', '日本', '日本福冈县福冈市', '天秤座', '40.00', 'src/data/img/滨崎步.jpg', '3', '歌手、作词家', 'avexmanagement', '《A SONG FOR XX》', '日本崛越高中', 'B', '1');
INSERT INTO `singer` VALUES ('2', '蔡健雅', '', '英语;国语', '1975/1/28', '160.00', '女', '新加坡', '新加坡', '水瓶座', '45.00', 'src/data/img/蔡健雅.jpg', '1', '词曲创作者、制作人', '环球音乐', '《呼吸》', '新加坡理工学院、美国音乐学院', 'C', '1');
INSERT INTO `singer` VALUES ('3', '陈奕迅', '阿臣|医生', '国语;粤语;', '1974/7/27\r\n', '173.00', '男', '中国香港', '香港', '狮子座', '72.00', 'src/data/img/陈奕迅.jpg', '1', '歌手、演员', '华星唱片公司', '《爱情转移》', '英国金斯顿大学', 'C', '1');
INSERT INTO `singer` VALUES ('4', '光良', '情歌王子', '国语', '1970/8/30\r\n', '174.00', '男', '马来西亚', '马来西亚怡保', '处女座', '59.00', 'src/data/img/光良.jpg', '1', ':歌手 音乐人 制作人 演员', '星娱音乐', '《童话》', '马来西亚连曼莎拉吾达马学院', 'G', '1');
INSERT INTO `singer` VALUES ('5', '林俊杰', '', '英语;国语;', '1981/3/27\r\n', '172.00', '男', '新加坡', '新加坡', '白羊座', '60.00', 'src/data/img/LinJunJie.jpg', '1', '歌手、制作人、潮牌主理人', '星娱音乐', '《江南》', '美国音乐学院', 'L', '1');
INSERT INTO `singer` VALUES ('6', '王嘉尔', '王+2、王饺子', '国语', '1994/3/28\r\n', '176.00', '男', '中国香港', '中国香港', '牡羊座', '58.00', 'src/data/img/王嘉尔.jpg', '1', '歌手、主持人', 'JYP Entertainment、王嘉尔工作室', '《Papillon》', '', 'W', '1');
INSERT INTO `singer` VALUES ('7', '王俊凯', '小凯', '国语;', '1999/9/21\r\n', '176.00', '男', '中国', '重庆市', '处女座', '58.00', 'src/data/img/王俊凯.jpg', '1', '歌手、演员', '北京时代峰峻文化艺术发展有限公司', '《青春修炼手册》', '重庆八中', 'W', '1');
INSERT INTO `singer` VALUES ('8', '王力宏', '', '英语;国语;', '1976/5/17\r\n', '180.00', '男', '美国', '纽约罗切斯特', '金牛座', '66.00', 'src/data/img/王力宏.jpg', '1', '歌手、音乐制作人、演员、导演', '宏声艺人经纪有限公司、歌邦娱乐', '《唯一》', '', 'W', '1');
INSERT INTO `singer` VALUES ('9', '王源', '', '国语', '2000/11/8\r\n', '177.00', '男', '中国', '重庆市', '天蝎座', '55.00', 'src/data/img/王源.jpg', '1', '歌手、演员、主持人', '北京时代峰峻文化艺术发展公司', '《因为遇见你》', '南开中学', 'W', '1');
INSERT INTO `singer` VALUES ('10', '吴亦凡', '', '国语;韩语;', '1990/11/6\r\n', '187.00', '男', '加拿大', '中国广东省广州市', '天蝎座', '58.00', 'src/data/img/吴亦凡.png', '1', '演员、歌手', '吴亦凡工作室', '《有一个地方》', '加拿大温斯顿爵士邱吉尔中等学校', 'W', '0');
INSERT INTO `singer` VALUES ('11', '五月天', '', '国语;', '', null, '男', '中国台湾', '中国台湾', '', null, 'src/data/img/五月天.jpg', '2', '歌手', '相信音乐', '《志明与春娇》', '', 'W', '0');
INSERT INTO `singer` VALUES ('12', '展展与罗罗', 'zl', '国语', '1987/5/15', '170.00', '男', '中国', '中国', '金牛座', '60.00', 'src/data/img/ZZHeLL.jpg', '1', '独立音乐人', '', '《沙漠骆驼》', '', 'Z', '1');
INSERT INTO `singer` VALUES ('13', '张学友', '七仔', '英语;国语;粤语', '1987/7/10\r\n', '176.00', '男', '中国香港', '中国香港鲗鱼涌', '巨蟹座', '64.00', 'src/data/img/张学友.jpg', '1', '歌手、演员', '上华唱片', '《吻别》、《祝福》', '北角官立小学，崇文英文书院', 'Z', '1');
INSERT INTO `singer` VALUES ('14', '张艺兴', '小绵羊', '国语;韩语;', '1991/10/7\r\n', '178.00', '男', '中国', '湖南长沙', '天秤座', '60.00', 'src/data/img/张艺兴.jpg', '1', '歌手、演员、主持人', 'SM娱乐（2012年至今）、张艺兴工作室', '《SHEEP》', '湖南师范大学附属中学', 'Z', '1');
INSERT INTO `singer` VALUES ('15', '周杰伦', '', '国语;', '1979/1/18\r\n', '173.00', '男', '中国台湾', '台湾新北市林口区', '魔羯座', '60.00', 'src/data/img/ZhouJieLun.jpg', '1', '音乐人、制作人、导演、企业家等', '杰威尔音乐有限公司', '《龙卷风》', '淡江中学音乐科', 'Z', '1');
INSERT INTO `singer` VALUES ('16', '庄心妍', '', '国语;', '1979/1/18\r\n', '165.00', '女', '中国', '广东陆丰', '魔羯座', '53.00', 'src/data/img/ZXinYan.jpg', '1', '歌手', '致力音乐', '《一万个舍不得》', '', 'Z', '1');
INSERT INTO `singer` VALUES ('17', 'Eminem', '', '英语;', '1972/10/17\r\n', '173.00', '男', '美国', '美国密苏里州圣约瑟夫', '天秤座', '76.00', 'src/data/img/Eminem.jpg', '5', '歌手、演员、音乐制作人', '', '《My Name Is》', '香港演艺学院', 'E', '1');
INSERT INTO `singer` VALUES ('18', '邓紫棋', '', '国语;粤语;', '1991/8/16\r\n', '160.00', '女', '中国香港', '上海', '狮子座', '45.00', 'src/data/img/DengZiQi.jpg', '1', '演员、歌手', '星娱音乐', '《睡公主》', '', 'D', '1');
INSERT INTO `singer` VALUES ('19', 'S.H.E', 'SHE', '国语;', '', null, '女', '中国台湾', '中国台湾', '', null, 'src/data/img/S.H.E.jpg', '1', '演员、歌手', '华研国际音乐股份有限公司', '《美丽新世界》', '', 'S', '1');

-- ----------------------------
-- Table structure for `singer_type`
-- ----------------------------
DROP TABLE IF EXISTS `singer_type`;
CREATE TABLE `singer_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别编号',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '类别名称',
  `remark` varchar(128) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of singer_type
-- ----------------------------
INSERT INTO `singer_type` VALUES ('1', '华语', '1');
INSERT INTO `singer_type` VALUES ('2', '华语组合', '1');
INSERT INTO `singer_type` VALUES ('3', '日韩', '1');
INSERT INTO `singer_type` VALUES ('4', '日韩组合', '1');
INSERT INTO `singer_type` VALUES ('5', '欧美', '1');
INSERT INTO `singer_type` VALUES ('6', '欧美组合', '1');

-- ----------------------------
-- Table structure for `song`
-- ----------------------------
DROP TABLE IF EXISTS `song`;
CREATE TABLE `song` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '歌曲编号',
  `name` varchar(128) NOT NULL DEFAULT '' COMMENT '歌曲名称',
  `time` varchar(64) NOT NULL DEFAULT '' COMMENT '上架时间',
  `lyric` varchar(512) NOT NULL DEFAULT '' COMMENT '歌词',
  `mp3` varchar(512) NOT NULL DEFAULT '' COMMENT '音频文件',
  `count` int(11) NOT NULL DEFAULT '0' COMMENT '播放次数',
  `duration` varchar(64) NOT NULL DEFAULT '' COMMENT '歌曲时长',
  `singerid` int(11) NOT NULL COMMENT '歌手编号',
  `song_typeid` int(11) NOT NULL COMMENT '歌曲类别编号',
  `remark` varchar(256) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `fk_singId` (`singerid`),
  KEY `fk_song_type` (`song_typeid`),
  CONSTRAINT `fk_singer_id` FOREIGN KEY (`singerid`) REFERENCES `singer` (`id`),
  CONSTRAINT `fk_song_type_id` FOREIGN KEY (`song_typeid`) REFERENCES `song_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of song
-- ----------------------------
INSERT INTO `song` VALUES ('1', '四季', '2019-03-14', 'src/data/lrc/滨崎步-四季.lrc', 'src/data/mp3/滨崎步-四季.mp3', '13', '4:25', '1', '8', '1');
INSERT INTO `song` VALUES ('2', '最爱', '2019-03-14', 'src/data/lrc/滨崎步-Dearest最爱.lrc', 'src/data/mp3/滨崎步-Dearest最爱.mp3', '11', '5:35', '1', '8', '1');
INSERT INTO `song` VALUES ('3', 'Who', '2019-03-14', 'src/data/lrc/滨崎步-Who.lrc', 'src/data/mp3/滨崎步-Who.mp3', '12', '5:36', '1', '8', '1');
INSERT INTO `song` VALUES ('4', '别找我麻烦', '2019-03-14', 'src/data/lrc/蔡健雅-别找我麻烦.lrc', 'src/data/mp3/蔡健雅-别找我麻烦.mp3', '14', '4:17', '2', '1', '1');
INSERT INTO `song` VALUES ('5', '红色高跟鞋', '2019-03-14', 'src/data/lrc/蔡健雅-红色高跟鞋.lrc', 'src/data/mp3/蔡健雅-红色高跟鞋.mp3', '11', '1:51', '2', '3', '1');
INSERT INTO `song` VALUES ('6', '周三的情书', '2019-03-14', 'src/data/lrc/蔡健雅-周三的情书.lrc', 'src/data/mp3/蔡健雅-周三的情书.mp3', '10', '4:08', '2', '3', '1');
INSERT INTO `song` VALUES ('7', '光辉岁月', '2019-03-14', 'src/data/lrc/陈奕迅-光辉岁月.lrc', 'src/data/mp3/陈奕迅-光辉岁月.mp3', '16', '4:01', '3', '5', '1');
INSERT INTO `song` VALUES ('8', '好久不见', '2019-03-14', 'src/data/lrc/陈奕迅-好久不见.lrc', 'src/data/mp3/陈奕迅-好久不见.mp3', '13', '4:13', '3', '5', '1');
INSERT INTO `song` VALUES ('9', '每一个人', '2019-03-14', 'src/data/lrc/陈奕迅-每一个人.lrc', 'src/data/mp3/陈奕迅-每一个人.mp3', '14', '5:23', '3', '3', '1');
INSERT INTO `song` VALUES ('10', '梦想天空分外蓝', '2019-03-14', 'src/data/lrc/陈奕迅-梦想天空分外蓝.lrc', 'src/data/mp3/陈奕迅-梦想天空分外蓝.mp3', '14', '3:32', '3', '1', '1');
INSERT INTO `song` VALUES ('11', '真的英雄', '2019-03-14', 'src/data/lrc/陈奕迅-真的英雄.lrc', 'src/data/mp3/陈奕迅-真的英雄.mp3', '20', '4:15', '3', '5', '1');
INSERT INTO `song` VALUES ('12', '淘汰', '2019-03-14', 'src/data/lrc/陈奕迅周杰伦-淘汰.lrc', 'src/data/mp3/陈奕迅周杰伦-淘汰.mp3', '46', '3:58', '3', '3', '1');
INSERT INTO `song` VALUES ('13', '烟火', '2019-03-14', 'src/data/lrc/光良-烟火.lrc', 'src/data/mp3/光良-烟火.mp3', '24', '4:10', '4', '1', '1');
INSERT INTO `song` VALUES ('14', '勇气', '2019-03-14', 'src/data/lrc/光良-勇气.lrc', 'src/data/mp3/光良-勇气.mp3', '30', '4:15', '4', '6', '1');
INSERT INTO `song` VALUES ('15', '背对背拥抱', '2019-03-14', 'src/data/lrc/林俊杰-背对背拥抱.lrc', 'src/data/mp3/林俊杰-背对背拥抱.mp3', '29', '3:58', '5', '3', '1');
INSERT INTO `song` VALUES ('16', '不为谁而作的歌', '2019-03-14', 'src/data/lrc/林俊杰-不为谁而作的歌.lrc', 'src/data/mp3/林俊杰-不为谁而作的歌.mp3', '37', '3:57', '5', '3', '1');
INSERT INTO `song` VALUES ('17', '翅膀', '2019-03-14', 'src/data/lrc/林俊杰-翅膀.lrc', 'src/data/mp3/林俊杰-翅膀.mp3', '27', '4:29', '5', '1', '1');
INSERT INTO `song` VALUES ('18', '江南', '2019-03-14', 'src/data/lrc/林俊杰-江南.lrc', 'src/data/mp3/林俊杰-江南.mp3', '42', '4:28', '5', '6', '1');
INSERT INTO `song` VALUES ('19', '她说', '2019-03-14', 'src/data/lrc/林俊杰-她说.lrc', 'src/data/mp3/林俊杰-她说.mp3', '17', '5:20', '5', '6', '1');
INSERT INTO `song` VALUES ('20', '小酒窝', '2019-03-14', 'src/data/lrc/林俊杰-小酒窝.lrc', 'src/data/mp3/林俊杰-小酒窝.mp3', '18', '3:37', '5', '6', '1');
INSERT INTO `song` VALUES ('21', '小瓶子', '2019-03-14', 'src/data/lrc/林俊杰-小瓶子.lrc', 'src/data/mp3/林俊杰-小瓶子.mp3', '21', '4:06', '5', '2', '1');
INSERT INTO `song` VALUES ('22', 'AlwaysOnline', '2019-03-14', 'src/data/lrc/林俊杰-AlwaysOnline.lrc', 'src/data/mp3/林俊杰-AlwaysOnline.mp3', '20', '3:45', '5', '1', '1');
INSERT INTO `song` VALUES ('23', '遇见', '2019-03-14', 'src/data/lrc/林俊杰孙燕姿-遇见.lrc', 'src/data/mp3/林俊杰孙燕姿-遇见.mp3', '5', '3:56', '5', '3', '1');
INSERT INTO `song` VALUES ('24', '外面的世界', '2019-03-14', 'src/data/lrc/莫文蔚陈奕迅-外面的世界.lrc', 'src/data/mp3/莫文蔚陈奕迅-外面的世界.mp3', '10', '4:32', '3', '3', '1');
INSERT INTO `song` VALUES ('25', '因为爱情', '2019-03-14', 'src/data/lrc/王菲陈奕迅-因为爱情.lrc', 'src/data/mp3/王菲陈奕迅-因为爱情.mp3', '34', '1:49', '3', '3', '1');
INSERT INTO `song` VALUES ('26', '拜托了', '2019-03-14', 'src/data/lrc/王嘉尔-拜托了.lrc', 'src/data/mp3/王嘉尔-拜托了.mp3', '17', '2:38', '6', '1', '1');
INSERT INTO `song` VALUES ('27', '九州天空城', '2019-03-14', 'src/data/lrc/王嘉尔-九州天空城 .lrc', 'src/data/mp3/王嘉尔-九州天空城 .mp3', '20', '4:15', '6', '4', '1');
INSERT INTO `song` VALUES ('28', 'Papillon', '2019-03-14', 'src/data/lrc/王嘉尔-Papillon.lrc', 'src/data/mp3/王嘉尔-Papillon.mp3', '20', '3:15', '6', '4', '1');
INSERT INTO `song` VALUES ('29', 'X', '2019-03-14', 'src/data/lrc/王嘉尔-X.lrc', 'src/data/mp3/王嘉尔-X.mp3', '14', '2:12', '6', '7', '1');
INSERT INTO `song` VALUES ('30', '童年', '2019-03-14', 'src/data/lrc/王俊凯-童年.lrc', 'src/data/mp3/王俊凯-童年.mp3', '22', '0:52', '7', '6', '1');
INSERT INTO `song` VALUES ('31', '我们曾在一起', '2019-03-14', 'src/data/lrc/王俊凯-我们曾在一起.lrc', 'src/data/mp3/王俊凯-我们曾在一起.mp3', '20', '3:54', '7', '3', '1');
INSERT INTO `song` VALUES ('32', '我在诛仙逍遥涧', '2019-03-14', 'src/data/lrc/王俊凯-我在诛仙逍遥涧.lrc', 'src/data/mp3/王俊凯-我在诛仙逍遥涧.mp3', '9', '4:16', '7', '1', '1');
INSERT INTO `song` VALUES ('33', '好心分手', '2019-03-14', 'src/data/lrc/王力宏-好心分手.lrc', 'src/data/mp3/王力宏-好心分手.mp3', '9', '3:02', '8', '3', '1');
INSERT INTO `song` VALUES ('34', '天下相亲与相爱', '2019-03-14', 'src/data/lrc/王力宏-天下相亲与相爱.lrc', 'src/data/mp3/王力宏-天下相亲与相爱.mp3', '10', '6:13', '8', '5', '1');
INSERT INTO `song` VALUES ('35', '需要人陪', '2019-03-14', 'src/data/lrc/王力宏-需要人陪.lrc', 'src/data/mp3/王力宏-需要人陪.mp3', '8', '0:49', '8', '3', '1');
INSERT INTO `song` VALUES ('36', '梦想起航', '2019-03-14', 'src/data/lrc/王源-梦想起航.lrc', 'src/data/mp3/王源-梦想起航.mp3', '12', '2:51', '9', '1', '1');
INSERT INTO `song` VALUES ('37', '明天,你好', '2019-03-14', 'src/data/lrc/王源-明天,你好.lrc', 'src/data/mp3/王源-明天,你好.mp3', '11', '4:33', '9', '3', '1');
INSERT INTO `song` VALUES ('38', '因为遇见你', '2019-03-14', 'src/data/lrc/王源-因为遇见你.lrc', 'src/data/mp3/王源-因为遇见你.mp3', '59', '4:37', '9', '3', '1');
INSERT INTO `song` VALUES ('39', '刀剑如梦', '2019-03-14', 'src/data/lrc/吴亦凡-刀剑如梦.lrc', 'src/data/mp3/吴亦凡-刀剑如梦.mp3', '6', '3:33', '10', '1', '1');
INSERT INTO `song` VALUES ('40', '花房姑娘', '2019-03-14', 'src/data/lrc/吴亦凡-花房姑娘.lrc', 'src/data/mp3/吴亦凡-花房姑娘.mp3', '9', '3:49', '10', '4', '1');
INSERT INTO `song` VALUES ('41', '家人', '2019-03-14', 'src/data/lrc/吴亦凡-家人.lrc', 'src/data/mp3/吴亦凡-家人.mp3', '6', '3:42', '10', '4', '1');
INSERT INTO `song` VALUES ('42', '情非得已', '2019-03-14', 'src/data/lrc/吴亦凡-情非得已.lrc', 'src/data/mp3/吴亦凡-情非得已.mp3', '6', '1:05', '10', '3', '1');
INSERT INTO `song` VALUES ('43', '英雄之火', '2019-03-14', 'src/data/lrc/吴亦凡-英雄之火.lrc', 'src/data/mp3/吴亦凡-英雄之火.mp3', '3', '5:52', '10', '1', '1');
INSERT INTO `song` VALUES ('44', '制作人', '2019-03-14', 'src/data/lrc/吴亦凡-制作人.lrc', 'src/data/mp3/吴亦凡-制作人.mp3', '9', '4:59', '10', '2', '1');
INSERT INTO `song` VALUES ('45', '后来的我们', '2019-03-14', 'src/data/lrc/五月天-后来的我们.lrc', 'src/data/mp3/五月天-后来的我们.mp3', '10', '0:07', '11', '3', '1');
INSERT INTO `song` VALUES ('46', '离开地球表面', '2019-03-14', 'src/data/lrc/五月天-离开地球表面.lrc', 'src/data/mp3/五月天-离开地球表面.mp3', '8', '4:51', '11', '3', '1');
INSERT INTO `song` VALUES ('47', '没什么不一样', '2019-03-14', 'src/data/lrc/五月天-没什么不一样.lrc', 'src/data/mp3/五月天-没什么不一样.mp3', '6', '0:08', '11', '3', '1');
INSERT INTO `song` VALUES ('48', '入阵曲', '2019-03-14', 'src/data/lrc/五月天-入阵曲.lrc', 'src/data/mp3/五月天-入阵曲.mp3', '9', '3:29', '11', '6', '1');
INSERT INTO `song` VALUES ('49', '疼爱', '2019-03-14', 'src/data/lrc/五月天-疼爱.lrc', 'src/data/mp3/五月天-疼爱.mp3', '6', '4:07', '11', '3', '1');
INSERT INTO `song` VALUES ('50', '缘份', '2019-03-14', 'src/data/lrc/五月天-缘份.lrc', 'src/data/mp3/五月天-缘份.mp3', '2', '6:23', '11', '3', '1');
INSERT INTO `song` VALUES ('51', '一生所爱', '2019-03-14', 'src/data/lrc/展展与罗罗-一生所爱.lrc', 'src/data/mp3/展展与罗罗-一生所爱.mp3', '3', '5:52', '12', '5', '1');
INSERT INTO `song` VALUES ('52', '沙漠骆驼', '2019-03-14', 'src/data/lrc/展展与罗罗斗鱼寅子-沙漠骆驼.lrc', 'src/data/mp3/展展与罗罗斗鱼寅子-沙漠骆驼.mp3', '5', '1:01', '12', '6', '1');
INSERT INTO `song` VALUES ('53', '爱心语录', '2019-03-14', 'src/data/lrc/张学友-爱心语录.lrc', 'src/data/mp3/张学友-爱心语录.mp3', '5', '6:58', '13', '3', '1');
INSERT INTO `song` VALUES ('54', '听海', '2019-03-14', 'src/data/lrc/张学友-听海.lrc', 'src/data/mp3/张学友-听海.mp3', '6', '4:47', '13', '5', '1');
INSERT INTO `song` VALUES ('55', '独角戏', '2019-03-14', 'src/data/lrc/张艺兴-独角戏.lrc', 'src/data/mp3/张艺兴-独角戏.mp3', '5', '4:05', '14', '3', '1');
INSERT INTO `song` VALUES ('56', '面罩', '2019-03-14', 'src/data/lrc/张艺兴-面罩.lrc', 'src/data/mp3/张艺兴-面罩.mp3', '8', '3:06', '14', '3', '1');
INSERT INTO `song` VALUES ('57', '男人的事', '2019-03-14', 'src/data/lrc/张艺兴-男人的事.lrc', 'src/data/mp3/张艺兴-男人的事.mp3', '10', '4:45', '14', '2', '1');
INSERT INTO `song` VALUES ('58', '祈愿', '2019-03-14', 'src/data/lrc/张艺兴-祈愿.lrc', 'src/data/mp3/张艺兴-祈愿.mp3', '7', '3:29', '14', '3', '1');
INSERT INTO `song` VALUES ('59', '青春快乐', '2019-03-14', 'src/data/lrc/张艺兴-青春快乐.lrc', 'src/data/mp3/张艺兴-青春快乐.mp3', '10', '3:30', '14', '1', '1');
INSERT INTO `song` VALUES ('60', '一个人', '2019-03-14', 'src/data/lrc/张艺兴-一个人.lrc', 'src/data/mp3/张艺兴-一个人.mp3', '7', '3:51', '14', '3', '1');
INSERT INTO `song` VALUES ('61', '最好的舞台', '2019-03-14', 'src/data/lrc/张艺兴-最好的舞台.lrc', 'src/data/mp3/张艺兴-最好的舞台.mp3', '6', '4:00', '14', '4', '1');
INSERT INTO `song` VALUES ('62', '不能说的秘密', '2019-03-14', 'src/data/lrc/周杰伦-不能说的秘密.lrc', 'src/data/mp3/周杰伦-不能说的秘密.mp3', '12', '5:35', '15', '3', '1');
INSERT INTO `song` VALUES ('63', '稻香', '2019-03-14', 'src/data/lrc/周杰伦-稻香.lrc', 'src/data/mp3/周杰伦-稻香.mp3', '17', '4:00', '15', '6', '1');
INSERT INTO `song` VALUES ('64', '东风破', '2019-03-14', 'src/data/lrc/周杰伦-东风破.lrc', 'src/data/mp3/周杰伦-东风破.mp3', '14', '5:15', '15', '1', '1');
INSERT INTO `song` VALUES ('65', '霍元甲', '2019-03-14', 'src/data/lrc/周杰伦-霍元甲.lrc', 'src/data/mp3/周杰伦-霍元甲.mp3', '10', '4:35', '15', '1', '1');
INSERT INTO `song` VALUES ('66', '菊花台', '2019-03-14', 'src/data/lrc/周杰伦-菊花台.lrc', 'src/data/mp3/周杰伦-菊花台.mp3', '11', '4:11', '15', '3', '1');
INSERT INTO `song` VALUES ('67', '千里之外', '2019-03-14', 'src/data/lrc/周杰伦-千里之外.lrc', 'src/data/mp3/周杰伦-千里之外(Live).mp3', '10', '6:14', '15', '3', '1');
INSERT INTO `song` VALUES ('68', '前世情人', '2019-03-14', 'src/data/lrc/周杰伦-前世情人.lrc', 'src/data/mp3/周杰伦-前世情人.mp3', '12', '3:27', '15', '2', '1');
INSERT INTO `song` VALUES ('69', '青花瓷', '2019-03-14', 'src/data/lrc/周杰伦-青花瓷.lrc', 'src/data/mp3/周杰伦-青花瓷.mp3', '10', '3:59', '15', '1', '1');
INSERT INTO `song` VALUES ('70', '淘汰', '2019-03-14', 'src/data/lrc/周杰伦-淘汰.lrc', 'src/data/mp3/周杰伦-淘汰.mp3', '8', '0:34', '15', '5', '1');
INSERT INTO `song` VALUES ('71', '夜店咖', '2019-03-14', 'src/data/lrc/周杰伦-夜店咖.lrc', 'src/data/mp3/周杰伦-夜店咖.mp3', '8', '2:50', '15', '3', '1');
INSERT INTO `song` VALUES ('72', '一路上有你', '2019-03-14', 'src/data/lrc/周杰伦-一路上有你.lrc', 'src/data/mp3/周杰伦-一路上有你.mp3', '11', '0:23', '15', '2', '1');
INSERT INTO `song` VALUES ('73', '画沙', '2019-03-14', 'src/data/lrc/周杰伦袁咏琳-画沙.lrc', 'src/data/mp3/周杰伦袁咏琳-画沙.mp3', '7', '4:33', '15', '3', '1');
INSERT INTO `song` VALUES ('74', '不要这样好不好', '2019-03-14', 'src/data/lrc/庄心妍-不要这样好不好.lrc', 'src/data/mp3/庄心妍-不要这样好不好.mp3', '4', '4:30', '16', '3', '1');
INSERT INTO `song` VALUES ('75', '放过自己', '2019-03-14', 'src/data/lrc/庄心妍-放过自己 (Live).lrc', 'src/data/mp3/庄心妍-放过自己.mp3', '6', '4:05', '16', '5', '1');
INSERT INTO `song` VALUES ('76', '海阔天空', '2019-03-14', 'src/data/lrc/庄心妍-海阔天空.lrc', 'src/data/mp3/庄心妍-海阔天空.mp3', '10', '4:12', '16', '5', '1');
INSERT INTO `song` VALUES ('77', '好可惜', '2019-03-14', 'src/data/lrc/庄心妍-好可惜(DJ版).lrc', 'src/data/mp3/庄心妍-好可惜(DJ版).mp3', '8', '6:52', '16', '2', '1');
INSERT INTO `song` VALUES ('78', '月牙湾', '2019-03-14', 'src/data/lrc/庄心妍-月牙湾.lrc', 'src/data/mp3/庄心妍-月牙湾.mp3', '5', '4:27', '16', '6', '1');
INSERT INTO `song` VALUES ('79', '再见只是陌生人', '2019-03-14', 'src/data/lrc/庄心妍-再见只是陌生人 (Live).lrc', 'src/data/mp3/庄心妍-再见只是陌生人 (Live).mp3', '12', '4:03', '16', '3', '1');
INSERT INTO `song` VALUES ('80', '真的不容易', '2019-03-14', 'src/data/lrc/庄心妍焦作风景-真的不容易.lrc', 'src/data/mp3/庄心妍焦作风景-真的不容易.mp3', '12', '3:56', '16', '3', '1');
INSERT INTO `song` VALUES ('81', '再见陌生人', '2019-03-14', 'src/data/lrc/庄心妍DJ迪迪-再见陌生人.lrc', 'src/data/mp3/庄心妍DJ迪迪-再见陌生人.mp3', '19', '4:54', '16', '3', '1');
INSERT INTO `song` VALUES ('82', 'Killshot', '2019-03-14', 'src/data/lrc/Eminem-Killshot.lrc', 'src/data/mp3/Eminem-Killshot.mp3', '13', '4:13', '17', '7', '1');
INSERT INTO `song` VALUES ('83', 'Lighters', '2019-03-14', 'src/data/lrc/Eminem-Lighters.lrc', 'src/data/mp3/Eminem-Lighters.mp3', '14', '5:03', '17', '7', '1');
INSERT INTO `song` VALUES ('84', '北京北京', '2019-03-14', 'src/data/lrc/G.E.M.邓紫棋-北京北京.lrc', 'src/data/mp3/G.E.M.邓紫棋-北京北京.mp3', '20', '4:41', '18', '3', '1');
INSERT INTO `song` VALUES ('85', '穿越火线', '2019-03-14', 'src/data/lrc/G.E.M.邓紫棋-穿越火线.lrc', 'src/data/mp3/G.E.M.邓紫棋-穿越火线.mp3', '21', '3:16', '18', '4', '1');
INSERT INTO `song` VALUES ('86', '泡沫', '2019-03-14', 'src/data/lrc/G.E.M.邓紫棋-泡沫.lrc', 'src/data/mp3/G.E.M.邓紫棋-泡沫.mp3', '13', '4:18', '18', '3', '1');
INSERT INTO `song` VALUES ('87', '囚鸟', '2019-03-14', 'src/data/lrc/G.E.M.邓紫棋-囚鸟.lrc', 'src/data/mp3/G.E.M.邓紫棋-囚鸟.mp3', '26', '4:41', '18', '2', '1');
INSERT INTO `song` VALUES ('88', '桃花诺', '2019-03-14', 'src/data/lrc/G.E.M.邓紫棋-桃花诺.lrc', 'src/data/mp3/G.E.M.邓紫棋-桃花诺.mp3', '6', '3:39', '18', '4', '1');
INSERT INTO `song` VALUES ('89', '喜欢你', '2019-03-14', 'src/data/lrc/G.E.M.邓紫棋-喜欢你.lrc', 'src/data/mp3/G.E.M.邓紫棋-喜欢你.mp3', '13', '3:39', '18', '3', '1');
INSERT INTO `song` VALUES ('90', '一路逆风', '2019-03-14', 'src/data/lrc/G.E.M.邓紫棋-一路逆风.lrc', 'src/data/mp3/G.E.M.邓紫棋-一路逆风.mp3', '6', '3:45', '18', '6', '1');
INSERT INTO `song` VALUES ('91', '有心人', '2019-03-14', 'src/data/lrc/G.E.M.邓紫棋-有心人.lrc', 'src/data/mp3/G.E.M.邓紫棋-有心人.mp3', '3', '4:00', '18', '3', '1');
INSERT INTO `song` VALUES ('92', '于是', '2019-03-14', 'src/data/lrc/G.E.M.邓紫棋-于是.lrc', 'src/data/mp3/G.E.M.邓紫棋-于是.mp3', '9', '3:49', '18', '6', '1');
INSERT INTO `song` VALUES ('93', '再见', '2019-03-14', 'src/data/lrc/G.E.M.邓紫棋-再见.lrc', 'src/data/mp3/G.E.M.邓紫棋-再见.mp3', '8', '3:26', '18', '9', '1');
INSERT INTO `song` VALUES ('94', '恋人未满', '2019-03-14', 'src/data/lrc/S.H.E-恋人未满.lrc', 'src/data/mp3/S.H.E-恋人未满.mp3', '1', '4:36', '19', '3', '1');
INSERT INTO `song` VALUES ('95', '中国话', '2019-03-14', 'src/data/lrc/S.H.E-中国话.lrc', 'src/data/mp3/S.H.E-中国话.mp3', '9', '3:15', '19', '9', '0');

-- ----------------------------
-- Table structure for `song_type`
-- ----------------------------
DROP TABLE IF EXISTS `song_type`;
CREATE TABLE `song_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '歌曲类别编号',
  `name` varchar(64) NOT NULL DEFAULT '' COMMENT '类别名称',
  `remark` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of song_type
-- ----------------------------
INSERT INTO `song_type` VALUES ('1', '网络', '1');
INSERT INTO `song_type` VALUES ('2', 'DJ', '1');
INSERT INTO `song_type` VALUES ('3', '伤感', '1');
INSERT INTO `song_type` VALUES ('4', '影视', '1');
INSERT INTO `song_type` VALUES ('5', 'KTV', '1');
INSERT INTO `song_type` VALUES ('6', '民谣', '1');
INSERT INTO `song_type` VALUES ('7', '欧美', '1');
INSERT INTO `song_type` VALUES ('8', '日韩', '1');
INSERT INTO `song_type` VALUES ('9', '华语', '1');

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `username` varchar(64) NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(64) NOT NULL DEFAULT '' COMMENT '用户密码',
  `role` int(11) NOT NULL DEFAULT '1' COMMENT '角色（1.前台用户，0.管理员）',
  `time` varchar(64) NOT NULL DEFAULT '' COMMENT '注册时间',
  `picture` varchar(512) NOT NULL DEFAULT '' COMMENT '用户头像',
  `tel` varchar(64) NOT NULL DEFAULT '' COMMENT '手机',
  `remark` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '很牛逼', '111111', '1', '2019-03-24 17:42:34', 'img/user.jpg', '15179975576', '1');
INSERT INTO `users` VALUES ('2', '贺南彬', '111111', '0', '2019-05-08 14:14:14', 'img/user.jpg', '17379914329', '1');
INSERT INTO `users` VALUES ('3', 'test', '111111', '1', '2019-05-08 16:42:58', 'img/user.jpg', '15179975577', '1');
INSERT INTO `users` VALUES ('4', 'test', '111111', '1', '2019-05-08 16:43:48', 'img/user.jpg', '15179975578', '1');
INSERT INTO `users` VALUES ('5', 'test2', '111111', '1', '2019-05-08 16:44:10', 'img/user.jpg', '15179975579', '1');
INSERT INTO `users` VALUES ('6', '1111', '111111', '1', '2019-05-13 16:50:59', 'img/user.jpg', '15179975588', '1');
INSERT INTO `users` VALUES ('7', 'Hero云', '111111', '1', '2019-05-27 15:29:53', 'img/user.jpg', '15179999999', '1');

-- ----------------------------
-- View structure for `view_allmessage`
-- ----------------------------
DROP VIEW IF EXISTS `view_allmessage`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_allmessage` AS (select `so`.`id` AS `songid`,`so`.`name` AS `songname`,`so`.`count` AS `songcount`,`so`.`time` AS `songtime`,`si`.`id` AS `singerid`,`si`.`name` AS `singername`,`si`.`picture` AS `picture`,`st`.`id` AS `singertypeid`,`st`.`name` AS `singertypename`,`sty`.`id` AS `songtypeid`,`sty`.`name` AS `songtypename`,`so`.`time` AS `uptime` from (((`song` `so` join `singer` `si` on((`so`.`singerid` = `si`.`id`))) join `singer_type` `st` on((`si`.`singer_typeid` = `st`.`id`))) join `song_type` `sty` on((`sty`.`id` = `so`.`song_typeid`)))) ;
