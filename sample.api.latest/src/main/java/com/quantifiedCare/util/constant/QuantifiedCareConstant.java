package com.quantifiedCare.util.constant;


/**
 * The Constant contains common constants. This class is used as a common container which contains all the QuantifiedCareConstant
 * application specific constants.
 *
 * This class is declared as final, hence it can't be extended. Private constructor is defined to prevent instantiation
 * of this class.
 */
/**
 * @author Arvind
 *
 */
public final class QuantifiedCareConstant {


    /*-------------------------------------------
    |           General Constants               |
    ===========================================*/

    /** The default sort direction of entities. */
    public static final String DEFAULT_SORT_DIRECTION = "ASC";

    /** The Constant XML_HTTP_REQ_STR. */
    public static final String XML_HTTP_REQ_STR = "XMLHttpRequest";

    /** The Constant AJAX_REQ_IND. */
    public static final String AJAX_REQ_IND = "X-Requested-With";

    /** The Constant SCHEMA_NAME_KEY. */
    public static final String SCHEMA_NAME_KEY = "schemaName";

    /** The Constant BACKEND_SCHEMA_NAME_KEY. */
    public static final String BACKEND_SCHEMA_NAME_KEY = "backendSchemaName";

    /** The constant REDIRECT. */
    public static final String REDIRECT = "redirect:/";

    /** The Constant DEFAULT_PAGE_NO . */
    public static final int DEFAULT_PAGE_NO = 0;

    /** The Constant DEFAULT_PAGE_SIZE . */
    public static final int DEFAULT_PAGE_SIZE = 20;

    /** The Constant PAGE_ITEMS. */
    public static final String PAGE_ITEMS = "pageItems";

    /** The Constant DESCENDING_DIRECTION. */
    public static final String DESCENDING_DIRECTION = "DESC";

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
    public static final String BACKSLASH = "\"";

    /** The constant FORWARD_SLSH. */
    public static final String FORWARD_SLASH = "/";

    /** The Constant BACKSLASH. */
    public static final char PLUS = '+';

    /** The Constant EMPTY STRING. */
    public static final String EMPTY_STRING = "";

    /** The Constant SPACE. */
    public static final String SPACE = " ";

    /** The Constant TRUE. */
    public static final String TRUE = "True";

    /** The Constant FALSE. */
    public static final String FALSE = "False";

    /** The Constant SUCCESS_MESSAGE. */
    public static final String SUCCESS_MESSAGE = "successMessage";

    /** The Constant WARNING_MESSAGE. */
    public static final String WARNING_MESSAGE = "warningMessage";

    /** The Constant SUCCESS. */
    public static final String SUCCESS = "success";

    /** The Constant ERRORS. */
    public static final String ERRORS = "errors";

    /*-------------------------------------------
    |           Session Map Key Constants        |
    ===========================================*/

    /** The Constant USER_KEY. */
    public static final String USER_KEY = "sessionUser";

    /** Key for logged in user id. */
    public static final String LOGGED_IN_USER_ID = "loggedInUserId";

    /** The constant for All. */
    public static final String ALL = "ALL";
    
    /** Salt to be used in password encoding. */
    public static final String SALT = "quantifiedcaresalt@$@4&#%^$*";

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

    /** The Constant TD_FETCH_DATE_FORMAT. */
    public static final String TD_FETCH_DATE_FORMAT = "dd-MM-yyyy";

    /** The Constant TD_FETCH_DATE_FORMAT. */
    public static final String TD_YEAR_MONTH_FORMAT = "yyyyMM";

    /** date format for events e.g 15APR2012 */
    public static final String DATE_FORMAT_FOR_EVENTS = "ddMMMyyyy";

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
	
    public static final String NOT_AVAILABLE = "NA";
    
    public static final String DOLLOR_SIGN = "$";
    
    public static final String AT_SIGN = "@";
    
    /**
     * Instantiates a new QuantifiedCareConstant.
     */
    private QuantifiedCareConstant() {
        //throw new InstantiationException("QuantifiedCareConstant");
    }
}
