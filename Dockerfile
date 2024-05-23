FROM openjdk:17
ARG JAR_FILE=build/libs/*.jarz
ARG jasyptkey
ENV JASYPT_KEY $jasyptkey
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
