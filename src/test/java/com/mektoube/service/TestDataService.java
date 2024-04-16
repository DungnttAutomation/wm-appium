package com.mektoube.service;

import java.io.FileInputStream;
import java.util.Properties;

public class TestDataService {
    public static Properties properties;

    public static void setProperties(String relativeFilePath) {
        properties = new Properties();
        try {
            FileInputStream file = new FileInputStream(System.getProperty("user.dir") + relativeFilePath);
            properties.load(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
