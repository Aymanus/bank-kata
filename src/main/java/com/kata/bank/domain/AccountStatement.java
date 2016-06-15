package com.kata.bank.domain;

import java.time.LocalDate;
import java.util.Objects;

public class AccountStatement {

	private LocalDate date;
	private double amount;
	private double balance;

	public AccountStatement(LocalDate date, double amount, double balance) {
		this.date = date;
		this.amount = amount;
		this.balance = balance;
	}

	public LocalDate getDate() {
		return date;
	}

	public double getAmount() {
		return amount;
	}

	public double getBalance() {
		return balance;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AccountStatement statement = (AccountStatement) o;
		return Double.compare(statement.amount, amount) == 0 &&
				Double.compare(statement.balance, balance) == 0 &&
				Objects.equals(date, statement.date);
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, amount, balance);
	}

	@Override
	public String toString() {
		return "AccountStatement{" +
				"date=" + date +
				", amount=" + amount +
				", balance=" + balance +
				'}';
	}
}
