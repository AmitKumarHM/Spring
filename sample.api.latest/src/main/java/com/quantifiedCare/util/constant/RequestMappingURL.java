package com.quantifiedCare.util.constant;

/**
 * The Class RequestMappingURL.
 */
/**
 * @author Arvind
 * 
 */
public final class RequestMappingURL {

	/** Login page URL. */
	public static final String LOGIN_VIEW_URL = "login";

	/** Login success URL. */
	public static final String LOGIN_SUCCESS_URL = "organization/authenticated" ;  //"loginSuccess";

	/** Login failed URL. */
	public static final String USER_FAILURE_URL = "loginFailed";
	
	/** The check user credentials url. */
	public static final String CHECK_USER_CREDENTIAL_URL = "check/credentials";
	
	/** The Constant APP_ERROR_VIEW_URL. */
	public static final String APP_ERROR_VIEW_URL = "appError";
	
	/** The Constant APP_ERROR_VIEW_URL. */
	public static final String TRANSACTION_ABORT_ERROR_VIEW_URL = "txAbortError";

	/** The Constant AJAX_ERROR_VIEW_URL. */
	public static final String AJAX_ERROR_VIEW_URL = "ajaxError";

	/** The Constant JSON_AJAX_ERROR_VIEW_URL. */
	public static final String JSON_AJAX_ERROR_VIEW_URL = "jsonAjaxError";
	
	/** The Constant ERROR_VIEW_URL. */
	public static final String ERROR_VIEW_URL = "error";
	
	/** The Constant EMAIL_SEND_VIEW_URL. */
	public static final String EMAIL_SEND_VIEW_URL= "forgot/forgotlockerkey";
	
	/** The Constant ADMIN_FORGOT_PWD_URL. */
	public static final String ADMIN_FORGOT_PWD_URL= "forgot/forgotpassword";
	
	/** The Constant RESET_PASSWORD_VIEW. */
	public static final String RESET_PASSWORD_VIEW_URL = "resetpassword/{user_id}/{forgottoken}";
	
	/** The Constant RESET_PASSWORD_SUBMIT. */
	public static final String RESET_PASSWORD_SUBMIT = "resetpasswordsubmit";
	
	/** LOGOUT_VIEW_URL page URL. */
	public static final String LOGOUT_VIEW_URL = "logout";
	
	/** The Constant FETCH_ALL_ORGANIZATION_URL. */
	public static final String FETCH_ALL_ORGANIZATION_URL= "user/fetchAllOrganization";
	
	/** The Constant FETCH_AJAX_ALL_ORGANIZATION_URL. */
	public static final String FETCH_AJAX_ALL_ORGANIZATION_URL= "user/fetchAjaxAllOrganization";
	
	/** The Constant ADD_MY_ORGANIZATION_URL. */
	public static final String ADD_MY_ORGANIZATION_URL= "user/addMyOrganization";
	
	/** The Constant LOCKER_CREATION_VIEW. */
	public static final String LOCKER_CREATION_VIEW = "user/lockercreation";
	
	/** The Constant ADD_ORGANIZATION_URL. */
	public static final String ADD_ORGANIZATION_URL= "user/addOrganization";
	
	/** The Constant ADD_ORGANIZATION_URL. */
	public static final String EDIT_ORGANIZATION_URL= "user/editOrganization/{id}";
	
	/** The Constant DELETE_ORGANIZATION_URL. */
	public static final String DELETE_ORGANIZATION_URL= "user/deleteOrganization/{id}";
	
	/** The Constant CHECK_EMAIL_VALIDATION. */
	public static final String CHECK_EMAIL_VALIDATION = "user/checkemailvalidation";
	
	/** The Constant DASHBOARD_URL. */
	public static final String DASHBOARD_URL= "user/dashboard";
	
	/** The Constant FETCH_ALL_ORGANIZATION_URL_BY_MENU. */
	public static final String FETCH_ALL_ORGANIZATION_URL_BY_MENU= "user/fetchAllOrganizationbymenu";
	
	/** The Constant OPEN_PATIENT_VIEW_URL. */
	public static final String OPEN_PATIENT_VIEW_URL= "patient/patientviewurl";
	
	/** The Constant ADD_PATIENT_SUBMIT_URL. */
	public static final String ADD_PATIENT_SUBMIT_URL= "patient/patientsubmiturl";
	
	/** The Constant GET_ALL_CARE_GIVER_VIA_ORGANIZATION_URL. */
	public static final String GET_ALL_CARE_GIVER_VIA_ORGANIZATION_URL= "organization/allcaregiver";
	
	/** The Constant VIEW_ALL_PATIENT_SUBMIT_URL. */
	public static final String VIEW_ALL_PATIENT_SUBMIT_URL= "view/allpatientsubmiturl";
	
	/** The Constant VIEW_AJAX_ALL_PATIENT_SUBMIT_URL. */
	public static final String VIEW_AJAX_ALL_PATIENT_SUBMIT_URL= "view/allajaxpatientsubmiturl";
	
	/** The Constant DELETE_PATIENT_SUBMIT_URL. */
	public static final String DELETE_PATIENT_SUBMIT_URL= "delete/deletepatientsubmiturl";
	
	/** The Constant EDIT_PATIENT_VIEW_URL. */
	public static final String EDIT_PATIENT_VIEW_URL= "edit/editpatientviewurl";
	
	/** The Constant UPDATE_PATIENT_SUBMIT_URL. */
	public static final String UPDATE_PATIENT_SUBMIT_URL= "update/updatepatientsubmiturl";
	
	/** The Constant UPDATE_ORGANIZATIONS_SUBMIT_URL. */
	public static final String UPDATE_ORGANIZATIONS_SUBMIT_URL= "updateorg/updateorganization";
	
	/** The Constant SOFT_DELETE_ORGANIZATION_URL. */
	public static final String SOFT_DELETE_ORGANIZATION_URL= "soft/softdeleteorganization";
	
	
	}
