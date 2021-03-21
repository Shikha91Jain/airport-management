package com.assessment.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the runway database table.
 * 
 */
@Entity
@NamedQuery(name="Runway.findAll", query="SELECT r FROM Runway r")
public class Runway implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="object_id")
	private Long objectId;

	private Boolean closed;

	@Column(name="height_displacement_threshold")
	private String heightDisplacementThreshold;

	@Column(name="height_elevation")
	private String heightElevation;

	@Column(name="height_heading")
	private String heightHeading;

	@Column(name="height_identifier")
	private String heightIdentifier;

	@Column(name="height_latitude")
	private String heightLatitude;

	@Column(name="height_longitude")
	private String heightLongitude;

	private BigDecimal length;

	@Column(name="length_displacement_threshold")
	private String lengthDisplacementThreshold;

	@Column(name="length_elevation")
	private String lengthElevation;

	@Column(name="length_heading")
	private String lengthHeading;

	@Column(name="length_identifier")
	private String lengthIdentifier;

	@Column(name="length_latitude")
	private String lengthLatitude;

	@Column(name="length_longitude")
	private String lengthLongitude;

	private Boolean lighted;

	private String surface;

	private BigDecimal width;

	//bi-directional many-to-one association to Airport
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk2airport")
	private Airport airport;

	public Runway() {
		// default constructor for entity
	}

	public Long getObjectId() {
		return this.objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public Boolean getClosed() {
		return this.closed;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}

	public String getHeightDisplacementThreshold() {
		return this.heightDisplacementThreshold;
	}

	public void setHeightDisplacementThreshold(String heightDisplacementThreshold) {
		this.heightDisplacementThreshold = heightDisplacementThreshold;
	}

	public String getHeightElevation() {
		return this.heightElevation;
	}

	public void setHeightElevation(String heightElevation) {
		this.heightElevation = heightElevation;
	}

	public String getHeightHeading() {
		return this.heightHeading;
	}

	public void setHeightHeading(String heightHeading) {
		this.heightHeading = heightHeading;
	}

	public String getHeightIdentifier() {
		return this.heightIdentifier;
	}

	public void setHeightIdentifier(String heightIdentifier) {
		this.heightIdentifier = heightIdentifier;
	}

	public String getHeightLatitude() {
		return this.heightLatitude;
	}

	public void setHeightLatitude(String heightLatitude) {
		this.heightLatitude = heightLatitude;
	}

	public String getHeightLongitude() {
		return this.heightLongitude;
	}

	public void setHeightLongitude(String heightLongitude) {
		this.heightLongitude = heightLongitude;
	}

	public BigDecimal getLength() {
		return this.length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public String getLengthDisplacementThreshold() {
		return this.lengthDisplacementThreshold;
	}

	public void setLengthDisplacementThreshold(String lengthDisplacementThreshold) {
		this.lengthDisplacementThreshold = lengthDisplacementThreshold;
	}

	public String getLengthElevation() {
		return this.lengthElevation;
	}

	public void setLengthElevation(String lengthElevation) {
		this.lengthElevation = lengthElevation;
	}

	public String getLengthHeading() {
		return this.lengthHeading;
	}

	public void setLengthHeading(String lengthHeading) {
		this.lengthHeading = lengthHeading;
	}

	public String getLengthIdentifier() {
		return this.lengthIdentifier;
	}

	public void setLengthIdentifier(String lengthIdentifier) {
		this.lengthIdentifier = lengthIdentifier;
	}

	public String getLengthLatitude() {
		return this.lengthLatitude;
	}

	public void setLengthLatitude(String lengthLatitude) {
		this.lengthLatitude = lengthLatitude;
	}

	public String getLengthLongitude() {
		return this.lengthLongitude;
	}

	public void setLengthLongitude(String lengthLongitude) {
		this.lengthLongitude = lengthLongitude;
	}

	public Boolean getLighted() {
		return this.lighted;
	}

	public void setLighted(Boolean lighted) {
		this.lighted = lighted;
	}

	public String getSurface() {
		return this.surface;
	}

	public void setSurface(String surface) {
		this.surface = surface;
	}

	public BigDecimal getWidth() {
		return this.width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public Airport getAirport() {
		return this.airport;
	}

	public void setAirport(Airport airport) {
		this.airport = airport;
	}

}