Feature: Go to discovery page

  Background: Login account
#    Given Clear stack

  @QRA-1081
  Scenario: Reset on discovery page
    Given I login with account "qa+anie@mektoube.fr" and "mektoube" and "allow" permission
    When I click button reset on discovery page
    Then The default filter state is hidden

  @QRA-1082
  Scenario: Click filter after scroll screen
    Given I login with account "qa+anie@mektoube.fr" and "mektoube" and "allow" permission
#    When I scroll up
#    And click button "btnReset"
    When I scroll up
    When  wait about 4 seconds
    And wait display button "filterAfterScrollDiscovery" and click
    When I scroll up to "Critères avancés" text
    When click button "btnAdvanced"
    When I scroll up to "Pseudo" text
    When click button "btnPseudo"
    When I enter "name" on field
    And I click tick button "btnTick"
    When click button "btnValidate"
    And wait about 2 seconds
    Then I check name of result
    When click on the user profile
    Then See name of user
#--------------------------------------------------------------------------------------
#  @QRA-1083
#  Scenario: Enter blank into boost profile
#    Given I login with account "qa+anie@mektoube.fr" and "mektoube" and "allow" permission
#    When I click on boost profile
#    When I scroll up to "VALIDER VOTRE CODE" text
#    When click button "btnValider"
#    Then See error message has text "Code d’accès est requis."
#  @QRA-1084
#  Scenario: Enter invalid code boost profile
#    Given I login with account "qa+anie@mektoube.fr" and "mektoube" and "allow" permission
#    When I click on boost profile
#    And I enter "abc" on the field
#    When I scroll up to "VALIDER VOTRE CODE" text
#    When click button "btnValider"
#    Then See error message "Code invalide"

#  @QRA-1085
#  Scenario: Boost profile
#    Given I change enviroment to "stagingEnvironment"
#    And I login with account "Fee13" and "mektoube" and "allow" permission
#    When I boost profile and logout
#    When I login with account "mozi66" and "mektoube"
#    When I click on boost profile
#    And I enter "X5596J93" on the field
#    When I scroll up to "VALIDER VOTRE CODE" text
#    When click button "btnValider"
#    And I scroll up
#    Then Can't click star green and hide boost profile
#    Then Display icon boost and show countdown on profile
#    When I logout account
#    When I login with account "Sonia20" and "mektoube"
#    Then Display star green in user "Mozi66"
#CHANGE ENVIROMENT to staging before run this case
  @QRA-1086
  Scenario: View photo of user on profile
    Given I login with account "qa+anie@mektoube.fr" and "mektoube" and "allow" permission
    Given I filter avec photo
    When I scroll up
    When click on the user profile
    Then I click see all photo of user

  @QRA-1087
  Scenario: View profile on similar
    Given I login with account "qa+anie@mektoube.fr" and "mektoube" and "allow" permission
    Given I scroll up
    When click on the user profile
    When I scoll to profile similar
    And I click profile similar
    Then Show the selected profile

  @QRA-1090
  Scenario: Next profile on similar
    Given I login with account "qa+anie@mektoube.fr" and "mektoube" and "allow" permission
    Given I scroll up
    When click on the user profile
    When I scoll to profile similar
    When I next profile on similar
    Then I check list user on similar profile

  @QRA-1091
  Scenario: Next profile on profile
    Given I login with account "qa+anie@mektoube.fr" and "mektoube" and "allow" permission
    Given I scroll up
    When click on the user profile
    And Get value "nameXpath"
    When click button "btnNextProfile"
    Then Check name of next user
    #---------------------------------------------------------------------------------------------
  @QRA-1096
  Scenario: Can not sent voice message on first message
    Given I login with account "qa+ngoctrinh89@mektoube.fr" and "mektoube" and "allow" permission
    When I filter account with name "Enlaine"
    And I click on the user profile has name "Enlaine"
    When click button "btnDiscuter"
    And wait about 2 seconds
    When click button "voiceButton"
    And wait about 2 seconds
    When click button "voiceButton"
    Then Display message warning "Vous ne pouvez pas envoyer un message vocal en premier message" on "xpathError"

  @QRA-1097
  Scenario: Can not sent image message on first message
    Given I login with account "qa+ngoctrinh89@mektoube.fr" and "mektoube" and "allow" permission
    Given I filter account with name "Enlaine"
    And I click on the user profile has name "Enlaine"
    When click button "btnDiscuter"
    And wait about 2 seconds
    When click button "btnSentImageDisable"
    And wait about 1 seconds
    When click button "btnSentImageDisable"
    Then Display message warning "Vous ne pouvez pas envoyer de photo en premier message" on "xpathError"
    #------------------------------------------------------------------------------------------------------
  @QRA-1098
  Scenario: send text message on first message
    Given I login with account "qa+anie@mektoube.fr" and "mektoube" and "allow" permission
    Given I filter account with name "Enlaine"
    And I click on the user profile has name "Enlaine"
    When click button "btnDiscuter"
    And wait about 2 seconds
    When I enter "first message" on the field
    And wait about 2 seconds
    When click button "btnSentMessage"
    Then Sent message success

  @QRA-1099
  Scenario: send gif message on first message
    Given I login with account "qa+ngoctrinh89@mektoube.fr" and "mektoube" and "allow" permission
    Given I filter account with name "Enlaine"
    And I click on the user profile has name "Enlaine"
    When click button "btnDiscuter"
    And wait about 2 seconds
    When click button "btnGifOnFirst"
    And I choose gif
    When I click button  sent gif
    And wait about 2 seconds
    Then Sent gif success

  @QRA-1100
  Scenario: send suggest message on first message
    Given I login with account "qa+shainez29@mektoube.fr" and "mektoube**" and "allow" permission
    Given I filter account with name "Enlaine"
    And I click on the user profile has name "Enlaine"
    When click button "btnDiscuter"
    And wait about 3 seconds
    When click button "btnSuggestMes"
    And I choose message
    And wait about 2 seconds
    When click button "btnSentMessage"
    And wait about 2 seconds
    Then Sent message suggest success

  @QRA-1148
  Scenario: Don't load list user when go to profile and click back to discovery
    Given I login with account "qa+anie@mektoube.fr" and "mektoube" and "allow" permission
    When I scroll up
    When I click on see profile user
    When click button "btnBackProfile"
    Then Check list user on discovery page

  @QRA-1149
  Scenario: Don't load list user when click go to any page and back to discovery
    Given I login with account "qa+anie@mektoube.fr" and "mektoube" and "allow" permission
    When I scroll up
    When Save list name user
    When I go to "contactRequestIcon" and back to discovery
    When I go to "messageIcon" and back to discovery
    When I go to "notificationScreen" and back to discovery
    When I go to "myProfileBtn" and back to discovery
