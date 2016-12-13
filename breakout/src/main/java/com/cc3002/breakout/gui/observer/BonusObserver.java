package com.cc3002.breakout.gui.observer;

import com.cc3002.breakout.facade.Flyweight;
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
   * @param uioverlay Overlay del juego para mostrar los mensajes.
   */
  public BonusObserver(BreakoutApp breakout) {
    this.uioverlay = breakout.getOverlay();
    this.score = breakout.getScorePlayer();
    this.life = breakout.getLifesPlayer();
    this.curScore = breakout.getFacade().getFlyweight().getCurScore();
    this.player = breakout.getFacade().getPlayer();
    this.breakout = breakout;
    //System.out.println("Observer initialized");
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
      score.set((int)curScore.getPoints());
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
      if (player.getNumberOfHearts() <= 0) {
        uioverlay.showMessage("Game Over!");
        breakout.pause();
      }
    }
  }

}
