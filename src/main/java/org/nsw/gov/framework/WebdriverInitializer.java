package org.nsw.gov.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.net.MalformedURLException;

public class WebdriverInitializer {
    private String browser;
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();
    public WebdriverInitializer(String browser)
    {
        this.browser = browser;
    }

    protected WebDriver getWebDriver() throws MalformedURLException
    {
        return webdriverInitializer(this.browser);
    }

   //protected static WebDriver webDriver=null;

    private WebDriver webdriverInitializer(String type) throws MalformedURLException
    {
        switch(type)
        {
            case "Chrome":
                File chromeFile = new File("./src/main/resources/drivers/chromedriver.exe");
                System.setProperty("webdriver.chrome.driver", chromeFile.getAbsolutePath());
//                ChromeOptions options = new ChromeOptions();
//                options.addArguments("--no-sandbox");
//                options.addArguments("--disable-dev-shm-usage");
//                options.addArguments("--disable-gpu");
//                options.addArguments("--headless"); // If you can run the tests without a GUI
                //driver = new ChromeDriver(options);
                webDriver.set(new ChromeDriver());
                System.out.println(webDriver);
                System.out.println(" CHROME HAS BEEN OPENED");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


                break;

            case "Firefox":
                File FireFoxFile = new File("./src/main/resources/driver/geckodriver_64.exe");
                System.setProperty("webdriver.gecko.driver", FireFoxFile.getAbsolutePath());
                webDriver.set(new FirefoxDriver());
                System.out.println(webDriver);
                break;

            default:
                break;
        }
        WebDriver driver = webDriver.get();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();

        return driver;
    }

    public static void quitDriver() {
        if (webDriver.get() != null) {
            webDriver.get().quit();
            webDriver.remove(); // Ensure the thread-local reference is removed
        }
    }
}
