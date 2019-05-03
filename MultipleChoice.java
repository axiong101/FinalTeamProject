package application;

import java.awt.Window;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MultipleChoice {
  Scene scene1, questionSaved;
  AnswerNode a1, a2, a3, a4, a5;
  QuestionNode q1; 
  String answer1, answer2, answer3, answer4, answer5;
  HBox Hb1 = new HBox(2);
  HBox Hb2 = new HBox(2);
  HBox Hb3 = new HBox(2);
  HBox Hb4 = new HBox(2);
  HBox Hb5 = new HBox(2);
  boolean c1, c2, c3, c4, c5;
  String correctOrWrong[] = {"Correct", "Wrong"};
  ComboBox<String> cb1 = new ComboBox<String>(FXCollections.observableArrayList(correctOrWrong));
  ComboBox<String> cb2 = new ComboBox<String>(FXCollections.observableArrayList(correctOrWrong));
  ComboBox<String> cb3 = new ComboBox<String>(FXCollections.observableArrayList(correctOrWrong));
  ComboBox<String> cb4 = new ComboBox<String>(FXCollections.observableArrayList(correctOrWrong));
  ComboBox<String> cb5 = new ComboBox<String>(FXCollections.observableArrayList(correctOrWrong));
  
  MultipleChoice(Stage window, Scene scene, QuizGraph graph, String topic, String question) {
    VBox Vb = new VBox(7);
    Vb.setPadding(new Insets(20, 50, 50, 50));
    Vb.setSpacing(10);
    Label topLabel = new Label("Create your multiple choice");

    TextField tf1 = new TextField();
    
   
     TextField tf2 = new TextField();
    
     TextField tf3 = new TextField();
    
     TextField tf4 = new TextField();
     TextField tf5 = new TextField();

     Hb1.getChildren().addAll(tf1, cb1);
     Hb2.getChildren().addAll(tf2, cb2);
     Hb3.getChildren().addAll(tf3, cb3);
     Hb4.getChildren().addAll(tf4, cb4);
     Hb5.getChildren().addAll(tf5, cb5);
     

     // Create action event 
     EventHandler<ActionEvent> event1 = 
               new EventHandler<ActionEvent>() { 
         public void handle(ActionEvent e) 
         { 
           if(cb1.getValue().equals("Correct")) {
             c1 = true; 
           }else {
             c1 = false;
           }
         } 
     }; 
     // Create action event 
     EventHandler<ActionEvent> event2 = 
               new EventHandler<ActionEvent>() { 
         public void handle(ActionEvent e) 
         { 
           if(cb2.getValue().equals("Correct")) {
             c2 = true; 
           }else {
             c2 = false;
           }
         } 
     }; 
     // Create action event 
     EventHandler<ActionEvent> event3 = 
               new EventHandler<ActionEvent>() { 
         public void handle(ActionEvent e) 
         { 
           if(cb1.getValue().equals("Correct")) {
             c3 = true; 
           }else {
             c3 = false;
           }
         } 
     }; 
     // Create action event 
     EventHandler<ActionEvent> event4 = 
               new EventHandler<ActionEvent>() { 
         public void handle(ActionEvent e) 
         { 
           if(cb4.getValue().equals("Correct")) {
             c4 = true; 
           }else {
             c4 = false;
           }
         } 
     }; 
     
     EventHandler<ActionEvent> event5 = 
         new EventHandler<ActionEvent>() { 
   public void handle(ActionEvent e) 
   { 
     if(cb5.getValue().equals("Correct")) {
       c5 = true; 
     }else {
       c5 = false;
     }
   } 
}; 

     

     // Set on action 
     cb1.setOnAction(event1);
     cb2.setOnAction(event2);
     cb3.setOnAction(event3);
     cb4.setOnAction(event4);
    
     
     
    
    Button b1 = new Button("Done");
    Button b2 = new Button("Back");
    b1.setOnAction(e -> {
      answer1 = tf1.getText(); // edited and solved different from kao
      answer2 = tf2.getText(); 
      answer3 = tf3.getText(); 
      answer4 = tf4.getText(); 
      answer5 = tf5.getText(); 
      a1 = new AnswerNode(answer1, c1); 
      a2 = new AnswerNode(answer2, c2); 
      a3 = new AnswerNode(answer3, c3); 
      a4 = new AnswerNode(answer4, c4); 
      a5 = new AnswerNode(answer5, c5); 
      
      q1 = new QuestionNode(question,a1,a2,a3,a4,a5,false, null);
      graph.addQuestion(topic, q1);
      questionSaved = new QuestionSaved(window, scene1).getScene();
      window.setScene(questionSaved);
    });
    b2.setOnAction(e -> {
      window.close();
    });
    b1.setTranslateX(150);
    b1.setTranslateY(30);
    
    Vb.getChildren().addAll(topLabel, Hb1, Hb2, Hb3, Hb4,Hb5, b1, b2);

    scene1 = new Scene(Vb);
  }

  public Scene getScene() {
    return scene1;

  }
}