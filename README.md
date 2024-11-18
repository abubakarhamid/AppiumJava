
Pre-requisites:
  - Node Must be installed
  - Java 17 must be installed
  - Appium 2 Must be installed
  - JAVA_HOME environment variable should be set

Steps to execute script:
  - Right click on testng.xml file you want to execute
  - Wait for full execution
  - After execution is completed, open /reports/ExtentReport.html on Chrome browser to view results


Config Folder:
 - Consists of all the configuration of the project.

General:
 - This folder consists of generic classes to be used in over all project.
 - envGlobals consists of all the data.
 - basicFlows consists of swipe and scroll functions.
 - GenericFunctions is for random common functions. 
 - Main call is where we create objects for the classes and call them in test class.
 
 PageObject:
 - It consists of two folders Android and iOS.
 - Both have classes divided on the basis of screens. Like Login screen consists of locators and functions of Login Screen only.
 
 Testcases:
 - Test case class is same for android and iOS, if else case is used to distinguish.

 Utils:
 - consists of loghelper class, a generic clas for calling assertions and logging steps that are executed.
