Feature: Payment [ANDROID ONLY]

  Background:
  Given I need an Android phone

  @TEST_QRA-1222
  Scenario Outline: Check total price in valider button
    Given login with username or email "qa+sirenhy22@mektoube.fr" and password "mektoube"
    When click button by xpath "btnSaveFilter2"
    When wait until display button has text "Pass Mektoube"
    Then choose Pass <number> mois and check total price in valider button
    Examples:
      | number |
      | 1      |
      | 3      |
      | 6      |

  @TEST_QRA-1223
  Scenario: Payment with empty field
    Given login with username or email "qa+sirenhy22@mektoube.fr" and password "mektoube"
    When click button by xpath "btnSaveFilter2"
    When wait until display button has text "Pass Mektoube"
    Then choose Pass 3 mois and check total price in valider button
    When I click button has text "VALIDER"
    When wait until display button has text "Pass 3 mois"
    When wait until display button has text "Payer avec Google play"
    When I click button has text "PAYER 43,99€"
    Then See red message "Le numéro de carte est obligatoire" under field "placeholderNumberCart"
    Then See red message "La date est obligatoire" under field "placeholderDateExpiration"
    Then See red message "Le CVV est obligatoire" under field "placeholderCVV"
    Then See red message "Vous devez accepter les CGU" under field "conditionLink"

#  @TEST_QRA-1224
#  Scenario: Payment with card number and date invalid
#    Given login with username or email "qa+arthur97@mektoube.fr" and password "Mektoubi2017"
#    When click button by xpath "btnSaveFilter2"
#    When wait until display button has text "Pass Mektoube"
#    Then choose Pass 1 mois and check total price in valider button
#    When I click button has text "VALIDER"
#    When wait until display button has text "Pass 1 mois"
#    When wait until display button has text "Payer avec Google play"
#    When insert "4111111" into field "inputNumberCart"
#    When insert "20/04" into field "inputDateExpiration"
#    When insert "123" into field "inputCVV"
#    When click button by xpath "checkBoxCGU"
#    When I click button has text "PAYER 19,99€"
#    Then See red message "Le numéro de la carte est invalide" under field "placeholderNumberCart"
#    Then See red message "La date de la carte est invalide" under field "placeholderDateExpiration"

  @TEST_QRA-1225
  Scenario: Payment fail 3 times, check message in 4th time
    Given login with username or email "qa+sirenhy22@mektoube.fr" and password "mektoube"
    When click button by xpath "btnSaveFilter2"
    When wait until display button has text "Pass Mektoube"
    Then choose Pass 6 mois and check total price in valider button
    When I click button has text "VALIDER"
    When wait until display button has text "Pass 6 mois"
    When wait until display button has text "Payer avec Google play"
    When insert "5425233430109903" into field "inputNumberCart"
    When insert "12/04" into field "inputDateExpiration"
    When insert "123" into field "inputCVV"
    When click button by xpath "checkBoxCGU"
    When I click button has text "PAYER 65,99€"
    Then The page show message contain text "Echec du paiement"
    When I click button has text "PAYER 65,99€"
    Then The page show message contain text "Echec du paiement"
    When I click button has text "PAYER 65,99€"
    Then The page show message contain text "Echec du paiement"
    When I click button has text "PAYER 65,99€"
    Then The page show message contain text "Trop de tentatives de paiement en échec"

  @TEST_QRA-1226
  Scenario: Click links in payment page
    Given login with username or email "qa+sirenhy22@mektoube.fr" and password "mektoube"
    When click button by xpath "btnSaveFilter2"
    When wait until display button has text "Pass Mektoube"
    When I scroll up to "Conditions générales d’utilisations" text
    When I click button has text "Conditions générales d’utilisations"
    Then The page redirect "Conditions générales d’utilisations" screen
    When click button by xpath "backButtonOnTheLeftMektoubeLabel"
    When I click button has text "Politique de Confidentialité"
    Then The page redirect "Politique de Confidentialité" screen
    When click button by xpath "backButtonOnTheLeftMektoubeLabel"
    When I click button has text "VALIDER"
    When wait until display button has text "Pass 6 mois"
    When wait until display button has text "Payer avec Google play"
    When I click button has text "Conditions générales d’utilisations"
    Then The page redirect "Conditions générales d’utilisations" screen

#  @TEST_QRA-1237
#  Scenario: Belgium user cannot payment by paybox (card)
#    Given login with username or email "qa+arthur98@mektoube.fr" and password "Mektoubi2017"
#    When click button by xpath "btnSaveFilter2"
#    When wait until display button has text "Pass Mektoube"
#    Then choose Pass 6 mois and check total price in valider button
#    When I click button has text "VALIDER"
#    When wait about 2 seconds
#    Then wait until dont see this element "inputNumberCart" in screen

