package application;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import projectDAO.SparkDF;

public abstract class SparkDataFrame implements SparkDF{

	private Dataset<Row> dataFrame;
	
//	public Dataset<Row> getDataFrameFromJSON(SparkSession spark, String path) {
//		dataFrame = spark.read().format("json")
//				.option("multiline", true)
//				.option("inferSchema",false)
//				.load(path);
//		
//		return dataFrame;
//	}
	
//	public Dataset<Row> getDataFrameFromCSV(SparkSession spark, String path) {
//		dataFrame = spark.read().format("csv")
//				.option("header", "true")
//				.option("multiline", true)
//				.option("inferSchema",false)
//				.load(path);
//		
//		return dataFrame;
//	}
//	
//	public Dataset<Row> getDataFrameFromSpecifiedCSV(SparkSession spark, String path) {
//
//		dataFrame = spark.read().format("csv") 
//				.option("header", "true") 
//				.option("multiline", true)
//				.option("sep", ";") 
//				.option("quote", "^")
//				.option("dateFormat", "M/d/y") 
//				.option("inferSchema", true) 
//				.load(path);
//
//		return dataFrame;
//	}

	public Dataset<Row> getDataFrame() {
		return dataFrame;
	}

	public void setDataFrame(Dataset<Row> dataFrame) {
		this.dataFrame = dataFrame;
	}

}
