package com.company.service;

import java.util.List;

import com.company.model.Car;

public interface CarService {

	public List<Car> findAll();
	public boolean executeStoredProcedure();
}
