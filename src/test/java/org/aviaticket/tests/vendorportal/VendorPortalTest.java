package org.aviaticket.tests.vendorportal;


import org.aviaticket.tests.AbstractTest;
import org.aviaticket.tests.vendorportal.model.VendorPortalTestData;
import org.aviaticket.util.JsonUtil;
import org.aviaticket.vendorportal.DashboardPage;
import org.aviaticket.vendorportal.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VendorPortalTest extends AbstractTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;

    @BeforeTest
    @Parameters("testDataPath")

    public void setPageObjects(String testDataPath){
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
        this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
    }

    @Test
    public void loginTest(){
        loginPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/vendor-app/index.html");
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testData.getUsername(), testData.getPassword());

    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest(){
        Assert.assertTrue(dashboardPage.isAt());

        // finance metrics
        Assert.assertEquals(dashboardPage.getMonthlyEarning(), testData.getMonthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(), testData.getAnnualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(), testData.getProfitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(), testData.getAvailableInventory());

        // order history search
        dashboardPage.searchOrderHistoryBy(testData.getSearchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultsCount(), testData.getSearchResultsCount());
    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logoutTest(){
        dashboardPage.logout();
        Assert.assertTrue(loginPage.isAt());
    }

}