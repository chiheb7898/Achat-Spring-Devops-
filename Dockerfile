FROM openjdk:8-jdk-alpine
RUN apk --no-cache add curl
RUN curl -u admin:19129 -o achat.jar "http://192.168.1.4:8080/repository/maven-releases/tn/esprit/rh/achat/1.0/achat-1.0.jar" -L
ENTRYPOINT ["java","-jar","/achat.jar"]
EXPOSE 8089