package com.training.universityjdbc;

import com.training.universityjdbc.student.Student;
import com.training.universityjdbc.student.StudentHandler;

public class App 
{
    public static void main( String[] args )
    {
    	StudentHandler studentHandler = new StudentHandler();
    	int studentId = 123;
    	Student student = new Student();
    	student = studentHandler.getDetails(studentId);
    	studentHandler.getResult(student);
    }
}
