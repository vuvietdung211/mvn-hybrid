package commons;

import java.io.File;

public class GlobalConstants {
	
	//System info
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String OS_NAME = System.getProperty("os.name");
	public static final String JAVA_VERSION = System.getProperty("java.version");
	public static final String FILE_SEPARATOR = File.separator;

	
	//App user
	public static final String DEV_USER_URL = "https://demo.nopcommerce.com/";
	public static final String TESTING_USER_URL = "https://demo.nopcommerce.com/";
	public static final String STAGING_USER_URL = "https://demo.nopcommerce.com/";
	public static final String LIVE_USER_URL = "https://demo.nopcommerce.com/";
	
	// App Admin
	public static final String DEV_ADMIN_URL = "https://admin-demo.nopcommerce.com/";
	public static final String TESTING_ADMIN_URL = "https://admin-demo.nopcommerce.com/";
	public static final String STAGING_ADMIN_URL = "https://admin-demo.nopcommerce.com/";
	public static final String LIVE_ADMIN_URL = "https://admin-demo.nopcommerce.com/";
	
	//Download/upload file
	public static final String 	UPLOAD_FOLDER_PATH = PROJECT_PATH +  File.separator + "uploadFiles" + File.separator;
	public static final String 	DOWNLOAD_FOLDER_PATH = PROJECT_PATH + File.separator + "/downloadfiles";

	// Timeout
	public static final long 	LONG_TIMEOUT = 30;
	public static final long 	SHORT_TIMEOUT = 5;

	//Data test
	public static final String DATA_PATH = PROJECT_PATH + FILE_SEPARATOR + "testdata" + FILE_SEPARATOR;
	
	// Retry case failed
	public static final int RETRY_NUMBER =3;
	
	//Browser log/ extension
	public static final String BROSER_LOG_PATH = PROJECT_PATH + "/browserLogs/";
	public static final String EXTENT_TEST = PROJECT_PATH + "/htmlExtent/";



}
