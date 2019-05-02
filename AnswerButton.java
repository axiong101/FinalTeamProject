package application;

import javafx.scene.control.Button;

public class AnswerButton extends Button{
  boolean correct;

  protected AnswerButton(AnswerNode answer) {
    this.setText(answer.getAnswer());
    this.correct = answer.getCorrect();
      
  }
  public boolean getBoolean() {
    return correct;
  }
  
}