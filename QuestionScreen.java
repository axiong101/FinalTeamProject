package application;

import java.util.ArrayList;
import java.util.Collections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
  QuizGraph quiz;
  ArrayList<QuestionNode> questionList;
  QuestionNode questionTested;
  int totalCorrect;

  protected QuestionScreen(Stage primaryStage, Scene mainScene, int qNum, int maxQuestions,
      QuizGraph quiz, ArrayList<QuestionNode> questionList,int totalCorrect) {
    
    this.totalCorrect = totalCorrect;
    this.quiz = quiz;
    this.questionList = questionList;
    root = new BorderPane();
    this.qNum = qNum;
    this.primaryStage = primaryStage;
    this.mainScene = mainScene;
    this.maxQuestions = maxQuestions;
    this.qNum++;
    questionTested = questionList.get(qNum - 1);

    // setTop
    HBox topLabel = new HBox();
    topLabel.setAlignment(Pos.CENTER);
    Label questionLabel = new Label(questionTested.getQuestion());
    questionLabel.setFont(new Font("Times New Roman", 40));
    Label questionNum = new Label(Integer.toString(qNum));
    questionNum.setFont(new Font("Times New Roman", 40));
    topLabel.getChildren().addAll(questionLabel, questionNum);
    root.setTop(topLabel);


    // setBotton
    VBox center = new VBox();
    Label question = new Label("Question:");
    ArrayList<AnswerNode> answerList = this.questionTested.getAnswerList();
    Collections.shuffle(answerList);
    question.setFont(new Font("Times New Roman", 30));
    if (questionTested.TorF()) {
      answerA = new AnswerButton(answerList.get(0));
      answerA.setFont(new Font("Times New Roman", 20));
      answerB = new AnswerButton(answerList.get(1));
      answerB.setFont(new Font("Times New Roman", 20));
      center.getChildren().addAll(question, answerA, answerB);
    } else {
      answerA = new AnswerButton(answerList.get(0));
      answerA.setFont(new Font("Times New Roman", 20));
      answerB = new AnswerButton(answerList.get(1));
      answerB.setFont(new Font("Times New Roman", 20));
      answerC = new AnswerButton(answerList.get(2));
      answerC.setFont(new Font("Times New Roman", 20));
      answerD = new AnswerButton(answerList.get(3));
      answerD.setFont(new Font("Times New Roman", 20));
      center.getChildren().addAll(question, answerA, answerB, answerC, answerD);
    }
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
    if (correct) {
      totalCorrect++;
    }
    correctScreen =
        new CorrectScreen(primaryStage, mainScene, qNum, maxQuestions, correct, quiz, questionList, totalCorrect)
            .getScene();
    primaryStage.setScene(correctScreen);
  }
}
