package edu.wpi.teamA.controllers.OfficeMove;

import edu.wpi.teamA.navigation.Navigation;
import edu.wpi.teamA.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;

public class OfficeMoveController {
  @FXML MFXButton backButton;

  @FXML
  public void initialize() {}

  public void goBack() {
    backButton.setOnMouseClicked(event -> Navigation.navigate(Screen.HOME));
  }
}
