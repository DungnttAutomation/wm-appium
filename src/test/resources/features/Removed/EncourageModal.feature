Feature: Encourage modal

  @QRA_Test-2011
  Scenario: Login account don't validate email - close modal validate email and close modal encourage.
    And I click button has text "SE CONNECTER"
    When I fill "qa+sirenhy1@mektoube.fr" and "mektoube"
    When Click login button
    And wait about 3 seconds
    When active location permission after login success
#close modal validate email
    When close modal request validate email
#=> show modal encourage -> close modal
    When wait display button "messageIcon" and click
    And wait until "//*[contains(@text,'Messages')]" is invisiable
    When wait display button "discoveryPage" and click
    And wait about 1 seconds
    When close modal encourage user
#log out and login again + check show modal
    When I logout account
    When I click button has text "SE CONNECTER"
    When Click login button
    And wait about 3 seconds
    Then show modal encourage user

  @QRA_Test-2012
  Scenario: Login account don't validate email - close modal validate email and click fill modal encourage.
    And I click button has text "SE CONNECTER"
    When I fill "qa+sirenhy1@mektoube.fr" and "mektoube"
    When Click login button
    And wait about 2 seconds
    When active location permission after login success
#close modal validate email
    When close modal request validate email
#=> show modal encourage -> fill modal
    When wait display button "messageIcon" and click
    And wait until "//*[contains(@text,'Messages')]" is invisiable
    When wait display button "discoveryPage" and click
    And wait about 1 seconds
    When click fill modal encourage and close
#log out and login again + check show modal
    When I logout account
    When I click button has text "SE CONNECTER"
    When Click login button
    And wait about 3 seconds
    Then show modal encourage user

  @QRA_Test-2013
  Scenario: Login account don't validate email - click change email and close modal encourage.
    And I click button has text "SE CONNECTER"
    When I fill "qa+sirenhy1@mektoube.fr" and "mektoube"
    When Click login button
    And wait about 2 seconds
    When active location permission after login success
#click change at modal validate email
    When click change email at modal validate email
#=> show modal encourage -> close modal
    When close modal encourage user
#log out and login again + check show modal
    When I logout account
    And I click button has text "SE CONNECTER"
    When Click login button
    And wait about 2 seconds
    Then show modal encourage user

  @QRA_Test-2014
  Scenario: Login account don't validate email - click change email and click fill modal encourage.
    And I click button has text "SE CONNECTER"
    When I fill "qa+sirenhy1@mektoube.fr" and "mektoube"
    When Click login button
    And wait about 2 seconds
    When active location permission after login success
#click change at modal validate email
    When click change email at modal validate email
#=> show modal encourage -> film modal
    When click fill modal encourage and close
#log out and login again + check show modal
    When I logout account
    When I click button has text "SE CONNECTER"
    When Click login button
    And wait about 3 seconds
    Then show modal encourage user

  @QRA_Test-2015
  Scenario: Login account validate email and has 1 child - click fill modal encourage.
    And I click button has text "SE CONNECTER"
    When I fill "qa+phuongw@mektoube.fr" and "mektoube"
    When Click login button
    And wait about 2 seconds
    When active location permission after login success
#=> show modal encourage -> fill modal
    When I click button has text "REMPLIR"
# show screen 1 child at modal encourage
    Then show screen select enfant