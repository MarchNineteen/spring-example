# 构建

### Dockerfile占位符
- resources 添加 <filtering>true</filtering> 
                
- 直接继承springboot-start 使用@@ 自己的parent${}

#推送
- maven setting文件 配置server
```
<server>
    <id>docker-hub</id>
    <username></username>
    <password></password>
    <configuration>
      <email></email>
    </configuration>
  </server>
```
- docker-maven-plugin 在configuration 中添加<serverId>docker-hub</serverId>

- 打包指令 mvn git-commit-id:revision docker:build docker:push
