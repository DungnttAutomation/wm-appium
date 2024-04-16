Feature: Ephemeral photo function

  Background:
#    Given Change environment to "preprodEnvironment"

  @TEST_QRA-1088
  Scenario: Send a ephemeral photo success - select photo from gallery
    Given login with username or email "qa+sirenhy99@mektoube.fr" and password "mektoube"
    When Search user by pseudo "Ngoctrinh89" and go to conversation
    When Wait until display element by xpath "photoIcon"
    When click button by xpath "photoIcon"
    When wait about 3 seconds
    When I click button has text "Choisir dans la bibliothèque"
    When I choose "allow" permission voice
#    When wait display button "galleryIcon" and click
    When Click photo index 1 in gallery
    When Wait until display element by xpath "previewPhoto"
    When Select time view "5s"
    When click button by xpath "sendPhotoBtn"
    When Wait until display element by xpath "sentLastPhoto"
    When message is send realtime

  @TEST_QRA-1089
  Scenario: Send a ephemeral photo success - take a photo
    Given login with username or email "qa+sirenhy99@mektoube.fr" and password "mektoube"
    When Search user by pseudo "Ngoctrinh89" and go to conversation
    When Wait until display element by xpath "photoIcon"
    When click button by xpath "photoIcon"
    When I click button has text "Prendre une photo"
    When I choose "allow" permission voice
    When wait display button "takePictureBtn" and click
    When wait display button "okButton" and click
    When Wait until display element by xpath "previewPhoto"
    When Select time view "5s"
    When click button by xpath "sendPhotoBtn"
    When Wait until display element by xpath "sentLastPhoto"
    When message is send realtime

  @TEST_QRA-1104
  Scenario: View ephemeral photo
    Given login with username or email "qa+sirenhy99@mektoube.fr" and password "mektoube"
    When Search user by pseudo "Ngoctrinh89" and go to conversation
    When Send a ephemeral photo
    When click button by xpath "backBtnInConversation"
    When Logout current account and login account "qa+ngoctrinh89@mektoube.fr" and password "mektoube"
    When click "messageIcon" in menu bar
    Then last message in thread 1 is "Message image" message
    When click thread message index 1 in thread list
    When wait display button "newPhotoMessageInChat" and click
    When Wait until display element by xpath "previewPhoto"
    When Wait until display element by xpath "viewedPhoto"
    Then Show viewed time on photo and cannot view again

#    BUG https://ltservices.atlassian.net/browse/RM-3163 4.0.95 - 10/06/2021
  @TEST_QRA-1110
  Scenario: Dont allow send a GIF PHOTO as a ephemeral photo
    Given login with username or email "qa+sirenhy99@mektoube.fr" and password "mektoube"
    When click "messageIcon" in menu bar
    When click thread message index 1 in thread list
    When Wait until display element by xpath "photoIcon"
    When click button by xpath "photoIcon"
    When I click button has text "Choisir dans la bibliothèque"
#    When click button by xpath "allowPermission"
    And I choose "allow" permission voice
#    When wait display button "galleryIcon" and click
    When wait display button "favouriteAlbum" and click
    When wait about 2 seconds
    When Click photo index 0 in gallery
#    When Wait until display element by xpath "previewPhoto"
#    When Select time view "5s"
#    When click button by xpath "sendPhotoBtn"
    When The page show message contain text "Fichier invalide, choisir un fichier jpg, jpeg, bmp ou png"

  @TEST_QRA-1114
  Scenario: Report a ephemeral photo
    Given login with username or email "qa+sirenhy99@mektoube.fr" and password "mektoube"
    When Make sure dont block user "Ngoctrinh89"
    When Search user by pseudo "Ngoctrinh89" and go to conversation
    When Send a ephemeral photo
    When click button by xpath "backBtnInConversation"
    When Logout current account and login account "qa+ngoctrinh89@mektoube.fr" and password "mektoube"
    When wait display button "messageIcon" and click
    Then last message in thread 1 is "Message image" message
    When click thread message index 1 in thread list
    When View newest ephemeral photo in conversation
    When click three dot felling report reply button in message index 1
    When click button by xpath "reportMessageButton"
    When I click button has text "VALIDER"
    Then The page show message contain text "Votre signalement a été pris en compte"
    When wait about 2 seconds
    When click three dot felling report reply button in message index 1
    When click button by xpath "reportMessageButton"
    When I click button has text "VALIDER"
    Then The page show message contain text "La photo a déjà été signalée"
    When wait about 2 seconds

  @TEST_QRA-1218
  Scenario: Dont allow permission access to photos in phone - when send ephemeral photo
    Given login with username or email "qa+sirenhy99@mektoube.fr" and password "mektoube"
    When click "messageIcon" in menu bar
    When click thread message index 1 in thread list
#    deny don't ask again photo permission
    When Wait until display element by xpath "photoIcon"
    When click button by xpath "photoIcon"
    When I click button has text "Choisir dans la bibliothèque"
    When click button by xpath "denyPermission"
    When click button by xpath "photoIcon"
    When I click button has text "Choisir dans la bibliothèque"
    When click button by xpath "doNotAskCheckBox"
    When click button by xpath "denyPermission"
