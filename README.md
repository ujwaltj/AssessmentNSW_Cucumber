
# Offical NSW Government Media Releases from different Ministers




## Documentation

Used Tools such as Selenium WebDriver, Cucumber, Gherkins to create a framework and Java as the programming language.

*HOW TO RUN THE PROJECT

Go to the TestRunner class 
E:\intelj\AssessmentNSW\src\test\java\runner\TestRunner.java
Right click and click on Run"TestRunner"

*STRUCTURE OF THE FRAMEWORK 

Under com.nsw.gov.framework package, we have the below classes.

The Base Class will call the WebDWebdriverInitializer class methods using webDriver to open the Chrome Browser and also Initalize the config.properties(contains URL, browser - key value pairs) and object.properties files(contains locators in a key value format).

The CommonFunction class launches the application URL using the ConfigData class which in turn uses the config.proerties object reference to fetch the link. 

PropertiesReader class to read the config.properites and object.properties files which are under the src/main/resounces.

Under com.nsw.gov.pages package, we have the below application page classes that identifies webelements and actions are performed on these elements using the methods.

Under src/main/resounces, we have chrome driver.exe and config.properites and object.properites files

Under src/test/java, we have the test class in the package "org.nsw.gov.stepdefs" which contains step definitions for the scenarios in the feature file.

Under src/test/resounces, we have the feature folder contains a feature file with 3 sceanarios 

*POM.xml contains all the dependencies related to cucumber, selenium, maven surefire plugin. 

the testng.xml is used for performing parallel exeuction of the scenarios in the feature file.





