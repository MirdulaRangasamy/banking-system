package com.mirdu.banking_system.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mirdu.banking_system.entity.Account;


public class AccountDAO {
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	public Connection getConnect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/b6835", "root", "Kuzhazhi@123456");
		} catch (Exception e) {
			System.err.println(e);
		}
		return con;
	}

	public int insertAccount(int accno, String accname, double balance) throws SQLException {
		int n = 0;
	
			con = getConnect();
			pst = con.prepareStatement("insert into account values(?,?,?)");
			pst.setInt(1, accno);
			pst.setString(2, accname);
			pst.setDouble(3, balance);
			n = pst.executeUpdate();
		/*} catch (SQLException e) {
			e.printStackTrace();
		}*/
		return n;
	}

	public int updateAccountName(int accno, String accname) {
		int n = 0;
		try {
			con = getConnect();
			Statement st = con.createStatement();
			String sql = "update account set accname='"+accname+"' where accno="+accno;
			n = st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	public int decreaseAccountBalance(int accno, double amount) {
		int n = 0;
		try {
			con = getConnect();
			pst = con.prepareStatement("select balance from account where accno=?");
			pst.setInt(1, accno);
			rs = pst.executeQuery();
			if (rs.next()) {
				double cbal = rs.getDouble(1);
				if (cbal >= amount) {
					con.setAutoCommit(false);
					pst = con.prepareStatement("update account set balance = ? where accno = ?");
					pst.setDouble(1, (cbal - amount));
					pst.setInt(2, accno);
					n = pst.executeUpdate();
					if (n > 0) {
						System.out.println("Amount withdrawn Successfully");
						con.commit();
					} else {
						System.out.println("Sorry for inconvenience");
						con.rollback();
					}
				} else {
					System.out.println("Incufficient Balance..");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	public int increaseAccountBalance(int accno, double amount) {
		int n = 0;
		try {
			con = getConnect();
			pst = con.prepareStatement("select balance from account where accno=?");
			pst.setInt(1, accno);
			rs = pst.executeQuery();
			if (rs.next()) {
				con.setAutoCommit(false);
				double cbal = rs.getDouble(1);
				pst = con.prepareStatement("update account set balance = ? where accno = ?");
				pst.setDouble(1, (cbal + amount));
				pst.setInt(2, accno);
				n = pst.executeUpdate();
				if (n > 0) {
					System.out.println("Amount deposited Successfully");
					con.commit();
				} else {
					System.out.println("Sorry for inconvenience");
					con.rollback();
				}
			} else {
				System.out.println("Wrong Data..");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	public int deleteAccount(int accno)
	{
		int n = 0;
		
		try {
			con = getConnect();
			pst = con.prepareStatement("delete from account where accno = ?");
			pst.setInt(1, accno);
			n = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}
	
	public Account viewAccount(int accno)
	{
		try {
			con = getConnect();
			ResultSet rs = null;
			pst = con.prepareStatement("select * from account where accno=?");
			pst.setInt(1, accno);
			rs = pst.executeQuery();
			if(rs.next())
			{
				return new Account(rs.getInt(1),rs.getString(2),rs.getDouble(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Account> viewAccounts()
	{
		ArrayList<Account> al = new ArrayList<Account>();
		try {
			con = getConnect();
			ResultSet rs = null;
			pst = con.prepareStatement("select * from account");
			rs = pst.executeQuery();
			while(rs.next())
			{
				al.add(new Account(rs.getInt(1),rs.getString(2),rs.getDouble(3)));
			}
			return al;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
