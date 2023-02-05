FROM openjdk:11-jre-slim-buster
COPY ./target/coupon_system_spring_boot-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch coupon_system_spring_boot-0.0.1-SNAPSHOT.jar'
ENTRYPOINT ["java","-jar","coupon_system_spring_boot-0.0.1-SNAPSHOT.jar"]