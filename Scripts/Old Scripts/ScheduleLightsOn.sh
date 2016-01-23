#!/bin/bash
cd /Users/kirsten/IdeaProjects/ScheduleLights/out/artifacts/ScheduleLights_jar
java -jar ScheduleLights.jar > output.txt
CRONJOB=$(cat output.txt)
#write out current crontab
crontab -l > mycron
#echo new cron into cron file
echo "$CRONJOB" >> mycron
#install new cron file
crontab mycron

