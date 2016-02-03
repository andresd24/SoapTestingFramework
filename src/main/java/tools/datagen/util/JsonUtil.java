package tools.datagen.util;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import com.company.model.Car;

public class JsonUtil {

	private static final Logger logger = Logger.getLogger(JsonUtil.class);

	public void createAndSaveJson(List<Car> cars, String fileName) {
		JSONObject obj = createJson(cars);
		saveJson(obj, fileName);
	}

	@SuppressWarnings("unchecked")
	public JSONObject createJson(List<Car> cars) {
		logger.info("Starting json creation ...");

		List<Scenario.Row> rows = new ArrayList<>();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("title", "Car calculates correct trip per gallon");
		jsonObject.put("testColumnTitles",
				Arrays.asList("carBrand", "carMake", "gallons", "fuelType", "expectedMiles"));

		for (Car car : cars) {
			Scenario.Row row = new Scenario.Row();
			row.setPosition(car.getId());

			List<String> rowList = new ArrayList<>();
			rowList.add(car.getBrand());
			rowList.add(car.getModel());

			Random galonsRandom = new Random();
			int galons = galonsRandom.nextInt(20);

			rowList.add(String.valueOf(galons));
			rowList.add(car.getGasType());
			rowList.add(String.valueOf(car.getMilagePerGalon() * galons));

			row.setRowData(rowList);
			rows.add(row);
		}
		jsonObject.put("testRows", rows);

		logger.info("Finished json creation ...");
		return jsonObject;
	}

	public void saveJson(JSONObject obj, String fileName) {
		logger.info("Starting to save json file ...");
		try {
			FileWriter file = new FileWriter(".//features_json//" + fileName);
			file.write(obj.toJSONString());
			file.flush();
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("Finished of saving json file ...");
	}
}
