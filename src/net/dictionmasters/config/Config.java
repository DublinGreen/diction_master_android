package net.dictionmasters.config;

public class Config {
	
	public static final short STANDARD_TOAST_TIMEOUT = 5000;
	public static final short LONG_TOAST_TIMEOUT = 9000;

	public static final String FILES_DIR = "DICTION_MASTER";
	public static final String APP_NAME = "Diction Master";

	public static final String ONLINE_RESOURCES_FOLDER = "XML/";
	public static final String ONLINE_RESOURCES_AUDIO_FOLDER = "AUDIOS/";
	public static final String ONLINE_RESOURCES_VIDEO_FOLDER = "VIDEOS/";
	public static final String ONLINE_RESOURCES_UPLOADS_FOLDER = "UPLOADS/";

	public static final String DOMAIN = "http://dictionmasters.net/index.php/";
	public static final String SERVER = "http://dictionmasters.net/";
	public static final String DOMAIN_EMAIL = "info@dictionmasters.net";
	public static final String SERVER_ERROR_HANDLER = "/error/";//class then message
	public static final String APP_DOWNLOAD_LINK = "http://dictionmasters.net";
	public static final String PLAY_STORE_LINK = "https://play.google.com/store/apps/details?id=";
	public static final String PLAY_STORE_MARKET_ID = "market://details?id=";
	public static final String SMS_MESSAGE = "Hi, i am using diction master. It an app that helps with diction. Get it here " + APP_DOWNLOAD_LINK;
	public static final String EMAIL_MESSAGE = "Hi, i am using diction master. It an app that helps with diction. Get it here " + APP_DOWNLOAD_LINK;;

	public static final String LOG_TAG = "GREEN";
	public static final String VOLLEY_STRING_REQUEST_TAG = "diction_masters_request_strings";

	public static final String SERVICE_TITLE = "DICTION MASTERS";
	public static final String SERVICE_STARTED = "Application started";
	public static final String SERVICE_ON_DESCRIPTION = "Online";
	public static final String SERVICE_OFF_DESCRIPTION = "Offline";
	public static final String VERSION_NUMBER = "1.0"; 

	public static final String NO_INTERNET_REG = "Network error. Check your network connection and try again.";
	public static final String RESEND_REG_CODE = "Code has been resent.";

	public static final String WORDS_ROOT_XML_TAG = "words";
	public static final String WORDS_XML_TAG_INSIDE_ROOT_TAG = "word";
	
	public static final int totalWords = 5000;
	public static final String SHARED_PREF_STRING_NAME = "APP_SETTINGS";
	
}
