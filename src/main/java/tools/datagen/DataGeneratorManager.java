package tools.datagen;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.company.service.CarService;

import tools.datagen.util.JsonUtil;
import tools.datagen.util.StoredProcedureExecutor;

public class DataGeneratorManager {
	private GenericXmlApplicationContext ctx;
	
	public DataGeneratorManager () {
		ctx = new GenericXmlApplicationContext();
    	ctx.load("classpath:spring.xml");
    	ctx.refresh();
	}
	
	public void execute () {
		CarService carService = ctx.getBean("carService", CarService.class);
		StoredProcedureExecutor executor = new StoredProcedureExecutor(carService);
		executor.execute();
		
		JsonUtil jsonUtil = new JsonUtil();
		jsonUtil.createAndSaveJson(carService.findAll(), "fueling.feature.json");		
	}
	
	public static void main(String[] args) {
		DataGeneratorManager dataGeneratorManager = new DataGeneratorManager();
		dataGeneratorManager.execute();    	
	}
}
