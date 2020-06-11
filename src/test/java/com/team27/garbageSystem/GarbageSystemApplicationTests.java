package com.team27.garbageSystem;

import com.team27.garbageSystem.controllers.AboutController;
import com.team27.garbageSystem.controllers.ContactController;
import com.team27.garbageSystem.controllers.HomeController;
import com.team27.garbageSystem.controllers.LoginController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.io.IOException;
import java.util.Map;

@SpringBootTest
class GarbageSystemApplicationTests {


//	@Test
//	void CheckGoodAdmin() throws IOException {
//		WebDriver driver = new FirefoxDriver();
//		WebDriverWait wait = new WebDriverWait(driver, 20);
//		String admin = "";
//		try{
//			driver.get("http://localhost:8080");
//			driver.findElement(By.id("importPart")).findElement(By.id("login")).click();
//			wait.until(presenceOfElementLocated(By.id("admin-btn")));
//			driver.findElement(By.id("uname")).sendKeys("admin1");
//			driver.findElement(By.id("password")).sendKeys("1234admin1");
//			driver.findElement(By.id("admin-btn")).click();
//			WebElement adminName = wait.until(presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/p/strong")));
//			admin = adminName.getText();
//		}finally {
//			driver.quit();
//		}
//		Assertions.assertEquals("Ofir Haim", admin);
//	}

//	@Test
//	void CheckBadAdmin() throws IOException {
//		WebDriver driver = new FirefoxDriver();
//		WebDriverWait wait = new WebDriverWait(driver, 20);
//		String admin = "";
//		try{
//			driver.get("http://localhost:8080");
//			driver.findElement(By.id("importPart")).findElement(By.id("login")).click();
//			wait.until(presenceOfElementLocated(By.id("admin-btn")));
//			driver.findElement(By.id("uname")).sendKeys("add");
//			driver.findElement(By.id("password")).sendKeys("1234");
//			driver.findElement(By.id("admin-btn")).click();
//		} finally {
//			driver.quit();
//		}
//		Assertions.assertEquals("", admin);
//	}

//	@Test
//	void CheckGoodWorker() throws IOException {
//		WebDriver driver = new FirefoxDriver();
//		WebDriverWait wait = new WebDriverWait(driver, 20);
//		String admin = "";
//		try{
//			driver.get("http://localhost:8080");
//			driver.findElement(By.id("importPart")).findElement(By.id("login")).click();
//			wait.until(presenceOfElementLocated(By.id("admin-btn")));
//			driver.findElement(By.id("uname")).sendKeys("worker1");
//			driver.findElement(By.id("password")).sendKeys("1234worker1");
//			driver.findElement(By.id("admin-btn")).click();
//			WebElement adminName = wait.until(presenceOfElementLocated(By.xpath("//*[@id=\"app\"]/p/strong")));
//			admin = adminName.getText();
//		} finally {
//			driver.quit();
//		}
//		Assertions.assertEquals("Liam Ava", admin);
//	}

//	@Test
//	void CheckWorkerRoute() throws IOException {
//		WebDriver driver = new FirefoxDriver();
//		WebDriverWait wait = new WebDriverWait(driver, 20);
//		String mapid = "";
//		try{
//			driver.get("http://localhost:8080");
//			driver.findElement(By.id("importPart")).findElement(By.id("login")).click();
//			wait.until(presenceOfElementLocated(By.id("admin-btn")));
//			driver.findElement(By.id("uname")).sendKeys("worker1");
//			driver.findElement(By.id("password")).sendKeys("1234worker1");
//			driver.findElement(By.id("admin-btn")).click();
//			wait.until(presenceOfElementLocated(By.xpath("//*[@id=\"show-truck-route\"]/i")));
//			driver.findElement(By.xpath("//*[@id=\"show-truck-route\"]/i")).click();
//			WebElement map = wait.until(presenceOfElementLocated(By.xpath("//*[@id=\"show-map\"]")));
//			mapid = map.getAttribute("id");
//		} finally {
//			driver.quit();
//		}
//		Assertions.assertEquals("show-map", mapid);
//	}

//@Test
//void AdminShowBins() throws IOException {
//	WebDriver driver = new FirefoxDriver();
//	WebDriverWait wait = new WebDriverWait(driver, 20);
//	String tableId = "";
//	try{
//		driver.get("http://localhost:8080");
//		driver.findElement(By.id("importPart")).findElement(By.id("login")).click();
//		wait.until(presenceOfElementLocated(By.id("admin-btn")));
//		driver.findElement(By.id("uname")).sendKeys("admin1");
//		driver.findElement(By.id("password")).sendKeys("1234admin1");
//		driver.findElement(By.id("admin-btn")).click();
//		wait.until(presenceOfElementLocated(By.xpath("//*[@id=\"show-bins\"]/i")));
//		driver.findElement(By.xpath("//*[@id=\"show-bins\"]/i")).click();
//		WebElement bins = wait.until(presenceOfElementLocated(By.xpath("//*[@id=\"bins-table\"]")));
//		tableId = bins.getAttribute("id");
//	} finally {
//		driver.quit();
//	}
//	Assertions.assertEquals("bins-table", tableId);
//}

