package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class CreateConnection {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");

		Connection serverConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");

		Statement serverStatement = serverConnection.createStatement();
		serverStatement.execute("CREATE DATABASE IF NOT EXISTS employees");
		System.out.println("Database Created");

		serverStatement.close();
		serverConnection.close();

		Connection vaultConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/employees", "root",
				"root");

		Statement vaultStatement = vaultConnection.createStatement();
		vaultStatement.execute(
				"CREATE TABLE IF NOT EXISTS employee (id INT AUTO_INCREMENT PRIMARY KEY, ename VARCHAR(20) NOT NULL, job VARCHAR(20) NOT NULL, salary BIGINT NOT NULL, hiredate DATE)");

		System.out.println("Table Created");

		vaultStatement.close();
		vaultConnection.close();
	}
}
