# Framework example
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

![image](https://user-images.githubusercontent.com/67543695/227717091-555914e8-c23e-45ba-9eb7-7d55f5542dce.png)

![image](https://user-images.githubusercontent.com/67543695/227717115-fc0103b7-09b7-4463-b398-e4b606b580de.png)

3. Allure Report

![image](https://user-images.githubusercontent.com/67543695/227717165-5cfea855-edc3-4e42-8d2e-b655f8a851e6.png)

![image](https://user-images.githubusercontent.com/67543695/227717182-e07b41ee-717d-4034-af3c-96f68ad7b83d.png)

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

### ALLURE-DOCKER-SERVICE

1 `docker compose up -d`

2 `mvn clean verify`

![image](https://github.com/minhvl1/CucumberTestNGExamples/assets/67543695/78f3c3f3-e3da-4076-b9e4-25f19be7a752)

![image](https://github.com/minhvl1/CucumberTestNGExamples/assets/67543695/b5d14b99-caa1-41c4-92b0-1b589dee944b)

![image](https://github.com/minhvl1/CucumberTestNGExamples/assets/67543695/a60ad159-9cb7-43ed-b24e-6de5cb830f89)
