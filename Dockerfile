FROM java:8
VOLUME /tmp
ADD target/wercker-example-java-spring-redis.jar app.jar
RUN bash -c 'touch /app.jar'
# To reduce Tomcat startup time we added a system property pointing to "/dev/urandom" as a source of entropy.
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]