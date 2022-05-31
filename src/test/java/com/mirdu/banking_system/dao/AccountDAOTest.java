package com.mirdu.banking_system.dao;

import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.junit.Assert;
import org.junit.Test;

public class AccountDAOTest {
	AccountDAO accdao = new AccountDAO();
	@Test
	public void testGetConnect() {
		Assert.assertNotNull(accdao.getConnect());
	}
	
	@Test(expected=SQLIntegrityConstraintViolationException.class)
	public void testInsertAccount() throws SQLException {
		accdao.insertAccount(301,"Akshay",32000);
	}

	@Test
	public void testUpdateAccountName() {
		Assert.assertEquals(1,accdao.updateAccountName(301,"Akshay"));
	}

	@Test
	public void testDecreaseAccountBalance() {
		Assert.assertEquals(1, accdao.decreaseAccountBalance(103, 2000));
	}

	@Test
	public void testIncreaseAccountBalance() {
		Assert.assertEquals(1, accdao.increaseAccountBalance(901, 7000));
	}

	@Test
	public void testDeleteAccount() {
		Assert.assertEquals(1, accdao.deleteAccount(105));
	}

	@Test
	public void testViewAccount() {
		Assert.assertNotNull(accdao.viewAccount(101));
	}

	@Test
	public void testViewAccounts() {
		Assert.assertNotNull(accdao.viewAccounts());
	}

}
