package com.assessment.mapper;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.assessment.model.CountryAggregatedData;

/**
 * Mapper class to map the details related to CountryAggregatedData
 * object
 *
 */
public final class CountryAggregatedDataMapper {
	
	private CountryAggregatedDataMapper() {
		
	}
	
	/**
	 * Method to map the list of aggregated data returned by database
	 * 
	 * @param resultSet - Result set returned by database that needs to
	 * be mapped
	 * @return List<CountryAggregatedData> - List of mapped country
	 * aggregated data
	 */
	public static List<CountryAggregatedData> map(List<Object[]> resultSet, String aggregation) {
		if(CollectionUtils.isEmpty(resultSet)) {
			return Collections.emptyList();
		}
		
		return resultSet.stream().map(record -> map(record, aggregation)).collect(Collectors.toList());
	}
	
	/**
	 * Method to map the aggregated data for each row
	 * 
	 * @param row - Row data that needs to be mapped
	 * @param aggregation - Aggregation type
	 * @return CountryAggregatedData - Mapped object
	 */
	private static CountryAggregatedData map(Object[] row, String aggregation) {
		CountryAggregatedData data = new CountryAggregatedData();
		data.setCountryId(((BigDecimal) row[0]).longValue());
		data.setCountryCode((String) row[1]);
		data.addAggregatedDataItem(AggregatedDataMapper.map(aggregation, String.valueOf(row[2])));
		
		return data;
	}

}
