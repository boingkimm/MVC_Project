package com.dto;

public class EmpDTO {

	int empno;
	String ename;
	String job;
	String hireDate;
	int sal;
	
	public EmpDTO() {
		super();
	}

	public EmpDTO(int empno, String ename, String job, String hireDate, int sal) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.hireDate = hireDate;
		this.sal = sal;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	@Override
	public String toString() {
		return "EmpDTO [empno=" + empno + ", ename=" + ename + ", job=" + job + ", hireDate=" + hireDate + ", sal="
				+ sal + "]";
	}
	
	
	
}
