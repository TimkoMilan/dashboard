package com.globallogic.dashboard.old;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//TODO cretae as spring propertyloader
public class PropertyUtil {
    private PropertyUtil() {
        //as it is util class
    }

    public static Properties loadProperties(String propFileName) throws IOException {

        try (InputStream inputStream = PropertyUtil.class.getClassLoader().getResourceAsStream(propFileName)) {
            if (inputStream != null) {
                Properties p = new Properties();
                p.load(inputStream);
                return p;
            }
        }
        throw new RuntimeException("Properties not loaded");
    }
}

