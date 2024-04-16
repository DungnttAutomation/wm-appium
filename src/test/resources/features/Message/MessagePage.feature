Feature: Message module

  Background:
#    Given Change environment to"stagingEnvironment"
#  Given login with username or email "qa+ngoctrinh89@mektoube.fr" and password "mektoube" and go to Thread chat

  @TEST_QRA-1028
  Scenario: Go to a conversation from thread list
    Given login with username or email "qa+ngoctrinh89@mektoube.fr" and password "mektoube"
    When Search user by pseudo "Sirenhy99" and go to conversation
    When Send a text message "Hello Sirenhy99"
    When click button by xpath "backBtnInConversation"
    When Logout current account and login account "qa+sirenhy99@mektoube.fr" and password "mektoube"
    When click "messageIcon" in menu bar
    When click thread message index 1 in thread list
    Then conversation is displayed

  @TEST_QRA-1037
  Scenario: Cannot send a empty text message
    Given login with username or email "qa+sirenhy99@mektoube.fr" and password "mektoube"
    When click "messageIcon" in menu bar
    When click thread message index 2 in thread list
    When Wait until display element by xpath "inputTextMsg"
    When click button by xpath "inputTextMsg"
    When clear text and insert text "   " into field "inputTextMsg"
    Then "sendButton" button dont display

  @TEST_QRA-1038
  Scenario: Send a text message
    Given login with username or email "qa+sirenhy99@mektoube.fr" and password "mektoube"
    When click "messageIcon" in menu bar
    When click thread message index 3 in thread list
    When Wait until display element by xpath "inputTextMsg"
    When click button by xpath "inputTextMsg"
    When clear text and insert text "Hello" into field "inputTextMsg"
    When Wait until display element by xpath "sendButton"
    When click button by xpath "sendButton"
    When wait about 5 seconds
    Then Text message is send realtime

  @TEST_QRA-1046
  Scenario Outline: Filter unread message
    Given login with username or email "<username>" and password "<pass>"
    When click "messageIcon" in menu bar
    When click button by xpath "filterMessage"
    When I click button has text "Messages non lus"
    When wait about 2 seconds
    Then check unread message
    Examples:
      | username                   | pass     |
      | qa+sirenhy99@mektoube.fr   | mektoube |
      | qa+ngoctrinh89@mektoube.fr | mektoube |

  @TEST_QRA-1047
  Scenario: Delete conversation in thread list
    Given login with username or email "qa+ngoctrinh89@mektoube.fr" and password "mektoube"
    When Search user by pseudo "Sirenhy99" and go to conversation
    When Send a text message "Hello Sirenhy99"
    When click button by xpath "backBtnInConversation"
#    Given login with username or email "qa+Shainez29@mektoube.fr" and password "mektoube**"
    When Logout current account and login account "qa+sirenhy99@mektoube.fr" and password "mektoube"
    When wait display button "messageIcon" and click
    When Swipe left in thread index 1 to delete thread chat
    Then That thread is deleted success

  @TEST_QRA-1197
  Scenario: Delete conversation from chat detail
    Given login with username or email "qa+sahara02@mektoube.fr" and password "Mektoube**"
    When Make sure dont block user "hann99"
    When click button by xpath "iconSearch"
    When Search user by pseudo "hann99" and go to conversation
#    When Send a text message "Message from saharay"
    When click button by xpath "3DotDeleteBlockReportChat"
    When I click button has text "Supprimer cette conversation"
    When wait about 2 seconds
    When I click button has text "ANNULER"
    When Wait until display element by xpath "3DotDeleteBlockReportChat"
    When click button by xpath "3DotDeleteBlockReportChat"
    When I click button has text "Supprimer cette conversation"
    When I click button has text "OK"
