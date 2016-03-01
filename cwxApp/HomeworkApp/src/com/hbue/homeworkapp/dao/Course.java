package com.hbue.homeworkapp.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table COURSE.
 */
public class Course {

	private Long courseNum;
	private String courseName;
	private String teacherName;
	private String imgPath;
	private String courseTime;
	private String classRoom;

	public Course() {
	}

	public Course(Long courseNum) {
		this.courseNum = courseNum;
	}

	public Course(Long courseNum, String courseName, String teacherName,
			String imgPath, String courseTime, String classRoom) {
		this.courseNum = courseNum;
		this.courseName = courseName;
		this.teacherName = teacherName;
		this.imgPath = imgPath;
		this.courseTime = courseTime;
		this.classRoom = classRoom;
	}

	public Long getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(Long courseNum) {
		this.courseNum = courseNum;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getCourseTime() {
		return courseTime;
	}

	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}

	public String getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}

}
