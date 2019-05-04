package application;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Save extends Button {
  static String file;
  static boolean answer;


  @SuppressWarnings("unchecked")
  public static void saveHelper(String jsonFilepath, QuizGraph qg) throws FileNotFoundException,
      IOException, ParseException, org.json.simple.parser.ParseException {
    // Object obj = new JSONParser().parse(new FileReader(jsonFilepath));
    JSONObject mainObject = new JSONObject(); // w (JSONObject) obj;
    // JSONArray packageArray = (JSONArray) jo.get("questionArray");

    Set<String> topics = qg.getAllTopics();
    // JSONArray jsonTopics = new JSONArray();
    JSONArray jsonQuestions = new JSONArray();
    for (String topic : topics) {
      // JSONObject jsonTopic = new JSONObject();

      ArrayList<QuestionNode> questions = qg.getTopicQuestions(topic);
      // JSONArray jsonQuestions = new JSONArray();
      for (QuestionNode question : questions) {
        JSONObject jsonQuestion = new JSONObject();

        // jsonQuestion.put("TorF", question.TorF());

        String image = "none";
        if (question.getImage() != null)
          image = question.getImage().toString();



        JSONArray answerList = new JSONArray();
        for (AnswerNode answer : question.getAnswerList()) {
          JSONObject jsonAnswer = new JSONObject();
          jsonAnswer.put("answer", answer.getAnswer());
          jsonAnswer.put("isCorrect", answer.getCorrect());
          answerList.add(jsonAnswer);
        }
        jsonQuestion.put("choiceArray", answerList);
        jsonQuestion.put("image", image);
        jsonQuestion.put("topic", topic);
        jsonQuestion.put("questionText", question.getQuestion());
        jsonQuestion.put("meta-data", "unused");
        jsonQuestions.add(jsonQuestion);
      }

      // jsonTopic.put("questions", jsonQuestions);


      // jsonTopic.put("topic", topic);
      // jsonTopic.put("meta-data", "unused");

      // jsonTopics.add(jsonTopic);
    }

    mainObject.put("questionArray", jsonQuestions);

    try {
      System.out.println(mainObject.toJSONString());
      FileWriter file = new FileWriter(jsonFilepath);
      file.write(mainObject.toJSONString());
      file.flush();
      file.close();
    } catch (IOException e) {

    }

    /*
     * for (Object o : packageArray) { JSONObject person = (JSONObject) o;
     * 
     * String name = (String) person.put("meta-data", qg); System.out.println(name);
     * 
     * String city = (String) person.put("questionText", qg.getTopicQuestions("topic"));
     * 
     * System.out.println(city);
     * 
     * String topic = (String) person.put("topic", qg.getAllTopics()); qg.addTopic(topic);
     * System.out.println(topic);
     * 
     * String image = (String) person.put("image", qg); System.out.println(image);
     * 
     * JSONArray choice = (JSONArray) person.put("choiceArray", qg.getGraph()); for (Object c :
     * choice) { System.out.println(c+""); } }
     */
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
        saveHelper("C:\\Users\\Touger\\eclipse-workspace\\FinalTeamProject\\test.json", qg);
        System.out.println(qg.getTopicQuestions("questionText"));
        System.out.println(qg.questionNum());
        System.out.println(qg.topicNum());
        System.out.println(qg.getAllTopics());
      } catch (IOException | ParseException | org.json.simple.parser.ParseException e2) {
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
    Scene scene = new Scene(layout);
    window.setScene(scene);
    window.showAndWait();

    // return answer;
  }

}
