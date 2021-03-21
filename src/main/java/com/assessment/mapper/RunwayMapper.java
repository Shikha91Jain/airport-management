package com.assessment.mapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.assessment.model.Runway;

/**
 * Mapper class to map the details related to Runway object
 *
 */
public final class RunwayMapper {
	
	private RunwayMapper() {
		super();
	}
	
	/**
	 * Method to map the list of runway object with the details
	 * returned by database
	 * 
	 * @param runwayEntityList - List of Runway JPA entity for which
	 * details needs to be mapped
	 * @return List<Runway> - List of mapped runway objects for the
	 * list of runways found for given airport
	 */
	public static List<Runway> map(List<com.assessment.entity.Runway> runwayEntityList) {
		if(CollectionUtils.isEmpty(runwayEntityList)) {
			return Collections.emptyList();
		}
		
		return runwayEntityList.stream().map(RunwayMapper::map).collect(Collectors.toList());
	}
	
	/**
	 * Method to map Runway object with the details returned by
	 * database
	 * 
	 * @param runwayEntity - Runway JPA entity containing the runway
	 * details that needs to be mapped
	 * @return Runway - Mapped object
	 */
	private static Runway map(com.assessment.entity.Runway runwayEntity) {
		Runway runway = new Runway();
		runway.setRunwayId(runwayEntity.getObjectId());
		runway.setLength(runwayEntity.getLength() != null ? String.valueOf(runwayEntity.getLength()) : null);
		runway.setWidth(runwayEntity.getWidth() != null ? String.valueOf(runwayEntity.getWidth()) : null);
		runway.setSurface(runwayEntity.getSurface());
		runway.setLighted(runwayEntity.getLighted());
		runway.setClosed(runwayEntity.getClosed());
		
		// setting runway's length specifications
		runway.setLengthLatitude(runwayEntity.getLengthLatitude());
		runway.setLengthLongitude(runwayEntity.getLengthLongitude());
		runway.setLengthElevation(runwayEntity.getLengthElevation());
		runway.setLengthHeading(runwayEntity.getLengthHeading());
		runway.setLengthDisplacementThreshold(runwayEntity.getLengthDisplacementThreshold());
		
		// setting runway's height specifications
		runway.setHeightLatitude(runwayEntity.getHeightLatitude());
		runway.setHeightLongitude(runwayEntity.getHeightLongitude());
		runway.setHeightElevation(runwayEntity.getHeightElevation());
		runway.setHeightHeading(runwayEntity.getHeightHeading());
		runway.setHeightDisplacementThreshold(runwayEntity.getHeightDisplacementThreshold());
		
		return runway;
	}

}
