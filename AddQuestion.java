package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AddQuestion {
   Scene scene1; 
   String topic; 
       AddQuestion(Stage primaryStage, Scene scene) {

            VBox vb = new VBox();
            vb.setPadding(new Insets(100, 100, 100, 100));
            vb.setSpacing(10);

            Label topLabel = new Label("What Topic?");
            vb.getChildren().add(topLabel);

            ComboBox cb = new ComboBox();
            cb.getItems().add("Movies");
            cb.getItems().add("Video Games");
            cb.getItems().add("Food");
            vb.getChildren().add(cb);

            Button b1 = new Button("Create new Topic");
            Button b3 = new Button("Back To Menu");
         
            vb.getChildren().add(b1);
            vb.getChildren().add(b3); 
            b3.setOnAction(e -> primaryStage.setScene(scene));
            b1.setOnAction(e -> {
              topic = NewTopic.display("New Topic", "New Topic");
              cb.getItems().add(topic); 
            });
            
            
            Label label = new Label("Type Your Question");
         
            vb.getChildren().add(label);
            
            TextField tf = new TextField();
            vb.getChildren().add(tf);
            Button b2 = new Button("Add");
            vb.getChildren().add(b2);
            b2.setOnAction(e -> {
              AnwserType.display("Anwser Type", "Anwser Type");
            });
            // Adding VBox to the scene
            scene1 = new Scene(vb); 

       }
       public Scene getScene() {
        return scene1;
         
       }
    }
