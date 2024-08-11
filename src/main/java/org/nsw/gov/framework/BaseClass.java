package org.nsw.gov.framework;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseClass {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public CommonFunctions commonMethods;
    public static PropertiesReader configpropReaderObj;
    public static PropertiesReader locatorpropReaderObj;


    static
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        System.setProperty("current.date.time", dateFormat.format(new Date()));
    }


    @Before
    public void setup() throws Exception
    {

        try
        {
            configpropReaderObj = new PropertiesReader(new File("./src/main/resources/config.properties"));
            locatorpropReaderObj = new PropertiesReader(new File("./src/main/resources/objects.properties"));
            WebdriverInitializer webinit = new WebdriverInitializer(configpropReaderObj.getData("chromeBrowser"));
            driver.set(webinit.getWebDriver());
            // driver = webinit.getWebDriver();
            commonMethods = new CommonFunctions(driver.get(), locatorpropReaderObj);
        }
        catch (Throwable e)
        {

            e.printStackTrace();
        }
        String className = this.getClass().getSimpleName();
        String browser = configpropReaderObj.getData("chromeBrowser");

    }
    public WebDriver getDriver(){
        return driver.get();
    }



    @After
    public void tearDown(){
        //WebdriverInitializer.quitDriver();
        WebDriver driverInstance = driver.get();
        if (driverInstance != null) {
            driverInstance.quit();
            driver.remove();  // Ensure ThreadLocal is cleared
        }
    }


//    @BeforeTest
//    public void beforeTest()
//    {
//        System.out.println("BASE CLASS BEFORE_TEST");
//        configpropReaderObj = new PropertiesReader(new File("./src/main/resources/config.properties"));
//        locatorpropReaderObj = new PropertiesReader(new File("./src/main/resources/objects.properties"));
//    }

}
