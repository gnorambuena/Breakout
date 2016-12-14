package com.cc3002.breakout.facade;

import com.cc3002.breakout.logic.bonus.IBonus;
import com.cc3002.breakout.logic.brick.IBrick;
import com.cc3002.breakout.logic.level.GameConsole;
import com.cc3002.breakout.logic.level.ILevel;
import com.cc3002.breakout.logic.level.LevelHandler;
import com.cc3002.breakout.logic.level.Player;

import java.io.PrintStream;
import java.util.List;
import java.util.Observer;


/**
 * Facade de la tarea 2.
 * @author Gabriel Norambuena.
 *
 */

public class HomeworkTwoFacade {
  Flyweight flyweight;
  
  /**
   * Constructor vacío del Facade,
   * crea una instancia null de ILevel,
   * una instancia de la clase Player,
   * y una instancia de la clase Score.
   */
  
  public HomeworkTwoFacade() {
    flyweight = new Flyweight();
  }
  
  
  public List<Observer> getGameObservers() {
    return flyweight.getObservers();
  }
  
  public Player getPlayer() {
    return flyweight.getPlayer();
  }
  
  public GameConsole getGameConsole() {
    return flyweight.getGameConsole();
  }
  
  public long earnedScore() {
    return flyweight.getEarnedScore();
  }

  public List<IBrick> getBricks() {
    return flyweight.getBricks();
  }

  public ILevel getCurrentLevel() {
    return flyweight.getCurrentLevel();
  }

  public String getLevelName() {
    return flyweight.getLevelName();
  }

  public int getNumberOfHearts() {
    return flyweight.getNumberOfHearts();
  }

  public int getRequiredPoints() {
    return flyweight.getRequiredPoints();
  }
  
  public boolean hasNextLevel() {
    return flyweight.hasNextLevel();
  }

  public int lossOfHeart() {
    return flyweight.lossOfHeart();
  }

  public ILevel newLevelWithSoftAndStoneBricks(String levelName, int number, double probability) {
    return flyweight.newLevelWithSoftAndStoneBricks(levelName,number,probability);
  }

  public ILevel newLevelWithSpecialBricks(String levelName, int number, double probability) {
    return flyweight.newLevelWithSpecialBricks(levelName,number,probability);
  }
  
  public long numberOfBricks() {
    return flyweight.getNumberOfBricks();
  }

  /**
   * Setea el nivel actual del juego.
   * @param newLevel nivel al que se seteara el juego.
   */
  public void setCurrentLevel(final ILevel newLevel) {
    flyweight.setCurrentLevel(newLevel);
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
    flyweight.setNextLevel();
  }
  
  public void setNextLevel(final ILevel newLevel) {
    flyweight.setNextLevel(newLevel);
  }
  
  public List<IBonus> newBonuses(final int number, final double probability) {
    return flyweight.newBonuses(number,probability);
  }
  
  public void registerBonuses(final List<IBonus> bonuses) {
    flyweight.registerBonuses(bonuses);
  }
  
  public void setGameConsoleOutput(final PrintStream printStream) {
    flyweight.setGameConsoleOutput(printStream);
  }
  
  public void autoSwitchToNextLevel() {
    flyweight.setAutoSwitch();
    flyweight.autoSwitchToNextLevel();
  }
  
  public boolean hasCurrentLevel() {
    return flyweight.hasCurrentLevel();
  }
  
  public IBonus newExtraScore() {
    return flyweight.newExtraScore();
  }
  
  public IBonus newExtraHeart() {
    return flyweight.newExtraHeart();
  }
  
  public IBonus newScoreDiscount() {
    return flyweight.newScoreDiscount();
  }
  
  public IBonus newHeartDiscount() {
    return flyweight.newHeartDiscount();
  }


  public Flyweight getFlyweight() {
    return flyweight;
  }
  
}
