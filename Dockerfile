FROM amazoncorretto:17-alpine-jdk
WORKDIR /app
EXPOSE 8070
ADD ./target/kardex-0.0.1-SNAPSHOT.jar msvc-kardex.jar

ENTRYPOINT ["java", "-jar", "msvc-kardex.jar"]
