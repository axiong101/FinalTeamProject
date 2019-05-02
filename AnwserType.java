package application;

import java.util.Scanner;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AnwserType {
 static Scene scene, multiple, trueOrFalse;

   public static void display(String title, String message) {
     Stage window = new Stage();
     
     window.initModality(Modality.APPLICATION_MODAL);
     window.setTitle(title);
     window.setMinWidth(250);
     
     Label label = new Label();
     label.setText(message);
     
   
     Button b1 = new Button("Multiple Choice Questions");
     Button b2 = new Button("True/False");
     b1.setOnAction(e -> {
      multiple = new MultipleChoice(window, scene).getScene();
      window.setScene(multiple); 
     });
      b2.setOnAction(e -> {
      trueOrFalse = new TrueOrFalse(window, scene).getScene();
       window.setScene(trueOrFalse); 
      });
     VBox layout = new VBox(10);
     layout.getChildren().addAll(label,b1, b2);
     layout.setAlignment(Pos.CENTER);
     
     scene = new Scene(layout);
     window.setScene(scene);
     window.showAndWait();
     
   }
}
