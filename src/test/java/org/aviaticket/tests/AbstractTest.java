package org.aviaticket.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class AbstractTest {

    protected WebDriver driver;

    @BeforeTest
    @Parameters({"browser"})
    public void setDriver(String browser) throws MalformedURLException{

      if(Boolean.getBoolean("selenium.grid.enabled")) {
            this.driver = getRemoteDriver(browser);
      }else{
       this.driver = getLocalDriver();
        }
    }

    private WebDriver getRemoteDriver (String browser) throws MalformedURLException {
        //http://localhost:4444/wd/hub
        Capabilities capabilities;
        if(browser.equalsIgnoreCase("chrome")){
            capabilities= new ChromeOptions();
        }else{
            capabilities = new FirefoxOptions();
        }
        //Если тестировщики используют узлы Remote WebDriver, они должны использовать объекты RemoteWebDriver
        // и DesiredCapabilities для определения браузера, версии и платформы
        return  new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
    }

    private WebDriver getLocalDriver(){
     WebDriverManager.chromedriver().setup();
    return new ChromeDriver();

  }

    @AfterTest
    public void quitDriver(){
        this.driver.quit();
    }

}
