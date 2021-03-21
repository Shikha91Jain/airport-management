package com.assessment.utils;

import org.apache.commons.lang3.StringUtils;

import com.assessment.exception.BadRequestException;

/**
 * Validator class to perform validations on the incoming
 * request to the application.
 *
 */
public final class RequestValidator {
	
	private RequestValidator() {
		
	}
	
	/**
	 * Method to validate the request coming for search airport API.
	 * 
	 * Method will perform below validations:
	 * 1. It will validate sortOrder's value if present in request.
	 * If invalid, then method will throw BadRequestException.
	 * 2. It will validate the value for sortBy field. If the value is
	 * not one of the allowed values, then method will throw
	 * BadRequestException
	 * 
	 * @param sortBy - Sort by field which needs to be validated
	 * for request
	 * @param sortOrder - Sort order which needs to be validated
	 * @throws BadRequestException Thrown when the request validation
	 * fails due to invalid value provided for parameters
	 */
	public static void validateSearchAirport(String sortBy, String sortOrder) throws BadRequestException {
		validateValuesForParameter(sortOrder, Constants.SORT_ORDER, Constants.ASC, Constants.DESC);
		if(!StringUtils.equalsAnyIgnoreCase(sortBy, Constants.COUNTRY_COUNTRY_CODE, 
				Constants.NAME)) {
			throw new BadRequestException(StatusCodes.INVALID_VALUE_FOR_PARAM.getCode(), StatusCodes
					.INVALID_VALUE_FOR_PARAM.getReason(), Constants.SORT_BY);
		}
	}
	
	/**
	 * Method to validate the request coming for retrieving
	 * aggregated data for countries
	 * 
	 * @param groupBy - Group by field
	 * @param aggregation - Aggregation operation based on which aggregated
	 * data needs to be retrieved
	 * @param sortOrder - Sort order for the aggregated data
	 * @throws BadRequestException Thrown when request validation fails
	 */
	public static void validateRetrieveCountryAggregationData(String groupBy, String aggregation, String sortOrder) 
			throws BadRequestException {
		validateValuesForParameter(groupBy, Constants.GROUP_BY, Constants.AIRPORT);
		
		// Currently only single aggregation is supported in API
		// But if there is multiple aggregated data needs to be retrieved
		// then we can use the below list by splitting to check the aggregations
		// that needs to be retrieved
		validateValuesForParameter(aggregation, Constants.AGGREGATION, Constants.COUNT);
		validateValuesForParameter(sortOrder, Constants.SORT_ORDER, Constants.ASC, Constants.DESC);
	}
	
	/**
	 * Method to validate the value against the valid values for
	 * given parameter.
	 * If the value is not amongst one of the valid values, then
	 * method will throw BadRequestException
	 * 
	 * @param value - Value that needs to be validated
	 * @param parameterName - Name of the parameter getting validated
	 * This will be used in error message in case of error
	 * @param validValues - List of valid values for a parameter
	 * @throws BadRequestException Thrown when the value provided is
	 * not amongst the 
	 */
	private static void validateValuesForParameter(String value, String parameterName, String... validValues) 
			throws BadRequestException {
		if(!StringUtils.equalsAnyIgnoreCase(value, validValues)) {
			throw new BadRequestException(StatusCodes.INVALID_VALUE_FOR_PARAM.getCode(), StatusCodes
					.INVALID_VALUE_FOR_PARAM.getReason(), parameterName);
		}
	}

}
