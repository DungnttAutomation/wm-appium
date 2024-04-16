Feature: Payment via a promotion

  Background:
    Given I need an Android phone

  @TEST_QRA-1250
  Scenario: Don't allow user payment by Google via a promotion
    Given login with username or email "qa+arthur97@mektoube.fr" and password "Mektoubi2017"
    When wait display button "messageIcon" and click
    When Scroll down to refresh page
    When wait until display button has text "Equipe Mektoube"
    When I click button has text "Equipe Mektoube"
    When wait until display button has text "EN PROFITE - CLIQUEZ-ICI"
    When I click button has text "EN PROFITE - CLIQUEZ-ICI"
    When wait until display button has text "OFFRE SP"
    When I click button has text "PROFITE"
    When wait about 2 seconds
    Then wait until dont see this element "payByGoogleBtn" in screen

  @TEST_QRA-1251
  Scenario: Check all text in payment by paybox screen (payment via a promotion)
    Given login with username or email "qa+arthur97@mektoube.fr" and password "Mektoubi2017"
    When wait display button "messageIcon" and click
    When Scroll down to refresh page
    When wait until display button has text "Equipe Mektoube"
    When I click button has text "Equipe Mektoube"
    When wait until display button has text "EN PROFITE - CLIQUEZ-ICI"
    When I click button has text "EN PROFITE - CLIQUEZ-ICI"
    When wait until display button has text "OFFRE SP"
    When I click button has text "PROFITE"
    When Payment by paybox screen is show full

  @TEST_QRA-1284
  Scenario: Payment with empty field (payment via a promotion)
    Given login with username or email "qa+arthur97@mektoube.fr" and password "Mektoubi2017"
    When wait display button "messageIcon" and click
    When Scroll down to refresh page
    When wait until display button has text "Equipe Mektoube"
    When I click button has text "Equipe Mektoube"
    When wait until display button has text "EN PROFITE - CLIQUEZ-ICI"
    When I click button has text "EN PROFITE - CLIQUEZ-ICI"
    When wait until display button has text "OFFRE SP"
    When I click button has text "PROFITE"
    When wait about 2 seconds
    When I click button has text "Paiement de 9,90€"
    Then See red message "Le numéro de carte est obligatoire" under field "placeholderNumberCart"
    Then See red message "La date est obligatoire" under field "placeholderDateExpiration"
    Then See red message "Le CVV est obligatoire" under field "placeholderCVV"

  @TEST_QRA-1285
  Scenario: Payment with card number and date invalid (payment via a promotion)
    Given login with username or email "qa+arthur97@mektoube.fr" and password "Mektoubi2017"
    When wait display button "messageIcon" and click
    When Scroll down to refresh page
    When wait until display button has text "Equipe Mektoube"
    When I click button has text "Equipe Mektoube"
    When wait until display button has text "EN PROFITE - CLIQUEZ-ICI"
    When I click button has text "EN PROFITE - CLIQUEZ-ICI"
    When wait until display button has text "OFFRE SP"
    When I click button has text "PROFITE"
    When wait about 2 seconds
    When insert "4111 111" into field "inputNumberCart"
    When insert "20/04" into field "inputDateExpiration"
    When insert "123" into field "inputCVV"
    When I click button has text "Paiement de 9,90€"
    Then See red message "Le numéro de la carte est invalide" under field "placeholderNumberCart"
    Then See red message "La date de la carte est invalide" under field "placeholderDateExpiration"

  @TEST_QRA-1286
  Scenario: Payment fail 3 times, check message in 4th time (payment via a promotion)
    Given login with username or email "qa+arthur98@mektoube.fr" and password "Mektoubi2017"
    When wait display button "messageIcon" and click
    When Scroll down to refresh page
    When wait until display button has text "Equipe Mektoube"
    When I click button has text "Equipe Mektoube"
    When wait until display button has text "EN PROFITE - CLIQUEZ-ICI"
    When I click button has text "EN PROFITE - CLIQUEZ-ICI"
    When wait until display button has text "OFFRE SP"
    When I click button has text "PROFITE"
    When wait about 2 seconds
    When insert "1111 2222 3333 4444" into field "inputNumberCart"
    When insert "12/04" into field "inputDateExpiration"
    When insert "123" into field "inputCVV"
    When I click button has text "Paiement de 9,90€"
    Then The page show message contain text "Echec du paiement"
    When I click button has text "Paiement de 9,90€"
    Then The page show message contain text "Echec du paiement"
    When I click button has text "Paiement de 9,90€"
    Then The page show message contain text "Echec du paiement"
    When I click button has text "Paiement de 9,90€"
    Then The page show message contain text "Trop de tentatives de paiement en échec"

  @TEST_QRA-1287
  Scenario: Belgium user can payment by paybox (only when payment via a promotion)
    Given login with username or email "qa+arthur98@mektoube.fr" and password "Mektoubi2017"
    When wait display button "messageIcon" and click
    When Scroll down to refresh page
    When wait until display button has text "Equipe Mektoube"
    When I click button has text "Equipe Mektoube"
    When wait until display button has text "EN PROFITE - CLIQUEZ-ICI"
    When I click button has text "EN PROFITE - CLIQUEZ-ICI"
    When wait until display button has text "OFFRE SP"
    When I click button has text "PROFITE"
    Then Payment by paybox screen is show full
    When insert "1111 2222 3333 4444" into field "inputNumberCart"
    When insert "12/04" into field "inputDateExpiration"
    When insert "123" into field "inputCVV"
    When I click button has text "Paiement de 9,90€"
    Then The page show message contain text "paiement"
