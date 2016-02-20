package com.company;

import sun.misc.BASE64Encoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Main {

    public static void main(String[] args) {
        URL url;
        try {
            // get URL content
            String u = "";

            switch(args[0]) {
                case "on":
                    switch (args[1]) {
                        case "front":
                            u = "http://127.0.0.1:8083/ZWaveAPI/Run/devices[8].instances[0].SwitchMultilevel.Set(99)";
                            break;
                        case "back":
                            u = "http://127.0.0.1:8083/ZWaveAPI/Run/devices[7].instances[0].SwitchBinary.Set(99)";
                            break;
                        case "landscape":
                            u = "http://127.0.0.1:8083/ZWaveAPI/Run/devices[6].instances[0].SwitchBinary.Set(99)";
                            break;
                        case "upHall":
                            u = "http://127.0.0.1:8083/ZWaveAPI/Run/devices[5].instances[0].SwitchMultilevel.Set(99)";
                            break;
                    }
                    break;
                case "off":
                    switch (args[1]) {
                        case "front":
                            u = "http://127.0.0.1:8083/ZWaveAPI/Run/devices[8].instances[0].SwitchMultilevel.Set(0)";
                            break;
                        case "back":
                            u = "http://127.0.0.1:8083/ZWaveAPI/Run/devices[7].instances[0].SwitchBinary.Set(0)";
                            break;
                        case "landscape":
                            u = "http://127.0.0.1:8083/ZWaveAPI/Run/devices[6].instances[0].SwitchBinary.Set(0)";
                            break;
                        case "upHall":
                            u = "http://127.0.0.1:8083/ZWaveAPI/Run/devices[5].instances[0].SwitchMultilevel.Set(0)";
                            break;
                    }
                    break;
                case "dim":
                    switch (args[1]) {
                        case "front":
                            u= "http://127.0.0.1:8083/ZWaveAPI/Run/devices[8].instances[0].SwitchMultilevel.Set(" +
                                    args[2] + ")";
                            break;
                        case "upHall":
                            u = "http://127.0.0.1:8083/ZWaveAPI/Run/devices[5].instances[0].SwitchMultilevel.Set(" +
                                    args[2] + ")";
                            break;
                    }
                    break;
            }

            String login = "admin";
            String password = "klcklc";
            if(!u.equals("")) {
                url = new URL(u);

                URLConnection conn = url.openConnection();
                BASE64Encoder enc = new sun.misc.BASE64Encoder();
                String userpassword = login + ":" + password;
                String encodedAuthorization = enc.encode(userpassword.getBytes());
                conn.setRequestProperty("Authorization", "Basic " + encodedAuthorization);

                // open the stream and put it into BufferedReader
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));

                br.close();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
