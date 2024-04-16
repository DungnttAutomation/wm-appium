Feature: Age Filter

  @test
  Scenario: A female - active Age Filter
    When login with username or email "qa+rose@mektoube.fr" and password "mektoube"
    When the user actives the Age Filter
    Then should see alert message with content "Vos préférences ont été mises à jour" is showed on top

  @test
  Scenario: A female - inactive Photo Filter
    When the user logout successfully
    When login successfully with the username "qa+ladygaga@mektoube.fr" and password "mektoube"
    When the user inactive the Age Filter
    Then should see alert message with content "Vos préférences ont été mises à jour" is showed on top

  @test
  Scenario: A female - show blue alert when that profile is out of filter
    When the user logout successfully
    When login successfully with the username "qa+ladygaga@mektoube.fr" and password "mektoube"
    When the user actives the Age Filter
    When should see alert message with content "Vos préférences ont été mises à jour" is showed on top
    When the user searches the "montassir931" profile
    When the user click on "firstProfileFromDiscovery"
    Then should see "outOfAgeFilterBlueAlert" modal

  @
  Scenario: A female - the man is out of the filter can be smile unlimited when active Age Filter
    When the user logout successfully
    When login successfully with the username "qa+alexvause@mektoube.fr" and password "mektoube123"
    When the user actives the Age Filter
    When should see alert message with content "Vos préférences ont été mises à jour" is showed on top
    When the user logout successfully
    When login successfully with the username "montassir931" and password "Mektoubi2017"
    When the user searches the "alexvause" profile
    When the user click on "firstProfileFromDiscovery"
    When the user click on "btnSmile"





