package com.assessment.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment.entity.Country;

/**
 * Repository interface to handle the JPA operations related
 * to Country entity
 *
 */
@Repository
@Transactional
public interface CountryRepository extends JpaRepository<Country, Long> {
	
	/**
	 * Method to search the country based on ISO country code.
	 * Method supports search both by exact country code as well
	 * as search based on partial ISO country code.
	 * Method will perform case insensitive search and return the countries
	 * matching the criteria.
	 * 
	 * @param isoCountryCode - ISO Country code based on which the
	 * country needs to be retrieved
	 * @return List<Country> - List of countries found for given
	 * input
	 */
	List<Country> findByCountryCodeContainingIgnoreCase(String countryCode);
	
	/**
	 * Method to search the country based on the country name.
	 * Method supports search both by complete name as well
	 * as search based on partial country name.
	 * Method will perform case insensitive search and return the countries
	 * matching the criteria.
	 * 
	 * @param name - Name of the country based on which the list of
	 * countries needs to be retrieved
	 * @return List<Country> - List of countries found for given
	 * input
	 */
	List<Country> findByNameContainingIgnoreCase(String name);
	
	/**
	 * Method to search list of countries based on the given ISO country
	 * code and name of country.
	 * Method supports search both by complete countryCode/name as well
	 * as search based on partial countryCode/name provided in request.
	 * 
	 * Method will perform case insensitive search and return the countries
	 * matching the criteria.
	 * 
	 * @param isoCountryCode
	 * @param name
	 * @return
	 */
	List<Country> findByCountryCodeContainingIgnoreCaseAndNameContainingIgnoreCase(String countryCode, String name);
	
}
