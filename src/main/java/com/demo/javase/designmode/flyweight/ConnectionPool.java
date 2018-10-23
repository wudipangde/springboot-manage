package com.demo.javase.designmode.flyweight;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;

/**
 * @author wxw
 * @Description:XX
 * @date 2018/10/22
 */
public class ConnectionPool {
	private Vector<Connection> pool;
	private String url="jdbc:mysql://localhost:3306/test";
	private String username="root";
	private String password="ablejava";
	private String driverClassName="com.mysql.jdbc.Driver";

	private int poolSize=100;
	private static ConnectionPool instance=null;
	Connection conn=null;

	private ConnectionPool(){
		pool=new Vector<>(poolSize);
		for(int i=0;i<poolSize;i++){
			try {
				Class.forName(driverClassName);
				conn =DriverManager.getConnection(url,username,password);
			} catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	public synchronized void release(){
		pool.add(conn);
	}
	public synchronized Connection getConnection(){
		if(pool.size()>0){
			Connection conn=pool.get(0);
			pool.remove(conn);
			return conn;
		}else{
			return null;
		}
	}
}
