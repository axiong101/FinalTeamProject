package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Save extends Button {
  static String file;
  static boolean answer;

  public static void saveHelper(String newFileName, QuizGraph quiz) throws FileNotFoundException,
      IOException, ParseException, org.json.simple.parser.ParseException {


    JSONObject jasonSaveFile = new JSONObject();
    JSONArray questionArray = new JSONArray();

    jasonSaveFile.put("questionArray", (JSONArray) questionArray);


    Set<String> topics = quiz.getAllTopics();
    System.out.println(1);
    for (String topic : topics) {
      System.out.println(2);
      System.out.println(topic);
      ArrayList<QuestionNode> questions = quiz.getTopicQuestions(topic);
      for (QuestionNode q : questions) {
        System.out.println(3);
        System.out.println(q.getQuestion());
        Map<String, Object> m = new LinkedHashMap<String, Object>(5);
        m.put("meta-data", "unused");
        m.put("questionText", q.getQuestion());
        m.put("topic", topic);
        if (q.getImage() == null) {
          m.put("imageFilename", "none");
        } else {
          m.put("imageFilename", q.getImage());
        }
        System.out.println(4);
        Map<String, String> mA = new LinkedHashMap<String, String>(2);
        JSONArray answerArray = new JSONArray();
        ArrayList<AnswerNode> answers = q.getAnswerList();
        for (AnswerNode answer : answers) {
          System.out.println(answer.getAnswer());
          String correct;
          if (answer.getCorrect())
            correct = "T";
          else
            correct = "F";
          mA.put("isCorrect", correct);
          mA.put("choiceText", answer.getAnswer());
          answerArray.add(mA);
        }
        m.put("choiceArray", answerArray);
        questionArray.add(m);
      }

    }
    System.out.println(5);
    try {
      FileWriter pw = new FileWriter(newFileName);
      pw.write(jasonSaveFile.toJSONString());
      System.out.println(6);
      pw.flush();
      pw.close();
    } catch (Exception e) {
      e.printStackTrace();
    }


  }

  public static void saveMethod(String Title, String message, QuizGraph qg) {
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);
    window.setTitle("Save");
    window.setMinWidth(250.0);
    Label save = new Label();
    save.setText(message);



    Button yButton = new Button("Yes");
    Button nButton = new Button("No");



    yButton.setOnAction(e -> {
      try {
        createFile(qg);
      } catch (Exception e2) {
        e2.printStackTrace();
      }
      // answer = true;
      window.close();
    });

    nButton.setOnAction(e -> {
      // answer = false;
      window.close();
    });

    VBox layout = new VBox();
    layout.getChildren().addAll(save, yButton, nButton);
    layout.setAlignment(Pos.CENTER);
    Scene scene = new Scene(layout, 400, 150);
    window.setScene(scene);
    window.showAndWait();

    // return answer;
  }

  private static void createFile(QuizGraph quiz) {
    Stage window = new Stage();
    window.initModality(Modality.APPLICATION_MODAL);
    window.setTitle("Save");
    window.setMinWidth(250.0);
    Label newFileLabel = new Label("New File Name:");
    TextField newFile = new TextField();
    Button save = new Button("save");

    Label allFilesLabel = new Label("All Current Save Files:");
    ComboBox<String> allFiles = new ComboBox<String>();
    ArrayList<String> fileArray = getAllFiles();
    for (String file : fileArray) {
      allFiles.getItems().add(file);
    }

    save.setOnAction(e -> {
      try {
        saveHelper(newFile.getText(), quiz);
      } catch (IOException | ParseException | org.json.simple.parser.ParseException e2) {
        e2.printStackTrace();
      }
      window.close();
    });

    VBox layout = new VBox();
    layout.setAlignment(Pos.CENTER);
    layout.getChildren().addAll(allFilesLabel, allFiles, newFileLabel, newFile, save);
    Scene scene = new Scene(layout, 400, 150);
    window.setScene(scene);
    window.showAndWait();
  }

  private static ArrayList<String> getAllFiles() {
    File folder = new File(System.getProperty("user.dir") + "\\saveFiles");
    File[] listOfFiles = folder.listFiles();
    ArrayList<String> fileNames = new ArrayList<String>();
    for (int i = 0; i < listOfFiles.length; i++) {
      if (listOfFiles[i].isFile()) {
        fileNames.add(listOfFiles[i].getName());
      }
    }
    return fileNames;
  }
}
