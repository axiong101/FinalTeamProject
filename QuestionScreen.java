package application;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class QuestionScreen {
  Scene questionScreen;
  Scene correctScreen;
  BorderPane root;
  int qNum;
  private Stage primaryStage;
  private Scene mainScene;
  private int maxQuestions;
  AnswerButton answerA;
  AnswerButton answerB;
  AnswerButton answerC;
  AnswerButton answerD;


  protected QuestionScreen(Stage primaryStage, Scene mainScene, int qNum, int maxQuestions) {

    root = new BorderPane();
    this.qNum = qNum;
    this.primaryStage = primaryStage;
    this.mainScene = mainScene;
    this.maxQuestions = maxQuestions;
    this.qNum++;

    // setTop
    HBox topLabel = new HBox();
    topLabel.setAlignment(Pos.CENTER);
    Label questionLabel = new Label("Question ");
    questionLabel.setFont(new Font("Times New Roman", 40));
    Label questionNum = new Label(Integer.toString(qNum));
    questionNum.setFont(new Font("Times New Roman", 40));
    topLabel.getChildren().addAll(questionLabel, questionNum);
    root.setTop(topLabel);
    

    // setBotton
    VBox center = new VBox();
    Label question = new Label("Question:");
    question.setFont(new Font("Times New Roman", 30));
    answerA = new AnswerButton("A: Correct", true); // hashtable not implemented yet
    answerA.setFont(new Font("Times New Roman", 20));
    answerB = new AnswerButton("B: wrong", false);
    answerB.setFont(new Font("Times New Roman", 20));
    answerC = new AnswerButton("C: wrong", false);
    answerC.setFont(new Font("Times New Roman", 20));
    answerD = new AnswerButton("D: wrong", false);
    answerD.setFont(new Font("Times New Roman", 20));
    center.getChildren().addAll(question, answerA, answerB, answerC, answerD);
    root.setCenter(center);

    questionScreen = new Scene(root, 800, 400);

    answerA.setOnAction(e -> handleButton(answerA.getBoolean()));
    answerB.setOnAction(e -> handleButton(answerB.getBoolean()));
    answerC.setOnAction(e -> handleButton(answerC.getBoolean()));
    answerD.setOnAction(e -> handleButton(answerD.getBoolean()));
    


  }

  public Scene getScene() {
    return questionScreen;
  }

  private void handleButton(boolean correct) {
    correctScreen =
        new CorrectScreen(primaryStage, mainScene, qNum, maxQuestions, correct).getScene();
    primaryStage.setScene(correctScreen);
  }
}
