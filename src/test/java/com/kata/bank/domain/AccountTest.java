package com.kata.bank.domain;

import com.kata.bank.BankApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BankApplication.class)
public class AccountTest {

	private Account account;

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

	@Before
	public void setup() {
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
		account = new Account();
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
		System.setErr(null);
	}
	
	@Test
	public void should_deposit_amount_in_account_and_add_statement() {
		LocalDate date = LocalDate.of(2016, Month.JUNE, 1);
		double deposit = 30;
		AccountStatement statement = new AccountStatement(date, deposit, account.getBalance());
		account.deposit(deposit, date);
		assertThat(account.getAccountStatements().contains(statement), is(true));
		assertThat(account.getBalance(), is(equalTo(30D)));
		assertThat(account.getAccountStatements().get(0).getBalance(), is(equalTo(0D)));
		assertThat(account.getAccountStatements().get(0).getAmount(), is(equalTo(30D)));
		assertThat(account.getAccountStatements().get(0).getDate(), is(equalTo(date)));
		System.out.print(account.printStatement());
		assertThat(account.printStatement(), is(equalTo(outContent.toString())));

	}

	@Test
	public void should_withdrawal_amount_in_account_and_add_statement() {
		LocalDate date = LocalDate.of(2016, Month.JULY, 1);
		double withdrawal = 50;
		AccountStatement statement = new AccountStatement(date, -withdrawal, account.getBalance());
		account.withdrawal(withdrawal, date);
		assertThat(account.getAccountStatements().contains(statement), is(true));
		assertThat(account.getBalance(), is(equalTo(-50D)));
		assertThat(account.getAccountStatements().get(0).getBalance(), is(equalTo(0D)));
		assertThat(account.getAccountStatements().get(0).getAmount(), is(equalTo(-50D)));
		assertThat(account.getAccountStatements().get(0).getDate(), is(equalTo(date)));
		System.out.print(account.printStatement());
		assertThat(account.printStatement(), is(equalTo(outContent.toString())));
	}

	


}
