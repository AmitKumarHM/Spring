package com.bluepiit.etl.utils.constant;


/**
 * The Constant contains common constants. This class is used as a common container which contains all the QuantifiedCareConstant
 * application specific constants.
 *
 * This class is declared as final, hence it can't be extended. Private constructor is defined to prevent instantiation
 * of this class.
 */
/**
 * @author Amit
 *
 */
public final class DelhiveryEtlConstants {


	
	/*------------------------------------------
    |           Redis Config                       |
    ===========================================*/
	
	/** The URL for Redis. */
    public static final String REDIS_URL = "delhivery-dw.4w13el.ng.0001.euw1.cache.amazonaws.com";
	
    /** The URL for Redis. */
    public static final String LOCALHOST_URL = "localhost";
    
    /** The Port Number for Redis. */
    public static final int REDIS_PORT = 6379;
    
    /** The URL. */
    public static final String URL = "URL";
    
    /** The Port Number.*/
    public static final String PORT = "PORT";
    
    /** The Configuration for Redis. */
    public static final String REDIS_CONFIG_FILE ="config.properties";
    
	/*------------------------------------------
    |           S3 Bucket                       |
    ===========================================*/
	
	/** The name of the bucket in S3. */
    public static final String BUCKET_NAME = "delhivery";
	
    /*-------------------------------------------
    |           File Constants               |
    ===========================================*/

	/** The extension of a file for JSON file. */
    public static final String JSON_EXTENSIION = ".json";
    
    /*-------------------------------------------
    |           Kinesis Firehose               |
    ===========================================*/

	/** The extension of a file for JSON file. */
    public static final String  DISPATCH_KINESIS_FIREHOSE= "delhivery-development-dispatch";

    /*-------------------------------------------
    |           Compiler Warning                |
    ===========================================*/
    
    /** The Constant Unused for suppressing warning.*/
    public static final String UNCHECKED = "unchecked";
    
    /** The Constant raw types for suppressing warning.*/
    public static final String RAW_TYPES = "rawtypes";
    
    /** The Constant raw types for suppressing warning.*/
    public static final String UNUSED = "unused";
    
    /** The Constant raw types for suppressing warning.*/
    public static final String HIDING = "hiding";
    
    /*-------------------------------------------
    |           General Constants               |
    ===========================================*/
    
    /** The Constant YES. */
    public static final String YES = "yes";

    /** The Constant NO. */
    public static final String NO = "no";

    /** The Constant 'Y' for YES. */
    public static final String YES_INDICATOR = "Y";

    /** The Constant 'N' for NO. */
    public static final String NO_INDICATOR = "N";

    /** The Constant NO. */
    public static final String NONE = "none";

    /** The Constant HASH. */
    public static final String HASH = "#";

    /** The Constant DOT. */
    public static final String DOT = ".";

    /** The Constant BACKSLASH. */
    public static final String BACKSLASH = "\\";

    /** The constant FORWARD_SLSH. */
    public static final String FORWARD_SLASH = "/";

    /** The Constant BACKSLASH. */
    public static final char PLUS = '+';

    /** The Constant EMPTY STRING. */
    public static final String EMPTY_STRING = "";

    /** The Constant SPACE. */
    public static final String SPACE = " ";

    /*-------------------------------------------
    |           Operator Constants             |
    ===========================================*/

    /** Constant for equals. */
    public static final String EQUALS_TO = "=";

    /** Constant for forward slash. */
    public static final String FWD_SLASH = "/";

    /** Constant for Comma. */
    public static final String COMMA = ",";

    /** The Constant COMMA_CHAR. */
    public static final char COMMA_CHAR = ',';

    /** Constant for SemiColon. */
    public static final String SEMI_COLON = ";";

    /** Constant for Amp. */
    public static final String AMP = "&";

    /** Constant for Percentage. */
    public static final String PERCENTAGE = "%";

    /** Constant for Percentage. */
    public static final String PERCENT_SYMBOL = "%";

    /** Constant for start bracket. */
    public static final String START_BRACKET = "(";

    /** Constant for end bracket. */
    public static final String END_BRACKET = ")";

    /** The Constant SINGLE_QUOTE. */
    public static final String SINGLE_QUOTE = "'";

    /** The Constant HYPHEN. */
    public static final String HYPHEN = "-";

    /** The Constant START_CURLY_BRACES. */
    public static final String START_CURLY_BRACES = "{";

    /** The Constant END_CURLY_BRACES. */
    public static final String END_CURLY_BRACES = "}";

