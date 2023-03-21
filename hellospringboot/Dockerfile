# 使用官方的Java 8基本映像作為基礎
FROM openjdk:8-jdk-alpine

# 將應用程序的卷掛載到容器內
VOLUME /tmp

# 複製Maven構建的JAR文件到容器中，並命名為app.jar
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} hellospringboot-1.0.jar

# 開放容器中的端口，這取決於你的應用程序使用的端口
EXPOSE 8080

# 執行Spring Boot應用程序
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/hellospringboot-1.0.jar"]
