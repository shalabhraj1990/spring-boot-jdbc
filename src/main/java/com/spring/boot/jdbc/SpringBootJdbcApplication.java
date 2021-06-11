package com.spring.boot.jdbc;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring.boot.jdbc.modal.City;
import com.spring.boot.jdbc.repository.CityRepsitoryImpl;

@SpringBootApplication
public class SpringBootJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJdbcApplication.class, args);
	}

	@Bean
	public CommandLineRunner start(CityRepsitoryImpl cityRepsitoryImpl) {
		return args -> {

			int cityCount = cityRepsitoryImpl.cityCount();
			System.out.println("cityCount = " + cityCount);

			int countryCityCount = cityRepsitoryImpl.getCityCountByCountryCode("IND");
			System.out.println("countryCount = " + countryCityCount);

			long populationByCountryCity = cityRepsitoryImpl.populationByCountryCodeAndCity("ind", "delhi");
			System.out.println("populatonOfCountryCity  " + populationByCountryCity);

			City city1 = cityRepsitoryImpl.getCityById(29);
			System.out.println("City =   " + city1.toString());

			List<City> listOfCountry = cityRepsitoryImpl.getAllCityByCountryCode("ind");
			listOfCountry.forEach(System.out::println);

			City city = new City();
			city.setCountryCode("IND");
			city.setDistrict("ranchi");
			city.setName("ranchi");
			city.setPopulation(100000);

			int noOfRowsAfftected = cityRepsitoryImpl.addCity(city);
			System.out.println("no of rows affected = " + noOfRowsAfftected);

			int updatedCity = cityRepsitoryImpl.updateCity(20001, "namkom");
			System.out.println("no of rows updated = " + updatedCity);

		};

	}

}
