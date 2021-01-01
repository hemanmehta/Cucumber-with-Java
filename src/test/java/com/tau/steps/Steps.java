package com.tau.steps;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.tau.base.BaseUtil;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Steps extends BaseUtil {
	private BaseUtil baseUtil;
	private WebDriver driver;

	public Steps(BaseUtil util) {
		this.baseUtil = util;
	}

	@Before()
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@Given("I am in login page")
	@Given("I am in Login page of the Para Bank Application")
	public void i_am_in_login_page_of_the_para_bank_application() {
		driver.get("https://parabank.parasoft.com/parabank/index.htm");
	}

	@When("I enter credentials")
	public void i_enter_credentials() {
		driver.findElement(By.name("username")).sendKeys("tautestuser");
		driver.findElement(By.name("password")).sendKeys("password");
		driver.findElement(By.name("username")).submit();

	}

	// Scenario Outline Demo
	@When("I enter valid {string} and {string}")
	public void i_enter_valid_credentials(String username, String password) {
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("username")).submit();

	}

	// DataTable Demo
	@When("I enter credentials with datatable")
	public void i_enter_credentials_with_datatable(DataTable table) {

		List<String> loginForm = table.asList();

		driver.findElement(By.name("username")).sendKeys(loginForm.get(0));
		driver.findElement(By.name("password")).sendKeys(loginForm.get(1));
		driver.findElement(By.name("username")).submit();

	}

	// Dependency Injection Demo
	@When("I enter valid {string} and {string} with {string}")
	public void i_enter_valid_credentials_with_DI(String username, String password, String userFullName1) {
		
		baseUtil.userFullName=userFullName1;
		driver.findElement(By.name("username")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("username")).submit();

	}
	@Then("I should be taken to DI Overview Page")
	public void i_should_be_taken_to_DI_overview_page() throws InterruptedException {
		Thread.sleep(5000);
		
		String actualuserFullName = driver.findElement(By.className("smallText")).getText().toString();
		System.out.println("=====>"+baseUtil.userFullName);
		Assert.assertTrue(actualuserFullName, actualuserFullName.contains(baseUtil.userFullName));

		driver.findElement(By.linkText("Log Out")).click();
	}

	@Then("I should be taken to Overview Page")
	public void i_should_be_taken_to_overview_page() throws InterruptedException {
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"rightPanel\"]/div/div/h1")).isDisplayed();
		driver.findElement(By.linkText("Log Out")).click();
	}

	@After()
	public void teardown() {
		driver.close();
	}

}
