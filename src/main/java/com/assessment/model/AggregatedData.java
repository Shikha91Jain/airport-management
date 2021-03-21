package com.assessment.model;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * AggregatedData
 */
@Validated

public class AggregatedData   {
  @JsonProperty("type")
  private String type = null;

  @JsonProperty("value")
  private String value = null;

  public AggregatedData type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Type of aggregrated data, for example count.
   * @return type
  **/
  @ApiModelProperty(example = "count", value = "Type of aggregrated data, for example count.")
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public AggregatedData value(String value) {
    this.value = value;
    return this;
  }

  /**
   * Aggregated value for the given type
   * @return value
  **/
  @ApiModelProperty(example = "1000", value = "Aggregated value for the given type")


  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}

