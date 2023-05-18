package qq;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class sqll {

	public static void main(String[] args) throws SQLException, InterruptedException {
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/qafox", "root", "root");	
/*if(!connection.isClosed()) {
	System.out.println("we have successfully connected to database");
}*/
			Statement statement = connection.createStatement();	
	ResultSet resultSet = statement.executeQuery("select * from logincredentials;");
	WebDriverManager.chromedriver().setup();
	
	WebDriver driver= new ChromeDriver();
	//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().window().maximize();
	//Thread.sleep(3000);
    
    driver.get("https://tutorialsninja.com/demo/");
	resultSet.next();
		driver.findElement(By.id("input-email")).sendKeys(resultSet.getString("username"));
		driver.findElement(By.id("input-password")).sendKeys(resultSet.getString("password"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		

}}
