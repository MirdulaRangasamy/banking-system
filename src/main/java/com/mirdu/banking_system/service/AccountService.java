package com.mirdu.banking_system.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.mirdu.banking_system.dao.AccountDAO;
import com.mirdu.banking_system.entity.Account;


public class AccountService {
	private AccountDAO accDao = new AccountDAO();
	private Scanner ip = new Scanner(System.in);
	
	public boolean addAccount(int count)
	{
		int n = 0;
		while(count > 0)
		{
			System.out.println("Enter the Account details:");
			System.out.println("Enter the Account No:");
			int accno = ip.nextInt();
			System.out.println("Enter the Accont Holder Name:");
			String accname = ip.next();
			System.out.println("Enter the Initial Deposit amount: ");
			double balance = ip.nextDouble();
			try {
			if(accDao.insertAccount(accno, accname, balance)==1)
				n++;
			}catch(Exception e)
			{
				System.out.println(e);
			}
			count--;
		}
		if(n>0)
			return true;
		else
			return false;
	}
	
	public boolean changeName(int accno, String accname)
	{
		int n = accDao.updateAccountName(accno, accname);
		if(n > 0)
			return true;
		else
			return false;
	}
	
	public boolean withdraw(int accno, double amount)
	{
		int n = accDao.decreaseAccountBalance(accno, amount);
		if(n > 0)
			return true;
		else
			return false;
	}
	
	public boolean deposit(int accno, double amount)
	{
		int n = accDao.increaseAccountBalance(accno, amount);
		if(n > 0)
			return true;
		else
			return false;
	}
	
	public boolean deleteAccount(int accno)
	{
		accDao.deleteAccount(accno);
		return true;
	}
	
	public Account viewAccount(int accno)
	{
		return accDao.viewAccount(accno);
	}
	
	public ArrayList<Account> viewAccounts()
	{
		return accDao.viewAccounts();
	}
}
