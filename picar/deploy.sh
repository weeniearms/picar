#!/bin/sh
sshpass -p raspberry scp target/picar-exec.jar pi@192.168.1.106:~
