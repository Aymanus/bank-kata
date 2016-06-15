package com.kata.bank.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Account {

	private double balance;

	private List<AccountStatement> accountStatements;

	public Account() {
		this.balance = 0;
		accountStatements = new ArrayList<>();
	}

	public void deposit(double amount, LocalDate date) {
		AccountStatement statement = new AccountStatement(date, amount, balance);
		accountStatements.add(statement);
		this.balance += amount;
	}

	public void withdrawal(double amount, LocalDate date) {
		AccountStatement statement = new AccountStatement(date, -amount, balance);
		accountStatements.add(statement);
		this.balance -= amount;
	}

	public String printStatement(){
		return accountStatements.toString();
	}

	public List<AccountStatement> getAccountStatements() {
		return accountStatements;
	}

	public double getBalance() {
		return balance;
	}
	
}
