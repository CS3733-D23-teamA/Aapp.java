package edu.wpi.teamA.controllers;

import edu.wpi.teamA.navigation.Navigation;
import edu.wpi.teamA.navigation.Screen;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ConferenceController {
  @FXML MFXButton backButton;
  @FXML MFXButton submitButton;
  @FXML MFXButton clearButton;
  @FXML TextField name_input;
  String name;
  int date;
  int start;
  int end;
  int numPeople;
  String additionalRequests;

  @FXML
  public void intitialize() {}

  public void goBack() {
    // should be inherited through home page
    Navigation.navigate(Screen.HOME);
  }

  public void submitForm() {
    // send form information to database
    name = name_input.getText();

    System.out.println("Form submitted by: " + name);
    Navigation.navigate(Screen.SERVICE_REQUEST);
  }

  public void clearForm() {
    // clear form
  }
}
