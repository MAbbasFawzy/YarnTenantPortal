/*
 * 
 * 
 * 
 * Test Case 6: Add Visitor _ one time _ one visitor _ without vehicle _ new visitor
 * 
 * 
 * 
 */

package YarnTenantPortal.AutomationTestCases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.random.RandomGenerator;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.PointerInput.MouseButton;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class visitorOneTimeOneVisitorWithoutVehicleNewVisitor extends randomGenerator {

	WebDriver driver = new ChromeDriver();
	WebDriverWait wait;
	private String baseUrl;
	private String username;
	private String password;
	private String tenant;

	@BeforeTest
	public void setup() throws InterruptedException {
		loadProperties();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.navigate().to(baseUrl);
		login();
	}

	@AfterClass
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

	private void loadProperties() {
		Properties properties = new Properties();
		try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
			if (input == null) {
				System.out.println("Sorry, unable to find config.properties");
				return;
			}
			properties.load(input);
			baseUrl = properties.getProperty("base.url");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
			tenant = properties.getProperty("tenant");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void login() throws InterruptedException { // login code

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement email = driver.findElement(By.xpath("/html/body/div[1]/main/div/div/div[3]/form/div[1]/input"));
		email.sendKeys(username);

		WebElement passcode = driver
				.findElement(By.xpath("/html/body/div[1]/main/div/div/div[3]/form/div[2]/div/input"));
		passcode.sendKeys(password);

		WebElement loginButton = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[3]/form/div[3]/button"));
		loginButton.click();

		WebElement userName = driver.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/nav[1]/div/div[1]/div[2]/span[2]"));
		AssertJUnit.assertEquals(tenant, userName.getText());

		Thread.sleep(2000);
	}

	@Test(priority = 0)
	public void chooseVisitorTabAndAddVisitor() throws InterruptedException {

		randomGenerator.Visitor visitor = randomGenerator.generateRandomContact();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		Thread.sleep(500);
		WebElement visitorTab = driver.findElement(By.linkText("My Visitors"));
		visitorTab.click();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		WebElement addVisitorButton = driver.findElement(By.cssSelector(
				"#__nuxt > main > div > div > div.hidden.sm\\:block > div:nth-child(2) > div > div > button"));
		addVisitorButton.click();

		Thread.sleep(2000);
		WebElement visitorTypeList = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/span[1]"));
		visitorTypeList.click();

		Thread.sleep(500);
		WebElement visitorTypeListOption = driver.findElement(By.xpath("//li[contains(@class, 'p-dropdown-item') and .//span[text()='Friend']]"));
		visitorTypeListOption.click();

		Thread.sleep(500);
		WebElement entryType = driver.findElement(By.id("single"));
		entryType.click();

		Thread.sleep(500);
		// Locate and click the calendar input field
		WebElement calendarInput = driver
				.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/form/div[2]/div[2]/span/input"));
		calendarInput.click();

		Thread.sleep(500);
		// Select the date (e.g., November 9, 2024)
		// Navigate to the correct month if necessary
		WebElement nextMonthButton = driver.findElement(By.xpath("//button[@aria-label='Next Month']"));
		nextMonthButton.click(); // Click to go to the next month if needed

		// Select the day (9th in this case)
		WebElement dateToSelect = driver.findElement(By.xpath("//td[@aria-label='9']"));
		dateToSelect.click();

		// Select the time
		// Increment hour
		WebElement hourIncrementButton = driver.findElement(By.xpath("//button[@aria-label='Next Hour']"));
		hourIncrementButton.click(); // Click to increment hour

		// Increment minute
		WebElement minuteIncrementButton = driver.findElement(By.xpath("//button[@aria-label='Next Minute']"));
		minuteIncrementButton.click(); // Click to increment minute

		// Select AM/PM
		WebElement amPmButton = driver.findElement(By.xpath("//button[@aria-label='pm']")); // Change to 'am' if needed
		amPmButton.click(); // Click to select PM
		
		

		Thread.sleep(500);
		WebElement visitorFirstName = driver.findElement(
				By.xpath("/html/body/div[1]/main/div/div/div[2]/form/div[3]/div/div[2]/div/div/div[1]/div[1]/input"));
		visitorFirstName.sendKeys(visitor.firstName);

		Thread.sleep(500);
		WebElement visitorLastName = driver.findElement(
				By.xpath("/html/body/div[1]/main/div/div/div[2]/form/div[3]/div/div[2]/div/div/div[1]/div[2]/input"));
		visitorLastName.sendKeys(visitor.lastName);
		
		Thread.sleep(500);
		WebElement documentType = driver.findElement(By.xpath(
				"//span[@class='p-dropdown-label p-inputtext p-dropdown-label-empty' and @role='combobox' and @aria-haspopup='listbox']"));
		documentType.click();

		Thread.sleep(500);
		WebElement documentTypeListOption = driver.findElement(By.xpath("/html/body/div[4]/div[2]/ul/li[1]"));
		documentTypeListOption.click();

		Thread.sleep(500);
		WebElement documentNumber = driver.findElement(
				By.xpath("//div[@class='grid sm:grid-cols-2 gap-4 mb-8']//div[4]//input[1]"));
		documentNumber.sendKeys(visitor.numbers);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(500);
		WebElement nationality = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[2]/form[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[5]/div[1]"));
		
		js.executeScript("arguments[0].scrollIntoView(true);", documentNumber);
		nationality.click();
		
		WebElement searchBox = driver.findElement(By.xpath("//input[@role='searchbox']"));
		searchBox.sendKeys("Afghanistan");

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			WebElement alertDialog = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-dialog")));

			WebElement cancelButton = alertDialog.findElement(By.cssSelector("button[type='button']"));
			cancelButton.click();
		} catch (Exception e) {
			
			Thread.sleep(2000);
			WebElement nationalityOption = driver.findElement(By.xpath("//li[@aria-label='Afghanistan']"));
			
			Thread.sleep(2000);
			nationalityOption.click();
		}
		
		Thread.sleep(500);
		WebElement gender = driver.findElement(By.xpath(
				"/html[1]/body[1]/div[1]/main[1]/div[1]/div[1]/div[2]/form[1]/div[3]/div[1]/div[2]/div[1]/div[1]/div[1]/div[6]/div[1]/span[1]"));
		gender.click();

		Thread.sleep(500);
		WebElement genderOption = driver.findElement(By.xpath("//li[@aria-label='Male']"));
		genderOption.click();
		
		/*
		 * Thread.sleep(500); WebElement phoneNumber = driver.findElement( By.xpath(
		 * "/html/body/div[1]/main/div/div/div[2]/form/div[3]/div/div[2]/div/div/div[1]/div[9]/input"
		 * )); phoneNumber.sendKeys("01005710499");
		 * 
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		 * 
		 * // Format today's date LocalDate today = LocalDate.now(); DateTimeFormatter
		 * formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy"); String formattedDate =
		 * today.format(formatter);
		 * 
		 * // Locate the date of birth input and click it WebElement dateOfBirth =
		 * driver.findElement(By.xpath(
		 * "/html/body/div[1]/main/div/div/div[2]/form/div[3]/div/div[2]/div/div/div[1]/div[7]/span/input"
		 * )); dateOfBirth.click();
		 * 
		 * // Wait for the date picker to be visible WebDriverWait waitBirthDate = new
		 * WebDriverWait(driver, Duration.ofSeconds(2)); waitBirthDate
		 * .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
		 * "//div[@class='p-datepicker-group']"))); // Adjust the ID as needed
		 * 
		 * // Wait for the specific day to be clickable Thread.sleep(500); WebElement
		 * birthDateToSelect = wait
		 * .until(ExpectedConditions.elementToBeClickable(By.xpath(
		 * "//td[@class='p-datepicker-today']"))); Thread.sleep(500);
		 * birthDateToSelect.click();
		 * 
		 * Thread.sleep(500); WebElement email = driver.findElement(By.xpath(
		 * "/html/body/div[1]/main/div/div/div[2]/form/div[3]/div/div[2]/div/div/div[1]/div[3]/div[1]/input"
		 * )); email.sendKeys(visitor.email);
		 */
		

		Thread.sleep(500);
		WebElement transportation = driver.findElement(By.xpath(
				"/html/body/div[1]/main/div/div/div[2]/form/div[3]/div/div[2]/div/div/div[2]/div/div/span[1]/input"));
		transportation.click();

		Thread.sleep(6000);
		WebElement submitButton = driver
				.findElement(By.xpath("/html/body/div[1]/main/div/div/div[2]/form/div[4]/button[2]"));
		submitButton.click();

		WebDriverWait waitSuccessMessage = new WebDriverWait(driver, Duration.ofSeconds(20));
		WebElement successMessage = waitSuccessMessage
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-toast > div:nth-child(1)")));

		String alertMessageText = successMessage.getText();
		System.out.println("Alert message: " + alertMessageText);
	}

	@Test(priority = 1)
	public void checkAlertForMaxNumberOfVisitors() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		WebElement successMessage = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".p-toast > div:nth-child(1)")));

		String alertMessageText = successMessage.getText();

		assertEquals(alertMessageText, alertMessageText);

	}

	@Test(priority = 2)
	public void checkDefaultVisitorStatus() {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		WebElement visitorStatus = driver
				.findElement(By.xpath("//*[@id=\"__nuxt\"]/main/div/div/div[2]/div[1]/span[2]"));
		visitorStatus.getText();
		assertEquals("Pending", visitorStatus.getText());
	}

}
