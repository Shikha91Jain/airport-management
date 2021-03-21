package com.assessment.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.assessment.model.Airport;

/**
 * Mapper class to map the details related to Airport object
 *
 */
public final class AirportMapper {
	
	private AirportMapper() {
		super();
	}
	
	/**
	 * Method to map the list of airport model from the list
	 * of airports returned from database.
	 * If the list of airport is empty, then method will return
	 * empty list.
	 * 
	 * @param airportEntityList - List of airport JPA entities that needs
	 * to be mapped
	 * @return List<Airport> - List of mapped airport model
	 */
	public static List<Airport> map(List<com.assessment.entity.Airport> airportEntityList) {
		if(CollectionUtils.isEmpty(airportEntityList)) {
			return Collections.emptyList();
		}
		
		return airportEntityList.stream().map(AirportMapper::map).collect(Collectors.toList());
	}
	
	/**
	 * Method map the Airport details from the details returned by database
	 * for the entity
	 * 
	 * @param airportEntity - Airport JPA entity containing the details to be
	 * mapped
	 * @return Airport - Mapped airport model
	 */
	public static Airport map(com.assessment.entity.Airport airportEntity) {
		Airport airport = new Airport();
		airport.setAirportId(airportEntity.getObjectId());
		airport.setIdentifier(airportEntity.getAirportIdentifier());
		airport.setName(airportEntity.getName());
		airport.setLatitude(airportEntity.getLatitude());
		airport.setLongitude(airportEntity.getLongitude());
		airport.setElevation(airportEntity.getElevation());
		
		airport.setCountryRef(CountryMapper.mapRef(airportEntity.getCountry()));
		
		airport.setIsoRegion(airportEntity.getIsoRegion());
		airport.setMunicipality(airportEntity.getMunicipality());
		airport.setScheduledService(airportEntity.getScheduledService());
		airport.setGpsCode(airportEntity.getGpsCode());
		airport.setIataCode(airportEntity.getIataCode());
		airport.setLocalCode(airportEntity.getLocalCode());
		airport.setHomeLink(airportEntity.getHomeLink());
		airport.setWikipediaLink(airportEntity.getWikipediaLink());
		airport.setKeywords(airportEntity.getKeywords());
		
		// Retrieve the runway information and map
		airport.setRunways(RunwayMapper.map(airportEntity.getRunways()));
		
		return airport;
	}

}
