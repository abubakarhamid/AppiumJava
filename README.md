
# Project Documentation

## Pre-requisites

Before you can execute the scripts, make sure the following tools are installed and set up:

1. **Download the APK**:  
   - The `daraz.apk` file must be downloaded and placed in the `apps` folder. You can get the file from the [Google Drive link here](https://drive.google.com/file/d/1t0kDXCnnBZmCorNGr1_g6E6-77oSB9jW/view?usp=sharing).
   
2. **Install the Required Tools**:  
   - **Node.js** (Ensure Node.js is installed on your system)  
   - **Java 17** (Install the JDK version 17)
   - **Appium 2** (Make sure you have Appium 2 installed)
   
3. **Set Environment Variables**:  
   - The `JAVA_HOME` environment variable must be set to the Java 17 installation directory.

---

## Steps to Execute the Script

To run the test cases, follow these steps:

1. **Execute TestNG XML File**:  
   - Right-click on the `testng.xml` file you want to execute.

2. **Wait for Execution**:  
   - Wait for the script to complete its execution. 

3. **View Test Results**:  
   - Once execution is complete, open the file `/reports/ExtentReport.html` in your Chrome browser to view the results.

---

## Project Structure

### `Config` Folder
- Contains all configuration files for the project setup.

### `General` Folder
- This folder holds generic classes used throughout the project.
  - **envGlobals**: Contains all the environment data.
  - **basicFlows**: Includes functions for swipe and scroll operations.
  - **GenericFunctions**: Contains random common utility functions.
  - **Main**: The entry point where we create objects for the classes and invoke them in the test classes.

### `PageObject` Folder
- Contains two subfolders: **Android** and **iOS**, each of which includes classes specific to their respective platforms.
  - Each screen has its own class with locators and functions, e.g., a **Login screen** class with related locators and actions.

### `Testcases` Folder
- The test case classes are designed for both **Android** and **iOS**. Conditional statements (e.g., `if-else`) are used to handle platform-specific logic.

### `Utils` Folder
- Contains helper classes, including:
  - **LogHelper**: A class for logging the steps executed during the test.
  - **Assertion Helper**: A generic class to handle assertions during test execution.

---

## Additional Notes

- Make sure all necessary dependencies are installed and paths are correctly set up to ensure smooth execution.
- The results can be viewed in the **ExtentReport.html** file after test execution.
