package com.assessment.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.assessment.model.Country;

/**
 * Mapper class to map the details related to Country object
 *
 */
public final class CountryMapper {
	
	private CountryMapper() {
		super();
	}
	
	/**
	 * Method to map the details of list of countries found
	 * in the database.
	 * Method will map the details for each country and return
	 * the details in the list.
	 * 
	 * @param countries - List of JPA entities containing the respective
	 * country details
	 * @return List<Country> - List of mapped country models
	 */
	public static List<Country> map(List<com.assessment.entity.Country> countries) {	
		if(CollectionUtils.isEmpty(countries)) {
			return Collections.emptyList();
		}
		
		return countries.stream().map(CountryMapper::map).collect(Collectors.toList());
	}
	
	/**
	 * Method map the Country details in the model class from
	 * the details retrieved from database
	 * 
	 * @param countryEntity - JPA entity containing the country details
	 * @return Country - Mapped model class
	 */
	private static Country map(com.assessment.entity.Country countryEntity) {
		Country country = new Country();
		country.setCountryId(countryEntity.getObjectId());
		country.setCountryCode(countryEntity.getCountryCode());
		country.setContinent(countryEntity.getContinent());
		country.setName(countryEntity.getName());
		country.setWikipediaLink(countryEntity.getWikipediaLink());
		country.setKeywords(countryEntity.getKeywords());
		
		return country;
	}
	
	/**
	 * Method to map reference of country
	 * 
	 * @param countryEntity - Entity from which reference
	 * object needs to be mapped
	 * @return Country - Mapped object
	 */
	public static Country mapRef(com.assessment.entity.Country countryEntity) {
		Country country = new Country();
		country.setCountryId(countryEntity.getObjectId());
		country.setCountryCode(countryEntity.getCountryCode());
		return country;
	}

}
