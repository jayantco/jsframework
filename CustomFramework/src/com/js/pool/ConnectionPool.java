package com.js.pool;

import java.io.Console;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import com.js.db.DataJSConfigBean;

public class ConnectionPool {
	private String driverclass;
	private String url;
	private String username;
	private String password;
	private int poolsize;
	private Vector<Connection> pool;
	private HashMap<String, Vector<Connection>> dbmap = new HashMap<String, Vector<Connection>>();

	public ConnectionPool(DataJSConfigBean db) {
		this.driverclass = db.getDriverclass();
		this.url = db.getUrl();
		this.username = db.getUsername();
		this.password = db.getPassword();
		this.poolsize = Integer.parseInt(db.getPoolsize());
		pool = new Vector<Connection>();
	}

	public void init(String key) {
		Connection con = null;
		try {
			Class.forName(driverclass);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		for (int i = 0; i < poolsize; i++) {
			con = getConnection();
			if (con != null)
				pool.add(con);
		}
		dbmap.put(key, pool);
	}

	private Connection getConnection() {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	public void checkin(Connection con, String key) {
		Vector<Connection> poolcon = dbmap.get(key);
		if (con != null) {
			if (poolcon.size() < poolsize) {
				poolcon.add(con);
			} else {
				closeConnection(con);
			}
			dbmap.put(key, poolcon);
		}
	}

	private void closeConnection(Connection con) {
		// TODO Auto-generated method stub
		try {
			con.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

	public void release() {
		Connection con = null;

		Set<String> keys = dbmap.keySet();
		for (String key : keys) {
			Vector<Connection> poolcon = dbmap.get(key);
			for (int i = 0; i < poolcon.size();) {
				con = poolcon.remove(0);
				closeConnection(con);
			}
		}
	}

	public Vector<Connection> getConnection(String key) {
		return dbmap.get(key);
	}

	public void updatePool(Vector<Connection> poolcon, String key) {
		// TODO Auto-generated method stub
		dbmap.put(key, poolcon);

	}
}
