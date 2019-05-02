package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Warning {
  private static Button exitButton;
  private static Button cancel;
  static boolean anwser;

  public static Boolean display(String Title, String message, boolean closeProgram) {
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);
    window.setTitle("WARNING!");
    window.setMinWidth(300.0);
    window.setMinHeight(300.0);


    // setting a label
    Label warning = new Label();
    warning.setText(message);

    VBox layout = new VBox();
    if (closeProgram == true) {
      cancel = new Button("cancel");
      exitButton = new Button("yes, exit anyways");
      layout.getChildren().addAll(warning, cancel, exitButton);


      cancel.setOnAction(e -> {
        anwser = false;
        window.close();
      });

      exitButton.setOnAction(e -> {
        anwser = true;
        window.close();
      });
    } else {
      cancel = new Button("close");
      layout.getChildren().addAll(warning, cancel);
      
      cancel.setOnAction(e -> {
        anwser = false;
        window.close();
      });
    }

    layout.setSpacing(10.0);
    layout.setAlignment(Pos.CENTER);
    Scene scene = new Scene(layout);
    window.setScene(scene);
    window.showAndWait();

    return anwser;
  }

}
