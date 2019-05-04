package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Load {
  static FileChooser fileChooser = new FileChooser();
public static void display(QuizGraph graph) {
  Stage window = new Stage();
  window.initModality(Modality.APPLICATION_MODAL);
  window.setTitle("Load");
  window.setMinWidth(250);
  VBox vb = new VBox();
  vb.setPadding(new Insets(100, 100, 100, 100));
  vb.setSpacing(10);
  Button pick = new Button("Pick A JSON File To Load"); 
  
  pick.setOnAction(
      new EventHandler<ActionEvent>() {
        @Override
        public void handle(final ActionEvent e) {
            File file = fileChooser.showOpenDialog(window);
            if (file != null) {
               try {
                loadHelper(file, graph);
              } catch (IOException | org.json.simple.parser.ParseException e1) {
                Warning.display("IOException", "Encounter an IOException", true); 
              } 
               Warning.display("Success", "Sucessfully Loaded Questions to Program", false); 
            } 
            window.close();
        }
    });
   vb.getChildren().add(pick);
   Scene scene = new Scene(vb);
   window.setScene(scene);
   window.showAndWait();
  
  
  // Adding VBox to the scene
  window.showAndWait();
}

private static void loadHelper(File file,QuizGraph graph) throws FileNotFoundException, IOException, org.json.simple.parser.ParseException  {
  
  Object obj = new JSONParser().parse(new FileReader(file));
JSONObject jo = (JSONObject) obj;
JSONArray packageArray = (JSONArray) jo.get("questionArray"); 

  for (Object o : packageArray)
  {
    ArrayList<AnswerNode> a = new ArrayList<AnswerNode>(); 
    QuestionNode q; 
    Image image;
    String topic; 
    boolean isCorrect; 
    boolean ToF = false; 
    JSONObject question = (JSONObject) o;

    String ToFQuestion = (String) question.get("meta-data");
    if(!ToFQuestion.equals("unused")){
      if(ToFQuestion.equals("true") || ToFQuestion.equals("false")) {
      if(ToFQuestion.equalsIgnoreCase("true")) {
         ToF = true; 
      } else {
         ToF = false; 
      }
    }
    }
    String questionText = (String) question.get("questionText");
    
    topic = (String) question.get("topic");
    graph.addTopic(topic);
    
    String sImage = (String) question.get("image");
    if(!sImage.equals("none")) {
    image = (Image) question.get("image");
    } else {
       image = null; 
    }
    JSONArray choice = (JSONArray) question.get("choiceArray");
    for (Object c : choice)
    {
      JSONObject array = (JSONObject) c; 
      String choiceForGraph = (String) array.get("choice");
      String sToF = (String) array.get("isCorrect");
      if(sToF.equals("T")) {
         isCorrect = true; 
        a.add(new AnswerNode(choiceForGraph, isCorrect));
      }else {
         isCorrect = false;  
        a.add(new AnswerNode(choiceForGraph, isCorrect));
      } 
  }
    if(!ToF) {
      q = new QuestionNode(questionText,a.get(0),a.get(1),a.get(2),a.get(3),a.get(4),false,image);
      graph.addQuestion(topic, q);
    }else {
      q = new QuestionNode(questionText, a.get(0), a.get(1),true,image);
      graph.addQuestion(topic, q);
    }
}
}
}
