package projectDAO;

import java.util.Properties;

public class PostgresDAO {

	private Properties dbProp;

	public PostgresDAO(String url, String driver, String user, String password) {
		super();
		this.dbProp = new Properties();
		this.dbProp.setProperty("url", url);
		this.dbProp.setProperty("driver", driver);
		this.dbProp.setProperty("user", user);
		this.dbProp.setProperty("password", password);
	}

	public Properties getDbProp() {
		return dbProp;
	}

}
