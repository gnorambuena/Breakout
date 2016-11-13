package com.cc3002.breakout.facade;

import com.cc3002.breakout.logic.bonus.AddLifeModifier;
import com.cc3002.breakout.logic.bonus.AddScoreModifier;
import com.cc3002.breakout.logic.bonus.Bonus;
import com.cc3002.breakout.logic.bonus.IBonus;
import com.cc3002.breakout.logic.bonus.LossLifeModifier;
import com.cc3002.breakout.logic.bonus.LossScoreModifier;
import com.cc3002.breakout.logic.brick.IBrick;
import com.cc3002.breakout.logic.level.ILevel;
import com.cc3002.breakout.logic.level.Player;
import com.cc3002.breakout.logic.level.RealLevel;
import com.cc3002.breakout.logic.level.Score;
import com.cc3002.breakout.logic.observer.BonusObserver;
import com.cc3002.breakout.logic.observer.GameObserver;
import com.cc3002.breakout.logic.observer.LevelObserver;
import com.cc3002.breakout.logic.observer.ScoreObserver;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Facade de la tarea 1.
 * @author Gabriel Norambuena.
 *
 */

public class HomeworkTwoFacade {
  ILevel curLevel;
  ILevel nextLevel;
  Score curScore;
  Player pl;
  double probability;
  int number;
  List<GameObserver> observers;
  PrintStream stream;
  
  /**
   * Constructor vacío del Facade,
   * crea una instancia null de ILevel,
   * una instancia de la clase Player,
   * y una instancia de la clase Score.
   */
  
  public HomeworkTwoFacade() {
    curLevel = null;
    nextLevel = null;
    pl = new Player();
    curScore = new Score();
    probability = 0.2;
    number = 32;
    stream = System.out;
    genGameObservers();
   
  }
  
  /**
   * Constructor de la Facade.
   * @param name Nombre del nivel.
   * @param num Numero de bricks en el nivel.
   * @param prob Probabilidades de aparición de un SoftBrick en el nivel.
   */
  
  public HomeworkTwoFacade(String name, int num, double prob) {
    pl = new Player();
    curLevel = newLevelWithSoftAndStoneBricks(name, num, prob);
    number = num;
    nextLevel = null;
    curScore = new Score();
    probability = prob;
    stream = System.out;
    genGameObservers();
  }

  private void genGameObservers() {
    observers = new ArrayList<GameObserver>();
    observers.add(new BonusObserver(stream));
    observers.add(new LevelObserver(stream));
    observers.add(new ScoreObserver(stream));
    
  }
  
  public long earnedScore() {
    return pl.getTotalPoints();
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
    return new RealLevel(levelName, number, probability,pl);
  }

  public long numberOfBricks() {
    return curLevel.getNumberOfBricks();
  }

  /**
   * Setea el nivel actual del juego.
   * @param newLevel nivel al que se seteara el juego.
   */
  public void setCurrentLevel(final ILevel newLevel) {
    curLevel = newLevel;
    for (GameObserver observer: observers) {
      observer.levelUpdate(curLevel.getLevelName());
    }
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
  
  public void setNextLevel() {
    probability *= 1.1;
    nextLevel = newLevelWithSoftAndStoneBricks("Another Level",number,probability);
  }
  
  public List<IBonus> newBonuses(final int number, final double probability) {
    return Bonus.genBonuses(number,probability,pl,curLevel.getObservers());
  }
  
  public void registerBonuses(final List<IBonus> bonuses) {
    curLevel.setBonuses(bonuses);
  }
  
  public void setGameConsoleOutput(final PrintStream printStream){}
  
  public void autoSwitchToNextLevel() {
    setCurrentLevel(nextLevel);
    nextLevel = null;
  }
  
  public boolean hasCurrentLevel() {
    return curLevel != null;
  }
  
  public IBonus newExtraScore() {
    return new AddScoreModifier(pl,curLevel.getObservers());
  }
  
  public IBonus newExtraHeart() {
    return new AddLifeModifier(pl,curLevel.getObservers());
  }
  
  public IBonus newScoreDiscount() {
    return new LossScoreModifier(pl,curLevel.getObservers());
  }
  
  public IBonus newHeartDiscount() {
    return new LossLifeModifier(pl,curLevel.getObservers());
  }
  
}