	@Test
	void CheckGoodAdmin() throws IOException {
		WebDriver driver = new FirefoxDriver();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		String btn = "";
		try{
			driver.get("http://localhost:8080");
			driver.findElement(By.id("importPart")).findElement(By.id("login")).click();
			WebElement b =  wait.until(presenceOfElementLocated(By.id("admin-btn")));
			btn = b.getAttribute("id");
		}finally {
			driver.quit();
		}
		Assertions.assertEquals("admin-btn", btn);
	}

	@Test
	void CheckBadAdmin() throws IOException {
		WebDriver driver = new FirefoxDriver();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		String input = "";
		try{
			driver.get("http://localhost:8080");
			driver.findElement(By.id("importPart")).findElement(By.id("contact")).click();
			WebElement i = wait.until(presenceOfElementLocated(By.xpath("/html/body/div[4]/input")));
			input = i.getAttribute("class");
		} finally {
			driver.quit();
		}
		Assertions.assertEquals("form-control", input);
	}


	@Test
	void CheckGoodWorker() throws IOException {
		WebDriver driver = new FirefoxDriver();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		String p = "";
		try{
			driver.get("http://localhost:8080");
			driver.findElement(By.id("importPart")).findElement(By.id("about")).click();
			WebElement pic = wait.until(presenceOfElementLocated(By.xpath("/html/body/div[2]/img")));
			p = pic.getAttribute("id");
		} finally {
			driver.quit();
		}
		Assertions.assertEquals("image", p);
	}

	@Test
	void CheckWorkerRoute() throws IOException {
		WebDriver driver = new FirefoxDriver();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		String p = "";
		try{
			driver.get("http://localhost:8080");
			driver.findElement(By.xpath("/html/body/div[2]/a")).click();
			WebElement pic = wait.until(presenceOfElementLocated(By.xpath("/html/body/div[2]/img")));
			p = pic.getAttribute("id");
		} finally {
			driver.quit();
		}
		Assertions.assertEquals("image", p);
	}

	@Test
	void AdminShowBins() throws IOException {
		WebDriver driver = new FirefoxDriver();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		String type = "";
		try{
			driver.get("http://localhost:8080");
			driver.findElement(By.id("importPart")).findElement(By.id("login")).click();
			WebElement t = wait.until(presenceOfElementLocated(By.xpath("//*[@id=\"password\"]")));
			type = t.getAttribute("type");
		} finally {
			driver.quit();
		}
		Assertions.assertEquals("password", type);
	}


	@Test
	void AboutTest(){
		Assertions.assertEquals(true, AboutRoutesTest(new AboutController()));
	}

	@Test
	void HomeTest(){
		Assertions.assertEquals(true, HomeRoutesTest(new HomeController()));
	}

	@Test
	void ContactTest(){
		Assertions.assertEquals(true, ContactRoutesTest(new ContactController()));
	}

	private Boolean ContactRoutesTest(ContactController contact_tester){
		return contact_tester.home().equals("contact");
	}

	private Boolean HomeRoutesTest(HomeController home_tester){
		return home_tester.home().equals("home");
	}

	private Boolean AboutRoutesTest(AboutController about_tester){
		return about_tester.home().equals("about");
	}

	private Boolean AdministratorLoginTest(LoginController login_tester, Map<String, String> test_user) {
		return login_tester.loginCheck(test_user).equals("failed");
	}

	private Boolean LoginRoutesTest(LoginController login_tester){
		return (login_tester.login().equals("login"));
	}

}
