package application;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SaveMode;

import projectDAO.PostgresDAO;
import projectDAO.SparkDAO;

public class App {

	public static void main(String[] args) {

		// ++++++++++++++++++++++++++++++++++CHANGE THIS TO BE IN THE
		// PATH++++++++++++++++++++++++++++++++++
		System.setProperty("hadoop.home.dir", "C:\\hadoop");
		System.out.println("Starting App...............");

		SparkDAO spark = new SparkDAO("Spark1", "local", true);

		PostgresDAO dbDAO = new PostgresDAO("jdbc:postgresql://localhost/CarDealership", "org.postgresql.Driver",
				"postgres", "postgres");

//		Dataset<Row> dfSpecCSV = new SparkDataFrame().getDataFrameFromSpecifiedCSV(spark.getSparkSession(),
//				"src/main/resources/amazonProducts.txt");
//		dfSpecCSV.show();

		SparkDataFrame dfPeople = new SparkDfCSV();
		dfPeople.createSparkDf(spark.getSparkSession(), "src/main/resources/people.txt");
		dfPeople.getDataFrame().show();
		dfPeople.getDataFrame().write().mode(SaveMode.Overwrite).jdbc(dbDAO.getDbProp().getProperty("url"), "customer", dbDAO.getDbProp());
		

		SparkDataFrame dfCars = new SparkDfJSON();
		dfCars.createSparkDf(spark.getSparkSession(), "src/main/resources/car_stock.json");
		// Get local ref to car df.
		Dataset<Row> carStockDf = dfCars.getDataFrame(); 
		carStockDf.show();
		
		// Strip the extras from the sub category
		Dataset<Row> extrasDf =	carStockDf.select(carStockDf.col("id"), 
						carStockDf.col("extras.alloy_wheels"), 
						carStockDf.col("extras.ac"), 
						carStockDf.col("extras.sunroof"));

		extrasDf.show();

		carStockDf.drop("extras.*");
		carStockDf = carStockDf.join(extrasDf, 
				extrasDf.col("id").equalTo(carStockDf.col("id")))
				.drop(extrasDf.col("id"))
				.drop(carStockDf.col("extras"));

		carStockDf.show();
		carStockDf.write().mode(SaveMode.Overwrite).jdbc(dbDAO.getDbProp().getProperty("url"), "car_stock", dbDAO.getDbProp());

//		InferCSVSchema csv = new InferCSVSchema();
//		csv.printSchema(spark);

		System.out.println("Ending App...............");
	}

}
