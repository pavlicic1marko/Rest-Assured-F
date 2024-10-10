package com.users.util;

import java.io.InputStream;
import java.util.Properties;

public class UserCredentialsReader {

    private static volatile UserCredentialsReader userPropInstance;

    private UserCredentialsReader(){

    }

    public static synchronized UserCredentialsReader getInstance() {
        if (userPropInstance == null) {
            userPropInstance = new UserCredentialsReader();
        }
        return userPropInstance;
    }

    public String getProperty(String propertyName) {

        Properties prop = new Properties();

        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("user.properties");
            prop.load(inputStream);
            if(prop.getProperty(propertyName) != null){
                return prop.getProperty(propertyName);
            }
        } catch (Exception e) {
            System.out.println("property does not exist");
        }
        return null;
    }

}
