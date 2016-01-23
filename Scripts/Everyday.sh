#!/bin/bash
#Everyday.sh
cd /home/pi/Lights
java -jar ScheduleLights.jar
cat Schedule.txt >> Cron.txt
crontab Cron.txt
