
FROM openjdk:8-jdk-alpine
LABEL maintainer="arthur.alvesdeveloper@outlook.com"
VOLUME /tmp
EXPOSE 8080
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-XX:+UnlockExperimentalVMOptions","-XX:+UseCGroupMemoryLimitForHeap","-XX:MaxRAMFraction=1","-XshowSettings:vm","-cp","app:app/lib/*","br.com.ecosystem.SpringBootRunApplication"]