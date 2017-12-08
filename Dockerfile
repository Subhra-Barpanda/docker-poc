FROM openjdk:8
ADD target/poc.jar poc.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","poc.jar"]