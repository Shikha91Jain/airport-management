package com.assessment.utils;

import java.text.MessageFormat;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Utility class containing the utility methods
 * to be used by application
 *
 */
public final class GenericUtils {
	
	private GenericUtils() {
		super();
	}
	
	/**
	 * Method to format the error message with the parameters
	 * provided in the input
	 * 
	 * @param reason - Error message that needs to be formatted
	 * @param parameters - Array of parameters that needs to be
	 * replaced in the error message
	 * @return String - Formatted string
	 */
	public static String formatErrorMessage(String reason, String... parameters) {
		
		String errorMessage = reason;
		
		if(ArrayUtils.isNotEmpty(parameters)) {
			errorMessage = MessageFormat.format(reason, parameters);
		}
		
		return errorMessage;
	}
	
	/**
	 * Method to map the pageable object with the given page number and size.
	 * Method also map the sort object to sort the values based on sortBy field.
	 * 
	 * @param offset - Offset point from where the records needs to be retrieved
	 * @param limit - Number of records that needs to be retrieved
	 * @param sortBy - Sorting to be done based on this field.
	 * @param sortOrder - Sort order i.e. ascending or descending
	 * @return Pageable - Mapped object with the pagination and sorting settings
	 */
	public static Pageable mapPageable(Integer offset, Integer limit, String sortBy, String sortOrder) {
		Sort sort = null;
		
		if(StringUtils.equalsIgnoreCase(sortOrder, Constants.DESC)) {
			sort = Sort.by(sortBy).descending();
		} else {
			sort = Sort.by(sortBy).ascending();
		}
		
		return new OffsetBasedPageRequest(limit, offset, sort);
	}

}
