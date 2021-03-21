package com.assessment.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * AirportResponse
 */
@Validated

public class AirportResponse   {
  @JsonProperty("totalNumberOfRecords")
  private Long totalNumberOfRecords = null;

  @JsonProperty("airports")
  @Valid
  private List<Airport> airports = null;

  public AirportResponse totalNumberOfRecords(Long totalNumberOfRecords) {
    this.totalNumberOfRecords = totalNumberOfRecords;
    return this;
  }

  /**
   * Total number of records found for given search criteria irrespective of offset and limit. This will be returned to facilitate pagination support.
   * @return totalNumberOfRecords
  **/
  @ApiModelProperty(value = "Total number of records found for given search criteria irrespective of offset and limit. This will be returned to facilitate pagination support.")


  public Long getTotalNumberOfRecords() {
    return totalNumberOfRecords;
  }

  public void setTotalNumberOfRecords(Long totalNumberOfRecords) {
    this.totalNumberOfRecords = totalNumberOfRecords;
  }

  public AirportResponse airports(List<Airport> airports) {
    this.airports = airports;
    return this;
  }

  public AirportResponse addAirportsItem(Airport airportsItem) {
    if (this.airports == null) {
      this.airports = new ArrayList<>();
    }
    this.airports.add(airportsItem);
    return this;
  }

  /**
   * List of airports found for given search criteria. If no records are found for given input, the API will return this as empty list
   * @return airports
  **/
  @ApiModelProperty(value = "List of airports found for given search criteria. If no records are found for given input, the API will return this as empty list")

  @Valid

  public List<Airport> getAirports() {
    return airports;
  }

  public void setAirports(List<Airport> airports) {
    this.airports = airports;
  }
}

