FROM eclipse-temurin:17-jre

WORKDIR /app
COPY target/*.jar app.jar

ADD https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

EXPOSE 8080

ENTRYPOINT ["/wait-for-it.sh", "mysql:3306", "--timeout=60", "--", "java", "-jar", "app.jar"]