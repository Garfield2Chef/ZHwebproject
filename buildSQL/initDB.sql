

DROP TABLE IF EXISTS tb_user;
CREATE TABLE tb_user
(
	id varchar(64) NOT NULL COMMENT '编号',
	company_id varchar(64) NOT NULL COMMENT '归属公司',
	office_id varchar(64) NOT NULL COMMENT '归属部门',
	login_name varchar(25) NOT NULL COMMENT '登录名',
	password varchar(100) NOT NULL COMMENT '密码',
	name varchar(40) NOT NULL COMMENT '姓名',
	email varchar(50) COMMENT '邮箱',
	phone varchar(15) COMMENT '电话',
	mobile varchar(15) COMMENT '手机',
	photo varchar(1000) COMMENT '用户头像',
	login_ip varchar(100) COMMENT '最后登陆IP',
	login_date datetime COMMENT '最后登陆时间',
	login_flag varchar(5) COMMENT '是否可登录',
	create_by varchar(40) NOT NULL COMMENT '创建者',
	create_date datetime NOT NULL COMMENT '创建时间',
	update_by varchar(40) NOT NULL COMMENT '更新者',
	update_date datetime NOT NULL COMMENT '更新时间',
	remarks varchar(255) COMMENT '备注信息',
	del_flag char(1) DEFAULT '0' NOT NULL COMMENT '删除标记',
	PRIMARY KEY (id)
) COMMENT = '用户表';
