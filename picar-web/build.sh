#!/bin/sh

mvn clean install -f ../pom.xml

docker build --rm --tag=weenie/picar-web .


