FROM openjdk:11
ENV APP_FILE football-team-rest-service-1.0.jar
ENV APP_HOME /usr/apps
COPY target/$APP_FILE $APP_HOME/
WORKDIR $APP_HOME
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar ./football-team-rest-service-1.0.jar"]
