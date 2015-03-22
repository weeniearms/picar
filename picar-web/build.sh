#!/bin/sh

mvn clean install -f ../pom.xml

sudo docker build --rm --tag=weenie/picar-web .


