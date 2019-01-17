DROP TABLE
IF EXISTS USER;

DROP TABLE
IF EXISTS ROLE;

DROP TABLE
IF EXISTS user_role;

CREATE TABLE `user` (
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`user_id` VARCHAR (20) NOT NULL DEFAULT '' COMMENT '用户编号',
	`user_name` VARCHAR (30) NOT NULL DEFAULT '' COMMENT '姓名',
	`age` INT (11) NOT NULL DEFAULT '0' COMMENT '年龄',
	`email` VARCHAR (50) NOT NULL DEFAULT '' COMMENT '邮箱',
	`add_time` datetime NOT NULL DEFAULT '1900-01-01 00:00:00' COMMENT '创建时间',
	`modify_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	`is_del` TINYINT (4) NOT NULL DEFAULT '0' COMMENT '删除标志：0-未删除 1-已删除',
	PRIMARY KEY (`id`),
	UNIQUE KEY (user_id)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

CREATE TABLE `role` (
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`role_id` VARCHAR (20) NOT NULL DEFAULT '' COMMENT '角色编号',
	`role_name` VARCHAR (32) DEFAULT NULL COMMENT '角色名称',
	`role_type` VARCHAR (10) DEFAULT NULL COMMENT '角色类型',
	`add_time` datetime NOT NULL DEFAULT '1900-01-01 00:00:00' COMMENT '创建时间',
	`modify_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	`is_del` TINYINT (4) NOT NULL DEFAULT '0' COMMENT '删除标志：0-未删除 1-已删除',
	PRIMARY KEY (`id`),
	UNIQUE KEY (role_id)
) ENGINE = INNODB DEFAULT CHARSET = utf8;

CREATE TABLE `user_role` (
	`id` BIGINT (20) NOT NULL AUTO_INCREMENT COMMENT '主键',
	`user_id` VARCHAR (20) NOT NULL DEFAULT '' COMMENT '用户编号',
	`role_id` VARCHAR (20) NOT NULL DEFAULT '' COMMENT '角色编号',
	`add_time` datetime NOT NULL DEFAULT '1900-01-01 00:00:00' COMMENT '创建时间',
	`modify_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
	`is_del` TINYINT (4) NOT NULL DEFAULT '0' COMMENT '删除标志：0-未删除 1-已删除',
	PRIMARY KEY (`id`),
	UNIQUE KEY (user_id, role_id)
) ENGINE = INNODB DEFAULT CHARSET = utf8;


INSERT INTO user (id, user_id,user_name, age, email,add_time) VALUES
(1,'u1' ,'Jone', 18, 'test1@baomidou.com',NOW()),
(2,'u2', 'Jack', 20, 'test2@baomidou.com',NOW()),
(3,'u3', 'Tom', 28, 'test3@baomidou.com',NOW()),
(4,'u4', 'Sandy', 21, 'test4@baomidou.com',NOW()),
(5,'u5', 'Billie', 24, 'test5@baomidou.com',NOW()),
(6,'u6', 'ydp', 24, 'test6@baomidou.com',NOW());

INSERT INTO role(role_id,role_name,role_type,add_time) VALUES
('r1','管理员','admin',NOW()),
('r2','用户','user',NOW());


INSERT INTO user_role(user_id,role_id,add_time) VALUES
('u1','r2',NOW()),
('u2','r2',NOW()),
('u3','r2',NOW()),
('u4','r2',NOW()),
('u5','r1',NOW()),
('u6','r1',NOW());
