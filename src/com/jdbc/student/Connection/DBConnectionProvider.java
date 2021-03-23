package com.jdbc.student.Connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class DBConnectionProvider {

	private static String driver;
	static {
		try {
			driver = "com.mysql.cj.jdbc.Driver";
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unused")
	public static Connection getConnection() {

		Properties prop = new Properties();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Connection con = null;
		try {
			File file = new File("src/db.properties");
			FileInputStream fis = new FileInputStream(file);
			if (file.exists()) {
				System.out.println("You have the access to DB Operations!!");
				prop.load(fis);

				String JDBC_URL = prop.getProperty("url");
				String USERNAME = prop.getProperty("user");
				String PASSWORD = prop.getProperty("password");

				// Validating username and Password of MYSQL DB
				System.out.println("Enter the Username:-");
				String username = scanner.next();
				System.out.println("Enter the Password:-");
				String password = scanner.next();

				if (USERNAME.equals(username) && PASSWORD.equals(password)) {
					System.out.println("You have the access to MYSQL DB!!");
				} else {
					System.out.println("Invalid Credentials!!");
				}

				// Validating and Establishing the Connection

				if (con == null || con != null) {
					con = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
					System.out.println("Connection is Established successfully!!");
				}

				else {
					System.out.println("Connection is not Established successfully!!!");
				}
				System.out.println("=====================================");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return con;
	}

	public static void closeResources_1(PreparedStatement ps, Connection con) {

		try {
			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (con != null && !con.isClosed()) {
				con.close();
			}
			System.out.println("Connection is Closed!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeResources_2(ResultSet rs, PreparedStatement ps, Connection con) {

		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}

			if (ps != null && !ps.isClosed()) {
				ps.close();
			}
			if (con != null && !con.isClosed()) {
				con.close();
			}
			System.out.println("Connection is Closed!!");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
