package com.assessment.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.assessment.mapper.CountryAggregatedDataMapper;
import com.assessment.mapper.CountryMapper;
import com.assessment.model.Country;
import com.assessment.model.CountryAggregatedData;
import com.assessment.repository.CountryAggregationRepository;
import com.assessment.repository.CountryRepository;

/**
 * Service class to handle Country related operations
 *
 */
@Service
public class CountryService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);
	
	@Autowired
	private CountryRepository repository;
	
	@Autowired
	private CountryAggregationRepository aggregationRepository;
	
	@Value("${country.aggregation.result.default.size}")
	private Integer countryAggregationResultDefaultSize;
	
	/**
	 * Method to search or list countries available in database.
	 * Method can also search country based on country code and/or
	 * country's name.
	 * 
	 * Method supports partial search based on countrycode and name.
	 * 
	 * @param countryCode - Country code for which country needs to
	 * be retrieved
	 * @param name - Name of the country for which country needs to
	 * be retrieved
	 * @return List<Country> - List of countries found for given request
	 */
	public List<Country> searchCountry(String countryCode, String name) {
		List<com.assessment.entity.Country> countries = null;

		LOGGER.debug("In searchCountry with countryCode:[{}] and name: [{}", countryCode, name);
		
		if(StringUtils.isNoneBlank(countryCode, name)) {
			// If both countryCode and name are being provided in the request, then
			// search the countries based on both countryCode + name
			countries = repository.findByCountryCodeContainingIgnoreCaseAndNameContainingIgnoreCase(countryCode, 
					name);
		} else if(StringUtils.isNotBlank(countryCode)) {
			// If countryCode is provided in request, then search countries based
			// on countryCode
			countries = repository.findByCountryCodeContainingIgnoreCase(countryCode);
		} else if(StringUtils.isNotBlank(name)) {
			// If only country's name is provided in request, then search countries
			// based on the name
			countries = repository.findByNameContainingIgnoreCase(name);
		} else {
			// If filters are provided in request to retrieve countries
			// then retrieve all countries
			countries = repository.findAll();
		}

		return CountryMapper.map(countries);
	}
	
	/**
	 * Method to retrieve aggregated data for country for the given input.
	 * Currently the method will retrieve count of airports per country and
	 * return in response.
	 * 
	 * Method also provides option to indicate the number of
	 * results that needs to be retrieved 
	 * 
	 * @param groupBy - Field based on which aggregation needs to be
	 * done
	 * @param aggregation - Type of aggregation that needs to be retrieved
	 * @param sortOrder - Sort order
	 * @param limit - Maximum number of records that needs to be retrieved
	 * @return List<CountryAggregatedData> - List of country's aggregated data
	 * for given request
	 */
	public List<CountryAggregatedData> retrieveCountryAggregationData(String groupBy, String aggregation, 
			String sortOrder, Integer limit) {
		
		LOGGER.debug("In retrieveCountryAggregationData with groupBy: [{}], aggregation: [{}]", groupBy, aggregation);
		
		// Check if sortOrder and limit are coming in request
		// If not present, then assign default values
		limit = limit != null ? limit : countryAggregationResultDefaultSize;
		
		List<CountryAggregatedData> aggregatedDatas = null;
		List<Object[]> resultSet = null;
		
		// Currently only group by airport and aggregation as count is
		// supported. Thus no condition check is added.
		// If new groupBy or aggregation needs to be supported, then
		// introduce condition to go to respective flows.
		resultSet = aggregationRepository.aggregateCountByAirport(sortOrder, limit);
		aggregatedDatas = CountryAggregatedDataMapper.map(resultSet, aggregation);
		
		return aggregatedDatas;
	}

}
