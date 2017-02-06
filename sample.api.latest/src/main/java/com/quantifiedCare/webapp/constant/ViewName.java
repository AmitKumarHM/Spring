/*
 * 
 */
package com.quantifiedCare.webapp.constant;

// TODO: Auto-generated Javadoc
/**
 * This enum contains the constant for tiles view definitions.
 * @author Arvind
 *
 */
public enum ViewName {

    /*--------------------------------------------
    |                START                       |
    ============================================*/

    /** The user view. */
    LOGIN("login"),

    /** The default. */
    DEFAULT("default"),

    /** The app error view. */
    APP_ERROR_VIEW("unauthorizedView"),

    /** The ajax error view. */
    AJAX_ERROR_VIEW("ajaxErrorView"),

    /** The ajax error view. */
    TX_ABORT_ERROR_VIEW("txAbortErrorView"),

    /** The error view. */
    ERROR_VIEW("errorView"),
    
    /** The USER_VIEW view. */
    USER_VIEW("userView"),
    
    /** The RESET_PASSWORD_VIEW view. */
    RESET_PASSWORD_VIEW("resetPasswordView"),
    
    /** The LNKE_EXPIRED_VIEW view. */
    LNKE_EXPIRED_VIEW("linkExpireddView"),
    
    /** The FORGOT_PSSWORD_VIEW view. */
    FORGOT_PSSWORD_VIEW("forgotPasswordView"),
    
    /** The login view. */
    LOGOUT("logoutView"),
    
    /** The OPEN_ADMIN_VIEW view. */
    OPEN_ADMIN_VIEW("openAdminView"),
   
    /** The OPEN_ORGANIZATION_VIEW view. */
    OPEN_ORGANIZATION_VIEW("openOrganizationView"),
    
    /** The OPEN_ORGANIZATION_VIEW view. */
    EDIT_ORGANIZATION_VIEW("editOrganizationView"),
    
    /** The OPEN_DASHBOARD_VIEW view. */
    OPEN_DASHBOARD_VIEW("openDashboardView"),
    
    /** The MY_ORGANIZATION_VIEW view. */
    MY_ORGANIZATION_VIEW("myOrganizationView"),
    
    /** The MY_ORGANIZATION_VIEW view. */
    MY_ORGANIZATION_ERROR_VIEW("myOrganizationErrorView"),
    
    /** The ALL_ORGANIZATION_VIEW view. */
    ALL_ORGANIZATION_VIEW("allOrganizationView"),
    
    /** The ALL_ORGANIZATION_VIEW view. */
    ALL_AJAX_ORGANIZATION_VIEW("allAjaxOrganizationView"),
    
    /** The OPEN_ORGANIZATION_DEFAULT_PAGE view. */
    OPEN_ORGANIZATION_DEFAULT_PAGE("dashboardView"),
    
    /** The PATIENT_VIEW view. */
    PATIENT_VIEW("patientView"),
    
    /** The PATIENT_LIST_VIEW view. */
    PATIENT_LIST_VIEW("patientListView"),
    
    /** The ALL_PATIENT_LIST_VIEW view. */
    ALL_PATIENT_LIST_VIEW("allPatientListView"),
    
    /** The ALL_AJAX_PATIENT_LIST_VIEW view. */
    ALL_AJAX_PATIENT_LIST_VIEW("allAjaxPatientListView"),
    
    /** The EDIT_PATIENT_VIEW view. */
    EDIT_PATIENT_VIEW("editPatientView"),
    
    /** The ALREADY_PATIENT_ERROR_VIEW view. */
    ALREADY_PATIENT_ERROR_VIEW("alreadyPatientAddedErrorView");
    
    /** The String variable to hold tile definition name. */
    private String viewName;

    /**
     * Instantiates a new view names.
     * 
     * @param viewName
     *            the view name
     */
    private ViewName(final String viewName) {
        this.viewName = viewName;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return this.viewName;
    }

    /**
     * This method returns the view name configured in tiles definition configuration file.
     * 
     * @return the view name
     */
    public String getViewName() {
        return this.viewName;
    }

    /**
     * This method check the given view name with the values of all enums defined in the class.
     * 
     * @param viewName
     *            the view name
     * @return true if one of the enum's value matched with the given parameter, otherwise false.
     */
    public static Boolean isViewNameExist(String viewName) {
        boolean isFound = false;
        for (ViewName name : ViewName.values()) {
            if (name.getViewName().equals(viewName)) {
                isFound = true;
                break;
            }
        }
        return isFound;
    }
}
