@QRA-1385
Feature: Favorites List

  """account
    Male: Dennis99
  """
  Background: Open the Home Page
    When login with username or email "qa+dennis99@mektoube.fr" and password "mektoube"

#  @QRA-1062

  @QRA-1063
  Scenario: Add a profile in the favorites
    When click on first profile in discovery
    When the user click on "optionIconProfile"
    And add them into favorites by choose "addToFavoritesBtn"
    When the user click on "myAccountMenuBtn"
    When the user click on "favoritesTab"
    When scroll All page "profileItemFavorite"
    Then the user should see them in favorites list

  @QRA-1064
  Scenario: Remove a profile that existed by visiting a profile from the favorites list
    When click on first profile in discovery
    When the user click on "optionIconProfile"
    And add them into favorites by choose "addToFavoritesBtn"
    When visiting a profile from the favorites Tab
    When the user click on "optionIconProfile"
    When remove them from favorites by choose "removeFromFavoritesBtn"
    When back to previous page
    Then remove that profile successfully

  @QRA-1065
  Scenario: Remove a profile that existed by swipe left from the favorites list
    When click on first profile in discovery
    When the user click on "optionIconProfile"
    And add them into favorites by choose "addToFavoritesBtn"
    When the user click on "myAccountMenuBtn"
    When the user click on "favoritesTab"
    When delete a profile from favorites list by swipe left and click on "deleteSwipeLeftBtn"
    Then remove that profile successfully

  @QRA-1066
  Scenario: Cannot add their profile to your favorites while you've blocked them from message
    When the user visiting that first profile from message
    When the user click on "optionIconProfile"
    And add them into blacklist by choose "blockBtn"
    When wait about 5 seconds
    When the user click on "optionIconProfile"
    And the user click on "addToFavoritesBtn"
    Then Alert message with content "Personne bloquée - Ajout à vos favoris impossible" is showed on top

  @QRA-1067
  Scenario Outline: Filter favorites list by all and online
    When the user click on "myAccountMenuBtn"
    When the user click on "favoritesTab"
    When the user filter click on filter option
    When choose "<option>"
    Then the "<results>" should be shown
    Examples:
      | option      | results             |
      | Tout        | toutValidate        |
      | En ligne    | onlineAndNoOnline   |




