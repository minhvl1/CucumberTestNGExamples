# GB-Demo
### V1.2
1. Migrate log4j
2. Custom log level

### V1.1
1. Add multiple configure file for multiple environment (default: testing env)
2. Add Send message to slack utils

### V1.0
### SOME FEATURES IN FRAMEWORK

1. Run the parallel Scenario on feature file
2. Cucumber Report
3. Extent Report
4. Spark Report
5. Allure Report
6. TestNG Report
7. Send Mail after the run test (Report information and HTML file attachment)
8. Logging(to console and file), custom log level
9. Screenshot test case
10. Data driven/Dataprovider
11. Multiple profile for each enviroment 

### HOW TO USE
1. Default run:

`mvn clean verrify`

2. Run with profile (run-demo-api):

`mvn clean install -P run-demo-api`

Run by tags:

`mvn verify -D"cucumber.filter.tags=@debug1 or @debug2"`

### FEATURES
1. Config in config.properties(src/test/resources/config/config.properties)

![image](https://user-images.githubusercontent.com/67543695/220126487-781ba264-760d-4216-a69b-d2bba655d443.png)

2. Extent Report

![image](https://user-images.githubusercontent.com/67543695/218368035-87683f26-cbf7-404e-8912-abbb8ef5c410.png)

![image](https://user-images.githubusercontent.com/67543695/227716888-da392d37-c7d0-49b8-8bf0-e743ea68a4c8.png)

3. Allure Report

![image](https://user-images.githubusercontent.com/67543695/227716869-67d3a2d9-7332-4504-b3a0-04b4669086e9.png)

4. Cucumber Report

![image](https://user-images.githubusercontent.com/67543695/220126109-3ad6ea4d-bae4-4a91-be95-43fb73cd1fd5.png)

5. Pdf Report

![image](https://user-images.githubusercontent.com/67543695/218368288-8010dba5-53ff-4d80-a84b-2017cab19de7.png)

6. TestNG Reprt

![image](https://user-images.githubusercontent.com/67543695/227717037-61cea885-d58b-4642-afd5-cbc053a0a957.png)

7. Logging

![image](https://user-images.githubusercontent.com/67543695/227716811-40c9f251-1c47-43c3-9a1a-a188e2a4e03a.png)

![image](https://user-images.githubusercontent.com/67543695/227716832-19a0579b-3888-4b8a-85fd-2e20e332fd8a.png)

8. Send message to Slack

![image](https://user-images.githubusercontent.com/107853696/218916459-41388bca-49d0-438f-8b4c-d1c48c883f69.png)

9. Send message to Microsoft Teams

![image](https://user-images.githubusercontent.com/67543695/220000344-2344aca0-ee10-4c8e-be50-2c17eac04011.png)