#    When The page show message contain text "vos photos pour Mektoube"
    When Alert message with content "Veuillez activer l'accès à vos photos pour Mektoube" is showed on top
#    When click button by xpath "photoIcon"
#    When I click button has text "Choisir dans la bibliothèque"
#    When click button by xpath "denyAndDoNotAskPermission"
#    When wait about 1 seconds
#    When The page show message contain text "Veuillez activer l"
#    When click button by xpath "photoIcon"
#    When I click button has text "Choisir dans la bibliothèque"
#    When The page show message contain text "vos photos pour Mektoube"

  @TEST_QRA-1219
  Scenario: Dont allow permission access to camera in phone - when send ephemeral photo
    Given login with username or email "qa+sirenhy99@mektoube.fr" and password "mektoube"
    When click "messageIcon" in menu bar
    When click thread message index 1 in thread list
    When Wait until display element by xpath "photoIcon"
    When click button by xpath "photoIcon"
    When I click button has text "Prendre une photo"
    When click button by xpath "denyPermission"
    When click button by xpath "photoIcon"
    When I click button has text "Prendre une photo"
    When click button by xpath "doNotAskCheckBox"
    When click button by xpath "denyPermission"
#    When The page show message contain text "votre appareil photo pour Mektoube"
    When Alert message with content "Veuillez activer l'accès à votre appareil photo pour Mektoube" is showed on top
#    When click button by xpath "photoIcon"
#    When I click button has text "Prendre une photo"
#    When click button by xpath "denyAndDoNotAskPermission"
#    When The page show message contain text "votre appareil photo pour Mektoube"
#    When click button by xpath "photoIcon"
#    When I click button has text "Prendre une photo"
#    When The page show message contain text "votre appareil photo pour Mektoube"

  @TEST_QRA-1220
  Scenario: Reply a ephemeral photo by a text
    Given login with username or email "qa+sirenhy99@mektoube.fr" and password "mektoube"
    When Search user by pseudo "Ngoctrinh89" and go to conversation
    When Send a ephemeral photo
    When click button by xpath "backBtnInConversation"
    When Logout current account and login account "qa+ngoctrinh89@mektoube.fr" and password "mektoube"
    When click "messageIcon" in menu bar
    Then last message in thread 1 is "Message image" message
    When click thread message index 1 in thread list
    When wait display button "newPhotoMessageInChat" and click
    When Wait until display element by xpath "previewPhoto"
    When Wait until display element by xpath "viewedPhoto"
    Then Show viewed time on photo and cannot view again
    When click three dot felling report reply button in message index 1
    When click button by xpath "replyMessageButton"
    When Send a text message "Reply ephemeral photo done"

  @TEST_QRA-1304
  Scenario: View a ephemeral photo check name partner/send time
    Given login with username or email "qa+sirenhy99@mektoube.fr" and password "mektoube"
    When Search user by pseudo "Ngoctrinh89" and go to conversation
    When Send a ephemeral photo
    When click button by xpath "backBtnInConversation"
    When Logout current account and login account "qa+ngoctrinh89@mektoube.fr" and password "mektoube"
    When Search user by pseudo "Sirenhy99" and go to conversation
    When wait display button "newPhotoMessageInChat" and click
    When Wait until display element by xpath "previewPhoto"
    When Check name partner "Sirenhy99" and send photo time
    When Wait until display element by xpath "viewedPhoto"
    Then Show viewed time on photo and cannot view again

  @TEST_QRA-1305
  Scenario: Click close (X) button when view a photo
    Given login with username or email "qa+sirenhy99@mektoube.fr" and password "mektoube"
    When Search user by pseudo "Ngoctrinh89" and go to conversation
    When Send a ephemeral photo
    When click button by xpath "backBtnInConversation"
    When Logout current account and login account "qa+ngoctrinh89@mektoube.fr" and password "mektoube"
    When Search user by pseudo "Sirenhy99" and go to conversation
    When wait display button "newPhotoMessageInChat" and click
    When Wait until display element by xpath "previewPhoto"
    When click "closeModal" button
    When Wait until display element by xpath "viewedPhoto"
    Then Show viewed time on photo and cannot view again

  @TEST_QRA-1948
  Scenario: Send a big image >> check send and view success
    Given login with username or email "qa+sirenhy99@mektoube.fr" and password "mektoube"
    When click "messageIcon" in menu bar
    When click thread message index 1 in thread list
    When Wait until display element by xpath "photoIcon"
    When click button by xpath "photoIcon"
    When I click button has text "Choisir dans la bibliothèque"
#    When click button by xpath "allowPermission"
    And I choose "allow" permission voice
#    When wait display button "galleryIcon" and click
    When wait display button "favouriteAlbum" and click
    When wait about 2 seconds
    When Click photo index 3 in gallery
    When Wait until display element by xpath "previewPhoto"
    When Select time view "5s"
    When click button by xpath "sendPhotoBtn"
    When Wait until display element by xpath "sentLastPhoto"
    When message is send realtime