    /** The Constant START_CURLY_BRACES. */
    public static final String START_SQUARE_BRACES = "[";

    /** The Constant END_CURLY_BRACES. */
    public static final String END_SQUARE_BRACES = "]";

    /** Constant for pipe. */
    public static final String PIPE = "|";

    /** Constant for pipe. */
    public static final String PIPE_SPLIT = "\\|";

    /** Constant for colon. */
    public static final String COLON = ":";

    /** The Constant DOUBLE_COLON. */
    public static final String DOUBLE_COLON = "::";

    /** Constant for unescape. */
    public static final String UNESCAPE = "~";

    /** The Constant ASTERIX. */
    public static final String ASTERIX = "*";

    /** The Constant UNDERSCORE. */
    public static final String UNDERSCORE = "_";

    /** The constant SPACE_DOUBLE_COLON_SPACE. */
    public static final String SPACE_DOUBLE_COLON_SPACE = " :: ";

    /** The constant SPACE_HYPHEN_SPACE. */
    public static final String SPACE_HYPHEN_SPACE = " - ";

    /*-------------------------------------------
    |           Date Constants             |
    ===========================================*/

     
    /** date format for view */
    public static final String DATE_FORMAT_TO_VIEW = "ddMMMyy";

    /** date format. */
    public static final String DATE_FORMAT = "dd.MM.yyyy HH:mm";

    /** simple date format. */
    public static final String SIMPLE_DATE_FORMAT = "dd.MM.yyyy";

    /** simple time format. */
    public static final String SIMPLE_TIME_FORMAT = "HH:mm";

    /** date format appended when exporting a file. */
    public static final String DATE_FORMAT_FOR_FILE_EXPORT = "yyyyMMdd";

    /** date format for my flights dto. */
    public static final String DATE_FORMAT_MY_FLIGHTS_DTO = "yyyy-MM-dd";

    /** The Constant DATE_FORMAT_MSG_MARQUEE. */
    public static final String DATE_FORMAT_MSG_MARQUEE = "dd.MM.yyyy";

    /** constant for teradata date format. */
    public static final String TD_DATE_FORMAT = "yyyy-MM-dd";

    /** constant for teradata date time format. */
    public static final String TD_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    /** constant for teradata date time format. */
    public static final String TD_DATE_TIME_FORMAT_NO_SECONDS = "yyyy-MM-dd-HH";

    /** The Constant TD_FETCH_DATE_FORMAT. */
    public static final String TD_FETCH_DATE_FORMAT = "dd-MM-yyyy";

    /** The Constant TD_FETCH_DATE_FORMAT. */
    public static final String TD_YEAR_MONTH_FORMAT = "yyyyMM";

    /** date format for events e.g 15APR2012 */
    public static final String DATE_FORMAT_FOR_EVENTS = "ddMMMyyyy";

    /** date format for view */
    public static final String[] ALL_DATE_FORMAT = {DATE_FORMAT_TO_VIEW,DATE_FORMAT,SIMPLE_DATE_FORMAT,SIMPLE_TIME_FORMAT,DATE_FORMAT_FOR_FILE_EXPORT,DATE_FORMAT_MY_FLIGHTS_DTO,DATE_FORMAT_MSG_MARQUEE,TD_DATE_FORMAT,TD_DATE_TIME_FORMAT,TD_DATE_TIME_FORMAT_NO_SECONDS,TD_FETCH_DATE_FORMAT,TD_YEAR_MONTH_FORMAT,DATE_FORMAT_FOR_EVENTS};
  
    
    /*-------------------------------------------
    |           String Constants                |
    ===========================================*/
    /** The Constant to represent null. */
    public static final String NULL_STRING = "null";
    
    /** The Constant SPLIT_WORD_INTO_LETTER_REGEX. */
    public static final String SPLIT_WORD_INTO_LETTER_REGEX = "(?!^)";

	public static final Object STRING_UNDERSCORE = "_";

	 /** Descending order. */
    public static final String DESC = "DESC";
    
    /** The Constant MINUS_ONE. */
	public static final int MINUS_ONE = -1;
	
	public static final int START_INDEX = 0;
	
	public static final int FIRST_INDEX = 1;
	
	public static final int TEN_INDEX = 10;
	
    public static final String NOT_AVAILABLE = "NA";
    
    public static final String DOLLOR_SIGN = "$";
    
    public static final String AT_SIGN = "@";
    
    public static final String NEW_LINE = "\n";
    
    private DelhiveryEtlConstants() {   
    }
}
