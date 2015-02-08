FROM ubuntu:14.04

MAINTAINER weenie <weeniearms@gmail.com>

RUN \
    echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
    echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" | tee /etc/apt/sources.list.d/webupd8team-java.list && \
    echo "deb-src http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" | tee -a /etc/apt/sources.list.d/webupd8team-java.list && \ 
    apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys EEA14886 && \
    apt-get update && \
    apt-get -y install oracle-java8-installer && \
    apt-get -y install adduser && \
    apt-get -y install curl && \
    apt-get -y install unzip && \
    rm -rf /var/lib/apt/lists/* && \ 
    rm -rf /var/cache/oracle-jdk8-installer && \
    rm -rf /var/cache/adduser && \
    rm -rf /var/cache/curl && \
    rm -rf /var/cache/unzip && \
    adduser --system --uid 1001 --home /var/weenie --shell /sbin/nologin --group --debug weenie


ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

WORKDIR /var/weenie

USER weenie

CMD ["bash"]
