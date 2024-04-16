Feature:As a mektoube user
  I want to navigate to Discovery page and filter
  Background:
#  Given I change enviroment to "preprodEnvironment"

  @TEST_QRA-1020
  Scenario: I filter user online
    Given I login with account "yassine_yes2" and "Mektoubi2017" and "allow" permission
    When wait display button "searchBtn" and click
    When click button "btnEnligne"
    Then Display search box have tag "tagEnligne"
    When click button "btnValidate"
    And wait about 3 seconds
    Then Redirect to Discovery page
    When I scroll up
    When click on the user profile
    Then See status of user
    When I scroll up
    When click on the user profile
    Then See status of user
    When I scroll up
    When click on the user profile
    Then See status of user
  #done on android

  @TEST_QRA-1021
  Scenario: I filter user "Around me"
    Given I login with account "yassine_yes2" and "Mektoubi2017" and "allow" permission

    When wait display button "searchBtn" and click
    When I click on slider "sldAround" around 500
    Then Display on the search box have tag "tagAroundMe"
    When click button "btnValidate"
    And wait about 2 seconds
    When Redirect to Discovery page
    When click on the user profile
#    Then The distance between two accounts in the range matches the filter
  #done on android

  @TEST_QRA-1022
  Scenario: I filter user have birthday today
    Given I login with account "yassine_yes2" and "Mektoubi2017" and "allow" permission

    When wait display button "searchBtn" and click
    When I scroll up to "Critères avancés" text
    When click button "btnBirthday"
    Then Display search box have tag "tagBirthday"
    When click button "btnValidate"
    And wait about 2 seconds
    When Redirect to Discovery page
    When click on the user profile
    Then Should see text "textBirthday"
    #done on android

  @TEST_QRA-1023
  Scenario: I filter user "Avec photo"
    Given I login with account "yassine_yes2" and "Mektoubi2017" and "allow" permission

    When wait display button "searchBtn" and click
    When I scroll up to "Critères avancés" text
    When click button "btnAdvanced"
    When click button "btnAvecPhoto"
    Then Display search box have tag "tagAvecPhoto"
    When click button "btnValidate"
    And wait about 3 seconds
    When Redirect to Discovery page
    When click on the user profile
    Then I click "btnPhotoProfile" to see photo of user
    And wait about 2 seconds
    #done on android

  @TEST_QRA-1024
  Scenario: I filter users by "Origin"
    Given I login with account "yassine_yes2" and "Mektoubi2017" and "allow" permission

    When wait display button "searchBtn" and click
    When I scroll up to "Critères avancés" text
    When click button "btnAdvanced"
    When click button "btnOrigine"
    And I click tick button "btnTick"
    And display error when don't select and click validate
#    When choose option "optFrance"
    When choose option "optAfghanistan"
    And I click tick button "btnTick"
    Then Display search box have tag "tagOrigine"
    When click button "btnValidate"
    And wait about 3 seconds
    When Redirect to Discovery page
    When click on the user profile
    And I scroll up to "Origine :" text
    Then See "Origine" of user account value "Afghanistan"
    #done on android
  @TEST_QRA-1025
  Scenario: I filter users by "Pays"
    Given I login with account "yassine_yes2" and "Mektoubi2017" and "allow" permission

    When wait display button "searchBtn" and click
    When I scroll up to "Critères avancés" text
    When click button "btnAdvanced"
    When choose option "btnPays"
    When choose option "optFrance"
    And I click tick button "btnTick"
    When choose search region
    Then Display search box have tag "tagPays"
    When click button "btnValidate"
    And wait about 3 seconds
    When Redirect to Discovery page
    When click on the user profile
    Then See pays of user account
    #done on android
  @TEST_QRA-1026
  Scenario: I filter user by "Taille"
    Given I login with account "yassine_yes2" and "Mektoubi2017" and "allow" permission

    When wait display button "searchBtn" and click
    When I scroll up to "Critères avancés" text
    When click button "btnAdvanced"
    When I scroll up to "Taille" text
    When click button "btnTaille"
    And I handle slider "sldTailleMax" and "sldTailleMin"
    And I click tick button "btnTick"
    And Get value filter 0 and 6
    When click button "btnValidate"
    And wait about 3 seconds
    When Redirect to Discovery page
    When click on the user profile
    Then See taille of user account
    And click button "backAfterScrollProfile"
    And I scroll up
    When click on the user profile
    Then See taille of user account
    And click button "backAfterScrollProfile"
    And I scroll up
    When click on the user profile
    Then See taille of user account
    #done on android
  @TEST_QRA-1027
  Scenario: I filter user "Age"
    Given I login with account "yassine_yes2" and "Mektoubi2017" and "allow" permission

    When wait display button "searchBtn" and click
    When I scroll up to "Critères avancés" text
    When click button "btnAdvanced"
    When click button "btnAge"
    When I click on slider "sldAge" around 500
    And I click tick button "btnTick"
    Then Display search box have tag "tagAge"
    And Get value filter age 0 and 5
    When click button "btnValidate"
    And wait about 2 seconds