#  @TEST_QRA-1282
#  Scenario: Check all text in payment by paybox screen (normal)
#    Given login with username or email "qa+arthur97@mektoube.fr" and password "Mektoubi2017"
#    When click button by xpath "btnSaveFilter2"
#    When wait until display button has text "Pass Mektoube"
#    Then choose Pass 1 mois and check total price in valider button
#    When I click button has text "Paiement de"
#    Then Payment by paybox screen is show full

  @TEST_QRA-1297 #maintain done 18/03/2022
  Scenario: Unsubcription - enter wrong password
    Given Change environment to "stagingEnvironment"
    When login with username or email "dustin" and password "mektoube"
    When wait display button "myProfileBtn" and click
    When go to Setting tab and click button by text "Mon Compte"
    When I scroll up to "Mon abonnement" text
    When I click button has text "Mon abonnement"
    When I click button has text "Arrêter mon abonnement"
    When insert "abc" into field "passwordFieldInDesabonnementModal"
    When I click button has text "VALIDER"
    When Alert message with content "Mot de passe incorrect" is showed on top

  @TEST_QRA-1298 #maintain done 18/03/2022
  Scenario Outline: Unsubcription - check button or cancel unsubcription
    Given Change environment to "stagingEnvironment"
    When login with username or email "<username>" and password "mektoube"
    When wait display button "myProfileBtn" and click
    When go to Setting tab and click button by text "Mon Compte"
    When I scroll up to "Mon abonnement" text
    When I click button has text "Mon abonnement"
    Then Check button in Informations de votre abonnement modal and action
    Examples:
      | username    |
      | same       |
      | sevgilin    |
      | jawadorange |

#  @TEST_QRA-1283
#    # NOTE: MUST BE EDIT NAME EMAIL BEFORE RUN THIS TEST
#  Scenario: Payment by Google account
#    Given Change environment to "stagingEnvironment"
#    When Create account with email "cookie21@gmail.com" username "cookie21" pass "mektoube" success and go to discovery
##    When login with username or email "gary01" and password "mektoube"
#    When click button by xpath "btnSaveFilter2"
#    When wait until display button has text "Pass Mektoube"
#    Then choose Pass 6 mois and check total price in valider button
#    When I click button has text "VALIDER"
#    When wait about 1 seconds
#    When I click button has text "Payer avec Google play"
#    When wait about 2 seconds
#    Then Payment if google tester account is logined on PlayStore
#
#  @TEST_QRA-1236
#    # NOTE: MUST BE EDIT NAME EMAIL BEFORE RUN THIS TEST
#  Scenario: Payment by card success
#    Given Change environment to "stagingEnvironment"
#    When Create account with email "cookiee18@gmail.com" username "cookiee18" pass "mektoube" success and go to discovery
##    When login with username or email "gary003" and password "mektoube"
#    When click button by xpath "btnSaveFilter2"
#    When wait until display button has text "Pass Mektoube"
#    Then choose Pass 6 mois and check total price in valider button
#    When I click button has text "VALIDER"
#    When wait until display button has text "Payer avec Google play"
#    When insert "4263982640269299" into field "inputNumberCart"
#    When insert "02/23" into field "inputDateExpiration"
#    When insert "837" into field "inputCVV"
#    When click button by xpath "checkBoxCGU"
#    When I click button has text "PAYER 59,99€"
##    Then The page show message contain text "Paiement OK"
#    When wait about 15 seconds
#    Then I am on the discovery page
#
#  @TEST_QRA-1442
#    # NOTE: MUST BE EDIT NAME EMAIL BEFORE RUN THIS TEST
#  Scenario Outline: Payment by card success and can go to chat detail see messages
#    Given Change environment to "stagingEnvironment"
#    When Create account with email "<email>" username "<username>" pass "mektoube" success and go to discovery
#    When Logout current account and login account "sonia20" and password "mektoube"
##    When login with username or email "sonia20" and password "mektoube"
#    When Search user by pseudo "<username>" and go to conversation
#    When Send a text message "Hello boy"
#    When click button by xpath "backBtnInConversation"
#    When Logout current account and login account "<email>" and password "mektoube"
#    Then click button "messageIcon"
#    When wait about 2 seconds
#    When I click on the first message
#    And should see modal Payment
#    Then choose Pass 6 mois and check total price in valider button
#    When I click button has text "VALIDER"
#    When wait until display button has text "Payer avec Google play"
#    When insert "4263982640269299" into field "inputNumberCart"
#    When insert "02/23" into field "inputDateExpiration"
#    When insert "837" into field "inputCVV"
#    When click button by xpath "checkBoxCGU"
#    When I click button has text "PAYER 59,99€"
##    Then The page show message contain text "Paiement OK"
#    When wait about 5 seconds
#    Then I am on the discovery page
#    Then click button "messageIcon"
#    When wait about 2 seconds
#    When I click on the first message
#    Then conversation is displayed
#    Examples:
#      | email                | username   |
#      | cookieee18@mekmek.fr| cookieee18 |

