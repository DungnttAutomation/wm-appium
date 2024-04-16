Feature: Login function

  Background: Open the Home Page
#    Given Change environment to"preprodEnvironment"

  @TEST_QRA-1390
  Scenario: Redirect Contact Us page when click "Nous contacter"
    And reset data test
    When open url
    And wait about 5 seconds
    When click button "seConnecterBtn"
#    Then wait display button "nousContacter" and click
    And click button by action "nousContacter"
    And Wait until display element by xpath "Envoyer"

  @TEST_QRA-1056
  Scenario Outline: Verify the messages for each blank field.
    When click button "seConnecterBtn"
    When insert "<UserName>" into field "emailInput"
    When insert "<Password>" into field "passwordInput"
    When click button "spaceBtn"
    When click button "loginButton"
    Then I check error messages
    When click button by xpath "backBtn"
    Examples:
      | UserName                    | Password     |
      |                             | Mektoubi2017 |
      | qa+brandon92500@mektoube.fr |              |
      |                             |              |

  @TEST_QRA-1068
  Scenario Outline: Verify if a user cannot log in with an invalid username or password
    When click button "seConnecterBtn"
    When insert "<UserName>" into field "emailInput"
    When insert "<Password>" into field "passwordInput"
    When click button "spaceBtn"
    When click button "loginButton"
    Then Messages error is display "errorMsg"
    When click button by xpath "backBtn"
    Examples:
      | UserName              | Password |
      | qa+ryan10@mektoube.fr | Mektoubi |
      | qa+ryan@mektoube.fr   | mektoube |

#  @TEST_QRA-1391
#  Scenario: Redirect Registration Page when click "Inscrivez-vous gratuitement"
#    When click button "seConnecterBtn"
#    Then I click button has text "Inscrivez-vous gratuitement"
#    And wait until display button has text "Homme"

#    @TEST_QRA-
#    Scenario: Login with phone number
#      Given login with phone nb "0757132146" of country "Fr" and password "mektoube"

  @TEST_QRA-2125
  Scenario: Login with invalid phone number(empty/invalid/don't in DB)
    When click button "seConnecterBtn"
    And click button number 2 at button "phoneTab"
    When insert "" into field "phoneInput"
    When insert "mektoube" into field "passwordInput"
    When click button "spaceBtn"
    When click button "loginButton"
    Then show error message "Veuillez saisir votre numéro de téléphone." of element "errorMsg" when enter invalid phone number
    When insert "2302" into field "phoneInput"
    When click button "spaceBtn"
    When click button "loginButton"
    Then show error message "Veuillez saisir un numéro de téléphone valide." of element "errorMsg" when enter invalid phone number
    And change country code to "France" if forgot or login with phone number
    When insert "378037770" into field "phoneInput"
    When click button "spaceBtn"
    When click button "loginButton"
    Then show error message "Identifiant ou mot de passe incorrect" of element "errorMsg" when enter invalid phone number

  @TEST_QRA-2529
  Scenario Outline: Verify if a user can log in with a valid phone and password and allow location permission.
    And reset data test
    When click button "seConnecterBtn"
    And click button number 2 at button "phoneTab"
    And change country code to "France" if forgot or login with phone number
#    When search "France" of element "textBox" and choose country code
    When insert "<Phone>" into field "phoneInput"
    When insert "<Password>" into field "passwordInput"
    When click button "spaceBtn"
    When click button "loginButton"
#    And active location permission after login success
#    Then the user logout successfully
    And wait about 3 seconds
    When the user go to "Se déconnecter" screen
    Examples:
      | Phone              | Password |
#      | 757130276          | mektoube |
      | 644621323          | mektoube |

#  @TEST-QRA_1246
#  Scenario: Login success and allow location  permission
#    And wait about 4 seconds
#    When click button "seConnecterBtn"
#    When insert "qa+anie@mektoube.fr" into field "emailInput"
#    When insert "mektoube" into field "passwordInput"
#    When click button "spaceBtn"
#    When click button "loginButton"
##    And wait about 3 seconds
#    And active location permission after login success
#    Then the user logout successfully



