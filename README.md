window安装MySQL8.0.19记录
    安装完成之后,使用工具Navicat连接出现了不让远程连接引发的一系列处理问题整理
MySQL中查看用户一些信息
use mysql;
select user,host,authentication_string,plugin from user;
创建用户和授权
    1.创建一个用户
    create user 'test'@'%' identified with mysql_native_password by 'test';
    host为%表示不限制ip,localhost表示本机使用,plugin非mysql_native_password则需要修改密码
    2.授权
    grant all privileges on *.* to 'test'@'%' with grant option;

授权 root 用户的所有权限并设置远程访问
grant all on *.* to 'root'@'%';
grant all on:表示所有权限,%表示通配所有host可以访问远程。
alter user 'root'@'%' identified with mysql_native_password by '自己设置的密码值';
刷新权限
所有操作后,应执行
flush privileges;

MySQL8之前的版本中加密规则是mysql_native_password,而在MySQL8之后,
加密规则是caching_sha2_password.
有两种解决问题方案:
    一种是升级Navicat驱动;
    一种是把MySQL用户登录密码加密规则还原成mysql_native_password.
第二种方法是:
    1.修改加密规则
    alter user 'root'@'%' identified by '设置的密码' password expire never;
    2.更新root用户密码
    alter user 'root'@'%' identified with mysql_native_password by '设置的密码';
    3.刷新权限
    flush privileges;

删除用户
delete from user where user='test'and host='%';
flush privileges;

修改用户信息
update user set host='localhost' where user='test';
flush privileges;

使用数据库连接池Druid
参考文档地址:https://github.com/alibaba/druid

MySQl-8.0.19版本连接数据库设置遇到问题
服务器的时区设置serverTimezone=Asia/Shanghai或者是serverTimezone=GMT%2B8
如果不设置,那么就会连接报错以下错误
java.sql.SQLException: The server time zone value '�й���׼ʱ��' is unrecognized or represents more than one time zone. You must configure either the server or JDBC driver (via the 'serverTimezone' configuration property) to use a more specifc time zone value if you want to utilize time zone support.
如果设置为其他的值会出现时差
数据库以下参数需配置
useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=Asia/Shanghai