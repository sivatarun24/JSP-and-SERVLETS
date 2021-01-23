package com.luv2code.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {
	private DataSource dataSource;

	public StudentDbUtil(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	public List<Student> getStudents() throws Exception{
		
		List<Student> list = new ArrayList<>();
		
		String sql = "select * from student order by last_name";
		Connection conn = null;
		Statement smt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();

			smt = conn.createStatement();
			rs = smt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				
				Student tempStudent = new Student(id , firstName , lastName , email);
				
				list.add(tempStudent);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			conn.close();
			smt.close();
			rs.close();
		}
		return list;
		
	}

	public void addStudent(Student theStudent) throws Exception {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO student(first_name , last_name , email) VALUES (?,?,?)";
		
		conn = dataSource.getConnection();
		ps = conn.prepareStatement(sql);
		ps.setString(1, theStudent.getFirstName());
		ps.setString(2, theStudent.getLastName());
		ps.setString(3, theStudent.getEmail());
		
		int i = ps.executeUpdate();
		
		if(i>0) {
			
		}
			
	}

	public Student getStudent(String theStudentId) throws Exception {
		// TODO Auto-generated method stub
		
		Student theStudent = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int studentId;
		String sql = "select * from student where id = ?";
		
		try {
			studentId = Integer.parseInt(theStudentId);
			
			myConn = dataSource.getConnection();
			
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, studentId);
			
			myRs = myStmt.executeQuery();
			
			if(myRs.next()) {
				String firstName = myRs.getString(2);
				String lastName = myRs.getString(3);
				String email = myRs.getString(4);
				
				theStudent = new Student(studentId , firstName , lastName , email );
			}else {
				throw new Exception("Could not find Student Id:"+studentId);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			myConn.close();
			myStmt.close();
			myRs.close();
		}
		return theStudent;
	}

	public void updateStudent(Student theStudent) throws Exception {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "UPDATE student SET first_name = ? , last_name = ? , email = ? WHERE id = ? ";
		
		conn = dataSource.getConnection();
		
		ps = conn.prepareStatement(sql);
		
		ps.setString(1, theStudent.getFirstName());
		ps.setString(2, theStudent.getLastName());
		ps.setString(3, theStudent.getEmail());
		ps.setInt(4, theStudent.getId());
		
		int i = ps.executeUpdate();
		
		if(i>0) {
			
		}
	}

	public void deleteStudent(String theStudentId) throws Exception {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "DELETE FROM student WHERE id = ?";
		
		conn = dataSource.getConnection();
		
		ps = conn.prepareStatement(sql);
		
		ps.setInt(1, Integer.parseInt(theStudentId));
		
		int i = ps.executeUpdate();
		
		if(i>0) {
			
		}
	}
}
