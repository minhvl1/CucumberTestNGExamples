@FeatureApi
Feature: Demo API

  @getMethodApi @dev_iphone @aut_kamenrider
  Scenario: test get api
    Given send get method with id="1"
    When show get response body
    Then Status code is "200"

  @postMethodApi @dev_ipad @aut_supersentai
  Scenario: test post api
    Given send post method with "users" module
    When show post response body
    Then Status code post is "201"

  @ApiDataDriven  @dev_iphone @aut_ironman @aut_spiderman
  Scenario: fakeresapi
    Given test datadriven fakeresapi
    Then test datadriven reqres