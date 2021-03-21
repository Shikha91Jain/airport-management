package com.assessment.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.assessment.entity.Airport;
import com.assessment.entity.Country;
import com.assessment.entity.Runway;

/**
 * Utility class to have utlity methods for test class
 *
 */
public final class AirportManagementTestUtil {
	
	private AirportManagementTestUtil() {
		
	}
	
	public static Airport getAirport(Long objectId) {
		Airport airport = new Airport();
		airport.setObjectId(objectId);
		airport.setName("Schiphol");
		airport.setCountry(getCountry(123L, "NL", "Netherlands"));
		
		airport.addRunway(getRunway(11223L, new BigDecimal(1500), new BigDecimal(200)));
		airport.addRunway(getRunway(11224L, null, null));
		
		return airport;
	}
	
	public static List<Airport> getAirportList() {
		List<Airport> airports = new ArrayList<>();
		airports.add(getAirport(2132L));
		airports.add(getAirport(2133L));
		
		return airports;
	}
	
	public static List<Country> getCountryList() {
		List<Country> countries = new ArrayList<>();
		countries.add(getCountry(101L, "NL", "Netherlands"));
		countries.add(getCountry(102L, "NA", "Nort America"));
		
		return countries;
	}
	
	
	private static Country getCountry(Long objectId, String countryCode, String name) {
		Country country = new Country();
		country.setObjectId(objectId);
		country.setCountryCode(countryCode);
		country.setName(name);
		
		return country;
	}
	
	public static Runway getRunway(Long objectId, BigDecimal length, BigDecimal width) {
		Runway runway = new Runway();
		runway.setObjectId(objectId);
		runway.setLength(length);
		runway.setWidth(width);
		
		return runway;
	}
	
	public static List<Object[]> getCountryAggregationData() {
		List<Object[]> aggregationData = new ArrayList<>();
		
		aggregationData.add(new Object[] {new BigDecimal(101L), "US", new BigDecimal(23580)});
		aggregationData.add(new Object[] {new BigDecimal(102L), "SA", new BigDecimal(2000)});
		aggregationData.add(new Object[] {new BigDecimal(103L), "IN", new BigDecimal(500)});
		
		return aggregationData;
	}

}