#----------------------------------------------------------------------
  @QRA-1150
  Scenario: Display pratiquante on profile
    Given I login with account "qa+anie@mektoube.fr" and "mektoube" and "allow" permission
    When I scroll up
    When click on the user profile
    When I scroll up to "mètres" text
    Then Check value of Pratiquante on profile
    And click button "discoveryPage"
    When I scroll up
    And click on the user profile
    When I scroll up to "mètres" text
    Then Check value of Pratiquante on profile
    And click button "discoveryPage"
    When I scroll up
    And click on the user profile
    When I scroll up to "mètres" text
    Then Check value of Pratiquante on profile

  @QRA-1155
  Scenario: Display origine on profile
    Given I login with account "qa+anie@mektoube.fr" and "mektoube" and "allow" permission
    When I scroll up
    When click on the user profile
    When I scroll up to "mètres" text
    Then Check value of Origine on profile
    And click button "discoveryPage"
    When I scroll up
    When I scroll up
    When click on the user profile
    When I scroll up to "mètres" text
    Then Check value of Origine on profile
    And click button "discoveryPage"
    When I scroll up
    When click on the user profile
    When I scroll up to "mètres" text
    Then Check value of Origine on profile

#  @QRA-1947
#  Scenario: Show modal boost profile random 7-12, user boost at top and click boost profile at my profile
#    Given I login with account "qa+anie@mektoube.fr" and "mektoube" and "deny" permission
#    Then should be show user boosted profile at top of list
#    Then Show modal boost profile random
#    And wait display button "myProfileBtn" and click
#    When I click button has text "Boostez votre profil"
#    Then click boost profile at my profile show modal

  @QRA-2534
  Scenario:Testcase at screen Discovery & Profile page
    Given I login with account "qa+anie@mektoube.fr" and "mektoube" and "allow" permission
    When go to my profile and check display boost profile
    Then always show four list profile and show boost button if do not boosted, thumbnail,moderation
    Then visit right person when click profile from four list
    When show Skip button and Love button when scroll to end page
    When block user from profile should show at blacklist
    Then report profile and only report one time
    When react content of profile with name "sirenhy99"

  @QRA-
  Scenario: filter and save search
    Given I login with account "qa+anie@mektoube.fr" and "mektoube" and "allow" permission
    When filter online, birthday,origine, region and check result
    When filter age, profile recent and check result
    When filter has photo, tall, enfant and check result
    When filter pratiquant,femeur, study and check result
    Then save search, edit search, rename and remove save search




    
    
