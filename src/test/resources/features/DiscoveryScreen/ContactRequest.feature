Feature: As a mektoube user
  Background:
    Given Clear stack
  @QRA-1057
  Scenario: Click button cancel when sent contact request
    Given I login with account "yassine_yes2" and "Mektoubi2017" and "allow" permission
    When I filter account with name "Samiracute1"
    When click on the user profile
    And wait about 1 seconds
    When click button "btnCross"
    And wait about 2 seconds
    When click button "btnCancel"
    Then Close popup sent contact request
  @QRA-1058
  Scenario: Sent  contact request with blank text
    Given I login with account "yassine_yes2" and "Mektoubi2017" and "allow" permission
    When I filter account with name "Samiracute1"
    When click on the user profile
    And wait about 1 seconds
    When click button "btnCross"
    And wait about 2 seconds
    When click button "btnSent"
    Then Display error message
  @QRA-1059
  Scenario: Sent  contact request with personalize your message
    Given I login with account "yassine_yes2" and "Mektoubi2017" and "allow" permission
    When I filter account with name "Samiracute1"
    When click on the user profile
    And wait about 1 seconds
    When click button "btnCross"
    And wait about 2 seconds
    When I enter "test sent contact" on field
    When click button "btnSent"
    Then Display show popup sent contact request success
  @QRA-1060
  Scenario: Sent  contact request with random “Need some inspiration?”
    Given I login with account "qa+sirenhy93@mektoube.fr" and "mektoube" and "allow" permission
    When I filter account with name "Samiracute1"
    When click on the user profile
    And wait about 1 seconds
    When click button "btnCross"
    And wait about 2 seconds
    When click button "btnSuggest"
    When wait about 2 seconds
    And I click any someidea
    When click button "btnSent"
    And wait about 4 seconds
    Then Display show popup sent contact request success
  @QRA-1069
  Scenario: Accept on “contact request” page(I answer the message)
    Given I login with account "qa+sirenhy99@mektoube.fr" and "mektoube" and "allow" permission
    When I sent contact request to user has name "Samiracute1"
    When I logout account
    And I login with account "mektoubitest1@gmail.com" and "Mektoubi2017"
    When I click on contact request page
    When Click accept contact request most recent
    And I choose answer now
    Then See message and profile of partner
  @QRA-1070
  Scenario: Accept on “contact request” page(I answer later)
    Given I login with account "qa+sirenhy95@mektoube.fr" and "mektoube" and "allow" permission
    When I sent contact request to user has name "Samiracute1"
    When I logout account
    And I login with account "mektoubitest1@gmail.com" and "Mektoubi2017"
    When I click on contact request page
    When Click accept contact request most recent
    And I choose answer later
    When click button "messageIcon"
    And wait about 4 seconds
    When I click on the first message
    Then See message and profile of partner
  @QRA-1071
  Scenario: Refulse on “contact request” page
    Given I login with account "qa+sirenhy96@mektoube.fr" and "mektoube" and "allow" permission
    When I sent contact request to user has name "Samiracute1"
    When I logout account
    And I login with account "mektoubitest1@gmail.com" and "Mektoubi2017"
    When I click on contact request page
    When I click refulse contact request
    And wait about 2 seconds
    When I click list refule
    When wait about 2 seconds
    Then Display user has been refulse
  @QRA-1072
  Scenario: Click profile on “contact request” page  and refulse
    Given I login with account "qa+lavarr@mektoube.fr" and "mektoube" and "allow" permission
    When I sent contact request to user has name "Samiracute1"
    When I logout account
    And I login with account "mektoubitest1@gmail.com" and "Mektoubi2017"
    When I click on contact request page
    When I click profile most recent on contact request
    And wait about 2 seconds
    When I click refulse on profile
    And wait about 2 seconds
    When I click list refule
    When wait about 2 seconds
    Then Display user has been refulse
  @QRA-1073
  Scenario: Click profile on “list 5 refulse" and refulse
    Given I login with account "qa+alex16@mektoube.fr" and "mektoube" and "allow" permission
    When I sent contact request to user has name "Samiracute1"
    When I logout account
    And I login with account "mektoubitest1@gmail.com" and "Mektoubi2017"
    When I click on contact request page
    When I click refulse contact request
    When I click list refule
    And wait about 2 seconds
    When I first profile on list reulse
    When I click refulse on profile
    And wait about 2 seconds
    When I click list refule
    When wait about 2 seconds
    Then Display user has been refulse
  @QRA-1074
  Scenario: Click profile on “contact request” page  and accept(I answer the message)
    Given I login with account "qa+mashal01@mektoube.fr" and "mektoube" and "allow" permission
    When I sent contact request to user has name "Samiracute1"
    When I logout account
    And I login with account "mektoubitest1@gmail.com" and "Mektoubi2017"
    When I click on contact request page
    When I click profile most recent on contact request
    And wait about 2 seconds
    When I click accept on profile
    And I choose answer now on profile
    Then See message and profile of partner
  @QRA-1075
  Scenario: Click profile on “contact request” page  and accept(I answer later)
    Given I login with account "qa+alex10@mektoube.fr" and "mektoube" and "allow" permission
    When I sent contact request to user has name "Samiracute1"
    When I logout account
    And I login with account "mektoubitest1@gmail.com" and "Mektoubi2017"
    When I click on contact request page
    When I click profile most recent on contact request
    And wait about 2 seconds
    When I click accept on profile
    And I choose answer later on profile
    When click button "messageIcon"
    And wait about 4 seconds
    When I click on the first message
    Then See message and profile of partner
  @QRA-1076
  Scenario: Accept on “list 5 refulse" (I answer later)
    Given I login with account "qa+thor01@mektoube.fr" and "mektoube" and "allow" permission
    When I sent contact request to user has name "Samiracute1"
    When I logout account
    And I login with account "mektoubitest1@gmail.com" and "Mektoubi2017"
    When I click on contact request page
    When I click refulse contact request
    When I click list refule
    And wait about 2 seconds
    When I click accpept on list refulse
    And I choose answer later
    When click button "messageIcon"
    And wait about 4 seconds
    When I click on the first message
    Then See message and profile of partner
  @QRA-1077
  Scenario: Accept on “list 5 refulse" (I answer the message)
    Given I login with account "qa+sirenhy88@mektoube.fr" and "mektoube" and "allow" permission
    When I sent contact request to user has name "Samiracute1"
    When I logout account
    And I login with account "mektoubitest1@gmail.com" and "Mektoubi2017"
    When I click on contact request page
    When I click refulse contact request
    When I click list refule
    And wait about 2 seconds
    When I click accpept on list refulse
    And I choose answer now
    Then See message and profile of partner
  @QRA-1078
  Scenario: Click profile on “list 5 refulse" and click accept(I answer later)
    Given I login with account "qa+sirenhy67@mektoube.fr" and "mektoube" and "allow" permission
    When I sent contact request to user has name "Samiracute1"
    When I logout account
    And I login with account "mektoubitest1@gmail.com" and "Mektoubi2017"
    When I click on contact request page
    When I click refulse contact request
    When I click list refule
    And wait about 2 seconds
    When I first profile on list reulse
    When I click accept on profile
    When I choose answer later on profile
    When click button "messageIcon"
    And wait about 4 seconds
    When I click on the first message
    Then See message and profile of partner
  @QRA-1079
  Scenario: Click profile on “list 5 refulse" and click accept(I answer the message)
    Given I login with account "qa+sirenhy66@mektoube.fr" and "mektoube" and "allow" permission
    When I sent contact request to user has name "Samiracute1"
    When I logout account
    And I login with account "mektoubitest1@gmail.com" and "Mektoubi2017"
    When I click on contact request page
    When I click refulse contact request
    When I click list refule
    And wait about 2 seconds
    When I first profile on list reulse
    When I click accept on profile
    When I choose answer now on profile
    Then See message and profile of partner
    #_-----------------------------------------------------------------

  @QRA-1194
  Scenario Outline: Modal contact request when login account
    Given I login with account "<username>" and "mektoube" and "allow" permission
    When click button "contactRequestIcon"
    Then See text on second screen of modal "<text1>"
    And See text on third screen of modal "<text2>"
    Examples:
      | username | text1 | text2 |
      |qa+sirenhy99@mektoube.fr| Cette fonctionnalité permet aux femmes de choisir les hommes avec lesquels elles veulent discuter. \nSi vous lui plaisez, elle ne peut pas vous rater !|Si elle accepte votre invitation, vous pouvez échanger ensemble. \nSi elle refuse, vous ne pourrez pas la recontacter avant 30 jours.|
      |qa+anie@mektoube.fr | Les hommes vous invitent à discuter, vous pouvez accepter ou refuser !|En acceptant, vous pourrez échanger ensemble.\nEn refusant, il ne pourra pas vous renvoyer de message et il ne sera pas informé de votre refus.|
  @QRA-1195
  Scenario: Redirect to discovery when hasn't contact
    Given I login with account "Nicolas16+qa@mektoube.fr" and "mektoube" and "allow" permission
    When I click on contact request page
    And wait about 1 seconds
    When click button "btnRedirectDiscovery"
    Then Redirect to Discovery page
  @QRA-1199
  Scenario: Display list 5 refulse
    Given I login with account "qa+sirenhy24@mektoube.fr" and "mektoube" and "allow" permission
    When I sent contact request to user has name "Samiracute1"
    When I logout account
    And I login with account "mektoubitest1@gmail.com" and "Mektoubi2017"
    When I click on contact request page
    Then Do not display list refulse
    When I click refulse contact request
    Then Display list refulse and has name "Nicolas16" just refulse
  @QRA-1445
  Scenario: Man don't receive visit when woman go to profile from contact page
    Given Change environment to "stagingEnvironment"
    When I login with account "sofia9563" and "mektoube" and "allow" permission
    And Click on profile has name "mrik" at contact request screen
    When I logout account
    When I login with account "mrik" and "mektoube"
    Then Don't show visit of user "sofia9563" at noti screen

