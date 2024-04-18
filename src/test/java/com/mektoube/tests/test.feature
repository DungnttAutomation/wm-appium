Feature: Login function

  Background: Open the Home Page
#    Given Change environment to"preprodEnvironment"

  @TEST_QRA-2514
  Scenario: A user login with a valid username and valid password successfully
    When login with username or email "COM_INP_DATA_EMAIL_STAGE" and password "COM_INP_DATA_PASS_STAGE"
    And reset data test

