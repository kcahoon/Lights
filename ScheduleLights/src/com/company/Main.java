package com.company;

import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator;
import com.luckycatlabs.sunrisesunset.dto.Location;

import java.io.*;
import java.util.Calendar;

public class Main {

    public static void main(String[] args) {
        /** Times to be scheduled:
         *  Front
         *      ON: Sunset - (15 to 30 min)
         *      DIM: 10 pm + (0 to 30 min)
         *      OFF: 11:30 + (0 to 30 min)
         *
         *  Back
         *      ON: 6:30 am
         *          Sunset - (15 to 30 min)
         *      OFF: 8:30 am
         *           11:30 pm +- (15 min)
         *
         *  Landscape
         *      ON: Sunset - (15 to 30 min)
         *      OFF: 1:00 am
         *
         *  Upstairs Hallway
         *      ON: Sunset - (15 to 30 min)
         *      DIM: 9:00 pm
         *      OFF: 11:30 pm +- (15 min)
         *
         *  Master
         *      ON: Sunset - (15 to 30 min)
         *      OFF: 11:30 pm +- (15 min)
         */

	    //Get Sunrise and Sunset times
        Location location = new Location("37.681874", "-121.768009");
        SunriseSunsetCalculator calculator = new SunriseSunsetCalculator(location, "America/Los_Angeles");
        Calendar officialSunset = calculator.getOfficialSunsetCalendarForDate(Calendar.getInstance());

        //Generate Front times
        int frontOffset1 = (15 + (int)(Math.random() * ((30 - 15) + 1))) * -1;
        Calendar Front1 = (Calendar)officialSunset.clone();
        Front1.add(Calendar.MINUTE, frontOffset1);

        int frontOffset2 = (0 + (int)(Math.random() * ((30 + 0) + 1))) * 1;
        Calendar Front2 = Calendar.getInstance();
        Front2.set(Calendar.MINUTE, 30);
        Front2.set(Calendar.HOUR_OF_DAY, 23);
        Front2.add(Calendar.MINUTE, frontOffset2);

        int frontOffset3 = (0 + (int)(Math.random() * ((30 + 0) + 1))) * 1;
        Calendar Front3 = Calendar.getInstance();
        Front3.set(Calendar.MINUTE, 0);
        Front3.set(Calendar.HOUR_OF_DAY, 22);
        Front3.add(Calendar.MINUTE, frontOffset3);

        String frontOn = Front1.get(Calendar.MINUTE) + " " + Front1.get(Calendar.HOUR_OF_DAY)
                + " " + Front1.get(Calendar.DAY_OF_MONTH) + " " +  (Front1.get(Calendar.MONTH) + 1)
                + " * " + "/usr/bin/java -jar /home/pi/Lights/SwitchLights.jar on front";

        String frontOff = Front2.get(Calendar.MINUTE) + " " + Front2.get(Calendar.HOUR_OF_DAY)
                + " " + Front2.get(Calendar.DAY_OF_MONTH) + " " +  (Front2.get(Calendar.MONTH) + 1)
                + " * " + "/usr/bin/java -jar /home/pi/Lights/SwitchLights.jar off front";

        String frontDim = Front3.get(Calendar.MINUTE) + " " + Front3.get(Calendar.HOUR_OF_DAY)
                + " " + Front3.get(Calendar.DAY_OF_MONTH) + " " +  (Front3.get(Calendar.MONTH) + 1)
                + " * " + "/usr/bin/java -jar /home/pi/Lights/SwitchLights.jar dim front 60";

        //System.out.println(frontOn);
        //System.out.println(frontOff);

        //Generate Back times
        String backOn1 = "30 6 " + Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + " " +
                + (Calendar.getInstance().get(Calendar.MONTH) + 1) + " * " + "/usr/bin/java -jar /home/pi/Lights/SwitchLights.jar on back";

        String backOff1 = "30 8 " + Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + " " +
                + (Calendar.getInstance().get(Calendar.MONTH) + 1) + " * " + "/usr/bin/java -jar /home/pi/Lights/SwitchLights.jar off back";

        int backOffset3 = (15 + (int)(Math.random() * ((30 - 15) + 1))) * -1;
        Calendar Back3 = (Calendar)officialSunset.clone();
        Back3.add(Calendar.MINUTE, backOffset3);

        int backOffset4 = (-15 + (int)(Math.random() * ((15 + 15) + 1))) * -1;
        Calendar Back4 = Calendar.getInstance();
        Back4.set(Calendar.MINUTE, 30);
        Back4.set(Calendar.HOUR_OF_DAY, 23);
        Back4.add(Calendar.MINUTE, backOffset4);

        String backOn2 = Back3.get(Calendar.MINUTE) + " " + Back3.get(Calendar.HOUR_OF_DAY)
                + " " + Back3.get(Calendar.DAY_OF_MONTH) + " " +  (Back3.get(Calendar.MONTH) + 1)
                + " * " + "/usr/bin/java -jar /home/pi/Lights/SwitchLights.jar on back";

        String backOff2 = Back4.get(Calendar.MINUTE) + " " + Back4.get(Calendar.HOUR_OF_DAY)
                + " " + Back4.get(Calendar.DAY_OF_MONTH) + " " +  (Back4.get(Calendar.MONTH) + 1)
                + " * " + "/usr/bin/java -jar /home/pi/Lights/SwitchLights.jar off back";

        //System.out.println(backOn1);
        //System.out.println(backOff1);
        //System.out.println(backOn2);
        //System.out.println(backOff2);

        //Generate Landscape times
        int landscapeOffset1 = (15 + (int)(Math.random() * ((30 - 15) + 1))) * -1;
        Calendar Landscape1 = (Calendar)officialSunset.clone();
        Landscape1.add(Calendar.MINUTE, landscapeOffset1);
        Calendar Landscape2 = Calendar.getInstance();
        Landscape2.add(Calendar.DAY_OF_MONTH, 1);
        Landscape2.set(Calendar.HOUR_OF_DAY, 1);
        Landscape2.set(Calendar.MINUTE, 0);

        String landscapeOn = Landscape1.get(Calendar.MINUTE) + " " + Landscape1.get(Calendar.HOUR_OF_DAY)
                + " " + Landscape1.get(Calendar.DAY_OF_MONTH) + " " +  (Landscape1.get(Calendar.MONTH) + 1)
                + " * " + "/usr/bin/java -jar /home/pi/Lights/SwitchLights.jar on landscape";

        String landscapeOff = "0 1 " + Landscape2.get(Calendar.DAY_OF_MONTH) + " " +
                + (Landscape2.get(Calendar.MONTH) + 1) + " * " + "/usr/bin/java -jar /home/pi/Lights/SwitchLights.jar off landscape";

        //System.out.println(landscapeOn);
        //System.out.println(landscapeOff);

        //Generate Upstairs Hallway times
        int upHallOffset1 = (15 + (int)(Math.random() * ((30 - 15) + 1))) * -1;
        Calendar upHall1 = (Calendar)officialSunset.clone();
        upHall1.add(Calendar.MINUTE, upHallOffset1);

        int upHallOffset2 = (-15 + (int)(Math.random() * ((15 + 15) + 1))) * -1;
        Calendar upHall2 = Calendar.getInstance();
        upHall2.set(Calendar.MINUTE, 30);
        upHall2.set(Calendar.HOUR_OF_DAY, 23);
        upHall2.add(Calendar.MINUTE, upHallOffset2);

        String upHallOn = upHall1.get(Calendar.MINUTE) + " " + upHall1.get(Calendar.HOUR_OF_DAY)
                + " " + upHall1.get(Calendar.DAY_OF_MONTH) + " " +  (upHall1.get(Calendar.MONTH) + 1)
                + " * " + "/usr/bin/java -jar /home/pi/Lights/SwitchLights.jar on upHall";

        String upHallOff = upHall2.get(Calendar.MINUTE) + " " + upHall2.get(Calendar.HOUR_OF_DAY)
                + " " + upHall2.get(Calendar.DAY_OF_MONTH) + " " +  (upHall2.get(Calendar.MONTH) + 1)
                + " * " + "/usr/bin/java -jar /home/pi/Lights/SwitchLights.jar off upHall";

        String upHallDim = "0 21 " + Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + " " +
                + (Calendar.getInstance().get(Calendar.MONTH) + 1) + " * " + "/usr/bin/java -jar /home/pi/Lights/SwitchLights.jar dim upHall 50";

        //Generate Master times
        int masterOffset1 = (15 + (int)(Math.random() * ((30 - 15) + 1))) * -1;
        Calendar master1 = (Calendar)officialSunset.clone();
        master1.add(Calendar.MINUTE, masterOffset1);

        int masterOffset2 = (-15 + (int)(Math.random() * ((15 + 15) + 1))) * -1;
        Calendar master2 = Calendar.getInstance();
        master2.set(Calendar.MINUTE, 30);
        master2.set(Calendar.HOUR_OF_DAY, 23);
        master2.add(Calendar.MINUTE, masterOffset2);

        String masterOn = master1.get(Calendar.MINUTE) + " " + master1.get(Calendar.HOUR_OF_DAY)
                + " " + master1.get(Calendar.DAY_OF_MONTH) + " " +  (master1.get(Calendar.MONTH) + 1)
                + " * " + "/usr/bin/java -jar /home/pi/Lights/SwitchLights.jar on master";

        String masterOff = master2.get(Calendar.MINUTE) + " " + master2.get(Calendar.HOUR_OF_DAY)
                + " " + master2.get(Calendar.DAY_OF_MONTH) + " " +  (master2.get(Calendar.MONTH) + 1)
                + " * " + "/usr/bin/java -jar /home/pi/Lights/SwitchLights.jar off master";

        try {
            PrintWriter writer = new PrintWriter("Schedule.txt", "UTF-8");
            writer.println(frontOn);
            writer.println(frontOff);
            writer.println(frontDim);
            writer.println(backOn1);
            writer.println(backOn2);
            writer.println(backOff1);
            writer.println(backOff2);
            writer.println(landscapeOn);
            writer.println(landscapeOff);
            writer.println(upHallOn);
            writer.println(upHallOff);
            writer.println(upHallDim);
            writer.println(masterOn);
            writer.println(masterOff);
            writer.close();

        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
