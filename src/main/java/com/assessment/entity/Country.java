package com.assessment.entity;

import java.io.Serializable;
import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The persistent class for the country database table.
 * 
 */
@Entity
@NamedQuery(name="Country.findAll", query="SELECT c FROM Country c")
public class Country implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="object_id")
	private Long objectId;

	private String continent;

	@Column(name="country_code")
	private String countryCode;

	private String keywords;

	private String name;

	@Column(name="wikipedia_link")
	private String wikipediaLink;

	//bi-directional many-to-one association to Airport
	@OneToMany(mappedBy="country")
	private List<Airport> airports;

	public Country() {
		// default constructor for entity
	}

	public Long getObjectId() {
		return this.objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public String getContinent() {
		return this.continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWikipediaLink() {
		return this.wikipediaLink;
	}

	public void setWikipediaLink(String wikipediaLink) {
		this.wikipediaLink = wikipediaLink;
	}

	public List<Airport> getAirports() {
		if(this.airports == null) {
			this.airports = new ArrayList<>();
		}
		return this.airports;
	}

	public void setAirports(List<Airport> airports) {
		this.airports = airports;
	}

	public Airport addAirport(Airport airport) {
		getAirports().add(airport);
		airport.setCountry(this);

		return airport;
	}

	public Airport removeAirport(Airport airport) {
		getAirports().remove(airport);
		airport.setCountry(null);

		return airport;
	}

}