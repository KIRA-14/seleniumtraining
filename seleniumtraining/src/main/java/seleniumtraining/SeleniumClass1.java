package seleniumtraining;

import static org.junit.Assert.assertEquals;

import java.awt.Desktop.Action;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumClass1 {

	static WebDriver driver;
	static Select Selectdeopdown;
	static String CurrentUrl;
	static List<String> Resultofpageone = new ArrayList<String>();
	static List<WebElement> Pagination = new ArrayList<WebElement>();
	static Actions acttions;

	public static void main(String[] args) {
		/*
		 * LaunchBrowser("chrome"); Assignment1(); Browserclose();
		 * LaunchBrowser("chrome"); Assignment2(); Browserclose();
		 * LaunchBrowser("chrome");Assignment3();Browserclose();
		 */
		LaunchBrowser("chrome");Assignment4();//Browserclose();
		
	}

	public static void LaunchBrowser(String browser) {

		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.firefox.driver", "./geckodriver.exe");
			driver = new FirefoxDriver();
		}

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
			driver = new ChromeDriver();
		}

		if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "");
			driver = new InternetExplorerDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts();
		driver.get("http://google.com");
	}

	public static void Assignment1() {
		driver.get("https://www.facebook.com");
		driver.navigate().back();
		CurrentUrl = driver.getCurrentUrl();
		System.out.println(CurrentUrl);
		driver.navigate().forward();
		driver.navigate().refresh();
		CurrentUrl = driver.getCurrentUrl();
	}

	@Test
	public static void Assignment2() {
		driver.get("https://www.facebook.com");
		System.out.println(CurrentUrl);
		assertEquals("CurrentPhage", CurrentUrl, "https://www.facebook.com/");
		String FbaccountCreation = driver.findElement(By.xpath("//*[@class='mbs _52lq fsl fwb fcb']/span")).getText();
		System.out.println(FbaccountCreation);
		assertEquals("Create an account", FbaccountCreation);
		driver.findElement(By.name("firstname")).sendKeys("Dummey");
		driver.findElement(By.name("lastname")).sendKeys("Dummey");
		driver.findElement(By.name("reg_email__")).sendKeys("Dummey.Dummy@gamil.com");
		driver.findElement(By.name("reg_passwd__")).sendKeys("Dummey@123");
		driver.findElement(By.name("reg_email_confirmation__")).sendKeys("Dummey.Dummy@gamil.com");
		Select selectednValueDay = new Select(driver.findElement(By.name("birthday_day")));
		selectednValueDay.selectByValue("1");
		Select selectednValueMonth = new Select(driver.findElement(By.name("birthday_month")));
		selectednValueMonth.selectByIndex(1);
		Select selectednValueYear = new Select(driver.findElement(By.name("birthday_year")));
		selectednValueYear.selectByValue("2000");
		driver.findElement(By.xpath("//*[@name='sex'][@value='1']")).click();
		driver.findElement(By.xpath("//*[@name='websubmit']")).click();
	}

	public static void Browserclose() {
		driver.close();
	}

	public static void Assignment3() {
		driver.get("https://in.ebay.com/");
		Assignment3ResultofPage1();
		Assignment3Resultof10thProd();
		Assignment3ResultofAllpageProduct();
	}

	public static void Assignment3ResultofPage1() {
		driver.findElement(By.xpath("//*[@name='_nkw']")).sendKeys("Apple watches");
		driver.findElement(By.xpath("//*[@class='btn btn-prim gh-spr']")).click();
		List<WebElement> list = driver.findElements(By.xpath("//*[@class='s-item__title']"));
		System.out.println(list.size());
		for (WebElement webElement : list) {
			String e = webElement.getText();
			Resultofpageone.add(e);
		}
		System.out.println(Resultofpageone);
	}

	public static void Assignment3Resultof10thProd() {
		System.out.println(Resultofpageone.get(9));
	}

	public static void Assignment3ResultofAllpageProduct() {
		WebDriverWait searchelm = new WebDriverWait(driver, 20);
		Pagination = driver.findElements(
				By.xpath("//*[@class='x-pagination__li x-pagination__li--selected']//following-sibling::li"));
		
		System.out.println(Pagination.size());
		for (WebElement count : Pagination) {
			String a = driver.findElement(By.xpath("//*[@class='x-pagination__li x-pagination__li--selected']")).getText();
			System.out.println(a);
			WebElement clickme = searchelm.until(ExpectedConditions.elementToBeClickable
				(By.xpath("//*[@class='x-pagination__li x-pagination__li--selected']//following-sibling::li")));
			clickme.click();
		}
		
	}
	
	public static void Assignment4() {
		driver.get("http://WWW.snapdeal.com");
		
		WebDriverWait searchelm = new WebDriverWait(driver, 10);
		searchelm.until(ExpectedConditions.visibilityOfElementLocated
		(By.xpath(".//*[contains(text(),'Sign In')]")));
		
		acttions= new Actions(driver);
		acttions.moveToElement(driver.findElement(By.xpath(".//*[contains(text(),'Sign In')]"))).build().perform();
		driver.
		findElement(By.xpath("//*[contains(text(),'login')]//preceding::span[@class='accountBtn btn rippleWhite']")).click();
		
		int i = driver.findElements(By.tagName("iframe")).size();
		
		driver.switchTo().frame(0);
		
		searchelm.until(ExpectedConditions.visibilityOfElementLocated
		(By.xpath(".//input[@name='username']")));
		
		driver.findElement(By.xpath(".//input[@name='username']")).sendKeys("Dummey.Dummy@gmail.com");
		driver.findElement(By.xpath("//*[@id='checkUser']")).click();
		
		
		searchelm.until(ExpectedConditions.visibilityOfElementLocated
		(By.xpath("//*[@name='j_number']")));
		
		driver.findElement(By.xpath("//*[@name='j_number']")).sendKeys("9999999998");
		driver.findElement(By.xpath("//*[@name='j_name']")).sendKeys("Dummey");
		driver.findElement(By.xpath("//*[@id='j_dob']")).clear();
		driver.findElement(By.xpath("//*[@id='j_dob']")).sendKeys("01/01/2001");
		driver.findElement(By.xpath("//*[@class='active day']")).click();
		driver.findElement(By.xpath("//*[@id='j_password']")).sendKeys("Dummey@123");
		driver.findElement(By.xpath("//*[@class='loginCheckbox keepLoginSignUp']")).click();
		driver.findElement(By.xpath("//*[@class='loginCheckbox keepLoginSignUp']")).click();		
		driver.findElement(By.xpath("//*[@id='userSignup']")).click();
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

}