package application;

public class AnswerNode {

  private String answer;
  private boolean correct;
  
  public AnswerNode(String answer, boolean correct) {
    this.answer = answer;
    this.correct = correct;
  }
  
  public String getAnswer() {
    return this.answer;
  }
  
  public boolean getCorrect() {
    return this.correct;
  }
}
