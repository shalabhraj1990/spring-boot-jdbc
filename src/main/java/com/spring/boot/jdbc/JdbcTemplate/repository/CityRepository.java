package com.spring.boot.jdbc.JdbcTemplate.repository;

import java.util.List;

import com.spring.boot.jdbc.modal.City;

public interface CityRepository {
	City getCityById(final int cityId);

	List<City> getAllCityByCountryCode(String countryCode);

	List<City> getAllCityByCountryCode(List<String> countryCode);

	int cityCount();

	int getCityCountByCountryCode(final String countryCode);

	long populationByCountryCodeAndCity(final String countryCode, final String cityName);

	int addCity(City city);

	int updateCity(final int cityId, final String districtName);

	int removeCity(final int cityId);

	void crateTable();

	void dripTable();
}
