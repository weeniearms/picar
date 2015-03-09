FROM dockerfile/java:oracle-java8

MAINTAINER weenie <weeniearms@gmail.com>

RUN adduser --system --uid 1001 --home /var/weenie --shell /sbin/nologin --group --debug weenie

ADD target/picar-web-exec.jar /var/weenie/

WORKDIR /var/weenie

USER weenie

CMD ["java", "-jar", "picar-web-exec.jar"]
