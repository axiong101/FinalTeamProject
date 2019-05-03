package application;

import java.util.ArrayList;
import javafx.scene.image.Image;

public class QuestionNode {
  private String question;
  private boolean TorF;
  private ArrayList<AnswerNode> answerList;
  private Image image;


  public QuestionNode(String question, AnswerNode answerA, AnswerNode answerB, AnswerNode answerC,
      AnswerNode answerD, AnswerNode answerE, Boolean TorF, Image image) {
    this.question = question;
    answerList = new ArrayList<AnswerNode>();
    answerList.add(answerA);
    answerList.add(answerB);
    answerList.add(answerC);
    answerList.add(answerD);
    answerList.add(answerE);
    this.TorF = TorF;
    this.image = image;
  }
  public QuestionNode(String question, AnswerNode answerA, AnswerNode answerB, Boolean TorF) {
    this.question = question;
    answerList = new ArrayList<AnswerNode>();
    answerList.add(answerA);
    answerList.add(answerB);
    this.TorF = TorF;
  }
  
  public String getQuestion() {
    return this.question;
  }
  
  public boolean TorF() {
    return this.TorF;
  }
    
  public ArrayList<AnswerNode> getAnswerList() {
    return this.answerList;
  }
  
  public Image getImage() {
    return image;
  }
    
}
