package application;

import java.io.File;
import java.util.Set;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;


public class Main extends Application {
  Stage window;
  Scene scene1, scene2, takeQuizScene;
  QuizGraph quiz;
  Button takeQ;
  TakeQuizButton t1;
  @Override
  public void start(Stage primaryStage) {
    
    try {
      
      (new File("saveFiles")).mkdir();
      quiz = new QuizGraph();
      BorderPane root = new BorderPane();
      scene2 = new Scene(root, 800, 800);
      scene2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      primaryStage.setScene(scene2);
      primaryStage.setTitle("Quiz Generator");
      primaryStage.show();
      // setTop
      Button help = new Button("Help");
      Label topLabel = new Label("Quiz Guru");
      topLabel.setFont(new Font("Arial", 20));
      topLabel.setTextFill(Color.RED);
      topLabel.setStyle("-fx-font-weight: bold");
      HBox hBox2 = new HBox(2);
      hBox2.getChildren().add(topLabel);
      hBox2.getChildren().add(help);
      hBox2.setSpacing(350);
      hBox2.setAlignment(Pos.CENTER_RIGHT);
      root.setTop(hBox2);
      // setBottom



      // setCenter
      VBox vbox = new VBox(4);
      HBox hbox = new HBox(2);
      Button addQ = new Button("Add Questions");
      Button save = new Button("Save");
      Button load = new Button("Load");
      takeQ = new Button("Take Quiz");
      // Button help = new Button("?");
      // help.setTextAlignment(TextAlignment.RIGHT);
      vbox.getChildren().add(addQ);
      hbox.setSpacing(10.0);
      hbox.getChildren().add(save);
      hbox.getChildren().add(load);
      vbox.getChildren().add(hbox);
      vbox.getChildren().add(takeQ);
      vbox.setSpacing(10.0);
      hbox.setAlignment(Pos.CENTER);
      vbox.setAlignment(Pos.CENTER);
      root.setCenter(vbox);
      root.setAlignment(vbox, Pos.CENTER);


      addQ.setOnAction(e -> {
        quiz = AddQuestion.display(primaryStage, quiz);
      });

      primaryStage.setOnCloseRequest(e -> {
        e.consume();
        closeProgram(primaryStage);
      });
      save.setOnAction(e -> {
        
            Save.saveMethod("Save Window", "Are you sure you want to create a new save?", quiz);
      });

      load.setOnAction(e -> {
        Load.loadMethod("Load Window",
            "Are you sure you want to load the previous savefile?", quiz);
      });

      String message =
          "The add Question button: this buttons will take you to a page where you can customize your questions. \n "
              + "The Save button: this button will save all of the questions and your progress on this program so far.\n "
              + "The Load button: this button will load all your previous save file of your progress.\n "
              + "The Take Quiz: this button becomes available after you have created your questions, \n this will allow you to take the quiz with your questions created.";
      help.setOnAction(e -> {
        boolean result3 = Help.helpMethod("Help", message);
      });

      t1 = new TakeQuizButton(primaryStage, scene2, 1, quiz);
      takeQ.setOnAction(
          e -> primaryStage.setScene(new TakeQuizButton(primaryStage, scene2, 1, quiz).getScene()));
      // setLeft

      // setRight
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  public static void main(String[] args) {
    Application.launch(args);
  }

  private void closeProgram(Stage primaryStage) {
    boolean answer = Warning.display("WARNING!", "Would you like to exit without saving?", true);
    if (answer) {
      primaryStage.close();
    }
  }
}
