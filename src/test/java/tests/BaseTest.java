package tests;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {

    protected WebDriver driver;

    @BeforeTest
    public void setupDriver(ITestContext ctx) throws MalformedURLException {
//        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
//        this.driver = new ChromeDriver();
//        try{
            String host = "localhost";
//            DesiredCapabilities dc;
            MutableCapabilities dc;

            if(System.getProperty("BROWSER") != null &&
                    System.getProperty("BROWSER").equalsIgnoreCase("firefox")){
//                dc = DesiredCapabilities.firefox();
                dc = new FirefoxOptions();
            }else{
//                dc = DesiredCapabilities.chrome();
                dc = new ChromeOptions();
            }

            if(System.getProperty("HUB_HOST") != null){
                host = System.getProperty("HUB_HOST");
            }

            String testName = ctx.getCurrentXmlTest().getName();

            String completeUrl = "http://" + host + ":4444/wd/hub";
            dc.setCapability("name", testName);
            this.driver = new RemoteWebDriver(new URL(completeUrl), dc);

//        }
//        catch (Exception e){
//            System.out.println("Using local chromedriver! RemoteDriver block has failed!");
//            System.setProperty("webdriver.chrome.driver","chromedriver.exe");
//            this.driver = new ChromeDriver();
//        }
    }


    @AfterTest
    public void quitBrowser(){
        this.driver.quit();
    }
}
