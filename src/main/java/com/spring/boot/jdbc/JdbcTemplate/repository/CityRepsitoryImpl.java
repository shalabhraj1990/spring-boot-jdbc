package com.spring.boot.jdbc.JdbcTemplate.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.boot.jdbc.modal.City;

@Repository
public class CityRepsitoryImpl implements CityRepository {
	@Autowired
	JdbcTemplate cityTemplate;

	@Override
	public int cityCount() {
		// TODO Auto-generated method stub
		return cityTemplate.queryForObject("select count(*) from city", Integer.class);
	}

	@Override
	public int getCityCountByCountryCode(final String countryCode) {
		// TODO Auto-generated method stub
		return cityTemplate.queryForObject("select count(*) from city where CountryCode = ?", Integer.class,
				countryCode);
	}

	@Override
	public long populationByCountryCodeAndCity(String countryCode, String cityName) {
		// TODO Auto-generated method stub
		return cityTemplate.queryForObject("select Population from city where CountryCode = ? and Name = ?", Long.class,
				new Object[] { countryCode, cityName });

	}

	@Override
	public City getCityById(int cityId) {
		// TODO Auto-generated method stub

//		RowMapper<City> rowMapper = (rs, rowNum) -> {
//			City city = new City();
//			city.setId(rs.getInt("id"));
//			city.setCountryCode(rs.getString("countrycode"));
//			city.setDistrict(rs.getString("district"));
//			city.setName(rs.getString("name"));
//			city.setPopulation(rs.getDouble("population"));
//			return city;
//		};

		return cityTemplate.queryForObject("select * from city where ID = ? ", new Object[] { cityId },
				new RowMapperImpl());
	}

	@Override
	public List<City> getAllCityByCountryCode(String countryCode) {
		// TODO Auto-generated method stub
		return cityTemplate.query("select * from city  where CountryCode = ?",
				new RowMapperImpl(), new Object[] { countryCode });
	}
	
	@Override
	public List<City> getAllCityByCountryCode(List<String> countryCode) {
		String query = "select * from city  where CountryCode in (%s)";
		String inParameter = String.join(",", Collections.nCopies(countryCode.size(),"?"));
		return cityTemplate.query(String.format(query, inParameter), new RowMapperImpl(),countryCode.toArray());
	}

	@Override
	public int addCity(City city) {
		int maxCityID = cityTemplate.queryForObject("select max(id )from city", Integer.class);
		// TODO Auto-generated method stub
		return cityTemplate.update("insert into city (ID,Name,countryCode,District,Population) value (?,?,?,?,?)",
				new Object[] { ++maxCityID, city.getName(), city.getCountryCode(), city.getDistrict(),
						city.getPopulation() });
	}

	@Override
	public int updateCity(int cityId, String districtName) {
		// TODO Auto-generated method stub
		return cityTemplate.update("update city set district = ? where id = ?", new Object[] { districtName, cityId });

	}

	@Override
	public int removeCity(int cityId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void crateTable() {
		 cityTemplate.execute("create table mytable (id integer,name varchar(100)");
	}

	@Override
	public void dripTable() {
		cityTemplate.execute("drop table mytable");

	}

	public static class RowMapperImpl implements RowMapper<City> {
		@Override
		public City mapRow(ResultSet rs, int rowNum) throws SQLException {
			City city = new City();
			city.setId(rs.getInt("id"));
			city.setCountryCode(rs.getString("countrycode"));
			city.setDistrict(rs.getString("district"));
			city.setName(rs.getString("name"));
			city.setPopulation(rs.getDouble("population"));
			return city;

		}
	}



}
