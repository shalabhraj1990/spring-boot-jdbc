package com.spring.boot.jdbc.namedparameterJdbcTemplate.repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.spring.boot.jdbc.JdbcTemplate.repository.CityRepository;
import com.spring.boot.jdbc.JdbcTemplate.repository.CityRepsitoryImpl;
import com.spring.boot.jdbc.modal.City;

@Repository
public class CityNamedParameterRepositoryImpl implements CityRepository {
	@Autowired
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public City getCityById(int cityId) {
		final String sql = "select * from city where id = :cityId";
		Map<String, Integer> namedParameter = Collections.singletonMap("cityId", cityId);
		return namedParameterJdbcTemplate.queryForObject(sql, namedParameter, new CityRepsitoryImpl.RowMapperImpl());
	}

	@Override
	public List<City> getAllCityByCountryCode(String countryCode) {
		// TODO Auto-generated method stub
		final String sql = "select * from city where id = :countryCode";
		// Map<String, String> namedParameter = Collections.singletonMap("countryCode",
		// countryCode);
		SqlParameterSource namedParameter = new MapSqlParameterSource("countryCode", countryCode);
		return namedParameterJdbcTemplate.query(sql, namedParameter, new CityRepsitoryImpl.RowMapperImpl());
	}

	@Override
	public List<City> getAllCityByCountryCode(List<String> countryCode) {
		final String sql = "select * from city where countryCode in (:countryCode)";
		Map<String, List<String>> namedParameter = Collections.singletonMap("countryCode", countryCode);
		return namedParameterJdbcTemplate.query(sql, namedParameter, new CityRepsitoryImpl.RowMapperImpl());
	}

	@Override
	public int cityCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCityCountByCountryCode(String countryCode) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long populationByCountryCodeAndCity(String countryCode, String cityName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addCity(City city) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateCity(int cityId, String districtName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeCity(int cityId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void crateTable() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dripTable() {
		// TODO Auto-generated method stub

	}

}
