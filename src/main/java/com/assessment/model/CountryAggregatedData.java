package com.assessment.model;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * CountryAggregatedData
 */
@Validated

public class CountryAggregatedData   {
  @JsonProperty("countryId")
  private Long countryId = null;

  @JsonProperty("countryCode")
  private String countryCode = null;

  @JsonProperty("aggregatedData")
  @Valid
  private List<AggregatedData> aggregatedData = null;

  public CountryAggregatedData countryId(Long countryId) {
    this.countryId = countryId;
    return this;
  }

  /**
   * Unique identifier of the country
   * @return countryId
  **/
  @ApiModelProperty(value = "Unique identifier of the country")


  public Long getCountryId() {
    return countryId;
  }

  public void setCountryId(Long countryId) {
    this.countryId = countryId;
  }

  public CountryAggregatedData countryCode(String countryCode) {
    this.countryCode = countryCode;
    return this;
  }

  /**
   * Two letter ISO code for the country
   * @return countryCode
  **/
  @ApiModelProperty(value = "Two letter ISO code for the country")


  public String getCountryCode() {
    return countryCode;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public CountryAggregatedData aggregatedData(List<AggregatedData> aggregatedData) {
    this.aggregatedData = aggregatedData;
    return this;
  }

  public CountryAggregatedData addAggregatedDataItem(AggregatedData aggregatedDataItem) {
    if (this.aggregatedData == null) {
      this.aggregatedData = new ArrayList<>();
    }
    this.aggregatedData.add(aggregatedDataItem);
    return this;
  }

  /**
   * Aggregated data requested for the country
   * @return aggregatedData
  **/
  @ApiModelProperty(value = "Aggregated data requested for the country")

  @Valid

  public List<AggregatedData> getAggregatedData() {
    return aggregatedData;
  }

  public void setAggregatedData(List<AggregatedData> aggregatedData) {
    this.aggregatedData = aggregatedData;
  }
}

