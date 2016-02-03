package com.company.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.Table;

@NamedStoredProcedureQuery(name = "car_initial_data", procedureName = "car_initial_data", parameters = {})
@Entity
@Table(name = "car")
public class Car {
	@Id
	@Column(name = "CAR_ID")
	@GeneratedValue
	private Long id;

	@Column(name = "CAR_BRAND")
	private String brand;
	
	@Column(name = "CAR_MODEL")
	private String model;

	@Column(name = "FUEL_LEVEL")
	private Long fuelLevel;
	
	@Column(name = "GAS_TYPE")
	private String gasType;
	
	@Column(name = "MILAGE_PER_GALON")
	private float milagePerGalon;


	public Car() {
	}

	public Car(Long id, String brand, String model, Long fuelLevel, String gasType, float milagePerGalon) {
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.fuelLevel = fuelLevel;
		this.gasType = gasType;
		this.milagePerGalon = milagePerGalon;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Long getFuelLevel() {
		return fuelLevel;
	}

	public void setFuelLevel(Long fuelLevel) {
		this.fuelLevel = fuelLevel;
	}
	
	public String getGasType() {
		return gasType;
	}

	public void setGasType(String gasType) {
		this.gasType = gasType;
	}

	public float getMilagePerGalon() {
		return milagePerGalon;
	}

	public void setMilagePerGalon(float milagePerGalon) {
		this.milagePerGalon = milagePerGalon;
	}
}