/**
 * 
 */
package com.quantifiedCare.util.common;

import java.io.File;
import java.math.BigInteger;

/**
 * @author arvind
 *
 */
public class CommonUtil {

	
	/*It will be Convert String to int ,, but not proper working*/
	
	public  int strToInt( String str ){
	    int i = 0;
	    int num = 0;
	    boolean isNeg = false;

	    //Check for negative sign; if it's there, set the isNeg flag
	    if (str.charAt(0) == '-') {
	        isNeg = true;
	        i = 1;
	    }

	    //Process each character of the string;
	    while( i < str.length()) {
	        num *= 10;
	        num += str.charAt(i++) - '0'; //Minus the ASCII code of '0' to get the value of the charAt(i++).
	    }

	    if (isNeg)
	        num = -num;
	    return num;
	}
	
	
	/*It will be Convert String to int proper working*/
	
	 public Integer str2Int(String str) {
	    Integer result = null;
	    if (null == str || 0 == str.length()) {
	        return null;
	    }
	    try {
	        result = Integer.parseInt(str);
	    } 
	    catch (NumberFormatException e) {
	        String negativeMode = "";
	        if(str.indexOf('-') != -1)
	            negativeMode = "-";
	        str = str.replaceAll("-", "" );
	        if (str.indexOf('.') != -1) {
	            str = str.substring(0, str.indexOf('.'));
	            if (str.length() == 0) {
	                return (Integer)0;
	            }
	        }
	        String strNum = str.replaceAll("[^\\d]", "" );
	        if (0 == strNum.length()) {
	            return null;
	        }
	        result = Integer.parseInt(negativeMode + strNum);
	    }
	    return result;
	}
	 
	 
	 /*It will be get Ascii Value proper working*/
	 
	 public BigInteger prepareToAsciiValue(String inputToAscii){
			//String str = "abc,;'!@#$%^&*()_+\\///////////\\.";  // or anything else

		    StringBuilder sb = new StringBuilder();
		    for (char c : inputToAscii.toCharArray())
		    sb.append((int)c);
		   // String asciiValue = new String(sb.toString());
		    BigInteger asciiValue = new BigInteger(sb.toString());
		    //System.out.println(mInt);
		    //Double asciiValue = new Double(sb.toString());
		    return asciiValue;
		}
	
	 public void createDirectory() {

			String directoryName = ""; // get directory name from form
			File theDir = new File("new folder");

			// if the directory does not exist, create it
			if (!theDir.exists()) {
				System.out.println("creating directory: " + directoryName);
				boolean result = false;

				try {
					theDir.mkdir();
					result = true;
				} catch (SecurityException se) {
					// handle it
				}
				if (result) {
					System.out.println("DIR created");
				}
			}

		}
	 
}
