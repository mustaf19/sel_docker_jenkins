package com.newtours.tests;

import com.newtours.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tests.BaseTest;

public class BookFlightTest extends BaseTest {

//    private WebDriver driver;
    private String expectedPrice;
    private String noOfPassengers;

//    @BeforeTest
//    @Parameters({"noOfPassengers","expectedPrice"})
//    public void setupDriver(String noOfPassengers, String expectedPrice){
//        this.noOfPassengers = noOfPassengers;
//        this.expectedPrice = expectedPrice;
//        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
//        this.driver = new ChromeDriver();
//    }
//    @Parameters({"noOfPassengers", "expectedPrice"})
//    public void setupParameters(String noOfPassengers, String expectedPrice){
//        this.noOfPassengers = noOfPassengers;
//        this.expectedPrice = expectedPrice;
//    }

    @BeforeTest
    @Parameters({"noOfPassengers","expectedPrice"})
    public void setupParameters(String noOfPassengers, String expectedPrice){
        this.noOfPassengers=noOfPassengers;
        this.expectedPrice=expectedPrice;
    }

    @Test
    public void registrationPage() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo();
        registrationPage.enterUserDetails("selenium", "docker");
        registrationPage.enterUserCredentials("selenium", "docker");
        registrationPage.submit();
    }

    // TestNG doesn't sequentially execute @Test, thats why
    // to execute tests sequentially we use dependsOn. so that depending @Test is executed first
    @Test(dependsOnMethods = "registrationPage")  // dependOnMethods are used because
    public void registrationConfirmationPage(){
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        registrationConfirmationPage.goToFlightDetailsPage();
    }

    @Test(dependsOnMethods = "registrationConfirmationPage")
    public void flightDetailsPage(){
        FlightDetailsPage flightDetailsPage = new FlightDetailsPage(driver);
        flightDetailsPage.selectPassengers(noOfPassengers);
        flightDetailsPage.goToFindFlightsPage();
    }

    @Test(dependsOnMethods = "flightDetailsPage")
    public void findFlightPage(){
        FindFlightPage findFlightPage = new FindFlightPage(driver);
        findFlightPage.submitFindFlightPage();
        findFlightPage.goToFlightConfirmationPage();
    }

    @Test(dependsOnMethods = "findFlightPage")
    public void flightConfirmationPage(){
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
//        flightConfirmationPage.printConfirmation();
        String actualPrice = flightConfirmationPage.getPrice();
//        System.out.println("reutrned actualPrice "+actualPrice);
        Assert.assertEquals(actualPrice, expectedPrice);
    }

//    @AfterTest
//    public void quitBrowser(){
//        this.driver.quit();
//    }

}
