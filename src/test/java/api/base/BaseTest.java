package api.base;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeClass;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class BaseTest {

	@BeforeClass
	public void setupLogging() {
	    try {
	        FileOutputStream fos = new FileOutputStream("log.txt");
	        PrintStream log = new PrintStream(fos);

	        // Add ResponseLoggingFilter.logResponseTo(System.out) to see it in console too
	        RestAssured.filters(
	            new RequestLoggingFilter(log), 
	            new ResponseLoggingFilter(log),
	            new RequestLoggingFilter(), // This prints to console
	            new ResponseLoggingFilter() // This prints to console
	        );
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}