package com.training.universityjdbc.student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StudentHandler {

	private static final Logger LOG = LoggerFactory.getLogger(StudentHandler.class);

	static {
		LOG.debug("Loading MySQL driver");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			LOG.error("Could not load the driver", e);
		}
	}

	public Student getDetails(int studentId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Student student = new Student();
		if (studentId == 0) {
			LOG.error("StudentId is 0");
			student = null;
		} else {
			try{
				connection = DriverManager.getConnection("jdbc:mysql://localhost/universityproject", "root", "");
				preparedStatement = connection.prepareStatement("SELECT * FROM STUDENT WHERE SID = ?");
				preparedStatement.setInt(1, studentId);
				resultSet = preparedStatement.executeQuery();
				if(resultSet.next()){
					student.setStudentId(resultSet.getInt(1));
					student.setName(resultSet.getString(2));
					student.setUniversityId(resultSet.getInt(3));
				}
				
				preparedStatement = connection.prepareStatement("SELECT CID FROM ENROLLED WHERE SID = ?");
				preparedStatement.setInt(1, studentId);
				resultSet = preparedStatement.executeQuery();
				if(resultSet.next()){
					student.addCourseId(resultSet.getInt(1));
				}
				System.out.println(student.toString());
				
			} catch (SQLException e){
				LOG.error("SQL Exception while trying to fire query");
			}
		}
		return student;
	}

	public void getResult(Student student) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean status = false;
		int marks1, marks2, cid, studentId;
		studentId = student.getStudentId();
		if (student.getStudentId() == 0) {
			LOG.error("StudentId is 0");
		} else {
			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost/universityproject?user=root");
				preparedStatement = connection.prepareStatement("SELECT CID, MARKS1, MARKS2 FROM MARKS WHERE SID = ?");
				preparedStatement.setInt(1, studentId);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					cid = resultSet.getInt(1);
					marks1 = resultSet.getInt(2);
					marks2 = resultSet.getInt(3);
					status = ((marks1 + marks2) >= 3);
					if(status){
						System.out.println("[ Course Id: " + cid + " => Marks: " + marks1 + ", " + marks2 + " => Pass: " + status + " ]");
					}
				}
				
			} catch (SQLException e) {
				LOG.error("SQL Exception while trying thebleh ");
			}

		}

	}
}
