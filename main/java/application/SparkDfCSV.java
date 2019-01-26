package application;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkDfCSV extends SparkDataFrame{

	public void createSparkDf(SparkSession spark, String path) {
		Dataset<Row> df = spark.read().format("csv")
			.option("header", "true")
			.option("multiline", true)
			.option("inferSchema",false)
			.load(path);
		
		this.setDataFrame(df);
	}

}
