package application;

import java.util.ArrayList;
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
  Scene totalScoreScreen;
  BorderPane root;
  Label correctL;
  Button next;
  int qNum;
  int maxQuestions;
  Stage primaryStage;
  Scene mainScene;
  QuizGraph quiz;
  ArrayList<QuestionNode> questionList;
  int totalCorrect;

  protected CorrectScreen(Stage primaryStage, Scene mainScene, int qNum, int maxQuestions,
      boolean correct, QuizGraph quiz, ArrayList<QuestionNode> questionList, int totalCorrect) {

    this.totalCorrect = totalCorrect;
    this.questionList = questionList;
    this.quiz = quiz;
    this.qNum = qNum;
    this.maxQuestions = maxQuestions;
    this.primaryStage = primaryStage;
    this.mainScene = mainScene;
    root = new BorderPane();
    HBox label = new HBox();
    if (correct) {
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

    next.setOnAction(e -> HandleButton());


  }


  public Scene getScene() {
    // TODO Auto-generated method stub
    return correctScreen;
  }

  private void HandleButton() {
    if (qNum == maxQuestions + 1 || qNum == questionList.size() + 1) {
      int numTaken;
      if (maxQuestions > questionList.size())
        numTaken = questionList.size();
      else
        numTaken = maxQuestions;
      totalScoreScreen = new TotalScore(primaryStage, mainScene, numTaken, totalCorrect).getScene();
      primaryStage.setScene(totalScoreScreen);
    } else {
      questionScreen = new QuestionScreen(primaryStage, mainScene, qNum, maxQuestions, quiz,
          questionList, totalCorrect).getScene();
      primaryStage.setScene(questionScreen);
    }
  }
}
