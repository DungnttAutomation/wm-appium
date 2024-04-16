Feature: Notifications

  @draft
  Scenario Outline: Filter notifications by options
    Given login with username or email "qa+rose@mektoube.fr" and password "mektoube"
    When the user click on "notificationMenu"
    When click on the filter icon
    When click "<OptionFilter>" button
    Then The notifications page shows the "<results>" of the filter that the user selected before
    Examples:
      | OptionFilter             | results          |
      | All                      | AllIcon          |
      | Smiles                   | smileIcon        |
      | Visites                  | visitesIcon      |
      | Rendez-vous virtuel      | virtualIcon      |
      | Info Mektoube            | InfoMekIcon      |
      | Signalement Mektoube     | SignatureMekIcon |

