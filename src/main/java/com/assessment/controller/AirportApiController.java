package com.assessment.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.exception.APIException;
import com.assessment.exception.BadRequestException;
import com.assessment.exception.NotFoundException;
import com.assessment.model.Airport;
import com.assessment.model.AirportResponse;
import com.assessment.model.ErrorResponse;
import com.assessment.model.Runway;
import com.assessment.service.AirportService;
import com.assessment.utils.RequestValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
/**
 * Controller class for Airport related resources
 *
 */

@RestController
@Validated
@Api(value = "airports")
@RequestMapping(value = "/airport-management/v1")
public class AirportApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AirportApiController.class);
    
    @Autowired
    private AirportService airportService;

    @ApiOperation(value = "API to retrieve aiport based on the given id", nickname = "getAirport", notes = "API to retrieve airport information based on the given id of airport. API will also validate if airport with given id exists. If no airport with given id is found in database, then API will return appropriate error response. ", response = Airport.class, tags={ "Airport", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success response", response = Airport.class),
        @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
        @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
        @ApiResponse(code = 500, message = "Server encountered unexpected condition", response = ErrorResponse.class) })
    @GetMapping(value = "/airports/{airportId}",
        produces = { "application/json; charset=UTF-8" })
    public ResponseEntity<Airport> getAirport(@ApiParam(value = "Unique Identifier of the aiport based on which airport needs to be retrieved",required=true) 
    @PathVariable("airportId") Long airportId) throws NotFoundException {
    	Airport airport = airportService.retrieveAirportById(airportId);
    	return ResponseEntity.ok(airport);
    }

    @ApiOperation(value = "API to search/list airports based on provided input", nickname = "searchAirport", notes = "API to search/retrieve list of airports based on the given input parameters.  API provides option to search the list of airports based on respective countryCode and/or airports's name. API also supports partial/fuzzy search for these search parameters. If no search parameter is provided in request, then API will list all airports. API supports pagination and provides the option list of airports in paginated format. API is also capable to perform sorting of the search based on the given parameter. Sorted results can be returned in either ascending or descending order as requested by the consumer. If there are no airprots found for given input, then API will return empty list. ", response = AirportResponse.class, tags={ "Airport", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success response", response = AirportResponse.class),
        @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
        @ApiResponse(code = 500, message = "Server encountered unexpected condition", response = ErrorResponse.class) })
    @GetMapping(value = "/airports",
        produces = { "application/json; charset=UTF-8" })
    public ResponseEntity<AirportResponse> searchAirport(
    		@ApiParam(value = "ISO code of the country for which list of airports needs to be searched. This is an optional field and if provided, then API will return airports matching the provided countryCode.") @Valid @RequestParam(value = "country.countryCode", required = false) String countryCode,
    		@ApiParam(value = "Name of the airport based on which list of airports needs to be searched. This is an optional field and if provided, then API will return airports matching the provided name.") @Valid @RequestParam(value = "name", required = false) String name,
    		@ApiParam(value = "Requested index for start of resources to be provided in response. Default value: 0", defaultValue = "0") @Valid @RequestParam(value = "offset", required = false, defaultValue="0") Integer offset,
    		@ApiParam(value = "Requested number of maximum number of airports to be provided in response. Default value: 20") @Valid @RequestParam(value = "limit", required = false) Integer limit,
    		@ApiParam(value = "Name of the search parameter over which the results needs to be sorted. Possible Values: countryCode, name Note: If not present in request, then default value countryCode will be considered.", defaultValue = "country.countryCode") @Valid @RequestParam(value = "sortBy", required = false, defaultValue="country.countryCode") String sortBy,
    		@ApiParam(value = "Defines the order in which the result would be returned. Possible Values: ASC(Ascending), DESC(Descending) Note: This is an optional field, and if not provided then default sortOrder will be considered as ASC", defaultValue = "ASC") @Valid @RequestParam(value = "sortOrder", required = false, defaultValue="ASC") String sortOrder) 
    				throws BadRequestException {
        
    	LOGGER.debug("In searchAirport with name: [{}], countryCode: [{}]", name, countryCode);
    	
    	RequestValidator.validateSearchAirport(sortBy, sortOrder);
    	AirportResponse airportResponse = airportService.searchAirport(countryCode, name, offset, limit, sortBy, sortOrder);
    	return ResponseEntity.ok(airportResponse);
    }

	@ApiOperation(value = "API to retrieve runways for an airport", nickname = "retrieveRunwayForAirport", notes = "API is used to retrieve the list of runways for a given airport. API will also validate if airport with given id exists. If no airport with given id is found in database, then API will return appropriate error response. ", response = Runway.class, responseContainer = "List", tags={ "Runway", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success response", response = Runway.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
        @ApiResponse(code = 404, message = "Not found", response = ErrorResponse.class),
        @ApiResponse(code = 500, message = "Server encountered unexpected condition", response = ErrorResponse.class) })
	@GetMapping(value = "/airports/{airportId}/runways",
        produces = { "application/json; charset=UTF-8" })
    public ResponseEntity<List<Runway>> retrieveRunwayForAirport(
    		@ApiParam(value = "Unique Identifier of the airport for which list of runways needs to be retrieved",required=true) @PathVariable("airportId") Long airportId) throws APIException {
		LOGGER.debug("In retrieveRunwayForAirport with airportId: [{}]", airportId);
		
		List<Runway> runways = airportService.retrieveRunwayForAirport(airportId);
		return ResponseEntity.ok(runways);
	}

}
