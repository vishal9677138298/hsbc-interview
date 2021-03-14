package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
    private static String path;
    private static final Properties PROPERTIES = new Properties();
    private String propertyName;

    public PropertyLoader(String propertyName, String path){
        this.propertyName = propertyName;
        this.path = path;
        try(InputStream in = PropertyLoader.class.getClassLoader().getResourceAsStream(path)){
            PROPERTIES.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String name){
        String key = propertyName + "." + name;
        String property = PROPERTIES.getProperty(key);
        return property;
    }
}
