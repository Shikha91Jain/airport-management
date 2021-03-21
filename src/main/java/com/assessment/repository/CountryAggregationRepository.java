package com.assessment.repository;

import java.util.List;

/**
 * Custom Repository interface to perform aggregration operations
 * for Country entity
 *
 */
public interface CountryAggregationRepository {
	
	/**
	 * Method to retrieve count of airports per country for given
	 * limit of countries
	 * Method also provides option to sort the result in ascending
	 * or descending order.
	 * 
	 * @param sortOrder - Sort order i.e. ascending or descending for
	 * the results to be retrieved
	 * @param limit - Maximum number of results to be retrieved
	 * @return List<Object[]> - List of records found for request
	 */
	List<Object[]> aggregateCountByAirport(String sortOrder, Integer limit);

}
