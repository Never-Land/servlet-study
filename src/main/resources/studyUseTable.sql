CREATE TABLE IF NOT EXISTS product(
	id INT(10) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
	name VARCHAR(50) NOT NULL COMMENT '商品名称',
	description VARCHAR(200) NOT NULL DEFAULT '' COMMENT '商品描述',
	price DECIMAL(4,2) NOT NULL DEFAULT '0.00' COMMENT '商品价格',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品基本信息表';