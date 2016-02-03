package tools.datagen.util;

import com.company.service.CarService;

public class StoredProcedureExecutor {

	private CarService carService;
	
	public StoredProcedureExecutor(CarService carService) {
		super();
		this.carService = carService;
	}	
	
	public void execute(){
		carService.executeStoredProcedure();
	}
	
}
