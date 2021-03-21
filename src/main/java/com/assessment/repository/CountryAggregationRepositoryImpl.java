package com.assessment.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

/**
 * Custom Repository class to perform aggregration operations
 * for Country entity
 *
 */
@Repository
@SuppressWarnings("unchecked")
public class CountryAggregationRepositoryImpl implements CountryAggregationRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	@Value("${spring.hibernate.default_schema}")
	private String schemaName;
	
	private String aggregateCountryByAirportCountQuery = null;
	
	/**
	 * Method to retrieve the count of airports per country with
	 * sorting query
	 * 
	 * @return String - Retrieved query
	 */
	private String getAggregateCountryByAirportCountQuery() {
		
		if(StringUtils.isBlank(aggregateCountryByAirportCountQuery)) {
			String query = "select ct.object_id as object_id, ct.country_code as country_code, count(ct.country_code) as count "
					+ "from ${schemaName}.airport ar, ${schemaName}.country ct where ar.fk2country = "
					+ "ct.object_id group by ct.country_code, ct.object_id order by count(ct.country_code) ";
			aggregateCountryByAirportCountQuery = StringUtils.replace(query, "${schemaName}", schemaName);
		}
		
		return aggregateCountryByAirportCountQuery;
	}

	@Override
	public List<Object[]> aggregateCountByAirport(String sortOrder, Integer limit) {
		
		Query query = em.createNativeQuery(StringUtils.join(getAggregateCountryByAirportCountQuery(), 
				sortOrder));
		query.setMaxResults(limit);
		
		return query.getResultList();
	}

}
