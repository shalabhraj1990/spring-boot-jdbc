package com.spring.boot.jdbc.modal;

import lombok.Data;

@Data
public class City {
	private int id;
	private String name;
	private String countryCode;
	private String district;
	private double population;
}
