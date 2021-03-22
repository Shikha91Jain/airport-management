package com.assessment.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.assessment.exception.NotFoundException;
import com.assessment.mapper.AirportMapper;
import com.assessment.mapper.RunwayMapper;
import com.assessment.model.Airport;
import com.assessment.model.AirportResponse;
import com.assessment.model.Runway;
import com.assessment.repository.AirportRepository;
import com.assessment.utils.Constants;
import com.assessment.utils.GenericUtils;
import com.assessment.utils.StatusCodes;

/**
 * Service class to handle the Airport related operations
 *
 */
@Service
public class AirportService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AirportService.class);
	
	@Autowired
    private AirportRepository airportRepository;
	
	@Value("${search.airport.default.page.size}")
	private Integer searchAirportDefaultPageSize;
	
	/**
	 * Method to retrieve airport details based on the given
	 * airportId
	 * 
	 * Method will retrieve the airport entity based on the airportId
	 * provided in request. If no airport is found, then method will
	 * throw NotFoundException.
	 * 
	 * If airport is found, then it will map and return the airport
	 * details
	 * 
	 * @param airportId - Unique identifier of airport based on airport
	 * details needs to be retrieved
	 * @return Airport - Airport details found for given airportId
	 * @throws NotFoundException Thrown when Airport is not found for the
	 * given airportId
	 */
	public Airport retrieveAirportById(Long airportId) throws NotFoundException {
		// retrieve airport entity with the objectId provided in request
		Optional<com.assessment.entity.Airport> airportEntity = airportRepository.findById(airportId);
    	
		// Validate if the airport entity is present or not
		// If not found, then throw NotFoundException
    	if(!airportEntity.isPresent()) {
    		LOGGER.debug("Airport not found for airportId in retrieveAirportById: [{}]", airportId);
    		throw new NotFoundException(StatusCodes.DATA_NOT_FOUND.getCode(), StatusCodes.DATA_NOT_FOUND.getReason(), 
    				Constants.AIRPORT, String.valueOf(airportId));
    	}
    	
    	return AirportMapper.map(airportEntity.get());
	}
	
	/**
	 * Method to retrieve list of runways for a given airport
	 * based on the given airportId
	 * 
	 * Method will retrieve the airport entity based on the airportId
	 * provided in request. If no airport is found, then method will
	 * throw NotFoundException.
	 * 
	 * If airport is found, then it will retrieve the list of runways
	 * and map and return the details
	 * 
	 * @param airportId - Unique identifier of airport based on which
	 * runways needs to be retrieved
	 * @return List<Runway> - List of runways found for the request
	 * @throws NotFoundException Thrown when Airport is not found for the
	 * given airportId
	 */
	public List<Runway> retrieveRunwayForAirport(Long airportId) throws NotFoundException {
		// retrieve airport entity with the objectId provided in request
		Optional<com.assessment.entity.Airport> airportEntity = airportRepository.findById(airportId);
    	
		// Validate if the airport entity is present or not
		// If not found, then throw NotFoundException
    	if(!airportEntity.isPresent()) {
    		LOGGER.debug("Airport not found for airportId in retrieveRunwayForAirport: [{}]", airportId);
    		throw new NotFoundException(StatusCodes.DATA_NOT_FOUND.getCode(), StatusCodes.DATA_NOT_FOUND.getReason());
    	}
    	
    	// If Airport found then retrieve the runways for airport using lazy fetch
    	// and map and return the details
    	return RunwayMapper.map(airportEntity.get().getRunways());
	}

	/**
	 * Method to search airports based on the given filters in request.
	 * Method will perform below steps:
	 * 1. Retrieve total number of records matching the filter criteria
	 * in request.
	 * 2. Retrieve the limited list of airports from database if airport count is
	 * greater than 0  based on the offset & limit in sorted (based on the sortBy and
	 * sortOrder)
	 * 
	 * @param countryCode - CountryCode for which list of airports
	 * needs to be retrieved
	 * @param countryName - Name of country for which list of airports
	 * needs to be retrieved
	 * @param offset - Starting index from which the list of airports
	 * needs to be retrieved
	 * @param limit - Maximum number of records that needs to be retrieved
	 * offset onwards
	 * @param sortBy - Name of the search parameter over which the results needs 
	 * to be sorted.
	 * @param sortOrder - Defines the order in which the result would be returned.
	 * @return AirportResponse - Response containing the airports
	 * found for the request and total number of records
	 */
	public AirportResponse searchAirport(String countryCode, String countryName, Integer offset, Integer limit, 
			String sortBy, String sortOrder) {
		
		List<com.assessment.entity.Airport> airports = null;
		
		// Retrieve count of airports found based on given fitlers in request
		long airportCount = retrieveSearchAirportCount(countryCode, countryName);
		
		// Check if airportCount is greater than 0. If yes, then retrieve
		// the airport data with the requested pagination & sorting
		if(airportCount > 0) {
			// Check if parameters limit is present in request. 
			// If not present, then set the default value
			limit = limit != null ? limit : searchAirportDefaultPageSize;
			
			// Retrieve the list of airports from database
			airports = searchAirportInDatabase(countryCode, countryName, 
					offset, limit, sortBy, sortOrder);
		}
		
		// Mapping of response
		AirportResponse response = new AirportResponse();
		response.setTotalNumberOfRecords(airportCount);
		response.setAirports(AirportMapper.map(airports));
		
		return response;
	}

	/**
	 * Method to retrieve the count of airports present in 
	 * the database based on given filters
	 * 
	 * @param countryCode - Country code based on which list of airport needs
	 * to be retrieved
	 * @param countryName - Name of the country based on which list of airport
	 * needs to be retrieved
	 * @return long - Number of airports found based on given filters
	 */
	public long retrieveSearchAirportCount(String countryCode, String countryName) {
		long airportCount;
		
		// Check if both countryCode and name is coming in request
		// If yes, then retrieve count based on both countryCode and name
		if(StringUtils.isNoneEmpty(countryCode, countryName)) {
			airportCount = airportRepository.countByCountry_CountryCodeContainingIgnoreCaseAndCountry_NameContainingIgnoreCase(
					countryCode, countryName);
		} else if(StringUtils.isNotBlank(countryCode)) {
			// If only countryCode is provided in request, then retrieve
			// the count of airports based on countryCode
			airportCount = airportRepository.countByCountry_CountryCodeContainingIgnoreCase(countryCode);
		} else if(StringUtils.isNotBlank(countryName)) {
			// If only name is provided in request, then retrieve the
			// count of airports based on name
			airportCount = airportRepository.countByCountry_NameContainingIgnoreCase(countryName);
		} else {
			// If not filters are provided, then retrieve the count
			// of all airports present
			airportCount = airportRepository.count();
		}
		
		return airportCount;
	}
	
	/**
	 * Method to search airports in database based on the given filters.
	 * Method will also use the pagination and sorting filters while checking
	 * in database
	 * 
	 * @param countryCode - Country code based on which list of airport needs
	 * to be retrieved
	 * @param countryName - Name of the country based on which list of airport
	 * needs to be retrieved
	 * @param offset - Starting index from which airports needs to be read 
	 * @param limit - Maximum number of records that needs to be fetched starting
	 * from offset
	 * @param sortBy - Field based on which sorting needs to be done
	 * @param sortOrder - Sort order that needs to be used while querying database
	 * @return List<com.assessment.entity.Airport> - List of airport entities found
	 * in database
	 */
	public List<com.assessment.entity.Airport> searchAirportInDatabase(String countryCode, String countryName, 
			Integer offset, Integer limit, String sortBy, String sortOrder) {
		
		List<com.assessment.entity.Airport> airports = null;
		
		// Form pageable object with the offset, limit, sortBy and sort order
		// for pagination and sorting.
		Pageable pageable = GenericUtils.mapPageable(offset, limit, sortBy, sortOrder);
		
		if(StringUtils.isNoneBlank(countryCode, countryName)) {
			// If both countryCode and name are present, then retrieve
			// the list of airports based on countryCode and name
			airports = airportRepository.findAllByCountry_CountryCodeContainingIgnoreCaseAndCountry_NameContainingIgnoreCase(
					countryCode, countryName, pageable);
		} else if(StringUtils.isNotBlank(countryCode)) {
			// If only countrycode is present, then retrieve the list of
			// airports based on countryCode
			airports = airportRepository.findAllByCountry_CountryCodeContainingIgnoreCase(countryCode, pageable);
		} else if(StringUtils.isNotBlank(countryName)) {
			// If only name is present, then retrieve the list of airports
			// based on airport name
			airports = airportRepository.findAllByCountry_NameContainingIgnoreCase(countryName, pageable);
		} else {
			// If no filters are provided, then retrieve the list of airports
			// with the pagination
			Page<com.assessment.entity.Airport> airportPageResult = airportRepository.findAll(pageable);
			airports = airportPageResult.getContent();
		}
		
		return airports;
	}

}
