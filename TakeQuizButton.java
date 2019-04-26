package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TakeQuizButton {
  Button takeQuizButton;
  Scene takeButtonQuizScene;
  Scene questionScreen;
  BorderPane root;
  Button helper;
  Button next;
  int maxQuestions;
  int qNum;

  protected TakeQuizButton(Stage primaryStage, Scene mainScene, int qNum) {

    root = new BorderPane();
    this.qNum = qNum;

    // set top
    HBox top = new HBox();
    helper = new Button("?");
    top.getChildren().add(helper);
    top.setAlignment(Pos.TOP_RIGHT);
    root.setTop(top);

    // set center
    HBox hbox = new HBox();
    VBox vbox = new VBox();
    VBox topicBox = new VBox();
    Label whatTopic = new Label("What Topic?");
    whatTopic.setAlignment(Pos.CENTER);
    ComboBox topic = new ComboBox();
    topic.getItems().addAll("topic 1", "topic 2", "topic 3");
    topic.scaleShapeProperty();
    Label HowManyQuestions = new Label("How Many Questions?");
    HowManyQuestions.setAlignment(Pos.CENTER);
    TextArea numQuestion = new TextArea();
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
    next = new Button("next");
    bottom.getChildren().add(next);
    bottom.setAlignment(Pos.BOTTOM_RIGHT);
    root.setBottom(bottom);


    takeButtonQuizScene = new Scene(root, 800, 400);
    questionScreen = new QuestionScreen(primaryStage, mainScene, this.qNum, maxQuestions).getScene();
    next.setOnAction(e -> primaryStage.setScene(questionScreen));

  }

  public Scene getScene() {

    return takeButtonQuizScene;
  }

}
