package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TrueOrFalse {
   Scene scene1, questionSaved; 
   boolean answer; 
   AnswerNode a1, a2; 
   QuestionNode q1; 
       TrueOrFalse(Stage window, Scene scene, QuizGraph graph, String topic, String question) {
         VBox vb = new VBox();
         vb.setPadding(new Insets(20, 50, 50, 50));
         vb.setSpacing(10);
         Label topLabel = new Label("Answer");
         ComboBox cb = new ComboBox();
         cb.getItems().add(true); 
         cb.getItems().add(false); 
         Button b3 = new Button("Done"); 

         // Create action event 
         EventHandler<ActionEvent> event = 
                   new EventHandler<ActionEvent>() { 
             public void handle(ActionEvent e) 
             { 
               
               answer = (boolean) cb.getValue();
              
             } 
         }; 
         
         // Set on action 
         cb.setOnAction(event);
         b3.setOnAction(e -> {
           a1 = new AnswerNode("true", answer);
           a2 = new AnswerNode("false", answer);
           q1 = new QuestionNode(question,a1,a2,true); 
           graph.addQuestion(topic, q1);
           questionSaved = new QuestionSaved(window, scene).getScene(); 
           window.setScene(questionSaved);
         });
         vb.getChildren().addAll(topLabel,cb,b3); 
         scene1 = new Scene(vb); 
       }
      public Scene getScene() {
        return scene1;
        
      }
}
