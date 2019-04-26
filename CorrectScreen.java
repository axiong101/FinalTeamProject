package application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CorrectScreen {
  Scene correctScreen;
  Scene questionScreen;
  BorderPane root;
  Label correctL;
  Button next;


  protected CorrectScreen(Stage primaryStage, Scene mainScene, int qNum, int maxQuestions, boolean correct) {
    
    root = new BorderPane();
    HBox label = new HBox();
    if (correct)  {
    correctL = new Label("You Answered Correct");
    correctL.setFont(new Font("Times New Roman", 60));
    } else {
      correctL = new Label("Sorry That's Wrong");
      correctL.setFont(new Font("Times New Roman", 60));
    }
    label.getChildren().add(correctL);
    label.setAlignment(Pos.CENTER);
    root.setCenter(label);
    
    HBox bottom = new HBox();
    next = new Button("next");
    bottom.getChildren().add(next);
    bottom.setAlignment(Pos.BOTTOM_RIGHT);
    root.setBottom(bottom);
    
    correctScreen = new Scene(root, 800, 400);
    
    questionScreen = new QuestionScreen(primaryStage, mainScene, qNum, maxQuestions).getScene();
    next.setOnAction(e -> primaryStage.setScene(questionScreen));
    
  }


  public Scene getScene() {
    // TODO Auto-generated method stub
    return correctScreen;
  }
}
