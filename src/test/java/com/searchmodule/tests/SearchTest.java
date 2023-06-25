package com.searchmodule.tests;

import com.searchmodule.pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tests.BaseTest;

import javax.annotation.concurrent.NotThreadSafe;

public class SearchTest extends BaseTest {

//    private WebDriver driver;
//    private String keyword;

//    @BeforeTest
//    @Parameters({"keyword"})
//    public void setupDriver(String keyword){
//        this.keyword = keyword;
//        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
//        this.driver = new ChromeDriver();
//    }

    @Test
    @Parameters({"keyword"})
    public void search(String keyword){
        SearchPage searchPage = new SearchPage(driver);
        searchPage.goTo();
        searchPage.doSearch(keyword);
        searchPage.goToVideos();
        int size = searchPage.getResult();
        Assert.assertTrue(size > 0);
    }

//    @AfterTest
//    public void quitBrowser(){
//        this.driver.quit();
//    }

}
