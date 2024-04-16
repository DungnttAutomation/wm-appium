Feature: Login account invalid email
  I want to check account invalid email

  Background:
    Given I login with account "qa+mina20@mektoube.fr" and "mektoube" and "allow" permission

  @TEST_QRA-1205
  Scenario: Account invalid email has modal validate email
    When click button "myProfileBtn"
    Then Should see modal validate email

  @TEST_QRA-1206
  Scenario: Account invalid email has warning icon
    When click button "myProfileBtn"
    Then Should see modal validate email
    And click button "fermerBtn"
    And Wait until display element by xpath "warningIcon"

  @TEST_QRA-1207
  Scenario: Account invalid email can't change email
    When click button "myProfileBtn"
    And click button "fermerBtn"
    Then click button "btnSetting"
    And I click button has text "Email"
    Then Should see messages invalid email

  @TEST_QRA-1208
  Scenario: Account invalid email can't change name
    When click button "myProfileBtn"
    And click button "fermerBtn"
    Then click button "changenameBtn"
    Then Should see messages invalid email

  @TEST_QRA-1209
  Scenario: Account invalid email can't feeling messages
    Then Search pesudo "Ryan10"
    And Click discuter
    When last message in thread 1 is "Salam. Une question simple pour commencer : Si tu devais te décrire en un seul mot ? " message
    When Reaction "😍" icon on message index 1
    Then Should see pop up invalid email

  @TEST_QRA-1210
  Scenario: Account invalid email can't send gif/photo/messages/voice
    Then Search pesudo "Ryan10"
    And Click discuter
    When wait display button "gifButton" and click
    Then Should see pop up invalid email
    And click button "closeModal"
    And wait about 3 seconds
    When wait display button "photoIcon" and click
    Then Should see pop up invalid email
    And click button "closeModal"
    And wait about 3 seconds
    When click button "inputTextMsg"
    Then Should see pop up invalid email
    And click button "closeModal"
    And wait about 3 seconds
    When click button "voiceButton"
    Then Should see pop up invalid email
    And click button "closeModal"
    And wait about 3 seconds
    When click button "btnSuggestMes"
    And I choose message
    When click button "btnSentMessage"
    Then Should see pop up invalid email
    And click button "closeModal"