#   When Wait until display element by xpath "deleteConversationSuccessMsg"
    When click button by xpath "btnDiscuss"
    When wait about 2 seconds
    Then Conversation is empty

  @TEST_QRA-1563 # precondition: @TEST_QRA-1197
  Scenario: Continue chat after delete conversation
    Given login with username or email "qa+ngoctrinh89@mektoube.fr" and password "mektoube"
    When Make sure dont block user "Sirenhy99"
    When Search user by pseudo "Sirenhy99" and go to conversation
    Then Conversation is empty
    And wait about 3 seconds
    When Send a text message "Message from Lucas"
    Then Text message is send realtime

  @TEST_QRA-1564 # precondition: @TEST_QRA-1197
  Scenario: Delete conversation - messages still display on partner side
    Given login with username or email "qa+Sirenhy99@mektoube.fr" and password "mektoube"
    When click "messageIcon" in menu bar
    When last message in thread 1 is "Message from Lucas" message
    When click thread message index 1 in thread list
    Then conversation is displayed
    Then Send a text message "Message from partner after user delete conversation"

  @TEST_QRA-1055
  Scenario: Send a gif message
    Given login with username or email "qa+Sirenhy99@mektoube.fr" and password "mektoube"
    When Search user by pseudo "Ngoctrinh89" and go to conversation
    When Wait until display element by xpath "gifButton"
    When click button by xpath "gifButton"
    When Wait until display element by xpath "firstGifInList"
    When wait about 2 seconds
    When click button by xpath "firstGifInList"
    When click button by xpath "sendButton"
    When wait about 3 seconds
    Then message is send realtime

  @TEST_QRA-1080
  Scenario: Read message - check counter
    Given login with username or email "qa+Sirenhy99@mektoube.fr" and password "mektoube"
    When Search user by pseudo "Ngoctrinh89" and go to conversation
    When Send a text message "Hello"
    When click button by xpath "backBtnInConversation"
    When Logout current account and login account "qa+ngoctrinh89@mektoube.fr" and password "mektoube"
    When click "messageIcon" in menu bar
    Then read newest message and check counter

  @TEST_QRA-1094
  Scenario: Check last message (text/photo/gif) in thread list with in chat details
    Given login with username or email "qa+Sirenhy99@mektoube.fr" and password "mektoube"
    When Search user by pseudo "Ngoctrinh89" and go to conversation
    When Send a ephemeral photo
    When click button by xpath "backBtnInConversation"
    When wait display button "messageIcon" and click
    Then last message in thread 1 is "Message image" message
    When click thread message index 1 in thread list
    When Send a gif message
    When click button by xpath "backBtnInConversation"
    When wait about 3 seconds
    Then last message in thread 1 is "Message gif" message

  @TEST_QRA-1105
  Scenario: Scroll to view old message - click check button to view newest message
    Given login with username or email "qa+Sirenhy99@mektoube.fr" and password "mektoube"
    When Search user by pseudo "Ngoctrinh89" and go to conversation
    When Send a text message "Hello"
    When Scroll down to see old message
    When wait display button "scrollDownNewMsg" and click
    When wait about 2 seconds
    Then Check last message is still right
    And "scrollDownNewMsg" is disappeared

  @TEST_QRA-1109
  Scenario: Feeling message/last message - check subject in thread list
    Given login with username or email "qa+Sirenhy99@mektoube.fr" and password "mektoube"
    When Search user by pseudo "Ngoctrinh89" and go to conversation
    When Send a text message "Message 1"
    When click button by xpath "inputTextMsg"
    When clear text and insert text "Message 2" into field "inputTextMsg"
    When Wait until display element by xpath "sendButton"
    When click button by xpath "sendButton"
    When click button by xpath "backBtnInConversation"
    When Logout current account and login account "qa+ngoctrinh89@mektoube.fr" and password "mektoube"
    When wait about 2 seconds
    When click "messageIcon" in menu bar
    When wait about 3 seconds
    When last message in thread 1 is "Message 2" message
    When click thread message index 1 in thread list
    When Reaction "😂" icon on message index 2
    When wait about 3 seconds
    When Reaction "👍" icon on message index 1
    When click button by xpath "backBtnInConversation"
    When Logout current account and login account "qa+sirenhy99@mektoube.fr" and password "mektoube"
    When wait about 2 seconds
    When click "messageIcon" in menu bar
    When wait about 2 seconds
    Then last message in thread 1 is "a réagi à votre message" message
    When click thread message index 1 in thread list
    When wait about 5 seconds

  @TEST_QRA-1139
  Scenario: Reply text message by a text
    Given login with username or email "qa+Sirenhy99@mektoube.fr" and password "mektoube"
    When Search user by pseudo "Ngoctrinh89" and go to conversation
    When Send a text message "Hello"
    When click button by xpath "backBtnInConversation"
    When Logout current account and login account "qa+ngoctrinh89@mektoube.fr" and password "mektoube"
    When click "messageIcon" in menu bar
    When wait about 2 seconds
    Then last message in thread 1 is "Hello" message
    When click thread message index 1 in thread list
    When click three dot felling report reply button in message index 1
    When click button by xpath "replyMessageButton"
    When Send a text message "this is text reply message"
    When wait about 3 seconds
    Then Text message "Hello" is replied by "textMsgReply"

  @TEST_QRA-1140
  Scenario: Reply text message by a ephemeral photo
    Given login with username or email "qa+Sirenhy99@mektoube.fr" and password "mektoube"
    When Search user by pseudo "Ngoctrinh89" and go to conversation
    When Send a text message "Hello"
    When click button by xpath "backBtnInConversation"
    When Logout current account and login account "qa+ngoctrinh89@mektoube.fr" and password "mektoube"
    When click "messageIcon" in menu bar
    When wait about 2 seconds
    Then last message in thread 1 is "Hello" message
    When click thread message index 1 in thread list
    When click three dot felling report reply button in message index 1
    When click button by xpath "replyMessageButton"
    When Send a ephemeral photo
    When wait about 3 seconds
    Then Text message "Hello" is replied by "photoMsgReply"

  @TEST_QRA-1141
  Scenario: Reply text message by a GIF
    Given login with username or email "qa+Sirenhy99@mektoube.fr" and password "mektoube"
    When Search user by pseudo "Ngoctrinh89" and go to conversation
    When Send a text message "Hello"
    When click button by xpath "backBtnInConversation"
    When Logout current account and login account "qa+ngoctrinh89@mektoube.fr" and password "mektoube"
    When click "messageIcon" in menu bar
    When wait about 2 seconds
    Then last message in thread 1 is "Hello" message
    When click thread message index 1 in thread list
    When click three dot felling report reply button in message index 1
    When click button by xpath "replyMessageButton"
    When Send a gif message
    When wait about 3 seconds
    Then Text message "Hello" is replied by "gifMsgReply"

  @TEST_QRA-1142
  Scenario: Reply text message by a voice message
    Given login with username or email "qa+Sirenhy99@mektoube.fr" and password "mektoube"
    When Search user by pseudo "Ngoctrinh89" and go to conversation
    When Send a text message "Hello"
    When click button by xpath "backBtnInConversation"
    When Logout current account and login account "qa+ngoctrinh89@mektoube.fr" and password "mektoube"
    When click "messageIcon" in menu bar
    When wait about 1 seconds
    Then last message in thread 1 is "Hello" message
    When click thread message index 1 in thread list
    When click three dot felling report reply button in message index 1
    When click button by xpath "replyMessageButton"
    When Send a voice message
    When Wait until display element by xpath "voiceMsgReply"
    Then Text message "Hello" is replied by "voiceMsgReply"

  @TEST_QRA-1196
  Scenario: Block user from chat details
    Given login with username or email "qa+ngoctrinh89@mektoube.fr" and password "mektoube"
    When Make sure dont block user "Sirenhy99"
    When Search user by pseudo "Sirenhy99" and go to conversation
    When Send a text message "Hello"
    When click button by xpath "3DotDeleteBlockReportChat"
    When I click button has text "Bloquer cette personne"
    When The page show message contain text "Le blocage est confirmé"
    Then All buttons are grayed out and show message Vous avez bloqué "Sirenhy99"

    #maintain done 18/03/2022
  @TEST_QRA-1565 #precondition: PASS @TEST_QRA-1196
  Scenario: Unblock account from conversation - can send message normal
    Given login with username or email "qa+ngoctrinh89@mektoube.fr" and password "mektoube"
    When Make sure dont block user "Sirenhy99"
    When Search user by pseudo "Sirenhy99" and go to conversation
    When Send a gif message
    When Send a text message "Send mes sage normal after deblock"
    When Send a ephemeral photo
    When Send a voice message
    When click button by xpath "backBtnInConversation"
    When Logout current account and login account "qa+sirenhy99@mektoube.fr" and password "mektoube"
    When Make sure dont block user "Ngoctrinh89"
    When Search user by pseudo "Ngoctrinh89" and go to conversation
    When Send a gif message
    When Send a text message "Send message normal after deblock"

    When Wait until display element by xpath "photoIcon"
    When click button by xpath "photoIcon"
    When wait about 3 seconds
    When I click button has text "Choisir dans la bibliothèque"
