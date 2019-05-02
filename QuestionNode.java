package application;

import java.util.ArrayList;

public class QuestionNode {
  private String question;
  private boolean TorF;
  private ArrayList<AnswerNode> answerList;


  public QuestionNode(String question, AnswerNode answerA, AnswerNode answerB, AnswerNode answerC,
      AnswerNode answerD, Boolean TorF) {
    this.question = question;
    answerList = new ArrayList<AnswerNode>();
    answerList.add(answerA);
    answerList.add(answerB);
    answerList.add(answerC);
    answerList.add(answerD);
    this.TorF = TorF;
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
    
}
