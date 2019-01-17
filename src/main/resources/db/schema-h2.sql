DROP TABLE IF EXISTS user;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '姓名',
  `age` int(11) NOT NULL DEFAULT '0' COMMENT '年龄',
  `email` varchar(50) NOT NULL DEFAULT '' COMMENT '邮箱',
  `is_del` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标志：0-未删除 1-已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
