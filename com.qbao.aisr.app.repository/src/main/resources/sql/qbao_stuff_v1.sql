/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.14.107
 Source Server Type    : MySQL
 Source Server Version : 50614
 Source Host           : 192.168.14.107
 Source Database       : qbao_stuff

 Target Server Type    : MySQL
 Target Server Version : 50614
 File Encoding         : utf-8

 Date: 03/29/2017 10:54:29 AM
*/


CREATE DATABASE `qbao_stuff` ;

USE `qbao_stuff`;

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `ad_banner`
-- ----------------------------
DROP TABLE IF EXISTS `ad_banner`;
CREATE TABLE `ad_banner` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `img_url` varchar(512) NOT NULL COMMENT '图片地址',
  `link_url` varchar(255) NOT NULL COMMENT '点击url',
  `status` int(11) NOT NULL COMMENT '状态',
  `on_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上架时间',
  `off_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下架时间',
  `module_id` int(11) NOT NULL COMMENT 'stuff_module 表 id',
  `ad_location_id` int(255) NOT NULL COMMENT 'ad_location 表 type = 0 的id banner 位置id',
  `name` varchar(255) NOT NULL COMMENT 'banner 名称',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `advertiser` varchar(255) DEFAULT NULL COMMENT '广告主',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='广告banner表';

