package com.mirdu.banking_system;

import java.util.Scanner;

import com.mirdu.banking_system.entity.Account;
import com.mirdu.banking_system.service.AccountService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	AccountService as = new AccountService();
		int accno;
		double amt;
		char ch = 'Y';
		while(ch == 'Y')
		{
		System.out.println("*********BANKING SYSTEM***********");
		System.out.println("1)Add Account\n2)View Account\n3)View Accounts\n4)Change Name\n5)Withdraw Account\n6)Deposit Account\n7.)Delete Account");
		Scanner ip = new Scanner(System.in);
		System.out.println("Enter your choice: ");
		int option = ip.nextInt();
		switch(option)
		{
			case 1:
				System.out.println("Enter the number of Accounts:");
				int count = ip.nextInt();
				as.addAccount(count);
				break;
			case 2:
				System.out.println("Enter the account number of Customer:");
				accno = ip.nextInt();
				System.out.println(as.viewAccount(accno));
				break;
			case 3:
				for(Account a:as.viewAccounts())
				{
					System.out.println(a);
				}
				break;
			case 4:
				System.out.println("Enter the accno and the new Name of Customer:");
				accno = ip.nextInt();
				String accname = ip.next();
				as.changeName(accno,accname);
				break;
			case 5:
				System.out.println("Enter the account number of Customer:");
				accno = ip.nextInt();
				System.out.println("Enter the amount to be withdrawn..");
				amt = ip.nextDouble();
				as.withdraw(accno,amt);
				break;
			case 6:
				System.out.println("Enter the account number of Customer:");
				accno = ip.nextInt();
				System.out.println("Enter the amount to be deposited..");
				amt = ip.nextDouble();
				as.deposit(accno,amt);
				break;
			case 7:
				System.out.println("Enter the account number of Customer:");
				accno = ip.nextInt();
				as.deleteAccount(accno);
				break;
			default:
				System.out.println("Invalid Choice..");
				break;
		}
		System.out.println("Enter Y to continue...");
		ch = ip.next().charAt(0);
		}
		System.out.println("Thank you for using...");
    }
}
