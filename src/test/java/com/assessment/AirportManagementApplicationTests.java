package com.assessment;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import com.assessment.controller.AirportApiController;
import com.assessment.controller.CountryApiController;
import com.assessment.entity.Airport;
import com.assessment.entity.Country;
import com.assessment.repository.AirportRepository;
import com.assessment.repository.CountryAggregationRepository;
import com.assessment.repository.CountryRepository;
import com.assessment.service.AirportService;
import com.assessment.service.CountryService;
import com.assessment.utils.AirportManagementTestUtil;
import com.assessment.utils.Constants;

/**
 * Test class for the spring boot applications
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@DirtiesContext
class AirportManagementApplicationTests {
	
	@Autowired
	AirportApiController airportApiController;
	
	@Autowired
	CountryApiController countryApiController;
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	AirportService airportService;
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	CountryAggregationRepository countryAggregationRepository;
	
	@MockBean
	AirportRepository airportRepository;
	
	@MockBean
	CountryRepository countryRepository;
	
	@MockBean
	EntityManager em;
	
	@MockBean
	EntityManagerFactory entityManagerFactory;
	
	@Mock
	private Query query;
	
	private static final Long AIRPORT_ID = 2123L;
	private static final String COUNTRY_CODE = "N";
	private static final String AIRPORT_NAME = "schiphol";
	private static final String COUNTRY_NAME = "Netherlands";
	private static final String OFFSET = "0";
	private static final String LIMIT = "2";
	
	@BeforeEach
    public void setup() {
        when(entityManagerFactory.createEntityManager()).thenReturn(em);
    }
	
	/**
	 * Test case : Retrieve Airport based on airportId successfully
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testRetrieveAirportByIdSuccess() throws Exception {
		reset(airportRepository);
		
		Airport airport = AirportManagementTestUtil.getAirport(AIRPORT_ID);
		when(airportRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(airport));
		
		this.mockMvc.perform(get("/airport-management/v1/airports/{airportId}", AIRPORT_ID))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.airportId", is(2123)))
		.andExpect(jsonPath("$.name", is("Schiphol")));
	}
	
	/**
	 * Test case : Missing airportId in Retrieve Airport based on airportId API
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testRetrieveAirportByIdMissingAirportId() throws Exception {
		reset(airportRepository);
		
		this.mockMvc.perform(get("/airport-management/v1/airports/{airportId}", " "))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.code", is("400001")));
	}
	
	/**
	 * Test case : Invalid value for airportId in Retrieve Airport based on airportId API
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testRetrieveAirportByIdInvalidAirportId() throws Exception {
		reset(airportRepository);
		
		this.mockMvc.perform(get("/airport-management/v1/airports/{airportId}", "abdj"))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.code", is("400002")));
	}
	
	/**
	 * Test case : Airport not found in Retrieve Airport based on airportId API
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testRetrieveAirportByIdNotFound() throws Exception {
		reset(airportRepository);
		
		when(airportRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
		
		this.mockMvc.perform(get("/airport-management/v1/airports/{airportId}", AIRPORT_ID))
		.andExpect(status().isNotFound())
		.andExpect(jsonPath("$.code", is("404001")));
	}
	
	/**
	 * Test case : Retrieve runways for airport based on airportId successfully
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testRetrieveRunwayForAirportSuccess() throws Exception {
		reset(airportRepository);
		
		Airport airport = AirportManagementTestUtil.getAirport(AIRPORT_ID);
		when(airportRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(airport));
		
		this.mockMvc.perform(get("/airport-management/v1/airports/{airportId}/runways", AIRPORT_ID))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(2)))
		.andExpect(jsonPath("$[0].runwayId", is(11223)))
		.andExpect(jsonPath("$[1].runwayId", is(11224)));
	}
	
	/**
	 * Test case : Retrieve runways for airport based on airportId with no
	 * runway in response
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testRetrieveRunwayForAirportSuccessNoRunway() throws Exception {
		reset(airportRepository);
		
		Airport airport = AirportManagementTestUtil.getAirport(AIRPORT_ID);
		airport.setRunways(null);
		when(airportRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(airport));
		
		this.mockMvc.perform(get("/airport-management/v1/airports/{airportId}/runways", AIRPORT_ID))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(0)));
	}
	
	/**
	 * Test case : Missing airportId in Retrieve runways for given airportId
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testRetrieveRunwayForAirportMissingAirportId() throws Exception {
		reset(airportRepository);
		
		this.mockMvc.perform(get("/airport-management/v1/airports/{airportId}/runways", " "))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.code", is("400001")));
	}
	
	/**
	 * Test case : Airport not found for Retrieve runways based on airportId
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testRetrieveRunwayForAirportNotFound() throws Exception {
		reset(airportRepository);
		
		when(airportRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());
		
		this.mockMvc.perform(get("/airport-management/v1/airports/{airportId}/runways", AIRPORT_ID))
		.andExpect(status().isNotFound())
		.andExpect(jsonPath("$.code", is("404001")));
	}
	
	/**
	 * Test case : Search Airport with countryCode, offset, limit, sortBy and
	 * sortOrder as ASC in request
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testSearchAirportByCountryCodeWithOffsetLimitSortBySortOrder() throws Exception {
		reset(airportRepository);
		
		List<Airport> airports = AirportManagementTestUtil.getAirportList();
		when(airportRepository.countByCountry_CountryCodeContainingIgnoreCase(ArgumentMatchers.anyString())).thenReturn(10L);
		when(airportRepository.findAllByCountry_CountryCodeContainingIgnoreCase(ArgumentMatchers.anyString(), 
				ArgumentMatchers.any(Pageable.class))).thenReturn(airports);
		
		this.mockMvc.perform(get("/airport-management/v1/airports")
				.queryParam(Constants.COUNTRY_COUNTRY_CODE, COUNTRY_CODE)
				.queryParam(Constants.OFFSET, OFFSET)
				.queryParam(Constants.LIMIT, LIMIT)
				.queryParam(Constants.SORT_BY, Constants.COUNTRY_COUNTRY_CODE)
				.queryParam(Constants.SORT_ORDER, Constants.ASC))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.totalNumberOfRecords", is(10)))
		.andExpect(jsonPath("$.airports", Matchers.hasSize(2)))
		.andExpect(jsonPath("$.airports[0].airportId", is(2132)))
		.andExpect(jsonPath("$.airports[1].airportId", is(2133)));
	}
	
	/**
	 * Test case : Search Airport with countryCode, offset, limit, sortBy and
	 * sortOrder as DESC in request
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testSearchAirportByCountryCodeWithOffsetLimitSortBySortOrderDesc() throws Exception {
		reset(airportRepository);
		
		List<Airport> airports = AirportManagementTestUtil.getAirportList();
		when(airportRepository.countByCountry_CountryCodeContainingIgnoreCase(ArgumentMatchers.anyString())).thenReturn(10L);
		when(airportRepository.findAllByCountry_CountryCodeContainingIgnoreCase(ArgumentMatchers.anyString(), 
				ArgumentMatchers.any(Pageable.class))).thenReturn(airports);
		
		this.mockMvc.perform(get("/airport-management/v1/airports")
				.queryParam(Constants.COUNTRY_COUNTRY_CODE, COUNTRY_CODE)
				.queryParam(Constants.OFFSET, OFFSET)
				.queryParam(Constants.LIMIT, LIMIT)
				.queryParam(Constants.SORT_BY, Constants.COUNTRY_COUNTRY_CODE)
				.queryParam(Constants.SORT_ORDER, Constants.DESC))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.totalNumberOfRecords", is(10)))
		.andExpect(jsonPath("$.airports", Matchers.hasSize(2)))
		.andExpect(jsonPath("$.airports[0].airportId", is(2132)))
		.andExpect(jsonPath("$.airports[1].airportId", is(2133)));
	}
	
	/**
	 * Test case : Search Airport with countryCode without any pagination
	 * and sorting input
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testSearchAirportByCountryCode() throws Exception {
		reset(airportRepository);
		
		List<Airport> airports = AirportManagementTestUtil.getAirportList();
		when(airportRepository.countByCountry_CountryCodeContainingIgnoreCase(ArgumentMatchers.anyString())).thenReturn(2L);
		when(airportRepository.findAllByCountry_CountryCodeContainingIgnoreCase(ArgumentMatchers.anyString(), 
				ArgumentMatchers.any(Pageable.class))).thenReturn(airports);
		
		this.mockMvc.perform(get("/airport-management/v1/airports")
				.queryParam(Constants.COUNTRY_COUNTRY_CODE, COUNTRY_CODE))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.totalNumberOfRecords", is(2)))
		.andExpect(jsonPath("$.airports", Matchers.hasSize(2)))
		.andExpect(jsonPath("$.airports[0].airportId", is(2132)))
		.andExpect(jsonPath("$.airports[1].airportId", is(2133)));
	}
	
	/**
	 * Test case : Search Airport with countryCode but no records are found
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testSearchAirportByCountryCodeNoRecordsFound() throws Exception {
		reset(airportRepository);
		
		when(airportRepository.countByCountry_CountryCodeContainingIgnoreCase(ArgumentMatchers.anyString())).thenReturn(0L);
		
		this.mockMvc.perform(get("/airport-management/v1/airports")
				.queryParam(Constants.COUNTRY_COUNTRY_CODE, COUNTRY_CODE))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.totalNumberOfRecords", is(0)))
		.andExpect(jsonPath("$.airports", Matchers.hasSize(0)));
	}
	
	/**
	 * Test case : Search Airport with country's name, offset, limit, sortBy and
	 * sortOrder as ASC in request
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testSearchAirportByNameWithOffsetLimitSortBySortOrder() throws Exception {
		reset(airportRepository);
		
		List<Airport> airports = AirportManagementTestUtil.getAirportList();
		when(airportRepository.countByCountry_NameContainingIgnoreCase(ArgumentMatchers.anyString())).thenReturn(5L);
		when(airportRepository.findAllByCountry_NameContainingIgnoreCase(ArgumentMatchers.anyString(), 
				ArgumentMatchers.any(Pageable.class))).thenReturn(airports);
		
		this.mockMvc.perform(get("/airport-management/v1/airports")
				.queryParam(Constants.COUNTRY_NAME, COUNTRY_NAME)
				.queryParam(Constants.OFFSET, OFFSET)
				.queryParam(Constants.LIMIT, LIMIT)
				.queryParam(Constants.SORT_BY, Constants.COUNTRY_NAME)
				.queryParam(Constants.SORT_ORDER, Constants.ASC))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.totalNumberOfRecords", is(5)))
		.andExpect(jsonPath("$.airports", Matchers.hasSize(2)))
		.andExpect(jsonPath("$.airports[0].airportId", is(2132)))
		.andExpect(jsonPath("$.airports[1].airportId", is(2133)));
	}
	
	/**
	 * Test case : Search Airport with country's name
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testSearchAirportByCountryName() throws Exception {
		reset(airportRepository);
		
		List<Airport> airports = AirportManagementTestUtil.getAirportList();
		when(airportRepository.countByCountry_NameContainingIgnoreCase(ArgumentMatchers.anyString())).thenReturn(2L);
		when(airportRepository.findAllByCountry_NameContainingIgnoreCase(ArgumentMatchers.anyString(), 
				ArgumentMatchers.any(Pageable.class))).thenReturn(airports);
		
		this.mockMvc.perform(get("/airport-management/v1/airports")
				.queryParam(Constants.COUNTRY_NAME, COUNTRY_NAME))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.totalNumberOfRecords", is(2)))
		.andExpect(jsonPath("$.airports", Matchers.hasSize(2)))
		.andExpect(jsonPath("$.airports[0].airportId", is(2132)))
		.andExpect(jsonPath("$.airports[1].airportId", is(2133)));
	}
	
	/**
	 * Test case : Search Airport with country's name but no records found
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testSearchAirportByCountryNameNoRecordsFound() throws Exception {
		reset(airportRepository);
		
		when(airportRepository.countByCountry_NameContainingIgnoreCase(ArgumentMatchers.anyString())).thenReturn(0L);
		
		this.mockMvc.perform(get("/airport-management/v1/airports")
				.queryParam(Constants.COUNTRY_NAME, COUNTRY_NAME))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.totalNumberOfRecords", is(0)))
		.andExpect(jsonPath("$.airports", Matchers.hasSize(0)));
	}
	
	/**
	 * Test case : Search Airport with countryCode and country's name
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testSearchAirportByCountryCodeAndCountryName() throws Exception {
		reset(airportRepository);
		
		List<Airport> airports = AirportManagementTestUtil.getAirportList();
		when(airportRepository.countByCountry_CountryCodeContainingIgnoreCaseAndCountry_NameContainingIgnoreCase(ArgumentMatchers
				.anyString(), ArgumentMatchers.anyString())).thenReturn(10L);
		when(airportRepository.findAllByCountry_CountryCodeContainingIgnoreCaseAndCountry_NameContainingIgnoreCase(ArgumentMatchers
				.anyString(), ArgumentMatchers.anyString(), ArgumentMatchers.any(Pageable.class))).thenReturn(airports);
		
		this.mockMvc.perform(get("/airport-management/v1/airports")
				.queryParam(Constants.COUNTRY_COUNTRY_CODE, COUNTRY_CODE)
				.queryParam(Constants.COUNTRY_NAME, COUNTRY_NAME)
				.queryParam(Constants.OFFSET, OFFSET)
				.queryParam(Constants.LIMIT, LIMIT))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.totalNumberOfRecords", is(10)))
		.andExpect(jsonPath("$.airports", Matchers.hasSize(2)))
		.andExpect(jsonPath("$.airports[0].airportId", is(2132)))
		.andExpect(jsonPath("$.airports[1].airportId", is(2133)));
	}
	
	/**
	 * Test case : Search Airport with countryCode, country's name but no records
	 * found
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testSearchAirportByCountryCodeAndCountryNameNoRecordsFound() throws Exception {
		reset(airportRepository);
		
		when(airportRepository.countByCountry_CountryCodeContainingIgnoreCaseAndCountry_NameContainingIgnoreCase(ArgumentMatchers
				.anyString(), ArgumentMatchers.anyString())).thenReturn(0L);
		
		this.mockMvc.perform(get("/airport-management/v1/airports")
				.queryParam(Constants.COUNTRY_COUNTRY_CODE, COUNTRY_CODE)
				.queryParam(Constants.COUNTRY_NAME, COUNTRY_NAME))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.totalNumberOfRecords", is(0)))
		.andExpect(jsonPath("$.airports", Matchers.hasSize(0)));
	}
	
	/**
	 * Test case : Search Airport without any filters
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testSearchAirport() throws Exception {
		reset(airportRepository);
		
		List<Airport> airports = AirportManagementTestUtil.getAirportList();
		when(airportRepository.count()).thenReturn(10L);
		when(airportRepository.findAll(ArgumentMatchers.any(Pageable.class))).thenReturn(new PageImpl<Airport>(airports));
		
		this.mockMvc.perform(get("/airport-management/v1/airports")
				.queryParam(Constants.OFFSET, OFFSET)
				.queryParam(Constants.LIMIT, LIMIT))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.totalNumberOfRecords", is(10)))
		.andExpect(jsonPath("$.airports", Matchers.hasSize(2)))
		.andExpect(jsonPath("$.airports[0].airportId", is(2132)))
		.andExpect(jsonPath("$.airports[1].airportId", is(2133)));
	}
	
	/**
	 * Test case : Invalid sortBy provided in search airport request
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testSearchAirportInvalidSortBy() throws Exception {
		reset(airportRepository);
		
		this.mockMvc.perform(get("/airport-management/v1/airports")
				.queryParam(Constants.SORT_BY, Constants.SORT_BY)
				.queryParam(Constants.SORT_ORDER, Constants.ASC))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.code", is("400002")));
	}
	
	/**
	 * Test case : Invalid sortOrder provided in search airport request
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testSearchAirportInvalidSortOrder() throws Exception {
		reset(airportRepository);
		
		this.mockMvc.perform(get("/airport-management/v1/airports")
				.queryParam(Constants.SORT_BY, Constants.COUNTRY_COUNTRY_CODE)
				.queryParam(Constants.SORT_ORDER, Constants.SORT_ORDER))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.code", is("400002")));
	}
	
	/**
	 * Test case : Search Country based on country code
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testSearchCountryByCountryCode() throws Exception {
		reset(countryRepository);
		
		List<Country> countries = AirportManagementTestUtil.getCountryList();
		when(countryRepository.findByCountryCodeContainingIgnoreCase(ArgumentMatchers.anyString())).thenReturn(countries);
		
		this.mockMvc.perform(get("/airport-management/v1/countries")
				.queryParam(Constants.COUNTRY_CODE, COUNTRY_CODE))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(2)))
		.andExpect(jsonPath("$[0].countryId", is(101)))
		.andExpect(jsonPath("$[0].countryCode", is("NL")))
		.andExpect(jsonPath("$[1].countryId", is(102)))
		.andExpect(jsonPath("$[1].countryCode", is("NA")));
	}
	
	/**
	 * Test case : Search country based on country code but no records found
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testSearchCountryByCountryCodeNoRecordsFound() throws Exception {
		reset(countryRepository);
		
		when(countryRepository.findByCountryCodeContainingIgnoreCase(ArgumentMatchers.anyString())).thenReturn(null);
		
		this.mockMvc.perform(get("/airport-management/v1/countries")
				.queryParam(Constants.COUNTRY_CODE, "NLEW"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(0)));
	}
	
	/**
	 * Test case : Search Country by country's name
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testSearchCountryByName() throws Exception {
		reset(countryRepository);
		
		List<Country> countries = AirportManagementTestUtil.getCountryList();
		when(countryRepository.findByNameContainingIgnoreCase(ArgumentMatchers.anyString())).thenReturn(countries);
		
		this.mockMvc.perform(get("/airport-management/v1/countries")
				.queryParam(Constants.NAME, COUNTRY_NAME))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(2)))
		.andExpect(jsonPath("$[0].countryId", is(101)))
		.andExpect(jsonPath("$[0].countryCode", is("NL")))
		.andExpect(jsonPath("$[1].countryId", is(102)))
		.andExpect(jsonPath("$[1].countryCode", is("NA")));
	}
	
	/**
	 * Test case : Search Country by country's name but no records found
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testSearchCountryByNameNoRecords_Found() throws Exception {
		reset(countryRepository);
		
		when(countryRepository.findByNameContainingIgnoreCase(ArgumentMatchers.anyString())).thenReturn(null);
		
		this.mockMvc.perform(get("/airport-management/v1/countries")
				.queryParam(Constants.NAME, COUNTRY_NAME))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(0)));
	}
	
	/**
	 * Test case : Search Country by country code and country's name
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testSearchCountryByCountryCodeAndName() throws Exception {
		reset(countryRepository);
		
		List<Country> countries = AirportManagementTestUtil.getCountryList();
		when(countryRepository.findByCountryCodeContainingIgnoreCaseAndNameContainingIgnoreCase(ArgumentMatchers
				.anyString(), ArgumentMatchers.anyString())).thenReturn(countries);
		
		this.mockMvc.perform(get("/airport-management/v1/countries")
				.queryParam(Constants.COUNTRY_CODE, COUNTRY_CODE)
				.queryParam(Constants.NAME, COUNTRY_NAME))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(2)))
		.andExpect(jsonPath("$[0].countryId", is(101)))
		.andExpect(jsonPath("$[0].countryCode", is("NL")))
		.andExpect(jsonPath("$[1].countryId", is(102)))
		.andExpect(jsonPath("$[1].countryCode", is("NA")));
	}
	
	/**
	 * Test case : Search Country by country code and country's name but
	 * no records are found
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testSearchCountryByCountryCodeAndNameNoRecordsFound() throws Exception {
		reset(countryRepository);
		
		when(countryRepository.findByCountryCodeContainingIgnoreCaseAndNameContainingIgnoreCase(ArgumentMatchers
				.anyString(), ArgumentMatchers.anyString())).thenReturn(null);
		
		this.mockMvc.perform(get("/airport-management/v1/countries")
				.queryParam(Constants.COUNTRY_CODE, COUNTRY_CODE)
				.queryParam(Constants.NAME, COUNTRY_NAME))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(0)));
	}
	
	/**
	 * Test case : Retrieve all countries
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testSearchCountry() throws Exception {
		reset(countryRepository);
		
		List<Country> countries = AirportManagementTestUtil.getCountryList();
		when(countryRepository.findAll()).thenReturn(countries);
		
		this.mockMvc.perform(get("/airport-management/v1/countries"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(2)))
		.andExpect(jsonPath("$[0].countryId", is(101)))
		.andExpect(jsonPath("$[0].countryCode", is("NL")))
		.andExpect(jsonPath("$[1].countryId", is(102)))
		.andExpect(jsonPath("$[1].countryCode", is("NA")));
	}
	
	/**
	 * Test case : Search all countries but no record found
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testSearchCountryNoRecordsFound() throws Exception {
		reset(countryRepository);
		
		when(countryRepository.findAll()).thenReturn(null);
		
		this.mockMvc.perform(get("/airport-management/v1/countries"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(0)));
	}
	
	/**
	 * Test case : Retrieve country aggregation data with limit coming in
	 * request
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testRetrieveCountryAggregationDataWithLimit() throws Exception {
		reset(em);
		
		List<Object[]> aggregationData = AirportManagementTestUtil.getCountryAggregationData();
		
		when(em.createNativeQuery(ArgumentMatchers.anyString())).thenReturn(query);
		when(query.getResultList()).thenReturn(aggregationData);
		
		this.mockMvc.perform(get("/airport-management/v1/countries/aggregation")
				.queryParam(Constants.GROUP_BY, Constants.AIRPORT)
				.queryParam(Constants.AGGREGATION, Constants.COUNT)
				.queryParam(Constants.SORT_ORDER, Constants.DESC)
				.queryParam(Constants.LIMIT, "3"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(3)))
		.andExpect(jsonPath("$[0].countryId", is(101)))
		.andExpect(jsonPath("$[0].countryCode", is("US")))
		.andExpect(jsonPath("$[1].countryId", is(102)))
		.andExpect(jsonPath("$[1].countryCode", is("SA")))
		.andExpect(jsonPath("$[2].countryId", is(103)))
		.andExpect(jsonPath("$[2].countryCode", is("IN")));
	}
	
	/**
	 * Test case : Retrieve Country Aggregation without any limit
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testRetrieveCountryAggregationData() throws Exception {
		reset(em);
		
		List<Object[]> aggregationData = AirportManagementTestUtil.getCountryAggregationData();
		
		when(em.createNativeQuery(ArgumentMatchers.anyString())).thenReturn(query);
		when(query.getResultList()).thenReturn(aggregationData);
		
		this.mockMvc.perform(get("/airport-management/v1/countries/aggregation")
				.queryParam(Constants.GROUP_BY, Constants.AIRPORT)
				.queryParam(Constants.AGGREGATION, Constants.COUNT)
				.queryParam(Constants.SORT_ORDER, Constants.DESC))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(3)))
		.andExpect(jsonPath("$[0].countryId", is(101)))
		.andExpect(jsonPath("$[0].countryCode", is("US")))
		.andExpect(jsonPath("$[1].countryId", is(102)))
		.andExpect(jsonPath("$[1].countryCode", is("SA")))
		.andExpect(jsonPath("$[2].countryId", is(103)))
		.andExpect(jsonPath("$[2].countryCode", is("IN")));
	}
	
	/**
	 * Test case : Retrieve country aggregation data when no data is found
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testRetrieveCountryAggregationDataNoDataFound() throws Exception {
		reset(em);
		
		when(em.createNativeQuery(ArgumentMatchers.anyString())).thenReturn(query);
		when(query.getResultList()).thenReturn(null);
		
		this.mockMvc.perform(get("/airport-management/v1/countries/aggregation")
				.queryParam(Constants.GROUP_BY, Constants.AIRPORT)
				.queryParam(Constants.AGGREGATION, Constants.COUNT)
				.queryParam(Constants.SORT_ORDER, Constants.DESC)
				.queryParam(Constants.LIMIT, "3"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$", Matchers.hasSize(0)));
	}
	
	/**
	 * Test case : Missing groupBy in retrieve country aggregation request
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testRetrieveCountryAggregationDataMissingGroupBy() throws Exception {
		
		this.mockMvc.perform(get("/airport-management/v1/countries/aggregation")
				.queryParam(Constants.AGGREGATION, Constants.COUNT))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.code", is("400001")));
	}
	
	/**
	 * Test case : Invalid groupBy provided in retrieve country aggregation
	 * request
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testRetrieveCountryAggregationDataInvalidGroupBy() throws Exception {
		
		this.mockMvc.perform(get("/airport-management/v1/countries/aggregation")
				.queryParam(Constants.GROUP_BY, Constants.COUNTRY_CODE)
				.queryParam(Constants.AGGREGATION, Constants.COUNT))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.code", is("400002")));
	}
	
	/**
	 * Test case : Missing aggregation in retrieve country aggregation data request
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testRetrieveCountryAggregationDataMissingAggregation() throws Exception {
		
		this.mockMvc.perform(get("/airport-management/v1/countries/aggregation")
				.queryParam(Constants.GROUP_BY, Constants.AIRPORT))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.code", is("400001")));
	}
	
	/**
	 * Test case : Invalid aggregation provided in retrieve country 
	 * aggregation data request
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testRetrieveCountryAggregationDataInvalidAggregation() throws Exception {
		
		this.mockMvc.perform(get("/airport-management/v1/countries/aggregation")
				.queryParam(Constants.GROUP_BY, Constants.AIRPORT)
				.queryParam(Constants.AGGREGATION, Constants.COUNTRY_CODE))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.code", is("400002")));
	}
	
	/**
	 * Test case : Invalid sort order provided in retrieve country aggregation data
	 * request
	 * 
	 * @throws Exception Thrown when exception occurs during execution
	 * of test case.
	 */
	@Test
	void testRetrieveCountryAggregationDataInvalidSortOrder() throws Exception {
		
		this.mockMvc.perform(get("/airport-management/v1/countries/aggregation")
				.queryParam(Constants.GROUP_BY, Constants.AIRPORT)
				.queryParam(Constants.AGGREGATION, Constants.COUNT)
				.queryParam(Constants.SORT_ORDER, Constants.COUNT))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.code", is("400002")));
	}

}
