package com.jdbc.student.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.jdbc.student.Connection.DBConnectionProvider;
import com.jdbc.student.DAO.StudentDAO;
import com.jdbc.student.Entity.Student;

public class MainRetrievalController {

	private static Connection con;
	private static PreparedStatement ps;
	private static ResultSet rs;

	public static void main(String[] args) {

		StudentDAO dao = new StudentDAO(DBConnectionProvider.getConnection());

		List<Student> students = dao.getAllStudents();
		if (students.isEmpty()) {
			System.out.println("Students records are not available!!!");
		} else {
			for (Student records : students) {
				System.out.println("Username:- " + records.getUsername());
				System.out.println("Password:- " + records.getPassword());
				System.out.println("=====================================");
			}
		}
		DBConnectionProvider.closeResources_2(rs, ps, con);
	}

}
