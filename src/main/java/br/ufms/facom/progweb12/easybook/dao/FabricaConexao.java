package br.ufms.facom.progweb12.easybook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class FabricaConexao {

	public static Connection getConnection() {

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Where is your PostgreSQL JDBC Driver? Include in your library path!");
			return null;
		}
		System.out.println("PostgreSQL JDBC Driver Registered!");

		Connection connection = null;
		try {
			String url = "jdbc:postgresql://localhost:5432/EasyBookDB";
			Properties props = new Properties();
			props.setProperty("user", "postgres");
			props.setProperty("password", "root");
			connection = DriverManager.getConnection(url, props);
		} catch (SQLException e) {
			System.err.println("Connection Failed!");
			return null;
		}

		if (connection != null) {
			System.out.println("Connected to EasyBookDB!");
			return connection;
		} else {
			System.err.println("Failed to make connection!");
			return null;
		}

	}

	public static Connection getConnectionHeroku() {

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("Where is your PostgreSQL JDBC Driver? Include in your library path!");
			return null;
		}
		System.out.println("PostgreSQL JDBC Driver Registered!");

		Connection connection = null;
		try {
			String url = "jdbc:postgresql://ec2-54-225-113-7.compute-1.amazonaws.com:5432/d9ks0tnpjhtqih";
			Properties props = new Properties();
			props.setProperty("user", "nqakuibmjpvabv");
			props.setProperty("password", "0cf4062726839570dc1d8ca8d194d7e175268ec5c8e45ce822769e742166ca12");
			connection = DriverManager.getConnection(url, props);
		} catch (SQLException e) {
			System.err.println("Connection Failed!");
			return null;
		}

		if (connection != null) {
			System.out.println("Connected to Heroku DataBase!");
			return connection;
		} else {
			System.err.println("Failed to make connection!");
			return null;
		}

	}

}
