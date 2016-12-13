package com.cc3002.breakout.gui.observer;

import com.almasb.fxgl.app.FXGL;
import com.cc3002.breakout.gui.AudioController;
import com.cc3002.breakout.gui.BreakoutApp;
import com.cc3002.breakout.gui.Overlay;
import com.cc3002.breakout.logic.bonus.IBonus;
import com.cc3002.breakout.logic.level.Player;
import com.cc3002.breakout.logic.level.Score;

import javafx.beans.property.IntegerProperty;

import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;

/**
 * Clase abstracta para todos los Observer del juego.
 * @author gabriel
 *
 */
public class BonusObserver implements Observer {
  
  Overlay uioverlay;
  IntegerProperty score;
  IntegerProperty life;
  Score curScore;
  Player player;
  BreakoutApp breakout;
  Hashtable<String,String> tabla;
  
  /**
   * Observer del juego, se encarga de mostrar los mensajes de los eventos.
   */
  public BonusObserver() {
    breakout = (BreakoutApp)FXGL.getApp();
    uioverlay = breakout.getOverlay();
    score = breakout.getScorePlayer();
    life = breakout.getLifesPlayer();
    curScore = breakout.getFacade()
                       .getFlyweight()
                       .getCurScore();
    player = breakout.getFacade().getPlayer();

    tabla = new Hashtable<String,String>();
    tabla.put("SD", "3 Score discount!");
    tabla.put("ES", "5 Extra Score!");
    tabla.put("HD", "1 Heart discount!");
    tabla.put("EH", "1 Extra Heart!");
    tabla.put("RB", "Extra large bat!");
    tabla.put("AB", "Extra ball!");
  }

  /**
   * Metodo que muestra el mensaje.
   */
  public void update(Observable object, Object arg) {
    //System.out.println(arg);
    String value = tabla.get(arg);
    if (value != null) {
      uioverlay.showMessageFlash(value, 0.7, 25, 45);
      life.set(player.getNumberOfHearts());
      IBonus curBonus = (IBonus)object;
      if (curBonus.isDiscount()) {
        new AudioController().playSound(breakout.getSound("discount"),0.7);
        if (curBonus.isAddBall()) {
          breakout.spawnSecondBall();
        }
      } else {
        new AudioController().playSound(breakout.getSound("bonus"),0.7);
        if (curBonus.isBatResize()) {
          //System.out.println("bonus observer bat resize");
          breakout.batResize();
        }
      }
      if (player.getNumberOfHearts() < 0) {
        uioverlay.showMessage("Game Over!");
        breakout.pause();
      } else {
        score.set((int)curScore.getPoints());
      }
    }
  }

}
