package com.assessment.model;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Country
 */
@Validated

public class Country   {
  @JsonProperty("countryId")
  private Long countryId = null;

  @JsonProperty("countryCode")
  private String countryCode = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("continent")
  private String continent = null;

  @JsonProperty("wikipediaLink")
  private String wikipediaLink = null;

  @JsonProperty("keywords")
  private String keywords = null;

  public Country countryId(Long countryId) {
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

  public Country countryCode(String countryCode) {
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

  public Country name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Name of the country
   * @return name
  **/
  @ApiModelProperty(value = "Name of the country")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Country continent(String continent) {
    this.continent = continent;
    return this;
  }

  /**
   * Continent under which the country is.
   * @return continent
  **/
  @ApiModelProperty(value = "Continent under which the country is.")


  public String getContinent() {
    return continent;
  }

  public void setContinent(String continent) {
    this.continent = continent;
  }

  public Country wikipediaLink(String wikipediaLink) {
    this.wikipediaLink = wikipediaLink;
    return this;
  }

  /**
   * Wikipedia URL for the country if present
   * @return wikipediaLink
  **/
  @ApiModelProperty(value = "Wikipedia URL for the country if present")


  public String getWikipediaLink() {
    return wikipediaLink;
  }

  public void setWikipediaLink(String wikipediaLink) {
    this.wikipediaLink = wikipediaLink;
  }

  public Country keywords(String keywords) {
    this.keywords = keywords;
    return this;
  }

  /**
   * Keywords for country if present
   * @return keywords
  **/
  @ApiModelProperty(value = "Keywords for country if present")


  public String getKeywords() {
    return keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }
}

