#!/bin/sh
sshpass -p raspberry ssh pi@192.168.1.106 "nohup sudo java -jar picar-1.0-SNAPSHOT-exec.jar> /dev/null 2>&1 &"
