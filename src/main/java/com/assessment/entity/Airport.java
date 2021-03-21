package com.assessment.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the airport database table.
 * 
 */
@Entity
@NamedQuery(name="Airport.findAll", query="SELECT a FROM Airport a")
public class Airport implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="object_id")
	private Long objectId;

	@Column(name="airport_identifier")
	private String airportIdentifier;

	private String elevation;

	@Column(name="gps_code")
	private String gpsCode;

	@Column(name="home_link")
	private String homeLink;

	@Column(name="iata_code")
	private String iataCode;

	@Column(name="iso_region")
	private String isoRegion;

	private String keywords;

	private String latitude;

	@Column(name="local_code")
	private String localCode;

	private String longitude;

	private String municipality;

	private String name;

	@Column(name="scheduled_service")
	private String scheduledService;

	private String type;

	@Column(name="wikipedia_link")
	private String wikipediaLink;

	//bi-directional many-to-one association to Country
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="fk2country")
	private Country country;

	//bi-directional many-to-one association to Runway
	@OneToMany(mappedBy="airport")
	private List<Runway> runways;

	public Airport() {
		// default constructor for entity
	}

	public Long getObjectId() {
		return this.objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public String getAirportIdentifier() {
		return this.airportIdentifier;
	}

	public void setAirportIdentifier(String airportIdentifier) {
		this.airportIdentifier = airportIdentifier;
	}

	public String getElevation() {
		return this.elevation;
	}

	public void setElevation(String elevation) {
		this.elevation = elevation;
	}

	public String getGpsCode() {
		return this.gpsCode;
	}

	public void setGpsCode(String gpsCode) {
		this.gpsCode = gpsCode;
	}

	public String getHomeLink() {
		return this.homeLink;
	}

	public void setHomeLink(String homeLink) {
		this.homeLink = homeLink;
	}

	public String getIataCode() {
		return this.iataCode;
	}

	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}

	public String getIsoRegion() {
		return this.isoRegion;
	}

	public void setIsoRegion(String isoRegion) {
		this.isoRegion = isoRegion;
	}

	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLocalCode() {
		return this.localCode;
	}

	public void setLocalCode(String localCode) {
		this.localCode = localCode;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getMunicipality() {
		return this.municipality;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getScheduledService() {
		return this.scheduledService;
	}

	public void setScheduledService(String scheduledService) {
		this.scheduledService = scheduledService;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWikipediaLink() {
		return this.wikipediaLink;
	}

	public void setWikipediaLink(String wikipediaLink) {
		this.wikipediaLink = wikipediaLink;
	}

	public Country getCountry() {
		return this.country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Runway> getRunways() {
		if(this.runways == null) {
			this.runways = new ArrayList<>();
		}
		return this.runways;
	}

	public void setRunways(List<Runway> runways) {
		this.runways = runways;
	}

	public Runway addRunway(Runway runway) {
		getRunways().add(runway);
		runway.setAirport(this);

		return runway;
	}

	public Runway removeRunway(Runway runway) {
		getRunways().remove(runway);
		runway.setAirport(null);

		return runway;
	}

}