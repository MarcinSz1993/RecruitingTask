FROM openjdk:22
LABEL authors="Marcin Szabała"

COPY target/RecruitingTask-0.0.1-SNAPSHOT.jar /usr/app/recruiting_task.jar

WORKDIR /usr/app

ENTRYPOINT ["java", "-jar","recruiting_task.jar"]