@QRA-1470
Feature: My Profile

  #check lai toan bo compare
  @QRA-1471
  Scenario: Upload photo from phone's gallery in my profile
    When login with username or email "qa+rose@mektoube.fr" and password "mektoube"
    When the user click on "myProfileBtn"
    When the user choose "picture02" from a device to post it
    When the user click on "closeViewPhotoIcon"

  @QRA-1472
  Scenario: Upload photo by take a photo in my profile
    When login with username or email "qa+alexvause@mektoube.fr" and password "mektoube123"
    When the user click on "myProfileBtn"
    When the user take a photo from device and post it
    When the user click on "closeViewPhotoIcon"

  @QRA-1473
  Scenario: Upload photo from phone's gallery in edit my profile
    When login with username or email "qa+rose@mektoube.fr" and password "mektoube"
    When the user click on "myProfileBtn"
    When the user click on "editMyProfileBtn"
    When the user choose "picture01" from a device to post it
    When the user click on "closeViewPhotoIcon"
    When make sure the app isn't crashed

  @QRA-1474
  Scenario: Upload photo by take a photo in edit my profile
    When login with username or email "qa+rose@mektoube.fr" and password "mektoube"
    When the user click on "myProfileBtn"
    When the user click on "editMyProfileBtn"
    When the user take a photo from device and post it
    When the user click on "closeViewPhotoIcon"
    When make sure the app isn't crashed

  @QRA-1475
  Scenario: Upload photo from phone's gallery in edit my criteria
    When login with username or email "qa+rose@mektoube.fr" and password "mektoube"
    When the user click on "myProfileBtn"
    When the user click on "editMyCriteriaBtn"
    When the user choose "picture01" from a device to post it
    When the user click on "closeViewPhotoIcon"
    When make sure the app isn't crashed

  @QRA-1476
  Scenario: Upload photo by take a photo in edit my criteria
    When login with username or email "qa+rose@mektoube.fr" and password "mektoube"
    When the user click on "myProfileBtn"
    When the user click on "editMyCriteriaBtn"
    When the user take a photo from device and post it
    When the user click on "closeViewPhotoIcon"
    When make sure the app isn't crashed

  Scenario: Edit my profile
#    When Create account with email "mektoubitest2@gmail.com" username "theorin" pass "mektoube" success and go to discovery
    When login with username or email "qa+toptop@mektoube.fr" and password "mektoube"
    When the user click on "myProfileBtn"
    When the user click on "FERMER"
    When the user click on "editMyProfileBtn"
    When do not allow users to validation all fields left blank