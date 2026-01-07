package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeCrud {

	static final String URL = "jdbc:mysql://localhost:3306/employees";
	static final String USER = "root";
	static final String PASS = "root";

	public static void main(String[] args) throws Exception {

		Class.forName("com.mysql.cj.jdbc.Driver");

		insert();
		readAll();
		readById(1);
		update(1, 50000);
		delete(1);
	}

	// INSERT
	static void insert() throws SQLException {
		Connection con = DriverManager.getConnection(URL, USER, PASS);
		Statement st = con.createStatement();

		st.execute("INSERT INTO employee (ename, job, salary, hiredate) VALUES ('SMITH','CLERK',800, '1982-11-10')");
		st.execute("INSERT INTO employee (ename, job, salary, hiredate) VALUES ('ALLEN','CLERK',1200, '1982-07-21')");
		st.execute("INSERT INTO employee (ename, job, salary, hiredate) VALUES ('WARDS','SALESMAN',1600, '1981-01-19')");
		st.execute("INSERT INTO employee (ename, job, salary, hiredate) VALUES ('MARTIN','MANAGER',3600, '1980-09-30')");

		System.out.println("Inserted");

		st.close();
		con.close();
	}

	// READ ALL
	static void readAll() throws SQLException {
		Connection con = DriverManager.getConnection(URL, USER, PASS);
		Statement st = con.createStatement();

		ResultSet rs = st.executeQuery("SELECT * FROM employee");

		while (rs.next()) {
			System.out.println("ID       : " + rs.getInt("id"));
			System.out.println("Name     : " + rs.getString("ename"));
			System.out.println("Job      : " + rs.getString("job"));
			System.out.println("Salary   : " + rs.getLong("salary"));
			System.out.println("Hiredate : " + rs.getDate("hiredate"));
		}

		rs.close();
		st.close();
		con.close();
	}

	// READ BY ID
	static void readById(int id) throws SQLException {
		Connection con = DriverManager.getConnection(URL, USER, PASS);
		Statement st = con.createStatement();

		ResultSet rs = st.executeQuery("SELECT * FROM employee WHERE id=" + id);

		if (rs.next()) {
			System.out.println("ID       : " + rs.getInt("id"));
			System.out.println("Name     : " + rs.getString("ename"));
			System.out.println("Job      : " + rs.getString("job"));
			System.out.println("Salary   : " + rs.getLong("salary"));
			System.out.println("Hiredate : " + rs.getDate("hiredate"));
		} else {
			System.out.println("Employee not found");
		}
		rs.close();
		st.close();
		con.close();
	}

	// UPDATE
	static void update(int id, long salary) throws SQLException {
		Connection con = DriverManager.getConnection(URL, USER, PASS);
		Statement st = con.createStatement();

		st.execute("UPDATE employee SET salary=" + salary + " WHERE id=" + id);

		System.out.println("Updated");

		st.close();
		con.close();
	}

	// DELETE
	static void delete(int id) throws SQLException {
		Connection con = DriverManager.getConnection(URL, USER, PASS);
		Statement st = con.createStatement();

		st.execute("DELETE FROM employee WHERE id=" + id);

		System.out.println("Deleted");

		st.close();
		con.close();
	}
}
