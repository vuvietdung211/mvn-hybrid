package commons;

import java.io.File;

public class GlobalConstants {
	
	
	public static final String FILE_SEPERATOR = File.separator;
	public static final String PORTAL_DEV_URL = "https://demo.nopcommerce.com/";
	public static final String ADMIN_DEV_URL = "https://admin-demo.nopcommerce.com/";
	
	public static final String PORTAL_TEST_URL = "https://demo.nopcommerce.com/";
	public static final String ADMIN_TEST_URL = "https://admin-demo.nopcommerce.com/";
	
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String UPLOAD_FOLDER_PATH = getFolderSeperator("uploadFiles");
	public static final String DOWNLOAD_FOLDER_PATH = getFolderSeperator("downloadfiles");

	public static final long LONG_TIMEOUT = 30;
	public static final long SHORT_TIMEOUT = 5;
	
	public static final String EXTENT_TEST = getFolderSeperator("HtmlExtent");
	public static final String JAVA_VERSION = System.getProperty("java.version");
	
	public static String getFolderSeperator(String folderName) {
		return PROJECT_PATH + FILE_SEPERATOR + folderName + FILE_SEPERATOR;
	}
	
	public static void main(String[] args) {
		System.out.println(EXTENT_TEST);
	}
	
}
