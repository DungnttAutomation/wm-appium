Feature: Smile/Visit/Report
  Background: Login account
    Given Clear stack
#    And I login with account "sofia9563" and "mektoube" and "deny" permission
  @QRA-1143
  Scenario: Smile
    And I login with account "qa+ngoctrinh89@mektoube.fr" and "mektoube" and "allow" permission
    When I filter account with name "Sirenhy96"
    And I click on the user profile has name "Sirenhy96"
    When click button "btnSmile"
    Then Alert message with content "Smile envoyé" is showed on top
    When wait about 3 seconds
    When click button "btnSmile"
    Then Alert message with content "Personne déjà smilée" is showed on top
    When wait about 3 seconds
    When click button "btnSmile"
    Then Alert message with content "Personne déjà smilée" is showed on top
    When I logout account
    And I login with account "qa+sirenhy96@mektoube.fr" and "mektoube"
    When click button "notificationScreen"
    Then Display new notification smile
    When click button "firstNoti"
    Then Display button Discuter
  @QRA-1144
  Scenario: report a profile - "Block this person" must be checked by default
    Given I login with account "qa+xeme02@mektoube.fr" and "mektoube" and "allow" permission

    And I filter account with name "samiracute1"
    And I click on the user profile has name "Samiracute1"
    When click button "threeDotIcon"
    When click button "btnReportProfile"
    When I click any reason
    When click button "btnValidate"
    And I enter "test report profile(just for test)" on field
    When click button "btnValidate"
    Then Alert message with content "L'abus a été signalé" is showed on top
    And User account is blocked
    When wait about 3 seconds
    When click button "threeDotIcon"
    When click button "deBlockOrBlock"
    And wait about 3 seconds
  @QRA-1145
  Scenario: report a profile - choose 1 report reason and block the profile - should see green message, account blocked in Blacklist (premium man account)
    Given I login with account "qa+sirenhy93@mektoube.fr" and "mektoube" and "allow" permission

    And I filter account with name "samiracute1"
    And I click on the user profile has name "Samiracute1"
    When click button "threeDotIcon"
    When click button "btnReportProfile"
    When I click any reason
    When click button "btnValidate"
    And I enter "test report profile(just for test)" on field
    When click button "btnValidate"
    Then Alert message with content "L'abus a été signalé" is showed on top
    And User account is blocked
    When check user has name "samiracute1" has in blacklist
  @QRA-1146
  Scenario: block a profile - cannot search by name (girl block man)
    Given I login with account "qa+shainez29@mektoube.fr" and "mektoube**" and "allow" permission
    When I filter account with name "Flu"
    When I get all name user
    And click on the user profile
    When click button "threeDotIcon"
    When click button "deBlockOrBlock"
    When Alert message with content "Le blocage est confirmé" is showed on top
    Then Result not have user block recently
    When check user has name "" has in blacklist
  @QRA-1147
  Scenario: Unblock a female profile - should see button discuss, smile, destiny again (man unblock woman)
    Given I login with account "qa+shainez29@mektoube.fr" and "mektoube**" and "allow" permission
    When I scroll up
    When click on the user profile
    When click button "threeDotIcon"
    When click button "deBlockOrBlock"
    When Alert message with content "Le blocage est confirmé" is showed on top
    Then Don't show button discuter and button smile
    And wait about 3 seconds
    When click button "threeDotIcon"
    When click button "deBlockOrBlock"
    When Alert message with content "Personne débloquée" is showed on top
    Then Show button discuter and button smile
#-------------------------------------------------------------------------------------------------------------------------------
##  @QRA-
#  Scenario: Visit many profile
#    Given I login with account "qa+sirenhy99@mektoube.fr" and "mektoube" and "allow" permission
#    When click on the user profile
#    Then check information of 10 user
#    And wait about 2 seconds
#    And click button "btnBackProfile"
#    When I scroll up
#    When I scroll up
#    When I scroll up
#    And wait about 2 seconds
#    When click on the user profile
#    Then check information of 10 user
#    And wait about 2 seconds
#    And click button "btnBackProfile"
#    When I scroll up
#    When I scroll up
#    When I scroll up
#    And wait about 2 seconds
#    When click on the user profile
#    Then check information of 10 user
# -------------------------------------------------------------------------------------------------------------------------------
  @QRA-1921
  Scenario: List similar profile should be different with each user
    Given I login with account "qa+anie@mektoube.fr" and "mektoube" and "allow" permission
    When I scroll up
    When click on the user profile
    When I scoll to profile similar
    And save all name profile at similar to stack
    When click button "btnNextProfile"
    When I scoll to profile similar
    Then check similar profile diffirent with each user

  @QRA-192
  Scenario: edit profile field levoile and converti to Non => Don't show field le voile/Converti(e)
    And I login with account "qa+sirenhy99@mektoube.fr" and "mektoube" and "allow" permission
    When I filter account with name "anied"
    When click on the user profile
    Then do not show field levoile and converti at profile
    
