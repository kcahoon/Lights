#!/bin/bash
#write out current crontab
crontab -l > mycron
#echo new cron into cron file
echo "15 23 * * * /Users/kirsten/Documents/Bash/LightsOff.sh" >> mycron
#install new cron file
crontab mycron
