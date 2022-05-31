package com.mirdu.banking_system.entity;

public class Account {
	private int accno;
	private String accname;
	private double balance;
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Account(int accno, String accname, double balance) {
		super();
		this.accno = accno;
		this.accname = accname;
		this.balance = balance;
	}
	public int getAccno() {
		return accno;
	}
	public void setAccno(int accno) {
		this.accno = accno;
	}
	public String getAccname() {
		return accname;
	}
	public void setAccname(String accname) {
		this.accname = accname;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Account [accno=" + accno + ", accname=" + accname + ", balance=" + balance + "]";
	}
}