#    When wait display button "galleryIcon" and click
    When Click photo index 0 in gallery
    When Wait until display element by xpath "previewPhoto"
    When Select time view "5s"
    When click button by xpath "sendPhotoBtn"
    When Wait until display element by xpath "sentLastPhoto"

    When sent voice message
    Then Sent voice message success

  @TEST_QRA-1198
  Scenario: Report - block account from conversation - can't send message
    Given login with username or email "mektoubitest1@gmail.com" and password "Mektoubi2017"
    When Make sure dont block user "Sirenhy99"
    When Search user by pseudo "Sirenhy99" and go to conversation
    When Send a text message "Message before block"
    When click button by xpath "3DotDeleteBlockReportChat"
    When I click button has text "Signaler un abus"
    Then The page show full modal "Signaler un utilisateur"
    When I click button has text "VALIDER"
    Then The page show full modal "Signaler un utilisateur"
    When I click any reason
    When I click button has text "VALIDER"
    When wait about 2 seconds
    Then The page show full modal "Dites-nous en plus sur ce signalement"
    And I enter "Just 4 test report acc from conversation" on field
    When I click button has text "VALIDER"
    Then Alert message with content "L'abus a été signalé" is showed on top
    Then All buttons are grayed out and show message Vous avez bloqué "sirenhy99"
    Then wait about 2 seconds
    When click button by xpath "backBtnInConversation"
    When Logout current account and login account "qa+sirenhy99@mektoube.fr" and password "mektoube"
    When Make sure dont block user "samiracute1"
    When click "messageIcon" in menu bar
    When last message in thread 1 is "Message before block" message
    When click thread message index 1 in thread list
    Then All buttons are grayed out and show message Vous avez bloqué "samiracute1"
    When click button by xpath "backBtnInConversation"
    When Logout current account and login account "mektoubitest1@gmail.com" and password "Mektoubi2017"
    When Make sure dont block user "Sirenhy99"


  @TEST_QRA-1566
  Scenario: Report - don't block account from conversation - Still send messages
    Given login with username or email "mektoubitest1@gmail.com" and password "Mektoubi2017"
    When Make sure dont block user "Montassir931"
    When Search user by pseudo "Montassir931" and go to conversation
    When click button by xpath "3DotDeleteBlockReportChat"
    When I click button has text "Signaler un abus"
    Then The page show full modal "Signaler un utilisateur"
    When click button by xpath "checkBoxBlockReportAbuse"
    When I click any reason
    When I click button has text "VALIDER"
    When wait about 2 seconds
    Then The page show full modal "Dites-nous en plus sur ce signalement"
    And I enter "Just 4 test report acc from conversation" on field
    When I click button has text "VALIDER"
    Then Alert message with content "L'abus a été signalé" is showed on top
    When Send a gif message
    When Send a text message "Still send text after report"
    When Send a ephemeral photo
    When Send a voice message

  @TEST_QRA-1856
  Scenario: [App] Limit report - report 2 times show red message (report from chat detail), continue chat normal
    Given login with username or email "qa+Sirenhy99@mektoube.fr" and password "mektoube"
    When Make sure dont block user "Ngoctrinh89"
    When Search user by pseudo "Ngoctrinh89" and go to conversation
    When click button by xpath "3DotDeleteBlockReportChat"
    When I click button has text "Signaler un abus"
    Then The page show full modal "Signaler un utilisateur"
    When click button by xpath "checkBoxBlockReportAbuse"
    When wait about 2 seconds
    When I click any reason
    When I click button has text "VALIDER"
    When wait about 2 seconds
    Then The page show full modal "Dites-nous en plus sur ce signalement"
    And I enter "Just 4 test limit report acc from conversation" on field
    When I click button has text "VALIDER"
    Then Alert message with content "« Vous avez déjà signalé ce profil, le signalement a déjà été pris en compte »" is showed on top
    When Send a text message "Send text message success after report fail by limit report feature"
    When click button by xpath "backBtnInConversation"
    When Logout current account and login account "qa+ngoctrinh89@mektoube.fr" and password "mektoube"
    When Search user by pseudo "Sirenhy99" and go to conversation
    When Send a text message "Send text message success after report fail by limit report feature"

