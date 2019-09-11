FROM java:8-jre
#ENV APP_NAME @project.build.finalName@.jar   引用springboot的parent需要使用@@占位符
#ENV APP_NAME ${project.build.finalName}.jar

#当pom直接继承springboot-statrt 要使用@@占位符
ADD @project.build.finalName@.jar app.jar
ENTRYPOINT ["sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar app.jar"]

