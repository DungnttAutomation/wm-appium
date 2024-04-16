Feature: Voice message

  Background: Login account
#    When Change environment to "preprodEnvironment"
  @QRA-1115
  Scenario: Enable permission & sent voice 00:00,cancel when recording voice
#    When the user go to "Se déconnecter" screen
#    And reset data test
#    Given  I login with account "qa+sirenhy961@mektoube.fr" and "mektoube" and "allowPermissionAndroid7" permission
    When click button "messengerMenuBtn"
    When I click on the first message
    And Wait until display element by xpath "inputTextMsg"
    When I long press button "voiceButton" with 3 seconds
    When I choose "allowPermissionAndroid7" permission voice
    And wait about 2 seconds
#    When I long press button "voiceButton" with 2 seconds
    When I choose "allowPermissionAndroid7" permission voice
#    sent voice 0:00
    And wait about 2 seconds
    When click button "voiceButton"
    Then Display message warning "Les messages vocaux de moins d’une seconde ne sont pas envoyés" on "lessVoiceMesError"

    And wait about 2 seconds
    When sent voice message
    Then Sent voice message success

    And play voice just sent
    When I lock recording voice
    Then Voice just play is paused
    And Can not play voice
    And click button "btnCancelVoice"
    And Wait until display element by xpath "inputTextMsg"
    And wait about 2 seconds
    When click button "voiceButton"
    And wait about 1 seconds
    Then Display message warning "Les messages vocaux de moins d’une seconde ne sont pas envoyés" on "lessVoiceMesError"

    When click button "backBtnInConversation"
    And wait about 1 seconds
    When click button "messengerMenuBtn"
    Then Show last voice in thread chat


  @QRA-1123
  Scenario: Check message when report voice and recording & click profile
    When the user go to "Se déconnecter" screen
    Given reset data test
    Given  I login with account "qa+thao11@mektoube.fr" and "mektoube" and "allowPermissionAndroid7" permission
    When click button "messengerMenuBtn"
#    When wait about 1 seconds
#    When I click on the first message
#    When I long press button "voiceButton" with 2 seconds
#    When I choose "deny" permission voice
#    And wait about 2 seconds
#    When I long press button "voiceButton" with 2 seconds
#    Then Display modal ask permission again
#    When I choose "deny" permission voice
#    When I long press button "voiceButton" with 2 seconds
#    Then Redirect to setting
#    And Enable permission and click back
#    When click button "backBtnInConversation"
#    When I scroll down
#    And wait about 2 seconds
    When I click on the first message
    And Wait until display element by xpath "inputTextMsg"

    When I long press button "voiceButton" with 2 seconds
    When I choose "allowPermissionAndroid7" permission voice
    And wait about 2 seconds

#    When I long press button "voiceButton" with 2 seconds
    When I choose "allowPermissionAndroid7" permission voice
    And wait about 2 seconds

    When sent voice message
    Then Sent voice message success

    When I lock recording voice
    And click button "profileSmallIcon"
    When click button "backInProfile"
    And Wait until display element by xpath "inputTextMsg"

    When I click report voice message
    Then Alert message with content "Votre signalement sur le message vocal a bien été pris en compte" is showed on top
    When I click report voice message
    Then Alert message with content "Ce message vocal a déjà été signalé" is showed on top

    And wait about 1 seconds
    When click three dot felling report reply button in message index 1
    When click button by xpath "iconReplyMessage"
    And sent voice message
#    Then play voice received then play voice just send





#  @QRA-1124
#  Scenario: When someone reports and block person in voice message
#    When Sent voice messsage
#    When Sent voice message success
#    When click button "backBtnInConversation"
#    Then Show last voice in thread chat
#    When click "messengerMenuBtn" in menu bar
#    When I logout account
#    And I login with account "qa+anie@mektoube.fr" and "mektoube"
#    When click button "messengerMenuBtn"
#    When wait about 1 seconds
#    When I click on the first message
#    When I click report voice message and choose block
#    Then Alert message with content "Votre signalement a été pris en compte et la personne a été bloquée" is showed on top
#    And wait about 1 seconds
#    When click button "voiceButton"
#    Then Alert message with content "Vous avez bloqué sirenhy99" is showed on top
#    And wait about 2 seconds
#    When click button "backBtnInConversation"
#    When check user has name "sirenhy99" has in blacklist
#
#  @QRA-1930
#  Scenario: Reply message by voice and play voice
#    When Sent voice messsage
#    When Sent voice message success
#    When click button "backBtnInConversation"
#    When click "messengerMenuBtn" in menu bar
#    Then Show last voice in thread chat
#    When I logout account
#    And I login with account "qa+anie@mektoube.fr" and "mektoube"
#    When click button "messengerMenuBtn"
#    When wait about 1 seconds
#    When I click on the first message
#    When click three dot felling report reply button in message index 1
#    When click button by xpath "replyMessageButton"
#    And sent voice message
#    Then play voice received then play voice just send
#1












