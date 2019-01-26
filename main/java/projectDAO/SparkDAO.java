package projectDAO;

import org.apache.spark.sql.SparkSession;

/*
 * Creates a spark session.
 * Use getSparkSession() to access the session/object. 
 */
public class SparkDAO {

	private SparkSession spark;
	private String appName;
	private String master;
	private boolean activeSession = false;

	public SparkDAO(String appName, String master, boolean createSession) {
		super();
		this.appName = appName;
		this.master = master;

		if (createSession) {
			createNewSparkSession();
		}
	}

	public void createNewSparkSession() {
		// We only want 1 session per instance
		if (!activeSession) {
			spark = SparkSession.builder().appName("appName").master(master).getOrCreate();
			activeSession = true;
			System.out.println("Spark session:  " + appName + " created.");
		} else {
			System.out.println("Spark session:  " + appName + " already running!");
		}

	}

	public SparkSession getSparkSession() {
		return spark;
	}

	public String getAppName() {
		return appName;
	}

	public String getMaster() {
		return master;
	}

}
