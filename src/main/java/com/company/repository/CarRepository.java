package com.company.repository;

import org.springframework.data.repository.CrudRepository;

import com.company.model.Car;

public interface CarRepository extends CrudRepository<Car, Long> {

}
