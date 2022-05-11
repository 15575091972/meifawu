/*
Navicat MySQL Data Transfer

Source Server         : moyun
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : meifawu

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2022-05-03 14:18:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admins
-- ----------------------------
DROP TABLE IF EXISTS `admins`;
CREATE TABLE `admins` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `admin_name` varchar(255) DEFAULT NULL,
  `admin_password` varchar(255) DEFAULT NULL,
  `admin_agency` varchar(255) DEFAULT NULL COMMENT '管理员所属门店',
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of admins
-- ----------------------------
INSERT INTO `admins` VALUES ('1', 'meifawu', 'e10adc3949ba59abbe56e057f20f883e', '美发屋造型');
INSERT INTO `admins` VALUES ('2', 'ximending', 'e10adc3949ba59abbe56e057f20f883e', '西门町造型');

-- ----------------------------
-- Table structure for advice
-- ----------------------------
DROP TABLE IF EXISTS `advice`;
CREATE TABLE `advice` (
  `advice_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '投诉建议id',
  `nick_name` varchar(255) DEFAULT NULL COMMENT '用户昵称',
  `advice_detail` varchar(255) DEFAULT NULL COMMENT '投诉详情',
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`advice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of advice
-- ----------------------------
INSERT INTO `advice` VALUES ('1', '魔云连洲', '要是可以多点特殊服务就好了！', '18711965665');
INSERT INTO `advice` VALUES ('2', '我不吃饭', '我觉得你们店的顾问技术不咋地', '19888766877');
INSERT INTO `advice` VALUES ('3', '唐朝的宇宙', '剪发剪得又快又好，但是理发师都是男的，有小姐姐就好了', '');
INSERT INTO `advice` VALUES ('8', '魔云连洲', '多搞点活动可能生意会更加兴隆', '');
INSERT INTO `advice` VALUES ('9', '魔云连洲', '刘诚可最帅，不容反驳', '18711965665');
INSERT INTO `advice` VALUES ('10', '魔云连洲', 'gdffg', '234235235');

-- ----------------------------
-- Table structure for assess
-- ----------------------------
DROP TABLE IF EXISTS `assess`;
CREATE TABLE `assess` (
  `assess_id` int(11) NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(255) DEFAULT NULL,
  `assess_level_environment` float(10,2) DEFAULT NULL,
  `assess_level_skill` float(10,2) DEFAULT NULL,
  `assess_level_serve` float(10,2) DEFAULT NULL,
  `assess_content` text,
  `assess_picture` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`assess_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of assess
-- ----------------------------
INSERT INTO `assess` VALUES ('1', '魔云连洲', '3.50', '2.00', '1.50', 'sdf', 'http://h40421346u.wicp.vip:18232/977ce353-41b5-41fa-9176-f695e064a3b6PCpf23kM00Nkdac9e50a905c54f723704caf01a150c1.png');
INSERT INTO `assess` VALUES ('10', '魔云连洲', '3.50', '2.00', '3.00', 'jiuhuih', 'http://h40421346u.wicp.vip:18232/fedb09f6-ddf9-4a18-a435-a2c492c467abHd8K2nmaFTR5dac9e50a905c54f723704caf01a150c1.png');
INSERT INTO `assess` VALUES ('11', '魔云连洲', '3.50', '3.00', '3.00', '服务很好啊！！！', 'http://h40421346u.wicp.vip:18232/02ddedb2-4593-4edd-92b8-a4f2368d7061hWodjKvc67bM8fe143314e344f4b784819ac9f4c71bf.jpg');

-- ----------------------------
-- Table structure for barbers
-- ----------------------------
DROP TABLE IF EXISTS `barbers`;
CREATE TABLE `barbers` (
  `barber_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '理发师id',
  `barber_name` varchar(255) DEFAULT NULL COMMENT '理发师姓名',
  `barber_age` varchar(255) DEFAULT NULL COMMENT '理发师年龄',
  `barber_phone` varchar(255) DEFAULT NULL COMMENT '理发师电话',
  `barber_introduction` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '理发师介绍',
  `barber_goodrate` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '理发师好评率',
  `barber_salary` int(11) DEFAULT NULL COMMENT '理发师薪水',
  `barber_level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '理发师级别（店长、顾问、精英）',
  `barber_image` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '理发师图片',
  `serve_count` int(11) DEFAULT NULL,
  `begoodat` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`barber_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of barbers
-- ----------------------------
INSERT INTO `barbers` VALUES ('1', '李志刚', '35', '15477896546', '理发店店长，美发行业工作15年，经验丰富', '100', '8000', '店长', 'https://m.qpic.cn/psc?/V540JUhK2DGy8y1UeOeQ2prBhs2tFVcI/ruAMsa53pVQWN7FLK88i5qEXpKejC*VsJA8vuk56kmnAe*pt9wSXQD5JXl.00CN93ng4dkQSFZXpb1KCnBYikA078yvnng6DE.EhonCYddA!/mnull&bo=vAK8AgAAAAABByA!&rf=photolist&t=5', '643', '男士快剪、烫染、造型');
INSERT INTO `barbers` VALUES ('2', '赵宇翔', '27', '18977765412', '理发店顾问，健谈，经验丰富', '97.7', '6500', '顾问', 'https://m.qpic.cn/psc?/V540JUhK2DGy8y1UeOeQ2prBhs2tFVcI/ruAMsa53pVQWN7FLK88i5qEXpKejC*VsJA8vuk56kmm3L5pdgwj0IK4ylgqLrRCtFmXG1J5aisqGTju5dVBdHJIDDobL5me7vKebAcmxA6g!/mnull&bo=vAK8AgAAAAABByA!&rf=photolist&t=5', '454', '女士烫染、洗剪吹');
INSERT INTO `barbers` VALUES ('3', '金易强', '22', '17658903348', '理发店精英，不拘泥于形式，善于设计发型', '94.2', '4500', '精英', 'https://m.qpic.cn/psc?/V540JUhK2DGy8y1UeOeQ2prBhs2tFVcI/ruAMsa53pVQWN7FLK88i5kW5N5Ys0ee7XHsIuIBO4XX7.oUF8AV28z..NJLrHA7ZXeelLENwqjPlag1RvZnFnKzw2hvyHFHcYXrc3zd7pu4!/mnull&bo=vAK8AgAAAAABByA!&rf=photolist&t=5', '183', '造型');
INSERT INTO `barbers` VALUES ('4', '张三', '25', '17505254433', '从事美发行业8年啦！2016年毕业于深圳名师堂学院，擅长修剪男士发型。', '90.87', '5000', '精英', 'https://m.qpic.cn/psc?/V540JUhK2DGy8y1UeOeQ2prBhs2tFVcI/ruAMsa53pVQWN7FLK88i5qEXpKejC*VsJA8vuk56kmkER7X7PiQZ6*lXChLJLnr0PFYLzJd0Sbp1MSaaT9izpgTbTgoHk4DoV73gJFALuzU!/mnull&bo=vAK8AgAAAAABByA!&rf=photolist&t=5', '55', '女士洗剪吹');

-- ----------------------------
-- Table structure for barberstate
-- ----------------------------
DROP TABLE IF EXISTS `barberstate`;
CREATE TABLE `barberstate` (
  `state_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '状态编号',
  `the_date` date DEFAULT NULL COMMENT '日期',
  `barber_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '理发师名字',
  `t1_state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '空闲' COMMENT '8:00-10:00空闲或繁忙',
  `t2_state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '空闲' COMMENT '10:00-12:00空闲或繁忙',
  `t3_state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '空闲' COMMENT '14:00-16:00空闲或繁忙',
  `t4_state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '空闲' COMMENT '16:00-18:00空闲或繁忙',
  `t5_state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '空闲' COMMENT '20:00-22:00空闲或繁忙',
  PRIMARY KEY (`state_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of barberstate
-- ----------------------------
INSERT INTO `barberstate` VALUES ('1', '2022-01-01', '李志刚', '空闲', '空闲', '空闲', '空闲', '空闲');
INSERT INTO `barberstate` VALUES ('2', '2021-12-31', '赵宇翔', '空闲', '空闲', '繁忙', '空闲', '空闲');
INSERT INTO `barberstate` VALUES ('3', '2021-12-31', '金易强', '空闲', '空闲', '空闲', '繁忙', '繁忙');
INSERT INTO `barberstate` VALUES ('4', '2021-12-31', '张三', '空闲', '繁忙', '繁忙', '空闲', '空闲');
INSERT INTO `barberstate` VALUES ('5', '2021-12-31', '李胖胖', '空闲', '空闲', '空闲', '繁忙', '繁忙');
INSERT INTO `barberstate` VALUES ('6', '2021-01-01', '李志刚', '空闲', '空闲', '空闲', '空闲', '空闲');

-- ----------------------------
-- Table structure for investrecord
-- ----------------------------
DROP TABLE IF EXISTS `investrecord`;
CREATE TABLE `investrecord` (
  `invest_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '充值记录id',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户微信昵称',
  `invest_date` datetime DEFAULT NULL COMMENT '充值日期',
  `invest_money` float(10,2) DEFAULT NULL COMMENT '充值金额',
  PRIMARY KEY (`invest_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of investrecord
-- ----------------------------
INSERT INTO `investrecord` VALUES ('1', '魔云连洲', '2021-12-28 23:51:38', '1.00');
INSERT INTO `investrecord` VALUES ('4', '魔云连洲', '2021-12-28 23:52:32', '2.00');
INSERT INTO `investrecord` VALUES ('7', '魔云连洲', '2021-12-29 00:01:49', '30.00');
INSERT INTO `investrecord` VALUES ('8', '魔云连洲', '2021-12-29 00:07:47', '598.00');
INSERT INTO `investrecord` VALUES ('9', '魔云连洲', '2021-12-29 19:15:33', '398.00');
INSERT INTO `investrecord` VALUES ('10', '魔云连洲', '2021-12-29 21:34:49', '30.00');
INSERT INTO `investrecord` VALUES ('11', '魔云连洲', '2021-12-30 18:47:48', '1.00');
INSERT INTO `investrecord` VALUES ('12', '魔云连洲', '2021-12-30 18:47:57', '1.00');
INSERT INTO `investrecord` VALUES ('13', '魔云连洲', '2021-12-31 08:40:17', '30.00');
INSERT INTO `investrecord` VALUES ('14', '魔云连洲', '2021-12-31 08:40:27', '598.00');
INSERT INTO `investrecord` VALUES ('15', '魔云连洲', '2022-01-01 13:23:44', '198.00');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '点餐id',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户姓名',
  `user_sex` varchar(255) DEFAULT NULL,
  `menu_detail` varchar(255) DEFAULT NULL COMMENT '点餐详情',
  `menu_price` float(10,2) DEFAULT NULL COMMENT '点餐的价格',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '黄雅', '女', '一杯蜜雪冰城的荔枝啵啵', '8.00');
INSERT INTO `menu` VALUES ('2', '刘彦', '男', '一杯大杯书亦烧仙草（三分糖、少冰、加珍珠），一瓶大可乐', '16.00');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `nick_name` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '预约人姓名',
  `phone` varchar(255) DEFAULT NULL,
  `barber_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '理发师姓名',
  `appointment_date` date DEFAULT NULL COMMENT '预约日期',
  `timeslot` varchar(255) DEFAULT NULL COMMENT '预约时间段',
  `order_price` float(10,2) DEFAULT NULL COMMENT '订单金额',
  `order_detail` varchar(255) DEFAULT NULL COMMENT '订单详情（服务方式）',
  `other_demand` varchar(255) DEFAULT NULL COMMENT '其他需求',
  `verify_state` varchar(255) DEFAULT '未确认' COMMENT '商家确认订单状态',
  `order_state` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '未完成' COMMENT '订单状态（完成/未完成）',
  `order_evaluate` varchar(255) DEFAULT NULL COMMENT '订单评价（好评/一般/差评）',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES ('1', '魔云连洲', '黄雅', '14898765544', '李志刚', '2021-12-30', '8:00-10:00', '500.00', '烫染', '无', '已确认', '已完成', '差评');
INSERT INTO `orders` VALUES ('2', '魔云连洲', '刘彦', '19877658766', '金易强', '2021-12-30', '16:00-18:00', '25.00', '洗剪吹', '希望剪快一点', '已确认', '已完成', '一般');
INSERT INTO `orders` VALUES ('3', '我不吃饭', '刘陈凯歌', '3432423', '李志刚', '2021-12-31', '10:00-12:00', '180.00', '造型', '想要很好的服务', '已确认', '未完成', '好评');
INSERT INTO `orders` VALUES ('31', '唐朝的宇宙', '刘岩柏', '18711965665', '赵宇翔', '2021-12-30', '10:00-12:00', '180.00', '造型', '便宜一点', '已确认', '未完成', '好评');
INSERT INTO `orders` VALUES ('34', '魔云连洲', '刘诚可', '18711965665', '李志刚', '2021-12-31', '8:00-10:00', '500.00', '烫染', '希望给我设计一个漂亮的发型', '未确认', '未完成', '好评');
INSERT INTO `orders` VALUES ('36', '魔云连洲', '刘少', '16577897654', '赵宇翔', '2021-12-31', '10:00-12:00', '180.00', '造型', '希望小姐姐给我服务！！！', '未确认', '未完成', '好评');
INSERT INTO `orders` VALUES ('60', '魔云连洲', 'ijij', '234234234234', '李志刚', '2021-12-31', '8:00-10:00', '45.00', '洗剪吹', 'sdfaadsfsad', '未确认', '未完成', null);

-- ----------------------------
-- Table structure for pricelist
-- ----------------------------
DROP TABLE IF EXISTS `pricelist`;
CREATE TABLE `pricelist` (
  `pricelist_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '价目id',
  `pricelist_way` varchar(255) DEFAULT NULL COMMENT '服务方式',
  `pricelist_level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '服务人身份级别',
  `pricelist_price` float(10,2) DEFAULT NULL COMMENT '服务价格',
  PRIMARY KEY (`pricelist_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of pricelist
-- ----------------------------
INSERT INTO `pricelist` VALUES ('1', '洗剪吹', '店长', '45.00');
INSERT INTO `pricelist` VALUES ('2', '烫发', '店长', '300.00');
INSERT INTO `pricelist` VALUES ('3', '烫染', '店长', '500.00');
INSERT INTO `pricelist` VALUES ('4', '造型', '店长', '200.00');
INSERT INTO `pricelist` VALUES ('5', '洗剪吹', '顾问', '35.00');
INSERT INTO `pricelist` VALUES ('6', '烫发', '顾问', '280.00');
INSERT INTO `pricelist` VALUES ('7', '烫染', '顾问', '480.00');
INSERT INTO `pricelist` VALUES ('8', '造型', '顾问', '180.00');
INSERT INTO `pricelist` VALUES ('9', '洗剪吹', '精英', '25.00');
INSERT INTO `pricelist` VALUES ('10', '烫发', '精英', '250.00');
INSERT INTO `pricelist` VALUES ('11', '烫染', '精英', '450.00');
INSERT INTO `pricelist` VALUES ('12', '造型', '精英', '150.00');

-- ----------------------------
-- Table structure for projects
-- ----------------------------
DROP TABLE IF EXISTS `projects`;
CREATE TABLE `projects` (
  `project_id` int(11) NOT NULL AUTO_INCREMENT,
  `project_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of projects
-- ----------------------------
INSERT INTO `projects` VALUES ('1', '洗剪吹');
INSERT INTO `projects` VALUES ('2', '烫发');
INSERT INTO `projects` VALUES ('3', '烫染');
INSERT INTO `projects` VALUES ('4', '造型');

-- ----------------------------
-- Table structure for publicity
-- ----------------------------
DROP TABLE IF EXISTS `publicity`;
CREATE TABLE `publicity` (
  `publicity_id` int(11) NOT NULL AUTO_INCREMENT,
  `publicity_name` varchar(255) DEFAULT NULL,
  `publicity_date` date DEFAULT NULL,
  `publicity_content` text,
  PRIMARY KEY (`publicity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of publicity
-- ----------------------------
INSERT INTO `publicity` VALUES ('1', '开学大优惠', '2021-12-06', '凡是新生可凭借学生卡免费洗剪吹一次，而且新生充值会员多送20。');
INSERT INTO `publicity` VALUES ('2', '老客户回馈', '2021-12-07', '凡是在本店销售累计消费超过300元以上的会员用户可以在本店进行免费洗剪吹一次。');
INSERT INTO `publicity` VALUES ('3', '元旦节活动', '2021-12-29', '为喜迎元旦，本店决定，凡是在元旦当天进店的用户可以免费领取一张满300减50的优惠券，永久有效！');

-- ----------------------------
-- Table structure for sales
-- ----------------------------
DROP TABLE IF EXISTS `sales`;
CREATE TABLE `sales` (
  `sale_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '销售情况id',
  `sale_date` date DEFAULT NULL COMMENT '销售日期',
  `sale_price` float(10,2) DEFAULT NULL COMMENT '销售金额',
  `sale_orders` int(11) DEFAULT NULL COMMENT '销售的订单数',
  PRIMARY KEY (`sale_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of sales
-- ----------------------------
INSERT INTO `sales` VALUES ('1', '2021-12-06', '3450.00', '18');
INSERT INTO `sales` VALUES ('2', '2021-12-07', '3690.00', '24');
INSERT INTO `sales` VALUES ('3', '2021-12-08', '4500.00', '37');
INSERT INTO `sales` VALUES ('4', '2021-12-09', '3800.00', '32');
INSERT INTO `sales` VALUES ('5', '2021-12-10', '4800.00', '30');
INSERT INTO `sales` VALUES ('6', '2021-12-11', '5050.00', '40');
INSERT INTO `sales` VALUES ('7', '2021-12-12', '4500.00', '34');

-- ----------------------------
-- Table structure for servers
-- ----------------------------
DROP TABLE IF EXISTS `servers`;
CREATE TABLE `servers` (
  `server_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '服务人员id',
  `server_name` varchar(255) DEFAULT NULL COMMENT '服务人员姓名',
  `server_age` varchar(255) DEFAULT NULL COMMENT '服务人员年龄',
  `server_phone` varchar(255) DEFAULT '' COMMENT '服务人员电话',
  `server_salary` int(11) DEFAULT NULL COMMENT '服务人员薪水',
  PRIMARY KEY (`server_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of servers
-- ----------------------------
INSERT INTO `servers` VALUES ('1', '李玉', '20', '16544567789', '5000');
INSERT INTO `servers` VALUES ('2', '张言', '22', '16999877878', '5000');

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
  `store_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '美发店id',
  `store_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '美发店位置',
  `store_level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '美发店水平',
  `store_introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '美发店介绍',
  PRIMARY KEY (`store_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` VALUES ('1', '湖南省长沙市五一广场100号', '高端', '一个高端，性价比极高的美发连锁店，系统集成商的下属产业');
INSERT INTO `store` VALUES ('2', '湖南省长沙市岳麓区裕园小区A2栋', '中端', '位于湖南中医药大学小吃街附近的一家中等服务理发店');

-- ----------------------------
-- Table structure for timeslotcount
-- ----------------------------
DROP TABLE IF EXISTS `timeslotcount`;
CREATE TABLE `timeslotcount` (
  `the_date` date NOT NULL COMMENT '日期',
  `time_slot1_count` int(11) DEFAULT NULL COMMENT '时间段1的预约人数',
  `time_slot2_count` int(11) DEFAULT NULL,
  `time_slot3_count` int(11) DEFAULT NULL,
  `time_slot4_count` int(11) DEFAULT NULL,
  `time_slot5_count` int(11) DEFAULT NULL,
  `max_count` int(11) DEFAULT NULL COMMENT '最大预约数量'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of timeslotcount
-- ----------------------------
INSERT INTO `timeslotcount` VALUES ('2021-12-06', '5', '4', '9', '6', '3', '9');
INSERT INTO `timeslotcount` VALUES ('2021-12-07', '5', '5', '7', '9', '4', '9');
INSERT INTO `timeslotcount` VALUES ('2021-12-08', '4', '7', '7', '8', '2', '9');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户的电话号码，作为用户id',
  `nick_name` varchar(255) DEFAULT NULL,
  `header_url` varchar(255) DEFAULT NULL,
  `user_category` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '非会员' COMMENT '用户是否为会员',
  `user_balance` float(10,2) DEFAULT '0.00' COMMENT '用户余额',
  `user_score` int(11) DEFAULT '0' COMMENT '用户积分',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('82', '魔云连洲', 'https://thirdwx.qlogo.cn/mmopen/vi_32/BXMZCnSI4ibtuRSxbtSsmdXRsTaGNdmPFPxVXwrxGokr595icVrIsHuntbNz1S1Gy6qbzdmCpMFKtpwgpuvWpjVQ/132', '会员', '736.00', '0');
