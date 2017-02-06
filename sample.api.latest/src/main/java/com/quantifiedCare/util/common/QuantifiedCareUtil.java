package com.quantifiedCare.util.common;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ComparatorUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.comparators.NullComparator;
import org.apache.commons.collections.comparators.ReverseComparator;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import com.quantifiedCare.util.constant.QuantifiedCareConstant;

/**
 * The QuantifiedCareUtil has been roles to common utility class which provides various utility methods
 * specific to this application.
 */
/**
 * @author Arvind
 *
 */
public class QuantifiedCareUtil {

	/** The Full qualified class name constant. */
	@SuppressWarnings("unused")
	private static final String FQCN = QuantifiedCareUtil.class.getName();

	/**
	 * Instantiates a new RMCUtil.
	 */
	private QuantifiedCareUtil() {
		// throw new Exception("QuantifiedCareUtil");
	}

	/**
	 * This method return the username of the current logged in person .
	 * 
	 * @return the Object from session for the given key.
	 */
	public static String getLoggedInUsername() {
		String userName = null;
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (auth instanceof UsernamePasswordAuthenticationToken) {
			userName = ((UserDetails) auth.getPrincipal()).getUsername();
		}
		return userName;
	}

	/**
	 * This method breaks the string into a list on the basis of comma
	 * seperator. And it returns the list of longs.
	 * 
	 * @param inputString
	 *            the input string
	 * @return the numeric list from string
	 */
	public static List<Long> getNumericListFromString(final String inputString) {
		List<String> inputList = Arrays.asList(inputString
				.split(QuantifiedCareConstant.COMMA));
		List<Long> newInputList = new ArrayList<Long>(inputList.size());
		for (String marketId : inputList) {
			if (StringUtils.isNotBlank(marketId)) {
				newInputList.add(Long.valueOf(marketId.trim()));
			}
		}
		return newInputList;
	}

