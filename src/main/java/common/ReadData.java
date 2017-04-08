package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ReadData {
    public static String getValue(String key) {
        return (String) Singleton.initialize().get(key);
    }

    private static class Singleton {
        public final static Properties configProp = new Properties();

        static {
            try {
                InputStream in = new FileInputStream(file().getPath());
                configProp.load(in);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        private static Properties initialize() {
            return configProp;
        }

        private static File file() {
            return new File("src/test/resources/DataStore.properties");
        }
    }
}