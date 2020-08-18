package SeleniumDBConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DbConnectSelenium {

	public static void main(String[] args) throws SQLException {
	
		// Connect to DB of MySQL Database
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "anagha@123");
		
		//Check weateher DB connected or not
		
		/* if(!dbconnect.isClosed()) {
			
			System.out.println("MySQl Server is successfuly connected");
		} */
		
		// get the data from the employees db of demo  table(3rd record)
		
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from logincredentials" );
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://tutorialsninja.com/demo/index.php?route=account/login");
		
		while (resultSet.next()) {
		driver.findElement(By.id("input-email")).sendKeys(resultSet.getString("username"));
		driver.findElement(By.id("input-password")).sendKeys(resultSet.getString("password"));
		
		
		}
		
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		 
		/* Java code for DB
		
		while (resultSet.next()) {
			
			System.out.println(resultSet.getInt("id"));
			System.out.println(resultSet.getString("name"));
			System.out.println(resultSet.getString("place"));
			System.out.println(resultSet.getInt("experience"));
		}
		
		*/ 

	}

}
