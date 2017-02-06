package com.quantifiedCare.util.common;


/**
 * Collected methods which allow easy implementation of <code>equals</code>.
 * 
 * <em>Arrays are not handled by this class</em>. This is because the <code>Arrays.equals</code> methods should be used
 * for array fields.
 * @author arvind
 *
 */
public class QuantifiedCareEqualsUtil {

    /**
     * Instantiates a new QuantifiedCareEqualsUtil constant.
     */
    private QuantifiedCareEqualsUtil() {
    }

    /**
     * Are equal.
     * 
     * @param aThis
     *            the a this
     * @param aThat
     *            the a that
     * @return true, if successful
     */
    public static boolean areEqual(final boolean aThis, final boolean aThat) {
        return aThis == aThat;
    }

    /**
     * Are equal.
     * 
     * @param aThis
     *            the a this
     * @param aThat
     *            the a that
     * @return true, if successful
     */
    public static boolean areEqual(final char aThis, final char aThat) {
        // System.out.println("char");
        return aThis == aThat;
    }

    /**
     * Are equal.
     * 
     * @param aThis
     *            the a this
     * @param aThat
     *            the a that
     * @return true, if successful
     */
    public static boolean areEqual(final long aThis, final long aThat) {
        /*
         * Implementation Note Note that byte, short, and int are handled by this method, through implicit conversion.
         */
        // System.out.println("long");
        return aThis == aThat;
    }

    /**
     * Are equal.
     * 
     * @param aThis
     *            the a this
     * @param aThat
     *            the a that
     * @return true, if successful
     */
    public static boolean areEqual(final float aThis, final float aThat) {
        // System.out.println("float");
        return Float.floatToIntBits(aThis) == Float.floatToIntBits(aThat);
    }

    /**
     * Are equal.
     * 
     * @param aThis
     *            the a this
     * @param aThat
     *            the a that
     * @return true, if successful
     */
    public static boolean areEqual(final double aThis, final double aThat) {
        // System.out.println("double");
        return Double.doubleToLongBits(aThis) == Double.doubleToLongBits(aThat);
    }

    /**
     * Possibly-null object field.
     * 
     * Includes type-safe enumerations and collections, but does not include arrays. See class comment.
     * 
     * @param aThis
     *            the a this
     * @param aThat
     *            the a that
     * @return true, if successful
     */
    public static boolean areEqual(final Object aThis, final Object aThat) {
        // System.out.println("Object");
        return aThis == null ? aThat == null : aThis.equals(aThat);
    }
}
