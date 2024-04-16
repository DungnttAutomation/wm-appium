@QRA-1260
Feature: Settings

  @QRA-1159
  Scenario: Change email - Correct password + invalid email address and should see red border
#    When login with username or email "qa+rose@mektoube.fr" and password "mektoube"
    When the user go to "Email" screen
    When the user should see border color red when input an invalid email address

  @QRA-1160
  Scenario: Change the email address invalid - alert error messages
#    When login with username or email "qa+rose@mektoube.fr" and password "mektoube"
    When the user go to "Email" screen
    When the user should see alert error messaging when input an invalid email address

  @QRA-1161
  Scenario Outline: Change the email address successfully then back to old data
    When login with username or email "<email>" and password "mektoube"
    When the user go to "Email" screen
    When clear text and insert text "<emailNeedsToBeChanged>" into field "newEmailTextField"
    When the user click on "registerEmailBtn"
    When Alert message with content "Un email de confirmation vous a été envoyé" is showed on top
    When the user logs out from current account
    Then login fail with an account has old data by email "<oldEmail>" and password "mektoube"
    Examples:
      |   email                     | emailNeedsToBeChanged     | oldEmail                  |
      |   qa+dennis99@mektoube.fr   | thaocutp@gmail.com        | qa+dennis99@mektoube.fr   |
      |   thaocutp@gmail.com        | qa+dennis99@mektoube.fr   | thaocutp@gmail.com        |

  @QRA-1162
  Scenario: Change password unsuccessfully - an error
    When login with username or email "qa+rose@mektoube.fr" and password "mektoube"
    When the user go to "Mot de Passe" screen
    When the user should see border color red when input password is invalid

  @QRA-1163
  Scenario: Change password unsuccessfully - the current password is wrong and new password valid
    When login with username or email "qa+rose@mektoube.fr" and password "mektoube"
    When the user go to "Changer votre mot de passe" screen
    When the user type "wrongPass" in "currentPasswordTextField2"
    When the user type "Mektoube2017" in "newPasswordTextField"
    When the user click on "confirmChangePassBtn"
    Then should see alert message with content "Votre mot de passe actuel est incorrect" is showed on top

  @QRA-1164
  Scenario Outline: Change password successfully then back to old data
    When login with username or email "qa+dennis99@mektoube.fr" and password "<password>"
    When the user go to "Changer votre mot de passe" screen
    When clear text and insert text "<currentPassword>" into field "currentPasswordTextField2"
    When clear text and insert text "<newPassword>" into field "newPasswordTextField"
    When the user click on "confirmChangePassBtn"
#    When Alert message with content "Votre mot de passe a été mis à jour" is showed on top
    When the user logs out from current account
    Then login fail with an account has old data by email "qa+ladygaga@mektoube.fr" and password "<oldPassword>"
    Examples:
      |password     | currentPassword   | newPassword     | oldPassword       |
      |mektoube     | mektoube          | Mektoubi2017    | mektoube          |
      |Mektoubi2017 | Mektoubi2017      | mektoube        | Mektoube2017      |

  @QRA-1168
  Scenario: Send a photo to service client successfully
#    When login with username or email "qa+rose@mektoube.fr" and password "mektoube"
    When the user go to "Service Client" screen
    When the user select a "Inscription" label
    When clear text and insert text "Send a photo to service" into field "serviceTextField"
    When the user add "picture01" to send service client
    When the user click on "sendServiceBtn"
    Then should see alert message with content "Message envoyé au service client" is showed on top
#    When Alert message with content "Message envoyé au service client" is showed on top

  @QRA-1165
  Scenario: Send service client successfully when don't have attach a photo
#    When login with username or email "qa+rose@mektoube.fr" and password "mektoube"
    When the user go to "Service Client" screen
    When the user select a "Inscription" label
    When clear text and insert text "Test Service Client without photo" into field "serviceTextField"
    When make sure "screenshotAdded" do not display
    When the user click on "sendServiceBtn"
    Then should see alert message with content "Message envoyé au service client" is showed on top

  @QRA-1166
  Scenario: Cannot send service client when leave empty the label and the message
#    When login with username or email "qa+rose@mektoube.fr" and password "mektoube"
    When the user go to "Service Client" screen
    When wait until display "selectLabelService"
    When wait until display "serviceTextField"
    When the user click on "sendServiceBtn"
    When wait until display "labelEmptyError"
    When wait until display "textFieldEmptyError"

  @QRA-1468
  Scenario: Service Client - go to the gallery but do not choose an image, make sure image don't appear
#    When login with username or email "qa+rose@mektoube.fr" and password "mektoube"
    When the user go to "Service Client" screen
    When the user click on "chooseImageServiceText"
    When back to previous by using back button
    When make sure "screenshotAdded" do not display

  @QRA-1469
  Scenario: When a screenshot is added in service client, the user can delete the screenshot and add another image
#    When login with username or email "qa+rose@mektoube.fr" and password "mektoube"
    When the user go to "Service Client" screen
    When the user add "picture01" to send service client
    When the user click on "deleteScreenshotAddedIcon"
    When make sure "screenshotAdded" do not display
    When the user add "picture01" to send service client

  @QRA-1167
  Scenario: Close customer service
#    When login with username or email "qa+rose@mektoube.fr" and password "mektoube"
    When the user go to "Service Client" screen
    When the user click on "cancelServiceBtn"

  @QRA-1169
  Scenario: Go to CGU Menu
#    When login with username or email "qa+rose@mektoube.fr" and password "mektoube"
    When the user go to "CGU" screen
    Then should see CGU content
    When back to previous by using back button

  @QRA-1170
  Scenario: Go to Vie
#    When login with username or email "qa+rose@mektoube.fr" and password "mektoube"
    When the user go to "Vie" screen
    Then should see Vie Privee content
    When back to previous by using back button

  @QRA-1171
  Scenario: Go to Mentions légales
#    When login with username or email "qa+rose@mektoube.fr" and password "mektoube"
    When the user go to "Mentions" screen
    Then should see Mentions Legales content
    When back to previous by using back button

  @QRA-1172
  Scenario: Logout successfully
#    When login with username or email "qa+rose@mektoube.fr" and password "mektoube"
    When the user logout successfully