package com.cc3002.breakout.facade;

import com.cc3002.breakout.logic.bonus.AddLifeModifier;
import com.cc3002.breakout.logic.bonus.AddScoreModifier;
import com.cc3002.breakout.logic.bonus.BonusHandler;
import com.cc3002.breakout.logic.bonus.IBonus;
import com.cc3002.breakout.logic.bonus.LossLifeModifier;
import com.cc3002.breakout.logic.bonus.LossScoreModifier;
import com.cc3002.breakout.logic.brick.IBrick;
import com.cc3002.breakout.logic.level.GameConsole;
import com.cc3002.breakout.logic.level.ILevel;
import com.cc3002.breakout.logic.level.LevelHandler;
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
 * Facade de la tarea 2.
 * @author Gabriel Norambuena.
 *
 */

public class HomeworkTwoFacade {
  ILevel levelHandler;
  Score curScore;
  Player pl;
  double probability;
  int number;
  List<GameObserver> observers;
  GameConsole gameConsole;
  BonusHandler bonusHandler;
  
  /**
   * Constructor vacío del Facade,
   * crea una instancia null de ILevel,
   * una instancia de la clase Player,
   * y una instancia de la clase Score.
   */
  
  public HomeworkTwoFacade() {
    bonusHandler = new BonusHandler();
    levelHandler = new LevelHandler(null);
    pl = new Player();
    curScore = new Score();
    probability = 0.2;
    number = 32;
    gameConsole = new GameConsole();
    genGameObservers();
   
  }
  
  /**
   * Constructor de la Facade.
   * @param name Nombre del nivel.
   * @param num Numero de bricks en el nivel.
   * @param prob Probabilidades de aparición de un SoftBrick en el nivel.
   */
  
  public HomeworkTwoFacade(String name, int num, double prob) {
    bonusHandler = new BonusHandler();
    pl = new Player();
    levelHandler = new LevelHandler(newLevelWithSoftAndStoneBricks(name, num, prob));
    number = num;
    curScore = new Score();
    probability = prob;
    gameConsole = new GameConsole();
    genGameObservers();
  }

  private void genGameObservers() {
    observers = new ArrayList<GameObserver>();
    observers.add(new BonusObserver(gameConsole));
    observers.add(new LevelObserver(gameConsole));
    observers.add(new ScoreObserver(gameConsole));
    
  }
  
  public long earnedScore() {
    return pl.getTotalPoints();
  }

  public List<IBrick> getBricks() {
    return levelHandler.getBricks();
  }

  public ILevel getCurrentLevel() {
    return levelHandler.getCurrentLevel();
  }

  public String getLevelName() {
    return levelHandler.getLevelName();
  }

  public int getNumberOfHearts() {
    return pl.getNumberOfHearts();
  }

  public int getRequiredPoints() {
    return levelHandler.getRequiredPoints();
  }

  public boolean hasNextLevel() {
    return levelHandler.hasNextLevel();
  }

  public int lossOfHeart() {
    pl.lossOfHeart();
    return pl.getNumberOfHearts();
  }

  public ILevel newLevelWithSoftAndStoneBricks(String levelName, int number, double probability) {
    return new RealLevel(levelName, number, probability,pl,gameConsole);
  }

  public long numberOfBricks() {
    return levelHandler.getNumberOfBricks();
  }

  /**
   * Setea el nivel actual del juego.
   * @param newLevel nivel al que se seteara el juego.
   */
  public void setCurrentLevel(final ILevel newLevel) {
    levelHandler.setCurrentLevel(newLevel);
  }
  
  /**
   * Funcion que utiliza la representacion como String
   * de los IBricks y luego los retorna para ser representado
   * en la consola.
   * @param rlevel Un ILevel que se necesita como String.
   * @return La representacion del ILevel para la consola.
   */
  
  public String spawnBricks(final ILevel rlevel) {
    return LevelHandler.spawnBricks(rlevel);
  }
  
  public void setNextLevel() {
    levelHandler.setNextLevel(
        newLevelWithSoftAndStoneBricks("Another Level",number,probability * 1.1));
  }
  
  public List<IBonus> newBonuses(final int number, final double probability) {
    return BonusHandler.genBonuses(number,probability,pl,levelHandler.getObservers());
  }
  
  public void registerBonuses(final List<IBonus> bonuses) {
    bonusHandler.setBonuses(bonuses);
  }
  
  public void setGameConsoleOutput(final PrintStream printStream) {
    gameConsole.setStream(printStream);
  }
  
  public void autoSwitchToNextLevel() {
    levelHandler.autoSwitchToNextLevel();
  }
  
  public boolean hasCurrentLevel() {
    return levelHandler.hasCurrentLevel();
  }
  
  public IBonus newExtraScore() {
    return new AddScoreModifier(pl,levelHandler.getObservers());
  }
  
  public IBonus newExtraHeart() {
    return new AddLifeModifier(pl,levelHandler.getObservers());
  }
  
  public IBonus newScoreDiscount() {
    return new LossScoreModifier(pl,levelHandler.getObservers());
  }
  
  public IBonus newHeartDiscount() {
    return new LossLifeModifier(pl,levelHandler.getObservers());
  }
  
  
}
