package org.nsw.gov.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.nsw.gov.framework.PropertiesReader;

public class NewPage {
	 WebDriver driver;
	PropertiesReader propObject;

	public NewPage(WebDriver driver, PropertiesReader propObject) {
		this.driver = driver;
		this.propObject = propObject;
	}

	public boolean validateTheMinisterNameOnArticleMatchesWithFilterApplied(String ministerName)
			throws InterruptedException {
		// Select the checkbox for the specified minister
		boolean flag = false;
		System.out.println("to read the Release BY");
		// WebElement ministerNameInArticle = driver.findElement(By.cssSelector("p.node-content__element.node-content__element--meta:nth-of-type(2)"));
		WebElement ministerNameInArticle = driver.findElement(By.cssSelector(propObject.getData("nswFetchTheMinisterNamesFromReleaseBy")));
		String ministerNameRawValuesOnTheArticle = ministerNameInArticle.getText();
		System.out.println(ministerNameRawValuesOnTheArticle);
		String ministerNameOnTheArticle = ministerNameRawValuesOnTheArticle.replaceAll("[:,]", "");
		String[] fetchMinisterName = ministerNameOnTheArticle.split(" ");
		System.out.println(driver);
		for (String iterateMinisterName : fetchMinisterName) {
			if (iterateMinisterName.equals(ministerName)) {
				System.out.println("The Minsiters name is "+iterateMinisterName);
				flag = true;
				break;
			}		
		}
		return flag;
	}
}
