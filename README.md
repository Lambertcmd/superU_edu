# 谷粒学苑-在线教育项目

# 一、项目介绍

## 01、商业模式介绍

### 01-1、B2C模式

> 两个角色 B：管理员，C：普通用户

管理员：添加 修改 删除

普通用户：查询 

在线教育项目使用的就是B2C模式

核心模块：课程模块

### 01-2、B2B2C模式

例如京东 有普通用户、普通商家、京东自营

普通用户(B)可以购买普通商家(B)和京东自营(C)的商品

## 02、项目功能模块介绍

### 02-1、b2c模式

b2c模式的项目分为两个系统

- 管理员使用的系统(后台管理系统)
  - 讲师管理模块
  - 课程分类管理模块
  - 课程管理模块
  - 统计与分析模块
  - 订单管理模块
  - banner管理模块（首页轮播图管理）
  - 权限管理模块
- 普通用户使用的系统（前台）
  - 首页数据显示
  - 讲师列表和详情
  - 课程列表和课程详情
    - 视频在线播放
  - 登录和注册功能
  - 微信扫码登录
  - 微信扫码支付

## 03、项目技术栈介绍

> 采用前后端分离开发

- 后端
  - SpringBoot
  - SpringCloud
  - MybatisPlus
  - SpringSecurity
  - Redis
  - Maven
  - esayExcel
  - JWT
  - OAuth2
- 前端
  - vue
  - element-ui
  - axios
  - node.js
- 其他技术
  - 阿里云oss存储服务
  - 阿里云视频点播服务
  - 阿里云短信服务
  - 微信支付和登录
  - docker
  - git
  - jenkins

# 二、项目环境搭建

## 01、创建数据库

1. 新建一个`guli_edu`数据库

