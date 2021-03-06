package fisi.order.module.config;

import java.util.LinkedHashMap;

// import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class DatabaseSource {

    private String driver;
    private String url;
    private String username;
    private String password;

    public DatabaseSource(String driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }
	
/* 	public static String encryp(String passwordHash) {
        StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
        decryptor.setPassword(Config.SIMI);
        return decryptor.decrypt(passwordHash);
	} */

    //	public static DatabaseSource setProperties(LinkedHashMap<Object, Object> properties) {
//		String driver = (String) properties.get("driver");
//		String url = "jdbc:"+ (String) properties.get("type") + "://" +
//				(String)properties.get("ip") + ":" + (String)properties.get("port") + "/" +
//				(String)properties.get("db")+"?autoReconnect=true&useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//		String username = (String)properties.get("username");
//		String password ="";
//		if (properties.get("password").toString().equals("root")) {
//			password= "root";
//		} else
//		if (properties.get("password").toString().length() != 0) {
//			// password = encryp(properties.get("password").toString());
//		}
//
//		return new DatabaseSourceBuilder().driver(driver).url(url).username(username).password(password).build();
//	}
    public static DatabaseSource setProperties() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:" + "mysql" + "://" +
                "157.230.55.229" + ":" + "3306" + "/" +
                "dbpurchase" + "?autoReconnect=true&useSSL=false";
        String username = "team3";
        String password = "RooT1234-T";

        return new DatabaseSourceBuilder().driver(driver).url(url).username(username).password(password).build();
    }

    public static DatabaseSourceBuilder builder() {
        return new DatabaseSourceBuilder();
    }

    public String getDriver() {
        return driver;
    }


    public void setDriver(String driver) {
        this.driver = driver;
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static class DatabaseSourceBuilder {
        private String driver;
        private String url;
        private String username;
        private String password;

        public DatabaseSourceBuilder driver(String driver) {
            this.driver = driver;
            return this;
        }

        public DatabaseSourceBuilder url(String url) {
            this.url = url;
            return this;
        }

        public DatabaseSourceBuilder username(String username) {
            this.username = username;
            return this;
        }

        public DatabaseSourceBuilder password(String password) {
            this.password = password;
            return this;
        }

        public DatabaseSource build() {
            return new DatabaseSource(driver, url, username, password);
        }
    }
}
