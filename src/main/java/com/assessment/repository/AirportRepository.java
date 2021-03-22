package com.assessment.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment.entity.Airport;

/**
 * Repository interface to handle the JPA operations on the
 * Airport entity
 *
 */
@Repository
@Transactional
public interface AirportRepository extends JpaRepository<Airport, Long> {

	/**
	 * Method to search list of airports based on the given ISO country
	 * code.
	 * Method supports receiving  both complete ISO country code as well
	 * as partial country code. Method performs case insensitive search based
	 * on the country code.
	 * 
	 * @param isoCountryCode - ISO country code based on which the airports needs
	 * to be searched
	 * @param pageable - Pagination and sorting parameters to be applied
	 * when querying database.
	 * @return List<Airport> - List of airports found based on the given
	 * isoCountryCode
	 */
	List<Airport> findAllByCountry_CountryCodeContainingIgnoreCase(String countryCode, Pageable pageable);
	
	/**
	 * Method to retrieve the count of airports present in DB based
	 * on the given countryCode.
	 * Method supports receiving  both complete ISO country code as well
	 * as partial country code. Method performs case insensitive search based
	 * on the country code.
	 * 
	 * @param countryCode - Country code based on which count of airports
	 * needs to be retrieved.
	 * @return long - Count of airports found
	 */
	long countByCountry_CountryCodeContainingIgnoreCase(String countryCode);
	
	/**
	 * Method to search list of airports based on the given name.
	 * Method supports receiving  both complete country name as well
	 * as partial country name. Method performs case insensitive search based
	 * on the name.
	 * 
	 * @param name - Name of the country based on which the airports needs
	 * to be searched
	 * @param pageable - Pagination and sorting parameters to be applied
	 * when querying database.
	 * @return List<Airport> - List of airports found based on the given
	 * isoCountryCode
	 */
	List<Airport> findAllByCountry_NameContainingIgnoreCase(String name, Pageable pageable);
	
	/**
	 * Method to retrieve the count of airports present in DB based
	 * on the given airport name.
	 * Method supports receiving  both complete name as well
	 * as partial name. Method performs case insensitive search based
	 * on the name.
	 * 
	 * @param name - Airport's name based on which count of airports
	 * needs to be retrieved.
	 * @return long - Count of airports found
	 */
	long countByCountry_NameContainingIgnoreCase(String name);
	
	/**
	 * Method to search list of airports based on the given country
	 * code and airport's name.
	 * Method supports receiving  both complete as well
	 * as partial country code and name of airport. Method performs case 
	 * insensitive search based on the country code & name.
	 * 
	 * @param countryCode - Country code based on which airports needs to
	 * be searched
	 * @param name - Name of the country based on which airports needs to
	 * be searched
	 * @param pageable - Pagination and sorting parameters to be applied
	 * when querying database.
	 * @return List<Airport> - List of airports found for given input
	 */
	List<Airport> findAllByCountry_CountryCodeContainingIgnoreCaseAndCountry_NameContainingIgnoreCase(String countryCode, String name, Pageable pageable);
	
	/**
	 * Method to retrieve the count of airports present in DB based
	 * on the given airport name and country code.
	 * Method supports receiving  both complete as well
	 * as partial name and country code. Method performs case insensitive 
	 * search based on the name and country code.
	 * 
	 * @param countryCode - Country code based on which airports needs to
	 * be searched
	 * @param name - Name of the country based on which airports needs to
	 * be searched
	 * @return long - Count of airports found for given input
	 */
	long countByCountry_CountryCodeContainingIgnoreCaseAndCountry_NameContainingIgnoreCase(String countryCode, String name);
	
}
