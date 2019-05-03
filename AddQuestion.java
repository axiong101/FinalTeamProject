package application;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddQuestion {
   Scene scene1; 
   static String topic; 
   static FileChooser fileChooser = new FileChooser();
   static Desktop desktop = Desktop.getDesktop();
   static Image image; 
   public static QuizGraph display(Stage primaryStage, QuizGraph graph) {
    
         Stage window = new Stage();
         window.initModality(Modality.APPLICATION_MODAL);
         window.setTitle("Add Question");
         window.setMinWidth(250);

            VBox vb = new VBox();
            vb.setPadding(new Insets(100, 100, 100, 100));
            vb.setSpacing(10);

            Label topLabel = new Label("What Topic?");
            vb.getChildren().add(topLabel);

            ComboBox<String> cb = new ComboBox();
            cb.getItems().addAll(graph.getAllTopics()); 
            vb.getChildren().add(cb);

            Button b1 = new Button("Create new Topic");
            Button b3 = new Button("Back To Menu");
            Button openButton = new Button("Add A Picture");
            vb.getChildren().addAll(b1,b3,openButton); 
            b3.setOnAction(e -> window.close());
            b1.setOnAction(e -> {
              topic = NewTopic.display("New Topic", "New Topic");
              if(topic.equals("")) {
                return; 
              }
              cb.getItems().add(topic); 
              graph.addTopic(topic); 
            });
            
            openButton.setOnAction(
                new EventHandler<ActionEvent>() {
                  @Override
                  public void handle(final ActionEvent e) {
                      File file = fileChooser.showOpenDialog(primaryStage);
                      if (file != null) {
                         image = new Image(file.toURI().toString());
                         Warning.display("Success", "Sucessfully Saved Image To Question", false); 
                      } 
                  }
              });
            
            
            Label label = new Label("Type Your Question");
         
            vb.getChildren().add(label);
            
            TextField tf = new TextField();
            vb.getChildren().add(tf);
            Button b2 = new Button("Add");
            vb.getChildren().add(b2);
            b2.setOnAction(e -> {
              if(tf.getText().isEmpty() || cb.getValue() == null) {
                Warning.display("Warning", "Question field or topic selection is blank, please enter a question or select a topic", false);
              return; 
              }
              String question = tf.getText(); 
              String userTopic = cb.getValue(); 
              
              AnwserType.display("Anwser Type", "Anwser Type", graph, userTopic, question, image);
        
            });
            // Adding VBox to the scene
            Scene scene1 = new Scene(vb);
            window.setScene(scene1);
            window.showAndWait();
           
          return graph; 
       }

    }
