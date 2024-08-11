package org.nsw.gov.pages;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.nsw.gov.framework.PropertiesReader;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
public class MediaRelease {
	 WebDriver driver;
	PropertiesReader propObject;
//	PropertiesReader locatorPropReaderObj;

	public MediaRelease(WebDriver driver, PropertiesReader propObject) 
	{
		this.driver = driver;
		this.propObject = propObject;
	}

	public void clickOnMinistersAccordian() {
		// Expand the "Ministers" accordion
		WebElement ministersAccordion = driver.findElement(By.xpath(propObject.getData("nswMinisters")));
		if (!ministersAccordion.getAttribute("aria-expanded").equals("true")) {
			ministersAccordion.click();
		}
	}
	
	public void clickOnMinisterCheckboxes(String ministerNames) throws InterruptedException {
		// Select the checkbox for the specified minister

        WebElement ministerCheckbox = driver.findElement(By.xpath("//label[text()='"+ministerNames+"']/preceding-sibling::input"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", ministerCheckbox);
        Thread.sleep(2000);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", ministerCheckbox);

	}
	
	public void clickOnApplyFilter() throws InterruptedException {
		 // Click the "Apply filters" button
        WebElement applyFiltersButton = driver.findElement(By.xpath(propObject.getData("nswApplyFilter")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", applyFiltersButton);
        Thread.sleep(2000);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", applyFiltersButton);
       //wait for the page to load
        Thread.sleep(2000); 
        
	}
	public void clickOnClearAllFilterButton() throws InterruptedException {
		 // Click the "Clear all filters" button
		Thread.sleep(4000);
       WebElement clearALLFiltersButton = driver.findElement(By.xpath(propObject.getData("nswClearAllFiltersButton")));
       ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", clearALLFiltersButton);
       Thread.sleep(2000);
       //clearALLFiltersButton.click();
       JavascriptExecutor executor = (JavascriptExecutor)driver;
       executor.executeScript("arguments[0].click();", clearALLFiltersButton);
       //wait for the page to load
       Thread.sleep(4000);
	}
	
	public NewPage openTheArticleForCorrespondingMinister() throws InterruptedException {
		List<WebElement> clickOntheFirstArticle = driver.findElements(By.xpath(propObject.getData("nswFirstLinkMediaReleaseItemCards")));
		clickOntheFirstArticle.get(0).click();
		Thread.sleep(3000);
		return new NewPage(driver, propObject);
		
	}
	
	public void scrollToTotalPagesOnMediaRelease() throws InterruptedException {
		WebElement totalPages = driver.findElement(By.xpath(propObject.getData("nswTotalPages")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", totalPages);
	    Thread.sleep(2000);
	}
	public String totalPagesOnMediaRelease() {
		
		WebElement totalPages = driver.findElement(By.xpath(propObject.getData("nswTotalPages")));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.visibilityOf(totalPages));
		String pages = "";
		pages = totalPages.getText();
		String[] totalNumberOfPagesByDefault= pages.split(" "); //Showing 1 - 10 of 1999 results
		return totalNumberOfPagesByDefault[5];
	}
}
