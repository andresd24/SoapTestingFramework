package tools.datagen;

import org.apache.log4j.Logger;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.company.service.CarService;

import tools.datagen.util.JsonUtil;
import tools.datagen.util.StoredProcedureExecutor;

public class DataGeneratorManager {
	
	private static final Logger logger = Logger.getLogger(DataGeneratorManager.class);
	
	private GenericXmlApplicationContext ctx;
	
	public DataGeneratorManager () {
		logger.info("Initializing DataGeneratorManager ...");
		ctx = new GenericXmlApplicationContext();
    	ctx.load("classpath:spring.xml");
    	ctx.refresh();
	}
	
	public void execute () {
		logger.info("Call to stored procedure ...");
		CarService carService = ctx.getBean("carService", CarService.class);
		StoredProcedureExecutor executor = new StoredProcedureExecutor(carService);
		executor.execute();
		
		logger.info("Call to create json ...");
		JsonUtil jsonUtil = new JsonUtil();
		jsonUtil.createAndSaveJson(carService.findAll(), "fueling.feature.json");		
	}
	
	public static void main(String[] args) {
		DataGeneratorManager dataGeneratorManager = new DataGeneratorManager();
		dataGeneratorManager.execute();    	
	}
}
