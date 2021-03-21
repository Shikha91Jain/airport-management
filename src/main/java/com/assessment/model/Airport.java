package com.assessment.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Airport
 */
@Validated

public class Airport   {
  @JsonProperty("airportId")
  private Long airportId = null;

  @JsonProperty("identifier")
  private String identifier = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("latitude")
  private String latitude = null;

  @JsonProperty("longitude")
  private String longitude = null;

  @JsonProperty("elevation")
  private String elevation = null;

  @JsonProperty("countryRef")
  private Country countryRef = null;

  @JsonProperty("isoRegion")
  private String isoRegion = null;

  @JsonProperty("municipality")
  private String municipality = null;

  @JsonProperty("scheduledService")
  private String scheduledService = null;

  @JsonProperty("gpsCode")
  private String gpsCode = null;

  @JsonProperty("iataCode")
  private String iataCode = null;

  @JsonProperty("localCode")
  private String localCode = null;

  @JsonProperty("homeLink")
  private String homeLink = null;

  @JsonProperty("wikipediaLink")
  private String wikipediaLink = null;

  @JsonProperty("keywords")
  private String keywords = null;

  @JsonProperty("runways")
  @Valid
  private List<Runway> runways = null;

  public Airport airportId(Long airportId) {
    this.airportId = airportId;
    return this;
  }

  /**
   * Unqiue identifier of the airport
   * @return airportId
  **/
  @ApiModelProperty(value = "Unqiue identifier of the airport")


  public Long getAirportId() {
    return airportId;
  }

  public void setAirportId(Long airportId) {
    this.airportId = airportId;
  }

  public Airport identifier(String identifier) {
    this.identifier = identifier;
    return this;
  }

  /**
   * Identifier code of the airport
   * @return identifier
  **/
  @ApiModelProperty(value = "Identifier code of the airport")


  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public Airport type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Indiciates the type of airport. Possible values- small_airport, balloonport, heliport, larget_airport, seaplane_base, medium_airport, closed
   * @return type
  **/
  @ApiModelProperty(value = "Indiciates the type of airport. Possible values- small_airport, balloonport, heliport, larget_airport, seaplane_base, medium_airport, closed")


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Airport name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the airport
   * @return name
  **/
  @ApiModelProperty(value = "Name of the airport")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Airport latitude(String latitude) {
    this.latitude = latitude;
    return this;
  }

  /**
   * Latitude of the airport in degrees.
   * @return latitude
  **/
  @ApiModelProperty(value = "Latitude of the airport in degrees.")


  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public Airport longitude(String longitude) {
    this.longitude = longitude;
    return this;
  }

  /**
   * Longitude of the airport in degrees.
   * @return longitude
  **/
  @ApiModelProperty(value = "Longitude of the airport in degrees.")


  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public Airport elevation(String elevation) {
    this.elevation = elevation;
    return this;
  }

  /**
   * Elevation of the airport in feet(ft).
   * @return elevation
  **/
  @ApiModelProperty(value = "Elevation of the airport in feet(ft).")


  public String getElevation() {
    return elevation;
  }

  public void setElevation(String elevation) {
    this.elevation = elevation;
  }

  public Airport countryRef(Country countryRef) {
    this.countryRef = countryRef;
    return this;
  }

  /**
   * Reference of the country in which airport is present
   * @return countryRef
  **/
  @ApiModelProperty(value = "Reference of the country in which airport is present")

  @Valid

  public Country getCountryRef() {
    return countryRef;
  }

  public void setCountryRef(Country countryRef) {
    this.countryRef = countryRef;
  }

  public Airport isoRegion(String isoRegion) {
    this.isoRegion = isoRegion;
    return this;
  }

  /**
   * ISO Region code in which airport is present.
   * @return isoRegion
  **/
  @ApiModelProperty(value = "ISO Region code in which airport is present.")


  public String getIsoRegion() {
    return isoRegion;
  }

  public void setIsoRegion(String isoRegion) {
    this.isoRegion = isoRegion;
  }

  public Airport municipality(String municipality) {
    this.municipality = municipality;
    return this;
  }

  /**
   * Municipality in which the airport is present.
   * @return municipality
  **/
  @ApiModelProperty(value = "Municipality in which the airport is present.")


  public String getMunicipality() {
    return municipality;
  }

  public void setMunicipality(String municipality) {
    this.municipality = municipality;
  }

  public Airport scheduledService(String scheduledService) {
    this.scheduledService = scheduledService;
    return this;
  }

  /**
   * Indicates if the scheduled services are available for the airport.
   * @return scheduledService
  **/
  @ApiModelProperty(value = "Indicates if the scheduled services are available for the airport.")


  public String getScheduledService() {
    return scheduledService;
  }

  public void setScheduledService(String scheduledService) {
    this.scheduledService = scheduledService;
  }

  public Airport gpsCode(String gpsCode) {
    this.gpsCode = gpsCode;
    return this;
  }

  /**
   * GPS code of the airport.
   * @return gpsCode
  **/
  @ApiModelProperty(value = "GPS code of the airport.")


  public String getGpsCode() {
    return gpsCode;
  }

  public void setGpsCode(String gpsCode) {
    this.gpsCode = gpsCode;
  }

  public Airport iataCode(String iataCode) {
    this.iataCode = iataCode;
    return this;
  }

  /**
   * IATA location identifier allocated to the airport.
   * @return iataCode
  **/
  @ApiModelProperty(value = "IATA location identifier allocated to the airport.")


  public String getIataCode() {
    return iataCode;
  }

  public void setIataCode(String iataCode) {
    this.iataCode = iataCode;
  }

  public Airport localCode(String localCode) {
    this.localCode = localCode;
    return this;
  }

  /**
   * Local code of the airport.
   * @return localCode
  **/
  @ApiModelProperty(value = "Local code of the airport.")


  public String getLocalCode() {
    return localCode;
  }

  public void setLocalCode(String localCode) {
    this.localCode = localCode;
  }

  public Airport homeLink(String homeLink) {
    this.homeLink = homeLink;
    return this;
  }

  /**
   * Airport's website URL.
   * @return homeLink
  **/
  @ApiModelProperty(value = "Airport's website URL.")


  public String getHomeLink() {
    return homeLink;
  }

  public void setHomeLink(String homeLink) {
    this.homeLink = homeLink;
  }

  public Airport wikipediaLink(String wikipediaLink) {
    this.wikipediaLink = wikipediaLink;
    return this;
  }

  /**
   * Wikipedia page URL for the airport.
   * @return wikipediaLink
  **/
  @ApiModelProperty(value = "Wikipedia page URL for the airport.")


  public String getWikipediaLink() {
    return wikipediaLink;
  }

  public void setWikipediaLink(String wikipediaLink) {
    this.wikipediaLink = wikipediaLink;
  }

  public Airport keywords(String keywords) {
    this.keywords = keywords;
    return this;
  }

  /**
   * Keywords for the airport
   * @return keywords
  **/
  @ApiModelProperty(value = "Keywords for the airport")


  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

  public Airport runways(List<Runway> runways) {
    this.runways = runways;
    return this;
  }

  public Airport addRunwaysItem(Runway runwaysItem) {
    if (this.runways == null) {
      this.runways = new ArrayList<Runway>();
    }
    this.runways.add(runwaysItem);
    return this;
  }

  /**
   * List of runways for the airport.
   * @return runways
  **/
  @ApiModelProperty(value = "List of runways for the airport.")

  @Valid

  public List<Runway> getRunways() {
    return runways;
  }

  public void setRunways(List<Runway> runways) {
    this.runways = runways;
  }
}

