package org.aviaticket.tests.flightreservation;

import org.aviaticket.flightreservation.*;

import org.aviaticket.tests.AbstractTest;
import org.aviaticket.tests.flightreservation.model.FlightReservationTestData;
import org.aviaticket.util.JsonUtil;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import com.codeborne.selenide.WebDriverRunner;

public class FlightReservationTest extends AbstractTest {

    private FlightReservationTestData testData;

    @BeforeTest
  @Parameters("testDataPath")

   public void setParameters(String testDataPath) {
  this.testData = JsonUtil.getTestData(testDataPath, FlightReservationTestData.class);

    }

    @Test()
    public void userRegistrationTest(){

        RegistrationPage registrationPage = new RegistrationPage(driver);
        driver.get("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html");
        Assert.assertTrue(registrationPage.isAt());

        registrationPage.enterUserDetails(testData.getFirstName(), testData.getLastName());
        registrationPage.enterUserCredentials(testData.getEmail(), testData.getPassword());
        registrationPage.enterAddress(testData.getStreet(), testData.getCity(), testData.getZip());
        registrationPage.register();

    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmationTest(){
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        Assert.assertEquals(registrationConfirmationPage.getFirstName(), testData.getFirstName());
        registrationConfirmationPage.goToFlightsSearch();
    }

    @Test(dependsOnMethods = "registrationConfirmationTest")
    public void flightsSearchTest(){
        FlightsSearchPage flightsSearchPage = new FlightsSearchPage(driver);
        Assert.assertTrue(flightsSearchPage.isAt());
        flightsSearchPage.selectPassengers(testData.getPassengersCount());
        flightsSearchPage.searchFlights();
    }

    @Test(dependsOnMethods = "flightsSearchTest")
    public void flightsSelectionTest(){
        FlightsSelectionPage flightsSelectionPage = new FlightsSelectionPage(driver);
        Assert.assertTrue(flightsSelectionPage.isAt());
        flightsSelectionPage.selectFlights();
        flightsSelectionPage.confirmFlights();
    }

    @Test(dependsOnMethods = "flightsSelectionTest")
    public void flightReservationConfirmationTest(){
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        Assert.assertEquals(flightConfirmationPage.getPrice(), testData.getExpectedPrice());

    }

}