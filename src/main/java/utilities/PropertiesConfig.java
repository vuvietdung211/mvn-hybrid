package utilities;

import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class PropertiesConfig {
	private static PropertiesConfig instance = null;
	private Properties properties = null;

	private PropertiesConfig() {
		properties = new Properties();
		try {
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("env-staging.properties");
			properties.load(inputStream);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static synchronized PropertiesConfig getProperties() {
		if (instance == null) {
			instance = new PropertiesConfig();
		}
		return instance;
	}

	public String getValue(String key) {
		return this.properties.getProperty(key, String.format("The key %s does not exists!", key));
	}
}
