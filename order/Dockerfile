FROM hub.c.163.com/library/java:8-alpine

MAINTAINER dario dario2018@aliyun.com

ADD target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]

