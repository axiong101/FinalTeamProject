package application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TakeQuizButton {
  Button takeQuizButton;
  Scene takeButtonQuizScene;
  Scene questionScreen;
  Scene mainScene;
  Stage primaryStage;
  BorderPane root;
  Button helper;
  Button next;
  int maxQuestions;
  int qNum;
  ComboBox<String> topic;
  String topicTested;
  ArrayList<QuestionNode> questionList;
  QuizGraph quiz;
  private Object window;

  protected TakeQuizButton(Stage primaryStage, Scene mainScene, int qNum, QuizGraph quiz) {

    this.quiz = quiz;
    root = new BorderPane();
    this.qNum = qNum;
    this.mainScene = mainScene;
    this.primaryStage = primaryStage;

    // set top
    HBox top = new HBox();
    helper = new Button("help");
    top.getChildren().add(helper);
    top.setAlignment(Pos.TOP_RIGHT);
    root.setTop(top);

    // set center
    HBox hbox = new HBox();
    VBox vbox = new VBox();
    VBox topicBox = new VBox();
    Label whatTopic = new Label("What Topic?");
    whatTopic.setAlignment(Pos.CENTER);
    topic = new ComboBox<String>();
    Set<String> topics = quiz.getAllTopics();

    Set<String> a = new TreeSet<String>();
    a.add("a");
    a.add("b");

    for (String t : topics) {
      topic.getItems().add(t);
    }
    topic.scaleShapeProperty();
    Label HowManyQuestions = new Label("How Many Questions?");
    HowManyQuestions.setAlignment(Pos.CENTER);
    TextField numQuestion = new TextField();
    numQuestion.setPrefColumnCount(1);
    numQuestion.setPrefHeight(1);
    topicBox.getChildren().add(whatTopic);
    topicBox.getChildren().add(topic);
    topicBox.getChildren().add(HowManyQuestions);
    topicBox.getChildren().add(numQuestion);
    hbox.getChildren().add(topicBox);
    hbox.setAlignment(Pos.CENTER);
    vbox.getChildren().add(hbox);
    vbox.setAlignment(Pos.CENTER);
    root.setCenter(vbox);

    // set bottom
    HBox bottom = new HBox();
    Label numQLabel = new Label("Number of Questions in Database: " + quiz.questionNum());
    numQLabel.setAlignment(Pos.BOTTOM_LEFT);
    numQLabel.setFont(new Font("Times New Roman", 20));
    next = new Button("next");
    next.setTranslateX(400);
    bottom.getChildren().addAll(numQLabel, next);
    root.setBottom(bottom);

    EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
      public void handle(ActionEvent e) {
        topicTested = topic.getValue();
      }
    };

    takeButtonQuizScene = new Scene(root, 800, 400);
    next.setOnAction(e -> {
      HandleButton(numQuestion.getText(), quiz);
    });
    topic.setOnAction(event);

  }

  public Scene getScene() {
    return takeButtonQuizScene;
  }



  private void HandleButton(String numQuestions, QuizGraph quiz) {
    try {
      System.out.println(topicTested);
      questionList = quiz.getTopicQuestions(topicTested);
      Collections.shuffle(questionList);
      next.setOnAction(e -> {
        primaryStage.setScene(new QuestionScreen(primaryStage, mainScene, this.qNum,
            Integer.valueOf(numQuestions), quiz, questionList, 0).getScene());
      });
    } catch (NumberFormatException e1) {

      boolean answer = Warning.display("WARNING!",
          "You did not type in a number, Please Type in a Number", false);
    } catch (Exception e2) {
      e2.printStackTrace();
      boolean answer = Warning.display("WARNING!", "unexpected exception thrown", false);
    }
  }

}
