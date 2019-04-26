package application;

import javafx.scene.control.Button;

public class AnswerButton extends Button{
  boolean correct;

  protected AnswerButton(String answer, boolean correct) {
    this.setText(answer);
    this.correct = correct;
      
  }
  public boolean getBoolean() {
    return correct;
  }
  
}
