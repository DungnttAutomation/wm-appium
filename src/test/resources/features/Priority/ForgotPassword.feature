Feature: Forgot password

  Background: Open the Home Page
#    Given I change environment to "productionEnvironment"

  @TEST_QRA-1387
  Scenario: Switching method forgot password suitable to login
    And reset data test
    When click button "seConnecterBtn"
    #check error when forgot pass by email
    And wait about 1 seconds
    When click button "forgotPasswordTextLink"
    Then Wait until display element by xpath "forgotPasswordByEmailTextField"
    When click button "envoyerLeCode"
    And show message error after validate with invalid data on "redError"
    When clear text and insert text "qa@mektoube" into field "forgotPasswordByEmailTextField"
    And show message error after validate with invalid data on "redError"
    When clear text and insert text "qamektoube.fr" into field "forgotPasswordByEmailTextField"
    And show message error after validate with invalid data on "redError"
    When clear text and insert text "qa @mektoube.fr" into field "forgotPasswordByEmailTextField"
    And show message error after validate with invalid data on "redError"
      #check error when forgot pass by phone
    And click button "redError"
    When click button "switchingForgotPassword"
    Then Wait until display element by xpath "forgotPWByPhoneNumberTitle"
    When click button "envoyerLeCode"
    And wait about 1 seconds
    And show message error after validate with invalid data on "redError"
    When clear text and insert text "74888037" into field "forgotPWByPhoneNumber"
    And show message error after validate with invalid data on "redError"
    And click button "redError"
    When scroll up to element "switchingForgotPassword"
    When click button "switchingForgotPassword"
    Then Wait until display element by xpath "forgotPWByEmailTitle"

  @TEST_QRA-1388
  Scenario Outline: Password forgotten page - enter wrong code many times check red message
    When click button "seConnecterBtn"
    And click button number <methodLogin> at button "phoneTab"
#    When click button by xpath "<methodLogin>"
    And wait about 1 seconds
    When click button "forgotPasswordTextLink"
    And change country code to "France" if forgot or login with phone number
    When clear text and insert text "<emailphone>" into field "<inputField>"
    When click button "envoyerLeCode"
#    When click button "envoyerLeCode"
#    When wait until display button has text "<textInCodeScreen>"
    Then Wait until display element by xpath "4thCodeBox"
    When Enter verify code "1234"
    Then should see alert message with content "<message>" is showed on top
    When clear text and insert text "5" into field "4thCodeBox" not hide key
    Then should see alert message with content "<message>" is showed on top
    When clear text and insert text "5" into field "4thCodeBox" not hide key
    Then should see alert message with content "<message>" is showed on top
    When clear text and insert text "5" into field "4thCodeBox" not hide key
    Then should see alert message with content "<lastmessage>" is showed on top
    Examples:
      | methodLogin  | inputField       | emailphone            | message                                                    | lastmessage                                                                           |textInCodeScreen|
      | 2 | forgotPWByPhoneNumber | 757052287             | Le code ajouté ne correspond pas à celui envoyé par SMS    | Trop de tentatives, vous ne pouvez pas ajouter de code supplémentaire pour le moment                 |Si vous êtes membre, vous recevrez immédiatement par SMS le code afin de choisir un nouveau mot de passe au (+33) 6 44 62 88 62 (Modifier)|
      | 1 | forgotPasswordByEmailTextField    | qa+lisa98@mektoube.fr | Le code ajouté ne correspond pas à celui envoyé par E-MAIL | Trop de tentatives, vous ne pouvez pas ajouter de code supplémentaire pour le moment |Si vous êtes membre, vous recevrez immédiatement par email le code afin de choisir un nouveau mot de passe à l’adresse qa+lisa98@mektoube.fr (Modifier)|
    @TEST_QRA-2077
    Scenario Outline: Forgot password - back to modify email/phone number more than 3 times, show red message
      When click button "seConnecterBtn"
      And click button number <methodLogin> at button "phoneTab"
      And wait about 1 seconds
      When click button "forgotPasswordTextLink"
      And change country code to "France" if forgot or login with phone number
      When clear text and insert text "<first data>" into field "<inputField>"
      When click button "envoyerLeCode"
#      When click button "envoyerLeCode"
#      When wait until display button has text "<textInCodeScreen>"
      And wait until display "4thCodeBox"
      When click button by xpath "backBtn"
      When clear text and insert text "<second data>" into field "<inputField>"
      When click button "envoyerLeCode"
#      When click button "envoyerLeCode"
      And wait until display "4thCodeBox"
      When click button by xpath "backBtn"
      When clear text and insert text "<third data>" into field "<inputField>"
      When click button "envoyerLeCode"
#      When click button "envoyerLeCode"
      And wait until display "4thCodeBox"
      When click button by xpath "backBtn"
      When clear text and insert text "<last data>" into field "<inputField>"
      When click button "envoyerLeCode"
      When click button "envoyerLeCode"
      Then should see alert message with content "Vous ne pouvez pas changer de numéro plus de 3 fois" is showed on top

      Examples:
        | methodLogin  | inputField       | first data             | second data            | third data             | last data              |textInCodeScreen|
        | 1 | forgotPasswordByEmailTextField    | qa+lisa98@mektoube.fr | qa+lucas30@mektoube.fr | qa+lucas31@mektoube.fr | qa+lucas32@mektoube.fr |Si vous êtes membre, vous recevrez immédiatement par email le code afin de choisir un nouveau mot de passe à l’adresse qa+lisa98@mektoube.fr (Modifier)|
        | 2 | forgotPWByPhoneNumber | 756484447              | 756484448              | 756484449              | 756484441              |Si vous êtes membre, vous recevrez immédiatement par SMS le code afin de choisir un nouveau mot de passe au (+33) 6 44 23 45 67 (Modifier)|