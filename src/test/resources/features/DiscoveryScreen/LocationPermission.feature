Feature: Location Permission

  @QRA-1456
  Scenario: Choose deny and go to search box and allow location.
    Given I login with account "qa+sirenhy99@mektoube.fr" and "mektoube" and "deny" permission
    When click button "searchBtn"
    And click button "btnBlueActiveLocation"
    And Display modal ask location and choose "allow"
    Then Display search box have tag "Autour de moi"
    When click button "btnValidate"
    Then show result seach location

    @QRA-1457
    Scenario: Choose deny and go to search box and deny location.
      Given I login with account "qa+sirenhy99@mektoube.fr" and "mektoube" and "deny" permission
      When click button "searchBtn"
      And click button "btnBlueActiveLocation"
      When Display modal ask location and choose "deny"
      Then Don't hide blue button active location and when click again show modal ask location

  @QRA-1458
  Scenario: Choose deny and go to search, don't ask again then allow again (on Setting).
    Given I login with account "qa+sirenhy99@mektoube.fr" and "mektoube" and "deny" permission
    When click button "searchBtn"
    And click button "btnBlueActiveLocation"
    When Display modal ask location and choose "don't ask again"
    And click button "btnBlueActiveLocation"
    When click button "btnActiveModal"
    When Redirect to setting
    When enable permission location again and click back to app
    And click button "btnBlueActiveLocation"
    Then Display search box have tag "Autour de moi"
    When click button "btnValidate"
    Then show result seach location

  @QRA-1459
  Scenario: Choose allow when login success.
    Given I login with account "qa+sirenhy99@mektoube.fr" and "mektoube" and "allow" permission
    Then show result seach location