#    When Redirect to Discovery page
    When click on the user profile
    Then See age of user account
    #done on android
  @TEST_QRA-1029
  Scenario: I filter user "Silhouette"
    Given I login with account "yassine_yes2" and "Mektoubi2017" and "allow" permission

    When wait display button "searchBtn" and click
    When I scroll up to "Critères avancés" text
    When click button "btnAdvanced"
    When I scroll up to "Silhouette" text
    When click button "btnSilhouette"
    And I click tick button "btnTick"
    Then display error must select one option in field search
    And select any multi option
    And I click tick button "btnTick"
    Then Display search box have tag "tagSilhouette"
    When click button "btnValidate"
    And wait about 3 seconds
    Then the page show all profile that have above Silhouette
    #done on android
  @TEST_QRA-1030
  Scenario: I filter user "Situation familiale"
    Given I login with account "yassine_yes2" and "Mektoubi2017" and "allow" permission

    When wait display button "searchBtn" and click
    When I scroll up to "Critères avancés" text
    When click button "btnAdvanced"
    When I scroll up to "Enfants" text
    When click button "btnSituationFamiliale"
    And I click tick button "btnTick"
    Then display error must select one option in field search
    And select any multi option
    And I click tick button "btnTick"
    Then Display search box have tag "tagSituationFamiliale"
    When click button "btnValidate"
    And wait about 3 seconds
    Then the page show all profile that have above Silhouette Familiale
    #done on android
  @TEST_QRA-1031
  Scenario: I filter user "Enfants"
    Given I login with account "yassine_yes2" and "Mektoubi2017" and "allow" permission

    When wait display button "searchBtn" and click
    When I scroll up to "Critères avancés" text
    When click button "btnAdvanced"
    When I scroll up to "Pseudo" text
    When click button "btnEnfants"
    And I click tick button "btnTick"
    Then display error must select one option in field search
    And select any multi option
    And I click tick button "btnTick"
    Then Display search box have tag "tagEnfants"
    When click button "btnValidate"
    And wait about 3 seconds
    Then the page show all profile that have above Enfants
    #in progress
  @TEST_QRA-1032
  Scenario: I filter user "Pratiquant"
    Given I login with account "yassine_yes2" and "Mektoubi2017" and "allow" permission

    When wait display button "searchBtn" and click
    When I scroll up to "Critères avancés" text
    When click button "btnAdvanced"
    When I scroll up to "Pseudo" text
    When click button "btnPratiquant"
    When choose option "optOui"
    And I click tick button "btnTick"
    Then Display search box have tag "tagPratiquant"
    When click button "btnValidate"
    And wait about 2 seconds
    When Redirect to Discovery page
    When click on the user profile
    And I scroll up to "Pratiquant :" text
    Then See "Pratiquant" of user account value "Dans le Din"
    When click button "backAfterScrollProfile"
    And I scroll up
    When click on the user profile
    And I scroll up to "Pratiquant" text
    Then See "Pratiquant :" of user account value "Dans le Din"
    When click button "backAfterScrollProfile"
    And I scroll up
    When click on the user profile
    And I scroll up to "Pratiquant :" text
    Then See "Pratiquant" of user account value "Dans le Din"
    #done on android
  @TEST_QRA-1033
  Scenario: I filter user "Fumeur"
    Given I login with account "yassine_yes2" and "Mektoubi2017" and "allow" permission

    When wait display button "searchBtn" and click
    When I scroll up to "Critères avancés" text
    When click button "btnAdvanced"
    When I scroll up to "Pseudo" text
    When click button "btnFumeur"
    And I click tick button "btnTick"
    Then display error must select one option in field search
    And select any multi option
    And I click tick button "btnTick"
    Then Display search box have tag "tagFumeur"
    When click button "btnValidate"
    And wait about 3 seconds
    Then the page show all profile that have above Fumeur
    #done on android
  @TEST_QRA-1034
  Scenario: I filter user "Niveau d'études"
    Given I login with account "yassine_yes2" and "Mektoubi2017" and "allow" permission

    When wait display button "searchBtn" and click
    When I scroll up to "Critères avancés" text
    When click button "btnAdvanced"
    When I scroll up to "Pseudo" text
    When click button "btnNiveau"
    And I click tick button "btnTick"
    Then display error must select one option in field search
    And select any multi option
    And I click tick button "btnTick"
    Then Display search box have tag "tagNiveau"
    When click button "btnValidate"
    And wait about 3 seconds
    Then the page show all profile that have above Niveau
  @TEST_QRA-1035
  Scenario: I filter user "Pseudo"
    Given I login with account "yassine_yes2" and "Mektoubi2017" and "allow" permission

    When wait display button "searchBtn" and click
    When I scroll up to "Critères avancés" text
    When click button "btnAdvanced"
    When I scroll up to "Pseudo" text
    When click button "btnPseudo"
    When I enter "name" on field
    And I click tick button "btnTick"
    Then Display search box have tag "tagPseudo"
    When click button "btnValidate"
    And wait about 2 seconds
    When Redirect to Discovery page
    When click on the user profile
    Then See name of user
    #done on android
  @TEST_QRA-1036
  Scenario: I filter user by "Avec photo","Age" and "Pseudo"
    Given I login with account "yassine_yes2" and "Mektoubi2017" and "allow" permission

    When wait display button "searchBtn" and click
    When I scroll up to "Critères avancés" text
    When click button "btnAdvanced"
    When click button "btnAvecPhoto"
    When I choose filter age
    When I choose filter pseudo
    Then Display search box have tag "tagPseudo"
    And Display search box have tag "tagAvecPhoto"
    And Display search box have tag "tagAge"
    And Get value filter age 0 and 5
    When click button "btnValidate"
    And wait about 2 seconds
    When Redirect to Discovery page
    When click on the user profile
    Then See name of user
    And I click "btnPhotoProfile" to see photo of user
    And See age of user account
    #done on android
  @TEST_QRA-1039
  Scenario: I filter user by "Origin","Age" and "Pseudo"
    Given I login with account "yassine_yes2" and "Mektoubi2017" and "allow" permission

    When wait display button "searchBtn" and click
    When I scroll up to "Critères avancés" text
    When click button "btnAdvanced"
    When I choose filter age
    When I choose filter origine
    When I choose filter pseudo
    Then Display search box have tag "tagPseudo"
    And Display search box have tag "tagOrigine"
    And Display search box have tag "tagAge"
    And Get value filter age 0 and 5
    When click button "btnValidate"
    And wait about 2 seconds
    When Redirect to Discovery page
    When click on the user profile
    Then See name of user
    And I scroll up to "Origine :" text
    Then See "Origine" of user account value "France"
    And See age of user account
    #done on android
  @TEST_QRA-1040
  Scenario: I filter user by "Fumeuse" and "Niveau"
    Given I login with account "yassine_yes2" and "Mektoubi2017" and "allow" permission

    When wait display button "searchBtn" and click
    When I scroll up to "Critères avancés" text
    When click button "btnAdvanced"
    When I choose filter fumeuse
    When I choose filter niveau
    Then Display search box have tag "tagFumeur"
    And Display search box have tag "tagNiveau"
    When click button "btnValidate"
    When click on the user profile
    Then See profile of account
    #done on android
  @TEST_QRA-1041
  Scenario: I filter at save filter
    Given I login with account "yassine_yes2" and "Mektoubi2017" and "allow" permission

    When wait display button "searchBtn" and click
    When I filter pseudo
    When I click save filter and enter name save filter
    And wait about 2 seconds
    When wait display button "searchBtn" and click
    And wait about 2 seconds
    Then Display search box have tag "tagPseudo"
    When click button "btnSaveFilter"
    When I click on first save filter
    And wait about 2 seconds
    When click on the user profile
    Then See name of user
    When click button "btnBackProfile"
    When click button "searchBtn"
    Then Remove save filter
    And wait about 2 seconds
    #done on android
  @TEST_QRA-1042
  Scenario: I rename save filter
    Given I login with account "yassine_yes2" and "Mektoubi2017" and "allow" permission

    When wait display button "searchBtn" and click
    When click button "btnSaveFilter"
    When I rename save filter
    Then See new name of save filter
    #done on android
  @TEST_QRA-1043
  Scenario: I enter blank into pseudo field
    Given I login with account "yassine_yes2" and "Mektoubi2017" and "allow" permission

    When wait display button "searchBtn" and click
    When I scroll up to "Critères avancés" text
    When click button "btnAdvanced"
    When I scroll up to "Pseudo" text
    When click button "btnPseudo"
    And wait about 2 seconds
    When I click tick button "btnTick"
    Then See error message
    When I enter "@@@@" on field
    When I click tick button "btnTick"
    Then show red error message when enter invalid pseudo

    #done on android
  @TEST_QRA-1050
  Scenario: Reset filter
    Given I login with account "yassine_yes2" and "Mektoubi2017" and "allow" permission

    When wait display button "searchBtn" and click
    When I filter pseudo
    When wait display button "searchBtn" and click
    When click button "btnReset"
    Then Click button search on discovery page and don't display search box
    When wait display button "searchBtn" and click
    When I filter pseudo
    And I scroll up
    And wait about 1 seconds

  @TEST_QRA-1934
  Scenario: Filter Pilosité (only female user)
    Given I login with account "qa+anie@mektoube.fr" and "mektoube" and "allow" permission

    When wait display button "searchBtn" and click
    When I scroll up to "Critères avancés" text
    When click button "btnAdvanced"
    And filter man with criteria pilosite
    Then Display search box have tag "Pilosit"
    When click button "btnValidate"
    Then the page show all profile that have above Pilosité

  @TEST_QRA-1935
  Scenario: Filter Le voile (only male user)
    Given I login with account "qa+sirenhy99@mektoube.fr" and "mektoube" and "allow" permission

    When wait display button "searchBtn" and click
    When I scroll up to "Critères avancés" text
    When click button "btnAdvanced"
    And filter man with criteria le voile
    Then Display search box have tag "Le voile"
    When click button "btnValidate"
    Then the page show all profile that have above Le voile

  @TEST_QRA-2004
  Scenario: Click save search after scroll, enter empty name, remove filter and click back to discovery
    Given I login with account "qa+sirenhy99@mektoube.fr" and "mektoube" and "allow" permission

    When I scroll up
    And wait about 1 seconds
    And wait display button "saveSearchAfterScrollDiscovery" and click
    Then click green button show error empty name save search
    When I scroll up
    And wait about 1 seconds
    And wait display button "filterAfterScrollDiscovery" and click
    When remove all criteria at search box
    Then click back at search form back to discovery








