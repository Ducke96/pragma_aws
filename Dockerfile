FROM openjdk:21
VOLUME /tmp
EXPOSE 8081
ADD ./target/pragma_aws-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