-- ----------------------------
--  Table structure for `ad_ju`
-- ----------------------------
DROP TABLE IF EXISTS `ad_ju`;
CREATE TABLE `ad_ju` (
  `ju_id` bigint(20) NOT NULL COMMENT 'stuff_ju 表的id',
  `ad_location_id` int(255) NOT NULL COMMENT 'ad_location_style_conf 表 type =1 的id',
  `status` int(11) NOT NULL COMMENT '广告位状态 商品状态 0:审核,1:上架,2下架',
  `on_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '广告上架开始时间',
  `off_time` datetime NOT NULL COMMENT '广告位下架时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `advertiser` varchar(255) DEFAULT NULL COMMENT '广告主',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ju_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='聚好货广告位表';

-- ----------------------------
--  Table structure for `ad_location_style_conf`
-- ----------------------------
DROP TABLE IF EXISTS `ad_location_style_conf`;
CREATE TABLE `ad_location_style_conf` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `module_id` int(11) DEFAULT NULL COMMENT 'stuff_module 的 id',
  `level` int(11) DEFAULT '1' COMMENT '模块的坑位级别 1 是好货主页, 2 是2级详细页面首页',
  `style_id` int(11) DEFAULT NULL COMMENT 'stuff_module 的id',
  `location_index` int(11) DEFAULT NULL COMMENT '定位索引',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `size` varchar(255) NOT NULL COMMENT '广告为尺寸大小',
  `count` int(11) DEFAULT '1' COMMENT '广告坑位可以同时投放广告数',
  `type` int(11) NOT NULL DEFAULT '1' COMMENT '坑位类型: 0 banner , 1 商品 , 2 店铺',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='广告位样式配置表';

-- ----------------------------
--  Table structure for `ad_qbao`
-- ----------------------------
DROP TABLE IF EXISTS `ad_qbao`;
CREATE TABLE `ad_qbao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `img_url` varchar(512) NOT NULL COMMENT '图片链接',
  `link_url` varchar(255) NOT NULL COMMENT '点击链接',
  `status` int(11) NOT NULL COMMENT '状态',
  `on_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上线时间',
  `off_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下架时间',
  `module_id` int(11) NOT NULL COMMENT 'stuff_module 表 id',
  `ad_location_id` int(255) NOT NULL COMMENT 'ad_location 表 type = 0 的id banner 位置id',
  `name` varchar(255) NOT NULL COMMENT 'banner 名称',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `advertiser` varchar(255) DEFAULT NULL COMMENT '广告主',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='钱宝自营广告位表';

-- ----------------------------
--  Table structure for `ad_shop`
-- ----------------------------
DROP TABLE IF EXISTS `ad_shop`;
CREATE TABLE `ad_shop` (
  `shop_id` bigint(20) NOT NULL COMMENT 'shop_promotion 表的id',
  `shop_name` varchar(255) DEFAULT NULL COMMENT 'shop_promotion 表的name',
  `source` varchar(255) DEFAULT '' COMMENT '店铺的来源 taobao,tmall,jd,qbao',
  `ad_location_id` int(255) NOT NULL COMMENT 'ad_location 表 type = 2 的id banner 位置id',
  `status` int(11) NOT NULL COMMENT '广告位状态 商品状态 0:审核,1:上架,2下架',
  `on_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '广告上架开始时间',
  `off_time` datetime NOT NULL COMMENT '广告位下架时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `advertiser` varchar(255) DEFAULT NULL COMMENT '广告主',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='聚好店广告位表';

-- ----------------------------
--  Table structure for `ad_stuff`
-- ----------------------------
DROP TABLE IF EXISTS `ad_stuff`;
CREATE TABLE `ad_stuff` (
  `stuff_id` bigint(20) NOT NULL COMMENT 'stuff_promotion 表的id',
  `source` varchar(255) DEFAULT NULL COMMENT '商品来源 taobao, jd ,tmall, qbao',
  `ad_location_id` int(255) NOT NULL COMMENT 'ad_location_style_conf 表 type =1 的id',
  `status` int(11) NOT NULL COMMENT '广告位状态 商品状态 0:审核,1:上架,2下架',
  `on_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '广告上架开始时间',
  `off_time` datetime NOT NULL COMMENT '广告位下架时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `advertiser` varchar(255) DEFAULT NULL COMMENT '广告主',
  `memo` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`stuff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品广告位表';

-- ----------------------------
--  Table structure for `dict_keyword_dirids`
-- ----------------------------
DROP TABLE IF EXISTS `dict_keyword_dirids`;
CREATE TABLE `dict_keyword_dirids` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `keyword` varchar(30) NOT NULL COMMENT '关键词',
  `dirids` text COMMENT '目录ids',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='关键词类目映射表';

-- ----------------------------
--  Table structure for `qbaozy_class`
-- ----------------------------
DROP TABLE IF EXISTS `qbaozy_class`;
CREATE TABLE `qbaozy_class` (
  `id` bigint(20) DEFAULT NULL COMMENT '主键id',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `dir_name` varchar(150) DEFAULT NULL COMMENT '目录名称',
  `url` varchar(900) DEFAULT NULL COMMENT '内部接口url',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='钱宝自营分类表';

-- ----------------------------
--  Table structure for `rec_cloud_stuff`
-- ----------------------------
DROP TABLE IF EXISTS `rec_cloud_stuff`;
CREATE TABLE `rec_cloud_stuff` (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `stuff_ids` text NOT NULL COMMENT '推荐结果',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='云好货推荐表';

-- ----------------------------
--  Table structure for `rec_search_stuff`
-- ----------------------------
DROP TABLE IF EXISTS `rec_search_stuff`;
CREATE TABLE `rec_search_stuff` (
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `stuff_ids` text NOT NULL COMMENT '推荐结果',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='搜索推荐表';

-- ----------------------------
--  Table structure for `rec_user_stuff`
-- ----------------------------
DROP TABLE IF EXISTS `rec_user_stuff`;
CREATE TABLE `rec_user_stuff` (
  `user_id` bigint(20) NOT NULL,
  `stuff_ids` text NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户推荐表';

-- ----------------------------
--  Table structure for `shop_category`
-- ----------------------------
DROP TABLE IF EXISTS `shop_category`;
CREATE TABLE `shop_category` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `p_id` int(11) NOT NULL COMMENT '父分类ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商店分类表';

-- ----------------------------
--  Table structure for `shop_promotion`
-- ----------------------------
DROP TABLE IF EXISTS `shop_promotion`;
CREATE TABLE `shop_promotion` (
  `id` bigint(20) NOT NULL COMMENT '统一店铺id',
  `real_shop_id` bigint(20) NOT NULL COMMENT '实际店铺id',
  `name` varchar(255) DEFAULT NULL COMMENT '店铺名称',
  `url` varchar(255) DEFAULT NULL COMMENT '店铺链接',
  `cover_url` varchar(512) DEFAULT NULL COMMENT '店铺封面图片',
  `logo_url` varchar(255) DEFAULT NULL COMMENT '店铺logo图片链接',
  `category_id` int(11) DEFAULT NULL COMMENT '店铺类目id',
  `status` int(11) DEFAULT NULL COMMENT '店铺状态 0:审核,1:正常,2关闭',
  `source` varchar(255) DEFAULT NULL COMMENT '店铺来源:taobao,tmall,jd',
  `order_num` int(11) DEFAULT NULL COMMENT '推广销量',
  `click_num` int(11) DEFAULT NULL COMMENT '点击量',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺表';

-- ----------------------------
--  Table structure for `stuff_category`
-- ----------------------------
DROP TABLE IF EXISTS `stuff_category`;
CREATE TABLE `stuff_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `cat_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '目录id 例：一级目录id:101100100100，二级目录id:101101100100',
  `pid` varchar(12) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '父id',
  `main_cat_id` varchar(12) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '该商品所属的主目录id，例：iphone6所属的主目录就是手机',
  `cat_name` varchar(35) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '目录名',
  `lev` tinyint(2) NOT NULL COMMENT '目录级别：0 - root目录，1 -  一级目录，其余依此类推',
  `icon` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '目录图标',
  `sort` smallint(3) DEFAULT '-1' COMMENT '目录排序：-1表示不排序',
  `valid_flag` tinyint(1) DEFAULT '1' COMMENT '目录是否为有效目录：0 - 无效, 1 - 有效',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `cat_id_uidx` (`cat_id`) USING BTREE COMMENT '目录id唯一索引',
  KEY `cat_sort_idx` (`sort`) USING BTREE COMMENT '目录表排序字段索引',
  KEY `catname_lev_uidx` (`cat_name`,`lev`) USING BTREE COMMENT '目录名称+级别的唯一索引'
) ENGINE=InnoDB AUTO_INCREMENT=22129 DEFAULT CHARSET=utf8 COMMENT='商品分类表';

-- ----------------------------
--  Table structure for `stuff_headline`
-- ----------------------------
DROP TABLE IF EXISTS `stuff_headline`;
CREATE TABLE `stuff_headline` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `msg` text NOT NULL COMMENT '好货头条',
  `link_url` varchar(255) NOT NULL COMMENT '头条详细页面',
  `status` bit(1) NOT NULL COMMENT '  头条状态 0 : 下架 1: 上架',
  `on_time` datetime NOT NULL COMMENT '上架时间',
  `off_time` datetime NOT NULL COMMENT '下架时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='好货头条表';

-- ----------------------------
--  Table structure for `stuff_hot_class`
-- ----------------------------
DROP TABLE IF EXISTS `stuff_hot_class`;
CREATE TABLE `stuff_hot_class` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT '主键id',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `dir_name` varchar(150) DEFAULT NULL COMMENT '目录名称',
  `url` varchar(900) DEFAULT NULL COMMENT '内部接口url',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='热卖好货分类表';

-- ----------------------------
--  Table structure for `stuff_ju`
-- ----------------------------
DROP TABLE IF EXISTS `stuff_ju`;
CREATE TABLE `stuff_ju` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `cat_id` varchar(255) NOT NULL COMMENT 'stuff_category 的商品类目cat_id',
  `name` varchar(255) NOT NULL COMMENT '聚好货名称',
  `img_url` varchar(512) NOT NULL COMMENT '图片链接',
  `link_url` varchar(512) NOT NULL COMMENT '跳转链接',
  `status` int(11) DEFAULT '0' COMMENT '状态 0:审核,1:上架,2下架',
  `on_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '专场开始时间',
  `off_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '专场结束时间',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='聚好货表';

-- ----------------------------
--  Table structure for `stuff_menu`
-- ----------------------------
DROP TABLE IF EXISTS `stuff_menu`;
CREATE TABLE `stuff_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) NOT NULL COMMENT '名称',
  `status` int(11) DEFAULT NULL COMMENT '上下架状态 0 下架 1 上架',
  `link_url` varchar(255) DEFAULT NULL COMMENT '跳转到页面',
  `icon_url` varchar(255) NOT NULL COMMENT '图片url',
  `display_order` int(11) DEFAULT NULL COMMENT '菜单顺序',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
--  Table structure for `stuff_module`
-- ----------------------------
DROP TABLE IF EXISTS `stuff_module`;
CREATE TABLE `stuff_module` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `style_id` int(11) NOT NULL COMMENT '模块样式id',
  `status` int(11) DEFAULT NULL COMMENT '上下架状态 0 下架 1 上架',
  `more` varchar(255) DEFAULT NULL COMMENT '更多 页面',
  `title_icon` varchar(512) NOT NULL COMMENT '图标url',
  `display_order` int(11) DEFAULT NULL COMMENT '菜单顺序',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `mark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='好货模块表';

-- ----------------------------
--  Table structure for `stuff_pay_flow`
-- ----------------------------
DROP TABLE IF EXISTS `stuff_pay_flow`;
CREATE TABLE `stuff_pay_flow` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `USER_ID` int(11) NOT NULL DEFAULT '0' COMMENT 'uid',
  `AMT` int(11) NOT NULL DEFAULT '0' COMMENT '支付金额（宝券）',
  `CREATE_TIME` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '支付时间',
  `pay_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '支付状态 0-未支付,1-支付成功，2-支付失败',
  `fail_msg` varchar(500) DEFAULT '' COMMENT '支付失败原因',
  `pay_trade_no` varchar(32) DEFAULT '' COMMENT '支付返回code',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='返宝券流水表';

-- ----------------------------
--  Table structure for `stuff_promotion`
-- ----------------------------
DROP TABLE IF EXISTS `stuff_promotion`;
CREATE TABLE `stuff_promotion` (
  `id` bigint(20) NOT NULL COMMENT '统一商品id',
  `real_stuff_id` bigint(20) NOT NULL COMMENT '实际商品id',
  `name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `reserve_price` decimal(10,2) DEFAULT NULL COMMENT '原价',
  `final_price` decimal(10,2) DEFAULT NULL COMMENT '商品最终价格',
  `rebate_id` int(20) DEFAULT NULL COMMENT '返利类型 rebate 表 id',
  `promotion_rate` int(11) DEFAULT NULL COMMENT '推广佣金比 1234即12.34%.34',
  `promotion_url` varchar(255) DEFAULT NULL COMMENT '推广链接',
  `url` varchar(255) DEFAULT NULL COMMENT '商品链接',
  `img_url` varchar(512) DEFAULT NULL COMMENT '商品图片链接',
  `cat_id` varchar(12) DEFAULT NULL COMMENT 'stuff_category 表 的 商品类目cat_id',
  `status` int(11) DEFAULT NULL COMMENT '商品状态 0:审核,1:上架,2下架',
  `source` varchar(255) DEFAULT NULL COMMENT '商品来源:taobao,tmall,jd',
  `shop_id` bigint(20) DEFAULT NULL COMMENT '店铺id',
  `shop_name` varchar(255) DEFAULT NULL COMMENT '商家名称',
  `order_num` int(11) DEFAULT NULL COMMENT '推广销量',
  `click_num` int(11) DEFAULT NULL COMMENT '点击量',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `real_stuff_id_source` (`real_stuff_id`,`source`),
  KEY `shop_id` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
--  Table structure for `stuff_rebate`
-- ----------------------------
DROP TABLE IF EXISTS `stuff_rebate`;
CREATE TABLE `stuff_rebate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) NOT NULL COMMENT '返利名称',
  `value` decimal(10,2) DEFAULT NULL COMMENT '返利多少',
  `is_absolute` bit(1) NOT NULL COMMENT '返利方式是百分比还是绝对返回 0 : 百分比, 1: 绝对值',
  `remark` varchar(255) DEFAULT NULL COMMENT '标注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='返券规则表';

-- ----------------------------
--  Table structure for `summary_order`
-- ----------------------------
DROP TABLE IF EXISTS `summary_order`;
CREATE TABLE `summary_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `total_stuff_item_amount` int(11) NOT NULL COMMENT '销售商品总数',
  `total_sales_amount` bigint(20) NOT NULL COMMENT '销售总金额',
  `total_buyer_amount` int(11) NOT NULL COMMENT '用户购买数',
  `total_orders_amount` int(11) NOT NULL COMMENT '订单总数',
  `dt` date NOT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单统计表';

-- ----------------------------
--  Table structure for `summary_shop`
-- ----------------------------
DROP TABLE IF EXISTS `summary_shop`;
CREATE TABLE `summary_shop` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `shop_id` bigint(20) NOT NULL COMMENT '店铺id',
  `total_views_amount` int(11) NOT NULL COMMENT '商品浏览量',
  `total_sales_amount` bigint(20) NOT NULL COMMENT '销售总金额',
  `total_buyer_amount` int(11) NOT NULL COMMENT '用户购买数',
  `ave_order_price` int(11) NOT NULL COMMENT '平均订单单价',
  `total_orders_amount` int(11) NOT NULL COMMENT '订单总数',
  `dt` date NOT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺相关统计表';

-- ----------------------------
--  Table structure for `summary_stuff`
-- ----------------------------
DROP TABLE IF EXISTS `summary_stuff`;
CREATE TABLE `summary_stuff` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `stuff_id` bigint(20) NOT NULL COMMENT '商品表 stuff_promotion id',
  `total_views_amount` int(11) NOT NULL COMMENT '商品浏览量',
  `total_sales_amount` bigint(20) NOT NULL COMMENT '销售总金额',
  `total_buyer_amount` int(11) NOT NULL COMMENT '用户购买数',
  `total_orders_amount` int(11) NOT NULL COMMENT '订单总数',
  `dt` date NOT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品相关统计表';

-- ----------------------------
--  Table structure for `summary_stuff_category`
-- ----------------------------
DROP TABLE IF EXISTS `summary_stuff_category`;
CREATE TABLE `summary_stuff_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `cat_id` int(11) NOT NULL COMMENT 'stuff_category 表 cat_id',
  `total_view_amount` int(11) NOT NULL COMMENT '浏览量',
  `total_sales_amount` bigint(20) NOT NULL COMMENT '销售总金额',
  `total_buyer_amount` int(11) NOT NULL COMMENT '用户购买数',
  `ave_order_price` int(11) NOT NULL COMMENT '平均订单单价',
  `total_orders_amount` int(11) NOT NULL COMMENT '订单总数',
  `dt` date NOT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品类目相关统计表';

-- ----------------------------
--  Table structure for `summary_user`
-- ----------------------------
DROP TABLE IF EXISTS `summary_user`;
CREATE TABLE `summary_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `total_active` int(11) NOT NULL COMMENT '用户活跃数',
  `total_login` int(20) NOT NULL COMMENT '登录数',
  `total_register` int(11) NOT NULL COMMENT '用户注册数',
  `total_re_order` int(11) NOT NULL COMMENT '重复购买数',
  `total_view_cvr` double DEFAULT NULL COMMENT '浏览之后转化率',
  `total_register_cvr` double DEFAULT NULL COMMENT '注册转化率',
  `total_leave` bigint(20) DEFAULT NULL COMMENT '用户流失数',
  `dt` date NOT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户相关统计表';

-- ----------------------------
--  Table structure for `tag_detail`
-- ----------------------------
DROP TABLE IF EXISTS `tag_detail`;
CREATE TABLE `tag_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标签id',
  `name` varchar(255) NOT NULL COMMENT '标签名称',
  `icon` varchar(255) DEFAULT '' COMMENT '图标url',
  `count` bigint(20) DEFAULT NULL COMMENT '标签总人数',
  `tag_type_id` int(11) NOT NULL COMMENT '标签类型id,与 tag_type id 对应',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8 COMMENT='标签详情表';

-- ----------------------------
--  Table structure for `tag_type`
-- ----------------------------
DROP TABLE IF EXISTS `tag_type`;
CREATE TABLE `tag_type` (
  `id` bigint(20) NOT NULL COMMENT '标签类型id',
  `type` varchar(255) NOT NULL COMMENT '标签类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标签类型表';

-- ----------------------------
--  Table structure for `taoke_detail`
-- ----------------------------
DROP TABLE IF EXISTS `taoke_detail`;
CREATE TABLE `taoke_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `order_time` datetime NOT NULL COMMENT '订单创建时间',
  `click_time` datetime NOT NULL COMMENT '点击时间',
  `real_stuff_id` bigint(20) DEFAULT NULL COMMENT '真正商品id',
  `stuff_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `wangwang` varchar(255) DEFAULT NULL COMMENT '掌柜旺旺',
  `shop_name` varchar(255) DEFAULT NULL COMMENT '所属店铺',
  `stuff_num` int(11) DEFAULT NULL COMMENT '商品数',
  `price` decimal(10,2) DEFAULT NULL COMMENT '商品单价',
  `status` int(11) DEFAULT NULL COMMENT '订单状态 1:订单付款 2:订单成功 3:订单失效 4:订单结算',
  `order_status` varchar(255) DEFAULT NULL COMMENT '订单状态',
  `order_type` varchar(255) DEFAULT NULL COMMENT '订单类型',
  `income_rate` double DEFAULT NULL COMMENT '收入比率',
  `sharing_rate` double DEFAULT NULL COMMENT '分成比率',
  `commission_rate` double DEFAULT NULL COMMENT '佣金比率',
  `subsidy_rate` double DEFAULT NULL COMMENT '补贴比率',
  `subsidy_type` varchar(255) DEFAULT NULL COMMENT '补贴类型',
  `pay_value` decimal(10,2) DEFAULT NULL COMMENT '付款金额',
  `estimate_value` decimal(10,2) DEFAULT NULL COMMENT '预估价格',
  `settlement_value` decimal(10,2) DEFAULT NULL COMMENT '结算价格',
  `commission_value` decimal(10,2) DEFAULT NULL COMMENT '佣金金额',
  `platform` varchar(255) DEFAULT NULL COMMENT '成交平台',
  `third_party_serivce` varchar(255) DEFAULT NULL COMMENT '第三方服务',
  `order_id` bigint(20) NOT NULL COMMENT '订单编号',
  `category_name` varchar(255) DEFAULT NULL COMMENT '类目名称',
  `source_id` bigint(11) DEFAULT NULL COMMENT '来源媒体id',
  `media_name` varchar(255) DEFAULT NULL COMMENT '来源媒体名称',
  `source_name` varchar(255) DEFAULT NULL COMMENT '产品来源',
  `adv_id` bigint(11) DEFAULT NULL COMMENT '广告位ID',
  `adv_name` varchar(255) DEFAULT NULL COMMENT '广告位名称',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `img_url` varchar(512) DEFAULT '' COMMENT '商品图片地址',
  `click_url` varchar(512) DEFAULT '' COMMENT '商品详细页面',
  `rebate_value` bigint(20) DEFAULT NULL COMMENT '返券数量',
  `name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `CLICK_TIME_ORDER_ID_INDEX` (`click_time`,`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='淘客订单详情表';

-- ----------------------------
--  Table structure for `top_click_shop`
-- ----------------------------
DROP TABLE IF EXISTS `top_click_shop`;
CREATE TABLE `top_click_shop` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `stuff_id` int(11) NOT NULL COMMENT 'stuff_promotion 表id',
  `count` int(11) DEFAULT NULL COMMENT '统计数',
  `create_time` date DEFAULT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商铺点击排行表';

-- ----------------------------
--  Table structure for `top_click_stuff`
-- ----------------------------
DROP TABLE IF EXISTS `top_click_stuff`;
CREATE TABLE `top_click_stuff` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `stuff_id` int(11) NOT NULL COMMENT 'stuff_promotion 表id',
  `count` int(11) DEFAULT NULL COMMENT '统计数',
  `create_time` date DEFAULT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品点击排行表';

-- ----------------------------
--  Table structure for `top_goods_stuff_pai_img`
-- ----------------------------
DROP TABLE IF EXISTS `top_goods_stuff_pai_img`;
CREATE TABLE `top_goods_stuff_pai_img` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `path` varchar(255) NOT NULL COMMENT '图片路径',
  `key` varchar(255) DEFAULT NULL COMMENT '图片对应的关键字',
  `count` int(11) DEFAULT NULL COMMENT '统计数',
  `create_time` date DEFAULT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='拍立购图片排行表';

-- ----------------------------
--  Table structure for `top_pai_img`
-- ----------------------------
DROP TABLE IF EXISTS `top_pai_img`;
CREATE TABLE `top_pai_img` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `img_url` varchar(255) NOT NULL COMMENT '图片路径',
  `key` varchar(255) DEFAULT NULL COMMENT '图片对应的关键字',
  `count` int(11) DEFAULT NULL COMMENT '统计数',
  `create_time` date DEFAULT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='拍立购图片排行表';

-- ----------------------------
--  Table structure for `top_search_key`
-- ----------------------------
DROP TABLE IF EXISTS `top_search_key`;
CREATE TABLE `top_search_key` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `key` varchar(255) NOT NULL COMMENT '搜索关键字',
  `url` varchar(255) DEFAULT NULL COMMENT 'url 为null或者"" : 直接搜索关键(原生态)  url不为null或者"" 跳转到活动页(H5页面)',
  `count` int(11) DEFAULT NULL COMMENT '统计数',
  `create_time` date DEFAULT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5000 DEFAULT CHARSET=utf8 COMMENT='搜索关键词排行表';

-- ----------------------------
--  Table structure for `top_search_stuff`
-- ----------------------------
DROP TABLE IF EXISTS `top_search_stuff`;
CREATE TABLE `top_search_stuff` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `stuff_id` bigint(20) NOT NULL COMMENT 'stuff_promotion 的id',
  `count` int(11) DEFAULT NULL COMMENT '统计数',
  `create_time` date DEFAULT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5000 DEFAULT CHARSET=utf8 COMMENT='商品搜索次数排行表';

-- ----------------------------
--  Table structure for `user_dislike_stuff`
-- ----------------------------
DROP TABLE IF EXISTS `user_dislike_stuff`;
CREATE TABLE `user_dislike_stuff` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `stuff_ids` text NOT NULL COMMENT '商品id拼接字符串',
  `create_time` datetime NOT NULL COMMENT '创建日期',
  `update_time` datetime NOT NULL COMMENT '修改日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户不喜欢商品';

-- ----------------------------
--  Table structure for `user_search`
-- ----------------------------
DROP TABLE IF EXISTS `user_search`;
CREATE TABLE `user_search` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `key` varchar(255) NOT NULL COMMENT '搜索关键字',
  `category_id` varchar(255) DEFAULT '' COMMENT '搜索商品的类目id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `user_id_index` (`user_id`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户搜索关键词表';

-- ----------------------------
--  Table structure for `user_stuff_pai`
-- ----------------------------
DROP TABLE IF EXISTS `user_stuff_pai`;
CREATE TABLE `user_stuff_pai` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) NOT NULL COMMENT '用户Id',
  `img_url` varchar(512) NOT NULL COMMENT '图片路径',
  `key` varchar(255) DEFAULT NULL COMMENT '图片对应的关键字',
  `create_time` date DEFAULT NULL COMMENT '统计日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='我的拍';

-- ----------------------------
--  Table structure for `user_stuff_promotion`
-- ----------------------------
DROP TABLE IF EXISTS `user_stuff_promotion`;
CREATE TABLE `user_stuff_promotion` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `order_id` varchar(100) DEFAULT NULL COMMENT '订单id',
  `source` varchar(255) DEFAULT NULL COMMENT '订单来源  tmall, jd, taobao,qbao',
  `return_coupon_status` int(11) DEFAULT '-1' COMMENT '返宝券状态-1,为订单录入 0: 未返券,1已返券,2返券被收回(以后可能被用到),3返券失败',
  `rebate_value` bigint(20) DEFAULT '0' COMMENT '返券数',
  `order_time` datetime DEFAULT NULL COMMENT '下单时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `status` int(11) DEFAULT '1' COMMENT '逻辑是否删除：0:删除，1:正常',
  `price` decimal(10,2) DEFAULT NULL COMMENT '总价格',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8 COMMENT='用户推广返券订单表';

-- ----------------------------
--  Table structure for `user_tags`
-- ----------------------------
DROP TABLE IF EXISTS `user_tags`;
CREATE TABLE `user_tags` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `tag_type_id` int(11) NOT NULL COMMENT 'tag_type 表的id',
  `tag_detail_ids` varchar(255) NOT NULL COMMENT '标签ids列表,多个值用逗号分割',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `user_id_tag_type_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=212 DEFAULT CHARSET=utf8 COMMENT='用户所属标签表';

SET FOREIGN_KEY_CHECKS = 1;
