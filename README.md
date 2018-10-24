福耀ARG发布
====

####step 1:
停止服务

```Bash
cd /u01/apache-tomcat-9.0.8/bin/
./shutdown.sh
```

####step 2:
删除旧war包, 删除缓存文件
````Bash
cd /u01/apache-tomcat-9.0.8/webapps/
rm -rf ROOT*
````

####step 3:
打包, 确保arg-web为最新代码运行打包命令

```Bash
cd arg-web
mvn clean compile package
```

####step 4:
将war包拷贝至/u01/apache-tomcat-9.0.8/webapps/
运行http服务启动命令
```Bash
cd /u01/apache-tomcat-9.0.8/bin/
./startup.sh
```