2. 新建数据表

   ```sql
   # Host: localhost  (Version 5.7.19)
   # Date: 2019-11-18 15:49:32
   # Generator: MySQL-Front 6.1  (Build 1.26)
   
   
   #
   # Structure for table "edu_chapter"
   #
   
   CREATE TABLE `edu_chapter` (
     `id` char(19) NOT NULL COMMENT '章节ID',
     `course_id` char(19) NOT NULL COMMENT '课程ID',
     `title` varchar(50) NOT NULL COMMENT '章节名称',
     `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '显示排序',
     `gmt_create` datetime NOT NULL COMMENT '创建时间',
     `gmt_modified` datetime NOT NULL COMMENT '更新时间',
     PRIMARY KEY (`id`),
     KEY `idx_course_id` (`course_id`)
   ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='课程';
   
   #
   # Data for table "edu_chapter"
   #
   
   INSERT INTO `edu_chapter` VALUES ('1','14','第一章：HTML',0,'2019-01-01 12:27:40','2019-01-01 12:55:30'),('1181729226915577857','18','第七章：I/O流',70,'2019-10-09 08:32:58','2019-10-09 08:33:20'),('1192252428399751169','1192252213659774977','第一章节',0,'2019-11-07 09:28:25','2019-11-07 09:28:25'),('15','18','第一章：Java入门',0,'2019-01-01 12:27:40','2019-10-09 09:13:19'),('3','14','第二章：CSS',0,'2019-01-01 12:55:35','2019-01-01 12:27:40'),('32','18','第二章：控制台输入和输出',0,'2019-01-01 12:27:40','2019-01-01 12:27:40'),('44','18','第三章：控制流',0,'2019-01-01 12:27:40','2019-01-01 12:27:40'),('48','18','第四章：类的定义',0,'2019-01-01 12:27:40','2019-01-01 12:27:40'),('63','18','第五章：数组',0,'2019-01-01 12:27:40','2019-01-01 12:27:40'),('64','18','第六章：继承',61,'2019-01-01 12:27:40','2019-10-09 08:32:47');
   
   #
   # Structure for table "edu_comment"
   #
   
   CREATE TABLE `edu_comment` (
     `id` char(19) NOT NULL COMMENT '讲师ID',
     `course_id` varchar(19) NOT NULL DEFAULT '' COMMENT '课程id',
     `teacher_id` char(19) NOT NULL DEFAULT '' COMMENT '讲师id',
     `member_id` varchar(19) NOT NULL DEFAULT '' COMMENT '会员id',
     `nickname` varchar(50) DEFAULT NULL COMMENT '会员昵称',
     `avatar` varchar(255) DEFAULT NULL COMMENT '会员头像',
     `content` varchar(500) DEFAULT NULL COMMENT '评论内容',
     `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
     `gmt_create` datetime NOT NULL COMMENT '创建时间',
     `gmt_modified` datetime NOT NULL COMMENT '更新时间',
     PRIMARY KEY (`id`),
     KEY `idx_course_id` (`course_id`),
     KEY `idx_teacher_id` (`teacher_id`),
     KEY `idx_member_id` (`member_id`)
   ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论';
   
   #
   # Data for table "edu_comment"
   #
   
   INSERT INTO `edu_comment` VALUES ('1194499162790211585','1192252213659774977','1189389726308478977','1','小三123','http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132','课程很好',0,'2019-11-13 14:16:08','2019-11-13 14:16:08'),('1194898406466420738','1192252213659774977','1189389726308478977','1','小三123','http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132','11',0,'2019-11-14 16:42:35','2019-11-14 16:42:35'),('1194898484388200450','1192252213659774977','1189389726308478977','1','小三123','http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132','111',0,'2019-11-14 16:42:53','2019-11-14 16:42:53'),('1195251020861317122','1192252213659774977','1189389726308478977','1',NULL,NULL,'2233',0,'2019-11-15 16:03:45','2019-11-15 16:03:45'),('1195251382720700418','1192252213659774977','1189389726308478977','1',NULL,NULL,'4455',0,'2019-11-15 16:05:11','2019-11-15 16:05:11'),('1195252819177570306','1192252213659774977','1189389726308478977','1','小三1231','http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132','55',0,'2019-11-15 16:10:53','2019-11-15 16:10:53'),('1195252899448160258','1192252213659774977','1189389726308478977','1','小三1231','http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132','55',0,'2019-11-15 16:11:13','2019-11-15 16:11:13'),('1195252920587452417','1192252213659774977','1189389726308478977','1','小三1231','http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132','223344',0,'2019-11-15 16:11:18','2019-11-15 16:11:18'),('1195262128095559681','14','1189389726308478977','1','小三1231','http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132','11',0,'2019-11-15 16:47:53','2019-11-15 16:47:53'),('1196264505170767874','1192252213659774977','1189389726308478977','1','小三1231','http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132','666666',0,'2019-11-18 11:10:58','2019-11-18 11:10:58');
   
   #
   # Structure for table "edu_course"
   #
   
   CREATE TABLE `edu_course` (
     `id` char(19) NOT NULL COMMENT '课程ID',
     `teacher_id` char(19) NOT NULL COMMENT '课程讲师ID',
     `subject_id` char(19) NOT NULL COMMENT '课程专业ID',
     `subject_parent_id` char(19) NOT NULL COMMENT '课程专业父级ID',
     `title` varchar(50) NOT NULL COMMENT '课程标题',
     `price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '课程销售价格，设置为0则可免费观看',
     `lesson_num` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '总课时',
     `cover` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '课程封面图片路径',
     `buy_count` bigint(10) unsigned NOT NULL DEFAULT '0' COMMENT '销售数量',
     `view_count` bigint(10) unsigned NOT NULL DEFAULT '0' COMMENT '浏览数量',
     `version` bigint(20) unsigned NOT NULL DEFAULT '1' COMMENT '乐观锁',
     `status` varchar(10) NOT NULL DEFAULT 'Draft' COMMENT '课程状态 Draft未发布  Normal已发布',
     `is_deleted` tinyint(3) DEFAULT NULL COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
     `gmt_create` datetime NOT NULL COMMENT '创建时间',
     `gmt_modified` datetime NOT NULL COMMENT '更新时间',
     PRIMARY KEY (`id`),
     KEY `idx_title` (`title`),
     KEY `idx_subject_id` (`subject_id`),
     KEY `idx_teacher_id` (`teacher_id`)
   ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='课程';
   
   #
   # Data for table "edu_course"
   #
   
   INSERT INTO `edu_course` VALUES ('1192252213659774977','1189389726308478977','1178214681139539969','1178214681118568449','java基础课程：test',0.01,2,'https://guli-file-190513.oss-cn-beijing.aliyuncs.com/cover/default.gif',4,387,1,'Normal',0,'2019-11-07 09:27:33','2019-11-18 13:35:03'),('14','1189389726308478977','1101348944971091969','1101348944920760321','XHTML CSS2 JS整站制作教程课程学习',0.00,3,'http://guli-file.oss-cn-beijing.aliyuncs.com/cover/2019/03/13/d0086eb0-f2dc-45f7-bba1-744d95e5be0f.jpg',3,44,15,'Normal',0,'2018-04-02 18:33:34','2019-11-16 21:21:45'),('15','1189389726308478977','1101348944971091969','1101348944920760321','HTML5入门课程学习',0.00,23,'http://guli-file.oss-cn-beijing.aliyuncs.com/cover/2019/03/13/22997b8e-3606-4d2e-9b4f-09f48418b6e4.jpg',0,51,17,'Normal',0,'2018-04-02 18:34:32','2019-11-12 10:19:20'),('18','1189389726308478977','1178214681139539969','1178214681118568449','Java精品课程',0.01,20,'http://guli-file.oss-cn-beijing.aliyuncs.com/cover/2019/03/06/866e9aca-b530-4f71-a690-72d4a4bfd1e7.jpg',151,737,6,'Normal',0,'2018-04-02 21:28:46','2019-11-18 11:14:52');
   
   #
   # Structure for table "edu_course_collect"
   #
   
   CREATE TABLE `edu_course_collect` (
     `id` char(19) NOT NULL COMMENT '收藏ID',
     `course_id` char(19) NOT NULL COMMENT '课程讲师ID',
     `member_id` char(19) NOT NULL DEFAULT '' COMMENT '课程专业ID',
     `is_deleted` tinyint(3) NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
     `gmt_create` datetime NOT NULL COMMENT '创建时间',
     `gmt_modified` datetime NOT NULL COMMENT '更新时间',
     PRIMARY KEY (`id`)
   ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='课程收藏';
   
   #
   # Data for table "edu_course_collect"
   #
   
   INSERT INTO `edu_course_collect` VALUES ('1196269345666019330','1192252213659774977','1',1,'2019-11-18 11:30:12','2019-11-18 11:30:12');
   
   #
   # Structure for table "edu_course_description"
   #
   
   CREATE TABLE `edu_course_description` (
     `id` char(19) NOT NULL COMMENT '课程ID',
     `description` text COMMENT '课程简介',
     `gmt_create` datetime NOT NULL COMMENT '创建时间',
     `gmt_modified` datetime NOT NULL COMMENT '更新时间',
     PRIMARY KEY (`id`)
   ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程简介';
   
   #
   # Data for table "edu_course_description"
   #
   
   INSERT INTO `edu_course_description` VALUES ('1104870479077879809','<p>11</p>','2019-03-11 06:23:44','2019-03-11 06:23:44'),('1192252213659774977','<p>测试</p>','2019-11-07 09:27:33','2019-11-13 16:21:28'),('14','','2019-03-13 06:04:43','2019-03-13 06:05:33'),('15','','2019-03-13 06:03:33','2019-03-13 06:04:22'),('18','<p>本套Java视频完全针对零基础学员，课堂实录，自发布以来，好评如潮！Java视频中注重与学生互动，讲授幽默诙谐、细致入微，覆盖Java基础所有核心知识点，同类Java视频中也是代码量大、案例多、实战性强的。同时，本Java视频教程注重技术原理剖析，深入JDK源码，辅以代码实战贯穿始终，用实践驱动理论，并辅以必要的代码练习。</p>\n<p>------------------------------------</p>\n<p>视频特点：</p>\n<p>通过学习本Java视频教程，大家能够真正将Java基础知识学以致用、活学活用，构架Java编程思想，牢牢掌握\"源码级\"的Javase核心技术，并为后续JavaWeb等技术的学习奠定扎实基础。<br /><br />1.通俗易懂，细致入微：每个知识点高屋建瓴，深入浅出，简洁明了的说明问题<br />2.具实战性：全程真正代码实战，涵盖上百个企业应用案例及练习<br />3.深入：源码分析，更有 Java 反射、动态代理的实际应用等<br />4.登录尚硅谷官网，技术讲师免费在线答疑</p>','2019-03-06 18:06:36','2019-10-30 19:58:36');
   
   #
   # Structure for table "edu_subject"
   #
   
   CREATE TABLE `edu_subject` (
     `id` char(19) NOT NULL COMMENT '课程类别ID',
     `title` varchar(10) NOT NULL COMMENT '类别名称',
     `parent_id` char(19) NOT NULL DEFAULT '0' COMMENT '父ID',
     `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序字段',
     `gmt_create` datetime NOT NULL COMMENT '创建时间',
     `gmt_modified` datetime NOT NULL COMMENT '更新时间',
     PRIMARY KEY (`id`),
     KEY `idx_parent_id` (`parent_id`)
   ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='课程科目';
   
   #
   # Data for table "edu_subject"
   #
   
   INSERT INTO `edu_subject` VALUES ('1178214681118568449','后端开发','0',1,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681139539969','Java','1178214681118568449',1,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681181483010','前端开发','0',3,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681210843137','JavaScript','1178214681181483010',4,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681231814658','云计算','0',5,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681252786178','Docker','1178214681231814658',5,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681294729217','Linux','1178214681231814658',6,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681324089345','系统/运维','0',7,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681353449473','Linux','1178214681324089345',7,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681382809602','Windows','1178214681324089345',8,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681399586817','数据库','0',9,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681428946945','MySQL','1178214681399586817',9,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681454112770','MongoDB','1178214681399586817',10,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681483472898','大数据','0',11,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681504444418','Hadoop','1178214681483472898',11,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681529610242','Spark','1178214681483472898',12,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681554776066','人工智能','0',13,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681584136193','Python','1178214681554776066',13,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681613496321','编程语言','0',14,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178214681626079234','Java','1178214681613496321',14,'2019-09-29 15:47:25','2019-09-29 15:47:25'),('1178585108407984130','Python','1178214681118568449',2,'2019-09-30 16:19:22','2019-09-30 16:19:22'),('1178585108454121473','HTML/CSS','1178214681181483010',3,'2019-09-30 16:19:22','2019-09-30 16:19:22');
   
   #
   # Structure for table "edu_teacher"
   #
   
   CREATE TABLE `edu_teacher` (
     `id` char(19) NOT NULL COMMENT '讲师ID',
     `name` varchar(20) NOT NULL COMMENT '讲师姓名',
     `intro` varchar(500) NOT NULL DEFAULT '' COMMENT '讲师简介',
     `career` varchar(500) DEFAULT NULL COMMENT '讲师资历,一句话说明讲师',
     `level` int(10) unsigned NOT NULL COMMENT '头衔 1高级讲师 2首席讲师',
     `avatar` varchar(255) DEFAULT NULL COMMENT '讲师头像',
     `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序',
     `is_deleted` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
     `gmt_create` datetime NOT NULL COMMENT '创建时间',
     `gmt_modified` datetime NOT NULL COMMENT '更新时间',
     PRIMARY KEY (`id`),
     UNIQUE KEY `uk_name` (`name`)
   ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='讲师';
   
   #
   # Data for table "edu_teacher"
   #
   
   INSERT INTO `edu_teacher` VALUES ('1','张三','近年主持国家自然科学基金（6项）、江苏省重大科技成果转化项目（5项）、江苏省产学研前瞻性联合研究项目（3项）、省工业科技支撑、省高技术、省自然科学基金等省部级及其企业的主要科研项目40多个，多个项目在企业成功转化，产生了较好的经济、社会和环境效益。积极开展产学研科技合作，并与省内16家企业建立了江苏省研究生工作站，其中6家为江苏省优秀研究生工作站','高级',1,'https://guli-file-190513.oss-cn-beijing.aliyuncs.com/avatar/default.jpg',0,0,'2019-10-30 14:18:46','2019-11-12 13:36:36'),('1189389726308478977','晴天','高级讲师简介','高级讲师资历',2,'https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/10/30/de47ee9b-7fec-43c5-8173-13c5f7f689b2.png',1,0,'2019-10-30 11:53:03','2019-10-30 11:53:03'),('1189390295668469762','李刚','高级讲师简介','高级讲师',2,'https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/10/30/b8aa36a2-db50-4eca-a6e3-cc6e608355e0.png',2,0,'2019-10-30 11:55:19','2019-11-12 13:37:52'),('1189426437876985857','王二','高级讲师简介','高级讲师',1,'https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/11/08/e44a2e92-2421-4ea3-bb49-46f2ec96ef88.png',0,0,'2019-10-30 14:18:56','2019-11-12 13:37:35'),('1189426464967995393','王五','高级讲师简介','高级讲师',1,'https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/10/30/65423f14-49a9-4092-baf5-6d0ef9686a85.png',0,0,'2019-10-30 14:19:02','2019-11-12 13:37:18'),('1192249914833055746','李四','高级讲师简介','高级讲师',1,'https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/11/07/91871e25-fd83-4af6-845f-ea8d471d825d.png',0,0,'2019-11-07 09:18:25','2019-11-12 13:37:01'),('1192327476087115778','1222-12-12','1111','11',1,'https://online-teach-file.oss-cn-beijing.aliyuncs.com/teacher/2019/11/08/5805c6cd-c8ad-4a77-aafd-d2e083bfd8a4.png',0,1,'2019-11-07 14:26:37','2019-11-11 16:26:26'),('1195337453429129218','test','sdfsdf','sdfdf',1,'https://guli-file-190513.oss-cn-beijing.aliyuncs.com/avatar/default.jpg',0,1,'2019-11-15 21:47:12','2019-11-15 21:47:27');
   
   #
   # Structure for table "edu_video"
   #
   
   CREATE TABLE `edu_video` (
     `id` char(19) NOT NULL COMMENT '视频ID',
     `course_id` char(19) NOT NULL COMMENT '课程ID',
     `chapter_id` char(19) NOT NULL COMMENT '章节ID',
     `title` varchar(50) NOT NULL COMMENT '节点名称',
     `video_source_id` varchar(100) DEFAULT NULL COMMENT '云端视频资源',
     `video_original_name` varchar(100) DEFAULT NULL COMMENT '原始文件名称',
     `sort` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '排序字段',
     `play_count` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '播放次数',
     `is_free` tinyint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否可以试听：0收费 1免费',
     `duration` float NOT NULL DEFAULT '0' COMMENT '视频时长（秒）',
     `status` varchar(20) NOT NULL DEFAULT 'Empty' COMMENT 'Empty未上传 Transcoding转码中  Normal正常',
     `size` bigint(20) unsigned NOT NULL DEFAULT '0' COMMENT '视频源文件大小（字节）',
     `version` bigint(20) unsigned NOT NULL DEFAULT '1' COMMENT '乐观锁',
     `gmt_create` datetime NOT NULL COMMENT '创建时间',
     `gmt_modified` datetime NOT NULL COMMENT '更新时间',
     PRIMARY KEY (`id`),
     KEY `idx_course_id` (`course_id`),
     KEY `idx_chapter_id` (`chapter_id`)
   ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='课程视频';
   
   #
   # Data for table "edu_video"
   #
   
   INSERT INTO `edu_video` VALUES ('1182499307429339137','18','32','第一节','','',0,0,0,0,'',0,1,'2019-10-11 11:32:59','2019-10-11 11:57:38'),('1185312444399071234','14','1','12','','',0,0,0,0,'Empty',0,1,'2019-10-19 05:51:23','2019-10-19 05:51:33'),('1189434737808990210','18','44','测试','','',1,0,0,0,'Empty',0,1,'2019-10-30 14:51:55','2019-10-30 14:51:55'),('1189471423678939138','18','1181729226915577857','test','2b887dc9584d4dc68908780ec57cd3b9','视频',1,0,0,0,'Empty',0,1,'2019-10-30 17:17:41','2019-10-30 17:17:41'),('1189476403626409986','18','1181729226915577857','22','5155c73dc112475cbbddccf4723f7cef','视频.mp4',0,0,0,0,'Empty',0,1,'2019-10-30 17:37:29','2019-10-30 17:37:29'),('1192252824606289921','1192252213659774977','1192252428399751169','第一课时','756cf06db9cb4f30be85a9758b19c645','eae2b847ef8503b81f5d5593d769dde2.mp4',0,0,0,0,'Empty',0,1,'2019-11-07 09:29:59','2019-11-07 09:29:59'),('1192628092797730818','1192252213659774977','1192252428399751169','第二课时','2a02d726622f4c7089d44cb993c531e1','eae2b847ef8503b81f5d5593d769dde2.mp4',0,0,1,0,'Empty',0,1,'2019-11-08 10:21:10','2019-11-08 10:21:22'),('1192632495013380097','1192252213659774977','1192252428399751169','第三课时','4e560c892fdf4fa2b42e0671aa42fa9d','eae2b847ef8503b81f5d5593d769dde2.mp4',0,0,1,0,'Empty',0,1,'2019-11-08 10:38:40','2019-11-08 10:38:40'),('1194117638832111617','1192252213659774977','1192252428399751169','第四课时','4e560c892fdf4fa2b42e0671aa42fa9d','eae2b847ef8503b81f5d5593d769dde2.mp4',0,0,0,0,'Empty',0,1,'2019-11-12 13:00:05','2019-11-12 13:00:05'),('1196263770832023554','1192252213659774977','1192252428399751169','第五课时','27d21158b0834cb5a8d50710937de330','eae2b847ef8503b81f5d5593d769dde2.mp4',5,0,0,0,'Empty',0,1,'2019-11-18 11:08:03','2019-11-18 11:08:03'),('17','18','15','第一节：Java简介','196116a6fee742e1ba9f6c18f65bd8c1','1',1,1000,1,100,'Draft',0,1,'2019-01-01 13:08:57','2019-10-11 11:26:39'),('18','18','15','第二节：表达式和赋值语句','2d99b08ca0214909899910c9ba042d47','7 - How Do I Find Time for My ',2,999,1,100,'Draft',0,1,'2019-01-01 13:09:02','2019-03-08 03:30:27'),('19','18','15','第三节：String类','51120d59ddfd424cb5ab08b44fc8b23a','eae2b847ef8503b81f5d5593d769dde2.mp4',3,888,0,100,'Draft',0,1,'2019-01-01 13:09:05','2019-11-12 12:50:45'),('20','18','15','第四节：程序风格','2a38988892d84df598752226c50f3fa3','00-day10总结.avi',4,666,0,100,'Draft',0,1,'2019-01-01 13:09:05','2019-10-11 09:20:09');
   ```

   ![](README.assets/image-20211227140922872.png)

## 02、数据库设计规范

以下规约只针对本模块，更全面的文档参考《阿里巴巴Java开发手册》：五、MySQL数据库

1. 库名与应用名称尽量一致

2. 表名、字段名必须使用小写字母或数字，禁止出现数字开头，

3. 表名不使用复数名词

4. 表的命名最好是加上“业务名称_表的作用”。如，edu_teacher

5. 表必备三字段：id, gmt_create, gmt_modified

   说明：

   其中 id 必为主键，类型为 bigint unsigned、单表时自增、步长为 1。

   （如果使用分库分表集群部署，则id类型为verchar，非自增，业务中使用分布式id生成器）

   gmt_create, gmt_modified 的类型均为 datetime 类型，前者现在时表示主动创建，后者过去分词表示被 动更新。

6. 单表行数超过 500 万行或者单表容量超过 2GB，才推荐进行分库分表。 说明：如果预计三年后的数据量根本达不到这个级别，请不要在创建表时就分库分表。 

7. 表达是与否概念的字段，必须使用 is_xxx 的方式命名，数据类型是 unsigned tinyint （1 表示是，0 表示否）。 

   说明：任何字段如果为非负数，必须是 unsigned。 

   注意：POJO 类中的任何布尔类型的变量，都不要加 is 前缀。数据库表示是与否的值，使用 tinyint 类型，坚持 is_xxx 的 命名方式是为了明确其取值含义与取值范围。 

   正例：表达逻辑删除的字段名 is_deleted，1 表示删除，0 表示未删除。 

8. 小数类型为 decimal，禁止使用 float 和 double。 说明：float 和 double 在存储的时候，存在精度损失的问题，很可能在值的比较时，得到不 正确的结果。如果存储的数据范围超过 decimal 的范围，建议将数据拆成整数和小数分开存储。

9. 如果存储的字符串长度几乎相等，使用 char 定长字符串类型。 

10. varchar 是可变长字符串，不预先分配存储空间，长度不要超过 5000，如果存储长度大于此值，定义字段类型为 text，独立出来一张表，用主键来对应，避免影响其它字段索 引效率。

11. 唯一索引名为 uk_字段名；普通索引名则为 idx_字段名。

    说明：uk_ 即 unique key；idx_ 即 index 的简称

12. 不得使用外键与级联，一切外键概念必须在应用层解决。外键与级联更新适用于单机低并发，不适合分布式、高并发集群；级联更新是强阻塞，存在数据库更新风暴的风险；外键影响数据库的插入速度。 

## 03、项目结构

<img src="README.assets/image-20211227143323524.png" style="zoom: 67%;" />

**guli-parent：在线教学根目录（父工程），管理四个子模块：**

- canal-client：canal数据库表同步模块（统计同步数据）

- common：公共模块父节点

  - common-util：工具类模块，所有模块都可以依赖于它
  - service-base：service服务的base包，包含service服务的公共配置类，所有service模块依赖于它
  - spring-security：认证与授权模块，需要认证授权的service服务依赖于它

- infrastructure：基础服务模块父节点

  api-gateway：api网关服务

- service：api接口服务父节点

  - service-acl：用户权限管理api接口服务（用户管理、角色管理和权限管理等）
  - service-cms：cms api接口服务
  - service-edu：教学相关api接口服务
  - service-msm：短信api接口服务
  - service-order：订单相关api接口服务
  - service-oss：阿里云oss api接口服务
  - service-statistics：统计报表api接口服务
  - service-ucenter：会员api接口服务
  - service-vod：视频点播api接口服务

## 04、搭建项目

### 04-1、pom父工程配置

1. pom.xml改为父工程

   ```xml
   <packaging>pom</packaging>
   ```

   ![](README.assets/image-20211227144936384.png)

2. 删除src文件夹和删除父工程配置

   ```xml
   <parent>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-parent</artifactId>
       <version>2.2.1.RELEASE</version>
       <relativePath/> <!-- lookup parent from repository -->
   </parent>
   ```

3. 添加统一管理jar包依赖版本

   ```xml
   <properties>
       <java.version>1.8</java.version>
       <guli.version>0.0.1-SNAPSHOT</guli.version>
       <mybatis-plus.version>3.0.5</mybatis-plus.version>
       <velocity.version>2.0</velocity.version>
       <swagger.version>3.0.0</swagger.version>
       <aliyun.oss.version>2.8.3</aliyun.oss.version>
       <jodatime.version>2.10.1</jodatime.version>
       <poi.version>3.17</poi.version>
       <commons-fileupload.version>1.3.1</commons-fileupload.version>
       <commons-io.version>2.6</commons-io.version>
       <httpclient.version>4.5.1</httpclient.version>
       <jwt.version>0.7.0</jwt.version>
       <aliyun-java-sdk-core.version>4.3.3</aliyun-java-sdk-core.version>
       <aliyun-sdk-oss.version>3.1.0</aliyun-sdk-oss.version>
       <aliyun-java-sdk-vod.version>2.15.2</aliyun-java-sdk-vod.version>
       <aliyun-java-vod-upload.version>1.4.11</aliyun-java-vod-upload.version>
       <aliyun-sdk-vod-upload.version>1.4.11</aliyun-sdk-vod-upload.version>
       <fastjson.version>1.2.28</fastjson.version>
       <gson.version>2.8.2</gson.version>
       <json.version>20170516</json.version>
       <commons-dbutils.version>1.7</commons-dbutils.version>
       <canal.client.version>1.1.0</canal.client.version>
       <docker.image.prefix>zx</docker.image.prefix>
       <cloud-alibaba.version>0.2.2.RELEASE</cloud-alibaba.version>
   </properties>
   ```

4. 配置子类锁定依赖的版本和不用写groupId和version

5. 整个pom.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
       <modelVersion>4.0.0</modelVersion>
   
       <groupId>com.geek</groupId>
       <artifactId>guli_edu</artifactId>
       <version>0.0.1-SNAPSHOT</version>
       <packaging>pom</packaging>
   
       <properties>
           <java.version>1.8</java.version>
           <guli.version>0.0.1-SNAPSHOT</guli.version>
           <mybatis-plus.version>3.0.5</mybatis-plus.version>
           <velocity.version>2.0</velocity.version>
           <swagger.version>3.0.0</swagger.version>
           <aliyun.oss.version>2.8.3</aliyun.oss.version>
           <jodatime.version>2.10.1</jodatime.version>
           <poi.version>3.17</poi.version>
           <commons-fileupload.version>1.3.1</commons-fileupload.version>
           <commons-io.version>2.6</commons-io.version>
           <httpclient.version>4.5.1</httpclient.version>
           <jwt.version>0.7.0</jwt.version>
           <aliyun-java-sdk-core.version>4.3.3</aliyun-java-sdk-core.version>
           <aliyun-sdk-oss.version>3.1.0</aliyun-sdk-oss.version>
           <aliyun-java-sdk-vod.version>2.15.2</aliyun-java-sdk-vod.version>
           <aliyun-java-vod-upload.version>1.4.11</aliyun-java-vod-upload.version>
           <aliyun-sdk-vod-upload.version>1.4.11</aliyun-sdk-vod-upload.version>
           <fastjson.version>1.2.28</fastjson.version>
           <gson.version>2.8.2</gson.version>
           <json.version>20170516</json.version>
           <commons-dbutils.version>1.7</commons-dbutils.version>
           <canal.client.version>1.1.0</canal.client.version>
           <docker.image.prefix>zx</docker.image.prefix>
           <cloud-alibaba.version>0.2.2.RELEASE</cloud-alibaba.version>
       </properties>
       <dependencyManagement>
           <dependencies>
               <!--Spring Cloud-->
               <dependency>
                   <groupId>org.springframework.cloud</groupId>
                   <artifactId>spring-cloud-dependencies</artifactId>
                   <version>Hoxton.RELEASE</version>
                   <type>pom</type>
                   <scope>import</scope>
               </dependency>
               <dependency>
                   <groupId>org.springframework.cloud</groupId>
                   <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                   <version>${cloud-alibaba.version}</version>
                   <type>pom</type>
                   <scope>import</scope>
               </dependency>
               <!--mybatis-plus 持久层-->
               <dependency>
                   <groupId>com.baomidou</groupId>
                   <artifactId>mybatis-plus-boot-starter</artifactId>
                   <version>${mybatis-plus.version}</version>
               </dependency>
               <!-- velocity 模板引擎, Mybatis Plus 代码生成器需要 -->
               <dependency>
                   <groupId>org.apache.velocity</groupId>
                   <artifactId>velocity-engine-core</artifactId>
                   <version>${velocity.version}</version>
               </dependency>
               <!--swagger-->
               <dependency>
                   <groupId>io.springfox</groupId>
                   <artifactId>springfox-boot-starter</artifactId>
                   <version>${swagger.version}</version>
               </dependency>
               <!--swagger ui-->
               <dependency>
                   <groupId>io.springfox</groupId>
                   <artifactId>springfox-swagger-ui</artifactId>
                   <version>${swagger.version}</version>
               </dependency>
               <!--aliyunOSS-->
               <dependency>
                   <groupId>com.aliyun.oss</groupId>
                   <artifactId>aliyun-sdk-oss</artifactId>
                   <version>${aliyun.oss.version}</version>
               </dependency>
               <!--日期时间工具-->
               <dependency>
                   <groupId>joda-time</groupId>
                   <artifactId>joda-time</artifactId>
                   <version>${jodatime.version}</version>
               </dependency>
               <!--xls-->
               <dependency>
                   <groupId>org.apache.poi</groupId>
                   <artifactId>poi</artifactId>
                   <version>${poi.version}</version>
               </dependency>
               <!--xlsx-->
               <dependency>
                   <groupId>org.apache.poi</groupId>
                   <artifactId>poi-ooxml</artifactId>
                   <version>${poi.version}</version>
               </dependency>
               <!--文件上传-->
               <dependency>
                   <groupId>commons-fileupload</groupId>
                   <artifactId>commons-fileupload</artifactId>
                   <version>${commons-fileupload.version}</version>
               </dependency>
               <!--commons-io-->
               <dependency>
                   <groupId>commons-io</groupId>
                   <artifactId>commons-io</artifactId>
                   <version>${commons-io.version}</version>
               </dependency>
               <!--httpclient-->
               <dependency>
                   <groupId>org.apache.httpcomponents</groupId>
                   <artifactId>httpclient</artifactId>
                   <version>${httpclient.version}</version>
               </dependency>
   
               <dependency>
                   <groupId>com.google.code.gson</groupId>
                   <artifactId>gson</artifactId>
                   <version>${gson.version}</version>
               </dependency>
               <!-- JWT -->
               <dependency>
                   <groupId>io.jsonwebtoken</groupId>
                   <artifactId>jjwt</artifactId>
                   <version>${jwt.version}</version>
               </dependency>
               <!--aliyun-->
               <dependency>
                   <groupId>com.aliyun</groupId>
                   <artifactId>aliyun-java-sdk-core</artifactId>
                   <version>${aliyun-java-sdk-core.version}</version>
               </dependency>
               <dependency>
                   <groupId>com.aliyun.oss</groupId>
                   <artifactId>aliyun-sdk-oss</artifactId>
                   <version>${aliyun-sdk-oss.version}</version>
               </dependency>
               <dependency>
                   <groupId>com.aliyun</groupId>
                   <artifactId>aliyun-java-sdk-vod</artifactId>
                   <version>${aliyun-java-sdk-vod.version}</version>
               </dependency>
               <dependency>
                   <groupId>com.aliyun</groupId>
                   <artifactId>aliyun-java-vod-upload</artifactId>
                   <version>${aliyun-java-vod-upload.version}</version>
               </dependency>
               <dependency>
                   <groupId>com.aliyun</groupId>
                   <artifactId>aliyun-sdk-vod-upload</artifactId>
                   <version>${aliyun-sdk-vod-upload.version}</version>
               </dependency>
               <dependency>
                   <groupId>com.alibaba</groupId>
                   <artifactId>fastjson</artifactId>
                   <version>${fastjson.version}</version>
               </dependency>
               <dependency>
                   <groupId>org.json</groupId>
                   <artifactId>json</artifactId>
                   <version>${json.version}</version>
               </dependency>
               <dependency>
                   <groupId>commons-dbutils</groupId>
                   <artifactId>commons-dbutils</artifactId>
                   <version>${commons-dbutils.version}</version>
               </dependency>
               <dependency>
                   <groupId>com.alibaba.otter</groupId>
                   <artifactId>canal.client</artifactId>
                   <version>${canal.client.version}</version>
               </dependency>
           </dependencies>
       </dependencyManagement>
   
       <build>
           <plugins>
               <plugin>
                   <groupId>org.springframework.boot</groupId>
                   <artifactId>spring-boot-maven-plugin</artifactId>
                   <configuration>
                       <fork>true</fork>
                       <addResources>true</addResources>
                   </configuration>
               </plugin>
           </plugins>
       </build>
   
   </project>
   ```

### 04-2、新建工程项目（service模块）









# 三、后台管理系统-讲师管理模块接口开发







# 四、后台管理系统-前端页面环境

```shell
# npm淘宝镜像设置
npm config set registry https://registry.npm.taobao.org
# 查看npm配置信息
npm config list
```

## 01、选取一个模板进行环境搭建

> 模板选择：vue-admin-template

1. 下载模板压缩文件

2. 解压文件到工作区内

3. 在VS的终端打开文件夹，进行依赖安装

   ```shell
   cnpm i
   ```
   
4. 启动下载好依赖的项目

   ```shell
   npm run dev
   ```


## 02、前端页面环境说明

1. 前端框架入口

   `index.html`和`main.js`

2. 前端页面环境使用框架（模板）是基于两种技术实现出来的

   `vue-admin-template模板`=`vue`+`element-ui`

### 02-1、目录结构

1. build：项目构建的脚本文件
2. mock：项目mock 模拟数据，在接口服务器没有就绪时，临时充当接口
3. public：静态资源

   - favicon.ico：favicon图标
   - index.html：html入口
4. src 源代码

   - api：所有请求
   - assets：主题 字体等静态资源，不会参与打包，直接直出
   - components：全局公用组件，和业务不相关，上传组件
   - icon：项目所有 svg icons
   - layout：全局 layout 负责搭建项目的整体架子结构 html结构
   - router：路由
   - store：全局store管理 vuex管理数据的位置 模块化开发 全局getters
   - styles：全局样式
   - utils：全局公用方法 request.js
   - vendor：公用vendor
   - views：views 所有页面 路由级别的组件
   - App.vue：入口页面 根组件
   - main.js：入口文件 加载组件 初始化等
   - permission.js：权限管理
   - settings.js：配置文件
5. tests：测试
6. .env.xxx：开发/生产等环境路径配置
7. .eslintignore：eslint 忽略文件 
8. .eslintrc.js：eslint 配置项 
9. .gitignore：git 忽略文件
10. .travis.yml：自动化CI配置
11. .babel.config.js：babel-loader 配置 
12. jest.config.js：测试配置
13. vue.config.js：vue-cli 配置（可以修改端口号）
14. postcss.config.js：postcss 配置
15. package.json：package.json

## 03、简易登录功能实现

后端：

`controller.EduLoginController`

```java
@RestController
@RequestMapping("eduservice/user")
@CrossOrigin //解决跨域
@Slf4j
public class EduLoginController {

    @PostMapping("/login")
    public R login(){
        log.info("请求登录");
        return R.ok().data("token","admin-token");
    }

    @GetMapping("/info")
    public R info(){
        log.info("获取用户信息");
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    @PostMapping("/logout")
    public R logout(){
        log.info("退出登录");
        return R.ok();
    }
}
```

前端：

`src/api/user.js`

```javascript
export function login(data) {
  return request({
    url: '/eduservice/user/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/eduservice/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/eduservice/user/logout',
    method: 'post'
  })
}
```

测试：

1. 启动前端服务：http://localhost:9528
2. 登录访问后端服务:http://localhost:8001/login
3. 此时无法登入，会出现跨域问题

## 04、跨域访问问题

![](README.assets/image-20220101214033171.png)

例如：http://localhost:9528/访问http://localhost:8001 这里端口不同就是跨域

1. 第一种

   `vue.config.js`

   ```javascript
   proxy: {
         [process.env.VUE_APP_BASE_API]:{//后台服务器地址
           target:"http://localhost:8001",
           changeOrigin:true,
           pathRewrite:{
             ['^' + process.env.VUE_APP_BASE_API]:''
           }
         }
       },
   ```

2. 第二种

   `.env.development`

   ```text
   # VUE_APP_BASE_API = 'http://localhost:8001'
   ```

   controller类上添加注解`@CrossOrigin`

## 05、讲师CRUD功能前端实现

1. 添加讲师列表和添加讲师路由

   `router.index.js`

   ```javascript
     {
       path: '/teacher',
       component: Layout,
       redirect: '/teacher/table',//访问/teacher，路径自动重定向为/teacher/table
       name: '讲师管理',
       meta: { title: '讲师管理', icon: 'el-icon-s-help' },//title：侧边栏标题，icon：标题旁边的图标
       children: [
         {
           path: 'table',
           name: '讲师列表',
           component: () => import('@/views/table/index'),//页面内容
           meta: { title: '讲师列表', icon: 'table' }
         },
         {
           path: 'save',
           name: '添加讲师',
           component: () => import('@/views/tree/index'),//页面内容
           meta: { title: '添加讲师', icon: 'tree' }
         }
       ]
     },
   ```

2. 点击页面的某个路由，在views创建vue文件显示路由对应的页面内容

   修改路由:

   ```java
   component: () => import('@/views/edu/teacher/list'),//内容
   
   component: () => import('@/views/edu/teacher/save'),
   ```

   views创建edu/teacher/list.vue和save.vue文件

   ```vue
   <template>
       <div class="app-container">
           讲师列表
       </div>
   </template>
   ```

   ```vue
   <template>
       <div class="app-container">
           讲师添加
       </div>
   </template>
   ```

   <img src="README.assets/image-20220102011604978.png" style="zoom:67%;" />

3. 在api文件夹创建`edu/teacher.js`，定义接口地址和参数从后端获取数据

   ```javascript
   import request from '@/utils/request'
   
   export default {
       /**
        * 讲师列表(对应分页复杂查询讲师接口)
        * @param {*} page       当前页
        * @param {*} size          每页大小
        * @param {*} teacherQuery  查询条件
        * @returns 
        */
       getTeacherListPage(page, size, teacherQuery) {
           return request({
               url: `/eduservice/edu-teacher/pageTeacherCondition/${page}/${size}`,
               method: "post",
               //teacherQuery条件对象，后端使用RequestBody获取数据,需要用json数据传递,
               //data表示将对象转换为json的形式
               data: teacherQuery            
           })
       }
   }
   ```

4. 在views的vue页面引入api的`teacher.js`文件，调用方法获取数据回显页面

   ```vue
   <template>
       <div class="app-container">
           讲师列表
       </div>
   </template>
   <script>
   //引入teacher.jswe
   import teacher from "@/api/edu/teacher";
   
   export default {
     //定义变量和初始值
     data() {
       return {
         listLoading: true, // 是否显示loading信息
         list: null, //查询之后接口返回集合
         page: 1, //当前页
         size: 10, //每页记录数
         total: 0, //总记录数
         teacherQuery: {}, //条件封装对象
       };
     },
     //页面渲染之前执行，一般调用methods定义的方法
     created() {
       this.getTeacherListPage();
     },
     // 创建具体的方法，调用teacher.js定义的方法
     methods: {
       //调用讲师列表
       getTeacherListPage() {
         this.listLoading = true;
         teacher
           .getTeacherListPage(this.page, this.size, this.teacherQuery)
           .then((result) => {
             //请求成功
             this.list = result.data.rows;
             this.total = result.data.total;
             console.log(this.list);
             this.listLoading = false;
           })
           .catch((err) => {
             //请求失败
             console.log(err);
           });
       },
     },
   };
   </script>
   ```

   测试控制台能否获取数据：

   1. 启动前后端服务

   2. 点击讲师列表

   3. 踩坑：

      查看控制台console

      ```java
      Error: timeout of 5000ms exceeded
          at createError (createError.js?cc5a:16)
          at XMLHttpRequest.handleTimeout (xhr.js?eda7:77)
      ```

      后端控制台：

      ```java
      Resolved [org.springframework.http.converter.HttpMessageNotReadableException: I/O error while reading input message; nested exception is org.apache.catalina.connector.ClientAbortException
      ```

   4. 解决:

      1. 打开`mock/mock-server.js`

      2. 注释以下代码即可

         ```javascript
         // app.use(bodyParser.json())
         // app.use(bodyParser.urlencoded({
         //   extended: true
         // }))
         ```

      3. 参考文章：https://www.jianshu.com/p/6d9a11f3c537

5. 使用element-ui显示数据内容

   ```vue
   <template>
     <div class="app-container">
       <el-table
         :data="list"
         v-loading="listLoading"
         element-loading-text="数据加载中"
         border
         fit
         highlight-current-row
       >
         <el-table-column label="序号" width="70" align="center">
           <template slot-scope="scope">
             {{ (page - 1) * size + scope.$index + 1 }}
           </template>
         </el-table-column>
         <el-table-column width="80" prop="name" label="名称" />
         <el-table-column label="头衔" width="80">
           <template slot-scope="scope">
             {{ scope.row.level === 1 ? "高级讲师" : "首席讲师" }}
           </template>
         </el-table-column>
         <el-table-column prop="intro" label="资历" />
         <el-table-column prop="gmtCreate" label="添加时间" width="160" />
         <el-table-column prop="sort" label="排序" width="60" />
         <el-table-column label="操作" width="200" align="center">
           <template slot-scope="scope">
             <router-link :to="'/edu/teacher/edit/' + scope.row.id">
               <el-button type="primary" size="mini" icon="el-icon-edit"
                 >修改</el-button
               >
             </router-link>
             <el-button
               type="danger"
               size="mini"
               icon="el-icon-delete"
               @click="removeDataById(scope.row.id)"
               >删除
             </el-button>
           </template>
         </el-table-column>
       </el-table>
     </div>
   </template>
   ```

   测试：

   ![](README.assets/image-20220102184039863.png)

### 05-1、讲师列表-分页功能实现

1. 从element-ui选择分页组件写入`list.vue`并修改total,pagesize,pager-count和current-change参数

   ```vue
   <template>
     <div class="app-container">
       <el-table
         :data="list"
         v-loading="listLoading"
         element-loading-text="数据加载中"
         border
         fit
         highlight-current-row
       >
         <el-table-column label="序号" width="70" align="center">
           <template slot-scope="scope">
             {{ (page - 1) * size + scope.$index + 1 }}
           </template>
         </el-table-column>
         <el-table-column width="80" prop="name" label="名称" />
         <el-table-column label="头衔" width="80">
           <template slot-scope="scope">
             {{ scope.row.level === 1 ? "高级讲师" : "首席讲师" }}
           </template>
         </el-table-column>
         <el-table-column prop="intro" label="资历" />
         <el-table-column prop="gmtCreate" label="添加时间" width="160" />
         <el-table-column prop="sort" label="排序" width="60" />
         <el-table-column label="操作" width="200" align="center">
           <template slot-scope="scope">
             <router-link :to="'/edu/teacher/edit/' + scope.row.id">
               <el-button type="primary" size="mini" icon="el-icon-edit"
                 >修改</el-button
               >
             </router-link>
             <el-button
               type="danger"
               size="mini"
               icon="el-icon-delete"
               @click="removeDataById(scope.row.id)"
               >删除
             </el-button>
           </template>
         </el-table-column>
       </el-table>
       <!-- 分页功能 -->
       <template>
         <!-- total记录总数，page-size每页记录数,pager-count 设置最大页码按钮在第几个位置,current-change 当前页改变时触发 -->
         <el-pagination
           :page-size="size"
           :pager-count="5"
           :current-page="page"
           :total="total"
           layout="total, prev, pager, next, jumper"
           style="padding:30px 0; text-align:center"
           @current-change="getTeacherListPage"
         >
         </el-pagination>
       </template>
     </div>
   </template>
   ```

2. 对应`@current-change`当前页改变时传入当前页码

   ```javascript
     methods: {
       //调用讲师列表
       getTeacherListPage(page = 1) {//不传入page默认为1
         this.page = page //当page发现改变时，传入当前page
         this.listLoading = true;
         teacher
           .getTeacherListPage(this.page, this.size, this.teacherQuery)
           .then((result) => {
             //请求成功
             this.list = result.data.rows;
             this.total = result.data.total;
             console.log(this.list);
             this.listLoading = false;
           })
           .catch((err) => {
             //请求失败
             console.log(err);
           });
       },
     },
   ```

3. 效果

   ![](README.assets/image-20220104105643052.png)

### 05-2、讲师查询功能实现

1. 从element-ui选择form表单组件写入`list.vue`,加在表格列表的前面

   ```vue
       <!--查询表单-->
       <template>
         <el-form :inline="true" :model="teacherQuery" class="demo-form-inline" style="text-align:center">
           <el-form-item label="讲师名">
             <el-input v-model="teacherQuery.name" placeholder="讲师名"></el-input>
           </el-form-item>
           <el-form-item label="讲师头衔">
             <el-select v-model="teacherQuery.level" placeholder="讲师头衔">
               <el-option label="高级讲师" value="1"></el-option>
               <el-option label="首席讲师" value="2"></el-option>
             </el-select>
           </el-form-item>
           <el-form-item label="添加时间">
             <el-date-picker
               v-model="teacherQuery.begin"
               type="datetime"
               placeholder="选择开始时间"
               value-format="yyyy-MM-dd HH:mm:ss"
               default-time="00:00:00"
             />
           </el-form-item>
           <el-form-item size="normal">
             <el-date-picker
               v-model="teacherQuery.end"
               type="datetime"
               placeholder="选择截至时间"
               value-format="yyyy-MM-dd HH:mm:ss"
               default-time="00:00:00"
             />
           </el-form-item>
           <el-form-item>
             <el-button type="primary" @click="getTeacherListPage()">查询</el-button>
             <el-button type="default" @click="resetData()">清空</el-button>
           </el-form-item>
         </el-form>
       </template>
       <!-- 表格 -->
   ```

2. 在js添加清空条件方法

   ```javascript
   resetData() {
       this.teacherQuery = {};
       this.getTeacherListPage();
   },
   ```

3. 效果

   ![](README.assets/image-20220104134852790.png)

### 05-3、讲师删除

1. 每条记录后面添加删除按钮

   <img src="README.assets/image-20220104135314705.png" style="zoom: 67%;" />

2. 在按钮上绑定删除事件，并传参删除的记录的id

   ```vue
   <el-button type="danger" size="mini" icon="el-icon-delete" @click="removeTeacherById(scope.row.id)">删除</el-button>
   ```

3. 在`api/edu/teacher.js`定义删除接口

   ```javascript
   /**
        * 根据id逻辑删除讲师
        * @param {*} id 讲师id
        * @returns 
        */
   removeTeacherById(id) {
       return request({
           url: `/eduservice/edu-teacher/${id}`,
           method: "delete"
       })
   }
   ```

4. 在`views/list.vue`实现确认删除功能，选择确定后再调用删除接口

   ```javascript
   removeTeacherById(id) {
       //1.删除前提示是否继续删除
       this.$confirm("此操作将永久删除讲师记录,是否继续?", "提示", {
           confirmButtonText: "确定",
           cancelButtonText: "取消",
           type: "warning",
       }).then(() => { //2.选择继续删除
           //3.调用删除记录的接口
           teacher
               .removeTeacherById(id)
               .then((result) => {
               //4.提示删除成功
               this.$message({
                   type: "success",
                   message: "删除成功!",
               });
           })
           //5.刷新页面
           this.getTeacherListPage();
       });
   },
   ```

5. 效果

   1. 点击删除目标

      <img src="README.assets/image-20220104154150003.png" style="zoom:67%;" />

   2. 确定删除

      <img src="README.assets/image-20220104154230268.png" style="zoom: 80%;" />

### 05-4、讲师添加

1. 从element-ui选择form表单组件写入`save.vue`

   ```vue
   <template>
     <div class="app-container">
       <h2>讲师添加</h2>
       <el-form label-width="120px">
         <!-- 讲师名称 -->
         <el-form-item label="讲师名称">
           <el-input v-model="teacher.name"></el-input>
         </el-form-item>
         <!-- 讲师排序 -->
         <el-form-item label="讲师排序">
           <el-input-number
             v-model="teacher.sort"
             controls-position="right"
             :min="0"
           />
         </el-form-item>
         <!-- 讲师头衔 -->
         <el-form-item label="讲师头衔">
           <el-select v-model="teacher.level" placeholder="请选择" clearable>
             <!-- 
               数据类型一定要和取出的json中的一致 否则没法回填,
               因此，这里value使用动态绑定的值，保证其数据类型是number
            -->
             <el-option :value="1" label="高级讲师" />
             <el-option :value="2" label="首席讲师" />
           </el-select>
         </el-form-item>
         <!-- 讲师资历 -->
         <el-form-item label="讲师资历">
           <el-input v-model="teacher.career" />
         </el-form-item>
         <!-- 讲师简介 -->
         <el-form-item label="讲师简介">
           <el-input v-model="teacher.intro" :rows="10" type="textarea" />
         </el-form-item>
         <!-- 讲师头像 -->
         <el-form-item>
           <el-button
             type="primary"
             @click="saveOrUpdate"
             :disabled="saveBtnDisabled"
             >保存</el-button
           >
         </el-form-item>
       </el-form>
     </div>
   </template>
   ```

   页面效果：![](README.assets/image-20220104195608648.png)

2. 在`api`添加`添加讲师`接口

   ```javascript
   /**
     * 添加讲师
     * @param {} teacher 
     * @returns 
     */
   addTeacher(teacher) {
       return request({
           url: `/eduservice/edu-teacher/addTeacher`,
           method: "post",
           data: teacher
       })
   }
   ```

3. 在`views`调用api的接口

   ```javascript
   <script>
   import teacherApi from "@/api/edu/teacher";
   export default {
     data() {
       return {
         teacher: {},
         saveBtnDisabled: false, //点击一次后按钮是否被禁用
       };
     },
     created() {},
     methods: {
       saveOrUpdate() {
         this.saveTeacher();
       },
       //添加讲师的方法
       saveTeacher() {
         teacherApi.addTeacher(this.teacher).then((result) => {
           //1.提示添加成功信息
           this.$message({
             type: "success",
             message: "添加成功！",
           });
           //2.返回讲师列表（路由跳转）
           this.$router.push({ path: "/teacher/table" });
         });
       },
     },
   };
   </script>
   ```

4. 测试

   1. 进入讲师添加页面
   2. 填写讲师信息提交
   3. 显示添加成功，跳转讲师列表





























