package com.js.db;

import java.io.InputStream;

public class DataJSConfigBean {
	private String driverclass;
	private String url;
	private String username;
	private String password;
	private String poolsize;
	private String dbconnectioontype;
	private String table_creation;
	private String show_sql;
	private String jbm_file;
	
	
	public String getTable_creation() {
		return table_creation;
	}
	public void setTable_creation(String table_creation) {
		this.table_creation = table_creation;
	}
	public String getShow_sql() {
		return show_sql;
	}
	public void setShow_sql(String show_sql) {
		this.show_sql = show_sql;
	}
	public String getJbm_file() {
		return jbm_file;
	}
	public void setJbm_file(String jbm_file) {
		this.jbm_file = jbm_file;
	}
	public String getDbtype() {
		return dbconnectioontype;
	}
	public void setDbtype(String dbtype) {
		this.dbconnectioontype = dbtype;
	}
	public String getDriverclass() {
		return driverclass;
	}
	public void setDriverclass(String driverclass) {
		this.driverclass = driverclass;
		
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
	public String getPoolsize() {
		return poolsize;
	}
	public void setPoolsize(String poolsize) {
		this.poolsize = poolsize;
	}

	
}
