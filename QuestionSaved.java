package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class QuestionSaved {
   Scene scene1;  
       QuestionSaved(Stage window, Scene scene) {
         HBox hb = new HBox();
         hb.setPadding(new Insets(20, 50, 50, 50));
         hb.setSpacing(10);
         Button b2 = new Button("Back To Adding Question"); 
         b2.setOnAction(e -> {
           window.close();
         });
         hb.getChildren().addAll(b2); 
         scene1 = new Scene(hb); 
       }
      public Scene getScene() {
        return scene1;
        
      }
}