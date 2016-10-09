package com.cc3002.breakout.facade;

import com.cc3002.breakout.logic.IBrick;
import com.cc3002.breakout.logic.ILevel;
import com.cc3002.breakout.logic.Player;
import com.cc3002.breakout.logic.RealLevel;
import com.cc3002.breakout.logic.Score;

import java.util.List;
/**
 * Facade de la tarea 1.
 * @author Gabriel Norambuena.
 *
 */

public class HomeworkOneFacade {
  ILevel curLevel;
  Score curScore;
  Player pl;
  
  /**
   * Constructor vacío del Facade,
   * crea una instancia null de ILevel,
   * una instancia de la clase Player,
   * y una instancia de la clase Score.
   */
  
  public HomeworkOneFacade() {
    curLevel = null;
    pl = new Player();
    curScore = new Score();
  }
  
  /**
   * Constructor de la Facade.
   * @param name Nombre del nivel.
   * @param num Numero de bricks en el nivel.
   * @param prob Probabilidades de aparición de un SoftBrick en el nivel.
   */
  
  public HomeworkOneFacade(String name, int num, double prob) {
    curLevel = newLevelWithSoftAndStoneBricks(name, num, prob);
    pl = new Player();
    curScore = new Score();
  }

  public long earnedScore() {
    return curLevel.getEarnedScore().getPoints();
  }

  public List<IBrick> getBricks() {
    return curLevel.getBricks();
  }

  public ILevel getCurrentLevel() {
    return curLevel;
  }

  public String getLevelName() {
    return curLevel.getLevelName();
  }

  public int getNumberOfHearts() {
    return pl.getNumberOfHearts();
  }

  public int getRequiredPoints() {
    return curLevel.getRequiredPoints();
  }

  public boolean hasNextLevel() {
    return false;
  }

  public int lossOfHeart() {
    pl.lossOfHeart();
    return pl.getNumberOfHearts();
  }

  public ILevel newLevelWithSoftAndStoneBricks(String levelName, int number, double probability) {
    return new RealLevel(levelName, number, probability);
  }

  public long numberOfBricks() {
    return curLevel.getNumberOfBricks();
  }

  public void setCurrentLevel(final ILevel newLevel) {
    curLevel = newLevel;
  }
  /**
   * Funcion que utiliza la representacion como String
   * de los IBricks y luego los retorna para ser representado
   * en la consola.
   * @param rlevel Un ILevel que se necesita como String.
   * @return La representacion del ILevel para la consola.
   */
  
  public String spawnBricks(final ILevel rlevel) {
    return RealLevel.spawnBricks(rlevel);
  }
}
