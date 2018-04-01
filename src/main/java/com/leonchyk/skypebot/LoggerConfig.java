package com.leonchyk.skypebot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

public abstract class LoggerConfig {

    public static void setupLogger() {
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("log4j.properties"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        PropertyConfigurator.configure(props);
    }
}
