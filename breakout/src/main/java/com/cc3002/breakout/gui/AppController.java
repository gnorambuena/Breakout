package com.cc3002.breakout.gui;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.ui.UIController;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * @author Almas Baimagambetov (AlmasB) (almaslvl@gmail.com)
 */
public class AppController implements UIController {

  @FXML
  private Label labelScorePlayer;
  
  @FXML
  private Label labelScore;
  
  @FXML
  private Label labelLifes;
  
  @FXML
  private Label labelLifesPlayer;
  
  public Label getLabelScorePlayer() {
    return labelScorePlayer;
  }
  
  public Label getLabelScore() {
    return labelScore;
  }
  
  public Label getLabelLifesPlayer() {
    return labelLifesPlayer;
  }
  
  public Label getLabelLifes() {
    return labelLifes;
  } 
  
  @Override
  public void init() {
    labelScorePlayer.setFont(FXGL.getUIFactory().newFont(40));
    labelScore.setFont(FXGL.getUIFactory().newFont(40));
    labelLifes.setFont(FXGL.getUIFactory().newFont(40));
    labelLifesPlayer.setFont(FXGL.getUIFactory().newFont(40));

    labelScorePlayer.textProperty().addListener((observable, oldValue, newValue) -> {
      animateLabel(labelScorePlayer);
    });
    

    labelLifesPlayer.textProperty().addListener((observable, oldValue, newValue) -> {
      animateLabel(labelLifesPlayer);
    });


  }
  
  private void animateLabel(Label label) {
    FadeTransition ft = new FadeTransition(Duration.seconds(0.33), label);
    ft.setFromValue(0);
    ft.setToValue(1);
    ft.play();
  }


}