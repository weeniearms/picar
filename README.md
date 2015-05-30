picar
=====

[![Build Status](https://travis-ci.org/weeniearms/picar.svg?branch=master)](https://travis-ci.org/weeniearms/picar)

Java app for controlling an RC car with a Raspberry Pi.

## picar

A spring-boot based app that exposes an HTTP interface for controlling the car. Requires a [RaspiRobot Board](https://www.sparkfun.com/products/11561), JDK7 and Pi4J.

To run the app on a Raspberry Pi simply upload the picar-exec.jar and execute ```sudo java -jar picar-exec.jar```

## picar-web

A spring-boot and angular  based web app that's supposed to mimic an RC car remote.
