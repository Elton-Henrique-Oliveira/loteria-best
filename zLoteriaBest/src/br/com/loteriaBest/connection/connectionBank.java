package br.com.loteriaBest.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class connectionBank {
	
	public Connection conectaBD() {
		Connection conn = null;
		
		try {
			String url = "jdbc:mysql://45.152.44.204/u400453406_best_buds_loto?user=u400453406_admin&password=Elton4321,";
			conn = DriverManager.getConnection(url);
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, error.getMessage());
		}
		return conn;
	}
	public Connection conectaBDteste() {
		Connection conn = null;
		
		try {
			String url = "jdbc:mysql://45.152.44.204/u400453406_best_buds_loto?user=u400453406_admin&password=Elton4321,";
			conn = DriverManager.getConnection(url);
		} catch (SQLException error) {
			System.out.println("Erro:" + error);
		}
		return conn;
	}
}
