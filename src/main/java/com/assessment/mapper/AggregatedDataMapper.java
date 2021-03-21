package com.assessment.mapper;

import com.assessment.model.AggregatedData;

/**
 * Mapper class to map the details related to AggregatedData
 * object
 *
 */
public final class AggregatedDataMapper {
	
	private AggregatedDataMapper() {
		
	}
	
	/**
	 * Method to map the AggregatedData object with the
	 * given type and value
	 * 
	 * @param type - type of aggregated data
	 * @param value - Value of aggregated data
	 * @return AggregatedData - Mapped object
	 */
	public static AggregatedData map(String type, String value) {
		AggregatedData aggregatedData = new AggregatedData();
		aggregatedData.setType(type);
		aggregatedData.setValue(value);
		
		return aggregatedData;
	}

}
