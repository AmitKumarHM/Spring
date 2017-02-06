
package com.quantifiedCare.util.common;

import java.lang.reflect.Array;

/**
 * Collected methods which allow easy implementation of <code>hashCode</code> in custom classes.
 * 
 * Example use case:
 * 
 * <pre>
 * public int hashCode() {
 *     int result = HashCodeUtil.SEED;
 *     // collect the contributions of various fields
 *     result = HashCodeUtil.hash(result, fPrimitive);
 *     result = HashCodeUtil.hash(result, fObject);
 *     result = HashCodeUtil.hash(result, fArray);
 *     return result;
 * }
 * </pre>
 */
/**
 * @author arvind
 *
 */
public class QuantifiedCareHashCodeUtil {

    /**
     * An initial value for a <code>hashCode</code>, to which is added contributions from fields. Using a non-zero value
     * decreases collisions of <code>hashCode</code> values.
     */
    public static final int SEED = 23;

    /** The odd prime number. */
    private static final int ODD_PRIME_NO = 37;

    /**
     * Instantiates a new RMC constant.
     */
    private QuantifiedCareHashCodeUtil() {
    }

    /**
     * booleans.
     * 
     * @param aSeed
     *            the a seed
     * @param aBoolean
     *            the a boolean
     * @return the int
     */
    public static int hash(int aSeed, boolean aBoolean) {
        return QuantifiedCareHashCodeUtil.firstTerm(aSeed) + (aBoolean ? 1 : 0);
    }

    /**
     * chars.
     * 
     * @param aSeed
     *            the a seed
     * @param aChar
     *            the a char
     * @return the int
     */
    public static int hash(int aSeed, char aChar) {
        return QuantifiedCareHashCodeUtil.firstTerm(aSeed) + aChar;
    }

    /**
     * Provides hash code of integer type argument.Implementation Note Note that byte and short are handled by this
     * method, through implicit conversion.
     * 
     * @param aSeed
     *            the a seed
     * @param aInt
     *            the a int
     * @return the int
     */
    public static int hash(int aSeed, int aInt) {
        return QuantifiedCareHashCodeUtil.firstTerm(aSeed) + aInt;
    }

    /**
     * longs.
     * 
     * @param aSeed
     *            the a seed
     * @param aLong
     *            the a long
     * @return the int
     */
    public static int hash(int aSeed, long aLong) {
        return QuantifiedCareHashCodeUtil.firstTerm(aSeed) + (int) (aLong ^ (aLong >>> 32));
    }

    /**
     * floats.
     * 
     * @param aSeed
     *            the a seed
     * @param aFloat
     *            the a float
     * @return the int
     */
    public static int hash(int aSeed, float aFloat) {
        return QuantifiedCareHashCodeUtil.hash(aSeed, Float.floatToIntBits(aFloat));
    }

    /**
     * doubles.
     * 
     * @param aSeed
     *            the a seed
     * @param aDouble
     *            the a double
     * @return the int
     */
    public static int hash(int aSeed, double aDouble) {
        return QuantifiedCareHashCodeUtil.hash(aSeed, Double.doubleToLongBits(aDouble));
    }

    /**
     * <code>aObject</code> is a possibly-null object field, and possibly an array.
     * 
     * If <code>aObject</code> is an array, then each element may be a primitive or a possibly-null object.
     * 
     * @param aSeed
     *            the a seed
     * @param aObject
     *            the a object
     * @return the int
     */
    public static int hash(int aSeed, Object aObject) {
        int result = aSeed;
        if (aObject == null) {
            result = QuantifiedCareHashCodeUtil.hash(result, 0);
        } else if (!QuantifiedCareHashCodeUtil.isArray(aObject)) {
            result = QuantifiedCareHashCodeUtil.hash(result, aObject.hashCode());
        } else {
            int length = Array.getLength(aObject);
            for (int idx = 0; idx < length; ++idx) {
                Object item = Array.get(aObject, idx);
                // recursive call!
                result = QuantifiedCareHashCodeUtil.hash(result, item);
            }
        }
        return result;
    }

    /**
     * First term.
     * 
     * @param aSeed
     *            the a seed
     * @return the int
     */
    private static int firstTerm(int aSeed) {
        return ODD_PRIME_NO * aSeed;
    }

    /**
     * Checks if is array.
     * 
     * @param aObject
     *            the a object
     * @return true, if is array
     */
    private static boolean isArray(Object aObject) {
        return aObject.getClass().isArray();
    }
}
