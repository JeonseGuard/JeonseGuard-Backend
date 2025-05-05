# 기본 이미지 설정
FROM --platform=linux/amd64 openjdk:17-jdk-slim

# jar 파일 이름
ARG JAR_FILE=build/libs/JeonseGuard-Backend-0.0.1-SNAPSHOT.jar

# 애플리케이션 JAR 파일 복사
COPY ${JAR_FILE} app.jar

# 포트 오픈
EXPOSE 8080

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "app.jar"]
