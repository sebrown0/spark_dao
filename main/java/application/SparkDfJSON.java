package application;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkDfJSON extends SparkDataFrame {

	public void createSparkDf(SparkSession spark, String path) {
		Dataset<Row> df =  spark.read().format("json")
				.option("multiline", true)
				.option("inferSchema",false)
				.load(path);
		
		this.setDataFrame(df);
	}

}
