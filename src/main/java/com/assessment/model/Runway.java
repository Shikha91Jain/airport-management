package com.assessment.model;

import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * Runway
 */
@Validated

public class Runway   {
  @JsonProperty("runwayId")
  private Long runwayId = null;

  @JsonProperty("length")
  private String length = null;

  @JsonProperty("width")
  private String width = null;

  @JsonProperty("surface")
  private String surface = null;

  @JsonProperty("lighted")
  private Boolean lighted = null;

  @JsonProperty("closed")
  private Boolean closed = null;

  @JsonProperty("lengthLatitude")
  private String lengthLatitude = null;

  @JsonProperty("lengthLongitude")
  private String lengthLongitude = null;

  @JsonProperty("lengthElevation")
  private String lengthElevation = null;

  @JsonProperty("lengthHeading")
  private String lengthHeading = null;

  @JsonProperty("lengthDisplacementThreshold")
  private String lengthDisplacementThreshold = null;

  @JsonProperty("heightLatitude")
  private String heightLatitude = null;

  @JsonProperty("heightLongitude")
  private String heightLongitude = null;

  @JsonProperty("heightElevation")
  private String heightElevation = null;

  @JsonProperty("heightHeading")
  private String heightHeading = null;

  @JsonProperty("heightDisplacementThreshold")
  private String heightDisplacementThreshold = null;

  public Runway runwayId(Long runwayId) {
    this.runwayId = runwayId;
    return this;
  }

  /**
   * Unique identifier of runway.
   * @return runwayId
  **/
  @ApiModelProperty(value = "Unique identifier of runway.")


  public Long getRunwayId() {
    return runwayId;
  }

  public void setRunwayId(Long runwayId) {
    this.runwayId = runwayId;
  }

  public Runway length(String length) {
    this.length = length;
    return this;
  }

  /**
   * Length of the runway Unit: ft (feets)
   * @return length
  **/
  @ApiModelProperty(value = "Length of the runway Unit: ft (feets)")


  public String getLength() {
    return length;
  }

  public void setLength(String length) {
    this.length = length;
  }

  public Runway width(String width) {
    this.width = width;
    return this;
  }

  /**
   * Width of the runway. Unit: ft (feets)
   * @return width
  **/
  @ApiModelProperty(value = "Width of the runway. Unit: ft (feets)")


  public String getWidth() {
    return width;
  }

  public void setWidth(String width) {
    this.width = width;
  }

  public Runway surface(String surface) {
    this.surface = surface;
    return this;
  }

  /**
   * Surface details of the runway
   * @return surface
  **/
  @ApiModelProperty(value = "Surface details of the runway")


  public String getSurface() {
    return surface;
  }

  public void setSurface(String surface) {
    this.surface = surface;
  }

  public Runway lighted(Boolean lighted) {
    this.lighted = lighted;
    return this;
  }

  /**
   * Indicates if the airport is lighted
   * @return lighted
  **/
  @ApiModelProperty(value = "Indicates if the airport is lighted")


  public Boolean isLighted() {
    return lighted;
  }

  public void setLighted(Boolean lighted) {
    this.lighted = lighted;
  }

  public Runway closed(Boolean closed) {
    this.closed = closed;
    return this;
  }

  /**
   * Indicates if the airport is closed.
   * @return closed
  **/
  @ApiModelProperty(value = "Indicates if the airport is closed.")


  public Boolean isClosed() {
    return closed;
  }

  public void setClosed(Boolean closed) {
    this.closed = closed;
  }

  public Runway lengthLatitude(String lengthLatitude) {
    this.lengthLatitude = lengthLatitude;
    return this;
  }

  /**
   * Latitude of the length of runway. Unit: Degrees
   * @return lengthLatitude
  **/
  @ApiModelProperty(value = "Latitude of the length of runway. Unit: Degrees")


  public String getLengthLatitude() {
    return lengthLatitude;
  }

  public void setLengthLatitude(String lengthLatitude) {
    this.lengthLatitude = lengthLatitude;
  }

  public Runway lengthLongitude(String lengthLongitude) {
    this.lengthLongitude = lengthLongitude;
    return this;
  }

  /**
   * Longitude of the length of runway. Unit: Degrees
   * @return lengthLongitude
  **/
  @ApiModelProperty(value = "Longitude of the length of runway. Unit: Degrees")


