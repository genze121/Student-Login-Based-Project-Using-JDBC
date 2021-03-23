package com.jdbc.student.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

import com.jdbc.student.Connection.DBConnectionProvider;
import com.jdbc.student.DAO.StudentDAO;
import com.jdbc.student.Entity.Student;

@SuppressWarnings("static-access")
public class MainInsertController {

	private static Connection con;
	private static PreparedStatement ps;

	public static void main(String[] args) {

		StudentDAO dao = new StudentDAO(DBConnectionProvider.getConnection());

		Student student = new Student();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Enter the username:-");
			String username = scanner.next();
			System.out.println("Enter the password:-");
			String password = scanner.next();

			student.setUsername(username);
			student.setPassword(password);

			boolean insertTest = dao.saveStudent(student);
			if (insertTest) {
				System.out.println("Student records are inserted successfully!!!");
			} else {
				System.out.println("Something went wrong!!!");
			}

			System.out.println("Do you want to insert more record?If then type [Yes|No]");
			String choice = scanner.next();
			if (choice.equalsIgnoreCase("NO")) {
				break;
			}
		}

		DBConnectionProvider.closeResources_1(ps, con);

	}

}