#  @QRA-1451
#  Scenario: Hide red dot when woman account hasn't contact request
#    Given Change environment to "stagingEnvironment"
#    When I login with account "alnjar" and "mektoube" and "allow" permission
#    When I sent contact request to user has name "Fee13"
#    When I logout account
#    When I login with account "Fee13" and "mektoube" and "allow" permission
#    Then display red dot on contact icon
#    When go to contact page and refulse all invite
#    Then go to discovery and hide red dot

  @QRA-1452
  Scenario: Display status connection of user on chat detail
    Given I login with account "qa+ngoctrinh89@mektoube.fr" and "mektoube" and "allow" permission
    When I scroll up
    And click on the user profile
    When see status connection of user on profile
    And go to chat detail with user
    Then check status connection
    When click button "backBtnInConversation"
    And click button "discoveryPage"
    When I scroll up
    And click on the user profile
    When see status connection of user on profile
    And go to chat detail with user
    Then check status connection

  @QRA-1953
  Scenario: Show modal contact request when man user use message function at noti screen
    Given I login with account "qa+lavar01@mektoube.fr" and "mektoube" and "allow" permission
    When I filter account with name "Sirenhy67"
    And click on the user profile
    When I logout account
    When I login with account "qa+Sirenhy67@mektoube.fr" and "mektoube"
    And click button "notificationScreen"
    When choose message function at noti visit of "lavar01"
    Then show modal sent contact request

