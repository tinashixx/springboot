# 使用官方的Java 17 基本映像作為基礎
FROM maven:3.8.4-openjdk-17-slim

# 指定RUN,CMD,ENTRYPOINT,ADD,COPY時的工作目錄
WORKDIR /usr/src/app

# 複製Maven構建的JAR文件至映像檔
COPY . /usr/src/app

# 建立映像檔時執行指令
RUN mvn package

# 指令啟動容器時執行以下指令
ENV PORT 8080
EXPOSE $PORT
CMD [ "sh", "-c", "mvn -Dserver.port=${PORT} spring-boot:run" ]

# 定義環境變數
ARG DOCKER_IMAGE_VERSION=unknown
ENV DOCKER_IMAGE_VERSION=${DOCKER_IMAGE_VERSION}