#  @TEST_QRA-1567 #precondition: PASS @TEST_QRA-1566
#  Scenario: User is reported + don't block from conversation - Still send messages
#    Given login with username or email "qa+Sirenhy99@mektoube.fr" and password "mektoube"
#    When Make sure dont block user "samiracute1"
#    When Search user by pseudo "samiracute1" and go to conversation
#    When Send a gif message
#    When Send a text message "Still send text after is reported by sirenhy99"
#    When Send a ephemeral photo
#    When Send a voice message

  @TEST_QRA-1303
  Scenario Outline: Alway show active status in conversation
    Given login with username or email "qa+ngoctrinh89@mektoube.fr" and password "mektoube"
    When I filter account with name "<partner>"
    And wait about 3 seconds
    When Click profile have name "<partner>" after search psedou
    When Wait until display element by xpath "discussButton"
    When click button by xpath "discussButton"
    When wait about 2 seconds
    Then check active status of partner is show on header in conversation
    Examples:
      | partner     |
      | Yassine_yes |
      | Sirenhy99   |

  @TEST_QRA-1444
  Scenario Outline: Check number of threads in thread list before and after refresh (nonpremium & premium account)
    Given login with username or email "<email>" and password "<pass>"
    When click button "messageIcon"
    Then check number of threads in thread list before and after refresh
    Examples:
      | email                   | pass         |
      | qa+arthur98@mektoube.fr | Mektoubi2017 |
      | qa+arthur99@mektoube.fr | mektoube     |

  @TEST_QRA-1943 #maintain done 18/03/2022
  Scenario: Click green button when don't have any conversation in list
    Given login with username or email "Qa+lantiu@mektoube.fr" and password "mektoube"
    When click "messageIcon" in menu bar
    When I click button has text "DÉCOUVRIR DES PROFILS"
    Then I am on the discovery page

  @TEST_QRA-1944
  Scenario: Search a gif and send success
    Given login with username or email "qa+Sirenhy99@mektoube.fr" and password "mektoube"
    When Search user by pseudo "Ngoctrinh89" and go to conversation
    When Wait until display element by xpath "gifButton"
    When click button by xpath "gifButton"
    When clear text and insert text "funny" into field "searchGifField"
    When wait about 3 seconds
    When Wait until display element by xpath "firstGifInList"
    When wait about 2 seconds
    When click button by xpath "firstGifInList"
    When click button by xpath "sendButton"
    When wait about 3 seconds
    Then message is send realtime

  @TEST_QRA-1945
  Scenario Outline: Check status online of user must same in conversation
    Given login with username or email "qa+ngoctrinh89@mektoube.fr" and password "mektoube"
    When I filter account with name "<Username>"
    When I click on the user profile has name "<Username>"
    Then Check status online in profile and conversation
    Examples:
      | Username     |
      | Sirenhy99    |
      | Brandon92500 |

  @TEST_QRA-2006
  Scenario: Messages just send still display after refresh list
    Given login with username or email "qa+ngoctrinh89@mektoube.fr" and password "mektoube"
    When click "messageIcon" in menu bar
    When click conversation with "sirenhy99" in thread list

    When Send a text message "text message"
    Then last message in chat detail is "text message"
    When click button by xpath "backBtnInConversation"
    Then last message in thread with "sirenhy99" is "text message"
    When Scroll down to refresh page
    Then last message in thread with "sirenhy99" is "text message"
    When click conversation with "sirenhy99" in thread list
    Then last message in chat detail is "text message"

    When Send a gif message
    Then last message in chat detail is "gif"
    When click button by xpath "backBtnInConversation"
    Then last message in thread with "sirenhy99" is "gif"
    When Scroll down to refresh page
    Then last message in thread with "sirenhy99" is "gif"
    When click conversation with "sirenhy99" in thread list
    Then last message in chat detail is "gif"

    When Send a ephemeral photo
    Then last message in chat detail is "photo"
    When click button by xpath "backBtnInConversation"
    When wait about 5 seconds
    Then last message in thread with "sirenhy99" is "photo"
    When Scroll down to refresh page
    Then last message in thread with "sirenhy99" is "photo"
    When click conversation with "sirenhy99" in thread list
    Then last message in chat detail is "photo"

    When Send a voice message
    Then last message in chat detail is "voice"
    When click button by xpath "backBtnInConversation"
    Then last message in thread with "sirenhy99" is "voice"
    When Scroll down to refresh page
    Then last message in thread with "sirenhy99" is "voice"
    When click conversation with "sirenhy99" in thread list
    Then last message in chat detail is "voice"

  @TEST_QRA-2009
  Scenario: Bug still show green counter when seen message and refresh app
    Given login with username or email "qa+ngoctrinh89@mektoube.fr" and password "mektoube"
    When click "messageIcon" in menu bar
    When click conversation with "sirenhy99" in thread list
    When Send a text message "Message 1"
    When Send a text message "Message 2"
    When click button by xpath "backBtnInConversation"
    When Logout current account and login account "qa+sirenhy99@mektoube.fr" and password "mektoube"
    When click "messageIcon" in menu bar
    When Wait until display element by xpath "greenCounterInThreadWithNgoctrinh89"
    When read newest message and check counter
    When Scroll down to refresh page
    Then dont display this element "greenCounterInThreadWithNgoctrinh89"

  @TEST_QRA-2008
  Scenario: Blue counter reduce when delete a thread has new messages
    Given login with username or email "qa+ngoctrinh89@mektoube.fr" and password "mektoube"
    When Search user by pseudo "Yassine_yes" and go to conversation
    When Send a text message "blue counter reduces when delete a thread have new messages - app"
    When click button by xpath "backBtnInConversation"
    When Logout current account and login account "Yassine_yes" and password "Mektoubi2017"
    When click "messageIcon" in menu bar
    When last message in thread with "ngoctrinh89" is "blue counter reduces when d..."
    When last message in thread 1 is "blue counter reduces when d..." message
    When Swipe left in thread index 1 to delete thread chat
    Then That thread is deleted success
    Then the blue counter is reduced one


  @TEST_QRA-2532
  Scenario: Testcase at screen Thread chat  & chat detail
    Given login with username or email "qa+anie@mektoube.fr" and password "mektoube" and go to Thread chat
    When filter unread messages in chat thread if any then read
    When does not change the order of conversations after reloading the thread list
    When check status, last message, name and avatar in thread same as in chat detail of conversation with name "sirenhy99"
    When sent all type message success
    And limit report abuse
    Then visit the profile and comeback

  @TEST_QRA-2533
  Scenario: Testcase at screen chat detail  (reply to message and report voice,photo)- must pass previous testcase
    Given login with username or email "qa+sirenhy99@mektoube.fr" and password "mektoube" and go to Thread chat
    When open thread message with name "anied"
    And report voice and photo messsage
    When report profile
    Then reply all type message






