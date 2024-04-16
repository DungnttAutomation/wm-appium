Feature: Registration Step
  Background: Open registration page
#    Given Change environment to"preprodEnvironment"
#    When the user go to "Se déconnecter" screen
    And reset data test
    Given click button "regisBtn"
    When click button "male"
    And click button "submitBtn"
    When wait about 2 seconds
    And click button "submitBtn"
#    Then Enter a valid date of birth and validate
#    When select first option after search "France" at field "textField"
    When wait about 1 seconds
    When click button "firstOptionOrigin"
    When click button "submitBtn"
  @TEST_QRA-1101
  Scenario: Geolocation Step  Do not click "Ma localisation" label and give apps permission to use your location
    When wait about 1 seconds
    And click button "geoLocation"
#    And Active "allow" permission
    Then I select ville option 1 and submit "submitBtn"
    When clear text and insert text "mektoutou12" into field "nameLabel"
    Then click button "submitBtn"
    And click signup without CGU

  @TEST_QRA-1103
  Scenario: Registration Step - Zipcode Screen - Leave the postal code field empty - Enter a invalid postal code - Enter a single digit in Code postal filed - Enter a valid postal code
    Then click button "myLocation"
    And click country "DE" at "listOption"
#    And scroll up to "Allemagne" text at "listOption"
#    And select "Allemagne" at pays screen
    When click button "submitBtn"
    When clear text and insert text "12345" into field "postalCodeTextInput"
    When click button "submitBtn"
    Then should see alert message with content "Ce code postal n'existe pas." is showed on top

    When clear text and insert text "1" into field "postalCodeTextInput"
    When click button "submitBtn"
    Then should see alert message with content "Code postal non validé" is showed on top

    When clear text and insert text "10117" into field "postalCodeTextInput"
    When click button "submitBtn"
    And  I select ville option 1 and submit "submitBtn"
    When validate with invalid pseudo and valid pseudo
    And click signup without CGU