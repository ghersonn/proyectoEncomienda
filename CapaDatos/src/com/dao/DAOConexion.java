package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAOConexion {
	//Singleton
	public static DAOConexion _Instancia;
	private DAOConexion(){};
	public static DAOConexion Instancia(){
		if(_Instancia==null){
			_Instancia = new DAOConexion();
		}
		return _Instancia;
	}
	//endSingleton
	
	public Connection conectar() throws Exception{
		Connection cn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			cn = DriverManager.getConnection("jdbc:sqlserver://104.197.224.95:1433;"+
						"databaseName=SOWAD_Encomienda", "sa", "P@ssw0rd12");
		} catch (Exception e) {
			throw e;
		}
		return cn;
	}
}

