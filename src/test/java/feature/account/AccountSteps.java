package feature.account;

import com.kata.bank.domain.Account;
import cucumber.api.Format;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountSteps {

    private Account account;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Given("bank account")
    public void newAccount(){
        account =  new Account();
    }

    @When("i make a deposit of (.+) euros at date (.+)$")
    public void deposit(double amount, @Format("yyyy-MM-dd") Date date){
        Instant instant = Instant.ofEpochMilli(date.getTime());
        LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        account.deposit(amount, localDate);
    }

    @When("i make a withdrawal of (.+) euros at date (.+)$")
    public void withdrawal(double amount, @Format("yyyy-MM-dd") Date date){
        Instant instant = Instant.ofEpochMilli(date.getTime());
        LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
        account.withdrawal(amount, localDate);
    }

    @Then("my balance should be (.+) euros")
    public void verifyBalance(double balance){
        assertThat(account.getBalance(), is(equalTo(balance)));
    }

    @And("my historical is :")
    public void verifyHistorical(List<AccountStatementTest> statementTests){
        for(int i =0; i<statementTests.size();i++){
            assertThat(account.getAccountStatements().get(i).getBalance(), is(equalTo(statementTests.get(i).balance)));
            assertThat(account.getAccountStatements().get(i).getAmount(), is(equalTo(statementTests.get(i).amount)));
            assertThat(account.getAccountStatements().get(i).getDate(),
                    is(equalTo(LocalDate.parse(statementTests.get(i).date, formatter))));
        }

    }

    public class AccountStatementTest{
        public String date;
        public double amount;
        public double balance;
    }

}
