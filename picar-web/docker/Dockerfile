FROM weenie/base-jdk8

MAINTAINER weenie <weeniearms@gmail.com>

ADD picar-web-exec.jar /var/weenie/

WORKDIR /var/weenie

USER weenie

CMD ["java", "-jar", "picar-web-exec.jar"]
