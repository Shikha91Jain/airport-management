package com.assessment.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.exception.BadRequestException;
import com.assessment.model.Country;
import com.assessment.model.CountryAggregatedData;
import com.assessment.model.ErrorResponse;
import com.assessment.service.CountryService;
import com.assessment.utils.RequestValidator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller class for country related operation
 *
 */
@RestController
@Validated
@Api(value = "countries")
@RequestMapping(value = "/airport-management/v1")
public class CountryApiController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CountryApiController.class);
	
	@Autowired
	private CountryService countryService;

	@ApiOperation(value = "API to search/list countries", nickname = "searchCountry", notes = "API to search/retrieve list of countries available in the world.  API provides option to search the list of countries based on respective countryCode and/or country's name. API also supports partial/fuzzy search for these search parameters. If no search parameter is provided in request, then API will retrieve all the countries. The API will return the list of countries found for the given input in  response. If there are no countries found for given input, then API will return empty list. ", response = Country.class, responseContainer = "List", tags={ "Country", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success Response", response = Country.class, responseContainer = "List"),
        @ApiResponse(code = 500, message = "Server encountered unexpected condition", response = ErrorResponse.class) })
	@GetMapping(value = "/countries",
        produces = { "application/json; charset=UTF-8" })
    public ResponseEntity<List<Country>> searchCountry(
    		@ApiParam(value = "ISO code of the country for which country details needs to be retrieved. This is an optional field and if provided, then API will return country matching the provided countryCode.") @Valid @RequestParam(value = "countryCode", required = false) String countryCode,
    		@ApiParam(value = "Name of the country based on which country needs to be retrieved. This is an optional field and if provided, then API will return country matching the provided name.") @Valid @RequestParam(value = "name", required = false) String name) {

		LOGGER.debug("In getCountryList API with countryCode:[{}] and name: [{}]", countryCode, name);
		
		List<Country> countries = countryService.searchCountry(countryCode, name);
		return ResponseEntity.ok(countries);
	}
	
	@ApiOperation(value = "API to retrieve aggregated data related to countries", nickname = "retrieveCountryAggregationData", notes = "API to retrieve aggregated data for countries based on the provided groupBy and aggregation filters provided in request. Currently the API supports to provide the airport count per country. API also provides the option to sort the aggregated data based on the aggregation being performed. The number of result that needs to be retrieved can also be provided in request to retrieve limited result. ", response = CountryAggregatedData.class, responseContainer = "List", tags={ "Country", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Success response", response = CountryAggregatedData.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Bad request", response = ErrorResponse.class),
        @ApiResponse(code = 500, message = "Server encountered unexpected condition", response = ErrorResponse.class) })
    @GetMapping(value = "/countries/aggregation",
        produces = { "application/json; charset=UTF-8" })
    public ResponseEntity<List<CountryAggregatedData>> retrieveCountryAggregationData(
    		@ApiParam(value = "Field based on which the aggregation needs to be performed. Possible Values: airport Note: Currently the possible value for this is single field airport. The parameter can be extended based on the requirements to support group by based on multiple fields", required = true) @Valid @RequestParam(value = "groupBy", required = true) String groupBy,
    		@ApiParam(value = "Indicates what aggregation needs to be performed based on the groupBy field. Possible Values: count Note: Currently the possible value for this is count. The parameter can be extended based on the requirements to support and provide multiple aggregated data based on the groupBy field", required = true) @Valid @RequestParam(value = "aggregation", required = true) String aggregation,
    		@ApiParam(value = "Defines the order in which the result would be returned. Possible values: ASC (ascending), DESC (desecending) Note: This is an optional field, and if not provided then default sortOrder will be considered as DESC", defaultValue = "DESC") @Valid @RequestParam(value = "sortOrder", required = false, defaultValue="DESC") String sortOrder,
    		@ApiParam(value = "Indiciates the maximum number of resources that needs to be fetched in the response. Note: This is an optional field, and if not provided then default limit as 10 will be considered.") @Valid @RequestParam(value = "limit", required = false) Integer limit) 
    		throws BadRequestException  {
		
		RequestValidator.validateRetrieveCountryAggregationData(groupBy, aggregation, sortOrder);
		List<CountryAggregatedData> aggregatedDatas = countryService.retrieveCountryAggregationData(groupBy, aggregation, sortOrder, limit);
		return ResponseEntity.ok(aggregatedDatas);
	}

}
