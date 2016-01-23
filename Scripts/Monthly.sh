#!/bin/bash
#Monthly.sh
cd /home/pi/Lights
crontab -r
echo "0 5 * * * /home/pi/Lights/Everyday.sh" > Cron.txt
echo "0 4 1 * * /home/pi/Lights/Monthly.sh" >> Cron.txt
crontab Cron.txt
