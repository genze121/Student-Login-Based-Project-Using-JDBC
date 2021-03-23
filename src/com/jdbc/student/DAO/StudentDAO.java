package com.jdbc.student.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.student.Entity.Student;

public class StudentDAO {

	private static Connection con;

	@SuppressWarnings("static-access")
	public StudentDAO(Connection con) {
		super();
		this.con = con;
	}

	// SQL QUERIES

	// Insert Query
	private static final String INSERT_SQL_QUERY = "insert into login" + "(username,password)" + "values(?,?)";

	// Select All Students
	private static final String SELECT_SQL_QUERY = "select * from login";

	public static boolean saveStudent(Student student) {
		boolean flag = false;
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(INSERT_SQL_QUERY);
			ps.setString(1, student.getUsername());
			ps.setString(2, student.getPassword());

			int insertedStudent = ps.executeUpdate();
			if (insertedStudent == 1 || insertedStudent > 0) {
				flag = true;
			}
			System.out.println("The no of students inserted:- " + insertedStudent);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return flag;
	}

	public List<Student> getAllStudents() {

		List<Student> lists = new ArrayList<Student>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(SELECT_SQL_QUERY);
			rs = ps.executeQuery();
			while (rs.next()) {
				String name = rs.getString(1);
				String password = rs.getString(2);
				lists.add(new Student(name, password));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lists;
	}

}
