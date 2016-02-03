package com.company.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.model.Car;
import com.company.repository.CarRepository;
import com.google.common.collect.Lists;


@Service("carService")
@Repository
@Transactional
public class CarServiceImpl implements CarService {

	private Log log = LogFactory.getLog(CarServiceImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private CarRepository carRepository;
	
	@Override
	public boolean executeStoredProcedure(){
		log.info("Starting the call to the store procedure ...");
		StoredProcedureQuery query = em.createStoredProcedureQuery("car_initial_data");
		return query.execute();
	}

	@Override
	public List<Car> findAll() {
		return Lists.newArrayList(carRepository.findAll());
	}

}