  public String getLengthLongitude() {
    return lengthLongitude;
  }

  public void setLengthLongitude(String lengthLongitude) {
    this.lengthLongitude = lengthLongitude;
  }

  public Runway lengthElevation(String lengthElevation) {
    this.lengthElevation = lengthElevation;
    return this;
  }

  /**
   * Elevation of the length of runway. Unit: ft (feets)
   * @return lengthElevation
  **/
  @ApiModelProperty(value = "Elevation of the length of runway. Unit: ft (feets)")


  public String getLengthElevation() {
    return lengthElevation;
  }

  public void setLengthElevation(String lengthElevation) {
    this.lengthElevation = lengthElevation;
  }

  public Runway lengthHeading(String lengthHeading) {
    this.lengthHeading = lengthHeading;
    return this;
  }

  /**
   * Heading of the length of runway. Unit: Degrees
   * @return lengthHeading
  **/
  @ApiModelProperty(value = "Heading of the length of runway. Unit: Degrees")


  public String getLengthHeading() {
    return lengthHeading;
  }

  public void setLengthHeading(String lengthHeading) {
    this.lengthHeading = lengthHeading;
  }

  public Runway lengthDisplacementThreshold(String lengthDisplacementThreshold) {
    this.lengthDisplacementThreshold = lengthDisplacementThreshold;
    return this;
  }

  /**
   * Displacement threshold of length of runway. Unit: ft (feets)
   * @return lengthDisplacementThreshold
  **/
  @ApiModelProperty(value = "Displacement threshold of length of runway. Unit: ft (feets)")


  public String getLengthDisplacementThreshold() {
    return lengthDisplacementThreshold;
  }

  public void setLengthDisplacementThreshold(String lengthDisplacementThreshold) {
    this.lengthDisplacementThreshold = lengthDisplacementThreshold;
  }

  public Runway heightLatitude(String heightLatitude) {
    this.heightLatitude = heightLatitude;
    return this;
  }

  /**
   * Latitude of the height of runway Unit: Degrees
   * @return heightLatitude
  **/
  @ApiModelProperty(value = "Latitude of the height of runway Unit: Degrees")


  public String getHeightLatitude() {
    return heightLatitude;
  }

  public void setHeightLatitude(String heightLatitude) {
    this.heightLatitude = heightLatitude;
  }

  public Runway heightLongitude(String heightLongitude) {
    this.heightLongitude = heightLongitude;
    return this;
  }

  /**
   * Longitude of the height of runway Unit: Degrees
   * @return heightLongitude
  **/
  @ApiModelProperty(value = "Longitude of the height of runway Unit: Degrees")


  public String getHeightLongitude() {
    return heightLongitude;
  }

  public void setHeightLongitude(String heightLongitude) {
    this.heightLongitude = heightLongitude;
  }

  public Runway heightElevation(String heightElevation) {
    this.heightElevation = heightElevation;
    return this;
  }

  /**
   * Elevation of height of runway. Unit: ft (feets)
   * @return heightElevation
  **/
  @ApiModelProperty(value = "Elevation of height of runway. Unit: ft (feets)")


  public String getHeightElevation() {
    return heightElevation;
  }

  public void setHeightElevation(String heightElevation) {
    this.heightElevation = heightElevation;
  }

  public Runway heightHeading(String heightHeading) {
    this.heightHeading = heightHeading;
    return this;
  }

  /**
   * Heading of the height of runway. Unit: Degrees
   * @return heightHeading
  **/
  @ApiModelProperty(value = "Heading of the height of runway. Unit: Degrees")


  public String getHeightHeading() {
    return heightHeading;
  }

  public void setHeightHeading(String heightHeading) {
    this.heightHeading = heightHeading;
  }

  public Runway heightDisplacementThreshold(String heightDisplacementThreshold) {
    this.heightDisplacementThreshold = heightDisplacementThreshold;
    return this;
  }

  /**
   * Displacement threshold of height of runway. Unit: ft (feets)
   * @return heightDisplacementThreshold
  **/
  @ApiModelProperty(value = "Displacement threshold of height of runway. Unit: ft (feets)")


  public String getHeightDisplacementThreshold() {
    return heightDisplacementThreshold;
  }

  public void setHeightDisplacementThreshold(String heightDisplacementThreshold) {
    this.heightDisplacementThreshold = heightDisplacementThreshold;
  }
}

