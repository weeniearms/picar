#!/bin/sh
sshpass -p raspberry scp target/picar-1.0-SNAPSHOT-exec.jar pi@192.168.1.106:~
