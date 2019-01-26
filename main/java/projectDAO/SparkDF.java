package projectDAO;

import org.apache.spark.sql.SparkSession;

public interface SparkDF {
	public void createSparkDf(SparkSession spark, String path);
}
