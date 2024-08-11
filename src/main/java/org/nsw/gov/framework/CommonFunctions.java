package org.nsw.gov.framework;

import org.nsw.gov.pages.MediaRelease;
import org.openqa.selenium.WebDriver;

public class CommonFunctions {

    private WebDriver driver;
    private PropertiesReader locatorPropReaderObj;


    public CommonFunctions(WebDriver driver, PropertiesReader proObject)
    {
        this.driver = driver;
        this.locatorPropReaderObj = proObject;
    }

    public MediaRelease launchSFApplication()
    {
        driver.get(ConfigData.sfURL);
        System.out.println("Launched Application");
        return new MediaRelease(driver, locatorPropReaderObj);
    }
}
