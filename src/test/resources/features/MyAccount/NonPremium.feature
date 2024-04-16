Feature: Account non-premium (male users)
  I want check permission of account non-premium

  Background: Open the Home Page
#    Given I change environment to "preprodEnvironment"

  @TEST_QRA-1156
  Scenario: Account non-premium - Can't view full photo
    Given I login with account "qa+kenzo15@mektoube.fr" and "mektoube" and "allow" permission
    And I click profile has photo
    Then I click photo of profile "photoProfile"
    And should see pop up premium
    Then click button "J'YVAIS"
    And should see modal Payment

  @TEST_QRA-1157
  Scenario: Account non-premium - Can't cross partner
    Given I login with account "qa+kenzo15@mektoube.fr" and "mektoube" and "allow" permission
    When I click on see profile user
    When I check invitation for woman account
    And should see modal Payment

  @TEST_QRA-1158
  Scenario Outline: Account non-premium - Can't see who visit/smile
    Given I login with account "qa+kenzo15@mektoube.fr" and "mektoube" and "allow" permission
    Then click button "notificationScreen"
    When I click on the "filterIcon"
    When I click "<OptionFilter>" button
    Then The notifications page shows the "<icon>" of the filter
    And should see modal Payment
    Examples:
      | OptionFilter          | icon            |
      | Smiles                | smileIcon       |
      | Visites               | visitesIcon     |

  @TEST_QRA-1183
  Scenario: Account non-premium - Can't click conversation
    Given I login with account "qa+kenzo15@mektoube.fr" and "mektoube" and "allow" permission
    Then click button "messageIcon"
    When I click on the first message
    And should see modal Payment

  @TEST_QRA-1184
  Scenario: Account non-premium - Can't search advance
    Given I login with account "qa+kenzo15@mektoube.fr" and "mektoube" and "allow" permission
    Then wait display button "searchBtn" and click
    When I scroll up to "Critères avancés" text
    When click button "btnAdvanced"
    And should see pop up premium
    Then click button "J'YVAIS"
    And should see modal Payment

  @TEST_QRA-1185
  Scenario: Account non-premium - Visit more than X profiles per day
    Given I login with account "qa+kenzo15@mektoube.fr" and "mektoube" and "allow" permission
    When I click on see profile user
    Then click button "btnNextProfile" and view profile
    And should see pop up premium
    Then click button "J'YVAIS"
    And should see modal Payment

#    Change staging to test
  @TEST_QRA-1186
  Scenario: Account non-premium - Smiler more than X profiles per day
#    Given Change environment to "stagingEnvironment"
    When I login with account "qa+kenzo91@mektoube.fr" and "mektoube" and "allow" permission
    When I click on see profile user
    And click button "btnSmile" then click button "btnNextProfile"
    And should see pop up premium
    Then click button "J'YVAIS"
    And should see modal Payment

  @TEST_QRA-1202
  Scenario: Account non-premium - Can't save search filter
    And  I login with account "qa+kenzo91@mektoube.fr" and "mektoube" and "allow" permission
    When click button "btnSaveFilter2"
    And should see modal Payment

  @TEST_QRA-1203
  Scenario Outline: Account non-premium - Can't favoris/bloquer partner
#    Given Change environment to "stagingEnvironment"
    And  I login with account "qa+kenzo15@mektoube.fr" and "mektoube" and "allow" permission
    When I click on see profile user
    And wait about 3 seconds
    Then click button "threeDotIcon"
    And click button "<Option>"
    And should see modal Payment
    Examples:
      | Option  |
      | favoris |
      | bloquer |

  @TEST_QRA-1204
  Scenario: Account non-premium - "Boost profile" for men who don't have a subscription
    And  I login with account "qa+kenzo15@mektoube.fr" and "mektoube" and "allow" permission
    Then I scroll up to "attendez" text
    And check banners to subscribe is display in Discovery Page
    Then should see modal Payment

  @TEST_QRA-1340
  Scenario: Account non-premium - Check banners to subscribe is display
    And  I login with account "qa+kenzo15@mektoube.fr" and "mektoube" and "allow" permission
    Then click button "notificationScreen"
    And check banners to subscribe is display in Notif
    Then should see modal Payment

  @TEST_QRA-1341
  Scenario: Account non-premium - Check show modal when click Mon abonnement
    And  I login with account "qa+kenzo15@mektoube.fr" and "mektoube" and "allow" permission
    Then click button "myProfileBtn"
    And go to Setting tab and click button by text "Mon Compte"
#    When I click button has text "Mon abonnement"
    When the user scroll down and click "Mon abonnement"
    Then should see modal Payment

  @TESt_QRA-1441
  Scenario: Account non-premium - Check can't show full name partner
    And  I login with account "qa+kenzo15@mektoube.fr" and "mektoube" and "allow" permission
    Then click button "notificationScreen"
    And check name of a partner is not displayed in full
    And click button "nameProfile"
    Then should see modal Payment

  @TEST_QRA-1454
  Scenario: Account non-premium - Don't show button block when report in profile partner
    And  I login with account "qa+kenzo15@mektoube.fr" and "mektoube" and "allow" permission
    Then click on first profile in discovery
    And click button by xpath "threeDotIcon"
    Then click button by xpath "btnReportProfile"
    And check button Block not show