	/**
	 * This method will concatenate all the string passed in parameters and
	 * crate a single string and send back as response.
	 * 
	 * @param strings
	 *            the strings
	 * @return the string
	 */
	public static String concat(final String... strings) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < strings.length; i++) {
			sb.append(strings[i]);
		}
		return sb.toString();
	}

	/**
	 * This method will concatenate all the object passed in parameters and
	 * crate a single string and send back as response.
	 * 
	 * @param object
	 *            the object
	 * @return the string
	 */
	public static String concat(final Object... object) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < object.length; i++) {
			sb.append(String.valueOf(object[i]));
		}
		return sb.toString();
	}

	/**
	 * This method is used to validate string for the regex pattern provided.
	 * 
	 * @param input
	 *            the String.
	 * @param regex
	 *            the String.
	 * @return isValid ,boolean.
	 */
	public static boolean validatePattern(final String input, final String regex) {
		boolean isValid = true;
		isValid = input.matches(regex);
		return isValid;
	}

	/**
	 * Gets the number of decimal places in any double number.
	 * 
	 * @param number
	 *            the number
	 * @return the decimal places
	 */
	public static int getNumDecimalPlaces(final Double number) {
		String stringNumber = Double.toString(Math.abs(number));
		int integerPlaces = stringNumber.indexOf(QuantifiedCareConstant.DOT);
		int decimalPlaces = stringNumber.length() - integerPlaces - 1;
		return decimalPlaces;
	}

	/**
	 * Sort entities by multiple properties.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param entities
	 *            the entities
	 * @param dir
	 *            the direction
	 * @param beanPropertyNames
	 *            the property
	 */
	@SuppressWarnings("unchecked")
	public static <T> void sortEntitiesByMultiProperty(final List<T> entities,
			final String dir, final String... beanPropertyNames) {
		Comparator<T> comp = null;
		Collection<Comparator<T>> beanComparatorCollection = new ArrayList<Comparator<T>>(
				beanPropertyNames.length);
		Transformer transformer = new Transformer() {
			//@Override
			public Object transform(Object input) {
				if (input instanceof String) {
					return ((String) input).toLowerCase();
				}
				return input;
			}
		};
		if (beanPropertyNames != null && beanPropertyNames.length > 0
				&& !CollectionUtils.isEmpty(entities)) {
			if (StringUtils.equalsIgnoreCase(dir, QuantifiedCareConstant.DESC)) {
				comp = new BeanComparator(
						beanPropertyNames[0],
						new ReverseComparator(ComparatorUtils
								.transformedComparator(
										new NullComparator(false), transformer)));
			} else {
				comp = new BeanComparator(beanPropertyNames[0],
						ComparatorUtils.transformedComparator(
								new NullComparator(false), transformer));
			}
			beanComparatorCollection.add(comp);
			for (int i = 1; i < beanPropertyNames.length; i++) {
				comp = null;
				comp = new BeanComparator(beanPropertyNames[i],
						ComparatorUtils.transformedComparator(
								new NullComparator(false), transformer));
				beanComparatorCollection.add(comp);
			}
			Comparator<T> finalComparator = ComparatorUtils
					.chainedComparator(beanComparatorCollection);
			Collections.sort(entities, finalComparator);
		}
	}

	/**
	 * Sort entities by property.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param entities
	 *            the entities
	 * @param property
	 *            the property
	 * @param dir
	 *            the direction
	 */
	@SuppressWarnings("unchecked")
	public static <T> void sortEntitiesByProperty(final List<T> entities,
			final String property, final String dir) {
		Comparator<T> comp = null;
		Transformer transformer = new Transformer() {
			//@Override
			public Object transform(Object input) {
				if (input instanceof String) {
					return ((String) input).toLowerCase();
				}
				return input;
			}
		};
		if (StringUtils.equalsIgnoreCase(dir, QuantifiedCareConstant.DESC)) {
			comp = new BeanComparator(property, new ReverseComparator(
					ComparatorUtils.transformedComparator(comp, transformer)));
		} else {
			comp = new BeanComparator(property,
					ComparatorUtils.transformedComparator(comp, transformer));
		}
		Collections.sort(entities, comp);
	}

	/**
	 * Checks if coming request is ajax request or not.
	 * 
	 * @param request
	 *            the request
	 * @return true, if is ajax
	 */
	public static boolean isAjaxRequest(final HttpServletRequest request) {
		return QuantifiedCareConstant.XML_HTTP_REQ_STR.equals(request
				.getHeader(QuantifiedCareConstant.AJAX_REQ_IND));
	}

	/**
	 * This method gets the string by concatenation the values from the
	 * collections the given preAppender, postAppender and the separator.
	 * 
	 * Returns null in case collection pass is empty or null
	 * 
	 * @param collection
	 *            the collection
	 * @param separator
	 *            the separator
	 * @param preAppender
	 *            the pre appender
	 * @param postAppender
	 *            the post appender
	 * @return the string from collection
	 */
	public static String getStringFromCollection(Collection<String> collection,
			String separator, String preAppender, String postAppender) {

		String value = null;
		StringBuffer strBld = new StringBuffer();
		if (CollectionUtils.isNotEmpty(collection)) {
			// appending appenders and the separatwor to the value from the list
			for (String strArg : collection) {
				strBld.append(preAppender).append(strArg.trim())
						.append(postAppender).append(separator);
			}
			// removing last comma from the string
			value = strBld.substring(0, strBld.length() - 1);
		}
		return value;
	}

	/**
	 * This method gets the string by concatenation the values from the
	 * collections the given preAppender, postAppender and the separator.
	 * 
	 * Returns null in case collection pass is empty or null
	 * 
	 * @param collection
	 *            the collection
	 * @param separator
	 *            the separator
	 * @return the string from collection
	 */
	public static String getStringFromCollection(Collection<String> collection,
			String separator) {

		String value = null;
		StringBuffer strBld = new StringBuffer();
		if (CollectionUtils.isNotEmpty(collection)) {
			// appending appenders and the separatwor to the value from the list
			for (String strArg : collection) {
				if (StringUtils.isNotBlank(strArg)) {
					strBld.append(strArg.trim()).append(separator);
				}
			}
			if (strBld.length() > 0) {
				// removing last comma from the string
				value = strBld.substring(0, strBld.length() - 1);
			}

		}
		return value;
	}

	/**
	 * Returns List of trimmed string sperated from the passed delimeter.
	 * 
	 * @param stringVal
	 *            the string val
	 * @param delimeter
	 *            the delimeter
	 * @return List of string
	 */
	public static List<String> getListFromStringSeperatedByDelimeter(
			@Nonnull String stringVal, @Nonnull String delimeter) {
		String[] strArr = stringVal.split(delimeter);
		List<String> list = new ArrayList<String>();
		for (String str : strArr) {
			list.add(str.trim());
		}
		return list;
	}

	/**
	 * Gets the comma seprated ids.
	 * 
	 * @param ids
	 *            the ids
	 * @return the comma seprated ids
	 */
	public static String getCommaSepratedIds(Set<Long> ids) {
		StringBuilder result = new StringBuilder();

		boolean first = true;
		for (Long id : ids) {
			if (first) {
				result.append(id);
				first = false;
			} else {
				result.append(QuantifiedCareConstant.COMMA);
				result.append(id);
			}
		}
		return result.toString();
	}

	/**
	 * 
	 * Returns List of ids from string separated by delimeter.
	 * 
	 * @param stringVal
	 *            the string val
	 * @param delimeter
	 *            the delimeter
	 * @return the list ids from string seperated by delimeter
	 */
	public static List<Long> splitStringIntoListIds(String stringVal,
			String delimeter) {
		List<Long> list = new ArrayList<Long>();
		if (stringVal != null && stringVal != QuantifiedCareConstant.EMPTY_STRING) {
			String[] strArr = stringVal.split(delimeter);
			for (String str : strArr) {
				list.add(new Long(str));
			}
		}

		return list;
	}

	/**
	 * This method returns the date of next year to the current date.
	 * 
	 * @return the next year date
	 */
	public static String getNextYearDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 360); // to add 360 days to the current date.
		Date nextYear = cal.getTime();
		return QuantifiedCareDateUtil.format(nextYear, QuantifiedCareConstant.SIMPLE_DATE_FORMAT);
	}

	/**
	 * This function returns the current date in string form.
	 * 
	 * @return the date
	 */
	public static String getCurrentDate() {
		StringBuilder builder = new StringBuilder();
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		builder.append((day < 10 ? "0" : "") + day + QuantifiedCareConstant.DOT);
		int month = cal.get(Calendar.MONTH) + 1;
		builder.append((month < 10 ? "0" : "") + month + QuantifiedCareConstant.DOT);
		builder.append(cal.get(Calendar.YEAR));
		return builder.toString();
	}

	/**
	 * Gets the list from string.
	 * 
	 * @param inputString
	 *            the input string
	 * @return the list from string
	 */
	public static List<String> getListFromString(String inputString) {
		List<String> textList = new ArrayList<String>();
		if (StringUtils.isNotBlank(inputString)) {
			textList.addAll(Arrays.asList(inputString
					.split(QuantifiedCareConstant.SPLIT_WORD_INTO_LETTER_REGEX)));
		}
		return textList;
	}

	/**
	 * Gets all days ofweek.
	 * 
	 * @return the day ofweek
	 */
	public static String getDayOfweek() {
		return "1234567";
	}

	/**
	 * This method converts the passed Object argument to boolean. if passed
	 * object is null this method will return false else it will return the
	 * coverted boolean value representation of passed object.
	 * 
	 * @param object
	 *            the object
	 * @return true, if successful
	 */
	public static boolean toBoolean(Object object) {
		boolean covertedBooleanObj = Boolean.FALSE;
		if (null != object) {
			String stringObject = String.valueOf(object);
			covertedBooleanObj = Boolean.valueOf(stringObject);
		}
		return covertedBooleanObj;
	}

	/**
	 * Converts a string to integer and returns null if string provided is null.
	 * 
	 * @param value
	 *            - to be converted
	 * @return Integer equivalent
	 */
	public static Integer getSQLInt(String value) {
		return value == null ? null : Integer.valueOf(value);
	}

	/**
	 * Escapes the String to SQL compatible replaces ! with !!.
	 * 
	 * @param inputString
	 *            - to be escaped
	 * @return String escaped String
	 */
	public static String escapeSQL(String inputString) {
		String input = null;
		if (StringUtils.isNotBlank(inputString)) {
			input = inputString.replaceAll("!", "!!");
		}
		return input;
	}

	/**
	 * returns true if all data required on listing page.
	 * 
	 * @param size
	 *            - size of data
	 * @return true, if is all data rquired
	 */
	public static boolean isAllDataRquired(int size) {
		return size == 20 || size == 50 || size == 100 ? false : true;
	}

	/**
	 * This method checks if number represented by <code>numberString</code> is
	 * a valid range number i.e. it is between the given range. Returns true, if
	 * yes.
	 * 
	 * @param numberString
	 *            - number string
	 * @param maxRange
	 *            the max range
	 * @param minRange
	 *            the min range
	 * @return returns true if number string represents a valid range number
	 */
	public static boolean isNumberBetweenRange(String numberString,
			int maxRange, int minRange) {
		boolean isValid = Boolean.TRUE;
		int number = 0;
		try {
			number = Integer.valueOf(numberString);
		} catch (NumberFormatException numberFormatException) {
			isValid = Boolean.FALSE;
		}
		if (isValid && (number > maxRange || number < minRange)) {
			isValid = Boolean.FALSE;
		}
		return isValid;
	}

	/**
	 * This method formats the decimal number in the given format.
	 * 
	 * @param value
	 *            the value
	 * @param format
	 *            the format
	 * @return the string
	 */
	public static String formatDecimalNumber(Double value, String format) {
		DecimalFormat df = new DecimalFormat(format);
		return df.format(value);
	}

	/**
	 * Gets the object field with values.
	 * 
	 * @param object
	 *            the object
	 * @param clazz
	 *            the clazz
	 * @return the object field with values
	 */
	public static Map<String, Object> getObjectFieldWithValues(Object object,
			Class<?> clazz) {

		Map<String, Object> fieldNameWithVal = new HashMap<String, Object>();
		Field[] flields = clazz.getDeclaredFields();
		for (Field field : flields) {
			Object value = null;
			field.setAccessible(true);
			try {
				value = field.get(object);
			} catch (IllegalArgumentException e) {
				// throw new InstantiationException("getObjectFieldWithValues");
			} catch (IllegalAccessException e) {
				// throw new InstantiationException(
				// "getObjectFieldWithValues");
			}
			if (null != value) {
				fieldNameWithVal.put(field.getName(), value);
			}
		}
		return fieldNameWithVal;
	}

	/**
	 * The API trims empty string to Hyphen '-'.
	 * 
	 * @param str
	 *            the str
	 * @return the string
	 */
	public static String trimStringToHyphen(String str) {
		if (StringUtils.isBlank(str)) {
			str = QuantifiedCareConstant.HYPHEN;
		}
		return str;
	}

	/**
	 * Removes the timestamp from date.
	 * 
	 * @param date
	 *            the date
	 */
	public static void removeTimestampFromDate(Calendar date) {
		if (date != null) {
			date.set(Calendar.HOUR, 0);
			date.set(Calendar.MINUTE, 0);
			date.set(Calendar.SECOND, 0);
			date.set(Calendar.MILLISECOND, 0);
		}
	}

	/**
	 * Removes the timestamp from date.
	 * 
	 * @param date
	 *            the date
	 */
	public static void removeTimestampFromDate(Date date) {
		if (date != null) {
			date.setSeconds(0);
			date.setHours(0);
			date.setMinutes(0);
		}
	}

	/**
	 * Parses the resource.
	 * 
	 * @param resourceName
	 *            the resource name
	 * @return the string
	 */
	/*public static String parseResource(MultipartFile resourceName) {
		System.out.println("Parsing resource Tika : " + resourceName);
		InputStream inputStream = null;
		String content = QuantifiedCareConstant.EMPTY_STRING;
		try {
			inputStream = resourceName.getInputStream();// this.getClass().getClassLoader().getResourceAsStream(resourceName);

			Metadata metadata = new Metadata();
			BodyContentHandler ch = new BodyContentHandler();
			AutoDetectParser parser = new AutoDetectParser();

			String mimeType = new Tika().detect(multipartToFile(resourceName));
			metadata.set(Metadata.CONTENT_TYPE, mimeType);

			parser.parse(inputStream, ch, metadata, new ParseContext());
			inputStream.close();

			content = ch.toString();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (TikaException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return content;
	}*/

	/**
	 * Multipart to file.
	 * 
	 * @param multipart
	 *            the multipart
	 * @return the file
	 * @throws IllegalStateException
	 *             the illegal state exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static File multipartToFile(MultipartFile multipart)
			throws IllegalStateException, IOException {
		File convFile = new File(multipart.getOriginalFilename());
		multipart.transferTo(convFile);
		return convFile;
	}

	/**
	 * Read file.
	 * 
	 * @param file
	 *            the file
	 * @return the string
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static String readFile(MultipartFile file) throws IOException {
		StringBuilder fileContents = new StringBuilder((int) file.getSize());
		DataInputStream inputStream = new DataInputStream(file.getInputStream());
		Scanner scanner = new Scanner(inputStream);
		String lineSeparator = System.getProperty("line.separator");
		try {
			while (scanner.hasNextLine()) {
				fileContents.append(scanner.nextLine() + lineSeparator);
			}
			return fileContents.toString();
		} finally {
			scanner.close();
		}
	}
	
	/**
	 * Gets the allowed domains.
	 *
	 * @return the allowed domains
	 */
	public static List<String> getAllowedDomains () {
		List<String> allowedDomains = new ArrayList<String>();
		allowedDomains.add("krmaa.com");
		allowedDomains.add("lakshhr.com");
		return  allowedDomains;
	}
}
