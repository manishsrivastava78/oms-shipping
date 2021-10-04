# Base Alpine Linux based image with OpenJDK JRE only
FROM openjdk:8-jre-alpine
# copy application JAR (with libraries inside)
COPY target/oms-shipping-app-1.0.jar /oms-shipping-app-1.0.jar
# specify default command
CMD ["/usr/bin/java", "-jar",  "/oms-shipping-app-1.0.jar"]