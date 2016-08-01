package com.training.universityjdbc.student;

import java.util.ArrayList;
import java.util.List;

public class Student {
	private int studentId;
	private String name;
	private int universityId;
	private List<Integer> courseIds;

	public Student() {
		this.studentId = 0;
		this.name = null;
		this.universityId = 0;
		this.courseIds = new ArrayList<Integer>();
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getStudentId() {
		return this.studentId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setUniversityId(int universityId) {
		this.universityId = universityId;
	}

	public int getUniversityId() {
		return this.universityId;
	}

	public void addCourseId(int courseId) {
		courseIds.add(courseId);
	}

	@Override
	public String toString() {
		return "[Name: " + this.getName() + ", StudentId: " + this.getStudentId() + ", UniversityId: "
				+ this.getUniversityId() + ", CourseIds:" + this.courseIds + "]";

	}
}
