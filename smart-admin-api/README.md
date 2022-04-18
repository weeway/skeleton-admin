# 项目指南

## 项目启动
1. 在已有的MySQL实例上执行MySQL脚本，按如下顺序执行:
- src/main/resources/sql/smart-admin.sql
- src/main/resources/sql/quartz_mysql_2.3.0.sql

2. 修改配置文件`src/main/resources/dev/application.properties`中的mysql配置，把连接地址改为第一步执行sql的实例地址
```
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/smart-admin-dev?autoReconnect=true&useServerPreparedStmts=false&rewriteBatchedStatements=true&characterEncoding=UTF-8&useSSL=false&allowMultiQueries=true&serverTimezone=UTC
spring.datasource.username=erp
spring.datasource.password=listen1015
```
