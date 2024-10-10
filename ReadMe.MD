**Assignment for Validating car details:**

Write a test automation suite which does following. 
1. Reads given input file: car_input.txt 
2. Extracts vehicle registration numbers based on pattern(s). 
3. Each number extracted from input file is fed to any car valuation website for e.g. webuyanycar 
(Perform vehicle details search car valuation page with random mileage details)
4. Compare the output returned by car valuation website with given car_output.txt
5. Highlight/fail the test for any mismatches. Showcase your skills so it’s easier to add more input files in future. 
6. Utilise any JVM based language with browser automation tools. Use design patterns where appropriate.

**Pre-requisite:**
1. Java
2. Maven

Note: For Java nad Maven, home path system variables should be set prior to run the test.

**Steps to run:**

#### **_Using IntelliJ Editor:_**

1. Clone the repository/download the repository.
2. Import it in IntelliJ editor.
3. Build the project
4. Open "src/main/java/com/tests/CarRegistrationChecksTest.java"
5. Run the test: "VehicleDetails"

#### **_Using CMD:_**
1. Clone the repository.
2. Open terminal and navigate to the repository root folder.
3. Run the commands: "mvn clean install"
4. mvn test

**Future Improvements:**
1. Tried to implement webDriverManager and get the chrome driver set up. The code is commented for now, as getting some error while compiling.
2. Also, Tried to add extent report for the test pack. Extent report folder gets generated but "html" file is not created. So, we can definitely improve on that one.