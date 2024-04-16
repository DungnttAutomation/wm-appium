@QRA-1392
Feature: Block list

  """account
    Male: Dennis99,
    Female: Rose, Alexvause
  """

#  @QRA-1227

  @QRA-1228
  Scenario: Add someone into block list from message, then unblock them
    Given login with username or email "qa+rose@mektoube.fr" and password "mektoube"
    When Search user by pseudo "Dennis99" and go to conversation
    When get "partnerNameInChatDetail" of user
    When Send a text message "Hello"
    When the user click on "3DotDeleteBlockReportChat"
    When I click button has text "Bloquer cette personne"
    When The page show message contain text "Le blocage est confirmé"
    When the user click on "usernameInChatDetail"
    When should see smile and discuss button are disappear
    Then the user should see them in block list
    Then the user unblock them
    When remove that profile from block list successfully

  @QRA-1229
  Scenario: Add someone into blacklist from discovery, then unblock them
    Given login with username or email "qa+rose@mektoube.fr" and password "mektoube"
    When click on first profile in discovery
    When the user click on "optionIconProfile"
    When add them into block list by choose "blockBtn"
    When should see smile and discuss button are disappear
    Then the user should see them in block list
    Then the user unblock them
    When remove that profile from block list successfully

  @QRA-1230
  Scenario: Add someone into blacklist from notifications, then unblock them
    Given login with username or email "qa+rose@mektoube.fr" and password "mektoube"
    When the user click on "notificationScreen"
    When the user block them from notification
#    When Alert message with content "Le blocage est confirmé" is showed on top
    Then the user should see them in block list
    Then the user unblock them
    When remove that profile from block list successfully

  @QRA-1231
  Scenario: Remove the user by visiting the profile from the blacklist
    Given login with username or email "qa+rose@mektoube.fr" and password "mektoube"
    When click on first profile in discovery
    When the user click on "optionIconProfile"
    When add them into block list by choose "blockBtn"
    When should see smile and discuss button are disappear
    Then the user should see them in block list
    When the user click on "profileItemFromBlocklist"
    When should see smile and discuss button are disappear
    When the user click on "optionIconProfile"
    When the user click on "unblockBtn"
#    Then Alert message with content "Personne débloquée" is showed on top
    When the user click on "myAccountMenuBtn"
    When the user click on "settingTab"
    When the user click on "blacklistMenu"
    When remove that profile from block list successfully

  @QRA-1232
  Scenario: Cannot send any messages to people who have been blocked, then unblock them
    Given login with username or email "qa+rose@mektoube.fr" and password "mektoube"
    When Search user by pseudo "Dennis99" and go to conversation
    When Send a text message "Hello"
    When the user click on "3DotDeleteBlockReportChat"
    When I click button has text "Bloquer cette personne"
    When The page show message contain text "Le blocage est confirmé"
    Then All buttons are grayed out and show message Vous avez bloqué "Dennis99"
    When wait about 3 seconds
    When the user click on "backBtnInConversation"
    When the user click on "myAccountMenuBtn"
    When the user click on "settingTab"
    When the user click on "blacklistMenu"
    Then the user unblock them

  @QRA-1233
  Scenario: Discuss and Smile button disappear when block a man, then unlock them
    Given login with username or email "qa+rose@mektoube.fr" and password "mektoube"
    When I filter account with name "Dennis99"
    When Click profile have name "Dennis99" after search psedou
    When the user click on "optionIconProfile"
    When add them into block list by choose "blockBtn"
    Then should see smile and discuss button are disappear
    When the user click on "optionIconProfile"
    When the user click on "unblockBtn"
    Then Alert message with content "Personne débloquée" is showed on top

#  @QRA-1234
